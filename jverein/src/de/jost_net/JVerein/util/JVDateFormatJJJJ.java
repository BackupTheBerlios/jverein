/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/util/JVDateFormatJJJJ.java,v $
 * $Revision: 1.1 $
 * $Date: 2011/05/07 05:47:24 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: JVDateFormatJJJJ.java,v $
 * Revision 1.1  2011/05/07 05:47:24  jost
 * Neue Variable folgejahr
 *
 * Revision 1.1  2011-05-06 15:03:13  jost
 * Neu
 *
 **********************************************************************/
package de.jost_net.JVerein.util;

import java.text.SimpleDateFormat;

public class JVDateFormatJJJJ extends SimpleDateFormat
{
  private static final long serialVersionUID = 4017644423840096050L;

  public JVDateFormatJJJJ()
  {
    super("yyyy");
  }
}
