/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/rmi/Adresstyp.java,v $
 * $Revision: 1.1 $
 * $Date: 2011/01/27 22:24:05 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: Adresstyp.java,v $
 * Revision 1.1  2011/01/27 22:24:05  jost
 * Neu: Speicherung von weiteren Adressen in der Mitgliedertabelle
 *
 **********************************************************************/
package de.jost_net.JVerein.rmi;

import java.rmi.RemoteException;

import de.willuhn.datasource.rmi.DBObject;

public interface Adresstyp extends DBObject
{
  public String getBezeichnung() throws RemoteException;

  public void setBezeichnung(String bezeichnung) throws RemoteException;

  /**
   * JVerein-ID <br>
   * Mit der JVerein-ID werden Adresstypen mit festen Funktionen in JVerein
   * festgelegt. Beispiele: Mitglied, Spender.
   */
  public int getJVereinid() throws RemoteException;

  public void setJVereinid(int jvereinid) throws RemoteException;
}
