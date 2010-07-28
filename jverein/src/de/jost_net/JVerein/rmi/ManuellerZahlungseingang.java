/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/rmi/Attic/ManuellerZahlungseingang.java,v $
 * $Revision: 1.2 $
 * $Date: 2010/07/28 07:28:05 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: ManuellerZahlungseingang.java,v $
 * Revision 1.2  2010/07/28 07:28:05  jost
 * deprecated
 *
 * Revision 1.1  2007/03/13 19:58:40  jost
 * Neu: Manueller Zahlungseingang.
 *
 **********************************************************************/
package de.jost_net.JVerein.rmi;

import java.rmi.RemoteException;
import java.util.Date;

import de.willuhn.datasource.rmi.DBObject;
import de.willuhn.util.ApplicationException;

/**
 * @deprecated In Version 1.5 ausmustern
 */
public interface ManuellerZahlungseingang extends DBObject
{
  public void setID(String id) throws RemoteException;

  public String getName() throws RemoteException;

  public void setName(String name) throws RemoteException;

  public String getVZweck1() throws RemoteException;

  public void setVZweck1(String vzweck1) throws RemoteException;

  public String getVZweck2() throws RemoteException;

  public void setVZweck2(String vzweck2) throws RemoteException;

  public double getBetrag() throws RemoteException;

  public void setBetrag(double betrag) throws RemoteException;

  public void setEingabedatum() throws RemoteException;

  public Date getEingabedatum() throws RemoteException;

  public void setEingangsdatum(Date datum) throws RemoteException;

  public Date getEingangsdatum() throws RemoteException;

  public void insert() throws RemoteException, ApplicationException;

}
