/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/rmi/Formular.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/07/18 20:16:13 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: Formular.java,v $
 * Revision 1.1  2008/07/18 20:16:13  jost
 * Neu: Formulare
 *
 **********************************************************************/
package de.jost_net.JVerein.rmi;

import java.rmi.RemoteException;

import de.willuhn.datasource.rmi.DBObject;

public interface Formular extends DBObject
{
  public byte[] getInhalt() throws RemoteException;

  public void setInhalt(byte[] inhalt) throws RemoteException;

  public String getBezeichnung() throws RemoteException;

  public void setBezeichnung(String bezeichnung) throws RemoteException;

  public int getArt() throws RemoteException;

  public void setArt(int art) throws RemoteException;

}
