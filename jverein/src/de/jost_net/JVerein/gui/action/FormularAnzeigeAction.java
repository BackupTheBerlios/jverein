/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/FormularAnzeigeAction.java,v $
 * $Revision: 1.16 $
 * $Date: 2011/10/01 21:32:43 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Map;

import jonelo.NumericalChameleon.SpokenNumbers.GermanNumber;
import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.Variable.AllgemeineMap;
import de.jost_net.JVerein.gui.control.FormularfeldControl;
import de.jost_net.JVerein.io.FormularAufbereitung;
import de.jost_net.JVerein.rmi.Formular;
import de.jost_net.JVerein.rmi.Mitglied;
import de.jost_net.JVerein.rmi.Spendenbescheinigung;
import de.jost_net.JVerein.util.JVDateFormatTTMMJJJJ;
import de.willuhn.jameica.gui.Action;
import de.willuhn.util.ApplicationException;

public class FormularAnzeigeAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    Formular formular = null;

    if (context != null && (context instanceof Formular))
    {
      formular = (Formular) context;
    }
    else
    {
      throw new ApplicationException(JVereinPlugin.getI18n().tr(
          "Kein Formular zur Anzeige ausgew�hlt"));
    }
    try
    {
      final File file = File.createTempFile("formular", ".pdf");
      Mitglied m = (Mitglied) Einstellungen.getDBService().createObject(
          Mitglied.class, null);

      Map<String, Object> map = m.getMap(null);
      map = new AllgemeineMap().getMap(map);
      map.put(FormularfeldControl.EMPFAENGER,
          "Herr\nDr. Willi Wichtig\nTestgasse 1\n12345 Testenhausen");
      map.put(FormularfeldControl.BUCHUNGSDATUM, new Date());
      map.put(FormularfeldControl.ZAHLUNGSGRUND,
          "Zahlungsgrund1 Zahlungsgrund2");
      map.put(FormularfeldControl.ZAHLUNGSGRUND1, "Zahlungsgrund 1");
      map.put(FormularfeldControl.ZAHLUNGSGRUND2, "Zahlungsgrund 2");
      map.put(FormularfeldControl.BETRAG, new Double(1234));
      map.put("Betrag in Worten", GermanNumber.toString(1234));
      map.put(FormularfeldControl.ID, "444");
      map.put(FormularfeldControl.EXTERNEMITGLIEDSNUMMER, "9999");
      map.put(FormularfeldControl.ANREDE, "Herrn");
      map.put(FormularfeldControl.TITEL, "Dr.");
      map.put(FormularfeldControl.NAME, "Wichtig");
      map.put(FormularfeldControl.VORNAME, "Willi");
      map.put(FormularfeldControl.ADRESSIERUNGSZUSATZ, "Hinterhaus");
      map.put(FormularfeldControl.STRASSE, "Testgasse 1");
      map.put(FormularfeldControl.PLZ, "12345");
      map.put(FormularfeldControl.ORT, "Testenhausen");
      map.put(FormularfeldControl.ZAHLUNGSRHYTMUS, "j�hrlich");
      map.put(FormularfeldControl.BLZ, "10020030");
      map.put(FormularfeldControl.KONTO, "1234567");
      map.put(FormularfeldControl.KONTOINHABER, "Wichtig");
      map.put(FormularfeldControl.GEBURTSDATUM, new Date());
      map.put(FormularfeldControl.GESCHLECHT, "M");
      map.put(FormularfeldControl.TELEFONPRIVAT, "01234/56789");
      map.put(FormularfeldControl.TELEFONDIENSTLICH, "01234/55555");
      map.put(FormularfeldControl.HANDY, "0160/1234567");
      map.put(FormularfeldControl.EMAIL, "willi.wichtig@jverein.de");
      map.put(FormularfeldControl.EINTRITT, new Date());
      map.put(FormularfeldControl.BEITRAGSGRUPPE, "Erwachsene");
      map.put(FormularfeldControl.AUSTRITT, new Date());
      map.put(FormularfeldControl.KUENDIGUNG, new Date());
      map.put(FormularfeldControl.ZAHLUNGSWEG,
          "Abbuchung von Konto 1234567, BLZ: 10020030");
      map.put(FormularfeldControl.TAGESDATUM,
          new JVDateFormatTTMMJJJJ().format(new Date()));

      Spendenbescheinigung spb = (Spendenbescheinigung) Einstellungen
          .getDBService().createObject(Spendenbescheinigung.class, null);
      map = spb.getMap(map);
      map.put("Spendedatum", "15.12.2008");
      map.put("Buchungsdatum", new Date());
      map.put("Bescheinigungsdatum", "17.12.2008");
      map.put("Tagesdatum", new JVDateFormatTTMMJJJJ().format(new Date()));
      map.put(FormularfeldControl.BUCHUNGSDATUM, new Date());
      FormularAufbereitung fab = new FormularAufbereitung(file);
      fab.writeForm(formular, map);
      fab.showFormular();
    }
    catch (RemoteException e)
    {
      throw new ApplicationException(e);
    }
    catch (IOException e)
    {
      throw new ApplicationException(e);
    }
    catch (Exception e)
    {
      throw new ApplicationException(e);
    }
  }
}
