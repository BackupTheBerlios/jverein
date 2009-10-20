/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/io/Importer.java,v $
 * $Revision: 1.1 $
 * $Date: 2009/10/20 18:00:18 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: Importer.java,v $
 * Revision 1.1  2009/10/20 18:00:18  jost
 * Neu: Import von Zusatzbeträgen
 *
 **********************************************************************/

package de.jost_net.JVerein.io;

import java.rmi.RemoteException;

import de.willuhn.util.ApplicationException;
import de.willuhn.util.ProgressMonitor;

/**
 * Basis-Interface aller Importer.
 */
public interface Importer extends IO
{

  public void doImport(Object context, ProgressMonitor monitor)
      throws RemoteException, ApplicationException;

  public boolean hasFileDialog();

  public void set(String path, String file);

  public String getPath();

  public String getFile();
}
