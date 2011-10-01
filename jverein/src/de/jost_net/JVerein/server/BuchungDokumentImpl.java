/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/server/BuchungDokumentImpl.java,v $
 * $Revision: 1.2 $
 * $Date: 2011/10/01 21:50:35 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
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
