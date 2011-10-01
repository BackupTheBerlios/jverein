/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/rmi/Einstellung.java,v $
 * $Revision: 1.31 $
 * $Date: 2011/10/01 21:49:39 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/
package de.jost_net.JVerein.rmi;

import java.rmi.RemoteException;
import java.util.Date;

import de.willuhn.datasource.rmi.DBObject;

public interface Einstellung extends DBObject
{
  public String getID() throws RemoteException;

  public void setID() throws RemoteException;

  public String getName() throws RemoteException;

  public void setName(String name) throws RemoteException;

  public String getStrasse() throws RemoteException;

  public void setStrasse(String strasse) throws RemoteException;

  public String getPlz() throws RemoteException;

  public void setPlz(String plz) throws RemoteException;

  public String getOrt() throws RemoteException;

  public void setOrt(String ort) throws RemoteException;

  public String getFinanzamt() throws RemoteException;

  public void setFinanzamt(String finanzamt) throws RemoteException;

  public String getSteuernummer() throws RemoteException;

  public void setSteuernummer(String steuernummer) throws RemoteException;

  public Date getBescheiddatum() throws RemoteException;

  public void setBescheiddatum(Date bescheiddatum) throws RemoteException;

  public Boolean getVorlaeufig() throws RemoteException;

  public void setVorlaeufig(Boolean vorlaeufig) throws RemoteException;

  public Date getVorlaeufigab() throws RemoteException;

  public void setVorlaeufigab(Date vorlaeufigab) throws RemoteException;

  public String getBeguenstigterzweck() throws RemoteException;

  public void setBeguenstigterzweck(String beguenstigterzweck)
      throws RemoteException;

  public Boolean getMitgliedsbetraege() throws RemoteException;

  public void setMitgliedsbeitraege(Boolean mitgliedsbeitraege)
      throws RemoteException;

  public String getBlz() throws RemoteException;

  public void setBlz(String blz) throws RemoteException;

  public String getKonto() throws RemoteException;

  public void setKonto(String konto) throws RemoteException;

  public Boolean getGeburtsdatumPflicht() throws RemoteException;

  public void setGeburtsdatumPflicht(Boolean geburtsdatumpflicht)
      throws RemoteException;

  public Boolean getEintrittsdatumPflicht() throws RemoteException;

  public void setEintrittsdatumPflicht(Boolean eintrittsdatumpflicht)
      throws RemoteException;

  public Boolean getSterbedatum() throws RemoteException;

  public void setSterbedatum(Boolean sterbedatum) throws RemoteException;

  public Boolean getKommunikationsdaten() throws RemoteException;

  public void setKommunikationsdaten(Boolean kommunikationsdaten)
      throws RemoteException;

  public Boolean getZusatzbetrag() throws RemoteException;

  public void setZusatzbetrag(Boolean zusatzbetrag) throws RemoteException;

  public Boolean getVermerke() throws RemoteException;

  public void setVermerke(Boolean vermerke) throws RemoteException;

  public Boolean getWiedervorlage() throws RemoteException;

  public void setWiedervorlage(Boolean wiedervorlage) throws RemoteException;

  public Boolean getKursteilnehmer() throws RemoteException;

  public void setKursteilnehmer(Boolean kursteilnehmer) throws RemoteException;

  public Boolean getLehrgaenge() throws RemoteException;

  public void setJuristischePersonen(Boolean juristischePersonen)
      throws RemoteException;

  public Boolean getJuristischePersonen() throws RemoteException;

  public void setMitgliedskonto(Boolean mitgliedskonto) throws RemoteException;

  public Boolean getMitgliedskonto() throws RemoteException;

  public void setMitgliedfoto(Boolean mitgliedfoto) throws RemoteException;

  public Boolean getMitgliedfoto() throws RemoteException;

  public void setZusatzadressen(Boolean zusatzadressen) throws RemoteException;

  public Boolean getZusatzadressen() throws RemoteException;

  public void setAuslandsadressen(Boolean auslandsadressen)
      throws RemoteException;

  public Boolean getAuslandsadressen() throws RemoteException;

  public void setArbeitseinsatz(Boolean arbeitseinsatz) throws RemoteException;

  public Boolean getArbeitseinsatz() throws RemoteException;

  public void setDokumentenspeicherung(Boolean dokumentenspeicherung)
      throws RemoteException;

  public Boolean getDokumentenspeicherung() throws RemoteException;

  public void setIndividuelleBeitraege(Boolean individuellebeitraege)
      throws RemoteException;

  public Boolean getIndividuelleBeitraege() throws RemoteException;

  public void setRechnungTextAbbuchung(String rechnungtextabbuchung)
      throws RemoteException;

  public String getRechnungTextAbbuchung() throws RemoteException;

  public void setRechnungTextUeberweisung(String rechnungtextueberweisung)
      throws RemoteException;

  public String getRechnungTextUeberweisung() throws RemoteException;

  public void setRechnungTextBar(String rechnungtextbar) throws RemoteException;

  public String getRechnungTextBar() throws RemoteException;

  public void setLehrgaenge(Boolean lehrgaenge) throws RemoteException;

  public Boolean getExterneMitgliedsnummer() throws RemoteException;

  public void setExterneMitgliedsnummer(Boolean externemitgliedsnummer)
      throws RemoteException;

  public int getBeitragsmodel() throws RemoteException;

  public void setBeitragsmodel(int beitragsmodel) throws RemoteException;

  public String getDateinamenmuster() throws RemoteException;

  public void setDateinamenmuster(String dateinamenmuster)
      throws RemoteException;

  public String getBeginnGeschaeftsjahr() throws RemoteException;

  public void setBeginnGeschaeftsjahr(String beginngeschaeftsjahr)
      throws RemoteException;

  public String getSmtpServer() throws RemoteException;

  public void setSmtpServer(String smtp_server) throws RemoteException;

  public String getSmtpPort() throws RemoteException;

  public void setSmtpPort(String smtp_port) throws RemoteException;

  public String getSmtpAuthUser() throws RemoteException;

  public void setSmtpAuthUser(String smtp_auth_user) throws RemoteException;

  public String getSmtpAuthPwd() throws RemoteException;

  public void setSmtpAuthPwd(String smtp_auth_pwd) throws RemoteException;

  public String getSmtpFromAddress() throws RemoteException;

  public void setSmtpFromAddress(String smtp_from_address)
      throws RemoteException;

  public Boolean getSmtpSsl() throws RemoteException;

  public void setSmtpSsl(Boolean smtp_ssl) throws RemoteException;

  public Boolean getSmtpStarttls() throws RemoteException;

  public void setSmtpStarttls(Boolean smtp_starttls) throws RemoteException;

  public int getZahlungsrhytmus() throws RemoteException;

  public void setZahlungsrhytmus(int zahlungsrhytmus) throws RemoteException;

  public int getZahlungsweg() throws RemoteException;

  public void setZahlungsweg(int zahlungsweg) throws RemoteException;

  public String getAltersgruppen() throws RemoteException;

  public void setAltersgruppen(String altersgruppen) throws RemoteException;

  public String getJubilaeen() throws RemoteException;

  public void setJubilaeen(String jubilaeen) throws RemoteException;

  public String getAltersjubilaeen() throws RemoteException;

  public void setAltersjubilaeen(String altersjubilaeen) throws RemoteException;

  public int getDelaytime() throws RemoteException;

  public void setDelaytime(int delaytime) throws RemoteException;

  public boolean hasZusatzfelder() throws RemoteException;
}
