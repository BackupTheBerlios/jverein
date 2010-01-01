/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/rmi/Felddefinition.java,v $
 * $Revision: 1.2 $
 * $Date: 2010/01/01 20:10:37 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: Felddefinition.java,v $
 * Revision 1.2  2010/01/01 20:10:37  jost
 * Typisierung der Zusatzfelder
 *
 * Revision 1.1  2008/04/10 19:02:50  jost
 * Neu: Benutzerdefinierte Datenfelder
 *
 **********************************************************************/
package de.jost_net.JVerein.rmi;

import java.rmi.RemoteException;

import de.willuhn.datasource.rmi.DBObject;

public interface Felddefinition extends DBObject
{
  public String getID() throws RemoteException;

  public String getName() throws RemoteException;

  public void setName(String name) throws RemoteException;

  public String getLabel() throws RemoteException;

  public void setLabel(String label) throws RemoteException;

  public int getDatentyp() throws RemoteException;
  
  public void setDatentyp(int datentyp) throws RemoteException;
  
  public int getLaenge() throws RemoteException;
  
  public void setLaenge(int laenge) throws RemoteException;
}
