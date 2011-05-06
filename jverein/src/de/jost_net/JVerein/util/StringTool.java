/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/util/StringTool.java,v $
 * $Revision: 1.1 $
 * $Date: 2011/05/06 15:03:27 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: StringTool.java,v $
 * Revision 1.1  2011/05/06 15:03:27  jost
 * Neu
 *
 **********************************************************************/
package de.jost_net.JVerein.util;

public class StringTool
{
  public static String toNotNullString(String string)
  {
    return (string == null ? "" : string);
  }
}
