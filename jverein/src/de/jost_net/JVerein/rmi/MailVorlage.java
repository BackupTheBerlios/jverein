/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/rmi/MailVorlage.java,v $
 * $Revision: 1.1 $
 * $Date: 2010/02/01 21:02:25 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: MailVorlage.java,v $
 * Revision 1.1  2010/02/01 21:02:25  jost
 * Neu: Einfache Mailfunktion
 *
 **********************************************************************/
package de.jost_net.JVerein.rmi;

import java.rmi.RemoteException;

import de.willuhn.datasource.rmi.DBObject;

public interface MailVorlage extends DBObject
{
  public String getBetreff() throws RemoteException;

  public void setBetreff(String betreff) throws RemoteException;

  public String getTxt() throws RemoteException;

  public void setTxt(String txt) throws RemoteException;

}
