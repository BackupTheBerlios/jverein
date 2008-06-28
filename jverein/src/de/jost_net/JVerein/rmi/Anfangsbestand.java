/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/rmi/Anfangsbestand.java,v $
 * $Revision: 1.2 $
 * $Date: 2008/06/28 17:00:48 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: Anfangsbestand.java,v $
 * Revision 1.2  2008/06/28 17:00:48  jost
 * Bearbeiten nur, wenn kein Jahresabschluss vorliegt.
 *
 * Revision 1.1  2008/05/22 06:54:59  jost
 * Buchführung
 *
 **********************************************************************/
package de.jost_net.JVerein.rmi;

import java.rmi.RemoteException;
import java.util.Date;

import de.willuhn.datasource.rmi.DBObject;

public interface Anfangsbestand extends DBObject
{
  public String getID() throws RemoteException;

  public Konto getKonto() throws RemoteException;

  public String getKontoText() throws RemoteException;

  public void setKonto(Konto konto) throws RemoteException;

  public Date getDatum() throws RemoteException;

  public void setDatum(Date datum) throws RemoteException;

  public void setBetrag(double betrag) throws RemoteException;

  public double getBetrag() throws RemoteException;

  public Jahresabschluss getJahresabschluss() throws RemoteException;

}
