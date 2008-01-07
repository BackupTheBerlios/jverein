/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/control/AbbuchungControl.java,v $
 * $Revision: 1.11 $
 * $Date: 2008/01/07 20:28:55 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: AbbuchungControl.java,v $
 * Revision 1.11  2008/01/07 20:28:55  jost
 * Dateinamensvorgabe für die PDF-Datei
 *
 * Revision 1.10  2008/01/01 13:12:53  jost
 * Neu: Dateinamenmuster
 *
 * Revision 1.9  2007/12/26 18:12:32  jost
 * Lastschriften können jetzt als Einzellastschriften oder Sammellastschriften direkt in Hibuscus verbucht werden.
 *
 * Revision 1.8  2007/12/21 13:35:44  jost
 * Ausgabe der DTAUS-Datei im PDF-Format
 *
 * Revision 1.7  2007/12/02 14:14:33  jost
 * Überflüssige Plausi entfernt.
 *
 * Revision 1.6  2007/12/02 13:39:10  jost
 * Neu: Beitragsmodelle
 *
 * Revision 1.5  2007/08/14 19:19:28  jost
 * Refactoring
 *
 * Revision 1.4  2007/03/27 19:20:16  jost
 * Zus�tzliche Plausi
 *
 * Revision 1.3  2007/02/25 19:12:11  jost
 * Neu: Kursteilnehmer
 *
 * Revision 1.2  2007/02/23 20:26:22  jost
 * Mail- und Webadresse im Header korrigiert.
 *
 * Revision 1.1  2006/09/20 15:38:30  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.control;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.gui.input.AbbuchungsausgabeInput;
import de.jost_net.JVerein.gui.input.AbbuchungsmodusInput;
import de.jost_net.JVerein.io.Abbuchung;
import de.jost_net.JVerein.io.AbbuchungParam;
import de.jost_net.JVerein.util.Dateiname;
import de.willuhn.jameica.gui.AbstractControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.input.CheckboxInput;
import de.willuhn.jameica.gui.input.DateInput;
import de.willuhn.jameica.gui.input.TextInput;
import de.willuhn.jameica.gui.parts.Button;
import de.willuhn.jameica.system.Application;
import de.willuhn.jameica.system.BackgroundTask;
import de.willuhn.jameica.system.Settings;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;
import de.willuhn.util.ProgressMonitor;

public class AbbuchungControl extends AbstractControl
{
  private AbbuchungsmodusInput modus;

  private DateInput vondatum = null;

  private TextInput zahlungsgrund;

  private CheckboxInput zusatzabbuchung;

  private CheckboxInput kursteilnehmer;

  private CheckboxInput dtausprint;

  private AbbuchungsausgabeInput ausgabe;

  private Settings settings = null;

  public AbbuchungControl(AbstractView view)
  {
    super(view);
    settings = new Settings(this.getClass());
    settings.setStoreWhenRead(true);
  }

  public AbbuchungsmodusInput getAbbuchungsmodus() throws RemoteException
  {
    if (modus != null)
    {
      return modus;
    }
    modus = new AbbuchungsmodusInput(AbbuchungsmodusInput.KEINBEITRAG);
    modus.addListener(new Listener()
    {
      public void handleEvent(Event event)
      {
        Integer m = ((Integer) modus.getValue());
        if (m.intValue() != AbbuchungsmodusInput.EINGETRETENEMITGLIEDER)
        {
          vondatum.setText("");
          vondatum.setEnabled(false);
        }
        else
        {
          vondatum.setEnabled(true);
        }
      }
    });
    return modus;
  }

  public DateInput getVondatum() throws RemoteException
  {
    if (vondatum != null)
    {
      return vondatum;
    }
    Date d = null;
    this.vondatum = new DateInput(d, Einstellungen.DATEFORMAT);
    this.vondatum.setTitle("Anfangsdatum Abbuchung");
    this.vondatum.setText("Bitte Anfangsdatum der Verarbeitung w�hlen");
    this.vondatum.setEnabled(false);
    this.vondatum.addListener(new Listener()
    {
      public void handleEvent(Event event)
      {
        Date date = (Date) vondatum.getValue();
        if (date == null)
        {
          return;
        }
      }
    });
    return vondatum;
  }

  public TextInput getZahlungsgrund() throws RemoteException
  {
    if (zahlungsgrund != null)
    {
      return zahlungsgrund;
    }
    String zgrund = settings.getString("zahlungsgrund", "bitte eingeben");

    zahlungsgrund = new TextInput(zgrund, 27);
    return zahlungsgrund;
  }

  public CheckboxInput getZusatzabbuchung() throws RemoteException
  {
    if (zusatzabbuchung != null)
    {
      return zusatzabbuchung;
    }
    zusatzabbuchung = new CheckboxInput(false);
    return zusatzabbuchung;
  }

  public CheckboxInput getKursteilnehmer() throws RemoteException
  {
    if (kursteilnehmer != null)
    {
      return kursteilnehmer;
    }
    kursteilnehmer = new CheckboxInput(false);
    return kursteilnehmer;
  }

  public CheckboxInput getDtausPrint() throws RemoteException
  {
    if (dtausprint != null)
    {
      return dtausprint;
    }
    dtausprint = new CheckboxInput(false);
    return dtausprint;
  }

  public AbbuchungsausgabeInput getAbbuchungsausgabe() throws RemoteException
  {
    if (ausgabe != null)
    {
      return ausgabe;
    }
    ausgabe = new AbbuchungsausgabeInput(AbbuchungsausgabeInput.DTAUS);
    return ausgabe;
  }

  public Button getStartButton()
  {
    Button button = new Button("starten", new Action()
    {
      public void handleAction(Object context) throws ApplicationException
      {
        doAbbuchung();
      }
    }, null, true);
    return button;
  }

  private void doAbbuchung() throws ApplicationException
  {
    File dtausfile;
    settings.setAttribute("zahlungsgrund", (String) zahlungsgrund.getValue());

    Integer ausgabe;
    try
    {
      ausgabe = (Integer) this.getAbbuchungsausgabe().getValue();
    }
    catch (RemoteException e2)
    {
      throw new ApplicationException("Interner Fehler");
    }

    if (ausgabe == AbbuchungsausgabeInput.DTAUS)
    {
      FileDialog fd = new FileDialog(GUI.getShell(), SWT.SAVE);
      fd.setText("DTAUS-Ausgabedatei w�hlen.");

      String path = settings.getString("lastdir", System
          .getProperty("user.home"));
      if (path != null && path.length() > 0)
      {
        fd.setFilterPath(path);
      }
      fd.setFileName(new Dateiname("abbuchung", Einstellungen
          .getDateinamenmuster(), "TXT").get());
      String file = fd.open();

      if (file == null || file.length() == 0)
      {
        throw new ApplicationException("keine Datei ausgew�hlt!");
      }
      dtausfile = new File(file);
    }
    else
    {
      try
      {
        dtausfile = File.createTempFile("dtaus", null);
      }
      catch (IOException e)
      {
        throw new ApplicationException(
            "Tempor�re Datei f�r die Abbuchung kann nicht erstellt werden.");
      }
    }

    // PDF-Datei f�r Dtaus2PDF
    String pdffile = null;
    final Boolean pdfprintb = (Boolean) dtausprint.getValue();
    if (pdfprintb)
    {
      FileDialog fd = new FileDialog(GUI.getShell(), SWT.SAVE);
      fd.setText("PDF-Ausgabedatei w�hlen");

      String path = settings.getString("lastdir", System
          .getProperty("user.home"));
      if (path != null && path.length() > 0)
      {
        fd.setFilterPath(path);
      }
      fd.setFileName(new Dateiname("abbuchung", Einstellungen
          .getDateinamenmuster(), "PDF").get());
      pdffile = fd.open();
    }

    // Wir merken uns noch das Verzeichnis f�rs n�chste mal
    settings.setAttribute("lastdir", dtausfile.getParent());
    final AbbuchungParam abupar;
    try
    {
      abupar = new AbbuchungParam(this, dtausfile, pdffile);
    }
    catch (RemoteException e)
    {
      throw new ApplicationException(e);
    }
    BackgroundTask t = new BackgroundTask()
    {
      public void run(ProgressMonitor monitor) throws ApplicationException
      {
        try
        {
          new Abbuchung(abupar, monitor);
          monitor.setPercentComplete(100);
          monitor.setStatus(ProgressMonitor.STATUS_DONE);
          GUI.getStatusBar().setSuccessText(
              "Abbuchungsdatei geschrieben: "
                  + abupar.dtausfile.getAbsolutePath());
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
          Logger.error("error while reading objects from "
              + abupar.dtausfile.getAbsolutePath(), e);
          ApplicationException ae = new ApplicationException(
              "Fehler beim erstellen der Abbuchungsdatei: "
                  + abupar.dtausfile.getAbsolutePath(), e);
          monitor.setStatusText(ae.getMessage());
          GUI.getStatusBar().setErrorText(ae.getMessage());
          throw ae;
        }
      }

      public void interrupt()
      {
      }

      public boolean isInterrupted()
      {
        return false;
      }
    };
    Application.getController().start(t);
  }
}
