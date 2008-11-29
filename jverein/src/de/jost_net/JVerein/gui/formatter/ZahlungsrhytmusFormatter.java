/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/formatter/ZahlungsrhytmusFormatter.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/11/29 13:09:39 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: ZahlungsrhytmusFormatter.java,v $
 * Revision 1.1  2008/11/29 13:09:39  jost
 * Neu: Konfiguration der Spalten einer Tabelle
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.formatter;

import de.jost_net.JVerein.keys.Zahlungsrhytmus;
import de.willuhn.jameica.gui.formatter.Formatter;

public class ZahlungsrhytmusFormatter implements Formatter
{
  public String format(Object o)
  {
    Integer zr = (Integer) o;
    return Zahlungsrhytmus.get(zr);
  }
}
