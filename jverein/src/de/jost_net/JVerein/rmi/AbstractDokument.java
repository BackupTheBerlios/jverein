/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/rmi/AbstractDokument.java,v $
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

public interface AbstractDokument extends DBObject
{
  public Integer getReferenz() throws RemoteException;

  public void setReferenz(Integer referenz) throws RemoteException;

  public Date getDatum() throws RemoteException;

  public void setDatum(Date datum) throws RemoteException;

  public String getBemerkung() throws RemoteException;

  public void setBemerkung(String bemerkung) throws RemoteException;

  public String getUUID() throws RemoteException;

  public void setUUID(String uuid) throws RemoteException;
}
