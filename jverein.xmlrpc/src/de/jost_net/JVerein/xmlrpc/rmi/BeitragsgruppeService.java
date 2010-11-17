//TODO Kommentar einfügen

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
