/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/server/MitgliedDokumentImpl.java,v $
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
