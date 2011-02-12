/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/io/BuchungAuswertungPDFEinzelbuchungen.java,v $
 * $Revision: 1.6 $
 * $Date: 2011/02/12 09:37:58 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: BuchungAuswertungPDFEinzelbuchungen.java,v $
 * Revision 1.6  2011/02/12 09:37:58  jost
 * Statische Codeanalyse mit Findbugs
 *
 * Revision 1.5  2010-10-15 09:58:28  jost
 * Code aufger�umt
 *
 * Revision 1.4  2009-02-08 10:31:50  jost
 * Bugfix Gesamtsumme
 *
 * Revision 1.3  2008/12/30 11:28:23  jost
 * Summenzeilen korrekt ausgeben. Bug #14978
 *
 * Revision 1.2  2008/12/11 19:19:00  jost
 * *** empty log message ***
 *
 * Revision 1.1  2008/07/10 07:57:51  jost
 * PDF-Export der Buchungen jetzt mit Einzelbuchungen und als Summen
 *
 * Revision 1.7  2008/05/24 19:32:42  jost
 * Auswertung überarbeitet.
 *
 * Revision 1.6  2008/03/16 07:37:37  jost
 * Reaktivierung Buchführung
 *
 * Revision 1.4  2007/02/23 20:28:04  jost
 * Mail- und Webadresse im Header korrigiert.
 *
 * Revision 1.3  2006/10/16 17:40:02  jost
 * Korrekte Subtitle-Ausgabe
 *
 * Revision 1.2  2006/10/14 16:11:56  jost
 * Pagesize und R�nder gesetzt.
 *
 * Revision 1.1  2006/10/14 06:03:00  jost
 * Erweiterung um Buchungsauswertung
 *
 * Revision 1.1  2006/09/20 15:39:24  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.JVerein.io;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Date;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.rmi.Buchung;
import de.jost_net.JVerein.rmi.Buchungsart;
import de.jost_net.JVerein.rmi.Konto;
import de.jost_net.JVerein.util.JVDateFormatTTMMJJJJ;
import de.willuhn.datasource.rmi.DBIterator;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.internal.action.Program;
import de.willuhn.jameica.messaging.StatusBarMessage;
import de.willuhn.jameica.system.Application;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;
import de.willuhn.util.ProgressMonitor;

public class BuchungAuswertungPDFEinzelbuchungen
{

  private double summe = 0;

  public BuchungAuswertungPDFEinzelbuchungen(DBIterator list, final File file,
      ProgressMonitor monitor, Konto konto, Buchungsart buchungsart, Date dVon,
      Date dBis) throws ApplicationException
  {
    try
    {
      FileOutputStream fos = new FileOutputStream(file);
      String subtitle = "vom " + new JVDateFormatTTMMJJJJ().format(dVon)
          + " bis " + new JVDateFormatTTMMJJJJ().format(dBis);
      if (konto != null)
      {
        subtitle += " f�r Konto " + konto.getNummer() + " - "
            + konto.getBezeichnung();
      }
      Reporter reporter = new Reporter(fos, monitor, "Buchungsliste", subtitle,
          list.size());

      while (list.hasNext())
      {
        createTableContent(reporter, list, konto, dVon, dBis);
      }
      if (buchungsart.getArt() == -1)
      {
        createTableContent(reporter, null, konto, dVon, dBis);
      }
      createTableHeader(reporter);
      reporter.addColumn("", Element.ALIGN_LEFT);
      reporter.addColumn("", Element.ALIGN_LEFT);
      reporter.addColumn("", Element.ALIGN_LEFT);
      reporter.addColumn("", Element.ALIGN_LEFT);
      reporter.addColumn("Gesamtsumme ", Element.ALIGN_LEFT);
      reporter.addColumn(summe);
      reporter.closeTable();
      monitor.setStatusText("Auswertung fertig. " + list.size() + " S�tze.");

      reporter.close();
      fos.close();
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
    catch (DocumentException e)
    {
      e.printStackTrace();
      Logger.error("error while creating report", e);
      throw new ApplicationException("Fehler beim Erzeugen des Reports", e);
    }
    catch (FileNotFoundException e)
    {
      Logger.error("error while creating report", e);
      throw new ApplicationException("Fehler beim Erzeugen des Reports", e);
    }
    catch (IOException e)
    {
      Logger.error("error while creating report", e);
      throw new ApplicationException("Fehler beim Erzeugen des Reports", e);
    }

  }

  private void createTableHeader(Reporter reporter) throws DocumentException
  {
    reporter.addHeaderColumn("Datum", Element.ALIGN_CENTER, 40,
        Color.LIGHT_GRAY);
    reporter.addHeaderColumn("Auszug", Element.ALIGN_CENTER, 20,
        Color.LIGHT_GRAY);
    reporter.addHeaderColumn("Name", Element.ALIGN_CENTER, 100,
        Color.LIGHT_GRAY);
    reporter.addHeaderColumn("Zahlungsgrund", Element.ALIGN_CENTER, 100,
        Color.LIGHT_GRAY);
    reporter.addHeaderColumn("Zahlungsgrund2", Element.ALIGN_CENTER, 100,
        Color.LIGHT_GRAY);
    reporter.addHeaderColumn("Betrag", Element.ALIGN_CENTER, 50,
        Color.LIGHT_GRAY);
    reporter.createHeader();

  }

  private void createTableContent(Reporter reporter, DBIterator list,
      Konto konto, Date dVon, Date dBis) throws RemoteException,
      DocumentException
  {
    Buchungsart ba = null;
    Paragraph pBuchungsart = null;
    if (list != null)
    {
      ba = (Buchungsart) list.next();
      pBuchungsart = new Paragraph(ba.getBezeichnung(), FontFactory.getFont(
          FontFactory.HELVETICA_BOLD, 10));
    }
    else
    {
      pBuchungsart = new Paragraph("ohne Zuordnung", FontFactory.getFont(
          FontFactory.HELVETICA_BOLD, 10));
    }

    reporter.add(pBuchungsart);
    DBIterator listb = Einstellungen.getDBService().createList(Buchung.class);
    listb.addFilter("datum >= ?",
        new Object[] { new java.sql.Date(dVon.getTime()) });
    listb.addFilter("datum <= ?",
        new Object[] { new java.sql.Date(dBis.getTime()) });
    if (konto != null)
    {
      listb.addFilter("konto = ?", new Object[] { konto.getID() });
    }
    if (list != null)
    {
      listb.addFilter("buchungsart = ?",
          new Object[] { new Integer(ba.getID()) });
    }
    else
    {
      listb.addFilter("buchungsart is null");
    }
    listb.setOrder("ORDER BY datum");
    double buchungsartSumme = 0;
    createTableHeader(reporter);
    boolean gedruckt = false;
    while (listb.hasNext())
    {
      gedruckt = true;
      Buchung b = (Buchung) listb.next();
      reporter.addColumn(new JVDateFormatTTMMJJJJ().format(b.getDatum()),
          Element.ALIGN_LEFT);
      if (b.getAuszugsnummer() != null)
      {
        reporter.addColumn(b.getAuszugsnummer() + "/" + b.getBlattnummer(),
            Element.ALIGN_LEFT);
      }
      else
      {
        reporter.addColumn("", Element.ALIGN_LEFT);
      }
      reporter.addColumn(b.getName(), Element.ALIGN_LEFT);
      reporter.addColumn(b.getZweck(), Element.ALIGN_LEFT);
      reporter.addColumn(b.getZweck2(), Element.ALIGN_LEFT);
      reporter.addColumn(b.getBetrag());
      buchungsartSumme += b.getBetrag();
    }
    if (!gedruckt)
    {
      reporter.addColumn("", Element.ALIGN_LEFT);
      reporter.addColumn("", Element.ALIGN_LEFT);
      reporter.addColumn("keine Buchung", Element.ALIGN_LEFT);
      reporter.addColumn("", Element.ALIGN_LEFT);
      reporter.addColumn("", Element.ALIGN_LEFT);
      reporter.addColumn("", Element.ALIGN_LEFT);
    }
    reporter.addColumn("", Element.ALIGN_LEFT);
    reporter.addColumn("", Element.ALIGN_LEFT);
    reporter.addColumn("", Element.ALIGN_LEFT);
    reporter.addColumn("", Element.ALIGN_LEFT);
    if (list != null)
    {
      reporter.addColumn("Summe " + ba.getBezeichnung(), Element.ALIGN_LEFT);
    }
    else
    {
      reporter.addColumn("Summe ohne Zuordnung", Element.ALIGN_LEFT);
    }
    summe += buchungsartSumme;
    reporter.addColumn(buchungsartSumme);
    reporter.closeTable();
  }

}
