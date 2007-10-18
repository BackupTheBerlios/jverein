/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/rmi/JVereinDBService.java,v $
 * $Revision: 1.1 $
 * $Date: 2007/10/18 18:19:52 $
 * $Author: jost $
 *
 * Kopie aus Hibiscus
 * Copyright (c) by willuhn software & services
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: JVereinDBService.java,v $
 * Revision 1.1  2007/10/18 18:19:52  jost
 * Vorbereitung H2-DB
 *
 **********************************************************************/
package de.jost_net.JVerein.rmi;

import java.rmi.RemoteException;

import de.willuhn.datasource.rmi.DBService;
import de.willuhn.jameica.system.Settings;
import de.willuhn.util.ApplicationException;

/**
 * Interface fuer den Datenbank-Service von JVerein.
 */
public interface JVereinDBService extends DBService
{
  /**
   * Einstellungen fuer die DB-Services.
   */
  public final static Settings SETTINGS = new Settings(JVereinDBService.class);

  /**
   * Aktualisiert die Datenbank.
   * 
   * @param oldVersion
   *          vorherige Version.
   * @param newVersion
   *          neue Version.
   * @throws RemoteException
   *           Wenn beim Update ein Fehler auftrat.
   */
  public void update(double oldVersion, double newVersion)
      throws RemoteException;

  /**
   * Initialisiert/Erzeugt die Datenbank.
   * 
   * @throws RemoteException
   *           Wenn beim Initialisieren ein Fehler auftrat.
   */
  public void install() throws RemoteException;

  /**
   * Checkt die Konsistenz der Datenbank.
   * 
   * @throws RemoteException
   *           Wenn es beim Pruefen der Datenbank-Konsistenz zu einem Fehler
   *           kam.
   * @throws ApplicationException
   *           wenn die Datenbank-Konsistenz nicht gewaehrleistet ist.
   */
  public void checkConsistency() throws RemoteException, ApplicationException;

  /**
   * Liefert den Namen der SQL-Funktion, mit der die Datenbank aus einem
   * DATE-Feld einen UNIX-Timestamp macht. Bei MySQL ist das z.Bsp.
   * "UNIX_TIMESTAMP" und bei McKoi schlicht "TONUMBER".
   * 
   * @param content
   *          der Feld-Name.
   * @return Name der SQL-Funktion samt Parameter. Also zum Beispiel
   *         "TONUMBER(datum)".
   * @throws RemoteException
   */
  public String getSQLTimestamp(String content) throws RemoteException;

}
