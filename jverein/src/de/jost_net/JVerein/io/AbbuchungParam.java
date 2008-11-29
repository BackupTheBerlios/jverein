/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/io/AbbuchungParam.java,v $
 * $Revision: 1.6 $
 * $Date: 2008/11/29 13:12:13 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: AbbuchungParam.java,v $
 * Revision 1.6  2008/11/29 13:12:13  jost
 * Refactoring: Code-Optimierung
 *
 * Revision 1.5  2008/02/13 18:18:16  jost
 * Überflüssigen Import entfernt.
 *
 * Revision 1.4  2008/02/09 14:35:51  jost
 * Bugfix. Zusatzabbuchungen und Kursteilnehmer nur abbuchen, wenn das Häkchen gesetzt ist.
 *
 * Revision 1.3  2008/01/31 19:41:18  jost
 * Berücksichtigung eines Stichtages für die Abbuchung
 *
 * Revision 1.2  2007/12/28 13:14:25  jost
 * Bugfix beim erzeugen eines Stammdaten-Objektes
 *
 * Revision 1.1  2007/12/26 18:13:47  jost
 * Lastschriften können jetzt als Einzellastschriften oder Sammellastschriften direkt in Hibuscus verbucht werden.
 *
 **********************************************************************/
package de.jost_net.JVerein.io;

import java.io.File;
import java.rmi.RemoteException;
import java.util.Date;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.gui.control.AbbuchungControl;
import de.jost_net.JVerein.keys.Abrechnungsausgabe;
import de.jost_net.JVerein.rmi.Stammdaten;
import de.willuhn.datasource.rmi.DBIterator;
import de.willuhn.datasource.rmi.DBService;
import de.willuhn.jameica.hbci.HBCI;
import de.willuhn.jameica.hbci.gui.dialogs.KontoAuswahlDialog;
import de.willuhn.jameica.hbci.rmi.Konto;
import de.willuhn.jameica.system.Application;
import de.willuhn.jameica.system.OperationCanceledException;
import de.willuhn.util.ApplicationException;

public class AbbuchungParam
{
  public final int abbuchungsmodus;

  public final Date stichtag;

  public final int abbuchungsausgabe;

  public final Date vondatum;

  public final String verwendungszweck;

  public final Boolean zusatzabbuchung;

  public final Boolean kursteilnehmer;

  public final Boolean dtausprint;

  public final File dtausfile;

  public final String pdffile;

  public final Stammdaten stamm;

  public final DBService service;

  public Konto konto;

  public AbbuchungParam(AbbuchungControl ac, File dtausfile, String pdffile)
      throws ApplicationException, RemoteException
  {
    abbuchungsmodus = (Integer) ac.getAbbuchungsmodus().getValue();
    stichtag = (Date) ac.getStichtag().getValue();
    abbuchungsausgabe = (Integer) ac.getAbbuchungsausgabe().getValue();
    vondatum = (Date) ac.getVondatum().getValue();
    verwendungszweck = (String) ac.getZahlungsgrund().getValue();
    zusatzabbuchung = (Boolean) ac.getZusatzabbuchung().getValue();
    kursteilnehmer = (Boolean) ac.getKursteilnehmer().getValue();
    dtausprint = (Boolean) ac.getDtausPrint().getValue();
    this.pdffile = pdffile;
    this.dtausfile = dtausfile;

    try
    {
      DBIterator list = Einstellungen.getDBService().createList(
          Stammdaten.class);
      if (list.size() > 0)
      {
        stamm = (Stammdaten) list.next();
      }
      else
      {
        throw new RemoteException("Keine Stammdaten gespeichert");
      }
    }
    catch (RemoteException e)
    {
      throw new ApplicationException(
          "Keine Stammdaten gespeichert. Bitte erfassen.");
    }

    if (abbuchungsausgabe == Abrechnungsausgabe.HIBISCUS_EINZELBUCHUNGEN
        || abbuchungsausgabe == Abrechnungsausgabe.HIBISCUS_SAMMELBUCHUNG)
    {
      // DB-Service holen
      try
      {
        service = (DBService) Application.getServiceFactory().lookup(
            HBCI.class, "database");
        DBIterator konten = service.createList(Konto.class);
        while (konten.hasNext())
        {
          konto = (Konto) konten.next();
          if (stamm.getKonto().equals(konto.getKontonummer())
              && stamm.getBlz().equals(konto.getBLZ()))
          {
            // passendes Konto gefunden
            break;
          }
          else
          {
            konto = null;
          }
        }
        if (konto == null)
        {
          // Kein passendes Konto gefunden. Deshalb Kontoauswahldialog.
          KontoAuswahlDialog d = new KontoAuswahlDialog(
              KontoAuswahlDialog.POSITION_CENTER);
          konto = (Konto) d.open();
          if (konto == null)
          {
            throw new ApplicationException("Bitte w�hlen Sie ein Konto aus");
          }
        }
      }
      catch (OperationCanceledException e)
      {
        throw new ApplicationException("Bitte Konto ausw�hlen");
      }
      catch (Exception e)
      {
        e.printStackTrace();
        throw new ApplicationException(
            "Hibiscus-Datenbank kann nicht ge�ffnet werden.");
      }
    }
    else
    {
      service = null;
    }

  }
}
