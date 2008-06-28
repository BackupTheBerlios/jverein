/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/rmi/Konto.java,v $
 * $Revision: 1.3 $
 * $Date: 2008/06/28 17:06:31 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: Konto.java,v $
 * Revision 1.3  2008/06/28 17:06:31  jost
 * Neu: Jahresabschluss
 *
 * Revision 1.2  2008/05/26 18:59:04  jost
 * Neu: Eröffnungsdatum
 *
 * Revision 1.1  2008/05/22 06:55:38  jost
 * Buchführung
 *
 **********************************************************************/
package de.jost_net.JVerein.rmi;

import java.rmi.RemoteException;
import java.util.Date;

import de.jost_net.JVerein.util.Geschaeftsjahr;
import de.willuhn.datasource.rmi.DBIterator;
import de.willuhn.datasource.rmi.DBObject;

public interface Konto extends DBObject
{
  public String getID() throws RemoteException;

  public String getNummer() throws RemoteException;

  public void setNummer(String nummer) throws RemoteException;

  public String getBezeichnung() throws RemoteException;

  public void setBezeichnung(String bezeichnung) throws RemoteException;

  public Date getEroeffnung() throws RemoteException;

  public void setEroeffnung(Date eroeffnung) throws RemoteException;

  public Date getAufloesung() throws RemoteException;

  public void setAufloesung(Date aufloesungsdatum) throws RemoteException;

  public Integer getHibiscusId() throws RemoteException;

  public void setHibiscusId(Integer HibiscusId) throws RemoteException;

  public DBIterator getKontenEinesJahres(Geschaeftsjahr gj)
      throws RemoteException;

}
