/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/Variable/VarTools.java,v $
 * $Revision: 1.2 $
 * $Date: 2011/10/01 21:52:00 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * heiner@jverein.de
 * www.jverein.de
 * All rights reserved
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
