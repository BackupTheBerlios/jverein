/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/util/Datum.java,v $
 * $Revision: 1.9 $
 * $Date: 2011/10/01 21:51:11 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/
package de.jost_net.JVerein.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Datum
{

  /**
   * Berechnung der Differenz in Monaten zwischen 2 Daten. Die Tagesangaben
   * bleiben unberücksichtigt.
   * 
   * @param datum1
   *          von-Datum
   * @param datum2
   *          bis-Datum
   * @return Anzahl Monate
   */
  public static int getDifferenzInMonaten(Date datum1, Date datum2)
  {
    Calendar cal = Calendar.getInstance();
    cal.setTime(datum2);
    int diff = cal.get(Calendar.YEAR) * 12;
    diff = diff + cal.get(Calendar.MONTH);
    cal.setTime(datum1);
    diff = diff - (cal.get(Calendar.YEAR) * 12);
    diff = diff - cal.get(Calendar.MONTH);
    return diff;
  }

  public static boolean isImInterval(Date datum1, Date datum2, int intervall)
  {
    if (intervall == 0)
    {
      return true;
    }
    Calendar cal1 = Calendar.getInstance();
    cal1.setTime(datum1);
    Calendar cal2 = Calendar.getInstance();
    cal2.setTime(datum2);
    if (cal1.get(Calendar.DAY_OF_MONTH) != cal2.get(Calendar.DAY_OF_MONTH))
    {
      return false;
    }
    return getDifferenzInMonaten(datum1, datum2) % intervall == 0;
  }

  public static Date addInterval(Date letztefaelligkeit, int intervall)
  {
    Calendar cal = Calendar.getInstance();
    cal.setTime(letztefaelligkeit);
    cal.add(Calendar.MONTH, intervall);
    return cal.getTime();
  }

  public static Date subtractInterval(Date letztefaelligkeit, int intervall,
      Date startdatum)
  {
    Calendar cal = Calendar.getInstance();
    cal.setTime(letztefaelligkeit);
    cal.add(Calendar.MONTH, intervall * -1);
    if (cal.getTime().getTime() < startdatum.getTime())
    {
      return null;
    }
    else
    {
      return cal.getTime();
    }
  }

  public static Date addTage(Date datum, int tage)
  {
    Calendar cal = Calendar.getInstance();
    cal.setTime(datum);
    cal.add(Calendar.DAY_OF_MONTH, tage);
    return cal.getTime();
  }

  public static Date toDate(String value) throws ParseException
  {
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    return sdf.parse(value);
  }

  /**
   * Gibt das heutige Datum zurück. (Ohne Zeitanteil)
   */
  public final static Date getHeute()
  {
    return getDatumOhneZeit(new Date());
  }

  public final static Date getDatumOhneZeit(Date datum)
  {
    Calendar cal = Calendar.getInstance();
    cal.setTime(datum);
    cal.set(Calendar.HOUR, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    return cal.getTime();
  }

  /**
   * Gibt einen Leerstring aus, falls der Text null ist.
   * 
   * @return der Text oder Leerstring - niemals null.
   */
  public final static String formatDate(Date d)
  {
    return d == null ? "" : new JVDateFormatTTMMJJJJ().format(d);
  }

}
