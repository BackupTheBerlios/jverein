/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/rmi/Lehrgangsart.java,v $
 * $Revision: 1.2 $
 * $Date: 2011/10/01 21:49:39 $
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

public interface Lehrgangsart extends DBObject
{
  public String getID() throws RemoteException;

  public String getBezeichnung() throws RemoteException;

  public void setBezeichnung(String bezeichnung) throws RemoteException;

  public Date getVon() throws RemoteException;

  public void setVon(Date von) throws RemoteException;

  public Date getBis() throws RemoteException;

  public void setBis(Date bis) throws RemoteException;

  public String getVeranstalter() throws RemoteException;

  public void setVeranstalter(String veranstalter) throws RemoteException;
}
