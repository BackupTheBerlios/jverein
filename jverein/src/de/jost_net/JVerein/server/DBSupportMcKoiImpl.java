/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/server/Attic/DBSupportMcKoiImpl.java,v $
 * $Revision: 1.5 $
 * $Date: 2010/10/15 09:58:28 $
 * $Author: jost $
 *
 * Kopie aus Hibiscus
 * Copyright (c) by willuhn software & services
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: DBSupportMcKoiImpl.java,v $
 * Revision 1.5  2010/10/15 09:58:28  jost
 * Code aufger�umt
 *
 * Revision 1.4  2009-11-17 21:02:51  jost
 * DB-Aktualisierung optimiert.
 *
 * Revision 1.3  2009/04/10 09:53:08  jost
 * Umstellung auf aktuelle Methoden.
 *
 * Revision 1.2  2007/12/01 17:47:37  jost
 * Neue DB-Update-Mimik
 *
 * Revision 1.1  2007/10/18 18:20:23  jost
 * Vorbereitung H2-DB
 *
 **********************************************************************/

package de.jost_net.JVerein.server;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.text.MessageFormat;

import de.jost_net.JVerein.JVereinPlugin;
import de.willuhn.datasource.db.EmbeddedDatabase;
import de.willuhn.jameica.system.Application;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;
import de.willuhn.util.I18N;
import de.willuhn.util.ProgressMonitor;

/**
 * Implementierung des Datenbank-Supports fuer McKoi.
 */
public class DBSupportMcKoiImpl extends AbstractDBSupportImpl
{

  private static final long serialVersionUID = -1928366492576556400L;

  public String getJdbcDriver()
  {
    return "com.mckoi.JDBCDriver";
  }

  public String getJdbcPassword()
  {
    return "examplepassword";
  }

  public String getJdbcUrl()
  {
    return ":jdbc:mckoi:local://"
        + Application.getPluginLoader().getPlugin(JVereinPlugin.class).getResources().getWorkPath()
        + "/db/db.conf";
  }

  /**
   * @see de.willuhn.jameica.hbci.rmi.DBSupport#getJdbcUsername()
   */
  public String getJdbcUsername()
  {
    return "exampleuser";
  }

  @Override
  public void checkConsistency(Connection conn) throws ApplicationException
  {
    if (!Application.inClientMode())
    {
      try
      {
        new JVereinUpdateProvider(conn,
            Application.getCallback().getStartupMonitor());
      }
      catch (Exception e2)
      {
        Logger.error("Datenbankupdate kann nicht ausgef�hrt werden.", e2);
        throw new ApplicationException(e2);
      }
    }
  }

  @Override
  public void install() throws RemoteException
  {
    try
    {
      EmbeddedDatabase db = new EmbeddedDatabase(
          Application.getPluginLoader().getPlugin(JVereinPlugin.class).getResources().getWorkPath()
              + "/db", getJdbcUsername(), getJdbcPassword());
      if (!db.exists())
      {
        I18N i18n = Application.getPluginLoader().getPlugin(JVereinPlugin.class).getResources().getI18N();
        ProgressMonitor monitor = Application.getCallback().getStartupMonitor();
        monitor.setStatusText(i18n.tr("Erstelle JVerein-Datenbank"));
        db.create();
      }
    }
    catch (Exception e)
    {
      throw new RemoteException("unable to create embedded database", e);
    }
  }

  public String getSQLTimestamp(String content)
  {
    return MessageFormat.format("tonumber({0})", new Object[] { content});
  }

  public boolean getInsertWithID()
  {
    return true;
  }

  public void checkConnection(Connection conn)
  {
    // brauchen wir bei McKoi nicht
  }
}
