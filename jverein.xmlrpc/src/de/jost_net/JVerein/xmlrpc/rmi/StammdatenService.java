//TODO Kommentar einfügen

package de.jost_net.JVerein.xmlrpc.rmi;

import java.rmi.RemoteException;
import java.util.Map;

import de.willuhn.datasource.Service;

/**
 * XML-RPC-Service zum Zugriff auf Stammdaten.
 */
public interface StammdatenService extends Service
{
  public Map<String, Object> read() throws RemoteException;
}
