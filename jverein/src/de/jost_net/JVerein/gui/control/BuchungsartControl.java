/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/control/BuchungsartControl.java,v $
 * $Revision: 1.21 $
 * $Date: 2011/10/01 21:42:56 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/
package de.jost_net.JVerein.gui.control;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.rmi.RemoteException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;

import com.lowagie.text.Element;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.gui.action.BuchungsartAction;
import de.jost_net.JVerein.gui.formatter.JaNeinFormatter;
import de.jost_net.JVerein.gui.menu.BuchungsartMenu;
import de.jost_net.JVerein.io.Reporter;
import de.jost_net.JVerein.keys.ArtBuchungsart;
import de.jost_net.JVerein.rmi.Buchungsart;
import de.jost_net.JVerein.rmi.Buchungsklasse;
import de.jost_net.JVerein.util.Dateiname;
import de.willuhn.datasource.GenericObject;
import de.willuhn.datasource.rmi.DBIterator;
import de.willuhn.datasource.rmi.DBService;
import de.willuhn.jameica.gui.AbstractControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.Part;
import de.willuhn.jameica.gui.formatter.Formatter;
import de.willuhn.jameica.gui.input.CheckboxInput;
import de.willuhn.jameica.gui.input.Input;
import de.willuhn.jameica.gui.input.IntegerInput;
import de.willuhn.jameica.gui.input.SelectInput;
import de.willuhn.jameica.gui.input.TextInput;
import de.willuhn.jameica.gui.internal.action.Program;
import de.willuhn.jameica.gui.parts.Button;
import de.willuhn.jameica.gui.parts.Column;
import de.willuhn.jameica.gui.parts.TablePart;
import de.willuhn.jameica.messaging.StatusBarMessage;
import de.willuhn.jameica.system.Application;
import de.willuhn.jameica.system.BackgroundTask;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;
import de.willuhn.util.ProgressMonitor;

public class BuchungsartControl extends AbstractControl
{
  private de.willuhn.jameica.system.Settings settings;

  private TablePart buchungsartList;

  private IntegerInput nummer;

  private Input bezeichnung;

  private SelectInput art;

  private SelectInput buchungsklasse;

  private CheckboxInput spende;

  private Buchungsart buchungsart;

  public BuchungsartControl(AbstractView view)
  {
    super(view);
    settings = new de.willuhn.jameica.system.Settings(this.getClass());
    settings.setStoreWhenRead(true);
  }

  private Buchungsart getBuchungsart()
  {
    if (buchungsart != null)
    {
      return buchungsart;
    }
    buchungsart = (Buchungsart) getCurrentObject();
    return buchungsart;
  }

  public IntegerInput getNummer(boolean withFocus) throws RemoteException
  {
    if (nummer != null)
    {
      return nummer;
    }
    nummer = new IntegerInput(getBuchungsart().getNummer());
    if (withFocus)
    {
      nummer.focus();
    }
    return nummer;
  }

  public Input getBezeichnung() throws RemoteException
  {
    if (bezeichnung != null)
    {
      return bezeichnung;
    }
    bezeichnung = new TextInput(getBuchungsart().getBezeichnung(), 50);
    return bezeichnung;
  }

  public SelectInput getArt() throws RemoteException
  {
    if (art != null)
    {
      return art;
    }
    art = new SelectInput(ArtBuchungsart.getArray(), new ArtBuchungsart(
        getBuchungsart().getArt()));
    return art;
  }

  public CheckboxInput getSpende() throws RemoteException
  {
    if (spende != null)
    {
      return spende;
    }
    spende = new CheckboxInput(getBuchungsart().getSpende());
    return spende;
  }

  public Input getBuchungsklasse() throws RemoteException
  {
    if (buchungsklasse != null)
    {
      return buchungsklasse;
    }
    DBIterator list = Einstellungen.getDBService().createList(
        Buchungsklasse.class);
    list.setOrder("ORDER BY nummer");
    buchungsklasse = new SelectInput(list, getBuchungsart().getBuchungsklasse());
    buchungsklasse.setValue(getBuchungsart().getBuchungsklasse());
    buchungsklasse.setAttribute("bezeichnung");
    buchungsklasse.setPleaseChoose("Bitte ausw�hlen");
    return buchungsklasse;
  }

  /**
   * This method stores the project using the current values.
   */
  public void handleStore()
  {
    try
    {
      Buchungsart b = getBuchungsart();
      b.setNummer(((Integer) getNummer(false).getValue()).intValue());
      b.setBezeichnung((String) getBezeichnung().getValue());
      ArtBuchungsart ba = (ArtBuchungsart) getArt().getValue();
      b.setArt(ba.getKey());
      GenericObject o = (GenericObject) getBuchungsklasse().getValue();
      if (o != null)
      {
        b.setBuchungsklasse(new Integer(o.getID()));
      }
      else
      {
        b.setBuchungsklasse(null);
      }
      b.setSpende((Boolean) spende.getValue());

      try
      {
        b.store();
        GUI.getStatusBar().setSuccessText("Buchungsart gespeichert");
      }
      catch (ApplicationException e)
      {
        GUI.getStatusBar().setErrorText(e.getMessage());
      }
    }
    catch (RemoteException e)
    {
      String fehler = "Fehler bei speichern der Buchungsart";
      Logger.error(fehler, e);
      GUI.getStatusBar().setErrorText(fehler);
    }
  }

  public Part getBuchungsartList() throws RemoteException
  {
    DBService service = Einstellungen.getDBService();
    DBIterator buchungsarten = service.createList(Buchungsart.class);
    buchungsarten.addFilter("nummer >= 0");
    buchungsarten.setOrder("ORDER BY nummer");

    buchungsartList = new TablePart(buchungsarten, new BuchungsartAction());
    buchungsartList.addColumn("Nummer", "nummer");
    buchungsartList.addColumn("Bezeichnung", "bezeichnung");
    buchungsartList.addColumn("Art", "art", new Formatter()
    {
      public String format(Object o)
      {
        if (o == null)
        {
          return "";
        }
        if (o instanceof Integer)
        {
          Integer art = (Integer) o;
          switch (art.intValue())
          {
            case 0:
              return "Einnahme";
            case 1:
              return "Ausgabe";
            case 2:
              return "Umbuchung";
          }
        }
        return "ung�ltig";
      }
    }, false, Column.ALIGN_LEFT);
    buchungsartList.addColumn("Buchungsklasse", "buchungsklasse");
    buchungsartList.addColumn("Spende", "spende", new JaNeinFormatter());
    buchungsartList.setContextMenu(new BuchungsartMenu());
    buchungsartList.setRememberColWidths(true);
    buchungsartList.setRememberOrder(true);
    buchungsartList.setSummary(true);
    return buchungsartList;
  }

  public Button getPDFAusgabeButton()
  {
    Button b = new Button("PDF-Ausgabe", new Action()
    {

      public void handleAction(Object context) throws ApplicationException
      {
        try
        {
          starteAuswertung();
        }
        catch (RemoteException e)
        {
          Logger.error(e.getMessage());
          throw new ApplicationException(
              "Fehler beim Start der PDF-Ausgabe der Buchungsarten");
        }
      }
    }, null, true, "acroread.png");
    return b;
  }

  private void starteAuswertung() throws RemoteException
  {
    FileDialog fd = new FileDialog(GUI.getShell(), SWT.SAVE);
    fd.setText("Ausgabedatei w�hlen.");
    String path = settings
        .getString("lastdir", System.getProperty("user.home"));
    if (path != null && path.length() > 0)
    {
      fd.setFilterPath(path);
    }
    fd.setFileName(new Dateiname("buchungsarten", "", Einstellungen
        .getEinstellung().getDateinamenmuster(), "PDF").get());
    fd.setFilterExtensions(new String[] { "*.PDF" });

    String s = fd.open();
    if (s == null || s.length() == 0)
    {
      return;
    }
    if (!s.endsWith(".PDF"))
    {
      s = s + ".PDF";
    }
    final File file = new File(s);
    final DBIterator it = Einstellungen.getDBService().createList(
        Buchungsart.class);
    it.setOrder("ORDER BY nummer");
    settings.setAttribute("lastdir", file.getParent());
    BackgroundTask t = new BackgroundTask()
    {

      public void run(ProgressMonitor monitor) throws ApplicationException
      {
        try
        {
          FileOutputStream fos = new FileOutputStream(file);
          Reporter reporter = new Reporter(fos, monitor, "Buchungsarten", "",
              it.size());
          reporter.addHeaderColumn("Nummer", Element.ALIGN_LEFT, 15,
              Color.LIGHT_GRAY);
          reporter.addHeaderColumn("Bezeichnung", Element.ALIGN_LEFT, 80,
              Color.LIGHT_GRAY);
          reporter.addHeaderColumn("Art", Element.ALIGN_LEFT, 25,
              Color.LIGHT_GRAY);
          reporter.addHeaderColumn("Buchungsklasse", Element.ALIGN_LEFT, 80,
              Color.LIGHT_GRAY);
          reporter.addHeaderColumn("Spende", Element.ALIGN_CENTER, 10,
              Color.LIGHT_GRAY);
          reporter.createHeader();
          while (it.hasNext())
          {
            Buchungsart b = (Buchungsart) it.next();
            reporter.addColumn(b.getNummer() + "", Element.ALIGN_RIGHT);
            reporter.addColumn(b.getBezeichnung(), Element.ALIGN_LEFT);
            String arttxt = "";
            switch (b.getArt())
            {
              case 0:
                arttxt = "Einnahme";
                break;
              case 1:
                arttxt = "Ausgabe";
                break;
              case 2:
                arttxt = "Umbuchung";
                break;
            }
            reporter.addColumn(arttxt, Element.ALIGN_LEFT);
            if (b.getBuchungsklasse() != null)
            {
              reporter.addColumn(b.getBuchungsklasse().getBezeichnung(),
                  Element.ALIGN_LEFT);
            }
            else
            {
              reporter.addColumn("", Element.ALIGN_LEFT);
            }
            reporter.addColumn(b.getSpende());
            reporter.setNextRecord();
          }
          reporter.closeTable();
          reporter.close();
          fos.close();
          monitor.setPercentComplete(100);
          monitor.setStatus(ProgressMonitor.STATUS_DONE);
          GUI.getStatusBar().setSuccessText("Auswertung gestartet");
          GUI.getCurrentView().reload();
        }
        catch (Exception e)
        {
          Logger.error("Fehler", e);
          monitor.setStatusText(e.getMessage());
          monitor.setStatus(ProgressMonitor.STATUS_ERROR);
          GUI.getStatusBar().setErrorText(e.getMessage());
          throw new ApplicationException(e);
        }
        GUI.getDisplay().asyncExec(new Runnable()
        {

          public void run()
          {
            try
            {
              new Program().handleAction(file);
            }
            catch (ApplicationException ae)
            {
              Application.getMessagingFactory().sendMessage(
                  new StatusBarMessage(ae.getLocalizedMessage(),
                      StatusBarMessage.TYPE_ERROR));
            }
          }
        });

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

}
