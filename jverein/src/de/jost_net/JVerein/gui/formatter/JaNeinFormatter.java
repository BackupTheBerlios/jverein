/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/formatter/JaNeinFormatter.java,v $
 * $Revision: 1.2 $
 * $Date: 2010/09/09 18:50:26 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: JaNeinFormatter.java,v $
 * Revision 1.2  2010/09/09 18:50:26  jost
 * Eigenschaftengruppen können jetzt auch das Merkmal "Pflicht" haben. Dann muß mindestens eine Eigenschaft ausgewählt werden.
 *
 * Revision 1.1  2010/01/01 18:38:41  jost
 * Typisierung der Zusatzfelder
 *
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
