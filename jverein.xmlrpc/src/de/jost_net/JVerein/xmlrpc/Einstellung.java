/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein.xmlrpc/src/de/jost_net/JVerein/xmlrpc/Einstellung.java,v $
 * $Revision: 1.1 $
 * $Date: 2011/03/07 21:10:31 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * Kopie aus hibiscus.xmlrpc
 * heiner@jverein.de
 * www.jverein.de
 * $Log: Einstellung.java,v $
 * Revision 1.1  2011/03/07 21:10:31  jost
 * *** empty log message ***
 *
 *
 **********************************************************************/

package de.jost_net.JVerein.xmlrpc;

import de.willuhn.jameica.system.Application;

/**
 * Einstellungen.
 */
public class Einstellung
{
  private final static de.willuhn.jameica.system.Settings SETTINGS = Application
      .getPluginLoader().getPlugin(Plugin.class).getResources().getSettings();

  /**
   * Liefert true, wenn die XML-RPC-Implementierung NULL (<ex:nil/>)
   * unterstuetzen soll. Ist das der Fall, liefern u.a. die create-Funktionen
   * zum Anlegen neuer Auftraege im Erfolgsfall NULL zurueck und im Fehlerfall
   * den Fehlertext als String. Falls die Funktion false liefert, wird im
   * Erfolgsfall die ID des erstellten Auftrages zurueckgegeben und im
   * Fehlerfall eine Exception geworfen.
   * 
   * @return true, wenn XML-RPC NULL unterstuetzt (default: true).
   */
  public final static boolean isNullSupported()
  {
    return false;
    // return SETTINGS.getBoolean("xmlrpc.supports.null", true);
  }

  /**
   * Legt fest, ob die XML-RPC-Implementierung NULL unterstuetzen soll. Siehe
   * {@link Settings#isNullSupported()}
   * 
   * @param b
   *          true, wenn NULL unterstuetzt werden soll.
   */
  public final static void setNullSupported(boolean b)
  {
    SETTINGS.setAttribute("xmlrpc.supports.null", b);
  }

  /**
   * Liefert die maximale Anzahl zurueckzuliefernder Datensaetze. Verhindert
   * OutOfMemoryException bei zuvielen Ergebnissen.
   * 
   * @return maximale Anzahl zurueckzuliefernder Datensaetze. Per Default
   *         10.000.
   */
  public final static int getResultLimit()
  {
    return SETTINGS.getInt("xmlrpc.result.limit", 10000);
  }

}
