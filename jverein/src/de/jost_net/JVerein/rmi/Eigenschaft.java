/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/rmi/Eigenschaft.java,v $
 * $Revision: 1.1 $
 * $Date: 2009/11/17 21:01:38 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: Eigenschaft.java,v $
 * Revision 1.1  2009/11/17 21:01:38  jost
 * Neu: Eigenschaft und EigenschaftGruppe
 *
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
