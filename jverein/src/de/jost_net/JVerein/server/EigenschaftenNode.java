/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/server/EigenschaftenNode.java,v $
 * $Revision: 1.3 $
 * $Date: 2009/12/12 16:27:04 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: EigenschaftenNode.java,v $
 * Revision 1.3  2009/12/12 16:27:04  jost
 * todo's entfernt.
 *
 * Revision 1.2  2009/11/22 16:20:36  jost
 * Sortierung der Nodes
 *
 * Revision 1.1  2009/11/17 21:03:28  jost
 * Neu: Eigenschaft und EigenschaftGruppe
 *
 **********************************************************************/
package de.jost_net.JVerein.server;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.rmi.Eigenschaft;
import de.jost_net.JVerein.rmi.EigenschaftGruppe;
import de.jost_net.JVerein.rmi.Eigenschaften;
import de.jost_net.JVerein.rmi.Mitglied;
import de.willuhn.datasource.GenericIterator;
import de.willuhn.datasource.GenericObject;
import de.willuhn.datasource.GenericObjectNode;
import de.willuhn.datasource.pseudo.PseudoIterator;
import de.willuhn.datasource.rmi.DBIterator;

public class EigenschaftenNode implements GenericObjectNode
{
  private EigenschaftenNode parent = null;

  private Mitglied mitglied = null;

  private EigenschaftGruppe eigenschaftgruppe = null;

  private Eigenschaft eigenschaft = null;

  private Eigenschaften eigenschaften = null;

  private ArrayList<GenericObjectNode> childrens;

  private boolean preset;

  public static final int NONE = 0;

  public static final int ROOT = 1;

  public static final int EIGENSCHAFTGRUPPE = 2;

  public static final int EIGENSCHAFTEN = 3;

  private int nodetype = NONE;

  private ArrayList<String> eigenschaftids = new ArrayList<String>();

  public EigenschaftenNode(Mitglied mitglied) throws RemoteException
  {
    this(mitglied, "");
  }

  public EigenschaftenNode(String vorbelegung) throws RemoteException
  {
    this(null, vorbelegung);
  }

  private EigenschaftenNode(Mitglied mitglied, String vorbelegung)
      throws RemoteException
  {
    this.mitglied = mitglied;
    StringTokenizer stt = new StringTokenizer(vorbelegung, ",");
    while (stt.hasMoreElements())
    {
      eigenschaftids.add(stt.nextToken());
    }
    childrens = new ArrayList<GenericObjectNode>();
    nodetype = ROOT;
    DBIterator it = Einstellungen.getDBService().createList(
        EigenschaftGruppe.class);
    it.setOrder("order by bezeichnung");
    while (it.hasNext())
    {
      EigenschaftGruppe eg = (EigenschaftGruppe) it.next();
      childrens.add(new EigenschaftenNode(this, mitglied, eg, eigenschaftids));
    }
  }

  private EigenschaftenNode(EigenschaftenNode parent, Mitglied mitglied,
      EigenschaftGruppe eg, ArrayList<String> eigenschaftsids)
      throws RemoteException
  {
    this.parent = parent;
    this.mitglied = mitglied;
    childrens = new ArrayList<GenericObjectNode>();
    this.eigenschaftgruppe = eg;
    nodetype = EIGENSCHAFTGRUPPE;
    DBIterator it = Einstellungen.getDBService().createList(Eigenschaft.class);
    it.addFilter("eigenschaftgruppe = ?", new Object[] { eigenschaftgruppe
        .getID() });
    it.setOrder("order by bezeichnung");
    while (it.hasNext())
    {
      Eigenschaft eigenschaft = (Eigenschaft) it.next();
      Eigenschaften eigenschaften = null;
      if (mitglied != null)
      {
        DBIterator it2 = Einstellungen.getDBService().createList(
            Eigenschaften.class);
        it2.addFilter("mitglied = ? AND eigenschaft = ?", new Object[] {
            mitglied.getID(), eigenschaft.getID() });
        if (it2.hasNext())
        {
          eigenschaften = (Eigenschaften) it2.next();
        }
      }
      childrens.add(new EigenschaftenNode(this, mitglied, eigenschaft,
          eigenschaften, eigenschaftsids));
    }
  }

  private EigenschaftenNode(EigenschaftenNode parent, Mitglied mitglied,
      Eigenschaft eigenschaft, Eigenschaften eigenschaften,
      ArrayList<String> eigenschaftids) throws RemoteException
  {
    this.parent = parent;
    nodetype = EIGENSCHAFTEN;
    this.mitglied = mitglied;
    this.eigenschaft = eigenschaft;
    this.eigenschaften = eigenschaften;
    if (eigenschaftids.contains(this.eigenschaft.getID()))
    {
      preset = true;
    }
  }

  public GenericIterator getChildren() throws RemoteException
  {
    if (childrens == null)
    {
      return null;
    }
    return PseudoIterator.fromArray((GenericObject[]) childrens
        .toArray(new GenericObject[childrens.size()]));
  }

  public boolean removeChild(GenericObjectNode child)
  {
    return childrens.remove(child);
  }

  public EigenschaftenNode getParent() throws RemoteException
  {
    return parent;
  }

  public GenericIterator getPath() throws RemoteException
  {
    return null;
  }

  public GenericIterator getPossibleParents() throws RemoteException
  {
    return null;
  }

  public boolean hasChild(GenericObjectNode object) throws RemoteException
  {
    return childrens.size() > 0;
  }

  public boolean equals(GenericObject other) throws RemoteException
  {
    return false;
  }

  public Object getAttribute(String name) throws RemoteException
  {
    switch (nodetype)
    {
      case ROOT:
      {
        return "Eigenschaften";
      }
      case EIGENSCHAFTGRUPPE:
      {
        return eigenschaftgruppe.getBezeichnung();
      }
      case EIGENSCHAFTEN:
      {
        return eigenschaft.getBezeichnung();
      }
    }
    return "bla";
  }

  public String[] getAttributeNames() throws RemoteException
  {
    return null;
  }

  public String getID() throws RemoteException
  {
    return null;
  }

  public String getPrimaryAttribute() throws RemoteException
  {
    return null;
  }

  public Object getObject()
  {
    switch (nodetype)
    {
      case ROOT:
      {
        return mitglied;
      }
      case EIGENSCHAFTGRUPPE:
      {
        return eigenschaftgruppe;
      }
      case EIGENSCHAFTEN:
      {
        return eigenschaft;
      }
    }
    return null;
  }

  public int getNodeType()
  {
    return nodetype;
  }

  public Mitglied getMitglied()
  {
    return this.mitglied;
  }

  public Eigenschaft getEigenschaft()
  {
    return this.eigenschaft;
  }

  public Eigenschaften getEigenschaften()
  {
    return this.eigenschaften;
  }

  public boolean isPreset()
  {
    return preset;
  }
}
