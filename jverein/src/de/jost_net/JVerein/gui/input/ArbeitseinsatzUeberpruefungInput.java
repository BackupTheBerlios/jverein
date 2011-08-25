/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/input/ArbeitseinsatzUeberpruefungInput.java,v $
 * $Revision: 1.2 $
 * $Date: 2011/08/25 07:41:50 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: ArbeitseinsatzUeberpruefungInput.java,v $
 * Revision 1.2  2011/08/25 07:41:50  jost
 * Zus�tzliche Filtereinstellung
 *
 * Revision 1.1  2010-11-22 20:58:53  jost
 * Initial Commit
 *
 **********************************************************************/

package de.jost_net.JVerein.gui.input;

import java.rmi.RemoteException;
import java.util.ArrayList;

import de.jost_net.JVerein.JVereinPlugin;
import de.willuhn.datasource.GenericIterator;
import de.willuhn.datasource.GenericObject;
import de.willuhn.datasource.pseudo.PseudoIterator;
import de.willuhn.jameica.gui.input.SelectInput;

public class ArbeitseinsatzUeberpruefungInput extends SelectInput
{

  public static final int MINDERLEISTUNG = 1;

  public static final int PASSENDELEISTUNG = 2;

  public static final int MEHRLEISTUNG = 3;

  public static final int ALLE = 4;

  public ArbeitseinsatzUeberpruefungInput(int schluessel)
      throws RemoteException
  {
    super(init(), new ArbeitseinsatzUeberpruefungObject(schluessel));
  }

  /**
   * @return initialisiert die Liste der Optionen.
   * @throws RemoteException
   */
  private static GenericIterator init() throws RemoteException
  {
    ArrayList<ArbeitseinsatzUeberpruefungObject> l = new ArrayList<ArbeitseinsatzUeberpruefungObject>();
    l.add(new ArbeitseinsatzUeberpruefungObject(MINDERLEISTUNG));
    l.add(new ArbeitseinsatzUeberpruefungObject(PASSENDELEISTUNG));
    l.add(new ArbeitseinsatzUeberpruefungObject(MEHRLEISTUNG));
    l.add(new ArbeitseinsatzUeberpruefungObject(ALLE));
    return PseudoIterator.fromArray(l
        .toArray(new ArbeitseinsatzUeberpruefungObject[l.size()]));
  }

  /**
   * @see de.willuhn.jameica.gui.input.Input#getValue()
   */
  @Override
  public Object getValue()
  {
    ArbeitseinsatzUeberpruefungObject o = (ArbeitseinsatzUeberpruefungObject) super
        .getValue();
    return o.schluessel;
  }

  /**
   * Hilfs-Objekt zur Anzeige der Labels.
   */
  private static class ArbeitseinsatzUeberpruefungObject implements
      GenericObject
  {

    public int schluessel;

    private String label = null;

    private ArbeitseinsatzUeberpruefungObject(int schluessel)
    {
      this.schluessel = schluessel;

      if (schluessel == ALLE)
      {
        this.label = JVereinPlugin.getI18n().tr("alle");
      }
      else if (schluessel == MINDERLEISTUNG)
      {
        this.label = JVereinPlugin.getI18n().tr("Minderleistung");
      }
      else if (schluessel == PASSENDELEISTUNG)
      {
        this.label = JVereinPlugin.getI18n().tr("passende Leistung");
      }
      else if (schluessel == MEHRLEISTUNG)
      {
        this.label = JVereinPlugin.getI18n().tr("Mehrleistung");
      }
      else
      {
        this.label = "Programmfehler";
      }
    }

    public Object getAttribute(String arg0)
    {
      return label;
    }

    public String[] getAttributeNames()
    {
      return new String[] { "name" };
    }

    public String getID()
    {
      return schluessel + "";
    }

    public String getPrimaryAttribute()
    {
      return "name";
    }

    public boolean equals(GenericObject arg0) throws RemoteException
    {
      if (arg0 == null || !(arg0 instanceof ArbeitseinsatzUeberpruefungObject))
      {
        return false;
      }
      return this.getID().equals(arg0.getID());
    }
  }
}
