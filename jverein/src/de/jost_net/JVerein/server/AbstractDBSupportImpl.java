/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/server/AbstractDBSupportImpl.java,v $
 * $Revision: 1.5 $
 * $Date: 2011/10/01 21:50:35 $
 * $Author: jost $
 *
 * Kopie aus Hibiscus
 * Copyright (c) by willuhn software & services
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/

package de.jost_net.JVerein.server;

import java.io.File;
import java.io.FileReader;
import java.rmi.RemoteException;
import java.sql.Connection;

import de.jost_net.JVerein.rmi.DBSupport;
import de.willuhn.logging.Logger;
import de.willuhn.sql.ScriptExecutor;
import de.willuhn.util.ApplicationException;

/**
 * Abstrakte Basisklasse fuer den Datenbank-Support.
 */
public abstract class AbstractDBSupportImpl implements DBSupport
{

  private static final long serialVersionUID = 8344265686929785808L;

  public void checkConsistency(Connection conn) throws RemoteException,
      ApplicationException
  {
    // Leere Dummy-Implementierung
  }

  public void execute(Connection conn, File sqlScript) throws RemoteException
  {
    if (sqlScript == null)
      return;

    if (!sqlScript.canRead() || !sqlScript.exists())
      return;

    Logger.info("executing sql script: " + sqlScript.getAbsolutePath());

    FileReader reader = null;

    try
    {
      reader = new FileReader(sqlScript);
      ScriptExecutor.execute(reader, conn);
    }
    catch (RemoteException re)
    {
      throw re;
    }
    catch (Exception e)
    {
      throw new RemoteException(
          "error while executing sql script " + sqlScript, e);
    }
    finally
    {
      try
      {
        if (reader != null)
          reader.close();
      }
      catch (Exception e3)
      {
        Logger.error("error while closing file " + sqlScript, e3);
      }
    }
  }

  public void install() throws RemoteException
  {
    // Leere Dummy-Implementierung
  }

  public int getTransactionIsolationLevel()
  {
    return -1;
  }

}
