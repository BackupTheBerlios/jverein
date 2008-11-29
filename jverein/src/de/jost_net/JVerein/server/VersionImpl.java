/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/server/VersionImpl.java,v $
 * $Revision: 1.2 $
 * $Date: 2008/11/29 13:17:19 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: VersionImpl.java,v $
 * Revision 1.2  2008/11/29 13:17:19  jost
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
import de.willuhn.util.ApplicationException;

public class VersionImpl extends AbstractDBObject implements Version
{
  private static final long serialVersionUID = 1L;

  public VersionImpl() throws RemoteException
  {
    super();
  }

  protected String getTableName()
  {
    return "version";
  }

  public String getPrimaryAttribute() throws RemoteException
  {
    return "id";
  }

  protected void deleteCheck() throws ApplicationException
  {
  }

  protected void insertCheck() throws ApplicationException
  {
  }

  protected void updateCheck() throws ApplicationException
  {
    insertCheck();
  }

  @SuppressWarnings("unchecked")
  protected Class getForeignObject(String arg0) throws RemoteException
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
