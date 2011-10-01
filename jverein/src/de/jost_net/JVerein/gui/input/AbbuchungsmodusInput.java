/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/input/AbbuchungsmodusInput.java,v $
 * $Revision: 1.13 $
 * $Date: 2011/10/01 21:44:04 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: AbbuchungsmodusInput.java,v $
 * Revision 1.13  2011/10/01 21:44:04  jost
 * Log-Eintr�ge entfernt. Zeigt Eclipse-History-View viel besser an. Macht den Quelltext schlanker.
 *
 **********************************************************************/

package de.jost_net.JVerein.gui.input;

import java.rmi.RemoteException;
import java.util.ArrayList;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.keys.Abrechnungsmodi;
import de.jost_net.JVerein.keys.Beitragsmodel;
import de.willuhn.datasource.GenericIterator;
import de.willuhn.datasource.GenericObject;
import de.willuhn.datasource.pseudo.PseudoIterator;
import de.willuhn.jameica.gui.input.SelectInput;

/**
 * Combo-Box, fuer die Auswahl des Abbuchungsmodus.
 */
public class AbbuchungsmodusInput extends SelectInput
{

  public AbbuchungsmodusInput(int abbuchungsmodus) throws RemoteException
  {
    super(init(), new AbbuchungsmodusObject(abbuchungsmodus));
  }

  /**
   * @return initialisiert die Liste der Optionen.
   * @throws RemoteException
   */
  private static GenericIterator init() throws RemoteException
  {
    ArrayList<AbbuchungsmodusObject> l = new ArrayList<AbbuchungsmodusObject>();
    l.add(new AbbuchungsmodusObject(Abrechnungsmodi.KEINBEITRAG));
    if (Einstellungen.getEinstellung().getBeitragsmodel() == Beitragsmodel.GLEICHERTERMINFUERALLE)
    {
      l.add(new AbbuchungsmodusObject(Abrechnungsmodi.ALLE));
      l.add(new AbbuchungsmodusObject(Abrechnungsmodi.EINGETRETENEMITGLIEDER));
    }
    if (Einstellungen.getEinstellung().getBeitragsmodel() == Beitragsmodel.MONATLICH12631)
    {
      l.add(new AbbuchungsmodusObject(Abrechnungsmodi.JAHAVIMO));
      l.add(new AbbuchungsmodusObject(Abrechnungsmodi.JAVIMO));
      l.add(new AbbuchungsmodusObject(Abrechnungsmodi.HAVIMO));
      l.add(new AbbuchungsmodusObject(Abrechnungsmodi.VIMO));
      l.add(new AbbuchungsmodusObject(Abrechnungsmodi.JA));
      l.add(new AbbuchungsmodusObject(Abrechnungsmodi.HA));
      l.add(new AbbuchungsmodusObject(Abrechnungsmodi.VI));
      l.add(new AbbuchungsmodusObject(Abrechnungsmodi.MO));
      l.add(new AbbuchungsmodusObject(Abrechnungsmodi.EINGETRETENEMITGLIEDER));
    }
    return PseudoIterator.fromArray(l.toArray(new AbbuchungsmodusObject[l
        .size()]));
  }

  /**
   * @see de.willuhn.jameica.gui.input.Input#getValue()
   */
  @Override
  public Object getValue()
  {
    AbbuchungsmodusObject o = (AbbuchungsmodusObject) super.getValue();
    if (o == null)
    {
      return Integer.valueOf(Abrechnungsmodi.ALLE);
    }
    return Integer.valueOf(o.abbuchungsmodus);
  }

  /**
   * Hilfs-Objekt zur Anzeige der Labels.
   */
  private static class AbbuchungsmodusObject implements GenericObject
  {

    public int abbuchungsmodus;

    private String label = null;

    private AbbuchungsmodusObject(int abbuchungsmodus)
    {
      this.abbuchungsmodus = abbuchungsmodus;
      this.label = Abrechnungsmodi.get(abbuchungsmodus);
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
      return "" + abbuchungsmodus;
    }

    public String getPrimaryAttribute()
    {
      return "name";
    }

    public boolean equals(GenericObject arg0) throws RemoteException
    {
      if (arg0 == null || !(arg0 instanceof AbbuchungsmodusObject))
      {
        return false;
      }
      return this.getID().equals(arg0.getID());
    }
  }
}
