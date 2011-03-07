/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein.xmlrpc/src/de/jost_net/JVerein/xmlrpc/util/DateUtil.java,v $
 * $Revision: 1.1 $
 * $Date: 2011/03/07 21:12:43 $
 * $Author: jost $
 *
 * Kopie aus hibiscus.xmlrpc
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: DateUtil.java,v $
 * Revision 1.1  2011/03/07 21:12:43  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.JVerein.xmlrpc.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import de.jost_net.JVerein.xmlrpc.Plugin;
import de.willuhn.jameica.system.Application;
import de.willuhn.util.ApplicationException;
import de.willuhn.util.I18N;

/**
 * Hilfsklasse fuer Datums-Konvertierungen.
 */
public class DateUtil
{
  private final static I18N i18n = Application.getPluginLoader()
      .getPlugin(Plugin.class).getResources().getI18N();

  /**
   * Versucht, das Objekt als Datum zu parsen. Folgende Objekte werden
   * unterstuetzt: - Date-Objekt. - String im Format YYYY-MM-DD - String im
   * Format DD.MM.YYYY Wandelt ein Datum vom Format YYYY-MM-DD oder DD.MM.YYYY
   * in einen java.util.Date-Objekt um
   * 
   * @param object
   *          das Datum. Darf NULL sein.
   * @return das geparste Datum.
   * @throws ApplicationException
   */
  public static Date parse(Object object, String spalte)
      throws ApplicationException
  {
    if (object == null)
    {
      return null;
    }

    if (object instanceof Date)
    {
      return (Date) object;
    }

    String s = object.toString();

    try
    {
      // Dateformat ist nicht multithreading-tauglich, daher kein statisches
      // Member
      if (s.indexOf("-") != -1)
      {
        return new SimpleDateFormat("yyyy-MM-dd").parse(s);
      }
      return new SimpleDateFormat("dd.MM.yyyy").parse(s);
    }
    catch (Exception e)
    {
      throw new ApplicationException(i18n.tr(
          "Angegebenes Datum in Spalte {1} ungültig: {0}", s, spalte));
    }
  }

  /**
   * Versucht, das Objekt als Datum zu parsen. Folgende Objekte werden
   * unterstuetzt: - Date-Objekt. - String im Format YYYY-MM-DD - String im
   * Format DD.MM.YYYY Wandelt ein Datum vom Format YYYY-MM-DD oder DD.MM.YYYY
   * in einen java.util.Date-Objekt um
   * 
   * @param object
   *          das Datum. Darf NULL sein.
   * @return das geparste Datum.
   * @throws ApplicationException
   */
  public static String parseToString(Object object, String spalte)
      throws ApplicationException
  {
    if (object == null)
    {
      return "";
    }
    if (object instanceof String)
    {
      String s = (String) object;
      if (s.length() == 0)
      {
        return "";
      }
    }

    if (object instanceof Date)
    {
      return new SimpleDateFormat("dd.MM.yyyy").format(object);
    }

    String s = object.toString();

    try
    {
      // Dateformat ist nicht multithreading-tauglich, daher kein statisches
      // Member
      if (s.indexOf("-") != -1)
      {
        Date d = new SimpleDateFormat("yyyy-MM-dd").parse(s);
        return new SimpleDateFormat("dd.MM.yyyy").format(d);
      }
      Date d = new SimpleDateFormat("dd.MM.yyyy").parse(s);
      return new SimpleDateFormat("dd.MM.yyyy").format(d);
    }
    catch (Exception e)
    {
      throw new ApplicationException(i18n.tr(
          "Angegebenes Datum in Spalte {1} ungültig: {0}", s, spalte));
    }
  }

  /**
   * Wandelt ein Datum in das Format 'YYYY-MM-DD' um
   * 
   * @param date
   *          das Datum.
   * @return der formatierte String.
   */
  public static String format(Date date)
  {
    if (date == null)
    {
      return "";
    }
    // Dateformat ist nicht multithreading-tauglich, daher kein statisches
    // Member
    return new SimpleDateFormat("yyyy-MM-dd").format(date);
  }
}
