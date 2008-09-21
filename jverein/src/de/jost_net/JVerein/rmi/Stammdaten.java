/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/rmi/Attic/Stammdaten.java,v $
 * $Revision: 1.6 $
 * $Date: 2008/09/21 08:46:20 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: Stammdaten.java,v $
 * Revision 1.6  2008/09/21 08:46:20  jost
 * Neu: Altersjubliäen
 *
 * Revision 1.5  2007/12/28 13:16:52  jost
 * Bugfix beim erzeugen eines Stammdaten-Objektes
 *
 * Revision 1.3  2007/02/23 20:28:24  jost
 * Mail- und Webadresse im Header korrigiert.
 *
 * Revision 1.2  2006/10/29 07:50:22  jost
 * Neu: Mitgliederstatistik
 *
 * Revision 1.1  2006/09/20 15:39:35  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.JVerein.rmi;

import java.rmi.RemoteException;

import de.willuhn.datasource.rmi.DBObject;

public interface Stammdaten extends DBObject
{
  public String getName() throws RemoteException;

  public void setName(String name) throws RemoteException;

  public String getBlz() throws RemoteException;

  public void setBlz(String blz) throws RemoteException;

  public String getKonto() throws RemoteException;

  public void setKonto(String konto) throws RemoteException;

  public String getAltersgruppen() throws RemoteException;

  public void setAltersgruppen(String altersgruppen) throws RemoteException;

  public String getJubilaeen() throws RemoteException;

  public void setJubilaeen(String jubilaeen) throws RemoteException;

  public String getAltersjubilaeen() throws RemoteException;

  public void setAltersjubilaeen(String altersjubilaeen) throws RemoteException;
}
