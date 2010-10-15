/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/control/MitgliedskontoNode.java,v $
 * $Revision: 1.2 $
 * $Date: 2010/10/15 09:58:26 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: MitgliedskontoNode.java,v $
 * Revision 1.2  2010/10/15 09:58:26  jost
 * Code aufger�umt
 *
 * Revision 1.1  2010-07-25 18:32:06  jost
 * Neu: Mitgliedskonto
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.control;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.rmi.Buchung;
import de.jost_net.JVerein.rmi.Mitglied;
import de.jost_net.JVerein.rmi.Mitgliedskonto;
import de.willuhn.datasource.GenericIterator;
import de.willuhn.datasource.GenericObject;
import de.willuhn.datasource.GenericObjectNode;
import de.willuhn.datasource.pseudo.PseudoIterator;
import de.willuhn.datasource.rmi.DBIterator;

public class MitgliedskontoNode implements GenericObjectNode
{

  public static final int UNBEKANNT = 0;

  public static final int MITGLIED = 1;

  public static final int SOLL = 2;

  public static final int IST = 3;

  private int type = UNBEKANNT;

  private String id;

  private Mitglied mitglied;

  private String name = null;

  private Date datum = null;

  private String zweck1 = null;

  private String zweck2 = null;

  private Integer zahlungsweg = null;

  private Double soll = null;

  private Double ist = null;

  private MitgliedskontoNode parent = null;

  private ArrayList<MitgliedskontoNode> children;

  public MitgliedskontoNode(Mitglied m) throws RemoteException
  {
    type = MITGLIED;
    this.mitglied = m;
    this.zahlungsweg = m.getZahlungsweg();
    this.name = m.getNameVorname();
    this.soll = new Double(0);
    this.ist = new Double(0);
    this.children = new ArrayList<MitgliedskontoNode>();
    this.id = m.getID();
    DBIterator it = Einstellungen.getDBService().createList(
        Mitgliedskonto.class);
    it.addFilter("mitglied = ?", new Object[] { m.getID()});
    it.setOrder("order by datum desc");
    while (it.hasNext())
    {
      Mitgliedskonto mk = (Mitgliedskonto) it.next();
      soll += mk.getBetrag();
      MitgliedskontoNode mkn = new MitgliedskontoNode(this, mk);
      children.add(mkn);
    }
    for (MitgliedskontoNode node : children)
    {
      for (MitgliedskontoNode nodeist : node.children)
      {
        ist += nodeist.ist;
      }
    }
  }

  public MitgliedskontoNode(MitgliedskontoNode parent, Mitgliedskonto mk)
      throws RemoteException
  {
    this.type = SOLL;
    this.parent = parent;
    this.id = mk.getID();
    this.mitglied = mk.getMitglied();
    this.zahlungsweg = mk.getZahlungsweg();
    this.datum = mk.getDatum();
    this.zweck1 = mk.getZweck1();
    this.zweck2 = mk.getZweck2();
    this.soll = mk.getBetrag();
    this.ist = mk.getIstBetrag();
    if (this.type == SOLL)
    {
      this.children = new ArrayList<MitgliedskontoNode>();
      DBIterator it = Einstellungen.getDBService().createList(Buchung.class);
      it.addFilter("mitgliedskonto = ?", new Object[] { mk.getID()});
      it.setOrder("order by datum desc");
      ist = 0d;
      while (it.hasNext())
      {
        Buchung bist = (Buchung) it.next();
        MitgliedskontoNode mkn = new MitgliedskontoNode(this, mk, bist);
        ist += bist.getBetrag();
        children.add(mkn);
      }
    }

  }

  public MitgliedskontoNode(MitgliedskontoNode parent, Mitgliedskonto mk,
      Buchung bist) throws RemoteException
  {
    this.type = IST;
    this.parent = parent;
    this.id = bist.getID();
    this.mitglied = mk.getMitglied();
    this.zahlungsweg = mk.getZahlungsweg();
    this.datum = bist.getDatum();
    this.zweck1 = bist.getZweck();
    this.zweck2 = bist.getZweck2();
    this.ist = bist.getBetrag();
  }

  public int getType()
  {
    return type;
  }

  public Mitglied getMitglied()
  {
    return mitglied;
  }

  public String getPrimaryAttribute()
  {
    return null;
  }

  public String getID()
  {
    return id;
  }

  public String[] getAttributeNames()
  {
    return new String[] { "name", "datum", "zweck1", "zweck2", "zahlungsweg",
        "soll", "ist", "differenz"};
  }

  public Object getAttribute(String name)
  {
    if (name.equals("name"))
    {
      return this.name;
    }
    else if (name.equals("datum"))
    {
      return datum;
    }
    else if (name.equals("zweck1"))
    {
      return zweck1;
    }
    else if (name.equals("zweck2"))
    {
      return zweck2;
    }
    else if (name.equals("zahlungsweg"))
    {
      return zahlungsweg;
    }
    else if (name.equals("soll"))
    {
      return soll;
    }
    else if (name.equals("ist"))
    {
      return ist;
    }
    else if (name.equals("differenz"))
    {
      if (ist == null)
      {
        ist = new Double(0);
      }
      if (type == MITGLIED || type == SOLL)
      {
        double differenz = ist - soll;
        return differenz != 0d ? differenz : null;
      }
      else
      {
        return null;
      }
    }
    return null;
  }

  public boolean equals(GenericObject other)
  {
    return false;
  }

  public boolean hasChild(GenericObjectNode object)
  {
    return children.size() > 0;
  }

  public GenericIterator getPossibleParents()
  {
    return null;
  }

  public GenericIterator getPath()
  {
    return null;
  }

  public GenericObjectNode getParent()
  {
    return parent;
  }

  public GenericIterator getChildren() throws RemoteException
  {
    if (children != null)
    {
      return PseudoIterator.fromArray(children.toArray(new GenericObject[children.size()]));
    }
    return null;
  }

  public void remove()
  {
    if (parent != null)
    {
      parent.children.remove(this);
    }
  }

}
