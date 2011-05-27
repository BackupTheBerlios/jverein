/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/Variable/MitgliedVar.java,v $
 * $Revision: 1.3 $
 * $Date: 2011/05/27 18:49:30 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * heiner@jverein.de
 * www.jverein.de
 * All rights reserved
 * $Log: MitgliedVar.java,v $
 * Revision 1.3  2011/05/27 18:49:30  jost
 * Vereinheitlichung Variable
 *
 * Revision 1.2  2011-05-22 07:41:22  jost
 * CSV-Export �berarbeitet. Neue Spaltennamen. Zus�tzliche Spalten.
 *
 * Revision 1.1  2011-05-06 15:04:04  jost
 * Neue Variablenmimik
 *
 **********************************************************************/
package de.jost_net.JVerein.Variable;

public enum MitgliedVar
{
  ADRESSIERUNGSZUSATZ("mitglied_adressierungszusatz"), //
  ADRESSTYP("mitglied_adresstyp"), //
  ANREDE("mitglied_anrede"), //
  AUSTRITT("mitglied_austritt"), //
  BEITRAGSGRUPPE_ARBEITSEINSATZ_BETRAG("mitglied_arbeitseinsatz_betrag"), //
  BEITRAGSGRUPPE_ARBEITSEINSATZ_STUNDEN("mitglied_arbeitseinsatz_stunden"), //
  BEITRAGSGRUPPE_BEZEICHNUNG("mitglied_beitragsgruppe_bezeichnung"), //
  BEITRAGSGRUPPE_BETRAG("mitglied_beitragsgruppe_betrag"), //
  BEITRAGSGRUPPE_ID("mitglied_beitragsgruppe.id"), //
  BLZ("mitglied_blz"), //
  EINTRITT("mitglied_eintritt"), //
  EINGABEDATUM("mitglied_eingabedatum"), //
  EMPFAENGER("mitglied_empfaenger"), //
  EMAIL("mitglied_email"), //
  EXTERNE_MITGLIEDSNUMMER("mitglied_externe_mitgliedsnummer"), //
  GEBURTSDATUM("mitglied_geburtsdatum"), //
  GESCHLECHT("mitglied_geschlecht"), //
  HANDY("mitglied_handy"), //
  IBAN("mitglied_iban"), //
  ID("mitglied_id"), //
  INDIVIDUELLERBEITRAG("mitglied_individuellerbeitrag"), //
  KONTO("mitglied_konto"), //
  KONTOINHABER("mitglied_kontoinhaber"), //
  KUENDIGUNG("mitglied_kuendigung"), //
  LETZTEAENDERUNG("mitglied_letzte.aenderung"), //
  NAME("mitglied_name"), //
  NAMEVORNAME("mitglied_namevorname"), //
  ORT("mitglied_ort"), //
  PERSONENART("mitglied_personenart"), //
  PLZ("mitglied_plz"), //
  STAAT("mitglied_staat"), //
  STERBETAG("mitglied_sterbetag"), //
  STRASSE("mitglied_strasse"), //
  TELEFONDIENSTLICH("mitglied_telefon.dienstlich"), //
  TELEFONPRIVAT("mitglied_telefon.privat"), //
  TITEL("mitglied_titel"), //
  VERMERK1("mitglied_vermerk1"), //
  VERMERK2("mitglied_vermerk2"), //
  VORNAME("mitglied_vorname"), //
  VORNAMENAME("mitglied_vornamename"), //
  ZAHLUNGSRHYTMUS("mitglied_zahlungsrhytmus"), //
  ZAHLUNGSWEG("mitglied_zahlungsweg"), //
  ZAHLUNGSWEGTEXT("mitglied_zahlungsweg_text"), //
  ZAHLERID("mitglied_zahlerid");

  private String name;

  MitgliedVar(String name)
  {
    this.name = name;
  }

  public String getName()
  {
    return name;
  }
}
