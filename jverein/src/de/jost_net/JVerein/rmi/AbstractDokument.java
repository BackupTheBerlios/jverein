/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/rmi/AbstractDokument.java,v $
 * $Revision: 1.1 $
 * $Date: 2010/12/12 08:13:26 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: AbstractDokument.java,v $
 * Revision 1.1  2010/12/12 08:13:26  jost
 * Neu: Speicherung von Dokumenten
 *
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
