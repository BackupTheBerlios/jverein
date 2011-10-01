/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/dialogs/ImportDialog.java,v $
 * $Revision: 1.5 $
 * $Date: 2011/10/01 21:43:36 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe 
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/
package de.jost_net.JVerein.gui.dialogs;

import java.io.File;
import java.rmi.RemoteException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.io.IORegistry;
import de.jost_net.JVerein.io.Importer;
import de.willuhn.datasource.GenericObject;
import de.willuhn.datasource.pseudo.PseudoIterator;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.dialogs.AbstractDialog;
import de.willuhn.jameica.gui.input.Input;
import de.willuhn.jameica.gui.input.LabelInput;
import de.willuhn.jameica.gui.input.SelectInput;
import de.willuhn.jameica.gui.parts.Button;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.gui.util.LabelGroup;
import de.willuhn.jameica.system.Application;
import de.willuhn.jameica.system.BackgroundTask;
import de.willuhn.jameica.system.OperationCanceledException;
import de.willuhn.jameica.system.Settings;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;
import de.willuhn.util.ProgressMonitor;

/**
 * Dialog, ueber den Daten importiert werden koennen.
 */
public class ImportDialog extends AbstractDialog
{

  private Input importerListe = null;

  private GenericObject context = null;

  private Class<?> type = null;

  public ImportDialog(GenericObject context, Class<?> type)
  {
    super(POSITION_CENTER);
    i18n = JVereinPlugin.getI18n();
    setTitle(i18n.tr("Daten-Import"));
    this.context = context;
    this.type = type;
  }

  @Override
  protected void paint(Composite parent) throws Exception
  {
    LabelGroup group = new LabelGroup(parent,
        i18n.tr("Auswahl des Import-Filters"));
    group.addText(i18n
        .tr("Bitte w�hlen Sie das gew�nschte Dateiformat f�r den Import aus"),
        true);

    Input formats = getImporterList();
    group.addLabelPair(i18n.tr("Verf�gbare Formate:"), formats);

    ButtonArea buttons = new ButtonArea(parent, 2);
    Button button = new Button(i18n.tr("Import starten"), new Action()
    {

      public void handleAction(Object context) throws ApplicationException
      {
        doImport();
        close();
      }
    }, null, true);
    button.setEnabled(!(formats instanceof LabelInput));
    buttons.addButton(button);
    buttons.addButton(i18n.tr("Abbrechen"), new Action()
    {

      public void handleAction(Object context)
      {
        throw new OperationCanceledException();
      }
    });
  }

  /**
   * Importiert die Daten.
   * 
   * @throws ApplicationException
   */
  private void doImport() throws ApplicationException
  {
    Imp imp = null;

    try
    {
      imp = (Imp) getImporterList().getValue();
    }
    catch (Exception e)
    {
      Logger.error("error while saving import file", e);
      throw new ApplicationException(
          i18n.tr("Fehler beim Starten des Imports"), e);
    }

    if (imp == null || imp.importer == null)
      throw new ApplicationException(
          i18n.tr("Bitte w�hlen Sie ein Import-Format aus"));

    Settings settings = new Settings(this.getClass());
    settings.setStoreWhenRead(true);

    final Importer importer = imp.importer;
    if (importer.hasFileDialog())
    {

      FileDialog fd = new FileDialog(GUI.getShell(), SWT.OPEN);
      fd.setText(JVereinPlugin.getI18n().tr(
          "Bitte w�hlen Sie die Import-Datei aus."));

      String path = settings.getString("lastdir",
          System.getProperty("user.home"));
      if (path != null && path.length() > 0)
      {
        fd.setFilterPath(path);
      }
      final String s = fd.open();

      if (s == null || s.length() == 0)
      {
        return;
      }

      final File file = new File(s);
      if (!file.exists() || !file.isFile())
      {
        throw new ApplicationException(JVereinPlugin.getI18n().tr(
            "Datei existiert nicht oder ist nicht lesbar"));
      }
      // Wir merken uns noch das Verzeichnis f�rs n�chste mal
      settings.setAttribute("lastdir", file.getParent());
      importer.set(file.getParent(), file.getName());
    }

    BackgroundTask t = new BackgroundTask()
    {

      public void run(ProgressMonitor monitor) throws ApplicationException
      {
        try
        {
          importer.doImport(context, monitor);
          monitor.setPercentComplete(100);
          monitor.setStatus(ProgressMonitor.STATUS_DONE);
          GUI.getStatusBar().setSuccessText(i18n.tr("Daten importiert"));
          GUI.getCurrentView().reload();
        }
        catch (ApplicationException ae)
        {
          monitor.setStatusText(ae.getMessage());
          monitor.setStatus(ProgressMonitor.STATUS_ERROR);
          GUI.getStatusBar().setErrorText(ae.getMessage());
          throw ae;
        }
        catch (Exception e)
        {
          monitor.setStatus(ProgressMonitor.STATUS_ERROR);
          Logger.error("error while reading objects", e);
          ApplicationException ae = new ApplicationException(
              i18n.tr("Fehler beim Importieren der Daten"), e);
          monitor.setStatusText(ae.getMessage());
          GUI.getStatusBar().setErrorText(ae.getMessage());
          throw ae;
        }
      }

      public void interrupt()
      {
        //
      }

      public boolean isInterrupted()
      {
        return false;
      }
    };

    Application.getController().start(t);
  }

  /**
   * Liefert eine Liste der verfuegbaren Importer.
   * 
   * @return Liste der Importer.
   * @throws Exception
   */
  private Input getImporterList() throws Exception
  {
    if (importerListe != null)
    {
      return importerListe;
    }

    Importer[] importers = IORegistry.getImporter(type);

    ArrayList<Imp> l = new ArrayList<Imp>();

    int size = 0;

    for (int i = 0; i < importers.length; ++i)
    {
      Importer imp = importers[i];
      if (imp == null)
      {
        continue;
      }
      l.add(new Imp(imp));
      size++;
    }

    if (size == 0)
    {
      importerListe = new LabelInput(i18n.tr("Keine Import-Filter verf�gbar"));
      return importerListe;
    }

    Imp[] imp = l.toArray(new Imp[size]);
    importerListe = new SelectInput(PseudoIterator.fromArray(imp), null);
    return importerListe;
  }

  /**
   * @see de.willuhn.jameica.gui.dialogs.AbstractDialog#getData()
   */
  @Override
  protected Object getData() throws Exception
  {
    return null;
  }

  /**
   * Hilfsklasse zur Anzeige der Importer.
   */
  private static class Imp implements GenericObject
  {

    private Importer importer = null;

    private Imp(Importer importer)
    {
      this.importer = importer;
    }

    /**
     * @see de.willuhn.datasource.GenericObject#getAttribute(java.lang.String)
     */
    public Object getAttribute(String arg0)
    {
      return this.importer.getName();
    }

    /**
     * @see de.willuhn.datasource.GenericObject#getAttributeNames()
     */
    public String[] getAttributeNames()
    {
      return new String[] { "name" };
    }

    /**
     * @see de.willuhn.datasource.GenericObject#getID()
     */
    public String getID()
    {
      return this.importer.getClass().getName();
    }

    /**
     * @see de.willuhn.datasource.GenericObject#getPrimaryAttribute()
     */
    public String getPrimaryAttribute()
    {
      return "name";
    }

    /**
     * @see de.willuhn.datasource.GenericObject#equals(de.willuhn.datasource.GenericObject)
     */
    public boolean equals(GenericObject arg0) throws RemoteException
    {
      if (arg0 == null)
      {
        return false;
      }
      return this.getID().equals(arg0.getID());
    }
  }
}