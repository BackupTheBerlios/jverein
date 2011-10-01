/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/io/Importer.java,v $
 * $Revision: 1.2 $
 * $Date: 2011/10/01 21:47:49 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
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
