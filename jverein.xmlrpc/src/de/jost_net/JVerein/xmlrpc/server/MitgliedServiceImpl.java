/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein.xmlrpc/src/de/jost_net/JVerein/xmlrpc/server/MitgliedServiceImpl.java,v $
 * $Revision: 1.2 $
 * $Date: 2011/05/19 17:35:01 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: MitgliedServiceImpl.java,v $
 * Revision 1.2  2011/05/19 17:35:01  jost
 * *** empty log message ***
 *
 * Revision 1.1  2011-03-07 21:12:32  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.JVerein.xmlrpc.server;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.rmi.Mitglied;
import de.jost_net.JVerein.xmlrpc.Einstellung;
import de.jost_net.JVerein.xmlrpc.rmi.MitgliedService;
import de.jost_net.JVerein.xmlrpc.util.DateUtil;
import de.jost_net.JVerein.xmlrpc.util.IntUtil;
import de.jost_net.JVerein.xmlrpc.util.StringUtil;
import de.willuhn.datasource.rmi.DBIterator;
import de.willuhn.datasource.rmi.DBService;
import de.willuhn.jameica.system.Application;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

/**
 * Implementierung des Mitglied-Services.
 */
public class MitgliedServiceImpl extends AbstractServiceImpl implements
    MitgliedService
{
  private static final long serialVersionUID = -3216353423632273384L;

  public static final String ADRESSIERUNGSZUSATZ = "mitglied.adressierungszusatz";

  public static final String ADRESSTYP = "mitglied.adresstyp";

  public static final String ANREDE = "mitglied.anrede";

  public static final String AUSTRITT = "mitglied.austritt";

  public static final String BEITRAGSGRUPPE = "mitglied.beitragsgruppe";

  public static final String BLZ = "mitglied.blz";

  public static final String EINTRITT = "mitglied.eintritt";

  public static final String EMAIL = "mitglied.email";

  public static final String EXTERNE_MITGLIEDSNUMMER = "mitglied.externe_mitgliedsnummer";

  public static final String GEBURTSDATUM = "mitglied.geburtsdatum";

  public static final String GESCHLECHT = "mitglied.geschlecht";

  public static final String HANDY = "mitglied.handy";

  public static final String IBAN = "mitglied.iban";

  public static final String ID = "mitglied.id";

  public static final String KONTO = "mitglied.konto";

  public static final String KONTOINHABER = "mitglied.kontoinhaber";

  public static final String KUENDIGUNG = "mitglied.kuendigung";

  public static final String LETZTEAENDERUNG = "mitglied.letzte.aenderung";

  public static final String NAME = "mitglied.name";

  public static final String ORT = "mitglied.ort";

  public static final String PERSONENART = "mitglied.personenart";

  public static final String PLZ = "mitglied.plz";

  public static final String STAAT = "mitglied.staat";

  public static final String STERBETAG = "mitglied.sterbetag";

  public static final String STRASSE = "mitglied.strasse";

  public static final String TELEFONDIENSTLICH = "mitglied.telefon.dienstlich";

  public static final String TELEFONPRIVAT = "mitglied.telefon.privat";

  public static final String TITEL = "mitglied.titel";

  public static final String VERMERK1 = "mitglied.vermerk1";

  public static final String VERMERK2 = "mitglied.vermerk2";

  public static final String VORNAME = "mitglied.vorname";

  public static final String ZAHLUNGSRHYTMUS = "mitglied.zahlungsrhytmus";

  public static final String ZAHLUNGSWEG = "mitglied.zahlungsweg";

  public static final String ZAHLERID = "mitglied.zahlerid";

  public MitgliedServiceImpl() throws RemoteException
  {
    super();
  }

  public Map<String, Object> read(String id) throws RemoteException
  {
    Mitglied m = (Mitglied) Einstellungen.getDBService().createObject(
        Mitglied.class, id);
    Map<String, Object> retrec = new HashMap<String, Object>();
    retrec.put(ADRESSIERUNGSZUSATZ,
        StringUtil.notNull(m.getAdressierungszusatz()));
    retrec.put(ADRESSTYP, m.getAdresstyp().getID());
    retrec.put(ANREDE, StringUtil.notNull(m.getAnrede()));
    retrec.put(ANREDE, StringUtil.notNull(m.getAnrede()));
    retrec.put(AUSTRITT, StringUtil.notNull(m.getAustritt()));
    retrec.put(BEITRAGSGRUPPE, m.getBeitragsgruppeId());
    retrec.put(BLZ, StringUtil.notNull(m.getBlz()));
    retrec.put(EINTRITT, StringUtil.notNull(m.getEintritt()));
    retrec.put(EMAIL, StringUtil.notNull(m.getEmail()));
    retrec.put(EXTERNE_MITGLIEDSNUMMER,
        StringUtil.notNull(m.getExterneMitgliedsnummer()));
    retrec.put(GEBURTSDATUM, m.getGeburtsdatum());
    retrec.put(GESCHLECHT, m.getGeschlecht());
    retrec.put(HANDY, StringUtil.notNull(m.getHandy()));
    retrec.put(IBAN, StringUtil.notNull(m.getIban()));
    retrec.put(ID, m.getID());
    retrec.put(KONTO, StringUtil.notNull(m.getKonto()));
    retrec.put(KONTOINHABER, StringUtil.notNull(m.getKontoinhaber()));
    retrec.put(KUENDIGUNG, StringUtil.notNull(m.getKuendigung()));
    retrec.put(LETZTEAENDERUNG, StringUtil.notNull(m.getLetzteAenderung()));
    retrec.put(NAME, m.getName());
    retrec.put(ORT, StringUtil.notNull(m.getOrt()));
    retrec.put(PERSONENART, StringUtil.notNull(m.getPersonenart()));
    retrec.put(PLZ, StringUtil.notNull(m.getPlz()));
    retrec.put(STAAT, StringUtil.notNull(m.getStaat()));
    retrec.put(STERBETAG, StringUtil.notNull(m.getSterbetag()));
    retrec.put(STRASSE, StringUtil.notNull(m.getStrasse()));
    retrec.put(TELEFONDIENSTLICH, StringUtil.notNull(m.getTelefondienstlich()));
    retrec.put(TELEFONPRIVAT, StringUtil.notNull(m.getTelefonprivat()));
    retrec.put(TITEL, StringUtil.notNull(m.getTitel()));
    retrec.put(VERMERK1, StringUtil.notNull(m.getVermerk1()));
    retrec.put(VERMERK2, StringUtil.notNull(m.getVermerk2()));
    retrec.put(VORNAME, StringUtil.notNull(m.getVorname()));
    retrec.put(ZAHLUNGSRHYTMUS, StringUtil.notNull(m.getZahlungsrhytmus()));
    retrec.put(ZAHLUNGSWEG, StringUtil.notNull(m.getZahlungsweg()));
    retrec.put(ZAHLERID, StringUtil.notNull(m.getZahlerID()));
    return retrec;
  }

  public String[] getIDs(Integer adresstyp) throws RemoteException
  {
    DBIterator it = Einstellungen.getDBService().createList(Mitglied.class);
    it.addFilter("adresstyp = ?", new Object[] { adresstyp });
    String[] retlist = new String[it.size()];
    int i = 0;
    while (it.hasNext())
    {
      Mitglied m = (Mitglied) it.next();
      retlist[i] = m.getID();
      i++;
    }
    return retlist;
  }

  /**
   * @see de.willuhn.datasource.Service#getName()
   */
  public String getName() throws RemoteException
  {
    return "[xml-rpc] mitglied";
  }

  public String create(Map<String, Object> address) throws RemoteException
  {
    return store(address, false);
  }

  public String update(Map<String, Object> address) throws RemoteException
  {
    return store(address, true);
  }

  private String store(Map<String, Object> mitglied, boolean update)
      throws RemoteException
  {
    boolean supportNull = Einstellung.isNullSupported();

    try
    {
      if (mitglied == null || mitglied.size() == 0)
      {
        throw new ApplicationException(
            i18n.tr("Keine Mitgliedsdaten angegeben"));
      }
      String id = update ? StringUtil.notNull(mitglied.get(ID)) : null;
      if (update && (id == null || id.length() == 0))
      {
        throw new ApplicationException(
            i18n.tr("Schlüssel \"id\" des Mitglieds fehlt"));
      }
      DBService service = (DBService) Application.getServiceFactory().lookup(
          JVereinPlugin.class, "database");
      Mitglied m = (Mitglied) service.createObject(Mitglied.class, id);
      m.setAdressierungszusatz(StringUtil.notNull(mitglied
          .get(ADRESSIERUNGSZUSATZ)));
      m.setAdresstyp(IntUtil.parse(mitglied.get(ADRESSTYP), "Adresstyp"));
      m.setAnrede(StringUtil.notNull(mitglied.get(ANREDE)));
      m.setAustritt(DateUtil.parseToString(mitglied.get(AUSTRITT), "Austritt"));
      m.setBeitragsgruppe(IntUtil.parse(mitglied.get(BEITRAGSGRUPPE),
          "Beitragsgruppe"));
      m.setBlz(StringUtil.notNull(mitglied.get(BLZ)));
      m.setEintritt(DateUtil.parseToString(mitglied.get(EINTRITT), "Eintritt"));
      m.setEmail(StringUtil.notNull(mitglied.get(EMAIL)));
      // if (mitglied.get(EXTERNE_MITGLIEDSNUMMER) != null||
      // mitglied.get(EXTERNE_MITGLIEDSNUMMER))
      // {
      // m.setExterneMitgliedsnummer(IntUtil.parse(
      // mitglied.get(EXTERNE_MITGLIEDSNUMMER), "externe Mitgliedsnummer"));
      // }
      m.setGeburtsdatum(DateUtil.parseToString(mitglied.get(GEBURTSDATUM),
          "Geburtsdatum"));
      m.setGeschlecht(StringUtil.notNull(mitglied.get(GESCHLECHT)));
      m.setHandy(StringUtil.notNull(mitglied.get(HANDY)));
      m.setKonto(StringUtil.notNull(mitglied.get(KONTO)));
      m.setKontoinhaber(StringUtil.notNull(mitglied.get(KONTOINHABER)));
      m.setKuendigung(DateUtil.parseToString(mitglied.get(KUENDIGUNG),
          "Kündigung"));
      m.setLetzteAenderung();
      m.setName(StringUtil.notNull(mitglied.get(NAME)));
      m.setOrt(StringUtil.notNull(mitglied.get(ORT)));
      m.setPersonenart(StringUtil.notNull(mitglied.get(PERSONENART)));
      m.setPlz(StringUtil.notNull(mitglied.get(PLZ)));
      m.setStaat(StringUtil.notNull(mitglied.get(STAAT)));
      m.setSterbetag(DateUtil.parseToString(mitglied.get(STERBETAG),
          "Sterbetag"));
      m.setStrasse(StringUtil.notNull(mitglied.get(STRASSE)));
      m.setTelefondienstlich(StringUtil.notNull(mitglied.get(TELEFONDIENSTLICH)));
      m.setTelefonprivat(StringUtil.notNull(mitglied.get(TELEFONPRIVAT)));
      m.setTitel(StringUtil.notNull(mitglied.get(TITEL)));
      m.setVermerk1(StringUtil.notNull(mitglied.get(VERMERK1)));
      m.setVermerk2(StringUtil.notNull(mitglied.get(VERMERK2)));
      m.setVorname(StringUtil.notNull(mitglied.get(VORNAME)));
      m.setZahlungsrhytmus(IntUtil.parse(mitglied.get(ZAHLUNGSRHYTMUS),
          "Zahlungsrhytmus"));
      m.setZahlungsweg(IntUtil.parse(mitglied.get(ZAHLUNGSWEG), "Zahlungsweg"));
      // if (mitglied.get(ZAHLERID) != null)
      // {
      // m.setZahlerID(IntUtil.parse(mitglied.get(ZAHLERID), "zahlerid"));
      // }
       m.store();
      Logger.info((update ? "updated" : "created") + " mitglied [ID: "
          + m.getID() + "]");
      return supportNull ? null : m.getID();
    }
    catch (ApplicationException ae)
    {
      if (supportNull)
      {
        return ae.getMessage();
      }
      throw new RemoteException(ae.getMessage(), ae);
    }
    catch (Exception e)
    {
      Logger.error("unable to store mitglied", e);
      throw new RemoteException(i18n.tr(
          "Fehler beim Speichern des Mitglieds: {0}", e.getMessage()), e);
    }
  }

  //
  // private String store(Map<String, String> mitglied, boolean update)
  // throws RemoteException
  // {
  // boolean supportNull = Einstellung.isNullSupported();
  //
  // try
  // {
  // if (mitglied == null || mitglied.size() == 0)
  // {
  // throw new ApplicationException(
  // i18n.tr("Keine Eigenschaften des Mitglieds angegeben"));
  // }
  // String id = update ? mitglied.get("id") : null;
  // if (update && (id == null || id.length() == 0))
  // {
  // throw new ApplicationException(
  // i18n.tr("Schlüssel \"id\" des zu aktualisierenden Mitglieds fehlt"));
  // }
  // DBService service = (DBService) Application.getServiceFactory().lookup(
  // JVereinPlugin.class, "database");
  // Mitglied m = (Mitglied) service.createObject(Mitglied.class, id);
  // m.store();
  // Logger.info((update ? "updated" : "created") + " address [ID: "
  // + m.getID() + "]");
  // return supportNull ? null : m.getID();
  // }
  // catch (ApplicationException ae)
  // {
  // return ae.getMessage();
  // }
  // catch (Exception e)
  // {
  // Logger.error("unable to store address", e);
  // throw new RemoteException(i18n.tr(
  // "Fehler beim Speichern der Adresse: {0}", e.getMessage()), e);
  // }
  // }
}
