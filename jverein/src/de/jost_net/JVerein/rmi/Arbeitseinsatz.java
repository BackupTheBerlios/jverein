/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/rmi/Arbeitseinsatz.java,v $
 * $Revision: 1.2 $
 * $Date: 2011/10/01 21:49:38 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/
package de.jost_net.JVerein.rmi;

import java.rmi.RemoteException;
import java.util.Date;

import de.willuhn.datasource.rmi.DBObject;

public interface Arbeitseinsatz extends DBObject
{
  public String getID() throws RemoteException;

  public Mitglied getMitglied() throws RemoteException;

  public void setMitglied(int mitglied) throws RemoteException;

  public Date getDatum() throws RemoteException;

  public void setDatum(Date datum) throws RemoteException;

  public Double getStunden() throws RemoteException;

  public void setStunden(Double stunden) throws RemoteException;

  public String getBemerkung() throws RemoteException;

  public void setBemerkung(String bemerkung) throws RemoteException;
}
