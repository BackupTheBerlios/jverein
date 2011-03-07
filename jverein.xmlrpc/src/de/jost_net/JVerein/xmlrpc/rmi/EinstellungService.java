/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein.xmlrpc/src/de/jost_net/JVerein/xmlrpc/rmi/EinstellungService.java,v $
 * $Revision: 1.2 $
 * $Date: 2011/03/07 21:11:12 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: EinstellungService.java,v $
 * Revision 1.2  2011/03/07 21:11:12  jost
 * *** empty log message ***
 *
 **********************************************************************/

package de.jost_net.JVerein.xmlrpc.rmi;

import java.rmi.RemoteException;
import java.util.Map;

import de.willuhn.datasource.Service;

/**
 * XML-RPC-Service zum Zugriff auf Einstellungen.
 */
public interface EinstellungService extends Service
{
  public Map<String, Object> read() throws RemoteException;
}
