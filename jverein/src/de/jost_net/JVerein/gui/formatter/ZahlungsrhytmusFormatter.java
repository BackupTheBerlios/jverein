/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/formatter/ZahlungsrhytmusFormatter.java,v $
 * $Revision: 1.2 $
 * $Date: 2011/10/01 21:43:53 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
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
