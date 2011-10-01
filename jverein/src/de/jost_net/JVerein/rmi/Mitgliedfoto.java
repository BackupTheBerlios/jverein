/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/rmi/Mitgliedfoto.java,v $
 * $Revision: 1.3 $
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

import de.willuhn.datasource.rmi.DBObject;

public interface Mitgliedfoto extends DBObject
{
  public void setID(String id) throws RemoteException;

  public void setMitglied(Mitglied mitglied) throws RemoteException;

  public byte[] getFoto() throws RemoteException;

  public void setFoto(byte[] foto) throws RemoteException;
}
