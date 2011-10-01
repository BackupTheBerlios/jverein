/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/rmi/Zusatzbetrag.java,v $
 * $Revision: 1.3 $
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
import java.util.Date;

import de.willuhn.datasource.rmi.DBObject;

public interface Zusatzbetrag extends DBObject
{
  public Mitglied getMitglied() throws RemoteException;

  public void setMitglied(int mitglied) throws RemoteException;

  public Date getFaelligkeit() throws RemoteException;

  public void setFaelligkeit(Date faelligkeit) throws RemoteException;

  public String getBuchungstext() throws RemoteException;

  public void setBuchungstext(String buchungstext) throws RemoteException;

  public String getBuchungstext2() throws RemoteException;

  public void setBuchungstext2(String buchungstext2) throws RemoteException;

  public double getBetrag() throws RemoteException;

  public void setBetrag(double betrag) throws RemoteException;

  public Date getStartdatum() throws RemoteException;

  public void setStartdatum(Date value) throws RemoteException;

  public Integer getIntervall() throws RemoteException;

  public String getIntervallText() throws RemoteException;

  public void setIntervall(Integer value) throws RemoteException;

  public Date getEndedatum() throws RemoteException;

  public void setEndedatum(Date value) throws RemoteException;

  public Date getAusfuehrung() throws RemoteException;

  public void setAusfuehrung(Date ausfuehrung) throws RemoteException;

  public boolean isAktiv() throws RemoteException;
}
