/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/rmi/Buchungsart.java,v $
 * $Revision: 1.1 $
 * $Date: 2006/09/20 15:39:35 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * jost@berlios.de
 * jverein.berlios.de
 * $Log: Buchungsart.java,v $
 * Revision 1.1  2006/09/20 15:39:35  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.JVerein.rmi;

import java.rmi.RemoteException;

import de.willuhn.datasource.rmi.DBObject;

public interface Buchungsart extends DBObject
{
  public int getNummer() throws RemoteException;

  public void setNummer(int nummer) throws RemoteException;

  public String getBezeichnung() throws RemoteException;

  public void setBezeichnung(String bezeichnung) throws RemoteException;

}
