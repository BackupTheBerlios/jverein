/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/util/Dateiname.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/01/01 13:14:28 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: Dateiname.java,v $
 * Revision 1.1  2008/01/01 13:14:28  jost
 * Neu: Dateinamenmuster
 *
 **********************************************************************/
package de.jost_net.JVerein.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * Generierung eines Dateinamens unter Ber�cksichtigung von vorgegebener
 * Variablen
 * </p>
 * 
 * <dl>
 * <dt>a$</dt>
 * <dd> Aufgabe (z. B. auswertung, abbuchung) </dd>
 * <dt>d$</dt>
 * <dd> Aktuelles Datum (z. B. 20080101) </dd>
 * <dt>$z</dt>
 * <dd> Aktuelle Zeit (z. B. 120503) </dd>
 * <dt>$s</dt>
 * <dd> Sortierung. Wird nicht immer gef�llt. Ggfls. Leerstring. </dd>
 * </dl>
 */

public class Dateiname
{
  private String aufgabe;

  private String muster;

  private String extension;

  private String sortierung;

  /**
   * Konstruktor
   * 
   * @param aufgabe
   * @param muster
   * @param extension
   */
  public Dateiname(String aufgabe, String muster, String extension)
  {
    this(aufgabe, "", muster, extension);
  }

  /**
   * Konstruktor
   * 
   * @param aufgabe
   * @param sortierung
   * @param muster
   * @param extension
   */
  public Dateiname(String aufgabe, String sortierung, String muster,
      String extension)
  {
    this.aufgabe = aufgabe;
    this.muster = muster;
    this.extension = extension;
    this.sortierung = sortierung;
  }

  /**
   * Gibt den aufbereiteten String zur�ck. Wurde ein leeres Muster �bergeben,
   * wird ein Leerstring zur�ckgegeben.
   */
  public String get()
  {
    if (muster.length() == 0)
    {
      return "";
    }
    String ret = muster;
    ret = ret.replace("a$", aufgabe);
    ret = ret
        .replace("d$", new SimpleDateFormat("yyyyMMdd").format(new Date()));
    ret = ret.replace("z$", new SimpleDateFormat("HHmmss").format(new Date()));
    ret = ret.replace("s$", sortierung);
    return ret + "." + extension;
  }

  /**
   * Nur zu Testzwecken.
   * 
   * @param args
   */
  // todo Nach Abschluss des Tests entfernen
  public static final void main(String[] args)
  {
    Dateiname d = new Dateiname("auswertung", "name", "a$s$-d$-z$", "pdf");
    System.out.println(d.get());
  }
}
