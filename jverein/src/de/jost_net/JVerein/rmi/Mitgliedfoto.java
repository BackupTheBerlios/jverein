/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/rmi/Mitgliedfoto.java,v $
 * $Revision: 1.1 $
 * $Date: 2010/08/26 11:14:49 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: Mitgliedfoto.java,v $
 * Revision 1.1  2010/08/26 11:14:49  jost
 * Neu: Fotos von Mitgliedern
 *
 **********************************************************************/
package de.jost_net.JVerein.rmi;

import java.rmi.RemoteException;

import de.willuhn.datasource.rmi.DBObject;

public interface Mitgliedfoto extends DBObject
{

  public byte[] getFoto() throws RemoteException;

  public void setFoto(byte[] foto) throws RemoteException;

  public void setMitglied(int mitglied) throws RemoteException;
}
