/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/rmi/Mitgliedskonto.java,v $
 * $Revision: 1.3 $
 * $Date: 2010/07/25 18:46:21 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: Mitgliedskonto.java,v $
 * Revision 1.3  2010/07/25 18:46:21  jost
 * Neu: Mitgliedskonto
 *
 * Revision 1.2  2010/05/18 20:24:32  jost
 * Anpassung Klassenname
 *
 * Revision 1.1  2010/04/28 06:17:50  jost
 * Neu: Mitgliedskonto
 *
 **********************************************************************/
package de.jost_net.JVerein.rmi;

import java.rmi.RemoteException;
import java.util.Date;

import de.willuhn.datasource.rmi.DBObject;

public interface Mitgliedskonto extends DBObject
{

  public String getID() throws RemoteException;

  public Abrechnungslauf getAbrechnungslauf() throws RemoteException;

  public void setAbrechnungslauf(Abrechnungslauf abrechnungslauf)
      throws RemoteException;

  public Mitglied getMitglied() throws RemoteException;

  public void setMitglied(Mitglied mitglied) throws RemoteException;

  public Date getDatum() throws RemoteException;

  public void setDatum(Date datum) throws RemoteException;

  public String getZweck1() throws RemoteException;

  public void setZweck1(String zweck1) throws RemoteException;

  public String getZweck2() throws RemoteException;

  public void setZweck2(String zweck2) throws RemoteException;

  public Integer getZahlungsweg() throws RemoteException;

  public void setZahlungsweg(Integer zahlungsweg) throws RemoteException;

  public void setBetrag(Double betrag) throws RemoteException;

  public Double getBetrag() throws RemoteException;

  public void setIstBetrag(Double betrag) throws RemoteException;

  public Double getIstBetrag() throws RemoteException;

}
