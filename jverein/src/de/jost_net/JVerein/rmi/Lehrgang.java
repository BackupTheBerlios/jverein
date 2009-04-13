/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/rmi/Lehrgang.java,v $
 * $Revision: 1.1 $
 * $Date: 2009/04/13 11:40:26 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: Lehrgang.java,v $
 * Revision 1.1  2009/04/13 11:40:26  jost
 * Neu: Lehrg�nge
 *
 **********************************************************************/
package de.jost_net.JVerein.rmi;

import java.rmi.RemoteException;
import java.util.Date;

import de.willuhn.datasource.rmi.DBObject;

public interface Lehrgang extends DBObject
{
  public String getID() throws RemoteException;

  public Mitglied getMitglied() throws RemoteException;

  public void setMitglied(int mitglied) throws RemoteException;

  public Lehrgangsart getLehrgangsart() throws RemoteException;

  public void setLehrgangsart(int lehrgangsart) throws RemoteException;

  public Date getVon() throws RemoteException;

  public void setVon(Date von) throws RemoteException;

  public Date getBis() throws RemoteException;

  public void setBis(Date bis) throws RemoteException;

  public String getVeranstalter() throws RemoteException;

  public void setVeranstalter(String veranstalter) throws RemoteException;

  public String getErgebnis() throws RemoteException;

  public void setErgebnis(String ergebnis) throws RemoteException;
}
