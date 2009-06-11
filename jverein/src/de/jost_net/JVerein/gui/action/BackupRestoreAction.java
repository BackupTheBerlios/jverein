/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/BackupRestoreAction.java,v $
 * $Revision: 1.4 $
 * $Date: 2009/06/11 21:02:05 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: BackupRestoreAction.java,v $
 * Revision 1.4  2009/06/11 21:02:05  jost
 * Vorbereitung I18N
 *
 * Revision 1.3  2009/01/27 18:50:15  jost
 * Import-Statement korrigiert
 *
 * Revision 1.2  2008/11/29 13:05:10  jost
 * Refactoring: Warnungen beseitigt.
 *
 * Revision 1.1  2008/09/29 14:42:38  jost
 * Neu: Backup und Restore im XML-Format
 *
 **********************************************************************/

package de.jost_net.JVerein.gui.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.JVereinPlugin;
import de.willuhn.datasource.BeanUtil;
import de.willuhn.datasource.GenericObject;
import de.willuhn.datasource.db.AbstractDBObject;
import de.willuhn.datasource.serialize.ObjectFactory;
import de.willuhn.datasource.serialize.Reader;
import de.willuhn.datasource.serialize.XmlReader;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.jost_net.JVerein.gui.action.BackupCreateAction;
import de.willuhn.jameica.system.Application;
import de.willuhn.jameica.system.BackgroundTask;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;
import de.willuhn.util.ProgressMonitor;

/**
 * Action zum Einspielen eines XML-Backups.
 */
public class BackupRestoreAction implements Action
{

  /**
   * @see de.willuhn.jameica.gui.Action#handleAction(java.lang.Object)
   */
  public void handleAction(Object context) throws ApplicationException
  {
    FileDialog fd = new FileDialog(GUI.getShell(), SWT.OPEN);
    fd.setFileName("jverein-"
        + BackupCreateAction.DATEFORMAT.format(new Date()) + ".xml");
    fd.setFilterExtensions(new String[] { "*.xml" });
    fd.setText(JVereinPlugin.getI18n().tr(
        "Bitte w�hlen Sie die Backup-Datei aus"));
    String f = fd.open();
    if (f == null || f.length() == 0)
    {
      return;
    }

    final File file = new File(f);
    if (!file.exists())
    {
      return;
    }

    Application.getController().start(new BackgroundTask()
    {
      private boolean cancel = false;

      /**
       * @see de.willuhn.jameica.system.BackgroundTask#run(de.willuhn.util.ProgressMonitor)
       */
      public void run(ProgressMonitor monitor) throws ApplicationException
      {
        monitor.setStatusText(JVereinPlugin.getI18n().tr("Importiere Backup"));
        Logger.info("importing backup " + file.getAbsolutePath());
        final ClassLoader loader = Application.getPluginLoader().getPlugin(
            JVereinPlugin.class).getResources().getClassLoader();

        Reader reader = null;
        try
        {
          InputStream is = new BufferedInputStream(new FileInputStream(file));
          reader = new XmlReader(is, new ObjectFactory()
          {

            @SuppressWarnings("unchecked")
            public GenericObject create(String type, String id, Map values)
                throws Exception
            {
              AbstractDBObject object = (AbstractDBObject) Einstellungen
                  .getDBService().createObject(loader.loadClass(type), null);
              object.setID(id);
              Iterator i = values.keySet().iterator();
              while (i.hasNext())
              {
                String name = (String) i.next();
                object.setAttribute(name, values.get(name));
              }
              return object;
            }

          });

          long count = 1;
          GenericObject o = null;
          while ((o = reader.read()) != null)
          {
            try
            {
              ((AbstractDBObject) o).insert();
            }
            catch (Exception e)
            {
              Logger.error("unable to import " + o.getClass().getName() + ":"
                  + o.getID() + ", skipping", e);
              monitor.log(JVereinPlugin.getI18n().tr(
                  " {0} fehlerhaft: {1}, �berspringe ",
                  new String[] { BeanUtil.toString(o), e.getMessage() }));
            }
            if (count++ % 100 == 0)
            {
              monitor.addPercentComplete(1);
            }
          }

          monitor
              .setStatusText(JVereinPlugin.getI18n().tr("Backup importiert"));
          monitor.setPercentComplete(100);
          monitor.setStatus(ProgressMonitor.STATUS_DONE);
        }
        catch (Exception e)
        {
          Logger.error("error while importing data", e);
          throw new ApplicationException(e.getMessage());
        }
        finally
        {
          if (reader != null)
          {
            try
            {
              reader.close();
              Logger.info("backup imported");
            }
            catch (Exception e)
            {/* useless */
            }
          }
        }
      }

      /**
       * @see de.willuhn.jameica.system.BackgroundTask#isInterrupted()
       */
      public boolean isInterrupted()
      {
        return this.cancel;
      }

      /**
       * @see de.willuhn.jameica.system.BackgroundTask#interrupt()
       */
      public void interrupt()
      {
        this.cancel = true;
      }

    });
  }
}
