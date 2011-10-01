/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/server/MitgliedfotoImpl.java,v $
 * $Revision: 1.5 $
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

import de.jost_net.JVerein.rmi.Mitglied;
import de.jost_net.JVerein.rmi.Mitgliedfoto;
import de.willuhn.datasource.db.AbstractDBObject;

public class MitgliedfotoImpl extends AbstractDBObject implements Mitgliedfoto
{

  private static final long serialVersionUID = 1603994510932244220L;

  public MitgliedfotoImpl() throws RemoteException
  {
    super();
  }

  @Override
  protected String getTableName()
  {
    return "mitgliedfoto";
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
    updateCheck();
  }

  @Override
  protected void updateCheck()
  {
    //
  }

  @Override
  protected Class<?> getForeignObject(String arg0)
  {
    return null;
  }

  public void setMitglied(Mitglied mitglied) throws RemoteException
  {
    setAttribute("mitglied", mitglied.getID());
  }

  public byte[] getFoto() throws RemoteException
  {
    return (byte[]) this.getAttribute("foto");
  }

  public void setFoto(byte[] foto) throws RemoteException
  {
    setAttribute("foto", foto);
  }

}
