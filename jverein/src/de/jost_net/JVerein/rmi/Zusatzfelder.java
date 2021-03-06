/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/rmi/Zusatzfelder.java,v $
 * $Revision: 1.4 $
 * $Date: 2011/10/01 21:49:39 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/
package de.jost_net.JVerein.rmi;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.Date;

import de.willuhn.datasource.rmi.DBObject;

public interface Zusatzfelder extends DBObject
{
  public String getID() throws RemoteException;

  public Mitglied getMitglied() throws RemoteException;

  public void setMitglied(int mitglied) throws RemoteException;

  public Felddefinition getFelddefinition() throws RemoteException;

  public void setFelddefinition(int felddefiniton) throws RemoteException;

  public String getFeld() throws RemoteException;

  public void setFeld(String feld) throws RemoteException;

  public Date getFeldDatum() throws RemoteException;

  public void setFeldDatum(Date datum) throws RemoteException;

  public Integer getFeldGanzzahl() throws RemoteException;

  public void setFeldGanzzahl(Integer ganzzahl) throws RemoteException;

  public double getFeldGleitkommazahl() throws RemoteException;

  public void setFeldGleitkommazahl(double gleitkommazahl)
      throws RemoteException;

  public BigDecimal getFeldWaehrung() throws RemoteException;

  public void setFeldWaehrung(BigDecimal waehrung) throws RemoteException;

  public Boolean getFeldJaNein() throws RemoteException;

  public void setFeldJaNein(Boolean janein) throws RemoteException;

  public String getString() throws RemoteException;
}
