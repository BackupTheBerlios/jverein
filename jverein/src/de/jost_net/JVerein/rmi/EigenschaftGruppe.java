/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/rmi/EigenschaftGruppe.java,v $
 * $Revision: 1.4 $
 * $Date: 2011/06/19 06:33:04 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: EigenschaftGruppe.java,v $
 * Revision 1.4  2011/06/19 06:33:04  jost
 * Umstellung Datenbanktyp für booleans von char(5) auf boolean (h2) bzw. tinyint (MySQL)
 *
 * Revision 1.3  2010-11-27 19:29:11  jost
 * Optional: max. eine Eigenschaft auswählbar
 *
 * Revision 1.2  2010-09-09 18:50:57  jost
 * Eigenschaftengruppen können jetzt auch das Merkmal "Pflicht" haben. Dann muß mindestens eine Eigenschaft ausgewählt werden.
 *
 * Revision 1.1  2009/11/17 21:02:05  jost
 * Neu: Eigenschaft und EigenschaftGruppe
 *
 **********************************************************************/
package de.jost_net.JVerein.rmi;

import java.rmi.RemoteException;

import de.willuhn.datasource.rmi.DBObject;

public interface EigenschaftGruppe extends DBObject
{
  public String getBezeichnung() throws RemoteException;

  public void setBezeichnung(String bezeichnung) throws RemoteException;

  public void setPflicht(Boolean pflicht) throws RemoteException;

  public Boolean getPflicht() throws RemoteException;

  public void setMax1(Boolean max1) throws RemoteException;

  public Boolean getMax1() throws RemoteException;

}
