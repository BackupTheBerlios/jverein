/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/io/ArbeitseinsatzZeile.java,v $
 * $Revision: 1.1 $
 * $Date: 2010/11/22 21:00:51 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: ArbeitseinsatzZeile.java,v $
 * Revision 1.1  2010/11/22 21:00:51  jost
 * Initial Commit
 *
 **********************************************************************/
package de.jost_net.JVerein.io;

import java.rmi.RemoteException;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.rmi.Mitglied;
import de.willuhn.datasource.GenericObject;

/**
 * Hilfs-Objekt
 */
public class ArbeitseinsatzZeile implements GenericObject
{
  private String mitgliedid;

  private Double soll;

  private Double ist;

  private Double stundensatz;

  public ArbeitseinsatzZeile(String mitgliedid, Double soll, Double ist,
      Double stundensatz)
  {
    this.mitgliedid = mitgliedid;
    this.soll = soll;
    this.ist = ist;
    if (ist == null)
    {
      ist = new Double(0);
    }
    this.stundensatz = stundensatz;
  }

  public Object getAttribute(String arg0) throws RemoteException
  {
    if (arg0.equals("mitglied"))
    {
      Mitglied mitglied = (Mitglied) Einstellungen.getDBService().createObject(
          Mitglied.class, mitgliedid);
      return mitglied;
    }
    else if (arg0.equals("namevorname"))
    {
      Mitglied mitglied = (Mitglied) Einstellungen.getDBService().createObject(
          Mitglied.class, mitgliedid);
      return mitglied.getNameVorname();
    }
    else if (arg0.equals("mitgliedid"))
    {
      return mitgliedid;
    }
    else if (arg0.equals("soll"))
    {
      return soll;
    }
    else if (arg0.equals("ist"))
    {
      return ist;
    }
    else if (arg0.equals("stundensatz"))
    {
      return stundensatz;
    }
    else if (arg0.equals("differenz"))
    {
      return ist - soll;
    }
    else if (arg0.equals("gesamtbetrag"))
    {
      return (ist - soll) * stundensatz;
    }
    throw new RemoteException("Ung�ltige Spaltenbezeichung: " + arg0);
  }

  public String[] getAttributeNames()
  {
    return new String[] { "mitglied", "mitgliedid", "namevorname", "soll",
        "ist", "stundensatz", "differenz", "gesamtbetrag" };
  }

  public String getID() throws RemoteException
  {
    return mitgliedid;
  }

  public String getPrimaryAttribute()
  {
    return "mitglied";
  }

  public boolean equals(GenericObject arg0) throws RemoteException
  {
    if (arg0 == null || !(arg0 instanceof ArbeitseinsatzZeile))
    {
      return false;
    }
    return this.getID().equals(arg0.getID());
  }
}
