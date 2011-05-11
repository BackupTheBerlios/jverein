/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/rmi/Attic/AuswertungPos.java,v $
 * $Revision: 1.1 $
 * $Date: 2011/05/11 15:51:32 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: AuswertungPos.java,v $
 * Revision 1.1  2011/05/11 15:51:32  jost
 * Speicherung Auswertungskriterien und Listenüberschrift
 *
 **********************************************************************/
package de.jost_net.JVerein.rmi;

import java.rmi.RemoteException;
import java.util.Date;

import de.willuhn.datasource.rmi.DBObject;

public interface AuswertungPos extends DBObject
{
  public Auswertung getAuswertung() throws RemoteException;

  public void setAuswertung(Auswertung auswertung) throws RemoteException;

  public String getFeld() throws RemoteException;

  public void setFeld(String feld) throws RemoteException;

  public String getZeichenfolge() throws RemoteException;

  public void setZeichenfolge(String zeichenfolge) throws RemoteException;

  public Date getDatum() throws RemoteException;

  public void setDatum(Date datum) throws RemoteException;

  public Integer getGanzzahl() throws RemoteException;

  public void setGanzzahl(Integer ganzzahl) throws RemoteException;

  public boolean getJanein() throws RemoteException;

  public void setJanein(boolean janein) throws RemoteException;
}
