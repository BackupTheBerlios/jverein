/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/server/BuchungDokumentImpl.java,v $
 * $Revision: 1.1 $
 * $Date: 2010/12/12 08:14:02 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: BuchungDokumentImpl.java,v $
 * Revision 1.1  2010/12/12 08:14:02  jost
 * Neu: Speicherung von Dokumenten
 *
 **********************************************************************/
package de.jost_net.JVerein.server;

import java.rmi.RemoteException;

import de.jost_net.JVerein.rmi.BuchungDokument;

public class BuchungDokumentImpl extends AbstractDokumentImpl implements
    BuchungDokument
{

  private static final long serialVersionUID = 1L;

  public BuchungDokumentImpl() throws RemoteException
  {
    super();
  }

  @Override
  protected String getTableName()
  {
    return "buchungdokument";
  }

}
