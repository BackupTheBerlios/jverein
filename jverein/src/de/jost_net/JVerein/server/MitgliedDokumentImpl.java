/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/server/MitgliedDokumentImpl.java,v $
 * $Revision: 1.1 $
 * $Date: 2010/12/14 21:32:56 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: MitgliedDokumentImpl.java,v $
 * Revision 1.1  2010/12/14 21:32:56  jost
 * Neu: Speicherung von Dokumenten
 *
 **********************************************************************/
package de.jost_net.JVerein.server;

import java.rmi.RemoteException;

import de.jost_net.JVerein.rmi.MitgliedDokument;

public class MitgliedDokumentImpl extends AbstractDokumentImpl implements
    MitgliedDokument
{

  private static final long serialVersionUID = 1L;

  public MitgliedDokumentImpl() throws RemoteException
  {
    super();
  }

  @Override
  protected String getTableName()
  {
    return "mitglieddokument";
  }

}
