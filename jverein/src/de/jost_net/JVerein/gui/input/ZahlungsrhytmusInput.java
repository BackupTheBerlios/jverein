/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/input/Attic/ZahlungsrhytmusInput.java,v $
 * $Revision: 1.1 $
 * $Date: 2007/12/02 13:40:32 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: ZahlungsrhytmusInput.java,v $
 * Revision 1.1  2007/12/02 13:40:32  jost
 * Neu: Beitragsmodelle
 *
 **********************************************************************/

package de.jost_net.JVerein.gui.input;

import java.rmi.RemoteException;
import java.util.ArrayList;

import de.willuhn.datasource.GenericIterator;
import de.willuhn.datasource.GenericObject;
import de.willuhn.datasource.pseudo.PseudoIterator;
import de.willuhn.jameica.gui.input.SelectInput;

/**
 * Combo-Box, fuer die Auswahl des Zahlungsrhytmusses.
 */
public class ZahlungsrhytmusInput extends SelectInput
{
  public static final int JAEHRLICH = 12;

  public static final int HALBJAEHRLICH = 6;

  public static final int VIERTELJAEHRLICH = 3;

  public static final int MONATLICH = 1;

  public ZahlungsrhytmusInput() throws RemoteException
  {
    super(init(), new ZahlungsrhytmusObject(JAEHRLICH));
  }

  public ZahlungsrhytmusInput(int zahlungsrhytmus) throws RemoteException
  {
    super(init(), new ZahlungsrhytmusObject(zahlungsrhytmus));
  }

  /**
   * @return initialisiert die Liste der Optionen.
   * @throws RemoteException
   */
  private static GenericIterator init() throws RemoteException
  {

    ArrayList<ZahlungsrhytmusObject> l = new ArrayList<ZahlungsrhytmusObject>();
    l.add(new ZahlungsrhytmusObject(JAEHRLICH));
    l.add(new ZahlungsrhytmusObject(HALBJAEHRLICH));
    l.add(new ZahlungsrhytmusObject(VIERTELJAEHRLICH));
    l.add(new ZahlungsrhytmusObject(MONATLICH));
    return PseudoIterator.fromArray((ZahlungsrhytmusObject[]) l
        .toArray(new ZahlungsrhytmusObject[l.size()]));
  }

  /**
   * @see de.willuhn.jameica.gui.input.Input#getValue()
   */
  public Object getValue()
  {
    ZahlungsrhytmusObject o = (ZahlungsrhytmusObject) super.getValue();
    if (o == null)
    {
      return new Integer(JAEHRLICH);
    }
    return new Integer(o.zahlungsrhytmus);
  }

  /**
   * Hilfs-Objekt zur Anzeige der Labels.
   */
  private static class ZahlungsrhytmusObject implements GenericObject
  {
    public int zahlungsrhytmus = ZahlungsrhytmusInput.JAEHRLICH;

    private String label = null;

    private ZahlungsrhytmusObject(int zahlungsrhytmus)
    {
      this.zahlungsrhytmus = zahlungsrhytmus;

      switch (zahlungsrhytmus)
      {
        case ZahlungsrhytmusInput.JAEHRLICH:
          this.label = "j�hrlich";
          break;
        case ZahlungsrhytmusInput.HALBJAEHRLICH:
          this.label = "halbj�hrlich";
          break;
        case ZahlungsrhytmusInput.VIERTELJAEHRLICH:
          this.label = "viertelj�hrlich";
          break;
        case ZahlungsrhytmusInput.MONATLICH:
          this.label = "monatlich";
          break;
      }
    }

    public Object getAttribute(String arg0) throws RemoteException
    {
      return label;
    }

    public String[] getAttributeNames() throws RemoteException
    {
      return new String[] { "name" };
    }

    public String getID() throws RemoteException
    {
      return "" + zahlungsrhytmus;
    }

    public String getPrimaryAttribute() throws RemoteException
    {
      return "name";
    }

    public boolean equals(GenericObject arg0) throws RemoteException
    {
      if (arg0 == null || !(arg0 instanceof ZahlungsrhytmusObject))
      {
        return false;
      }
      return this.getID().equals(arg0.getID());
    }

  }
}
