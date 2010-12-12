/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/server/AbstractDokumentImpl.java,v $
 * $Revision: 1.1 $
 * $Date: 2010/12/12 08:14:25 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: AbstractDokumentImpl.java,v $
 * Revision 1.1  2010/12/12 08:14:25  jost
 * Neu: Speicherung von Dokumenten
 *
 **********************************************************************/
package de.jost_net.JVerein.server;

import java.rmi.RemoteException;
import java.util.Date;

import de.jost_net.JVerein.rmi.AbstractDokument;
import de.willuhn.datasource.db.AbstractDBObject;
import de.willuhn.util.ApplicationException;

public abstract class AbstractDokumentImpl extends AbstractDBObject implements
    AbstractDokument
{

  private static final long serialVersionUID = 1L;

  public AbstractDokumentImpl() throws RemoteException
  {
    super();
  }

  @Override
  public String getPrimaryAttribute()
  {
    return "id";
  }

  @Override
  protected void deleteCheck() throws ApplicationException
  {
    insertCheck();
  }

  @Override
  protected void insertCheck() throws ApplicationException
  {
    //
  }

  @Override
  protected void updateCheck() throws ApplicationException
  {
    insertCheck();
  }

  @Override
  protected Class<?> getForeignObject(String field)
  {
    return null;
  }

  public Integer getReferenz() throws RemoteException
  {
    return (Integer) getAttribute("referenz");
  }

  public void setReferenz(Integer referenz) throws RemoteException
  {
    setAttribute("referenz", referenz);
  }

  public Date getDatum() throws RemoteException
  {
    return (Date) getAttribute("datum");
  }

  public void setDatum(Date datum) throws RemoteException
  {
    setAttribute("datum", datum);
  }

  public String getBemerkung() throws RemoteException
  {
    return (String) getAttribute("bemerkung");
  }

  public void setBemerkung(String bemerkung) throws RemoteException
  {
    setAttribute("bemerkung", bemerkung);
  }

  public String getUUID() throws RemoteException
  {
    return (String) getAttribute("uuid");
  }

  public void setUUID(String uuid) throws RemoteException
  {
    setAttribute("uuid", uuid);
  }

  @Override
  public Object getAttribute(String fieldName) throws RemoteException
  {
    return super.getAttribute(fieldName);
  }

}
