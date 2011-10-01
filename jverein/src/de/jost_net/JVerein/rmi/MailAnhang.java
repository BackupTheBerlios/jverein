/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/rmi/MailAnhang.java,v $
 * $Revision: 1.2 $
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

public interface MailAnhang extends DBObject
{
  /**
   * ID der zugehörigen Mail
   */
  public Mail getMail() throws RemoteException;

  /**
   * ID der zugehörigen Mail
   */
  public void setMail(Mail mail) throws RemoteException;

  public byte[] getAnhang() throws RemoteException;

  public void setAnhang(byte[] anhang) throws RemoteException;

  public String getDateiname() throws RemoteException;

  public void setDateiname(String dateiname) throws RemoteException;

}
