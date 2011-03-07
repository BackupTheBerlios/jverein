/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein.xmlrpc/src/de/jost_net/JVerein/xmlrpc/server/EinstellungServiceImpl.java,v $
 * $Revision: 1.2 $
 * $Date: 2011/03/07 21:12:25 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: EinstellungServiceImpl.java,v $
 * Revision 1.2  2011/03/07 21:12:25  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.JVerein.xmlrpc.server;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.rmi.Einstellung;
import de.jost_net.JVerein.xmlrpc.rmi.EinstellungService;

/**
 * Implementierung des Einstellungs-Services.
 */
public class EinstellungServiceImpl extends AbstractServiceImpl implements
    EinstellungService
{
  private static final long serialVersionUID = 6930948263993044492L;

  public static final String AKTUELLEGEBURTSTAGENACHHER = "einstellung.aktuellegeburtstagenachher";

  public static final String AKTUELLEGEBURTSTAGEVORHER = "einstellung.aktuellegeburtstagevorher";

  public static final String ARBEITSEINSATZ = "einstellung.arbeitseinsatz";

  public static final String AUSLANDSADRESSEN = "einstellung.auslandsadressen";

  public static final String BEGINNGESCHAEFTSJAHR = "einstellung.beginngeschaeftsjahr";

  public static final String BEITRAGSMODEL = "einstellung.beitragsmodel";

  public static final String BLZ = "einstellung.blz";

  public static final String DATEINAMENMUSTER = "einstellung.dateinamenmuster";

  public static final String EINTRITTSDATUMPFLICHT = "einstellung.eintrittsdatumpflicht";

  public static final String EXTERNEMITGLIEDSNUMMER = "einstellung.externemitgliedsnummer";

  public static final String GEBURTSDATUMPFLICHT = "einstellung.geburtsdatumpflicht";

  public static final String JURISTISCHEPERSONEN = "einstellung.juristischepersonen";

  public static final String KOMMUNIKATIONSDATEN = "einstellung.kommunikationsdaten";

  public static final String KONTO = "einstellung.konto";

  public static final String KURSTEILNEHMER = "einstellung.kursteilnehmer";

  public static final String LEHRGAENGE = "einstellung.lehrgaenge";

  public static final String MITGLIEDSFOTO = "einstellung.mitgliedsfoto";

  public static final String MITGLIEDSKONTO = "einstellung.mitgliedskonto";

  public static final String NAME = "einstellung.name";

  public static final String RECHNUNGTEXTABBUCHUNG = "einstellung.rechnungtextabbuchung";

  public static final String RECHNUNGTEXTBAR = "einstellung.rechnungtextabbuchung";

  public static final String RECHNUNGTEXTUEBERWEISUNG = "einstellung.rechnungtextueberweisung";

  public static final String SMTPAUTHPWD = "einstellung.smtpauthpwd";

  public static final String SMTPAUTHUSER = "einstellung.smtpauthuser";

  public static final String SMTPFROMADDRESS = "einstellung.smtpfromaddress";

  public static final String SMTPPORT = "einstellung.smtpport";

  public static final String SMTPSERVER = "einstellung.smtpserver";

  public static final String SMTPSSL = "einstellung.ssl";

  public static final String VERMERKE = "einstellung.vermerke";

  public static final String WIEDERVORLAGE = "einstellung.wiedervorlage";

  public static final String ZAHLUNGSRHYTMUS = "einstellung.zahlungsrhytmus";

  public static final String ZAHLUNGSWEG = "einstellung.zahlungsweg";

  public static final String ZUSATZBETRAG = "einstellung.zusatzbetrag";

  public EinstellungServiceImpl() throws RemoteException
  {
    super();
  }

  public Map<String, Object> read() throws RemoteException
  {
    Einstellung einst = Einstellungen.getEinstellung();
    Map<String, Object> retval = new HashMap<String, Object>();
    retval.put(AKTUELLEGEBURTSTAGENACHHER,
        einst.getAktuelleGeburtstageNachher());
    retval.put(AKTUELLEGEBURTSTAGEVORHER, einst.getAktuelleGeburtstageVorher());
    retval.put(ARBEITSEINSATZ, einst.getArbeitseinsatz());
    retval.put(AUSLANDSADRESSEN, einst.getAuslandsadressen());
    retval.put(BEGINNGESCHAEFTSJAHR, einst.getBeginnGeschaeftsjahr());
    retval.put(BEITRAGSMODEL, einst.getBeitragsmodel());
    retval.put(BLZ, einst.getBlz());
    retval.put(DATEINAMENMUSTER, einst.getDateinamenmuster());
    retval.put(EINTRITTSDATUMPFLICHT, einst.getEintrittsdatumPflicht());
    retval.put(EXTERNEMITGLIEDSNUMMER, einst.getExterneMitgliedsnummer());
    retval.put(GEBURTSDATUMPFLICHT, einst.getGeburtsdatumPflicht());
    retval.put(JURISTISCHEPERSONEN, einst.getJuristischePersonen());
    retval.put(KOMMUNIKATIONSDATEN, einst.getKommunikationsdaten());
    retval.put(KONTO, einst.getKonto());
    retval.put(KURSTEILNEHMER, einst.getKursteilnehmer());
    retval.put(LEHRGAENGE, einst.getLehrgaenge());
    retval.put(MITGLIEDSFOTO, einst.getMitgliedfoto());
    retval.put(MITGLIEDSKONTO, einst.getMitgliedskonto());
    retval.put(NAME, einst.getName());
    retval.put(RECHNUNGTEXTABBUCHUNG, einst.getRechnungTextAbbuchung());
    retval.put(RECHNUNGTEXTBAR, einst.getRechnungTextBar());
    retval.put(RECHNUNGTEXTUEBERWEISUNG, einst.getRechnungTextUeberweisung());
    retval.put(SMTPAUTHPWD, einst.getSmtpAuthPwd());
    retval.put(SMTPAUTHUSER, einst.getSmtpAuthUser());
    retval.put(SMTPFROMADDRESS, einst.getSmtpFromAddress());
    retval.put(SMTPPORT, einst.getSmtpPort());
    retval.put(SMTPSERVER, einst.getSmtpServer());
    retval.put(SMTPSSL, einst.getSmtpSsl());
    retval.put(VERMERKE, einst.getVermerke());
    retval.put(WIEDERVORLAGE, einst.getWiedervorlage());
    retval.put(ZAHLUNGSRHYTMUS, einst.getZahlungsrhytmus());
    retval.put(ZAHLUNGSWEG, einst.getZahlungsweg());
    retval.put(ZUSATZBETRAG, einst.getZusatzbetrag());
    return retval;
  }

  /**
   * @see de.willuhn.datasource.Service#getName()
   */
  public String getName() throws RemoteException
  {
    return "[xml-rpc] einstellung";
  }
}
