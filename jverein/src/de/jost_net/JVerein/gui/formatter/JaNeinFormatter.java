/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/formatter/JaNeinFormatter.java,v $
 * $Revision: 1.3 $
 * $Date: 2011/10/01 21:43:53 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/
package de.jost_net.JVerein.gui.formatter;

import de.willuhn.jameica.gui.formatter.Formatter;

public class JaNeinFormatter implements Formatter
{
  public String format(Object o)
  {
    if (o == null)
    {
      return "_";
    }
    if (o instanceof Boolean)
    {
      Boolean b = (Boolean) o;
      if (b)
      {
        return "X";
      }
    }
    if (o instanceof String)
    {
      String s = (String) o;
      if (s.equalsIgnoreCase("TRUE"))
      {
        return "X";
      }
    }
    return "_";
  }
}
