/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/util/StringTool.java,v $
 * $Revision: 1.2 $
 * $Date: 2011/10/01 21:51:34 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/
package de.jost_net.JVerein.util;

public class StringTool
{
  public static String toNotNullString(String string)
  {
    return (string == null ? "" : string);
  }
}
