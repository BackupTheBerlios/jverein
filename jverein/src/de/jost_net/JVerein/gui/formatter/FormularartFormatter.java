/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/formatter/FormularartFormatter.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/11/29 13:09:29 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: FormularartFormatter.java,v $
 * Revision 1.1  2008/11/29 13:09:29  jost
 * Neu: Konfiguration der Spalten einer Tabelle
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.formatter;

import de.jost_net.JVerein.keys.Formularart;
import de.willuhn.jameica.gui.formatter.Formatter;

public class FormularartFormatter implements Formatter
{
  public String format(Object o)
  {
    Integer art = (Integer) o;
    return Formularart.get(art);
  }
}
