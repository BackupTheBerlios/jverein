/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/server/VersionImpl.java,v $
 * $Revision: 1.4 $
 * $Date: 2010/11/13 09:31:24 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: VersionImpl.java,v $
 * Revision 1.4  2010/11/13 09:31:24  jost
 * Warnings entfernt.
 *
 * Revision 1.3  2010-10-15 09:58:28  jost
 * Code aufger�umt
 *
 * Revision 1.2  2008-11-29 13:17:19  jost
 * Refactoring: Warnungen beseitigt.
 *
 * Revision 1.1  2007/12/01 17:47:50  jost
 * Neue DB-Update-Mimik
 *
 **********************************************************************/
package de.jost_net.JVerein.server;

import java.rmi.RemoteException;

import de.jost_net.JVerein.rmi.Version;
import de.willuhn.datasource.db.AbstractDBObject;

public class VersionImpl extends AbstractDBObject implements Version
{

  private static final long serialVersionUID = 1L;

  public VersionImpl() throws RemoteException
  {
    super();
  }

  @Override
  protected String getTableName()
  {
    return "version";
  }

  @Override
  public String getPrimaryAttribute()
  {
    return "id";
  }

  @Override
  protected void deleteCheck()
  {
    //
  }

  @Override
  protected void insertCheck()
  {
    //
  }

  @Override
  protected void updateCheck()
  {
    insertCheck();
  }

  @Override
  protected Class<?> getForeignObject(String arg0)
  {
    return null;
  }

  public int getVersion() throws RemoteException
  {
    Integer i = (Integer) getAttribute("version");
    return i.intValue();
  }

  public void setVersion(int version) throws RemoteException
  {
    setAttribute("version", version);
  }
}
