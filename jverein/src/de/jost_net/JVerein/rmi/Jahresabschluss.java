/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/rmi/Jahresabschluss.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/06/28 17:01:19 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: Jahresabschluss.java,v $
 * Revision 1.1  2008/06/28 17:01:19  jost
 * Neu: Jahresabschluss
 *
 **********************************************************************/
package de.jost_net.JVerein.rmi;

import java.rmi.RemoteException;
import java.util.Date;

import de.willuhn.datasource.rmi.DBObject;

public interface Jahresabschluss extends DBObject
{
  public String getID() throws RemoteException;

  public Date getVon() throws RemoteException;

  public void setVon(Date von) throws RemoteException;

  public Date getBis() throws RemoteException;

  public void setBis(Date von) throws RemoteException;

  public Date getDatum() throws RemoteException;

  public void setDatum(Date datum) throws RemoteException;

  public void setName(String name) throws RemoteException;

  public String getName() throws RemoteException;

}
