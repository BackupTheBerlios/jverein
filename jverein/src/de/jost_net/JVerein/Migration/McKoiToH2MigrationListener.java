/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/Migration/Attic/McKoiToH2MigrationListener.java,v $
 * $Revision: 1.1 $
 * $Date: 2007/12/01 10:07:07 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: McKoiToH2MigrationListener.java,v $
 * Revision 1.1  2007/12/01 10:07:07  jost
 * H2-Support
 *
 **********************************************************************/

package de.jost_net.JVerein.Migration;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.rmi.JVereinDBService;
import de.jost_net.JVerein.server.DBSupportMcKoiImpl;
import de.willuhn.jameica.messaging.Message;
import de.willuhn.jameica.messaging.MessageConsumer;
import de.willuhn.jameica.messaging.SystemMessage;
import de.willuhn.jameica.system.Application;
import de.willuhn.jameica.system.Settings;
import de.willuhn.logging.Logger;
import de.willuhn.util.I18N;

/**
 * Wird benachrichtigt, wenn die Anwendung gestartet wurde und zeigt ggf. ein
 * Dialog fuer die Datenmigration von Mckoi zu H2 an.
 */
public class McKoiToH2MigrationListener implements MessageConsumer
{
  public boolean autoRegister()
  {
    Settings s = Application.getPluginLoader().getPlugin(JVereinPlugin.class)
        .getResources().getSettings();
    return s.getBoolean("migration.h2", false);
  }

  public Class[] getExpectedMessageTypes()
  {
    return new Class[] { SystemMessage.class };
  }

  public void handleMessage(Message message) throws Exception
  {
    if (message == null || !(message instanceof SystemMessage))
      return;

    if (((SystemMessage) message).getStatusCode() != SystemMessage.SYSTEM_STARTED)
      return;

    // Checken, ob Migration schon lief
    if (DatabaseMigrationTask.SETTINGS.getString("migration.mckoi-to-h2", null) != null)
      return; // lief bereits

    // Checken, ob ueberhaupt die McKoi-Datenbank genutzt wird
    String driver = JVereinDBService.SETTINGS
        .getString("database.driver", null);
    if (driver == null || !driver.equals(DBSupportMcKoiImpl.class.getName()))
      return;

    Settings s = Application.getPluginLoader().getPlugin(JVereinPlugin.class)
        .getResources().getSettings();
    if (!s.getBoolean("migration.h2", false))
      return;

    I18N i18n = Application.getPluginLoader().getPlugin(JVereinPlugin.class)
        .getResources().getI18N();

    String text = i18n
        .tr("Das Datenbank-Format von JVerein wurde umgestellt.\nM�chten Sie jetzt die �bernahme der Daten in das neue Format durchf�hren?");
    if (!Application.getCallback().askUser(text))
      return;

    Logger.warn("starting database migration from mckoi to h2");
    Application.getController().start(new McKoiToH2MigrationTask());
  }
}