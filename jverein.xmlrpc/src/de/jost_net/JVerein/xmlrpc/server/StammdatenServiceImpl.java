package de.jost_net.JVerein.xmlrpc.server;

/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein.xmlrpc/src/de/jost_net/JVerein/xmlrpc/server/Attic/StammdatenServiceImpl.java,v $
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
import de.jost_net.JVerein.rmi.Stammdaten;
import de.jost_net.JVerein.xmlrpc.rmi.StammdatenService;

/**
 * Implementierung des Umsatz-Service.
 */
public class StammdatenServiceImpl extends AbstractServiceImpl implements
    StammdatenService
{
  private static final long serialVersionUID = 6930666263993044492L;

  public static final String ALTERSGRUPPEN = "stammdaten.altersgruppen";

  public static final String ALTERSJUBILAEEN = "stammdaten.altersjubilaeen";

  public static final String BLZ = "stammdaten.blz";

  public static final String JUBILAEEN = "stammdaten.jubilaeen";

  public static final String KONTO = "stammdaten.konto";

  public static final String NAME = "stammdaten.name";

  public StammdatenServiceImpl() throws RemoteException
  {
    super();
  }

  public Map<String, Object> read() throws RemoteException
  {
    Stammdaten std = (Stammdaten) Einstellungen.getDBService().createObject(
        Stammdaten.class, "1");
    Map<String, Object> retval = new HashMap<String, Object>();
    retval.put(ALTERSGRUPPEN, std.getAltersgruppen());
    retval.put(ALTERSJUBILAEEN, std.getAltersjubilaeen());
    retval.put(BLZ, std.getBlz());
    retval.put(JUBILAEEN, std.getJubilaeen());
    retval.put(KONTO, std.getKonto());
    retval.put(NAME, std.getName());
    return retval;
  }

  /**
   * @see de.willuhn.datasource.Service#getName()
   */
  public String getName() throws RemoteException
  {
    return "[xml-rpc] stammdaten";
  }
}
