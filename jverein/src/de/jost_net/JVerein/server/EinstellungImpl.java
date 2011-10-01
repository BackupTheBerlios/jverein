/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/server/EinstellungImpl.java,v $
 * $Revision: 1.36 $
 * $Date: 2011/10/01 21:50:35 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/
package de.jost_net.JVerein.server;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.io.AltersgruppenParser;
import de.jost_net.JVerein.io.JubilaeenParser;
import de.jost_net.JVerein.rmi.Einstellung;
import de.jost_net.JVerein.rmi.Felddefinition;
import de.willuhn.datasource.db.AbstractDBObject;
import de.willuhn.datasource.rmi.DBIterator;
import de.willuhn.jameica.plugin.AbstractPlugin;
import de.willuhn.jameica.system.Application;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

public class EinstellungImpl extends AbstractDBObject implements Einstellung
{

  private static final long serialVersionUID = 3513343626868776722L;

  /**
   * Variable, in der gespeichert wird, ob f�r den Verein Zusatzfelder vorhanden
   * sind.
   */
  private Boolean hasZus = null;

  public EinstellungImpl() throws RemoteException
  {
    super();
  }

  @Override
  protected String getTableName()
  {
    return "einstellung";
  }

  @Override
  public String getPrimaryAttribute()
  {
    return "id";
  }

  @Override
  protected void deleteCheck() throws ApplicationException
  {
    throw new ApplicationException(JVereinPlugin.getI18n().tr(
        "Einstellung darf nicht gel�scht werden"));
  }

  @Override
  protected void insertCheck() throws ApplicationException
  {
    try
    {
      if (getName() == null || getName().length() == 0)
      {
        throw new ApplicationException(JVereinPlugin.getI18n().tr(
            "Bitte Namen eingeben"));
      }
      if (getBlz() == null || getBlz().length() == 0)
      {
        throw new ApplicationException(JVereinPlugin.getI18n().tr(
            "Bitte Bankleitzahl eingeben"));
      }
      try
      {
        Integer.parseInt(getBlz());
      }
      catch (NumberFormatException e)
      {
        throw new ApplicationException(
            "Bankleitzahl enth�lt unzul�ssige Zeichen!");
      }
      if (getKonto() == null || getKonto().length() == 0)
      {
        throw new ApplicationException(JVereinPlugin.getI18n().tr(
            "Bitte Kontonummer eingeben"));
      }
      try
      {
        Long.parseLong(getKonto());
      }
      catch (NumberFormatException e)
      {
        throw new ApplicationException(
            "Kontonummer enth�lt unzul�ssige Zeichen!");
      }
      if (!Einstellungen.checkAccountCRC(getBlz(), getKonto()))
      {
        throw new ApplicationException(JVereinPlugin.getI18n().tr(
            "Ung�ltige BLZ/Kontonummer. Bitte pr�fen Sie Ihre Eingaben."));
      }
      try
      {
        new AltersgruppenParser(getAltersgruppen());
      }
      catch (RuntimeException e)
      {
        throw new ApplicationException(e.getMessage());
      }

      if (getDokumentenspeicherung())
      {
        boolean messaging = false;
        List<AbstractPlugin> plugins = Application.getPluginLoader()
            .getInstalledPlugins();
        for (AbstractPlugin plugin : plugins)
        {
          if (plugin.getManifest().getName().equals("jameica.messaging"))
          {
            messaging = true;
          }
        }
        if (!messaging)
        {
          throw new ApplicationException(
              "Plugin jameica.messaging ist nicht installiert!");
        }
      }
      try
      {
        new JubilaeenParser(getJubilaeen());
      }
      catch (RuntimeException e)
      {
        throw new ApplicationException(e.getMessage());
      }
    }
    catch (RemoteException e)
    {
      String fehler = JVereinPlugin.getI18n().tr(
          "Einstellung kann nicht gespeichert werden. Siehe system log");
      Logger.error(fehler, e);
      throw new ApplicationException(fehler);
    }
  }

  @Override
  protected void updateCheck() throws ApplicationException
  {
    insertCheck();
  }

  @Override
  protected Class<?> getForeignObject(String arg0)
  {
    return null;
  }

  public void setID() throws RemoteException
  {
    setAttribute("id", "1");
  }

  public String getName() throws RemoteException
  {
    return (String) getAttribute("name");
  }

  public void setName(String name) throws RemoteException
  {
    setAttribute("name", name);
  }

  public String getStrasse() throws RemoteException
  {
    return (String) getAttribute("strasse");
  }

  public void setStrasse(String strasse) throws RemoteException
  {
    setAttribute("strasse", strasse);
  }

  public String getPlz() throws RemoteException
  {
    return (String) getAttribute("plz");
  }

  public void setPlz(String plz) throws RemoteException
  {
    setAttribute("plz", plz);
  }

  public String getOrt() throws RemoteException
  {
    return (String) getAttribute("ort");
  }

  public void setOrt(String ort) throws RemoteException
  {
    setAttribute("ort", ort);
  }

  public String getFinanzamt() throws RemoteException
  {
    return (String) getAttribute("finanzamt");
  }

  public void setFinanzamt(String finanzamt) throws RemoteException
  {
    setAttribute("finanzamt", finanzamt);
  }

  public String getSteuernummer() throws RemoteException
  {
    return (String) getAttribute("steuernummer");
  }

  public void setSteuernummer(String steuernummer) throws RemoteException
  {
    setAttribute("steuernummer", steuernummer);
  }

  public Date getBescheiddatum() throws RemoteException
  {
    Date d = (Date) getAttribute("bescheiddatum");
    if (d == null)
    {
      return new Date();
    }
    return d;
  }

  public void setBescheiddatum(Date bescheiddatum) throws RemoteException
  {
    setAttribute("bescheiddatum", bescheiddatum);
  }

  public Boolean getVorlaeufig() throws RemoteException
  {
    return Util.getBoolean(getAttribute("vorlaeufig"));
  }

  public void setVorlaeufig(Boolean vorlaeufig) throws RemoteException
  {
    setAttribute("vorlaeufig", Boolean.valueOf(vorlaeufig));
  }

  public Date getVorlaeufigab() throws RemoteException
  {
    Date d = (Date) getAttribute("vorlaeufigab");
    if (d == null)
    {
      return new Date();
    }
    return d;
  }

  public void setVorlaeufigab(Date vorlaeufigab) throws RemoteException
  {
    setAttribute("vorlaeufigab", vorlaeufigab);
  }

  public String getBeguenstigterzweck() throws RemoteException
  {
    return (String) getAttribute("beguenstigterzweck");
  }

  public void setBeguenstigterzweck(String beguenstigterzweck)
      throws RemoteException
  {
    setAttribute("beguenstigterzweck", beguenstigterzweck);
  }

  public Boolean getMitgliedsbetraege() throws RemoteException
  {
    return Util.getBoolean(getAttribute("mitgliedsbeitraege"));
  }

  public void setMitgliedsbeitraege(Boolean mitgliedsbeitraege)
      throws RemoteException
  {
    setAttribute("mitgliedsbeitraege", Boolean.valueOf(mitgliedsbeitraege));
  }

  public String getBlz() throws RemoteException
  {
    return (String) getAttribute("blz");
  }

  public void setBlz(String blz) throws RemoteException
  {
    setAttribute("blz", blz);
  }

  public String getKonto() throws RemoteException
  {
    return (String) getAttribute("konto");
  }

  public void setKonto(String konto) throws RemoteException
  {
    setAttribute("konto", konto);
  }

  public Boolean getGeburtsdatumPflicht() throws RemoteException
  {
    return Util.getBoolean(getAttribute("geburtsdatumpflicht"));
  }

  public void setGeburtsdatumPflicht(Boolean geburtsdatumpflicht)
      throws RemoteException
  {
    setAttribute("geburtsdatumpflicht", Boolean.valueOf(geburtsdatumpflicht));
  }

  public Boolean getEintrittsdatumPflicht() throws RemoteException
  {
    return Util.getBoolean(getAttribute("eintrittsdatumpflicht"));
  }

  public void setEintrittsdatumPflicht(Boolean eintrittsdatumpflicht)
      throws RemoteException
  {
    setAttribute("eintrittsdatumpflicht",
        Boolean.valueOf(eintrittsdatumpflicht));
  }

  public Boolean getSterbedatum() throws RemoteException
  {
    return Util.getBoolean(getAttribute("sterbedatum"));
  }

  public void setSterbedatum(Boolean sterbedatum) throws RemoteException
  {
    setAttribute("sterbedatum", Boolean.valueOf(sterbedatum));
  }

  public Boolean getKommunikationsdaten() throws RemoteException
  {
    return Util.getBoolean(getAttribute("kommunikationsdaten"));
  }

  public void setKommunikationsdaten(Boolean kommunikationsdaten)
      throws RemoteException
  {
    setAttribute("kommunikationsdaten", Boolean.valueOf(kommunikationsdaten));
  }

  public Boolean getZusatzbetrag() throws RemoteException
  {
    return Util.getBoolean(getAttribute("zusatzabbuchung"));
  }

  public void setZusatzbetrag(Boolean zusatzabbuchung) throws RemoteException
  {
    setAttribute("zusatzabbuchung", Boolean.valueOf(zusatzabbuchung));
  }

  public Boolean getVermerke() throws RemoteException
  {
    return Util.getBoolean(getAttribute("vermerke"));
  }

  public void setVermerke(Boolean vermerke) throws RemoteException
  {
    setAttribute("vermerke", Boolean.valueOf(vermerke));
  }

  public Boolean getWiedervorlage() throws RemoteException
  {
    return Util.getBoolean(getAttribute("wiedervorlage"));
  }

  public void setWiedervorlage(Boolean wiedervorlage) throws RemoteException
  {
    setAttribute("wiedervorlage", Boolean.valueOf(wiedervorlage));
  }

  public Boolean getKursteilnehmer() throws RemoteException
  {
    return Util.getBoolean(getAttribute("kursteilnehmer"));
  }

  public void setKursteilnehmer(Boolean kursteilnehmer) throws RemoteException
  {
    setAttribute("kursteilnehmer", Boolean.valueOf(kursteilnehmer));
  }

  public Boolean getLehrgaenge() throws RemoteException
  {
    return Util.getBoolean(getAttribute("lehrgaenge"));
  }

  public void setLehrgaenge(Boolean lehrgaenge) throws RemoteException
  {
    setAttribute("lehrgaenge", Boolean.valueOf(lehrgaenge));
  }

  public Boolean getJuristischePersonen() throws RemoteException
  {
    return Util.getBoolean(getAttribute("juristischepersonen"));
  }

  public void setJuristischePersonen(Boolean juristischepersonen)
      throws RemoteException
  {
    setAttribute("juristischepersonen", Boolean.valueOf(juristischepersonen));
  }

  public Boolean getMitgliedskonto() throws RemoteException
  {
    return Util.getBoolean(getAttribute("mitgliedskonto"));
  }

  public void setMitgliedskonto(Boolean mitgliedskonto) throws RemoteException
  {
    setAttribute("mitgliedskonto", Boolean.valueOf(mitgliedskonto));
  }

  public Boolean getMitgliedfoto() throws RemoteException
  {
    return Util.getBoolean(getAttribute("mitgliedfoto"));
  }

  public void setMitgliedfoto(Boolean mitgliedfoto) throws RemoteException
  {
    setAttribute("mitgliedfoto", Boolean.valueOf(mitgliedfoto));
  }

  public Boolean getZusatzadressen() throws RemoteException
  {
    return Util.getBoolean(getAttribute("zusatzadressen"));
  }

  public void setZusatzadressen(Boolean zusatzadressen) throws RemoteException
  {
    setAttribute("zusatzadressen", Boolean.valueOf(zusatzadressen));
  }

  public Boolean getAuslandsadressen() throws RemoteException
  {
    return Util.getBoolean(getAttribute("auslandsadressen"));
  }

  public void setAuslandsadressen(Boolean auslandsadressen)
      throws RemoteException
  {
    setAttribute("auslandsadressen", Boolean.valueOf(auslandsadressen));
  }

  public Boolean getArbeitseinsatz() throws RemoteException
  {
    return Util.getBoolean(getAttribute("arbeitseinsatz"));
  }

  public void setArbeitseinsatz(Boolean arbeitseinsatz) throws RemoteException
  {
    setAttribute("arbeitseinsatz", Boolean.valueOf(arbeitseinsatz));
  }

  public Boolean getDokumentenspeicherung() throws RemoteException
  {
    return Util.getBoolean(getAttribute("dokumentenspeicherung"));
  }

  public void setDokumentenspeicherung(Boolean dokumentenspeicherung)
      throws RemoteException
  {
    setAttribute("dokumentenspeicherung",
        Boolean.valueOf(dokumentenspeicherung));
  }

  public Boolean getIndividuelleBeitraege() throws RemoteException
  {
    return Util.getBoolean(getAttribute("individuellebeitraege"));
  }

  public void setIndividuelleBeitraege(Boolean individuellebeitraege)
      throws RemoteException
  {
    setAttribute("individuellebeitraege",
        Boolean.valueOf(individuellebeitraege));
  }

  public String getRechnungTextAbbuchung() throws RemoteException
  {
    String text = (String) getAttribute("rechnungtextabbuchung");
    if (text == null)
    {
      text = "Der Betrag wird vom Konto ${Konto} (BLZ ${BLZ}) abgebucht.";
    }
    return text;
  }

  public void setRechnungTextAbbuchung(String rechnungtextabbuchung)
      throws RemoteException
  {
    setAttribute("rechnungtextabbuchung", rechnungtextabbuchung);
  }

  public String getRechnungTextUeberweisung() throws RemoteException
  {
    String text = (String) getAttribute("rechnungtextueberweisung");
    if (text == null)
    {
      text = "Bitte �berweisen Sie den Betrag auf das angegebene Konto.";
    }
    return text;
  }

  public void setRechnungTextUeberweisung(String rechnungtextueberweisung)
      throws RemoteException
  {
    setAttribute("rechnungtextueberweisung", rechnungtextueberweisung);
  }

  public String getRechnungTextBar() throws RemoteException
  {
    String text = (String) getAttribute("rechnungtextbar");
    if (text == null)
    {
      text = "Bitte zahlen Sie den Betrag auf das angegebene Konto ein.";
    }
    return text;
  }

  public void setRechnungTextBar(String rechnungtextbar) throws RemoteException
  {
    setAttribute("rechnungtextbar", rechnungtextbar);
  }

  public Boolean getExterneMitgliedsnummer() throws RemoteException
  {
    return Util.getBoolean(getAttribute("externemitgliedsnummer"));
  }

  public void setExterneMitgliedsnummer(Boolean externemitgliedsnummer)
      throws RemoteException
  {
    setAttribute("externemitgliedsnummer",
        Boolean.valueOf(externemitgliedsnummer));
  }

  public int getBeitragsmodel() throws RemoteException
  {
    return (Integer) getAttribute("beitragsmodel");
  }

  public void setBeitragsmodel(int beitragsmodel) throws RemoteException
  {
    setAttribute("beitragsmodel", beitragsmodel);
  }

  public String getDateinamenmuster() throws RemoteException
  {
    return (String) getAttribute("dateinamenmuster");
  }

  public void setDateinamenmuster(String dateinamenmuster)
      throws RemoteException
  {
    setAttribute("dateinamenmuster", dateinamenmuster);
  }

  public String getBeginnGeschaeftsjahr() throws RemoteException
  {
    return (String) getAttribute("beginngeschaeftsjahr");
  }

  public void setBeginnGeschaeftsjahr(String beginngeschaeftsjahr)
      throws RemoteException
  {
    setAttribute("beginngeschaeftsjahr", beginngeschaeftsjahr);
  }

  public String getMitgliedskontoIstzahlung() throws RemoteException
  {
    return (String) getAttribute("mitgliedskontoistzahlung");
  }

  public void setMitgliedskontoIstzahlung(String mitgliedskontoistzahlung)
      throws RemoteException
  {
    setAttribute("mitgliedskontoistzahlung", mitgliedskontoistzahlung);
  }

  public String getSmtpServer() throws RemoteException
  {
    return (String) getAttribute("smtp_server");
  }

  public void setSmtpServer(String smtp_server) throws RemoteException
  {
    setAttribute("smtp_server", smtp_server);
  }

  public String getSmtpPort() throws RemoteException
  {
    String ret = (String) getAttribute("smtp_port");
    if (ret == null)
    {
      ret = "25";
    }
    return ret;
  }

  public void setSmtpPort(String smtp_port) throws RemoteException
  {
    setAttribute("smtp_port", smtp_port);
  }

  public String getSmtpAuthUser() throws RemoteException
  {
    return (String) getAttribute("smtp_auth_user");
  }

  public void setSmtpAuthUser(String smtp_auth_user) throws RemoteException
  {
    setAttribute("smtp_auth_user", smtp_auth_user);
  }

  public String getSmtpAuthPwd() throws RemoteException
  {
    return (String) getAttribute("smtp_auth_pwd");
  }

  public void setSmtpAuthPwd(String smtp_auth_pwd) throws RemoteException
  {
    setAttribute("smtp_auth_pwd", smtp_auth_pwd);
  }

  public String getSmtpFromAddress() throws RemoteException
  {
    return (String) getAttribute("smtp_from_address");
  }

  public void setSmtpFromAddress(String smtp_from_address)
      throws RemoteException
  {
    setAttribute("smtp_from_address", smtp_from_address);
  }

  public Boolean getSmtpSsl() throws RemoteException
  {
    return Util.getBoolean(getAttribute("smtp_ssl"));
  }

  public void setSmtpSsl(Boolean smtp_ssl) throws RemoteException
  {
    setAttribute("smtp_ssl", smtp_ssl);
  }

  public Boolean getSmtpStarttls() throws RemoteException
  {
    return Util.getBoolean(getAttribute("smtp_starttls"));
  }

  public void setSmtpStarttls(Boolean smtp_starttls) throws RemoteException
  {
    setAttribute("smtp_starttls", smtp_starttls);
  }

  public int getZahlungsrhytmus() throws RemoteException
  {
    try
    {
      return (Integer) getAttribute("zahlungsrhytmus");
    }
    catch (NullPointerException e)
    {
      return 12;
    }
  }

  public void setZahlungsrhytmus(int zahlungsrhytmus) throws RemoteException
  {
    setAttribute("zahlungsrhytmus", zahlungsrhytmus);
  }

  public int getZahlungsweg() throws RemoteException
  {
    try
    {
      return (Integer) getAttribute("zahlungsweg");
    }
    catch (NullPointerException e)
    {
      return 1;
    }
  }

  public void setZahlungsweg(int zahlungsweg) throws RemoteException
  {
    setAttribute("zahlungsweg", zahlungsweg);
  }

  public String getAltersgruppen() throws RemoteException
  {
    return (String) getAttribute("altersgruppen");
  }

  public void setAltersgruppen(String altersgruppen) throws RemoteException
  {
    setAttribute("altersgruppen", altersgruppen);
  }

  public String getJubilaeen() throws RemoteException
  {
    String ag = (String) getAttribute("jubilaeen");
    if (ag == null || ag.length() == 0)
    {
      ag = "10,25,40,50";
    }
    return ag;
  }

  public void setJubilaeen(String jubilaeen) throws RemoteException
  {
    setAttribute("jubilaeen", jubilaeen);
  }

  public String getAltersjubilaeen() throws RemoteException
  {
    String aj = (String) getAttribute("altersjubilaeen");
    if (aj == null || aj.length() == 0)
    {
      aj = "50,60,65,70,75,80,85,90,95,100";
    }
    return aj;
  }

  public void setAltersjubilaeen(String altersjubilaeen) throws RemoteException
  {
    setAttribute("altersjubilaeen", altersjubilaeen);
  }

  public int getDelaytime() throws RemoteException
  {
    try
    {
      return (Integer) getAttribute("delaytime");
    }
    catch (NullPointerException e)
    {
      return 1000;
    }
  }

  public void setDelaytime(int delaytime) throws RemoteException
  {
    setAttribute("delaytime", delaytime);
  }

  public boolean hasZusatzfelder() throws RemoteException
  {
    if (hasZus == null)
    {
      DBIterator it = Einstellungen.getDBService().createList(
          Felddefinition.class);
      hasZus = new Boolean(it.size() > 0);
    }
    return hasZus;
  }

  @Override
  public Object getAttribute(String fieldName) throws RemoteException
  {
    return super.getAttribute(fieldName);
  }

}
