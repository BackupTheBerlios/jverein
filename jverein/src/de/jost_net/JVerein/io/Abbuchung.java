/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/io/Attic/Abbuchung.java,v $
 * $Revision: 1.43 $
 * $Date: 2010/05/18 20:21:08 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: Abbuchung.java,v $
 * Revision 1.43  2010/05/18 20:21:08  jost
 * Anpassung Klassenname
 *
 * Revision 1.42  2010/04/25 13:55:54  jost
 * Vorarbeiten Mitgliedskonto
 *
 * Revision 1.41  2010/04/18 06:54:23  jost
 * Zus�tzliche Pr�fung der Bankverbindung.
 *
 * Revision 1.40  2010/02/28 20:03:47  jost
 * Mitgliedsnummer mit ausgeben.
 *
 * Revision 1.39  2010/02/28 15:17:28  jost
 * Mitgliedsnummer in den Verwendungszweck �bernommen.
 *
 * Revision 1.38  2010/02/15 17:23:18  jost
 * Bugfix zu lange Namen mit Umlauten.
 *
 * Revision 1.37  2009/12/17 19:25:25  jost
 * �berfl�ssigen Code entfernt.
 *
 * Revision 1.36  2009/12/13 17:43:35  jost
 * Debugging-Infos entfernt.
 *
 * Revision 1.35  2009/12/06 21:41:23  jost
 * Bugfix ung�ltige Kontonummer
 *
 * Revision 1.34  2009/08/19 21:00:30  jost
 * Manuelle Buchungen auch f�r Zusatzbetr�ge.
 *
 * Revision 1.33  2009/07/30 18:23:18  jost
 * Bugfix DTAUS-Datei mit �berlangen Namen
 *
 * Revision 1.32  2009/07/19 13:49:03  jost
 * Bugfix Abrechnung
 *
 * Revision 1.31  2009/06/29 19:44:03  jost
 * Bugfix Zusatzbetr�ge jetzt auch ohne Bankverbindung.
 *
 * Revision 1.30  2009/06/11 21:03:52  jost
 * Vorbereitung I18N
 *
 * Revision 1.29  2009/01/27 18:51:37  jost
 * Abbuchung auch f�r Mitglieder ohne Eintrittsdatum
 *
 * Revision 1.28  2009/01/03 07:45:58  jost
 * Keine Abbuchungen f�r ausgetretene Mitglieder
 *
 * Revision 1.27  2009/01/02 14:21:57  jost
 * Rechnungen f�r Zusatzbetr�ge implementiert.
 *
 * Revision 1.26  2008/12/22 21:18:57  jost
 * Zusatzabbuchung->Zusatzbetrag
 *
 * Revision 1.25  2008/12/19 06:54:27  jost
 * Keine Abrechnung bei Eintrittsdatum in der Zukunft
 *
 * Revision 1.24  2008/11/29 13:12:04  jost
 * Refactoring: Code-Optimierung
 *
 * Revision 1.23  2008/11/16 16:58:18  jost
 * Speicherung der Einstellung von Property-Datei in die Datenbank verschoben.
 *
 * Revision 1.22  2008/08/10 12:37:25  jost
 * Abbuchung -> Abrechnung
 * Vorbereitung der Rechnungserstellung
 *
 * Revision 1.21  2008/07/09 13:01:16  jost
 * OBanToo-Fehlermeldung an die Oberfläche bringen
 *
 * Revision 1.20  2008/02/09 14:35:32  jost
 * Bugfix. Zusatzabbuchungen und Kursteilnehmer nur abbuchen, wenn das Häkchen gesetzt ist.
 *
 * Revision 1.19  2008/01/31 19:40:57  jost
 * Jährliche, Halbjährliche und Vierteljährliche Abbuchungen können jetzt separat ausgeführt werden.
 * Berücksichtigung eines Stichtages für die Abbuchung
 *
 * Revision 1.18  2008/01/07 20:28:21  jost
 * Bugfix Rundungsproblem
 *
 * Revision 1.17  2007/12/30 10:10:07  jost
 * Neuer Rhytmus: Jahr, Vierteljahr und Monat
 *
 * Revision 1.16  2007/12/26 18:13:33  jost
 * Lastschriften können jetzt als Einzellastschriften oder Sammellastschriften direkt in Hibuscus verbucht werden.
 *
 * Revision 1.15  2007/12/21 13:36:10  jost
 * Ausgabe der DTAUS-Datei im PDF-Format
 *
 * Revision 1.14  2007/12/02 14:15:25  jost
 * Neu: Beitragsmodelle
 *
 * Revision 1.13  2007/12/02 13:43:43  jost
 * Neu: Beitragsmodelle
 *
 * Revision 1.12  2007/08/14 19:20:57  jost
 * Bugfix wenn keine Beitragsgruppe mit 0 ? existiert.
 *
 * Revision 1.11  2007/04/20 12:17:46  jost
 * Bugfix: Mehr als eine Beitragsgruppe beitragsfrei
 *
 * Revision 1.10  2007/03/30 13:25:40  jost
 * Wiederkehrende Zusatzabbuchungen.
 *
 * Revision 1.9  2007/03/13 19:58:26  jost
 * Beiträge, die nicht abgebucht werden (Bar/Überweisung) werden in die Liste der manuellen Zahlungseingänge eingetragen.
 *
 * Revision 1.8  2007/03/10 20:37:06  jost
 * Neu: Zahlungsweg
 *
 * Revision 1.7  2007/03/10 19:42:36  jost
 * Bugfix: Abbuchungsdatum wird jetzt gesetzt.
 *
 * Revision 1.6  2007/02/25 19:14:22  jost
 * Neu: Kursteilnehmer
 *
 * Revision 1.5  2007/02/23 20:28:04  jost
 * Mail- und Webadresse im Header korrigiert.
 *
 * Revision 1.4  2007/01/14 12:42:29  jost
 * Java 1.5-Kompatibilität
 *
 * Revision 1.3  2006/12/20 20:25:44  jost
 * Patch von Ullrich Sch�fer, der die Primitive vs. Object Problematik adressiert.
 *
 * Revision 1.2  2006/09/21 18:49:00  jost
 * �berfl�ssiges Import-Statement entfernt.
 *
 * Revision 1.1  2006/09/20 15:39:24  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.JVerein.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Hashtable;

import com.lowagie.text.DocumentException;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.keys.Abrechnungsausgabe;
import de.jost_net.JVerein.keys.Abrechnungsmodi;
import de.jost_net.JVerein.keys.Beitragsmodel;
import de.jost_net.JVerein.keys.IntervallZusatzzahlung;
import de.jost_net.JVerein.keys.Zahlungsrhytmus;
import de.jost_net.JVerein.keys.Zahlungsweg;
import de.jost_net.JVerein.rmi.Abrechnung;
import de.jost_net.JVerein.rmi.Abrechnungslauf;
import de.jost_net.JVerein.rmi.Beitragsgruppe;
import de.jost_net.JVerein.rmi.Kursteilnehmer;
import de.jost_net.JVerein.rmi.ManuellerZahlungseingang;
import de.jost_net.JVerein.rmi.Mitglied;
import de.jost_net.JVerein.rmi.Zusatzbetrag;
import de.jost_net.JVerein.util.Datum;
import de.jost_net.OBanToo.Dtaus.CSatz;
import de.jost_net.OBanToo.Dtaus.Dtaus2Pdf;
import de.jost_net.OBanToo.Dtaus.DtausDateiParser;
import de.jost_net.OBanToo.Dtaus.DtausDateiWriter;
import de.jost_net.OBanToo.Dtaus.DtausException;
import de.willuhn.datasource.rmi.DBIterator;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.internal.action.Program;
import de.willuhn.jameica.hbci.rmi.Lastschrift;
import de.willuhn.jameica.hbci.rmi.SammelLastschrift;
import de.willuhn.jameica.hbci.rmi.SammelTransferBuchung;
import de.willuhn.jameica.messaging.StatusBarMessage;
import de.willuhn.jameica.system.Application;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;
import de.willuhn.util.ProgressMonitor;

public class Abbuchung
{
  public Abbuchung(AbbuchungParam param, ProgressMonitor monitor)
      throws Exception
  {
    FileOutputStream out = new FileOutputStream(param.dtausfile);

    // Vorbereitung: A-Satz best�cken und schreiben
    DtausDateiWriter dtaus = new DtausDateiWriter(out);
    dtaus.setABLZBank(Long.parseLong(param.stamm.getBlz()));
    dtaus.setADatum(new Date());
    dtaus.setAGutschriftLastschrift("LK");
    dtaus.setAKonto(Long.parseLong(param.stamm.getKonto()));
    dtaus.setAKundenname(param.stamm.getName());
    dtaus.writeASatz();

    abbuchenMitglieder(dtaus, param.abbuchungsmodus, param.stichtag,
        param.vondatum, monitor, param.verwendungszweck);
    if (param.zusatzbetraege)
    {
      abbuchenZusatzbetraege(dtaus);
    }
    if (param.kursteilnehmer)
    {
      abbuchenKursteilnehmer(dtaus);
    }
    // Ende der Abbuchung. Jetzt wird noch der E-Satz geschrieben. Die Werte
    // wurden beim Schreiben der C-S�tze ermittelt.
    dtaus.writeESatz();
    if (param.abbuchungsausgabe == Abrechnungsausgabe.HIBISCUS_EINZELBUCHUNGEN
        || param.abbuchungsausgabe == Abrechnungsausgabe.HIBISCUS_SAMMELBUCHUNG)
    {
      buchenHibiscus(param);
    }
    Abrechnungslauf abrl = (Abrechnungslauf) Einstellungen.getDBService()
        .createObject(Abrechnungslauf.class, null);
    abrl.setDatum(new Date());
    abrl.setAbbuchungsausgabe(param.abbuchungsausgabe);
    abrl.setDtausdruck(param.dtausprint);
    abrl.setEingabedatum(param.vondatum);
    abrl.setKursteilnehmer(param.kursteilnehmer);
    abrl.setModus(param.abbuchungsmodus);
    abrl.setStichtag(param.stichtag);
    abrl.setZahlungsgrund(param.verwendungszweck);
    abrl.setZusatzbetraege(param.zusatzbetraege);
    abrl.store();
    monitor.log(JVereinPlugin.getI18n().tr("Anzahl Abrechnungen: {0}",
        new String[] { dtaus.getAnzahlSaetze() + "" }));
    monitor.log(JVereinPlugin.getI18n().tr("Gesamtsumme: {0} EUR",
        Einstellungen.DECIMALFORMAT.format(dtaus.getSummeBetraegeDecimal())));
    dtaus.close();
    monitor.setPercentComplete(100);
    if (param.dtausprint)
    {
      ausdruckenDTAUS(param.dtausfile.getAbsolutePath(), param.pdffile);
    }
  }

  private void abbuchenMitglieder(DtausDateiWriter dtaus, int modus,
      Date stichtag, Date vondatum, ProgressMonitor monitor,
      String verwendungszweck) throws Exception
  {
    // Ermittlung der beitragsfreien Beitragsgruppen
    String beitragsfrei = "";
    DBIterator list = Einstellungen.getDBService().createList(
        Beitragsgruppe.class);
    list.addFilter("betrag = 0");
    int gr = 0; // Anzahl beitragsfreier Gruppen
    while (list.hasNext())
    {
      gr++;
      Beitragsgruppe b = (Beitragsgruppe) list.next();
      if (gr > 1)
      {
        beitragsfrei += " AND ";
      }
      beitragsfrei += " beitragsgruppe <> " + b.getID();
    }

    // Beitragsgruppen-Tabelle lesen und cachen
    list = Einstellungen.getDBService().createList(Beitragsgruppe.class);
    list.addFilter("betrag > 0");
    Hashtable<String, Double> beitr = new Hashtable<String, Double>();
    while (list.hasNext())
    {
      Beitragsgruppe b = (Beitragsgruppe) list.next();
      beitr.put(b.getID(), new Double(b.getBetrag()));
    }

    if (modus != Abrechnungsmodi.KEINBEITRAG)
    {
      // Alle Mitglieder lesen
      list = Einstellungen.getDBService().createList(Mitglied.class);

      // Das Mitglied muss bereits eingetreten sein
      list.addFilter("(eintritt <= ? or eintritt is null) ",
          new Object[] { new java.sql.Date(stichtag.getTime()) });
      // Das Mitglied darf noch nicht ausgetreten sein
      list.addFilter("(austritt is null or austritt > ?)",
          new Object[] { new java.sql.Date(stichtag.getTime()) });
      // Beitragsfreie Mitglieder k�nnen auch unber�cksichtigt bleiben.
      if (beitragsfrei.length() > 0)
      {
        list.addFilter(beitragsfrei);
      }
      // Bei Abbuchungen im Laufe des Jahres werden nur die Mitglieder
      // ber�cksichtigt, die ab einem bestimmten Zeitpunkt eingetreten sind.
      if (vondatum != null)
      {
        list.addFilter("eingabedatum >= ?", new Object[] { new java.sql.Date(
            vondatum.getTime()) });
      }
      if (Einstellungen.getEinstellung().getBeitragsmodel() == Beitragsmodel.MONATLICH12631)
      {
        if (modus == Abrechnungsmodi.HAVIMO)
        {
          list
              .addFilter(
                  "(zahlungsrhytmus = ? or zahlungsrhytmus = ? or zahlungsrhytmus = ?)",
                  new Object[] { new Integer(Zahlungsrhytmus.HALBJAEHRLICH),
                      new Integer(Zahlungsrhytmus.VIERTELJAEHRLICH),
                      new Integer(Zahlungsrhytmus.MONATLICH) });
        }
        if (modus == Abrechnungsmodi.JAVIMO)
        {
          list
              .addFilter(
                  "(zahlungsrhytmus = ? or zahlungsrhytmus = ? or zahlungsrhytmus = ?)",
                  new Object[] { new Integer(Zahlungsrhytmus.JAEHRLICH),
                      new Integer(Zahlungsrhytmus.VIERTELJAEHRLICH),
                      new Integer(Zahlungsrhytmus.MONATLICH) });
        }
        if (modus == Abrechnungsmodi.VIMO)
        {
          list.addFilter("(zahlungsrhytmus = ? or zahlungsrhytmus = ?)",
              new Object[] { new Integer(Zahlungsrhytmus.VIERTELJAEHRLICH),
                  new Integer(Zahlungsrhytmus.MONATLICH) });
        }
        if (modus == Abrechnungsmodi.MO)
        {
          list.addFilter("zahlungsrhytmus = ?", new Object[] { new Integer(
              Zahlungsrhytmus.MONATLICH) });
        }
        if (modus == Abrechnungsmodi.VI)
        {
          list.addFilter("zahlungsrhytmus = ?", new Object[] { new Integer(
              Zahlungsrhytmus.VIERTELJAEHRLICH) });
        }
        if (modus == Abrechnungsmodi.HA)
        {
          list.addFilter("zahlungsrhytmus = ?", new Object[] { new Integer(
              Zahlungsrhytmus.HALBJAEHRLICH) });
        }
        if (modus == Abrechnungsmodi.JA)
        {
          list.addFilter("zahlungsrhytmus = ?", new Object[] { new Integer(
              Zahlungsrhytmus.JAEHRLICH) });
        }
      }
      list.setOrder("ORDER BY name, vorname");
      // S�tze im Resultset
      monitor.log("Anzahl S�tze: " + list.size());

      int count = 0;
      while (list.hasNext())
      {
        monitor.setStatus((int) ((double) count / (double) list.size() * 100d));
        Mitglied m = (Mitglied) list.next();
        Double betr = new Double(0d);
        if (Einstellungen.getEinstellung().getBeitragsmodel() != Beitragsmodel.MONATLICH12631)
        {
          betr = (Double) beitr.get(m.getBeitragsgruppeId() + "");
        }
        else
        {
          // Zur Vermeidung von Rundungsdifferenzen wird mit BigDecimal
          // gerechnet.
          try
          {
            BigDecimal bbetr = new BigDecimal(beitr.get(m.getBeitragsgruppeId()
                + ""));
            bbetr = bbetr.setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal bmonate = new BigDecimal(m.getZahlungsrhytmus());
            bbetr = bbetr.multiply(bmonate);
            betr = bbetr.doubleValue();
          }
          catch (NullPointerException e)
          {
            Logger.error(m.getVornameName() + ": " + m.getBeitragsgruppeId());
            DBIterator li = Einstellungen.getDBService().createList(
                Beitragsgruppe.class);
            while (li.hasNext())
            {
              Beitragsgruppe bg = (Beitragsgruppe) li.next();
              Logger.error("Beitragsgruppe: " + bg.getID() + ", "
                  + bg.getBezeichnung() + ", " + bg.getBetrag() + ", "
                  + bg.getBeitragsArt());
            }
            throw e;
          }
        }
        if (m.getZahlungsweg() == Zahlungsweg.ABBUCHUNG)
        {
          try
          {
            writeCSatz(dtaus, m, verwendungszweck, betr);
          }
          catch (Exception e)
          {
            throw new ApplicationException(m.getNameVorname() + ": "
                + e.getMessage());
          }
        }
        else
        {
          writeManuellerZahlungseingang(m, verwendungszweck, betr);
        }
        writeAbrechungsdaten(m, verwendungszweck, m.getNameVorname(), betr);
      }
    }
  }

  private void abbuchenZusatzbetraege(DtausDateiWriter dtaus)
      throws NumberFormatException, DtausException, IOException,
      ApplicationException
  {
    DBIterator list = Einstellungen.getDBService().createList(
        Zusatzbetrag.class);
    while (list.hasNext())
    {
      Zusatzbetrag z = (Zusatzbetrag) list.next();
      if (z.isAktiv())
      {
        Mitglied m = z.getMitglied();
        if (m.getZahlungsweg() == Zahlungsweg.ABBUCHUNG)
        {
          try
          {
            writeCSatz(dtaus, m, z.getBuchungstext(), new Double(z.getBetrag()));
          }
          catch (Exception e)
          {
            throw new ApplicationException(m.getNameVorname() + ": "
                + e.getMessage());
          }
        }
        else
        {
          writeManuellerZahlungseingang(m, z.getBuchungstext(), new Double(z
              .getBetrag()));
        }
        if (z.getIntervall().intValue() != IntervallZusatzzahlung.KEIN
            && (z.getEndedatum() == null || z.getFaelligkeit().getTime() <= z
                .getEndedatum().getTime()))
        {
          z.setFaelligkeit(Datum.addInterval(z.getFaelligkeit(), z
              .getIntervall(), z.getEndedatum()));
        }
        z.setAusfuehrung(Datum.getHeute());
        z.store();
        writeAbrechungsdaten(m, z.getBuchungstext(), "", z.getBetrag());
      }
    }
  }

  private void abbuchenKursteilnehmer(DtausDateiWriter dtaus)
      throws ApplicationException, DtausException, IOException
  {
    DBIterator list = Einstellungen.getDBService().createList(
        Kursteilnehmer.class);
    list.addFilter("abbudatum is null");
    while (list.hasNext())
    {
      Kursteilnehmer kt = (Kursteilnehmer) list.next();
      kt.setAbbudatum();
      kt.store();

      dtaus.setCBetragInEuro(kt.getBetrag());
      try
      {
        dtaus.setCBLZEndbeguenstigt(Integer.parseInt(kt.getBlz()));
      }
      catch (NumberFormatException e)
      {
        throw new ApplicationException(kt.getName()
            + ": Ung�ltige Bankleitzahl " + kt.getBlz());
      }
      dtaus.setCInterneKundennummer(Integer.parseInt(kt.getID() + 100000));
      try
      {
        dtaus.setCKonto(Long.parseLong(kt.getKonto()));
      }
      catch (NumberFormatException e)
      {
        throw new ApplicationException(kt.getName()
            + ": Ung�ltige Kontonummer " + kt.getKonto());
      }
      dtaus.setCName(kt.getName());
      dtaus
          .setCTextschluessel(CSatz.TS_LASTSCHRIFT_EINZUGSERMAECHTIGUNGSVERFAHREN);
      dtaus.addCVerwendungszweck(kt.getVZweck1());
      dtaus.addCVerwendungszweck(kt.getVZweck2());
      try
      {
        dtaus.writeCSatz();
      }
      catch (Exception e)
      {
        throw new ApplicationException(kt.getName() + ": " + e.getMessage());
      }

    }
  }

  private void ausdruckenDTAUS(String dtaus, String dtauspdf)
      throws IOException, DtausException, DocumentException
  {
    final String dtauspdffinal = dtauspdf;
    new Dtaus2Pdf(dtaus, dtauspdf);
    GUI.getDisplay().asyncExec(new Runnable()
    {
      public void run()
      {
        try
        {
          new Program().handleAction(new File(dtauspdffinal));
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

  private void buchenHibiscus(AbbuchungParam param) throws ApplicationException
  {
    try
    {
      DtausDateiParser parser = new DtausDateiParser(param.dtausfile
          .getAbsolutePath());
      SammelLastschrift sl = null;
      CSatz c = parser.next();
      if (param.abbuchungsausgabe == Abrechnungsausgabe.HIBISCUS_SAMMELBUCHUNG)
      {
        sl = (SammelLastschrift) param.service.createObject(
            SammelLastschrift.class, null);
        sl.setKonto(param.konto);
        sl.setBezeichnung(param.verwendungszweck);
      }
      while (c != null)
      {
        if (param.abbuchungsausgabe == Abrechnungsausgabe.HIBISCUS_EINZELBUCHUNGEN)
        {
          Lastschrift o = (Lastschrift) param.service.createObject(
              Lastschrift.class, null);
          o.setKonto(param.konto);
          o.setBetrag(c.getBetragInEuro());
          o.setZweck(c.getVerwendungszweck(1));
          o.setZweck2(c.getVerwendungszweck(2));
          o.setGegenkontoName(c.getNameEmpfaenger());
          o.setGegenkontoBLZ(c.getBlzEndbeguenstigt() + "");
          o.setGegenkontoNummer(c.getKontonummer() + "");
          o.store();
        }
        if (param.abbuchungsausgabe == Abrechnungsausgabe.HIBISCUS_SAMMELBUCHUNG)
        {
          SammelTransferBuchung o = sl.createBuchung();
          o.setBetrag(c.getBetragInEuro());
          o.setZweck(c.getVerwendungszweck(1));
          o.setZweck2(c.getVerwendungszweck(2));
          o.setGegenkontoName(c.getNameEmpfaenger());
          o.setGegenkontoBLZ(c.getBlzEndbeguenstigt() + "");
          o.setGegenkontoNummer(c.getKontonummer() + "");
          o.store();
        }
        c = parser.next();
      }
    }
    catch (RemoteException e)
    {
      throw new ApplicationException(e);
    }
    catch (IOException e)
    {
      throw new ApplicationException("Fehler beim �ffnen der DTAUS-Datei");
    }
    catch (DtausException e)
    {
      throw new ApplicationException("Fehler beim parsen der DTAUS-Datei: "
          + e.getMessage());
    }
  }

  private void writeCSatz(DtausDateiWriter dtaus, Mitglied m,
      String verwendungszweck, Double betr) throws DtausException,
      NumberFormatException, IOException
  {
    dtaus.setCBetragInEuro(betr.doubleValue());
    if (!Einstellungen.checkAccountCRC(m.getBlz(), m.getKonto()))
    {
      throw new DtausException(
          JVereinPlugin
              .getI18n()
              .tr(
                  "BLZ/Kontonummer ({0}/{1}) ung�ltig. Bitte pr�fen Sie Ihre Eingaben.",
                  new String[] { m.getBlz(), m.getKonto() }));
    }

    try
    {
      dtaus.setCBLZEndbeguenstigt(Integer.parseInt(m.getBlz()));
    }
    catch (NumberFormatException e)
    {
      throw new DtausException("Ung�ltige Bankleitzahl " + m.getBlz());
    }
    dtaus.setCInterneKundennummer(Integer.parseInt(m.getID()));
    try
    {
      dtaus.setCKonto(Long.parseLong(m.getKonto()));
    }
    catch (NumberFormatException e)
    {
      throw new DtausException("Ung�ltige Kontonummer " + m.getKonto());
    }
    String name = m.getNameVorname();
    String mitgliedname = (Einstellungen.getEinstellung()
        .getExterneMitgliedsnummer() ? m.getExterneMitgliedsnummer() : m
        .getID())
        + "/" + name;
    if (mitgliedname.length() > 25)
    {
      mitgliedname = mitgliedname.substring(0, 25);
    }
    if (m.getKontoinhaber().length() > 0)
    {
      name = m.getKontoinhaber();
    }
    if (name.length() > 27)
    {
      name = name.substring(0, 27);
    }
    dtaus.setCName(name);
    dtaus
        .setCTextschluessel(CSatz.TS_LASTSCHRIFT_EINZUGSERMAECHTIGUNGSVERFAHREN);
    dtaus.addCVerwendungszweck(verwendungszweck);
    dtaus.addCVerwendungszweck(mitgliedname);
    dtaus.writeCSatz();
  }

  private void writeManuellerZahlungseingang(Mitglied m,
      String verwendungszweck, Double betr) throws RemoteException,
      ApplicationException
  {
    ManuellerZahlungseingang mz = (ManuellerZahlungseingang) Einstellungen
        .getDBService().createObject(ManuellerZahlungseingang.class, null);
    mz.setBetrag(betr);
    mz.setEingabedatum();
    String name = m.getName() + ", " + m.getVorname();
    if (m.getKontoinhaber().length() > 0)
    {
      name = m.getKontoinhaber();
    }
    if (name.length() > 27)
    {
      name = name.substring(0, 27);
    }
    mz.setName(name);
    mz.setVZweck1(verwendungszweck);
    mz.setVZweck2(m.getName() + "," + m.getVorname());
    mz.store();
  }

  private void writeAbrechungsdaten(Mitglied m, String zweck1, String zweck2,
      double betrag) throws RemoteException, ApplicationException
  {
    if ((m.getZahlungsweg() == Zahlungsweg.ABBUCHUNG && Einstellungen
        .getEinstellung().getRechnungFuerAbbuchung())
        || (m.getZahlungsweg() == Zahlungsweg.�BERWEISUNG && Einstellungen
            .getEinstellung().getRechnungFuerUeberweisung())
        || (m.getZahlungsweg() == Zahlungsweg.BARZAHLUNG && Einstellungen
            .getEinstellung().getRechnungFuerBarzahlung()))
    {
      Abrechnung abr = (Abrechnung) Einstellungen.getDBService().createObject(
          Abrechnung.class, null);
      abr.setMitglied(m);
      abr.setZweck1(zweck1);
      abr.setZweck2(zweck2);
      abr.setDatum(new Date());
      abr.setBetrag(betrag);
      abr.store();
    }
  }
}
