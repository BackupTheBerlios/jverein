/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/rmi/Attic/Report.java,v $
 * $Revision: 1.2 $
 * $Date: 2009/03/26 21:04:49 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: Report.java,v $
 * Revision 1.2  2009/03/26 21:04:49  jost
 * Neu: Reports - Erste Version
 *
 * Revision 1.1  2009/02/15 20:04:24  jost
 * Erster Code f�r neue Report-Mimik
 *
 **********************************************************************/
package de.jost_net.JVerein.rmi;

import java.rmi.RemoteException;
import java.sql.Timestamp;

import de.willuhn.datasource.rmi.DBObject;

public interface Report extends DBObject
{

  public String getBezeichnung() throws RemoteException;

  public void setBezeichnung(String bezeichnung) throws RemoteException;

  public int getArt() throws RemoteException;

  public void setArt(int art) throws RemoteException;

  public byte[] getQuelle() throws RemoteException;

  public void setQuelle(byte[] quelle) throws RemoteException;

  public byte[] getKompilat() throws RemoteException;

  public void setKompilat(byte[] kompilat) throws RemoteException;

  public void setAenderung(Timestamp aenderung) throws RemoteException;

  public Timestamp getAenderung() throws RemoteException;

  public void setNutzung(Timestamp nutzung) throws RemoteException;

  public Timestamp getNutzung() throws RemoteException;

}
