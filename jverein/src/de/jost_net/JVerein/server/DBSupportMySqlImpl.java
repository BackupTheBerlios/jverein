/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/server/DBSupportMySqlImpl.java,v $
 * $Revision: 1.12 $
 * $Date: 2011/10/01 21:50:36 $
 * $Author: jost $
 * Copyright (c) by Michael Trapp
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/
package de.jost_net.JVerein.server;

import java.io.File;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.rmi.JVereinDBService;
import de.willuhn.jameica.system.Application;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

/**
 * Implementierung des Datenbank-Supports fuer MySQL.
 */
public class DBSupportMySqlImpl extends AbstractDBSupportImpl
{

  private static final long serialVersionUID = 3516299482096025540L;

  /**
   * @see de.willuhn.jameica.hbci.rmi.DBSupport#getJdbcDriver()
   */
  public String getJdbcDriver()
  {
    return JVereinDBService.SETTINGS.getString(
        "database.driver.mysql.jdbcdriver", "com.mysql.jdbc.Driver");
  }

  /**
   * @see de.willuhn.jameica.hbci.rmi.DBSupport#getJdbcPassword()
   */
  public String getJdbcPassword()
  {
    String key = "database.driver.mysql.password";

    return JVereinDBService.SETTINGS.getString(key, null);
  }

  /**
   * @see de.willuhn.jameica.hbci.rmi.DBSupport#getJdbcUrl()
   */
  public String getJdbcUrl()
  {
    return JVereinDBService.SETTINGS
        .getString(
            "database.driver.mysql.jdbcurl",
            "jdbc:mysql://localhost:3306/jverein?useUnicode=Yes&characterEncoding=ISO8859_1");
  }

  /**
   * @see de.willuhn.jameica.hbci.rmi.DBSupport#getJdbcUsername()
   */
  public String getJdbcUsername()
  {
    return JVereinDBService.SETTINGS.getString(
        "database.driver.mysql.username", "jverein");
  }

  @Override
  public void checkConsistency(Connection conn) throws ApplicationException
  {
    if (!Application.inClientMode())
    {
      try
      {
        new JVereinUpdateProvider(conn, Application.getCallback()
            .getStartupMonitor());
      }
      catch (Exception e2)
      {
        Logger.error(
            JVereinPlugin.getI18n().tr(
                "Datenbankupdate kann nicht ausgef�hrt werden."), e2);
        throw new ApplicationException(e2);
      }
    }
  }

  /**
   * 
   */
  @Override
  public void execute(Connection conn, File sqlScript) throws RemoteException
  {
    if (sqlScript == null)
      return; // Ignore

    sqlScript = new File(sqlScript.getParent(), sqlScript.getName());
    if (!sqlScript.exists())
    {
      Logger.debug("file " + sqlScript + " does not exist, skipping");
      return;
    }
    super.execute(conn, sqlScript);
  }

  /**
   * @see de.willuhn.jameica.hbci.rmi.DBSupport#getSQLTimestamp(java.lang.String)
   */
  public String getSQLTimestamp(String content)
  {
    return MessageFormat.format("(UNIX_TIMESTAMP({0})*1000)",
        new Object[] { content });
  }

  /**
   * @see de.willuhn.jameica.hbci.rmi.DBSupport#getInsertWithID()
   */
  public boolean getInsertWithID()
  {
    return false;
  }

  private long lastCheck = 0;

  /**
   * @see de.willuhn.jameica.hbci.rmi.DBSupport#checkConnection(java.sql.Connection)
   */
  public void checkConnection(Connection conn) throws RemoteException
  {
    long newCheck = System.currentTimeMillis();
    if ((newCheck - lastCheck) < (10 * 1000L))
      return; // Wir checken hoechstens aller 10 Sekunden

    Statement s = null;
    ResultSet rs = null;
    try
    {
      s = conn.createStatement();
      rs = s.executeQuery("select 1");
      lastCheck = newCheck;
    }
    catch (SQLException e)
    {
      // das Ding liefert in getMessage() den kompletten Stacktrace mit, den
      // brauchen wir
      // nicht (das muellt uns nur das Log voll) Also fangen wir sie und werden
      // eine neue
      // saubere mit kurzem Fehlertext
      String msg = e.getMessage();
      if (msg != null && msg.indexOf("\n") != -1)
        msg = msg.substring(0, msg.indexOf("\n"));
      throw new RemoteException(msg);
    }
    finally
    {
      try
      {
        if (rs != null)
          rs.close();
        if (s != null)
          s.close();
      }
      catch (Exception e)
      {
        throw new RemoteException("unable to close statement/resultset", e);
      }
    }
  }

  @Override
  public int getTransactionIsolationLevel()
  {
    // damit sehen wir Datenbank-Updates durch andere
    // ohne vorher ein COMMIT machen zu muessen
    // Insbesondere bei MySQL sinnvoll.
    return Connection.TRANSACTION_READ_COMMITTED;
  }
}