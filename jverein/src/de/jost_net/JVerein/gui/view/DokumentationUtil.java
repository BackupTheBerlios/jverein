/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/DokumentationUtil.java,v $
 * $Revision: 1.20 $
 * $Date: 2010/04/03 11:31:51 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: DokumentationUtil.java,v $
 * Revision 1.20  2010/04/03 11:31:51  jost
 * Bugfix
 *
 * Revision 1.19  2010/02/01 21:00:24  jost
 * Neu: Einfache Mailfunktion
 *
 * Revision 1.18  2009/12/11 22:34:18  jost
 * Link korrigiert.
 *
 * Revision 1.17  2009/11/17 20:59:38  jost
 * Neu: Eigenschaft und EigenschaftGruppe
 *
 * Revision 1.16  2009/10/17 19:46:30  jost
 * *** empty log message ***
 *
 * Revision 1.15  2009/09/10 18:18:57  jost
 * neu: Buchungsklassen
 *
 * Revision 1.14  2009/08/19 20:59:46  jost
 * Link Buchungsarten aufgenommen.
 *
 * Revision 1.13  2009/08/17 18:52:12  jost
 * Neuer Pfad
 *
 * Revision 1.12  2009/08/09 15:28:14  jost
 * Umstellung auf neue Web-Site
 *
 * Revision 1.11  2009/04/13 11:40:14  jost
 * Neu: Lehrg�nge
 *
 * Revision 1.10  2009/03/26 21:02:24  jost
 * Neu: Adressbuchexport
 *
 * Revision 1.9  2008/09/16 18:52:08  jost
 * Neu: Rechnung
 *
 * Revision 1.8  2008/08/10 12:36:12  jost
 * Abbuchung -> Abrechnung
 * Vorbereitung der Rechnungserstellung
 *
 * Revision 1.7  2008/07/18 20:12:14  jost
 * Neu: Formulare und Spendenbescheinigung
 *
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

  private static final String PRE = "http://www.jverein.de/";

  public static final String ADRESSBUCHEXPORT = PRE
      + "dokumentationauswertungadressbuchexport.php";

  public static final String ABRECHNUNG = PRE + "abrechnung.php";

  public static final String RECHNUNG = PRE + "rechnungen.php";

  public static final String AUSWERTUNGKURSTEILNEHMER = PRE
      + "dokumentationauswertungkursteilnehmer.php";

  public static final String AUSWERTUNGMITGLIEDER = PRE
      + "dokumentationauswertungmitglieder.php";

  public static final String BEITRAGSGRUPPEN = PRE
      + "administration_beitragsgruppen.php";

  public static final String BUCHUNGSARTEN = PRE
      + "administration_buchungsarten.php";

  public static final String BUCHUNGSKLASSEN = PRE
      + "administration_buchungsklasse.php";

  public static final String EIGENSCHAFT = PRE
      + "administration_eigenschaft.php";

  public static final String EIGENSCHAFTGRUPPE = PRE
      + "administration_eigenschaftgruppe.php";

  public static final String FELDDEFINITIONEN = PRE
      + "administration_felddefinitionen.php";

  public static final String FORMULARE = PRE + "administration_formulare.php";

  public static final String EINSTELLUNGEN = PRE
      + "administration_einstellungen.php";

  public static final String IMPORT = PRE + "administration_import.php";

  public static final String JUBILAEEN = PRE
      + "dokumentationauswertungjubilaeen.php";

  public static final String LEHRGANG = PRE + "lehrgaenge.php";

  public static final String MAIL = PRE + "mail.php";

  public static final String MITGLIED = PRE + "mitglied.php";

  public static final String KURSTEILNEHMER = PRE + "kursteilnehmer.php";

  public static final String MANUELLEZAHLUNGSEINGAENGE = PRE
      + "manuelle_zahlungseingaenge.php";

  public static final String STAMMDATEN = PRE + "administration_stammdaten.php";

  public static final String STATISTIKMITGLIEDER = PRE
      + "dokumentationauswertungstatistik.php";

  public static final String WIEDERVORLAGE = PRE + "wiedervorlage.php";

  public static final String ZUSATZBETRAEGE = PRE + "zusatzbetraege.php";

  public static final String ZUSATZBETRAEGEIMPORT = PRE
      + "zusatzbetraegeimport.php";

  public static final String KONTEN = PRE
      + "dokumentationbuchfuehrungkonten.php";

  public static final String ANFANGSBESTAENDE = PRE
      + "dokumentationbuchfuehrunganfangsbestaende.php";

  public static final String BUCHUNGENAUSHIBISCUS = PRE
      + "dokumentationbuchfuehrungbuchungenaushibiscusuebernehmen.php";

  public static final String BUCHUNGEN = PRE
      + "dokumentationbuchfuehrungbuchungenaushibiscusuebernehmen.php";

  public static final String JAHRESSALDO = PRE
      + "dokumentationbuchfuehrungjahressaldo.php";

  public static final String JAHRESABSCHLUSS = PRE
      + "dokumentationbuchfuehrungjahresabschluss.php";

  public static final String SPENDENBESCHEINIGUNG = PRE
      + "spendenbescheinigung.php";
}
