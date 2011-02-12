/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/PersonalbogenAction.java,v $
 * $Revision: 1.12 $
 * $Date: 2011/02/12 09:27:06 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: PersonalbogenAction.java,v $
 * Revision 1.12  2011/02/12 09:27:06  jost
 * Statische Codeanalyse mit Findbugs
 *
 * Revision 1.11  2011-01-30 10:42:19  jost
 * Bugfix f. Adressen
 *
 * Revision 1.10  2010-11-27 17:56:10  jost
 * Warnung entfernt.
 *
 * Revision 1.9  2010-11-27 00:59:20  jost
 * Bugfix Eigenschaften
 *
 * Revision 1.8  2010-10-30 11:28:54  jost
 * Neu: Sterbetag
 *
 * Revision 1.7  2010-10-15 09:58:01  jost
 * Code aufger�umt
 *
 * Revision 1.6  2010-09-16 18:12:02  jost
 * Tippfehler beseitigt
 * Zeilenumbr�che in den Kommunikationsdaten.
 *
 * Revision 1.5  2010-09-15 20:44:10  jost
 * Vermeidung NPE
 * Kommunikationsdaten "aufgeh�bscht"
 *
 * Revision 1.4  2010-09-14 15:40:44  jost
 * Fehler loggen
 *
 * Revision 1.3  2010-09-07 16:58:55  jost
 * Div. �nderungen
 *
 * Revision 1.2  2010-09-01 13:49:12  jost
 * Header und NPE-Vermeidung
 *
 * Revision 1.1  2010-09-01 05:56:15  jost
 * neu: Personalbogen
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.io.Reporter;
import de.jost_net.JVerein.keys.Zahlungsweg;
import de.jost_net.JVerein.rmi.Buchung;
import de.jost_net.JVerein.rmi.Eigenschaften;
import de.jost_net.JVerein.rmi.Felddefinition;
import de.jost_net.JVerein.rmi.Lehrgang;
import de.jost_net.JVerein.rmi.Mitglied;
import de.jost_net.JVerein.rmi.Mitgliedfoto;
import de.jost_net.JVerein.rmi.Mitgliedskonto;
import de.jost_net.JVerein.rmi.Wiedervorlage;
import de.jost_net.JVerein.rmi.Zusatzbetrag;
import de.jost_net.JVerein.rmi.Zusatzfelder;
import de.jost_net.JVerein.util.Dateiname;
import de.jost_net.JVerein.util.JVDateFormatTTMMJJJJ;
import de.willuhn.datasource.rmi.DBIterator;
import de.willuhn.datasource.rmi.ResultSetExtractor;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.internal.action.Program;
import de.willuhn.jameica.messaging.StatusBarMessage;
import de.willuhn.jameica.system.Application;
import de.willuhn.jameica.system.BackgroundTask;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;
import de.willuhn.util.ProgressMonitor;

public class PersonalbogenAction implements Action
{

  private de.willuhn.jameica.system.Settings settings;

  public void handleAction(Object context) throws ApplicationException
  {
    Mitglied[] m = null;
    if (context != null
        && (context instanceof Mitglied || context instanceof Mitglied[]))
    {
      if (context instanceof Mitglied)
      {
        m = new Mitglied[] { (Mitglied) context };
      }
      else if (context instanceof Mitglied[])
      {
        m = (Mitglied[]) context;
      }
      try
      {
        generierePersonalbogen(m);
      }
      catch (IOException e)
      {
        Logger.error("Fehler", e);
        throw new ApplicationException("Fehler bei der Aufbereitung", e);
      }
    }
    else
    {
      throw new ApplicationException(JVereinPlugin.getI18n().tr(
          "Kein Mitglied ausgew�hlt"));
    }
  }

  private void generierePersonalbogen(Mitglied[] m) throws IOException
  {
    final Mitglied[] mitglied = m;
    FileDialog fd = new FileDialog(GUI.getShell(), SWT.SAVE);
    fd.setText("Ausgabedatei w�hlen.");

    settings = new de.willuhn.jameica.system.Settings(this.getClass());
    String path = settings
        .getString("lastdir", System.getProperty("user.home"));
    if (path != null && path.length() > 0)
    {
      fd.setFilterPath(path);
    }
    fd.setFileName(new Dateiname("personalbogen", "", Einstellungen
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
    settings.setAttribute("lastdir", file.getParent());
    BackgroundTask t = new BackgroundTask()
    {
      public void run(ProgressMonitor monitor) throws ApplicationException
      {
        try
        {
          Reporter rpt = new Reporter(new FileOutputStream(file), monitor, "",
              "Personalbogen", mitglied.length);

          monitor.setPercentComplete(100);
          monitor.setStatus(ProgressMonitor.STATUS_DONE);
          GUI.getStatusBar().setSuccessText("Auswertung gestartet");
          GUI.getCurrentView().reload();

          boolean first = true;

          for (Mitglied m : mitglied)
          {
            if (!first)
            {
              rpt.newPage();
            }
            first = false;

            rpt.add("Personalbogen " + m.getVornameName(), 14);

            generiereMitglied(rpt, m);

            if (Einstellungen.getEinstellung().getZusatzbetrag())
            {
              generiereZusatzbetrag(rpt, m);
            }
            if (Einstellungen.getEinstellung().getMitgliedskonto())
            {
              generiereMitgliedskonto(rpt, m);
            }
            if (Einstellungen.getEinstellung().getVermerke()
                && ((m.getVermerk1() != null && m.getVermerk1().length() > 0) || (m
                    .getVermerk2() != null && m.getVermerk2().length() > 0)))
            {
              generiereVermerke(rpt, m);
            }
            if (Einstellungen.getEinstellung().getWiedervorlage())
            {
              generiereWiedervorlagen(rpt, m);
            }
            if (Einstellungen.getEinstellung().getLehrgaenge())
            {
              generiereLehrgaenge(rpt, m);
            }
            generiereZusatzfelder(rpt, m);
            generiereEigenschaften(rpt, m);
            rpt.setNextRecord();
          }
          rpt.close();
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
        catch (ApplicationException ae)
        {
          Logger.error("Fehler", ae);
          monitor.setStatusText(ae.getMessage());
          monitor.setStatus(ProgressMonitor.STATUS_ERROR);
          GUI.getStatusBar().setErrorText(ae.getMessage());
          throw ae;
        }
        catch (Exception re)
        {
          Logger.error("Fehler", re);
          monitor.setStatusText(re.getMessage());
          monitor.setStatus(ProgressMonitor.STATUS_ERROR);
          GUI.getStatusBar().setErrorText(re.getMessage());
          throw new ApplicationException(re);
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

  private void generiereMitglied(Reporter rpt, Mitglied m)
      throws DocumentException, MalformedURLException, IOException
  {
    rpt.addHeaderColumn("Feld", Element.ALIGN_LEFT, 50, Color.LIGHT_GRAY);
    rpt.addHeaderColumn("Inhalt", Element.ALIGN_LEFT, 140, Color.LIGHT_GRAY);
    rpt.createHeader();
    DBIterator it = Einstellungen.getDBService().createList(Mitgliedfoto.class);
    it.addFilter("mitglied = ?", new Object[] { m.getID() });
    if (it.size() > 0)
    {
      Mitgliedfoto foto = (Mitgliedfoto) it.next();
      if (foto.getFoto() != null)
      {
        rpt.addColumn("Foto", Element.ALIGN_LEFT);
        rpt.addColumn(foto.getFoto(), 100, 100, Element.ALIGN_RIGHT);
      }
    }
    if (Einstellungen.getEinstellung().getExterneMitgliedsnummer())
    {
      rpt.addColumn("Ext. Mitgliedsnummer", Element.ALIGN_LEFT);
      rpt.addColumn(
          m.getExterneMitgliedsnummer() != null ? m.getExterneMitgliedsnummer()
              + "" : "", Element.ALIGN_LEFT);
    }
    rpt.addColumn("Name, Vorname", Element.ALIGN_LEFT);
    rpt.addColumn(m.getNameVorname(), Element.ALIGN_LEFT);
    rpt.addColumn("Anschrift", Element.ALIGN_LEFT);
    rpt.addColumn(m.getAnschrift(), Element.ALIGN_LEFT);
    rpt.addColumn("Geburtsdatum", Element.ALIGN_LEFT);
    rpt.addColumn(m.getGeburtsdatum(), Element.ALIGN_LEFT);
    if (m.getSterbetag() != null)
    {
      rpt.addColumn("Sterbetag", Element.ALIGN_LEFT);
      rpt.addColumn(m.getSterbetag(), Element.ALIGN_LEFT);
    }
    rpt.addColumn("Geschlecht", Element.ALIGN_LEFT);
    rpt.addColumn(m.getGeschlecht(), Element.ALIGN_LEFT);
    rpt.addColumn("Kommunikation", Element.ALIGN_LEFT);
    String kommunikation = "";
    if (m.getTelefonprivat().length() != 0)
    {
      kommunikation += "privat: " + m.getTelefonprivat();
    }
    if (m.getTelefondienstlich().length() != 0)
    {
      if (kommunikation.length() > 0)
      {
        kommunikation += "\n";
      }
      kommunikation += "dienstlich: " + m.getTelefondienstlich();
    }
    if (m.getHandy().length() != 0)
    {
      if (kommunikation.length() > 0)
      {
        kommunikation += "\n";
      }
      kommunikation += "Handy: " + m.getHandy();
    }
    if (m.getEmail().length() != 0)
    {
      if (kommunikation.length() > 0)
      {
        kommunikation += "\n";
      }
      kommunikation += "Email: " + m.getEmail();
    }
    rpt.addColumn(kommunikation, Element.ALIGN_LEFT);
    if (m.getAdresstyp().getID().equals("1"))
    {
      rpt.addColumn("Eintritt", Element.ALIGN_LEFT);
      rpt.addColumn(m.getEintritt(), Element.ALIGN_LEFT);
      rpt.addColumn("Beitragsgruppe", Element.ALIGN_LEFT);
      rpt.addColumn(
          m.getBeitragsgruppe().getBezeichnung()
              + " - "
              + Einstellungen.DECIMALFORMAT.format(m.getBeitragsgruppe()
                  .getBetrag()) + " EUR", Element.ALIGN_LEFT);
      rpt.addColumn("Austritts-/K�ndigungsdatum", Element.ALIGN_LEFT);
      String akdatum = "";
      if (m.getAustritt() != null)
      {
        akdatum += new JVDateFormatTTMMJJJJ().format(m.getAustritt());
      }
      if (m.getKuendigung() != null)
      {
        if (akdatum.length() != 0)
        {
          akdatum += " / ";
        }
        akdatum += new JVDateFormatTTMMJJJJ().format(m.getKuendigung());
      }
      rpt.addColumn(akdatum, Element.ALIGN_LEFT);
    }
    rpt.addColumn("Zahlungsweg", Element.ALIGN_LEFT);
    rpt.addColumn(Zahlungsweg.get(m.getZahlungsweg()), Element.ALIGN_LEFT);
    if (m.getBlz().length() > 0 && m.getKonto().length() > 0)
    {
      rpt.addColumn("Bankverbindung", Element.ALIGN_LEFT);
      rpt.addColumn(
          m.getBlz() + "/" + m.getKonto() + " ("
              + Einstellungen.getNameForBLZ(m.getBlz()) + ")",
          Element.ALIGN_LEFT);
    }
    rpt.addColumn("Datum Erstspeicherung", Element.ALIGN_LEFT);
    rpt.addColumn(m.getEingabedatum(), Element.ALIGN_LEFT);
    rpt.addColumn("Datum letzte �nderung", Element.ALIGN_LEFT);
    rpt.addColumn(m.getLetzteAenderung(), Element.ALIGN_LEFT);

    rpt.closeTable();
  }

  private void generiereZusatzbetrag(Reporter rpt, Mitglied m)
      throws RemoteException, DocumentException
  {
    DBIterator it = Einstellungen.getDBService().createList(Zusatzbetrag.class);
    it.addFilter("mitglied = ?", new Object[] { m.getID() });
    if (it.size() > 0)
    {
      rpt.add(new Paragraph("Zusatzbetrag"));
      rpt.addHeaderColumn("Start", Element.ALIGN_LEFT, 30, Color.LIGHT_GRAY);
      rpt.addHeaderColumn("n�chste F�ll.", Element.ALIGN_LEFT, 30,
          Color.LIGHT_GRAY);
      rpt.addHeaderColumn("letzte Ausf.", Element.ALIGN_LEFT, 30,
          Color.LIGHT_GRAY);
      rpt.addHeaderColumn("Intervall", Element.ALIGN_LEFT, 30, Color.LIGHT_GRAY);
      rpt.addHeaderColumn("Ende", Element.ALIGN_LEFT, 30, Color.LIGHT_GRAY);
      rpt.addHeaderColumn("Buchungstext", Element.ALIGN_LEFT, 60,
          Color.LIGHT_GRAY);
      rpt.addHeaderColumn("Betrag", Element.ALIGN_RIGHT, 30, Color.LIGHT_GRAY);
      rpt.createHeader();
      while (it.hasNext())
      {
        Zusatzbetrag z = (Zusatzbetrag) it.next();
        rpt.addColumn(z.getStartdatum(), Element.ALIGN_LEFT);
        rpt.addColumn(z.getFaelligkeit(), Element.ALIGN_LEFT);
        rpt.addColumn(z.getAusfuehrung(), Element.ALIGN_LEFT);
        rpt.addColumn(z.getIntervallText(), Element.ALIGN_LEFT);
        rpt.addColumn(z.getEndedatum(), Element.ALIGN_LEFT);
        rpt.addColumn(z.getBuchungstext(), Element.ALIGN_LEFT);
        rpt.addColumn(z.getBetrag());
      }
    }
    rpt.closeTable();

  }

  private void generiereMitgliedskonto(Reporter rpt, Mitglied m)
      throws RemoteException, DocumentException
  {
    DBIterator it = Einstellungen.getDBService().createList(
        Mitgliedskonto.class);
    it.addFilter("mitglied = ?", new Object[] { m.getID() });
    it.setOrder("order by datum desc");
    if (it.size() > 0)
    {
      rpt.add(new Paragraph("Mitgliedskonto"));
      rpt.addHeaderColumn("Text", Element.ALIGN_LEFT, 12, Color.LIGHT_GRAY);
      rpt.addHeaderColumn("Datum", Element.ALIGN_LEFT, 30, Color.LIGHT_GRAY);
      rpt.addHeaderColumn("Zweck 1", Element.ALIGN_LEFT, 50, Color.LIGHT_GRAY);
      rpt.addHeaderColumn("Zweck 2", Element.ALIGN_LEFT, 50, Color.LIGHT_GRAY);
      rpt.addHeaderColumn("Zahlungsweg", Element.ALIGN_LEFT, 30,
          Color.LIGHT_GRAY);
      rpt.addHeaderColumn("Betrag", Element.ALIGN_LEFT, 30, Color.LIGHT_GRAY);
      rpt.createHeader();
      while (it.hasNext())
      {
        Mitgliedskonto mk = (Mitgliedskonto) it.next();
        rpt.addColumn("Soll", Element.ALIGN_LEFT);
        rpt.addColumn(mk.getDatum(), Element.ALIGN_LEFT);
        rpt.addColumn(mk.getZweck1(), Element.ALIGN_LEFT);
        rpt.addColumn(mk.getZweck2(), Element.ALIGN_LEFT);
        rpt.addColumn(Zahlungsweg.get(mk.getZahlungsweg()), Element.ALIGN_LEFT);
        rpt.addColumn(mk.getBetrag());
        DBIterator it2 = Einstellungen.getDBService().createList(Buchung.class);
        it2.addFilter("mitgliedskonto = ?", new Object[] { mk.getID() });
        it2.setOrder("order by datum desc");
        while (it2.hasNext())
        {
          Buchung bu = (Buchung) it2.next();
          rpt.addColumn("Ist", Element.ALIGN_RIGHT);
          rpt.addColumn(bu.getDatum(), Element.ALIGN_LEFT);
          rpt.addColumn(bu.getZweck(), Element.ALIGN_LEFT);
          rpt.addColumn(bu.getZweck2(), Element.ALIGN_LEFT);
          rpt.addColumn("", Element.ALIGN_LEFT);
          rpt.addColumn(bu.getBetrag());
        }
      }
    }
    rpt.closeTable();

  }

  private void generiereVermerke(Reporter rpt, Mitglied m)
      throws DocumentException, RemoteException
  {
    rpt.add(new Paragraph("Vermerke"));
    rpt.addHeaderColumn("Text", Element.ALIGN_LEFT, 100, Color.LIGHT_GRAY);
    rpt.createHeader();
    if (m.getVermerk1() != null && m.getVermerk1().length() > 0)
    {
      rpt.addColumn(m.getVermerk1(), Element.ALIGN_LEFT);
    }
    if (m.getVermerk2() != null && m.getVermerk2().length() > 0)
    {
      rpt.addColumn(m.getVermerk2(), Element.ALIGN_LEFT);
    }
    rpt.closeTable();

  }

  private void generiereWiedervorlagen(Reporter rpt, Mitglied m)
      throws RemoteException, DocumentException
  {
    DBIterator it = Einstellungen.getDBService()
        .createList(Wiedervorlage.class);
    it.addFilter("mitglied = ?", new Object[] { m.getID() });
    it.setOrder("order by datum desc");
    if (it.size() > 0)
    {
      rpt.add(new Paragraph("Wiedervorlage"));
      rpt.addHeaderColumn("Datum", Element.ALIGN_LEFT, 50, Color.LIGHT_GRAY);
      rpt.addHeaderColumn("Vermerk", Element.ALIGN_LEFT, 100, Color.LIGHT_GRAY);
      rpt.addHeaderColumn("Erledigung", Element.ALIGN_LEFT, 50,
          Color.LIGHT_GRAY);
      rpt.createHeader();
      while (it.hasNext())
      {
        Wiedervorlage w = (Wiedervorlage) it.next();
        rpt.addColumn(w.getDatum(), Element.ALIGN_LEFT);
        rpt.addColumn(w.getVermerk(), Element.ALIGN_LEFT);
        rpt.addColumn(w.getErledigung(), Element.ALIGN_LEFT);
      }
    }
    rpt.closeTable();

  }

  private void generiereLehrgaenge(Reporter rpt, Mitglied m)
      throws RemoteException, DocumentException
  {
    DBIterator it = Einstellungen.getDBService().createList(Lehrgang.class);
    it.addFilter("mitglied = ?", new Object[] { m.getID() });
    it.setOrder("order by von");
    if (it.size() > 0)
    {
      rpt.add(new Paragraph("Lehrg�nge"));
      rpt.addHeaderColumn("Lehrgangsart", Element.ALIGN_LEFT, 50,
          Color.LIGHT_GRAY);
      rpt.addHeaderColumn("am/vom", Element.ALIGN_LEFT, 30, Color.LIGHT_GRAY);
      rpt.addHeaderColumn("bis", Element.ALIGN_LEFT, 30, Color.LIGHT_GRAY);
      rpt.addHeaderColumn("Veranstalter", Element.ALIGN_LEFT, 60,
          Color.LIGHT_GRAY);
      rpt.addHeaderColumn("Ergebnis", Element.ALIGN_LEFT, 60, Color.LIGHT_GRAY);
      rpt.createHeader();
      while (it.hasNext())
      {
        Lehrgang l = (Lehrgang) it.next();
        rpt.addColumn(l.getLehrgangsart().getBezeichnung(), Element.ALIGN_LEFT);
        rpt.addColumn(l.getVon(), Element.ALIGN_LEFT);
        rpt.addColumn(l.getBis(), Element.ALIGN_LEFT);
        rpt.addColumn(l.getVeranstalter(), Element.ALIGN_LEFT);
        rpt.addColumn(l.getErgebnis(), Element.ALIGN_LEFT);
      }
    }
    rpt.closeTable();

  }

  private void generiereZusatzfelder(Reporter rpt, Mitglied m)
      throws RemoteException, DocumentException
  {
    DBIterator it = Einstellungen.getDBService().createList(
        Felddefinition.class);
    it.setOrder("order by label");
    if (it.size() > 0)
    {
      rpt.add(new Paragraph("Zusatzfelder"));
      rpt.addHeaderColumn("Feld", Element.ALIGN_LEFT, 50, Color.LIGHT_GRAY);
      rpt.addHeaderColumn("Inhalt", Element.ALIGN_LEFT, 130, Color.LIGHT_GRAY);
      rpt.createHeader();
      while (it.hasNext())
      {
        Felddefinition fd = (Felddefinition) it.next();
        rpt.addColumn(fd.getLabel(), Element.ALIGN_LEFT);
        DBIterator it2 = Einstellungen.getDBService().createList(
            Zusatzfelder.class);
        it2.addFilter("mitglied = ? and felddefinition = ?",
            new Object[] { m.getID(), fd.getID() });
        if (it2.size() > 0)
        {
          Zusatzfelder zf = (Zusatzfelder) it2.next();
          rpt.addColumn(zf.getString(), Element.ALIGN_LEFT);
        }
        else
        {
          rpt.addColumn("", Element.ALIGN_LEFT);
        }
      }
      rpt.closeTable();
    }
  }

  private void generiereEigenschaften(Reporter rpt, Mitglied m)
      throws RemoteException, DocumentException
  {
    ResultSetExtractor rs = new ResultSetExtractor()
    {

      public Object extract(ResultSet rs) throws SQLException
      {
        List<String> ids = new ArrayList<String>();
        while (rs.next())
        {
          ids.add(rs.getString(1));
        }
        return ids;
      }
    };
    String sql = "select eigenschaften.id from eigenschaften, eigenschaft "
        + "where eigenschaften.eigenschaft = eigenschaft.id and mitglied = ? "
        + "order by eigenschaft.bezeichnung";
    ArrayList<String> idliste = (ArrayList<String>) Einstellungen
        .getDBService().execute(sql, new Object[] { m.getID() }, rs);
    if (idliste.size() > 0)
    {
      rpt.add(new Paragraph("Eigenschaften"));
      rpt.addHeaderColumn("Eigenschaftengruppe", Element.ALIGN_LEFT, 100,
          Color.LIGHT_GRAY);
      rpt.addHeaderColumn("Eigenschaft", Element.ALIGN_LEFT, 100,
          Color.LIGHT_GRAY);
      rpt.createHeader();
      for (String id : idliste)
      {
        DBIterator it = Einstellungen.getDBService().createList(
            Eigenschaften.class);
        it.addFilter("id = ?", new Object[] { id });
        while (it.hasNext())
        {
          Eigenschaften ei = (Eigenschaften) it.next();
          rpt.addColumn(ei.getEigenschaft().getEigenschaftGruppe()
              .getBezeichnung(), Element.ALIGN_LEFT);
          rpt.addColumn(ei.getEigenschaft().getBezeichnung(),
              Element.ALIGN_LEFT);
        }
      }
      rpt.closeTable();
    }
  }
}
