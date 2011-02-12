/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/util/JVDateFormatJJJJMMTT.java,v $
 * $Revision: 1.1 $
 * $Date: 2011/02/12 09:44:07 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: JVDateFormatJJJJMMTT.java,v $
 * Revision 1.1  2011/02/12 09:44:07  jost
 * Statische Codeanalyse mit Findbugs
 *
 **********************************************************************/
package de.jost_net.JVerein.util;

import java.text.SimpleDateFormat;

public class JVDateFormatJJJJMMTT extends SimpleDateFormat
{
  private static final long serialVersionUID = 4017644423840096050L;

  public JVDateFormatJJJJMMTT()
  {
    super("yyyy-MM-dd");
  }
}
