/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/rmi/MailEmpfaenger.java,v $
 * $Revision: 1.1 $
 * $Date: 2010/02/01 21:02:25 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: MailEmpfaenger.java,v $
 * Revision 1.1  2010/02/01 21:02:25  jost
 * Neu: Einfache Mailfunktion
 *
 **********************************************************************/
package de.jost_net.JVerein.rmi;

import java.rmi.RemoteException;

import de.willuhn.datasource.rmi.DBObject;

public interface MailEmpfaenger extends DBObject
{
  /**
   * ID der zugeh�rigen Mail
   */
  public Mail getMail() throws RemoteException;

  /**
   * ID der zugeh�rigen Mail
   */
  public void setMail(Mail mail) throws RemoteException;

  /**
   * "Hart kodierte"-Mailadresse
   */
  public String getAdresse() throws RemoteException;

  /**
   * "Hart kodierte"-Mailadresse
   */

  public void setAdresse(String adresse) throws RemoteException;

  /**
   * Mitglied ist Mail-Empf�nger
   */
  public Mitglied getMitglied() throws RemoteException;

  /**
   * Mitglied ist Mail-Empf�nger
   */

  public void setMitglied(Mitglied mitglied) throws RemoteException;

  /**
   * Gibt entweder die Mailadresse des Mitgliedes oder die "nackte" Adresse
   * zur�ck
   */
  public String getMailAdresse() throws RemoteException;

}
