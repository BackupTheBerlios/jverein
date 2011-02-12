/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/util/JVDateFormatTIMESTAMP.java,v $
 * $Revision: 1.1 $
 * $Date: 2011/02/12 09:44:06 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: JVDateFormatTIMESTAMP.java,v $
 * Revision 1.1  2011/02/12 09:44:06  jost
 * Statische Codeanalyse mit Findbugs
 *
 **********************************************************************/
package de.jost_net.JVerein.util;

import java.text.SimpleDateFormat;

public class JVDateFormatTIMESTAMP extends SimpleDateFormat
{
  private static final long serialVersionUID = 4017644423840096050L;

  public JVDateFormatTIMESTAMP()
  {
    super("dd.MM.yyyy HH:mm");
  }
}
