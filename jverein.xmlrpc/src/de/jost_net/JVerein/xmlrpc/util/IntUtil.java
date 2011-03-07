/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein.xmlrpc/src/de/jost_net/JVerein/xmlrpc/util/IntUtil.java,v $
 * $Revision: 1.1 $
 * $Date: 2011/03/07 21:12:43 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: IntUtil.java,v $
 * Revision 1.1  2011/03/07 21:12:43  jost
 * *** empty log message ***
 *
 **********************************************************************/

package de.jost_net.JVerein.xmlrpc.util;

import de.jost_net.JVerein.xmlrpc.Plugin;
import de.willuhn.jameica.system.Application;
import de.willuhn.util.ApplicationException;
import de.willuhn.util.I18N;

/**
 * Hilfsklasse zum Parsen von Integers.
 */
public class IntUtil
{
  private final static I18N i18n = Application.getPluginLoader()
      .getPlugin(Plugin.class).getResources().getI18N();

  /**
   * Versucht, das Objekt als Integer-Zahl zu parsen.
   * 
   * @param o
   *          das Objekt. Darf NICHT NULL sein.
   * @return die Zahl.
   * @throws ApplicationException
   */
  public static int parse(Object o, String spalte) throws ApplicationException
  {
    if (o == null)
    {
      throw new ApplicationException(i18n.tr("Ungültiger Wert in Spalte "
          + spalte));
    }

    try
    {
      if (o instanceof Number)
      {
        return ((Number) o).intValue();
      }
      return Integer.parseInt(o.toString());
    }
    catch (Exception e)
    {
      throw new ApplicationException(i18n.tr(
          "Ungültiger Wert in Spalte {1}: {0}", o.toString(), spalte));
    }
  }

  /**
   * Versucht, das Objekt als Integer-Zahl zu parsen.
   * 
   * @param o
   *          das Objekt. Darf NICHT NULL sein.
   * @return die Zahl.
   * @throws ApplicationException
   */
  public static String parseToString(Object o, String debug)
      throws ApplicationException
  {

    if (o == null)
    {
      return "";
    }

    try
    {
      return o.toString();
    }
    catch (Exception e)
    {
      throw new ApplicationException(i18n.tr(
          "Ungültiger Wert in Spalte {1}: {0}", o.toString(), debug));
    }
  }
}
