/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/formatter/Attic/ReportartFormatter.java,v $
 * $Revision: 1.1 $
 * $Date: 2009/03/26 21:00:14 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: ReportartFormatter.java,v $
 * Revision 1.1  2009/03/26 21:00:14  jost
 * Neu: Reports - Erste Version
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.formatter;

import de.jost_net.JVerein.keys.Reportart;
import de.willuhn.jameica.gui.formatter.Formatter;

public class ReportartFormatter implements Formatter
{
  public String format(Object o)
  {
    Integer art = (Integer) o;
    return Reportart.get(art);
  }
}
