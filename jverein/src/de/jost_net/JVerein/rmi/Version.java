/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/rmi/Version.java,v $
 * $Revision: 1.1 $
 * $Date: 2007/12/01 17:47:03 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: Version.java,v $
 * Revision 1.1  2007/12/01 17:47:03  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.JVerein.rmi;

import java.rmi.RemoteException;

import de.willuhn.datasource.rmi.DBObject;

public interface Version extends DBObject
{
  public int getVersion() throws RemoteException;

  public void setVersion(int version) throws RemoteException;
}
