/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/formatter/ZahlungswegFormatter.java,v $
 * $Revision: 1.2 $
 * $Date: 2011/10/01 21:43:52 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/
package de.jost_net.JVerein.gui.formatter;

import de.jost_net.JVerein.keys.Zahlungsweg;
import de.willuhn.jameica.gui.formatter.Formatter;

public class ZahlungswegFormatter implements Formatter
{
  public String format(Object o)
  {
    Integer zw = (Integer) o;
    return Zahlungsweg.get(zw);
  }
}
