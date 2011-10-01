/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/server/Util.java,v $
 * $Revision: 1.3 $
 * $Date: 2011/10/01 21:50:35 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/
package de.jost_net.JVerein.server;

import java.rmi.RemoteException;

import de.jost_net.JVerein.JVereinPlugin;

public class Util
{

  public static boolean getBoolean(Object o) throws RemoteException
  {
    if (o == null)
    {
      return false;
    }
    if (o instanceof Boolean)
    {
      return (Boolean) o;
    }
    if (o instanceof String)
    {
      String v = (String) o;
      return (v.equalsIgnoreCase("true") || v.equalsIgnoreCase("j") || v
          .equalsIgnoreCase("1"));
    }
    throw new RemoteException(JVereinPlugin.getI18n().tr(
        "Weder null, noch String oder Boolean"));
  }
}
