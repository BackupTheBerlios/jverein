/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/control/EinstellungControl.java,v $
 * $Revision: 1.48 $
 * $Date: 2011/10/01 21:42:57 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/
package de.jost_net.JVerein.gui.control;

import java.rmi.RemoteException;
import java.util.Date;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.keys.Beitragsmodel;
import de.jost_net.JVerein.keys.Zahlungsrhytmus;
import de.jost_net.JVerein.keys.Zahlungsweg;
import de.jost_net.JVerein.rmi.Einstellung;
import de.jost_net.JVerein.util.MitgliedSpaltenauswahl;
import de.willuhn.jameica.gui.AbstractControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.input.CheckboxInput;
import de.willuhn.jameica.gui.input.DateInput;
import de.willuhn.jameica.gui.input.Input;
import de.willuhn.jameica.gui.input.IntegerInput;
import de.willuhn.jameica.gui.input.PasswordInput;
import de.willuhn.jameica.gui.input.SelectInput;
import de.willuhn.jameica.gui.input.TextInput;
import de.willuhn.jameica.gui.parts.TablePart;
import de.willuhn.jameica.system.Settings;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

public class EinstellungControl extends AbstractControl
{

  private Input name;

  private Input strasse;

  private Input plz;

  private Input ort;

  private TextInput finanzamt;

  private TextInput steuernummer;

  private DateInput bescheiddatum;

  private CheckboxInput vorlaeufig;

  private DateInput vorlaeufigab;

  private TextInput beguenstigterzweck;

  private CheckboxInput mitgliedsbetraege;

  private Input blz;

  private Input konto;

  private CheckboxInput geburtsdatumpflicht;

  private CheckboxInput eintrittsdatumpflicht;

  private CheckboxInput sterbedatum;

  private CheckboxInput kommunikationsdaten;

  private CheckboxInput zusatzbetrag;

  private CheckboxInput vermerke;

  private CheckboxInput wiedervorlage;

  private CheckboxInput kursteilnehmer;

  private CheckboxInput lehrgaenge;

  private CheckboxInput juristischepersonen;

  private CheckboxInput mitgliedskonto;

  private CheckboxInput mitgliedfoto;

  private CheckboxInput zusatzadressen;

  private CheckboxInput auslandsadressen;

  private CheckboxInput arbeitseinsatz;

  private CheckboxInput dokumentenspeicherung;

  private CheckboxInput individuellebeitraege;

  private TextInput rechnungtextabbuchung;

  private TextInput rechnungtextueberweisung;

  private TextInput rechnungtextbar;

  private CheckboxInput externemitgliedsnummer;

  private SelectInput beitragsmodel;

  private TextInput dateinamenmuster;

  private TextInput beginngeschaeftsjahr;

  private TextInput smtp_server;

  private IntegerInput smtp_port;

  private TextInput smtp_auth_user;

  private PasswordInput smtp_auth_pwd;

  private TextInput smtp_from_address;

  private CheckboxInput smtp_ssl;

  private CheckboxInput smtp_starttls;

  private SelectInput zahlungsweg;

  private SelectInput zahlungsrhytmus;

  private Input altersgruppen;

  private Input jubilaeen;

  private Input altersjubilaeen;

  private IntegerInput delaytime;

  private Settings settings;

  private MitgliedSpaltenauswahl spalten;

  public EinstellungControl(AbstractView view)
  {
    super(view);
    settings = new Settings(this.getClass());
    settings.setStoreWhenRead(true);
  }

  public Input getName(boolean withFocus) throws RemoteException
  {
    if (name != null)
    {
      return name;
    }
    name = new TextInput(Einstellungen.getEinstellung().getName(), 27);
    name.setMandatory(true);
    if (withFocus)
    {
      name.focus();
    }
    return name;
  }

  public Input getStrasse() throws RemoteException
  {
    if (strasse != null)
    {
      return strasse;
    }
    strasse = new TextInput(Einstellungen.getEinstellung().getStrasse(), 30);
    return strasse;
  }

  public Input getPlz() throws RemoteException
  {
    if (plz != null)
    {
      return plz;
    }
    plz = new TextInput(Einstellungen.getEinstellung().getPlz(), 5);
    return plz;
  }

  public Input getOrt() throws RemoteException
  {
    if (ort != null)
    {
      return ort;
    }
    ort = new TextInput(Einstellungen.getEinstellung().getOrt(), 30);
    return ort;
  }

  public TextInput getFinanzamt() throws RemoteException
  {
    if (finanzamt != null)
    {
      return finanzamt;
    }
    finanzamt = new TextInput(Einstellungen.getEinstellung().getFinanzamt(), 30);
    return finanzamt;
  }

  public TextInput getSteuernummer() throws RemoteException
  {
    if (steuernummer != null)
    {
      return steuernummer;
    }
    steuernummer = new TextInput(Einstellungen.getEinstellung()
        .getSteuernummer(), 30);
    return steuernummer;
  }

  public DateInput getBescheiddatum() throws RemoteException
  {
    if (bescheiddatum != null)
    {
      return bescheiddatum;
    }
    bescheiddatum = new DateInput(Einstellungen.getEinstellung()
        .getBescheiddatum());
    return bescheiddatum;
  }

  public CheckboxInput getVorlaeufig() throws RemoteException
  {
    if (vorlaeufig != null)
    {
      return vorlaeufig;
    }
    vorlaeufig = new CheckboxInput(Einstellungen.getEinstellung()
        .getVorlaeufig());
    return vorlaeufig;
  }

  public DateInput getVorlaeufigab() throws RemoteException
  {
    if (vorlaeufigab != null)
    {
      return vorlaeufigab;
    }
    vorlaeufigab = new DateInput(Einstellungen.getEinstellung()
        .getVorlaeufigab());
    return vorlaeufigab;
  }

  public TextInput getBeguenstigterzweck() throws RemoteException
  {
    if (beguenstigterzweck != null)
    {
      return beguenstigterzweck;
    }
    beguenstigterzweck = new TextInput(Einstellungen.getEinstellung()
        .getBeguenstigterzweck(), 30);
    return beguenstigterzweck;
  }

  public CheckboxInput getMitgliedsbetraege() throws RemoteException
  {
    if (mitgliedsbetraege != null)
    {
      return mitgliedsbetraege;
    }
    mitgliedsbetraege = new CheckboxInput(Einstellungen.getEinstellung()
        .getMitgliedsbetraege());
    return mitgliedsbetraege;
  }

  public Input getBlz() throws RemoteException
  {
    if (blz != null)
    {
      return blz;
    }
    blz = new TextInput(Einstellungen.getEinstellung().getBlz(), 8);
    blz.setMandatory(true);
    BLZListener l = new BLZListener();
    blz.addListener(l);
    l.handleEvent(null);
    return blz;
  }

  public Input getKonto() throws RemoteException
  {
    if (konto != null)
    {
      return konto;
    }
    konto = new TextInput(Einstellungen.getEinstellung().getKonto(), 10);
    konto.setMandatory(true);
    konto.setComment("f�r die Abbuchung");
    return konto;
  }

  public CheckboxInput getGeburtsdatumPflicht() throws RemoteException
  {
    if (geburtsdatumpflicht != null)
    {
      return geburtsdatumpflicht;
    }
    geburtsdatumpflicht = new CheckboxInput(Einstellungen.getEinstellung()
        .getGeburtsdatumPflicht());
    return geburtsdatumpflicht;
  }

  public CheckboxInput getEintrittsdatumPflicht() throws RemoteException
  {
    if (eintrittsdatumpflicht != null)
    {
      return eintrittsdatumpflicht;
    }
    eintrittsdatumpflicht = new CheckboxInput(Einstellungen.getEinstellung()
        .getEintrittsdatumPflicht());
    return eintrittsdatumpflicht;
  }

  public CheckboxInput getSterbedatum() throws RemoteException
  {
    if (sterbedatum != null)
    {
      return sterbedatum;
    }
    sterbedatum = new CheckboxInput(Einstellungen.getEinstellung()
        .getSterbedatum());
    return sterbedatum;
  }

  public CheckboxInput getKommunikationsdaten() throws RemoteException
  {
    if (kommunikationsdaten != null)
    {
      return kommunikationsdaten;
    }
    kommunikationsdaten = new CheckboxInput(Einstellungen.getEinstellung()
        .getKommunikationsdaten());
    return kommunikationsdaten;
  }

  public CheckboxInput getZusatzbetrag() throws RemoteException
  {
    if (zusatzbetrag != null)
    {
      return zusatzbetrag;
    }
    zusatzbetrag = new CheckboxInput(Einstellungen.getEinstellung()
        .getZusatzbetrag());
    return zusatzbetrag;
  }

  public CheckboxInput getVermerke() throws RemoteException
  {
    if (vermerke != null)
    {
      return vermerke;
    }
    vermerke = new CheckboxInput(Einstellungen.getEinstellung().getVermerke());
    return vermerke;
  }

  public CheckboxInput getWiedervorlage() throws RemoteException
  {
    if (wiedervorlage != null)
    {
      return wiedervorlage;
    }
    wiedervorlage = new CheckboxInput(Einstellungen.getEinstellung()
        .getWiedervorlage());
    return wiedervorlage;
  }

  public CheckboxInput getKursteilnehmer() throws RemoteException
  {
    if (kursteilnehmer != null)
    {
      return kursteilnehmer;
    }
    kursteilnehmer = new CheckboxInput(Einstellungen.getEinstellung()
        .getKursteilnehmer());
    return kursteilnehmer;
  }

  public CheckboxInput getLehrgaenge() throws RemoteException
  {
    if (lehrgaenge != null)
    {
      return lehrgaenge;
    }
    lehrgaenge = new CheckboxInput(Einstellungen.getEinstellung()
        .getLehrgaenge());
    return lehrgaenge;
  }

  public CheckboxInput getJuristischePersonen() throws RemoteException
  {
    if (juristischepersonen != null)
    {
      return juristischepersonen;
    }
    juristischepersonen = new CheckboxInput(Einstellungen.getEinstellung()
        .getJuristischePersonen());
    return juristischepersonen;
  }

  public CheckboxInput getMitgliedskonto() throws RemoteException
  {
    if (mitgliedskonto != null)
    {
      return mitgliedskonto;
    }
    mitgliedskonto = new CheckboxInput(Einstellungen.getEinstellung()
        .getMitgliedskonto());
    return mitgliedskonto;
  }

  public CheckboxInput getMitgliedfoto() throws RemoteException
  {
    if (mitgliedfoto != null)
    {
      return mitgliedfoto;
    }
    mitgliedfoto = new CheckboxInput(Einstellungen.getEinstellung()
        .getMitgliedfoto());
    return mitgliedfoto;
  }

  public CheckboxInput getZusatzadressen() throws RemoteException
  {
    if (zusatzadressen != null)
    {
      return zusatzadressen;
    }
    zusatzadressen = new CheckboxInput(Einstellungen.getEinstellung()
        .getZusatzadressen());
    return zusatzadressen;
  }

  public CheckboxInput getAuslandsadressen() throws RemoteException
  {
    if (auslandsadressen != null)
    {
      return auslandsadressen;
    }
    auslandsadressen = new CheckboxInput(Einstellungen.getEinstellung()
        .getAuslandsadressen());
    return auslandsadressen;
  }

  public CheckboxInput getArbeitseinsatz() throws RemoteException
  {
    if (arbeitseinsatz != null)
    {
      return arbeitseinsatz;
    }
    arbeitseinsatz = new CheckboxInput(Einstellungen.getEinstellung()
        .getArbeitseinsatz());
    return arbeitseinsatz;
  }

  public CheckboxInput getDokumentenspeicherung() throws RemoteException
  {
    if (dokumentenspeicherung != null)
    {
      return dokumentenspeicherung;
    }
    dokumentenspeicherung = new CheckboxInput(Einstellungen.getEinstellung()
        .getDokumentenspeicherung());
    return dokumentenspeicherung;
  }

  public CheckboxInput getIndividuelleBeitraege() throws RemoteException
  {
    if (individuellebeitraege != null)
    {
      return individuellebeitraege;
    }
    individuellebeitraege = new CheckboxInput(Einstellungen.getEinstellung()
        .getIndividuelleBeitraege());
    return individuellebeitraege;
  }

  public TextInput getRechnungTextAbbuchung() throws RemoteException
  {
    if (rechnungtextabbuchung != null)
    {
      return rechnungtextabbuchung;
    }
    rechnungtextabbuchung = new TextInput(Einstellungen.getEinstellung()
        .getRechnungTextAbbuchung(), 100);
    return rechnungtextabbuchung;
  }

  public TextInput getRechnungTextUeberweisung() throws RemoteException
  {
    if (rechnungtextueberweisung != null)
    {
      return rechnungtextueberweisung;
    }
    rechnungtextueberweisung = new TextInput(Einstellungen.getEinstellung()
        .getRechnungTextUeberweisung(), 100);
    return rechnungtextueberweisung;
  }

  public TextInput getRechnungTextBar() throws RemoteException
  {
    if (rechnungtextbar != null)
    {
      return rechnungtextbar;
    }
    rechnungtextbar = new TextInput(Einstellungen.getEinstellung()
        .getRechnungTextBar(), 100);
    return rechnungtextbar;
  }

  public CheckboxInput getExterneMitgliedsnummer() throws RemoteException
  {
    if (externemitgliedsnummer != null)
    {
      return externemitgliedsnummer;
    }
    externemitgliedsnummer = new CheckboxInput(Einstellungen.getEinstellung()
        .getExterneMitgliedsnummer());
    return externemitgliedsnummer;
  }

  public SelectInput getBeitragsmodel() throws RemoteException
  {
    if (beitragsmodel != null)
    {
      return beitragsmodel;
    }
    beitragsmodel = new SelectInput(Beitragsmodel.getArray(),
        new Beitragsmodel(Einstellungen.getEinstellung().getBeitragsmodel()));
    return beitragsmodel;
  }

  public TextInput getDateinamenmuster() throws RemoteException
  {
    if (dateinamenmuster != null)
    {
      return dateinamenmuster;
    }
    dateinamenmuster = new TextInput(Einstellungen.getEinstellung()
        .getDateinamenmuster(), 30);
    return dateinamenmuster;
  }

  public TextInput getBeginnGeschaeftsjahr() throws RemoteException
  {
    if (beginngeschaeftsjahr != null)
    {
      return beginngeschaeftsjahr;
    }
    beginngeschaeftsjahr = new TextInput(Einstellungen.getEinstellung()
        .getBeginnGeschaeftsjahr(), 6);
    return beginngeschaeftsjahr;
  }

  public TextInput getSmtpServer() throws RemoteException
  {
    if (smtp_server != null)
    {
      return smtp_server;
    }
    smtp_server = new TextInput(Einstellungen.getEinstellung().getSmtpServer(),
        50);
    return smtp_server;
  }

  public IntegerInput getSmtpPort() throws RemoteException
  {
    if (smtp_port != null)
    {
      return smtp_port;
    }
    smtp_port = new IntegerInput(new Integer(Einstellungen.getEinstellung()
        .getSmtpPort()));
    return smtp_port;
  }

  public TextInput getSmtpAuthUser() throws RemoteException
  {
    if (smtp_auth_user != null)
    {
      return smtp_auth_user;
    }
    smtp_auth_user = new TextInput(Einstellungen.getEinstellung()
        .getSmtpAuthUser(), 50);
    return smtp_auth_user;
  }

  public PasswordInput getSmtpAuthPwd() throws RemoteException
  {
    if (smtp_auth_pwd != null)
    {
      return smtp_auth_pwd;
    }
    smtp_auth_pwd = new PasswordInput(Einstellungen.getEinstellung()
        .getSmtpAuthPwd());
    return smtp_auth_pwd;
  }

  public TextInput getSmtpFromAddress() throws RemoteException
  {
    if (smtp_from_address != null)
    {
      return smtp_from_address;
    }
    smtp_from_address = new TextInput(Einstellungen.getEinstellung()
        .getSmtpFromAddress(), 50);
    return smtp_from_address;
  }

  public CheckboxInput getSmtpSsl() throws RemoteException
  {
    if (smtp_ssl != null)
    {
      return smtp_ssl;
    }
    smtp_ssl = new CheckboxInput(Einstellungen.getEinstellung().getSmtpSsl());
    return smtp_ssl;
  }

  public CheckboxInput getSmtpStarttls() throws RemoteException
  {
    if (smtp_starttls != null)
    {
      return smtp_starttls;
    }
    smtp_starttls = new CheckboxInput(Einstellungen.getEinstellung()
        .getSmtpStarttls());
    return smtp_starttls;
  }

  public SelectInput getZahlungsweg() throws RemoteException
  {
    if (zahlungsweg != null)
    {
      return zahlungsweg;
    }
    zahlungsweg = new SelectInput(Zahlungsweg.getArray(), new Zahlungsweg(
        Einstellungen.getEinstellung().getZahlungsweg()));
    zahlungsweg.setName("Standard-Zahlungsweg f. neue Mitglieder");
    return zahlungsweg;
  }

  public SelectInput getZahlungsrhytmus() throws RemoteException
  {
    if (zahlungsrhytmus != null)
    {
      return zahlungsrhytmus;
    }
    zahlungsrhytmus = new SelectInput(
        Zahlungsrhytmus.getArray(),
        new Zahlungsrhytmus(Einstellungen.getEinstellung().getZahlungsrhytmus()));
    zahlungsrhytmus.setName("Standard-Zahlungsrhytmus f. neue Mitglieder");
    return zahlungsrhytmus;
  }

  public Input getAltersgruppen() throws RemoteException
  {
    if (altersgruppen != null)
    {
      return altersgruppen;
    }
    altersgruppen = new TextInput(Einstellungen.getEinstellung()
        .getAltersgruppen(), 50);
    return altersgruppen;
  }

  public Input getJubilaeen() throws RemoteException
  {
    if (jubilaeen != null)
    {
      return jubilaeen;
    }
    jubilaeen = new TextInput(Einstellungen.getEinstellung().getJubilaeen(), 50);
    return jubilaeen;
  }

  public Input getAltersjubilaeen() throws RemoteException
  {
    if (altersjubilaeen != null)
    {
      return altersjubilaeen;
    }
    altersjubilaeen = new TextInput(Einstellungen.getEinstellung()
        .getAltersjubilaeen(), 50);
    return altersjubilaeen;
  }

  public IntegerInput getDelaytime() throws RemoteException
  {
    if (delaytime != null)
    {
      return delaytime;
    }
    delaytime = new IntegerInput(Integer.valueOf(Einstellungen.getEinstellung()
        .getDelaytime()));
    return delaytime;
  }

  public TablePart getSpaltendefinitionTable(Composite parent)
      throws RemoteException
  {
    if (spalten == null)
    {
      spalten = new MitgliedSpaltenauswahl();
    }
    return spalten.paintSpaltenpaintSpaltendefinitionTable(parent);
  }

  // public void setCheckSpalten()
  // {
  // for (int i = 0; i < spalten.size(); ++i)
  // {
  // spaltendefinitionList.setChecked(spalten.get(i), spalten.get(i)
  // .isChecked());
  // }
  // }

  public void handleStore()
  {
    try
    {
      Einstellung e = Einstellungen.getEinstellung();
      e.setID();
      e.setName((String) getName(false).getValue());
      e.setStrasse((String) getStrasse().getValue());
      e.setPlz((String) getPlz().getValue());
      e.setOrt((String) getOrt().getValue());
      e.setFinanzamt((String) getFinanzamt().getValue());
      e.setSteuernummer((String) getSteuernummer().getValue());
      e.setBescheiddatum((Date) getBescheiddatum().getValue());
      e.setVorlaeufig((Boolean) getVorlaeufig().getValue());
      e.setVorlaeufigab((Date) getVorlaeufigab().getValue());
      e.setBeguenstigterzweck((String) getBeguenstigterzweck().getValue());
      e.setMitgliedsbeitraege((Boolean) getMitgliedsbetraege().getValue());
      e.setBlz((String) getBlz().getValue());
      e.setKonto((String) getKonto().getValue());
      e.setGeburtsdatumPflicht((Boolean) geburtsdatumpflicht.getValue());
      e.setEintrittsdatumPflicht((Boolean) eintrittsdatumpflicht.getValue());
      e.setSterbedatum((Boolean) sterbedatum.getValue());
      e.setKommunikationsdaten((Boolean) kommunikationsdaten.getValue());
      e.setZusatzbetrag((Boolean) zusatzbetrag.getValue());
      e.setVermerke((Boolean) vermerke.getValue());
      e.setWiedervorlage((Boolean) wiedervorlage.getValue());
      e.setKursteilnehmer((Boolean) kursteilnehmer.getValue());
      e.setLehrgaenge((Boolean) lehrgaenge.getValue());
      e.setJuristischePersonen((Boolean) juristischepersonen.getValue());
      e.setMitgliedskonto((Boolean) mitgliedskonto.getValue());
      e.setMitgliedfoto((Boolean) mitgliedfoto.getValue());
      e.setZusatzadressen((Boolean) zusatzadressen.getValue());
      e.setAuslandsadressen((Boolean) auslandsadressen.getValue());
      e.setArbeitseinsatz((Boolean) arbeitseinsatz.getValue());
      e.setDokumentenspeicherung((Boolean) dokumentenspeicherung.getValue());
      e.setIndividuelleBeitraege((Boolean) individuellebeitraege.getValue());
      e.setRechnungTextAbbuchung((String) rechnungtextabbuchung.getValue());
      e.setRechnungTextAbbuchung((String) rechnungtextabbuchung.getValue());
      e.setRechnungTextUeberweisung((String) rechnungtextueberweisung
          .getValue());
      e.setRechnungTextBar((String) rechnungtextbar.getValue());
      e.setExterneMitgliedsnummer((Boolean) externemitgliedsnummer.getValue());
      Beitragsmodel bm = (Beitragsmodel) beitragsmodel.getValue();
      e.setBeitragsmodel(bm.getKey());
      e.setDateinamenmuster((String) dateinamenmuster.getValue());
      e.setBeginnGeschaeftsjahr((String) beginngeschaeftsjahr.getValue());
      e.setSmtpServer((String) smtp_server.getValue());
      Integer port = (Integer) smtp_port.getValue();
      e.setSmtpPort(port.toString());
      e.setSmtpAuthUser((String) smtp_auth_user.getValue());
      e.setSmtpAuthPwd((String) smtp_auth_pwd.getValue());
      e.setSmtpFromAddress((String) smtp_from_address.getValue());
      e.setSmtpSsl((Boolean) smtp_ssl.getValue());
      e.setSmtpStarttls((Boolean) smtp_starttls.getValue());
      Zahlungsrhytmus zr = (Zahlungsrhytmus) zahlungsrhytmus.getValue();
      e.setZahlungsrhytmus(zr.getKey());
      Zahlungsweg zw = (Zahlungsweg) zahlungsweg.getValue();
      e.setZahlungsweg(zw.getKey());
      e.setAltersgruppen((String) getAltersgruppen().getValue());
      e.setJubilaeen((String) getJubilaeen().getValue());
      e.setAltersjubilaeen((String) getAltersjubilaeen().getValue());
      Integer delay = (Integer) delaytime.getValue();
      e.setDelaytime(delay);
      e.store();
      spalten.save();
      Einstellungen.setEinstellung(e);
      GUI.getStatusBar().setSuccessText("Einstellungen gespeichert");
    }
    catch (RemoteException e)
    {
      GUI.getStatusBar().setErrorText(e.getMessage());
    }
    catch (ApplicationException e)
    {
      GUI.getStatusBar().setErrorText(e.getMessage());
    }
  }

  /**
   * Sucht das Geldinstitut zur eingegebenen BLZ und zeigt es als Kommentar
   * hinter dem BLZ-Feld an.
   */
  private class BLZListener implements Listener
  {

    public void handleEvent(Event event)
    {
      try
      {
        String blz = (String) getBlz().getValue();
        getBlz().setComment(Einstellungen.getNameForBLZ(blz));
      }
      catch (RemoteException e)
      {
        Logger.error("error while updating blz comment", e);
      }
    }
  }

}
