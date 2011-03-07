/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein.xmlrpc/src/de/jost_net/JVerein/xmlrpc/util/StringUtil.java,v $
 * $Revision: 1.1 $
 * $Date: 2011/03/07 21:12:43 $
 * $Author: jost $
 *
 * Kopie aus hibiscus.xmlrpc
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: StringUtil.java,v $
 * Revision 1.1  2011/03/07 21:12:43  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.JVerein.xmlrpc.util;

import java.util.ArrayList;
import java.util.List;

import de.jost_net.JVerein.xmlrpc.Plugin;
import de.willuhn.jameica.system.Application;

/**
 * Hilfsklasse fuer String-Operationen.
 */
public class StringUtil
{
  /**
   * Quotet den Text.
   * 
   * @param s
   *          zu quotender Text.
   * @return der gequotete Text.
   */
  public static String quote(String s)
  {
    if (s == null)
      return s;

    String quote = Application.getPluginLoader().getPlugin(Plugin.class)
        .getResources().getSettings().getString("quoting.char", null);

    if (quote == null || quote.length() == 0)
      return s;

    // Erstmal enthaltene Quoting-Zeichen escapen
    s = s.replaceAll(quote, "\\" + quote);
    return quote + s + quote;
  }

  /**
   * Wandelt ein Objekt in einen String um.
   * 
   * @param o
   *          das Objekt.
   * @return Die String-Repraesentation oder "" - niemals aber null.
   */
  public static String notNull(Object o)
  {
    if (o == null)
      return "";
    String s = o.toString();
    return s == null ? "" : s;
  }

  /**
   * Versucht das Objekt als Verwendungszweck zu parsen. Die Funktion erkennt
   * selbst, ob "object" ein Array oder vom Typ "List" ist.
   * 
   * @param object
   *          der potentielle Verwendungszweck. Darf NULL sein.
   * @return String-Array mit den Verwendungszweck-Zeilen.
   */
  public static String[] parseUsage(Object object)
  {
    if (object == null)
      return null;

    if (object instanceof Object[])
    {
      Object[] list = (Object[]) object;
      ArrayList<String> lines = new ArrayList<String>();
      for (Object o : list)
      {
        if (o != null)
          lines.add(o.toString());
      }
      return lines.toArray(new String[lines.size()]);
    }

    if (object instanceof List)
    {
      List list = (List) object;
      ArrayList<String> lines = new ArrayList<String>();
      for (Object o : list)
      {
        if (o != null)
          lines.add(o.toString());
      }
      return lines.toArray(new String[lines.size()]);
    }
    return new String[] { object.toString() };
  }

}
