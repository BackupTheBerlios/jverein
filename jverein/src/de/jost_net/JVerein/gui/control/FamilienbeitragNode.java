/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/control/FamilienbeitragNode.java,v $
 * $Revision: 1.2 $
 * $Date: 2011/08/01 18:26:05 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: FamilienbeitragNode.java,v $
 * Revision 1.2  2011/08/01 18:26:05  jost
 * Nodes typisiert und zus�tzliche Ausgabe des Geburtsdatums
 *
 * Revision 1.1  2011-07-24 18:03:37  jost
 * Neu: Auflistung Familienbeitr�ge
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.control;

import java.rmi.RemoteException;
import java.util.ArrayList;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.keys.ArtBeitragsart;
import de.jost_net.JVerein.rmi.Beitragsgruppe;
import de.jost_net.JVerein.rmi.Mitglied;
import de.jost_net.JVerein.util.JVDateFormatTTMMJJJJ;
import de.willuhn.datasource.GenericIterator;
import de.willuhn.datasource.GenericObject;
import de.willuhn.datasource.GenericObjectNode;
import de.willuhn.datasource.pseudo.PseudoIterator;
import de.willuhn.datasource.rmi.DBIterator;
import de.willuhn.logging.Logger;

public class FamilienbeitragNode implements GenericObjectNode
{
  public static final int ROOT = 0;

  public static final int ZAHLER = 1;

  public static final int ANGEHOERIGER = 2;

  private int type = ROOT;

  private String id;

  private Mitglied mitglied;

  private FamilienbeitragNode parent = null;

  private ArrayList<FamilienbeitragNode> children;

  public FamilienbeitragNode() throws RemoteException
  {
    this.parent = null;
    this.type = ROOT;
    this.children = new ArrayList<FamilienbeitragNode>();
    DBIterator it = Einstellungen.getDBService().createList(
        Beitragsgruppe.class);
    it.addFilter("beitragsart = ?",
        new Object[] { ArtBeitragsart.FAMILIE_ZAHLER });
    while (it.hasNext())
    {
      Beitragsgruppe bg = (Beitragsgruppe) it.next();
      DBIterator it2 = Einstellungen.getDBService().createList(Mitglied.class);
      it2.addFilter("beitragsgruppe = ?", new Object[] { bg.getID() });
      it2.addFilter("austritt is null");
      while (it2.hasNext())
      {
        Mitglied m = (Mitglied) it2.next();
        FamilienbeitragNode fbn = new FamilienbeitragNode(this, m);
        children.add(fbn);
      }
    }
  }

  public FamilienbeitragNode(FamilienbeitragNode parent, Mitglied m)
      throws RemoteException
  {
    this.parent = parent;
    this.mitglied = m;
    this.type = ZAHLER;
    this.children = new ArrayList<FamilienbeitragNode>();
    DBIterator it = Einstellungen.getDBService().createList(Mitglied.class);
    it.addFilter("zahlerid = ?", new Object[] { m.getID() });
    it.addFilter("austritt is null");
    while (it.hasNext())
    {
      FamilienbeitragNode fbn = new FamilienbeitragNode(this,
          (Mitglied) it.next(), 1);
      children.add(fbn);
    }
  }

  public FamilienbeitragNode(FamilienbeitragNode parent, Mitglied m, int dummy)
  {
    this.parent = parent;
    this.type = ANGEHOERIGER;
    this.mitglied = m;
    this.children = new ArrayList<FamilienbeitragNode>();
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
    return new String[] { "name", "vorname", "blz", "konto" };
  }

  public Object getAttribute(String name)
  {
    try
    {
      if (mitglied == null)
      {
        return "Familienbeitr�ge";
      }
      JVDateFormatTTMMJJJJ jvttmmjjjj = new JVDateFormatTTMMJJJJ();
      return mitglied.getNameVorname()
          + (mitglied.getGeburtsdatum() != null ? ", "
              + jvttmmjjjj.format(mitglied.getGeburtsdatum()) : "")
          + (mitglied.getBlz().length() > 0 ? ", " + mitglied.getBlz() + ", "
              + mitglied.getKonto() : "");
    }
    catch (RemoteException e)
    {
      Logger.error("Fehler", e);
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
      return PseudoIterator.fromArray(children
          .toArray(new GenericObject[children.size()]));
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
