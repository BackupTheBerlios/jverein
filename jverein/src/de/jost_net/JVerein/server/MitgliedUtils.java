/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/server/MitgliedUtils.java,v $
 * $Revision: 1.7 $
 * $Date: 2011/10/01 21:50:34 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/
package de.jost_net.JVerein.server;

import java.rmi.RemoteException;
import java.util.Date;

import de.willuhn.datasource.rmi.DBIterator;

public class MitgliedUtils
{
  public static void setNurAktive(DBIterator it, Date datum)
      throws RemoteException
  {
    it.addFilter("(austritt is null or austritt > ?)", new Object[] { datum });
  }

  public static void setNurAktive(DBIterator it) throws RemoteException
  {
    setNurAktive(it, new Date());
  }

  public static void setMitglied(DBIterator it) throws RemoteException
  {
    it.addFilter("adresstyp = 1");
  }

  public static void setMitgliedOderSpender(DBIterator it)
      throws RemoteException
  {
    it.addFilter("(adresstyp = 1 or adresstyp = 2)");
  }

}
