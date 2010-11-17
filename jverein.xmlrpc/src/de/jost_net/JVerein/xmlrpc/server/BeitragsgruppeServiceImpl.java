package de.jost_net.JVerein.xmlrpc.server;

/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein.xmlrpc/src/de/jost_net/JVerein/xmlrpc/server/BeitragsgruppeServiceImpl.java,v $
 * $Revision: 1.1 $
 * $Date: 2010/11/17 20:51:15 $
 * $Author: jost $
 * $Locker:  $
 * $State: Exp $
 *
 * Copyright (c) by willuhn.webdesign
 * All rights reserved
 *
 **********************************************************************/

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.rmi.Beitragsgruppe;
import de.jost_net.JVerein.xmlrpc.rmi.BeitragsgruppeService;
import de.willuhn.datasource.rmi.DBIterator;

/**
 * Implementierung des Umsatz-Service.
 */
public class BeitragsgruppeServiceImpl extends AbstractServiceImpl implements
    BeitragsgruppeService
{
  private static final long serialVersionUID = 6930666263993044492L;

  public static final String ARBEITSEINSATZBETRAG = "beitragsgruppe.arbeitseinsatzbetrag";

  public static final String ARBEITSEINSATZSTUNDEN = "beitragsgruppe.arbeitseinsatzstunden";

  public static final String BEITRAGSART = "beitragsgruppe.beitragsart";

  public static final String BETRAG = "beitragsgruppe.betrag";

  public static final String BEZEICHNUNG = "beitragsgruppe.bezeichnung";

  public static final String ID = "bezeichnung.id";

  public BeitragsgruppeServiceImpl() throws RemoteException
  {
    super();
  }

  public Map<String, Object> read(String id) throws RemoteException
  {
    Beitragsgruppe bg = (Beitragsgruppe) Einstellungen.getDBService()
        .createObject(Beitragsgruppe.class, id);
    Map<String, Object> retrec = new HashMap<String, Object>();
    retrec.put(ARBEITSEINSATZBETRAG, bg.getArbeitseinsatzBetrag());
    retrec.put(ARBEITSEINSATZSTUNDEN, bg.getArbeitseinsatzStunden());
    retrec.put(BEITRAGSART, bg.getBeitragsArt());
    retrec.put(BETRAG, bg.getBetrag());
    retrec.put(BEZEICHNUNG, bg.getBezeichnung());
    retrec.put(ID, bg.getID());
    return retrec;
  }

  public String[] getIDs() throws RemoteException
  {
    DBIterator it = Einstellungen.getDBService().createList(
        Beitragsgruppe.class);
    String[] retlist = new String[it.size()];
    int i = 0;
    while (it.hasNext())
    {
      Beitragsgruppe bg = (Beitragsgruppe) it.next();
      retlist[i] = bg.getID();
      i++;
    }
    return retlist;
  }

  /**
   * @see de.willuhn.datasource.Service#getName()
   */
  public String getName() throws RemoteException
  {
    return "[xml-rpc] beitragsgruppe";
  }
}
