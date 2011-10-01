/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/rmi/Eigenschaft.java,v $
 * $Revision: 1.2 $
 * $Date: 2011/10/01 21:49:39 $
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

public interface Eigenschaft extends DBObject
{
  public String getBezeichnung() throws RemoteException;

  public void setBezeichnung(String name) throws RemoteException;

  public EigenschaftGruppe getEigenschaftGruppe() throws RemoteException;

  public int getEigenschaftGruppeId() throws RemoteException;

  public void setEigenschaftGruppe(Integer eigenschaftgruppe)
      throws RemoteException;

}
