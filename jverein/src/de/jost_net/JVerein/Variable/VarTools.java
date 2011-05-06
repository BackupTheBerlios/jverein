/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/Variable/VarTools.java,v $
 * $Revision: 1.1 $
 * $Date: 2011/05/06 15:04:16 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * heiner@jverein.de
 * www.jverein.de
 * All rights reserved
 * $Log: VarTools.java,v $
 * Revision 1.1  2011/05/06 15:04:16  jost
 * Neue Variablenmimik
 *
 **********************************************************************/
package de.jost_net.JVerein.Variable;

import java.util.Map;

import org.apache.velocity.VelocityContext;

public class VarTools
{
  /**
   * Übertragung einer Map<String, Object> in einen VelocityContext
   */
  public static void add(VelocityContext context, Map<String, Object> map)
  {
    for (String key : map.keySet())
    {
      context.put(key, map.get(key));
    }
  }
}
