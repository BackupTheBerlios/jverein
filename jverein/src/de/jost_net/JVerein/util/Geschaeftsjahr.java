/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/util/Geschaeftsjahr.java,v $
 * $Revision: 1.3 $
 * $Date: 2008/06/28 17:08:12 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: Geschaeftsjahr.java,v $
 * Revision 1.3  2008/06/28 17:08:12  jost
 * refactoring
 *
 * Revision 1.2  2008/05/26 18:59:31  jost
 * neue Methode
 *
 * Revision 1.1  2008/05/25 19:37:40  jost
 * Neu
 *
 **********************************************************************/
package de.jost_net.JVerein.util;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import de.jost_net.JVerein.Einstellungen;

public class Geschaeftsjahr
{
  private Date beginnGeschaeftsjahr;

  private int beginnGeschaeftsjahrjahr;

  private Date endeGeschaeftsjahr;

  public Geschaeftsjahr(int jahr) throws ParseException
  {
    beginnGeschaeftsjahr = Datum.toDate(Einstellungen.getBeginnGeschaeftsjahr()
        + jahr);
    Calendar cal = Calendar.getInstance();
    cal.setTime(beginnGeschaeftsjahr);
    beginnGeschaeftsjahrjahr = cal.get(Calendar.YEAR);
    cal.add(Calendar.YEAR, 1);
    cal.add(Calendar.DAY_OF_MONTH, -1);
    endeGeschaeftsjahr = cal.getTime();
  }

  /**
   * Gesch�ftsjahr zu einem vorgegebenen Datum ermitteln
   */
  public Geschaeftsjahr(Date datum) throws ParseException
  {
    Calendar cal = Calendar.getInstance();
    cal.setTime(datum);
    beginnGeschaeftsjahr = Datum.toDate(Einstellungen.getBeginnGeschaeftsjahr()
        + cal.get(Calendar.YEAR));
    if (datum.before(beginnGeschaeftsjahr))
    {
      cal.add(Calendar.YEAR, -1);
      beginnGeschaeftsjahr = Datum.toDate(Einstellungen
          .getBeginnGeschaeftsjahr()
          + cal.get(Calendar.YEAR));
    }
    cal.add(Calendar.YEAR, 1);
    cal.add(Calendar.DAY_OF_MONTH, -1);
    endeGeschaeftsjahr = cal.getTime();
  }

  public Date getBeginnGeschaeftsjahr()
  {
    return beginnGeschaeftsjahr;
  }

  public int getBeginnGeschaeftsjahrjahr()
  {
    return beginnGeschaeftsjahrjahr;
  }

  public Date getEndeGeschaeftsjahr()
  {
    return endeGeschaeftsjahr;
  }

  public String toString()
  {
    return Einstellungen.DATEFORMAT.format(beginnGeschaeftsjahr) + " - "
        + Einstellungen.DATEFORMAT.format(endeGeschaeftsjahr);
  }
}
