/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein.xmlrpc/src/de/jost_net/JVerein/xmlrpc/rmi/MitgliedService.java,v $
 * $Revision: 1.1 $
 * $Date: 2011/03/07 21:11:27 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: MitgliedService.java,v $
 * Revision 1.1  2011/03/07 21:11:27  jost
 * *** empty log message ***
 *
 **********************************************************************/

package de.jost_net.JVerein.xmlrpc.rmi;

import java.rmi.RemoteException;
import java.util.Map;

import de.willuhn.datasource.Service;

/**
 * XML-RPC-Service zum Zugriff auf Mitglieder.
 */
public interface MitgliedService extends Service
{
  public String[] getIDs(Integer adresstyp) throws RemoteException;

  public Map<String, Object> read(String id) throws RemoteException;

  public String create(Map<String, Object> address) throws RemoteException;

  public String update(Map<String, Object> address) throws RemoteException;

}
