/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/DokumentationUtil.java,v $
 * $Revision: 1.6 $
 * $Date: 2008/06/28 16:59:18 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: DokumentationUtil.java,v $
 * Revision 1.6  2008/06/28 16:59:18  jost
 * Neu: Jahresabschluss
 *
 * Revision 1.5  2008/05/25 19:36:42  jost
 * Neu: Jahressaldo
 *
 * Revision 1.4  2008/05/24 14:04:08  jost
 * Redatkionelle Änderung
 *
 * Revision 1.3  2008/05/22 06:53:26  jost
 * Buchführung
 *
 * Revision 1.2  2008/04/10 18:59:25  jost
 * Neu: Benutzerdefinierte Datenfelder
 *
 * Revision 1.1  2008/01/01 19:50:45  jost
 * Erweiterung um Hilfe-Funktion
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

public class DokumentationUtil
{
  public DokumentationUtil()
  {

  }

  private static final String PRE = "http://www.jverein.de/index.php5?title=";

  public static final String ABBUCHUNG = PRE + "Abbuchung";

  public static final String AUSWERTUNGKURSTEILNEHMER = PRE
      + "Auswertung#Auswertung_der_Kursteilnehmer";

  public static final String AUSWERTUNGMITGLIEDER = PRE
      + "Auswertung#Auswertung_der_Mitglieder";

  public static final String BEITRAGSGRUPPEN = PRE
      + "Administration#Beitragsgruppen";

  public static final String FELDDEFINITIONEN = PRE
      + "Administration#Felddefinitionen";

  public static final String EINSTELLUNGEN = PRE
      + "Administration#Einstellungen";

  public static final String IMPORT = PRE + "Administration#Import";

  public static final String JUBILAEEN = PRE + "Auswertung#Jubil.C3.A4en";

  public static final String MITGLIED = PRE + "Mitglieder";

  public static final String KURSTEILNEHMER = PRE + "Kursteilnehmer";

  public static final String MANUELLEZAHLUNGSEINGAENGE = PRE
      + "Manuelle_Zahlungseing%C3%A4nge";

  public static final String STAMMDATEN = PRE
      + "Administration#Stammdaten_des_Vereins";

  public static final String STATISTIKMITGLIEDER = PRE + "Auswertung#Statistik";

  public static final String WIEDERVORLAGE = PRE + "Wiedervorlage";

  public static final String ZUSATZABBUCHUNGEN = PRE + "Zusatzabbuchungen";

  public static final String KONTEN = PRE + "Konten";

  public static final String ANFANGSBESTAENDE = PRE + "Anfangsbest�nde";

  public static final String BUCHUNGENAUSHIBISCUS = PRE
      + "Buchungen_aus_Hibiscus_�bernehmen";

  public static final String BUCHUNGEN = PRE + "Buchungen";

  public static final String JAHRESSALDO = PRE + "Jahressaldo";

  public static final String JAHRESABSCHLUSS = PRE + "Jahresabschluss";
}
