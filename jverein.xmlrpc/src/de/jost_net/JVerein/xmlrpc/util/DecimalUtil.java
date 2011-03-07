/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein.xmlrpc/src/de/jost_net/JVerein/xmlrpc/util/DecimalUtil.java,v $
 * $Revision: 1.1 $
 * $Date: 2011/03/07 21:12:43 $
 * $Author: jost $
 *
 * Kopie aus hibiscus.xmlrpc
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: DecimalUtil.java,v $
 * Revision 1.1  2011/03/07 21:12:43  jost
 * *** empty log message ***
 *
 **********************************************************************/

package de.jost_net.JVerein.xmlrpc.util;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.xmlrpc.Plugin;
import de.willuhn.jameica.system.Application;
import de.willuhn.util.ApplicationException;
import de.willuhn.util.I18N;

/**
 * Hilfsklasse zum Parsen und Formatieren von Betraegen.
 */
public class DecimalUtil
{
  private final static I18N i18n = Application.getPluginLoader()
      .getPlugin(Plugin.class).getResources().getI18N();

  /**
   * Versucht, das Objekt als Dezimal-Zahl zu parsen.
   * 
   * @param o
   *          das Objekt. Darf NICHT NULL sein.
   * @return die Zahl.
   * @throws ApplicationException
   */
  public static double parse(Object o) throws ApplicationException
  {
    if (o == null)
      throw new ApplicationException(i18n.tr("Kein Betrag angegeben"));

    try
    {
      if (o instanceof Number)
        return ((Number) o).doubleValue();

      return Einstellungen.DECIMALFORMAT.parse(o.toString()).doubleValue();
    }
    catch (Exception e)
    {
      throw new ApplicationException(i18n.tr("Ungültiger Betrag: {0}",
          o.toString()));
    }
  }
}
