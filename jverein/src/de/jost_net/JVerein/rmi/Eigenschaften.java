/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/rmi/Eigenschaften.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/01/25 16:06:47 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: Eigenschaften.java,v $
 * Revision 1.1  2008/01/25 16:06:47  jost
 * Neu: Eigenschaften des Mitgliedes
 *
 **********************************************************************/
package de.jost_net.JVerein.rmi;

import java.rmi.RemoteException;

import de.willuhn.datasource.rmi.DBObject;

public interface Eigenschaften extends DBObject
{
  public Mitglied getMitglied() throws RemoteException;

  public void setMitglied(int mitglied) throws RemoteException;

  public String getEigenschaft() throws RemoteException;

  public void setEigenschaft(String eigenschaft) throws RemoteException;
}
