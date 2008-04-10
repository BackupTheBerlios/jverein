/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/rmi/Zusatzfelder.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/04/10 19:03:06 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: Zusatzfelder.java,v $
 * Revision 1.1  2008/04/10 19:03:06  jost
 * Neu: Benutzerdefinierte Datenfelder
 *
 **********************************************************************/
package de.jost_net.JVerein.rmi;

import java.rmi.RemoteException;

import de.willuhn.datasource.rmi.DBObject;

public interface Zusatzfelder extends DBObject
{
  public String getID() throws RemoteException;

  public Mitglied getMitglied() throws RemoteException;

  public void setMitglied(int mitglied) throws RemoteException;

  public Felddefinition getFelddefinition() throws RemoteException;

  public void setFelddefinition(int felddefiniton) throws RemoteException;

  public String getFeld() throws RemoteException;

  public void setFeld(String feld) throws RemoteException;

}
