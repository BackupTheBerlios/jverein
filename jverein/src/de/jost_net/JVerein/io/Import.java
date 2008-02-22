/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/io/Import.java,v $
 * $Revision: 1.9 $
 * $Date: 2008/02/22 17:31:29 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: Import.java,v $
 * Revision 1.9  2008/02/22 17:31:29  jost
 * Fehlermeldung sauber ausgeben.
 *
 * Revision 1.8  2008/02/17 08:29:02  jost
 * Bugfix beim Import des Zahlungsrhytmusses
 *
 * Revision 1.7  2007/12/18 17:25:21  jost
 * Neu: Zahlungsrhytmus importieren
 *
 * Revision 1.6  2007/03/25 17:03:44  jost
 * 1. Zus�tzliche Plausibilit�ten
 * 2. Import des Zahlungsweges
 *
 * Revision 1.5  2007/03/24 20:22:19  jost
 * Bugfix. Jetzt können, wie dokumentiert, beliebige Dateinamen verwendet werden.
 *
 * Revision 1.4  2007/02/23 20:28:04  jost
 * Mail- und Webadresse im Header korrigiert.
 *
 * Revision 1.3  2007/01/14 12:42:42  jost
 * Java 1.5-Kompatibilität
 *
 * Revision 1.2  2006/10/23 19:09:06  jost
 * Import optimiert
 *
 * Revision 1.1  2006/09/20 15:39:24  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.JVerein.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.gui.input.ZahlungswegInput;
import de.jost_net.JVerein.rmi.Beitragsgruppe;
import de.jost_net.JVerein.rmi.Mitglied;
import de.willuhn.datasource.rmi.DBIterator;
import de.willuhn.util.ApplicationException;
import de.willuhn.util.ProgressMonitor;

public class Import
{
  public Import(String path, String file, ProgressMonitor monitor)
  {
    try
    {
      BufferedReader rea = new BufferedReader(new InputStreamReader(
          new FileInputStream(path + "/" + file), "ISO-8859-1"));
      String line = "";
      boolean abbruch = false;
      while ((line = rea.readLine()) != null)
      {
        int pos = line.indexOf("\"");
        if (pos >= 0)
        {
          monitor.log("Zeile enth�lt Anf�hrungszeichen: " + line);
          abbruch = true;
        }
      }
      rea.close();
      if (abbruch)
      {
        monitor.log("Abbruch");
        return;
      }
      loescheBestand();
      int anz = 0;
      Properties props = new java.util.Properties();
      props.put("separator", ";"); // separator is a bar
      props.put("suppressHeaders", "false"); // first line contains data
      props.put("charset", "ISO-8859-1");
      int pos = file.lastIndexOf('.');
      props.put("fileExtension", file.substring(pos));

      // load the driver into memory
      Class.forName("org.relique.jdbc.csv.CsvDriver");

      // create a connection. The first command line parameter is assumed to
      // be the directory in which the .csv files are held
      Connection conn = DriverManager.getConnection("jdbc:relique:csv:" + path,
          props);

      // create a Statement object to execute the query with
      Statement stmt = conn.createStatement();

      ResultSet results = stmt.executeQuery("SELECT * FROM "
          + file.substring(0, pos));
      while (results.next())
      {
        String ba = results.getString("Beitragsart_1");
        String btr = results.getString("Beitrag_1");
        if (ba == null || ba.length() == 0 || btr == null || btr.length() == 0)
        {
          monitor
              .log(results.getString("Nachname") + ", "
                  + results.getString("Vorname")
                  + " keine Angaben zur Beitragsart");
          abbruch = true;
        }
      }
      if (abbruch)
      {
        monitor.log("Abbruch");
        return;
      }

      results = stmt.executeQuery("SELECT * FROM " + file.substring(0, pos));

      HashMap<String, Double> beitragsgruppen1 = new HashMap<String, Double>();

      while (results.next())
      {
        beitragsgruppen1.put(results.getString("Beitragsart_1"), new Double(
            results.getString("Beitrag_1").replace(',', '.')));
      }
      Set keys = beitragsgruppen1.keySet();
      Iterator it = keys.iterator();
      HashMap<String, String> beitragsgruppen2 = new HashMap<String, String>();
      while (it.hasNext())
      {
        Beitragsgruppe b = (Beitragsgruppe) Einstellungen.getDBService()
            .createObject(Beitragsgruppe.class, null);
        String key = (String) it.next();
        b.setBezeichnung(key);
        Double betr = beitragsgruppen1.get(key);
        b.setBetrag(betr.doubleValue());
        b.store();
        beitragsgruppen2.put(key, b.getID());
      }

      results = stmt.executeQuery("SELECT * FROM " + file.substring(0, pos));

      while (results.next())
      {
        anz++;
        monitor.setStatus(anz);
        monitor.log("ID= " + results.getString("Mitglieds_Nr") + " NAME= "
            + results.getString("Nachname"));

        Mitglied m = (Mitglied) Einstellungen.getDBService().createObject(
            Mitglied.class, null);

        m.setID(results.getString("Mitglieds_Nr"));
        m.setAnrede(results.getString("Anrede"));
        m.setTitel(results.getString("Titel"));
        m.setName(results.getString("Nachname"));
        m.setVorname(results.getString("Vorname"));
        try
        {
          m.setStrasse(results.getString("Stra�e"));

        }
        catch (Exception e)
        {
          m.setStrasse(results.getString("Strasse"));
        }

        m.setPlz(results.getString("Plz"));
        m.setOrt(results.getString("Ort"));
        m.setGeburtsdatum(results.getString("Geburtsdatum"));
        m.setGeschlecht(results.getString("Geschlecht"));
        if (results.getString("Zahlungsart").equals("l"))
        {
          m.setZahlungsweg(ZahlungswegInput.ABBUCHUNG);
          m.setBlz(results.getString("Bankleitzahl"));
          m.setKonto(results.getString("Kontonummer"));
        }
        else if (results.getString("Zahlungsart").equals("b"))
        {
          m.setZahlungsweg(ZahlungswegInput.BARZAHLUNG);
        }
        else
        {
          monitor.log(m.getNameVorname()
              + " ung�ltige Zahlungsart. Bar wird angenommen.");
          m.setZahlungsweg(ZahlungswegInput.BARZAHLUNG);
        }
        String zahlungsrhytmus = "12";
        try
        {
          results.getString("Zahlungsrhytmus");
        }
        catch (SQLException e)
        {
          // Nichts tun
        }
        m.setZahlungsrhytmus(Integer.parseInt(zahlungsrhytmus));
        m.setKontoinhaber(results.getString("Zahler"));
        m.setTelefonprivat(results.getString("Telefon_privat"));
        m.setTelefondienstlich(results.getString("Telefon_dienstlich"));
        m.setEmail(results.getString("Email"));
        String eintritt = results.getString("Eintritt");
        if (eintritt == null || eintritt.length() == 0
            || eintritt.equals("00.00.0000"))
        {
          eintritt = "01.01.1900";
        }
        m.setEintritt(eintritt);
        Integer bg = new Integer(beitragsgruppen2.get(results
            .getString("Beitragsart_1")));
        m.setBeitragsgruppe(bg);
        // beitragsart.setValue(results.getString("Beitragsart_1"));
        String austritt = results.getString("Austritt");
        if (austritt.equals("00.00.0000"))
        {
          austritt = null;
        }
        m.setAustritt(austritt);
        String kuendigung;

        try
        {
          kuendigung = results.getString("K�ndigung");
        }
        catch (Exception e)
        {
          kuendigung = results.getString("Kuendigung");
        }
        if (kuendigung.equals("00.00.0000"))
        {
          kuendigung = null;
        }
        m.setKuendigung(kuendigung);
        m.insert();
      }

      // clean up
      results.close();
      stmt.close();
      conn.close();
    }
    catch (Exception e)
    {
      monitor.log(" nicht importiert: " + e.getMessage());
    }
  }

  private void loescheBestand()
  {
    try
    {
      DBIterator list = Einstellungen.getDBService().createList(Mitglied.class);
      while (list.hasNext())
      {
        Mitglied m = (Mitglied) list.next();
        m.delete();
      }
      list = Einstellungen.getDBService().createList(Beitragsgruppe.class);
      while (list.hasNext())
      {
        Beitragsgruppe b = (Beitragsgruppe) list.next();
        b.delete();
      }

    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    catch (ApplicationException e)
    {
      e.printStackTrace();
    }
  }
}
