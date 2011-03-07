/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein.xmlrpc/src/de/jost_net/JVerein/xmlrpc/rmi/BeitragsgruppeService.java,v $
 * $Revision: 1.2 $
 * $Date: 2011/03/07 21:10:54 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: BeitragsgruppeService.java,v $
 * Revision 1.2  2011/03/07 21:10:54  jost
 * *** empty log message ***
 *
 **********************************************************************/

package de.jost_net.JVerein.xmlrpc.rmi;

import java.rmi.RemoteException;
import java.util.Map;

import de.willuhn.datasource.Service;

/**
 * XML-RPC-Service zum Zugriff auf die Beitragsgruppen.
 */
public interface BeitragsgruppeService extends Service
{
  public String[] getIDs() throws RemoteException;

  public Map<String, Object> read(String id) throws RemoteException;
}
