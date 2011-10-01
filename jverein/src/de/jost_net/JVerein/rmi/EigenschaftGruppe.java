/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/rmi/EigenschaftGruppe.java,v $
 * $Revision: 1.5 $
 * $Date: 2011/10/01 21:49:38 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/
package de.jost_net.JVerein.rmi;

import java.rmi.RemoteException;

import de.willuhn.datasource.rmi.DBObject;

public interface EigenschaftGruppe extends DBObject
{
  public String getBezeichnung() throws RemoteException;

  public void setBezeichnung(String bezeichnung) throws RemoteException;

  public void setPflicht(Boolean pflicht) throws RemoteException;

  public Boolean getPflicht() throws RemoteException;

  public void setMax1(Boolean max1) throws RemoteException;

  public Boolean getMax1() throws RemoteException;

}
