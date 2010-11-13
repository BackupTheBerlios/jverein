/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/Migration/Attic/McKoiToH2MigrationTask.java,v $
 * $Revision: 1.5 $
 * $Date: 2010/11/13 09:27:36 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: McKoiToH2MigrationTask.java,v $
 * Revision 1.5  2010/11/13 09:27:36  jost
 * Mit V 1.5 deprecatete Spalten und Tabellen entfernt.
 *
 * Revision 1.4  2010-10-15 09:58:30  jost
 * Code aufger�umt
 *
 * Revision 1.3  2008-11-29 13:14:18  jost
 * Refactoring: Warnungen beseitigt.
 *
 * Revision 1.2  2008/01/01 12:36:16  jost
 * Javadoc korrigiert
 *
 * Revision 1.1  2007/12/01 10:07:19  jost
 * H2-Support
 *
 **********************************************************************/

package de.jost_net.JVerein.Migration;

import java.rmi.RemoteException;
import java.util.Date;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.rmi.JVereinDBService;
import de.jost_net.JVerein.server.DBSupportH2Impl;
import de.jost_net.JVerein.server.JVereinDBServiceImpl;
import de.willuhn.datasource.db.AbstractDBObject;
import de.willuhn.jameica.gui.internal.action.FileClose;
import de.willuhn.jameica.system.Application;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;
import de.willuhn.util.ProgressMonitor;

/**
 * Migration von McKoi nach H2.
 */
public class McKoiToH2MigrationTask extends DatabaseMigrationTask
{
  @Override
  public void run(ProgressMonitor monitor) throws ApplicationException
  {
    // Checken, ob die Migration schon lief
    if (SETTINGS.getString("migration.mckoi-to-h2", null) != null)
      throw new ApplicationException(i18n
          .tr("Datenmigration bereits durchgef�hrt"));

    try
    {
      setSource(Einstellungen.getDBService());

      H2DBServiceImpl target = new H2DBServiceImpl();
      target.start();
      target.install();

      setTarget(target);
    }
    catch (RemoteException re)
    {
      monitor.setStatusText(re.getMessage());
      monitor.setStatus(ProgressMonitor.STATUS_ERROR);
      throw new ApplicationException(re);
    }
    super.run(monitor);

    // Datum der Migration speichern
    SETTINGS.setAttribute("migration.mckoi-to-h2", Einstellungen.TIMESTAMPFORMAT
        .format(new Date()));

    // Datenbank-Treiber umstellen
    JVereinDBService.SETTINGS.setAttribute("database.driver",
        DBSupportH2Impl.class.getName());

    // User ueber Neustart benachrichtigen
    String text = i18n
        .tr("Datenmigration erfolgreich beendet.\nHibiscus wird nun beendet. Starten Sie die Anwendung anschlie�end bitte neu.");
    try
    {
      Application.getCallback().notifyUser(text);
    }
    catch (Exception e)
    {
      Logger.error("unable to notify user about restart", e);
    }

    // JVerein beenden
    new FileClose().handleAction(null);
  }

  @Override
  protected void fixObject(AbstractDBObject object, ProgressMonitor monitor)
      throws RemoteException
  {
    super.fixObject(object, monitor);
  }

  @Override
  protected void copy(Class<?> type, ProgressMonitor monitor) throws Exception
  {
    super.copy(type, monitor);
  }

  /**
   * Wrapper des DB-Service, damit die Identifier gross geschrieben werden.
   */
  public static class H2DBServiceImpl extends JVereinDBServiceImpl
  {
    private static final long serialVersionUID = 4298826411943981642L;

    public H2DBServiceImpl() throws RemoteException
    {
      super(DBSupportH2Impl.class.getName());

      // Der Konstruktor von DBSupportH2Impl hat bereits Gross-Schreibung
      // fuer HBCIDBService aktiviert - nochmal fuer die Migration
      // deaktivieren
      System.setProperty(JVereinDBServiceImpl.class.getName() + ".uppercase",
          "false");

      // Fuer uns selbst aktivieren wir es jedoch
      System
          .setProperty(H2DBServiceImpl.class.getName() + ".uppercase", "true");
    }
  }
}
