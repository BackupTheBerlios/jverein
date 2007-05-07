/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/rmi/Wiedervorlage.java,v $
 *  * $Revision: 1.1 $
 * $Date: 2007/05/07 19:26:51 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: Wiedervorlage.java,v $
 * Revision 1.1  2007/05/07 19:26:51  jost
 * Neu: Wiedervorlage
 *
 **********************************************************************/
package de.jost_net.JVerein.rmi;

import java.rmi.RemoteException;
import java.util.Date;

import de.willuhn.datasource.rmi.DBObject;

public interface Wiedervorlage extends DBObject
{
  public Mitglied getMitglied() throws RemoteException;

  public void setMitglied(int mitglied) throws RemoteException;

  public Date getDatum() throws RemoteException;

  public void setDatum(Date datum) throws RemoteException;

  public String getVermerk() throws RemoteException;

  public void setVermerk(String buchungstext) throws RemoteException;

  public Date getErledigung() throws RemoteException;

  public void setErledigung(Date erledigung) throws RemoteException;
}
