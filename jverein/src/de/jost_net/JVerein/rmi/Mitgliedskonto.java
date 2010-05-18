/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/rmi/Mitgliedskonto.java,v $
 * $Revision: 1.2 $
 * $Date: 2010/05/18 20:24:32 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: Mitgliedskonto.java,v $
 * Revision 1.2  2010/05/18 20:24:32  jost
 * Anpassung Klassenname
 *
 * Revision 1.1  2010/04/28 06:17:50  jost
 * Neu: Mitgliedskonto
 *
 **********************************************************************/
package de.jost_net.JVerein.rmi;

import java.rmi.RemoteException;
import java.util.Date;

import de.willuhn.datasource.rmi.DBObject;

public interface Mitgliedskonto extends DBObject
{
  public String getID() throws RemoteException;

  public Abrechnungslauf getAbrechnungslauf() throws RemoteException;

  public void setAbrechnungslauf(Abrechnungslauf abrechnungslauf)
      throws RemoteException;

  public Mitglied getMitglied() throws RemoteException;

  public void setMitglied(Mitglied mitglied) throws RemoteException;

  public String getBuchungstyp() throws RemoteException;

  public void setBuchungstyp(String buchungstyp) throws RemoteException;

  public Date getDatum() throws RemoteException;

  public void setDatum(Date datum) throws RemoteException;

  public String getZweck1() throws RemoteException;

  public void setZweck1(String zweck1) throws RemoteException;

  public String getZweck2() throws RemoteException;

  public void setZweck2(String zweck2) throws RemoteException;

  public void setBetrag(double betrag) throws RemoteException;

  public double getBetrag() throws RemoteException;

  public void setReferenz(Mitgliedskonto referenz) throws RemoteException;

  public Mitgliedskonto getReferenz() throws RemoteException;

}
