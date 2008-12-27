/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/server/Util.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/12/27 15:19:41 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: Util.java,v $
 * Revision 1.1  2008/12/27 15:19:41  jost
 * Bugfix Booleans aus MySQL-DB lesen.
 *
 **********************************************************************/
package de.jost_net.JVerein.server;

import java.rmi.RemoteException;

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
    throw new RemoteException("Weder null, noch String oder Boolean");
  }
}
