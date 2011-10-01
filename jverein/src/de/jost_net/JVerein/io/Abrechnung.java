/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/io/Abrechnung.java,v $
 * $Revision: 1.14 $
 * $Date: 2011/10/01 21:46:58 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
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
import de.jost_net.JVerein.rmi.Abrechnungslauf;
import de.jost_net.JVerein.rmi.Beitragsgruppe;
import de.jost_net.JVerein.rmi.Buchung;
import de.jost_net.JVerein.rmi.Konto;
import de.jost_net.JVerein.rmi.Kursteilnehmer;
import de.jost_net.JVerein.rmi.Mitglied;
import de.jost_net.JVerein.rmi.Mitgliedskonto;
import de.jost_net.JVerein.rmi.Zusatzbetrag;
import de.jost_net.JVerein.server.MitgliedUtils;
import de.jost_net.JVerein.util.Datum;
import de.jost_net.OBanToo.Dtaus.CSatz;
import de.jost_net.OBanToo.Dtaus.Dtaus2Pdf;
import de.jost_net.OBanToo.Dtaus.DtausDateiParser;
import de.jost_net.OBanToo.Dtaus.DtausDateiWriter;
import de.jost_net.OBanToo.Dtaus.DtausException;
import de.willuhn.datasource.rmi.DBIterator;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.internal.action.Program;
import de.willuhn.jameica.hbci.TextSchluessel;
import de.willuhn.jameica.hbci.rmi.Lastschrift;
import de.willuhn.jameica.hbci.rmi.SammelLastschrift;
import de.willuhn.jameica.hbci.rmi.SammelTransferBuchung;
import de.willuhn.jameica.messaging.StatusBarMessage;
import de.willuhn.jameica.system.Application;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;
import de.willuhn.util.ProgressMonitor;

public class Abrechnung
{
  AbbuchungParam param;

  public Abrechnung(AbbuchungParam param, ProgressMonitor monitor)
      throws Exception
  {

    XLastschriften lastschriften = new XLastschriften();
    this.param = param;

    Abrechnungslauf abrl = getAbrechnungslauf();
    Konto konto = getKonto();
    abrechnenMitglieder(lastschriften, monitor, abrl, konto);
    if (param.zusatzbetraege)
    {
      abbuchenZusatzbetraege(lastschriften, abrl, konto, monitor);
    }
    if (param.kursteilnehmer)
    {
      abbuchenKursteilnehmer(lastschriften);
    }

    FileOutputStream out = new FileOutputStream(param.dtausfile);

    // Vorbereitung: A-Satz best�cken und schreiben
    DtausDateiWriter dtaus = new DtausDateiWriter(out);
    dtaus.setABLZBank(Long.parseLong(Einstellungen.getEinstellung().getBlz()));
    dtaus.setADatum(new Date());
    dtaus.setAGutschriftLastschrift("LK");
    dtaus.setAKonto(Long.parseLong(Einstellungen.getEinstellung().getKonto()));
    dtaus.setAKundenname(Einstellungen.getEinstellung().getName());
    dtaus.writeASatz();

    if (param.kompakteabbuchung)
    {
      lastschriften.compact();
    }
    for (XLastschrift lastschrift : lastschriften.getLastschriften())
    {
      writeCSatz(dtaus, lastschrift);
    }

    // Ende der Abbuchung. Jetzt wird noch der E-Satz geschrieben. Die Werte
    // wurden beim Schreiben der C-S�tze ermittelt.
    dtaus.writeESatz();
    dtaus.close();

    // Gegenbuchung f�r das Mitgliedskonto schreiben
    if (Einstellungen.getEinstellung().getMitgliedskonto())
    {
      writeMitgliedskonto(null, new Date(), "Gegenbuchung", "", dtaus
          .getSummeBetraegeDecimal().doubleValue() * -1, abrl, true,
          getKonto(), null);
    }

    if (param.abbuchungsausgabe == Abrechnungsausgabe.HIBISCUS_EINZELBUCHUNGEN
        || param.abbuchungsausgabe == Abrechnungsausgabe.HIBISCUS_SAMMELBUCHUNG)
    {
      buchenHibiscus();
    }
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

  private void abrechnenMitglieder(XLastschriften lastschriften,
      ProgressMonitor monitor, Abrechnungslauf abrl, Konto konto)
      throws Exception
  {
    // Ermittlung der beitragsfreien Beitragsgruppen
    StringBuilder beitragsfrei = new StringBuilder();
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
        beitragsfrei.append(" AND ");
      }
      beitragsfrei.append(" beitragsgruppe <> ");
      beitragsfrei.append(b.getID());
    }

    // Beitragsgruppen-Tabelle lesen und cachen
    list = Einstellungen.getDBService().createList(Beitragsgruppe.class);
    list.addFilter("betrag > 0");
    Hashtable<String, Beitragsgruppe> beitragsgruppe = new Hashtable<String, Beitragsgruppe>();
    while (list.hasNext())
    {
      Beitragsgruppe b = (Beitragsgruppe) list.next();
      beitragsgruppe.put(b.getID(), b);
    }

    if (param.abbuchungsmodus != Abrechnungsmodi.KEINBEITRAG)
    {
      // Alle Mitglieder lesen
      list = Einstellungen.getDBService().createList(Mitglied.class);
      MitgliedUtils.setMitglied(list);

      // Das Mitglied muss bereits eingetreten sein
      list.addFilter("(eintritt <= ? or eintritt is null) ",
          new Object[] { new java.sql.Date(param.stichtag.getTime()) });
      // Das Mitglied darf noch nicht ausgetreten sein
      list.addFilter("(austritt is null or austritt > ?)",
          new Object[] { new java.sql.Date(param.stichtag.getTime()) });
      // Beitragsfreie Mitglieder k�nnen auch unber�cksichtigt bleiben.
      if (beitragsfrei.length() > 0)
      {
        list.addFilter(beitragsfrei.toString());
      }
      // Bei Abbuchungen im Laufe des Jahres werden nur die Mitglieder
      // ber�cksichtigt, die ab einem bestimmten Zeitpunkt eingetreten sind.
      if (param.vondatum != null)
      {
        list.addFilter("eingabedatum >= ?", new Object[] { new java.sql.Date(
            param.vondatum.getTime()) });
      }
      if (Einstellungen.getEinstellung().getBeitragsmodel() == Beitragsmodel.MONATLICH12631)
      {
        if (param.abbuchungsmodus == Abrechnungsmodi.HAVIMO)
        {
          list.addFilter(
              "(zahlungsrhytmus = ? or zahlungsrhytmus = ? or zahlungsrhytmus = ?)",
              new Object[] { new Integer(Zahlungsrhytmus.HALBJAEHRLICH),
                  new Integer(Zahlungsrhytmus.VIERTELJAEHRLICH),
                  new Integer(Zahlungsrhytmus.MONATLICH) });
        }
        if (param.abbuchungsmodus == Abrechnungsmodi.JAVIMO)
        {
          list.addFilter(
              "(zahlungsrhytmus = ? or zahlungsrhytmus = ? or zahlungsrhytmus = ?)",
              new Object[] { new Integer(Zahlungsrhytmus.JAEHRLICH),
                  new Integer(Zahlungsrhytmus.VIERTELJAEHRLICH),
                  new Integer(Zahlungsrhytmus.MONATLICH) });
        }
        if (param.abbuchungsmodus == Abrechnungsmodi.VIMO)
        {
          list.addFilter("(zahlungsrhytmus = ? or zahlungsrhytmus = ?)",
              new Object[] { Integer.valueOf(Zahlungsrhytmus.VIERTELJAEHRLICH),
                  Integer.valueOf(Zahlungsrhytmus.MONATLICH) });
        }
        if (param.abbuchungsmodus == Abrechnungsmodi.MO)
        {
          list.addFilter("zahlungsrhytmus = ?",
              new Object[] { Integer.valueOf(Zahlungsrhytmus.MONATLICH) });
        }
        if (param.abbuchungsmodus == Abrechnungsmodi.VI)
        {
          list.addFilter(
              "zahlungsrhytmus = ?",
              new Object[] { Integer.valueOf(Zahlungsrhytmus.VIERTELJAEHRLICH) });
        }
        if (param.abbuchungsmodus == Abrechnungsmodi.HA)
        {
          list.addFilter("zahlungsrhytmus = ?",
              new Object[] { Integer.valueOf(Zahlungsrhytmus.HALBJAEHRLICH) });
        }
        if (param.abbuchungsmodus == Abrechnungsmodi.JA)
        {
          list.addFilter("zahlungsrhytmus = ?",
              new Object[] { Integer.valueOf(Zahlungsrhytmus.JAEHRLICH) });
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
        Double betr;
        if (Einstellungen.getEinstellung().getBeitragsmodel() != Beitragsmodel.MONATLICH12631)
        {
          betr = beitragsgruppe.get(m.getBeitragsgruppeId() + "").getBetrag();
          if (Einstellungen.getEinstellung().getIndividuelleBeitraege()
              && m.getIndividuellerBeitrag() > 0)
          {
            betr = m.getIndividuellerBeitrag();
          }
        }
        else
        {
          // Zur Vermeidung von Rundungsdifferenzen wird mit BigDecimal
          // gerechnet.
          try
          {
            BigDecimal bbetr = new BigDecimal(beitragsgruppe.get(
                m.getBeitragsgruppeId() + "").getBetrag());
            bbetr = bbetr.setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal bmonate = new BigDecimal(m.getZahlungsrhytmus());
            bbetr = bbetr.multiply(bmonate);
            betr = bbetr.doubleValue();
            if (Einstellungen.getEinstellung().getIndividuelleBeitraege()
                && m.getIndividuellerBeitrag() > 0)
            {
              betr = m.getIndividuellerBeitrag();
            }
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
        if (Einstellungen.getEinstellung().getMitgliedskonto())
        {
          writeMitgliedskonto(m, new Date(), param.verwendungszweck, "", betr,
              abrl, m.getZahlungsweg() == Zahlungsweg.ABBUCHUNG, konto,
              beitragsgruppe.get(m.getBeitragsgruppeId() + ""));
        }
        if (m.getZahlungsweg() == Zahlungsweg.ABBUCHUNG)
        {
          try
          {
            XLastschrift lastschrift = new XLastschrift();
            lastschrift.setBetrag(new BigDecimal(betr).setScale(2,
                BigDecimal.ROUND_HALF_UP));
            if (!Einstellungen.checkAccountCRC(m.getBlz(), m.getKonto()))
            {
              throw new DtausException(
                  JVereinPlugin
                      .getI18n()
                      .tr("BLZ/Kontonummer ({0}/{1}) ung�ltig. Bitte pr�fen Sie Ihre Eingaben.",
                          new String[] { m.getBlz(), m.getKonto() }));
            }
            lastschrift.setBlz(Integer.parseInt(m.getBlz()));
            lastschrift.setKonto(Long.parseLong(m.getKonto()));
            lastschrift.addVerwendungszweck(param.verwendungszweck);
            lastschrift.addVerwendungszweck(getVerwendungszweck2(m));
            lastschrift.addZahlungspflichtigen(getZahlungspflichtigen(m));
            lastschriften.add(lastschrift);
          }
          catch (Exception e)
          {
            throw new ApplicationException(m.getNameVorname() + ": "
                + e.getMessage());
          }
        }
      }
    }
  }

  private void abbuchenZusatzbetraege(XLastschriften lastschriften,
      Abrechnungslauf abrl, Konto konto, ProgressMonitor monitor)
      throws NumberFormatException, IOException, ApplicationException
  {
    DBIterator list = Einstellungen.getDBService().createList(
        Zusatzbetrag.class);
    while (list.hasNext())
    {
      Zusatzbetrag z = (Zusatzbetrag) list.next();
      if (z.isAktiv())
      {
        Mitglied m = z.getMitglied();
        if ((m.getEingabedatum() == null || m.getEingabedatum().before(
            param.stichtag))
            && (m.getAustritt() == null || m.getAustritt().before(
                param.stichtag)))
        {
          //
        }
        else
        {
          continue;
        }
        if (m.getZahlungsweg() == Zahlungsweg.ABBUCHUNG)
        {
          try
          {
            XLastschrift lastschrift = new XLastschrift();
            lastschrift.setBetrag(new BigDecimal(z.getBetrag()).setScale(2,
                BigDecimal.ROUND_HALF_UP));
            if (!Einstellungen.checkAccountCRC(m.getBlz(), m.getKonto()))
            {
              throw new DtausException(
                  JVereinPlugin
                      .getI18n()
                      .tr("BLZ/Kontonummer ({0}/{1}) ung�ltig. Bitte pr�fen Sie Ihre Eingaben.",
                          new String[] { m.getBlz(), m.getKonto() }));
            }
            lastschrift.setBlz(Integer.parseInt(m.getBlz()));
            lastschrift.setKonto(Long.parseLong(m.getKonto()));
            lastschrift.addZahlungspflichtigen(getZahlungspflichtigen(m));
            lastschrift.addVerwendungszweck(z.getBuchungstext());
            if (z.getBuchungstext2() != null
                && z.getBuchungstext2().length() > 0)
            {
              lastschrift.addVerwendungszweck(z.getBuchungstext2());
            }
            lastschrift.addVerwendungszweck(getVerwendungszweck2(m));
            lastschriften.add(lastschrift);
          }
          catch (Exception e)
          {
            throw new ApplicationException(m.getNameVorname() + ": "
                + e.getMessage());
          }
        }
        if (z.getIntervall().intValue() != IntervallZusatzzahlung.KEIN
            && (z.getEndedatum() == null || z.getFaelligkeit().getTime() <= z
                .getEndedatum().getTime()))
        {
          z.setFaelligkeit(Datum.addInterval(z.getFaelligkeit(),
              z.getIntervall()));
        }
        z.setAusfuehrung(Datum.getHeute());
        try
        {
          z.store();
        }
        catch (ApplicationException e)
        {
          String debString = z.getStartdatum() + ", " + z.getEndedatum() + ", "
              + z.getIntervallText() + ", " + z.getBuchungstext() + ", "
              + z.getBetrag();
          Logger.error(z.getMitglied().getNameVorname() + " " + debString, e);
          monitor.log(z.getMitglied().getName() + " " + debString + " " + e);
          throw e;
        }
        if (Einstellungen.getEinstellung().getMitgliedskonto())
        {
          writeMitgliedskonto(m, new Date(), z.getBuchungstext(),
              z.getBuchungstext2() != null ? z.getBuchungstext2() : "",
              z.getBetrag(), abrl, m.getZahlungsweg() == Zahlungsweg.ABBUCHUNG,
              konto, null);
        }

      }
    }
  }

  private void abbuchenKursteilnehmer(XLastschriften lastschriften)
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

      XLastschrift lastschrift = new XLastschrift();
      lastschrift.setBetrag(new BigDecimal(kt.getBetrag()).setScale(2,
          BigDecimal.ROUND_HALF_UP));
      if (!Einstellungen.checkAccountCRC(kt.getBlz(), kt.getKonto()))
      {
        throw new DtausException(
            JVereinPlugin
                .getI18n()
                .tr("BLZ/Kontonummer ({0}/{1}) ung�ltig. Bitte pr�fen Sie Ihre Eingaben.",
                    new String[] { kt.getBlz(), kt.getKonto() }));
      }

      lastschrift.setBlz(Integer.parseInt(kt.getBlz()));
      lastschrift.setKonto(Long.parseLong(kt.getKonto()));
      lastschrift.addZahlungspflichtigen(kt.getName());
      lastschrift.addVerwendungszweck(kt.getVZweck1());
      lastschrift.addVerwendungszweck(kt.getVZweck2());
      lastschriften.add(lastschrift);
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

  private void buchenHibiscus() throws ApplicationException
  {
    try
    {
      DtausDateiParser parser = new DtausDateiParser(
          param.dtausfile.getAbsolutePath());
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
          if (c.getAnzahlVerwendungszwecke() > 2)
          {
            String[] weiterzwecke = new String[c.getAnzahlVerwendungszwecke() - 2];
            for (int i = 3; i <= c.getAnzahlVerwendungszwecke(); i++)
            {
              weiterzwecke[i - 3] = c.getVerwendungszweck(i);
            }
            o.setWeitereVerwendungszwecke(weiterzwecke);
          }
          o.setGegenkontoName(c.getNameEmpfaenger());
          o.setGegenkontoBLZ(c.getBlzEndbeguenstigt() + "");
          o.setGegenkontoNummer(c.getKontonummer() + "");
          o.setTextSchluessel(TextSchluessel.TS_EINZUG);
          o.store();
        }
        if (param.abbuchungsausgabe == Abrechnungsausgabe.HIBISCUS_SAMMELBUCHUNG)
        {
          SammelTransferBuchung o = sl.createBuchung();
          o.setBetrag(c.getBetragInEuro());
          o.setZweck(c.getVerwendungszweck(1));
          o.setZweck2(c.getVerwendungszweck(2));
          if (c.getAnzahlVerwendungszwecke() > 2)
          {
            final String[] weiterzwecke = new String[c
                .getAnzahlVerwendungszwecke() - 2];
            for (int i = 3; i <= c.getAnzahlVerwendungszwecke(); i++)
            {
              weiterzwecke[i - 3] = c.getVerwendungszweck(i);
            }
            o.setWeitereVerwendungszwecke(weiterzwecke);
          }
          o.setGegenkontoName(c.getNameEmpfaenger());
          o.setGegenkontoBLZ(c.getBlzEndbeguenstigt() + "");
          o.setGegenkontoNummer(c.getKontonummer() + "");
          o.setTextSchluessel(TextSchluessel.TS_EINZUG);
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

  private void writeCSatz(DtausDateiWriter dtaus, XLastschrift lastschrift)
      throws DtausException, NumberFormatException, IOException
  {
    dtaus.setCBetragInEuro(lastschrift.getBetrag().doubleValue());
    dtaus.setCBLZEndbeguenstigt(lastschrift.getBlz());
    dtaus.setCKonto(lastschrift.getKonto());
    dtaus.setCName(lastschrift.getZahlungspflichtigen(0));
    dtaus.setCInterneKundennummer(0L);
    if (lastschrift.getAnzahlZahlungspflichtige() >= 2)
    {
      dtaus.setCName2(lastschrift.getZahlungspflichtigen(1));
    }
    for (int i = 0; i < lastschrift.getAnzahlVerwendungszwecke(); i++)
    {
      dtaus.addCVerwendungszweck(lastschrift.getVerwendungszweck(i));
    }
    dtaus
        .setCTextschluessel(CSatz.TS_LASTSCHRIFT_EINZUGSERMAECHTIGUNGSVERFAHREN);
    dtaus.writeCSatz();
  }

  private Abrechnungslauf getAbrechnungslauf() throws RemoteException,
      ApplicationException
  {
    if (!Einstellungen.getEinstellung().getMitgliedskonto())
    {
      return null;
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
    return abrl;
  }

  private void writeMitgliedskonto(Mitglied mitglied, Date datum,
      String zweck1, String zweck2, double betrag, Abrechnungslauf abrl,
      boolean haben, Konto konto, Beitragsgruppe beitragsgruppe)
      throws ApplicationException, RemoteException
  {
    Mitgliedskonto mk = null;
    if (mitglied != null) /*
                           * Mitglied darf dann null sein, wenn die Gegenbuchung
                           * geschrieben wird
                           */
    {
      mk = (Mitgliedskonto) Einstellungen.getDBService().createObject(
          Mitgliedskonto.class, null);
      mk.setAbrechnungslauf(abrl);
      mk.setZahlungsweg(mitglied.getZahlungsweg());
      mk.setBetrag(betrag);
      mk.setDatum(datum);
      mk.setMitglied(mitglied);
      mk.setZweck1(zweck1);
      mk.setZweck2(zweck2);
      mk.store();
    }
    if (haben)
    {
      Buchung buchung = (Buchung) Einstellungen.getDBService().createObject(
          Buchung.class, null);
      buchung.setAbrechnungslauf(abrl);
      buchung.setBetrag(betrag);
      buchung.setDatum(datum);
      buchung.setKonto(konto);
      buchung.setName(mitglied != null ? mitglied.getNameVorname() : "JVerein");
      buchung.setZweck(zweck1);
      buchung.setZweck2(zweck2);
      if (mk != null)
      {
        buchung.setMitgliedskonto(mk);
      }
      if (beitragsgruppe != null && beitragsgruppe.getBuchungsart() != null)
      {
        buchung.setBuchungsart(new Integer(beitragsgruppe.getBuchungsart()
            .getID()));
      }
      buchung.store();

    }
  }

  /**
   * Ist das Abbuchungskonto in der Buchf�hrung eingerichtet?
   */
  private Konto getKonto() throws ApplicationException, RemoteException
  {
    if (!Einstellungen.getEinstellung().getMitgliedskonto())
    {
      return null;
    }
    DBIterator it = Einstellungen.getDBService().createList(Konto.class);
    it.addFilter("nummer = ?", Einstellungen.getEinstellung().getKonto());
    if (it.size() != 1)
    {
      throw new ApplicationException(
          "Konto "
              + Einstellungen.getEinstellung().getKonto()
              + " ist in der Buchf�hrung nicht eingerichtet. Menu: Buchf�hrung | Konten");
    }
    Konto k = (Konto) it.next();
    return k;
  }

  private String getZahlungspflichtigen(Mitglied m) throws RemoteException
  {
    String zpfl = m.getNameVorname();
    if (m.getKontoinhaber().length() > 0)
    {
      zpfl = m.getKontoinhaber();
    }
    zpfl = dtaus27(zpfl);
    return zpfl;
  }

  private String getVerwendungszweck2(Mitglied m) throws RemoteException
  {
    String mitgliedname = (Einstellungen.getEinstellung()
        .getExterneMitgliedsnummer() ? m.getExterneMitgliedsnummer() : m
        .getID())
        + "/" + m.getNameVorname();
    mitgliedname = dtaus27(mitgliedname);
    return mitgliedname;
  }

  private String dtaus27(String in)
  {
    String out = in;
    if (in.length() > 27)
    {
      out = in.substring(0, 27);
    }
    int lae = out.length();
    for (int i = 0; i < out.length(); i++)
    {
      Character c = out.charAt(i);
      if (c.equals('�') || c.equals('�') || c.equals('�') || c.equals('�')
          || c.equals('�') || c.equals('�') || c.equals('�'))
      {
        lae--;
      }
    }
    out = out.substring(0, lae);
    return out;
  }

}
