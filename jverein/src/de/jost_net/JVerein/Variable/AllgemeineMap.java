/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/Variable/AllgemeineMap.java,v $
 * $Revision: 1.1 $
 * $Date: 2011/05/06 15:03:40 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * heiner@jverein.de
 * www.jverein.de
 * All rights reserved
 * $Log: AllgemeineMap.java,v $
 * Revision 1.1  2011/05/06 15:03:40  jost
 * Neue Variablenmimik
 *
 **********************************************************************/
package de.jost_net.JVerein.Variable;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import de.jost_net.JVerein.util.JVDateFormatMMJJJJ;
import de.jost_net.JVerein.util.JVDateFormatTTMMJJJJ;

public class AllgemeineMap
{
  private JVDateFormatTTMMJJJJ ttmmjjjj = new JVDateFormatTTMMJJJJ();

  private JVDateFormatMMJJJJ mmjjjj = new JVDateFormatMMJJJJ();

  public AllgemeineMap()
  {

  }

  public Map<String, Object> getMap(Map<String, Object> inma)
  {
    Map<String, Object> map = null;
    if (inma == null)
    {
      map = new HashMap<String, Object>();
    }
    else
    {
      map = inma;
    }
    map.put(AllgemeineVar.TAGESDATUM.getName(), ttmmjjjj.format(new Date()));
    map.put(AllgemeineVar.AKTUELLERMONAT.getName(), mmjjjj.format(new Date()));
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.MONTH, 1);
    map.put("folgemonat", mmjjjj.format(calendar.getTime()));
    calendar.add(Calendar.MONTH, -2);
    map.put("vormonat", mmjjjj.format(calendar.getTime()));
    return map;
  }
}
