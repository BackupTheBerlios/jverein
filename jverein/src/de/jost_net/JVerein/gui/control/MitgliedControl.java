/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/control/MitgliedControl.java,v $
 * $Revision: 1.125 $
 * $Date: 2011/10/01 21:42:57 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/
package de.jost_net.JVerein.gui.control;

import java.io.File;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TreeItem;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.Messaging.FamilienbeitragMessage;
import de.jost_net.JVerein.Queries.MitgliedQuery;
import de.jost_net.JVerein.gui.action.ArbeitseinsatzAction;
import de.jost_net.JVerein.gui.action.LehrgangAction;
import de.jost_net.JVerein.gui.action.MitgliedDetailAction;
import de.jost_net.JVerein.gui.action.WiedervorlageAction;
import de.jost_net.JVerein.gui.action.ZusatzbetraegeAction;
import de.jost_net.JVerein.gui.dialogs.EigenschaftenAuswahlDialog;
import de.jost_net.JVerein.gui.dialogs.ZusatzfelderAuswahlDialog;
import de.jost_net.JVerein.gui.formatter.JaNeinFormatter;
import de.jost_net.JVerein.gui.input.GeschlechtInput;
import de.jost_net.JVerein.gui.menu.ArbeitseinsatzMenu;
import de.jost_net.JVerein.gui.menu.FamilienbeitragMenu;
import de.jost_net.JVerein.gui.menu.LehrgangMenu;
import de.jost_net.JVerein.gui.menu.MitgliedMenu;
import de.jost_net.JVerein.gui.menu.WiedervorlageMenu;
import de.jost_net.JVerein.gui.menu.ZusatzbetraegeMenu;
import de.jost_net.JVerein.gui.parts.Familienverband;
import de.jost_net.JVerein.io.Jubilaeenliste;
import de.jost_net.JVerein.io.MitgliedAuswertungCSV;
import de.jost_net.JVerein.io.MitgliedAuswertungCSValt;
import de.jost_net.JVerein.io.MitgliedAuswertungPDF;
import de.jost_net.JVerein.io.MitgliederStatistik;
import de.jost_net.JVerein.keys.ArtBeitragsart;
import de.jost_net.JVerein.keys.Datentyp;
import de.jost_net.JVerein.keys.Zahlungsrhytmus;
import de.jost_net.JVerein.keys.Zahlungsweg;
import de.jost_net.JVerein.rmi.Adresstyp;
import de.jost_net.JVerein.rmi.Arbeitseinsatz;
import de.jost_net.JVerein.rmi.Beitragsgruppe;
import de.jost_net.JVerein.rmi.Eigenschaft;
import de.jost_net.JVerein.rmi.EigenschaftGruppe;
import de.jost_net.JVerein.rmi.Eigenschaften;
import de.jost_net.JVerein.rmi.Felddefinition;
import de.jost_net.JVerein.rmi.Lehrgang;
import de.jost_net.JVerein.rmi.Mitglied;
import de.jost_net.JVerein.rmi.Mitgliedfoto;
import de.jost_net.JVerein.rmi.Wiedervorlage;
import de.jost_net.JVerein.rmi.Zusatzbetrag;
import de.jost_net.JVerein.rmi.Zusatzfelder;
import de.jost_net.JVerein.server.EigenschaftenNode;
import de.jost_net.JVerein.server.MitgliedUtils;
import de.jost_net.JVerein.util.Dateiname;
import de.jost_net.JVerein.util.IbanBicCalc;
import de.jost_net.JVerein.util.JVDateFormatTTMMJJJJ;
import de.jost_net.JVerein.util.MitgliedSpaltenauswahl;
import de.willuhn.datasource.GenericObject;
import de.willuhn.datasource.GenericObjectNode;
import de.willuhn.datasource.pseudo.PseudoIterator;
import de.willuhn.datasource.rmi.DBIterator;
import de.willuhn.datasource.rmi.DBService;
import de.willuhn.datasource.rmi.ObjectNotFoundException;
import de.willuhn.datasource.rmi.ResultSetExtractor;
import de.willuhn.jameica.gui.AbstractControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.Part;
import de.willuhn.jameica.gui.formatter.CurrencyFormatter;
import de.willuhn.jameica.gui.formatter.DateFormatter;
import de.willuhn.jameica.gui.formatter.TreeFormatter;
import de.willuhn.jameica.gui.input.CheckboxInput;
import de.willuhn.jameica.gui.input.DateInput;
import de.willuhn.jameica.gui.input.DecimalInput;
import de.willuhn.jameica.gui.input.DialogInput;
import de.willuhn.jameica.gui.input.ImageInput;
import de.willuhn.jameica.gui.input.Input;
import de.willuhn.jameica.gui.input.IntegerInput;
import de.willuhn.jameica.gui.input.SearchInput;
import de.willuhn.jameica.gui.input.SelectInput;
import de.willuhn.jameica.gui.input.TextAreaInput;
import de.willuhn.jameica.gui.input.TextInput;
import de.willuhn.jameica.gui.parts.Button;
import de.willuhn.jameica.gui.parts.TablePart;
import de.willuhn.jameica.gui.parts.TreePart;
import de.willuhn.jameica.messaging.Message;
import de.willuhn.jameica.messaging.MessageConsumer;
import de.willuhn.jameica.system.Application;
import de.willuhn.jameica.system.BackgroundTask;
import de.willuhn.jameica.system.Platform;
import de.willuhn.jameica.system.Settings;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;
import de.willuhn.util.ProgressMonitor;

public class MitgliedControl extends AbstractControl
{
  private SelectInput adresstyp;

  private IntegerInput externemitgliedsnummer;

  private TextInput mitgliedsnummer;

  private Input anrede;

  private Input titel;

  private SearchInput name;

  private SearchInput vorname;

  private Input adressierungszusatz;

  private SearchInput strasse;

  private Input plz;

  private Input ort;

  private Input staat;

  private DateInput geburtsdatum = null;

  private GeschlechtInput geschlecht;

  private SelectInput zahlungsweg;

  private SelectInput zahlungsrhytmus;

  private Input blz;

  private Input konto;

  private Input iban;

  private Input kontoinhaber;

  private Input telefonprivat;

  private Input telefondienstlich;

  private Input handy;

  private Input email;

  private DateInput eintritt = null;

  private SelectInput beitragsgruppe;

  private DecimalInput individuellerbeitrag;

  private Familienverband famverb;

  private TreePart familienbeitragtree;

  private SelectInput zahler;

  private DateInput austritt = null;

  private DateInput kuendigung = null;

  private DateInput sterbetag = null;

  private Input[] zusatzfelder;

  private TreePart eigenschaftenTree;

  private TreePart eigenschaftenAuswahlTree;

  // Elemente f�r die Auswertung
  private TextInput auswertungUeberschrift = null;

  private SelectInput suchadresstyp = null;

  private DateInput geburtsdatumvon = null;

  private DateInput geburtsdatumbis = null;

  private DateInput sterbedatumvon = null;

  private DateInput sterbedatumbis = null;

  private DateInput eintrittvon = null;

  private DateInput eintrittbis;

  private DateInput austrittvon;

  private DateInput austrittbis;

  private TextAreaInput vermerk1;

  private TextAreaInput vermerk2;

  private SelectInput ausgabe;

  private SelectInput sortierung;

  private SelectInput status;

  private DateInput stichtag;

  private SelectInput jubeljahr;

  private SelectInput jubelart;

  public final static String JUBELART_MITGLIEDSCHAFT = "Mitgliedschaftsjubil�en";

  public final static String JUBELART_ALTER = "Altersjubil�en";

  private SelectInput beitragsgruppeausw;

  private DialogInput eigenschaftenabfrage;

  private DialogInput zusatzfelderabfrage;

  private IntegerInput suchexternemitgliedsnummer;

  private CheckboxInput ohneMail;

  private Mitglied mitglied;

  private FamilienbeitragMessageConsumer fbc = null;

  // Liste aller Zusatzbetr�ge
  private TablePart zusatzbetraegeList;

  // Liste der Wiedervorlagen
  private TablePart wiedervorlageList;

  // Liste der Arbeitseins�tze
  private TablePart arbeitseinsatzList;

  // Liste der Lehrg�nge
  private TablePart lehrgaengeList;

  private TablePart familienangehoerige;

  private ImageInput foto;

  private Settings settings = null;

  public MitgliedControl(AbstractView view)
  {
    super(view);
    settings = new Settings(this.getClass());
    settings.setStoreWhenRead(true);
  }

  public Mitglied getMitglied()
  {
    if (mitglied != null)
    {
      return mitglied;
    }
    mitglied = (Mitglied) getCurrentObject();
    return mitglied;
  }

  public void setMitglied(Mitglied mitglied)
  {
    this.mitglied = mitglied;
  }

  /**
   * 
   * @param typ
   *          1=Mitglieder 2= alle ohne Mitglieder
   * @return
   * @throws RemoteException
   */
  public SelectInput getSuchAdresstyp(int typ) throws RemoteException
  {
    if (suchadresstyp != null)
    {
      return suchadresstyp;
    }
    DBIterator at = Einstellungen.getDBService().createList(Adresstyp.class);
    switch (typ)
    {
      case 1:
        at.addFilter("jvereinid = 1");
        break;
      case 2:
        at.addFilter("jvereinid != 1 or jvereinid is null");
        break;
    }
    at.addFilter("jvereinid != 1 or jvereinid is null");
    at.setOrder("order by bezeichnung");
    suchadresstyp = new SelectInput(at, null);
    suchadresstyp.setName("Adresstyp");
    return suchadresstyp;
  }

  public SelectInput getAdresstyp() throws RemoteException
  {
    if (adresstyp != null)
    {
      return adresstyp;
    }
    DBIterator at = Einstellungen.getDBService().createList(Adresstyp.class);
    at.addFilter("jvereinid != 1 or jvereinid is null");
    at.setOrder("order by bezeichnung");
    adresstyp = new SelectInput(at, getMitglied().getAdresstyp());
    adresstyp.setName("Adresstyp");
    return adresstyp;
  }

  public IntegerInput getExterneMitgliedsnummer() throws RemoteException
  {
    if (externemitgliedsnummer != null)
    {
      return externemitgliedsnummer;
    }
    Integer ex = getMitglied().getExterneMitgliedsnummer();
    if (ex == null)
    {
      ex = -1;
    }
    externemitgliedsnummer = new IntegerInput(ex);
    externemitgliedsnummer.setName("Ext. Mitgliedsnummer");
    return externemitgliedsnummer;
  }

  public TextInput getMitgliedsnummer() throws RemoteException
  {
    if (mitgliedsnummer != null)
    {
      return mitgliedsnummer;
    }
    mitgliedsnummer = new TextInput(getMitglied().getID(), 10);
    mitgliedsnummer.setName("Mitgliedsnummer");
    mitgliedsnummer.setEnabled(false);
    return mitgliedsnummer;
  }

  public Input getAnrede() throws RemoteException
  {
    if (anrede != null)
    {
      return anrede;
    }
    anrede = new TextInput(getMitglied().getAnrede(), 40);
    anrede.setName("Anrede");
    return anrede;
  }

  public Input getTitel() throws RemoteException
  {
    if (titel != null)
    {
      return titel;
    }
    titel = new TextInput(getMitglied().getTitel(), 40);
    titel.setName("Titel");
    return titel;
  }

  public SearchInput getName(boolean withFocus) throws RemoteException
  {
    if (name != null)
    {
      return name;
    }

    name = new SearchInput()
    {

      @Override
      public List<?> startSearch(String text)
      {
        try
        {
          if (text != null)
          {
            text = text + "%";
          }
          ResultSetExtractor rs = new ResultSetExtractor()
          {

            public Object extract(ResultSet rs) throws SQLException
            {
              List<String> namen = new ArrayList<String>();
              while (rs.next())
              {
                namen.add(rs.getString(1));
              }
              return namen;
            }
          };
          String sql = "select name from mitglied where name like ? "
              + "group by name order by name";
          return (List<?>) Einstellungen.getDBService().execute(sql,
              new Object[] { text }, rs);
        }
        catch (Exception e)
        {
          Logger.error("kann Namenliste nicht aufbauen", e);
          return null;
        }
      }
    };
    name.setValue(getMitglied().getName());
    name.setName("Name");
    name.setMaxLength(40);
    name.setDelay(Einstellungen.getEinstellung().getDelaytime());
    name.setMandatory(true);
    name.setSearchString("");
    if (withFocus)
    {
      name.focus();
    }
    return name;
  }

  public SearchInput getVorname() throws RemoteException
  {
    if (vorname != null)
    {
      return vorname;
    }

    vorname = new SearchInput()
    {

      @Override
      public List<?> startSearch(String text)
      {
        try
        {
          if (text != null)
          {
            text = text + "%";
          }
          ResultSetExtractor rs = new ResultSetExtractor()
          {

            public Object extract(ResultSet rs) throws SQLException
            {
              List<String> vornamen = new ArrayList<String>();
              while (rs.next())
              {
                vornamen.add(rs.getString(1));
              }
              return vornamen;
            }
          };
          String sql = "select vorname from mitglied where vorname like ? "
              + "group by vorname order by vorname";
          return (List<?>) Einstellungen.getDBService().execute(sql,
              new Object[] { text }, rs);
        }
        catch (Exception e)
        {
          Logger.error("kann Vornamenliste nicht aufbauen", e);
          return null;
        }
      }
    };
    vorname.setValue(getMitglied().getVorname());
    vorname.setName("Vorname");
    vorname.setMaxLength(40);
    vorname.setDelay(Einstellungen.getEinstellung().getDelaytime());
    vorname.setSearchString("");
    vorname.setMandatory(true);
    return vorname;
  }

  public Input getAdressierungszusatz() throws RemoteException
  {
    if (adressierungszusatz != null)
    {
      return adressierungszusatz;
    }
    adressierungszusatz = new TextInput(getMitglied().getAdressierungszusatz(),
        40);
    adressierungszusatz.setName("Adressierungszusatz");
    return adressierungszusatz;
  }

  public SearchInput getStrasse() throws RemoteException
  {
    if (strasse != null)
    {
      return strasse;
    }
    // strasse = new TextInput(getMitglied().getStrasse(), 40);

    strasse = new SearchInput()
    {

      @Override
      public List<?> startSearch(String text)
      {
        try
        {
          if (text != null)
          {
            text = text + "%";
          }
          ResultSetExtractor rs = new ResultSetExtractor()
          {

            public Object extract(ResultSet rs) throws SQLException
            {
              List<String> strassen = new ArrayList<String>();
              while (rs.next())
              {
                strassen.add(rs.getString(1));
              }
              return strassen;
            }
          };
          String sql = "select strasse from mitglied where strasse like ? "
              + "group by strasse order by strasse";
          return (List<?>) Einstellungen.getDBService().execute(sql,
              new Object[] { text }, rs);
        }
        catch (Exception e)
        {
          Logger.error("kann Stra�enliste nicht aufbauen", e);
          return null;
        }
      }
    };
    strasse.setValue(getMitglied().getStrasse());
    strasse.setText("bla");
    strasse.setName("Stra�e");
    strasse.setMaxLength(40);
    strasse.setDelay(Einstellungen.getEinstellung().getDelaytime());
    strasse.setSearchString("");

    return strasse;
  }

  public Input getPlz() throws RemoteException
  {
    if (plz != null)
    {
      return plz;
    }
    plz = new TextInput(getMitglied().getPlz(), 10);
    plz.setName("PLZ");
    plz.addListener(new Listener()
    {

      public void handleEvent(Event event)
      {
        if (event.type == SWT.FocusOut)
        {
          try
          {
            DBIterator it = Einstellungen.getDBService().createList(
                Mitglied.class);
            it.addFilter("plz='" + (String) plz.getValue() + "'");
            if (it.hasNext())
            {
              Mitglied mplz = (Mitglied) it.next();
              ort.setValue(mplz.getOrt());
            }
          }
          catch (RemoteException e)
          {
            e.printStackTrace();
          }
        }
      }
    });
    return plz;
  }

  public Input getOrt() throws RemoteException
  {
    if (ort != null)
    {
      return ort;
    }
    ort = new TextInput(getMitglied().getOrt(), 40);
    ort.setName("Ort");
    return ort;
  }

  public Input getStaat() throws RemoteException
  {
    if (staat != null)
    {
      return staat;
    }
    staat = new TextInput(getMitglied().getStaat(), 50);
    staat.setName("Staat");
    return staat;
  }

  public DateInput getGeburtsdatum() throws RemoteException
  {
    if (geburtsdatum != null)
    {
      return geburtsdatum;
    }
    Date d = getMitglied().getGeburtsdatum();
    if (d.equals(Einstellungen.NODATE))
    {
      d = null;
    }
    this.geburtsdatum = new DateInput(d, new JVDateFormatTTMMJJJJ());
    this.geburtsdatum.setName("Geburtsdatum");
    this.geburtsdatum.setTitle("Geburtsdatum");
    this.geburtsdatum.setText("Bitte Geburtsdatum w�hlen");
    this.geburtsdatum.setMandatory(Einstellungen.getEinstellung()
        .getGeburtsdatumPflicht());
    this.geburtsdatum.addListener(new Listener()
    {

      public void handleEvent(Event event)
      {
        Date date = (Date) geburtsdatum.getValue();
        if (date == null)
        {
          return;
        }
      }
    });
    return geburtsdatum;
  }

  public GeschlechtInput getGeschlecht() throws RemoteException
  {
    if (geschlecht != null)
    {
      return geschlecht;
    }
    geschlecht = new GeschlechtInput(getMitglied().getGeschlecht());
    geschlecht.setName("Geschlecht");
    geschlecht.setPleaseChoose("Bitte ausw�hlen");
    geschlecht.setMandatory(true);
    geschlecht.setName(JVereinPlugin.getI18n().tr("Geschlecht"));
    return geschlecht;
  }

  public SelectInput getZahlungsweg() throws RemoteException
  {
    if (zahlungsweg != null)
    {
      return zahlungsweg;
    }
    if (getMitglied().getZahlungsweg() != null)
    {
      zahlungsweg = new SelectInput(Zahlungsweg.getArray(), new Zahlungsweg(
          getMitglied().getZahlungsweg().intValue()));
    }
    else
    {
      zahlungsweg = new SelectInput(Zahlungsweg.getArray(), new Zahlungsweg(
          Einstellungen.getEinstellung().getZahlungsweg()));
    }
    zahlungsweg.setName("Zahlungsweg");
    zahlungsweg.addListener(new Listener()
    {

      public void handleEvent(Event event)
      {
        Zahlungsweg z = (Zahlungsweg) zahlungsweg.getValue();
        blz.setMandatory(z.getKey() == Zahlungsweg.ABBUCHUNG);
        konto.setMandatory(z.getKey() == Zahlungsweg.ABBUCHUNG);
      }
    });
    return zahlungsweg;
  }

  public SelectInput getZahlungsrhytmus() throws RemoteException
  {
    if (zahlungsrhytmus != null)
    {
      return zahlungsrhytmus;
    }
    if (getMitglied().getZahlungsrhytmus() != null)
    {
      zahlungsrhytmus = new SelectInput(Zahlungsrhytmus.getArray(),
          new Zahlungsrhytmus(getMitglied().getZahlungsrhytmus().intValue()));
    }
    else
    {
      zahlungsrhytmus = new SelectInput(Zahlungsrhytmus.getArray(),
          new Zahlungsrhytmus(Einstellungen.getEinstellung()
              .getZahlungsrhytmus()));
    }
    zahlungsrhytmus.setName("Zahlungsrhytmus");
    return zahlungsrhytmus;
  }

  public Input getBlz() throws RemoteException
  {
    if (blz != null)
    {
      return blz;
    }
    blz = new TextInput(getMitglied().getBlz(), 8);
    blz.setName("BLZ");
    blz.setMandatory(getMitglied().getZahlungsweg() == null
        || getMitglied().getZahlungsweg().intValue() == Zahlungsweg.ABBUCHUNG);
    BLZListener l = new BLZListener();
    blz.addListener(l);
    l.handleEvent(null); // Einmal initial ausfuehren
    blz.addListener(new Listener()
    {

      public void handleEvent(Event arg0)
      {
        try
        {
          getIban().setValue(
              IbanBicCalc.createIban((String) getKonto().getValue(),
                  (String) getBlz().getValue(), "DE"));
        }
        catch (RemoteException e)
        {
          //
        }
      }

    });
    return blz;
  }

  public Input getKonto() throws RemoteException
  {
    if (konto != null)
    {
      return konto;
    }
    konto = new TextInput(getMitglied().getKonto(), 10);
    konto.setName("Konto");
    konto.setMandatory(getMitglied().getZahlungsweg() == null
        || getMitglied().getZahlungsweg().intValue() == Zahlungsweg.ABBUCHUNG);
    konto.addListener(new Listener()
    {

      public void handleEvent(Event arg0)
      {
        try
        {
          getIban().setValue(
              IbanBicCalc.createIban((String) getKonto().getValue(),
                  (String) getBlz().getValue(), "DE"));
        }
        catch (RemoteException e)
        {
          //
        }
      }
    });
    return konto;
  }

  public Input getIban() throws RemoteException
  {
    if (iban != null)
    {
      return iban;
    }
    iban = new TextInput(getMitglied().getIban(), 30);
    iban.setName("IBAN");
    iban.setMandatory(getMitglied().getZahlungsweg() == null
        || getMitglied().getZahlungsweg().intValue() == Zahlungsweg.ABBUCHUNG);
    iban.setEnabled(false);
    return iban;
  }

  public Input getKontoinhaber() throws RemoteException
  {
    if (kontoinhaber != null)
    {
      return kontoinhaber;
    }
    kontoinhaber = new TextInput(getMitglied().getKontoinhaber(), 27);
    kontoinhaber.setName("Kontoinhaber");
    return kontoinhaber;
  }

  public Input getTelefonprivat() throws RemoteException
  {
    if (telefonprivat != null)
    {
      return telefonprivat;
    }
    telefonprivat = new TextInput(getMitglied().getTelefonprivat(), 20);
    telefonprivat.setName("Telefon priv.");
    return telefonprivat;
  }

  public Input getTelefondienstlich() throws RemoteException
  {
    if (telefondienstlich != null)
    {
      return telefondienstlich;
    }
    telefondienstlich = new TextInput(getMitglied().getTelefondienstlich(), 20);
    telefondienstlich.setName("Telefon dienstl.");
    return telefondienstlich;
  }

  public Input getHandy() throws RemoteException
  {
    if (handy != null)
    {
      return handy;
    }
    handy = new TextInput(getMitglied().getHandy(), 20);
    handy.setName("Handy");
    return handy;
  }

  public Input getEmail() throws RemoteException
  {
    if (email != null)
    {
      return email;
    }
    email = new TextInput(getMitglied().getEmail(), 50);
    email.setName("EMail");
    return email;
  }

  public DateInput getEintritt() throws RemoteException
  {
    if (eintritt != null)
    {
      return eintritt;
    }

    Date d = getMitglied().getEintritt();
    if (d.equals(Einstellungen.NODATE))
    {
      d = null;
    }
    this.eintritt = new DateInput(d, new JVDateFormatTTMMJJJJ());
    this.eintritt.setTitle("Eintrittsdatum");
    this.eintritt.setName("Eintrittsdatum");
    this.eintritt.setText("Bitte Eintrittsdatum w�hlen");
    this.eintritt.setMandatory(Einstellungen.getEinstellung()
        .getEintrittsdatumPflicht());
    this.eintritt.addListener(new Listener()
    {

      public void handleEvent(Event event)
      {
        Date date = (Date) eintritt.getValue();
        if (date == null)
        {
          return;
        }
      }
    });
    return eintritt;
  }

  public Input getBeitragsgruppe(boolean allgemein) throws RemoteException
  {
    if (beitragsgruppe != null)
    {
      return beitragsgruppe;
    }
    DBIterator list = Einstellungen.getDBService().createList(
        Beitragsgruppe.class);
    list.setOrder("ORDER BY bezeichnung");
    if (!allgemein)
    {
      list.addFilter("beitragsart <> ?",
          new Object[] { ArtBeitragsart.FAMILIE_ANGEHOERIGER });
    }
    beitragsgruppe = new SelectInput(list, getMitglied().getBeitragsgruppe());
    beitragsgruppe.setName("Beitragsgruppe");
    beitragsgruppe.setValue(getMitglied().getBeitragsgruppe());
    beitragsgruppe.setMandatory(true);
    beitragsgruppe.setAttribute("bezeichnung");
    beitragsgruppe.setPleaseChoose("Bitte ausw�hlen");
    beitragsgruppe.addListener(new Listener()
    {

      public void handleEvent(Event event)
      {
        if (event.type != SWT.Selection)
        {
          return;
        }
        try
        {
          Beitragsgruppe bg = (Beitragsgruppe) beitragsgruppe.getValue();
          if (famverb != null)
          {
            famverb.setBeitragsgruppe(bg);
          }
          if (bg.getBeitragsArt() == ArtBeitragsart.FAMILIE_ANGEHOERIGER)
          {
            zahler.setEnabled(true);
          }
          else if (bg.getBeitragsArt() == ArtBeitragsart.FAMILIE_ZAHLER)
          {
            zahler.setValue(Einstellungen.getDBService().createObject(
                Mitglied.class, ""));
            getMitglied().setZahlerID(null);
            zahler.setEnabled(false);
          }
        }
        catch (RemoteException e)
        {
          e.printStackTrace();
        }
      }
    });
    return beitragsgruppe;
  }

  public DecimalInput getIndividuellerBeitrag() throws RemoteException
  {
    if (individuellerbeitrag != null)
    {
      return individuellerbeitrag;
    }
    individuellerbeitrag = new DecimalInput(getMitglied()
        .getIndividuellerBeitrag(), Einstellungen.DECIMALFORMAT);
    individuellerbeitrag.setName("individueller Beitrag");
    return individuellerbeitrag;
  }

  public TextInput getAuswertungUeberschrift()
  {
    if (auswertungUeberschrift != null)
    {
      return auswertungUeberschrift;
    }
    auswertungUeberschrift = new TextInput(settings.getString(
        "auswertung.ueberschrift", ""));
    auswertungUeberschrift.setName(JVereinPlugin.getI18n().tr("�berschrift"));
    return auswertungUeberschrift;
  }

  public SelectInput getBeitragsgruppeAusw() throws RemoteException
  {
    if (beitragsgruppeausw != null)
    {
      return beitragsgruppeausw;
    }
    Beitragsgruppe bg = null;
    String beitragsgru = settings.getString("mitglied.beitragsgruppe", "");
    if (beitragsgru.length() > 0)
    {
      try
      {
        bg = (Beitragsgruppe) Einstellungen.getDBService().createObject(
            Beitragsgruppe.class, beitragsgru);
      }
      catch (ObjectNotFoundException e)
      {
        bg = (Beitragsgruppe) Einstellungen.getDBService().createObject(
            Beitragsgruppe.class, null);
      }
    }
    DBIterator list = Einstellungen.getDBService().createList(
        Beitragsgruppe.class);
    list.setOrder("ORDER BY bezeichnung");
    beitragsgruppeausw = new SelectInput(list, bg);
    beitragsgruppeausw.setName("Beitragsgruppe");
    beitragsgruppeausw.setAttribute("bezeichnung");
    beitragsgruppeausw.setPleaseChoose("Bitte ausw�hlen");
    beitragsgruppeausw.setName(JVereinPlugin.getI18n().tr("Beitragsgruppe"));
    return beitragsgruppeausw;
  }

  public Familienverband getFamilienverband() throws RemoteException
  {
    if (famverb != null)
    {
      return famverb;
    }
    famverb = new Familienverband(this, getMitglied().getBeitragsgruppe());
    return famverb;
  }

  public Input getZahler() throws RemoteException
  {
    if (zahler != null)
    {
      return zahler;
    }

    StringBuffer cond = new StringBuffer();

    // Beitragsgruppen ermitteln, die Zahler f�r andere Mitglieder sind
    DBIterator bg = Einstellungen.getDBService().createList(
        Beitragsgruppe.class);
    bg.addFilter("beitragsart = 1");
    while (bg.hasNext())
    {
      if (cond.length() > 0)
      {
        cond.append(" OR ");
      }
      Beitragsgruppe beitragsgruppe = (Beitragsgruppe) bg.next();
      cond.append("beitragsgruppe = ");
      cond.append(beitragsgruppe.getID());
    }
    DBIterator zhl = Einstellungen.getDBService().createList(Mitglied.class);
    zhl.addFilter(cond.toString());
    MitgliedUtils.setNurAktive(zhl);
    MitgliedUtils.setMitglied(zhl);
    zhl.setOrder("ORDER BY name, vorname");

    String suche = "";
    if (getMitglied().getZahlerID() != null)
    {
      suche = getMitglied().getZahlerID().toString();
    }
    Mitglied zahlmitglied = (Mitglied) Einstellungen.getDBService()
        .createObject(Mitglied.class, suche);

    zahler = new SelectInput(zhl, zahlmitglied);
    zahler.setAttribute("namevorname");
    zahler.setPleaseChoose("Bitte ausw�hlen");
    zahler.addListener(new Listener()
    {

      public void handleEvent(Event event)
      {
        if (event.type != SWT.Selection)
        {
          return;
        }
        try
        {
          Mitglied m = (Mitglied) zahler.getValue();
          if (m.getID() != null)
          {
            getMitglied().setZahlerID(new Integer(m.getID()));
          }
          else
          {
            getMitglied().setZahlerID(null);
          }
        }
        catch (RemoteException e)
        {
          e.printStackTrace();
        }
      }
    });

    if (getBeitragsgruppe(true) != null
        && getBeitragsgruppe(true).getValue() != null)
    {
      Beitragsgruppe bg2 = (Beitragsgruppe) getBeitragsgruppe(true).getValue();
      if (bg2.getBeitragsArt() == ArtBeitragsart.FAMILIE_ANGEHOERIGER)
      {
        zahler.setEnabled(true);
      }
      else
      {
        zahler.setEnabled(false);
      }
    }
    return zahler;
  }

  public DateInput getAustritt() throws RemoteException
  {
    if (austritt != null)
    {
      return austritt;
    }
    Date d = getMitglied().getAustritt();

    this.austritt = new DateInput(d, new JVDateFormatTTMMJJJJ());
    this.austritt.setTitle("Austrittsdatum");
    this.austritt.setName("Austrittsdatum");
    this.austritt.setText("Bitte Austrittsdatum w�hlen");
    this.austritt.addListener(new Listener()
    {

      public void handleEvent(Event event)
      {
        Date date = (Date) austritt.getValue();
        if (date == null)
        {
          return;
        }
      }
    });
    return austritt;
  }

  public DateInput getKuendigung() throws RemoteException
  {
    if (kuendigung != null)
    {
      return kuendigung;
    }
    Date d = getMitglied().getKuendigung();

    this.kuendigung = new DateInput(d, new JVDateFormatTTMMJJJJ());
    this.kuendigung.setName("K�ndigungsdatum");
    this.kuendigung.setTitle("K�ndigungsdatum");
    this.kuendigung.setText("Bitte K�ndigungsdatum w�hlen");
    this.kuendigung.addListener(new Listener()
    {

      public void handleEvent(Event event)
      {
        Date date = (Date) kuendigung.getValue();
        if (date == null)
        {
          return;
        }
      }
    });
    return kuendigung;
  }

  public DateInput getSterbetag() throws RemoteException
  {
    if (sterbetag != null)
    {
      return sterbetag;
    }
    Date d = getMitglied().getSterbetag();

    this.sterbetag = new DateInput(d, new JVDateFormatTTMMJJJJ());
    this.sterbetag.setName("Sterbetag");
    this.sterbetag.setTitle("Sterbetag");
    this.sterbetag.setText("Bitte Sterbetag w�hlen");
    this.sterbetag.addListener(new Listener()
    {

      public void handleEvent(Event event)
      {
        Date date = (Date) sterbetag.getValue();
        if (date == null)
        {
          return;
        }
      }
    });
    return sterbetag;
  }

  public TextAreaInput getVermerk1() throws RemoteException
  {
    if (vermerk1 != null)
    {
      return vermerk1;
    }
    vermerk1 = new TextAreaInput(getMitglied().getVermerk1(), 255);
    vermerk1.setName("Vermerk 1");
    return vermerk1;
  }

  public TextAreaInput getVermerk2() throws RemoteException
  {
    if (vermerk2 != null)
    {
      return vermerk2;
    }
    vermerk2 = new TextAreaInput(getMitglied().getVermerk2(), 255);
    vermerk2.setName("Vermerk 2");
    return vermerk2;
  }

  public ImageInput getFoto() throws RemoteException
  {
    if (foto != null)
    {
      return foto;
    }
    DBIterator it = Einstellungen.getDBService().createList(Mitgliedfoto.class);
    it.addFilter("mitglied = ?", new Object[] { mitglied.getID() });
    Mitgliedfoto fo = null;
    if (it.size() > 0)
    {
      fo = (Mitgliedfoto) it.next();
    }
    byte[] f = null;
    if (fo != null)
    {
      f = fo.getFoto();
    }
    foto = new ImageInput(f, 150, 200);
    foto.setScale(Application.getPlatform().getOS() != Platform.OS_WINDOWS);
    return foto;
  }

  public Input[] getZusatzfelder() throws RemoteException
  {
    if (zusatzfelder != null)
    {
      return zusatzfelder;
    }
    DBIterator it = Einstellungen.getDBService().createList(
        Felddefinition.class);
    int anzahl = it.size();
    if (anzahl == 0)
    {
      return null;
    }
    zusatzfelder = new Input[anzahl];
    Zusatzfelder zf = null;
    int i = 0;
    while (it.hasNext())
    {
      Felddefinition fd = (Felddefinition) it.next();
      zf = (Zusatzfelder) Einstellungen.getDBService().createObject(
          Zusatzfelder.class, null);
      zf.setFelddefinition(Integer.parseInt(fd.getID()));

      if (getMitglied().getID() != null)
      {
        DBIterator it2 = Einstellungen.getDBService().createList(
            Zusatzfelder.class);
        it2.addFilter("mitglied=?", new Object[] { getMitglied().getID() });
        it2.addFilter("felddefinition=?", new Object[] { fd.getID() });
        if (it2.size() > 0)
        {
          zf.setMitglied(Integer.parseInt(getMitglied().getID()));
          zf = (Zusatzfelder) it2.next();
        }
      }
      switch (fd.getDatentyp())
      {
        case Datentyp.ZEICHENFOLGE:
          zusatzfelder[i] = new TextInput(zf.getFeld(), fd.getLaenge());
          break;
        case Datentyp.DATUM:
          Date d = zf.getFeldDatum();
          DateInput di = new DateInput(d, new JVDateFormatTTMMJJJJ());
          di.setName(fd.getLabel());
          di.setTitle(fd.getLabel());
          di.setText("Bitte " + fd.getLabel() + " w�hlen");
          zusatzfelder[i] = di;
          break;
        case Datentyp.GANZZAHL:
          if (zf.getFeldGanzzahl() == null)
          {
            zf.setFeldGanzzahl(0);
          }
          zusatzfelder[i] = new IntegerInput(zf.getFeldGanzzahl());
          break;
        case Datentyp.WAEHRUNG:
          zusatzfelder[i] = new DecimalInput(zf.getFeldWaehrung(),
              Einstellungen.DECIMALFORMAT);
          break;
        case Datentyp.JANEIN:
          zusatzfelder[i] = new CheckboxInput(zf.getFeldJaNein());
          break;
        default:
          zusatzfelder[i] = new TextInput("", fd.getLaenge());
          break;
      }
      zusatzfelder[i].setName(fd.getLabel());
      if (fd.getLabel() == null)
      {
        zusatzfelder[i].setName(fd.getName());
      }
      i++;
    }
    return zusatzfelder;
  }

  public Part getFamilienangehoerigenTable() throws RemoteException
  {
    if (familienangehoerige != null)
    {
      return familienangehoerige;
    }
    DBService service = Einstellungen.getDBService();
    DBIterator famiter = service.createList(Mitglied.class);
    famiter.addFilter("zahlerid = " + getMitglied().getID());
    familienangehoerige = new TablePart(famiter, null);
    familienangehoerige.setRememberColWidths(true);
    familienangehoerige.setRememberOrder(true);

    familienangehoerige.addColumn("Name", "name");
    familienangehoerige.addColumn("Vorname", "vorname");

    return familienangehoerige;
  }

  public Part getZusatzbetraegeTable() throws RemoteException
  {
    if (zusatzbetraegeList != null)
    {
      return zusatzbetraegeList;
    }
    DBService service = Einstellungen.getDBService();
    DBIterator zusatzbetraege = service.createList(Zusatzbetrag.class);
    zusatzbetraege.addFilter("mitglied = " + getMitglied().getID());
    zusatzbetraegeList = new TablePart(zusatzbetraege,
        new ZusatzbetraegeAction(getMitglied()));
    zusatzbetraegeList.setRememberColWidths(true);
    zusatzbetraegeList.setRememberOrder(true);

    zusatzbetraegeList.addColumn("Startdatum", "startdatum", new DateFormatter(
        new JVDateFormatTTMMJJJJ()));
    zusatzbetraegeList.addColumn("n�chste F�lligkeit", "faelligkeit",
        new DateFormatter(new JVDateFormatTTMMJJJJ()));
    zusatzbetraegeList.addColumn("letzte Ausf�hrung", "ausfuehrung",
        new DateFormatter(new JVDateFormatTTMMJJJJ()));
    zusatzbetraegeList.addColumn("Intervall", "intervalltext");
    zusatzbetraegeList.addColumn("Endedatum", "endedatum", new DateFormatter(
        new JVDateFormatTTMMJJJJ()));
    zusatzbetraegeList.addColumn("Buchungstext 1", "buchungstext");
    zusatzbetraegeList.addColumn("Buchungstext 2", "buchungstext2");
    zusatzbetraegeList.addColumn("Betrag", "betrag", new CurrencyFormatter("",
        Einstellungen.DECIMALFORMAT));
    zusatzbetraegeList.addColumn("aktiv", "aktiv", new JaNeinFormatter());
    zusatzbetraegeList
        .setContextMenu(new ZusatzbetraegeMenu(zusatzbetraegeList));
    return zusatzbetraegeList;
  }

  public Part getWiedervorlageTable() throws RemoteException
  {
    if (wiedervorlageList != null)
    {
      return wiedervorlageList;
    }
    DBService service = Einstellungen.getDBService();
    DBIterator wiedervorlagen = service.createList(Wiedervorlage.class);
    wiedervorlagen.addFilter("mitglied = " + getMitglied().getID());
    wiedervorlageList = new TablePart(wiedervorlagen, new WiedervorlageAction(
        getMitglied()));
    wiedervorlageList.setRememberColWidths(true);
    wiedervorlageList.setRememberOrder(true);

    wiedervorlageList.addColumn("Datum", "datum", new DateFormatter(
        new JVDateFormatTTMMJJJJ()));
    wiedervorlageList.addColumn("Vermerk", "vermerk");
    wiedervorlageList.addColumn("Erledigung", "erledigung", new DateFormatter(
        new JVDateFormatTTMMJJJJ()));
    wiedervorlageList.setContextMenu(new WiedervorlageMenu(wiedervorlageList));
    return wiedervorlageList;
  }

  public Part getArbeitseinsatzTable() throws RemoteException
  {
    if (arbeitseinsatzList != null)
    {
      return arbeitseinsatzList;
    }
    DBService service = Einstellungen.getDBService();
    DBIterator arbeitseinsaetze = service.createList(Arbeitseinsatz.class);
    arbeitseinsaetze.addFilter("mitglied = " + getMitglied().getID());
    arbeitseinsaetze.setOrder("ORDER by datum desc");
    arbeitseinsatzList = new TablePart(arbeitseinsaetze,
        new ArbeitseinsatzAction(mitglied));
    arbeitseinsatzList.setRememberColWidths(true);
    arbeitseinsatzList.setRememberOrder(true);
    arbeitseinsatzList.setContextMenu(new ArbeitseinsatzMenu());

    arbeitseinsatzList.addColumn("Datum", "datum", new DateFormatter(
        new JVDateFormatTTMMJJJJ()));
    arbeitseinsatzList.addColumn("Stunden", "stunden", new CurrencyFormatter(
        "", Einstellungen.DECIMALFORMAT));
    arbeitseinsatzList.addColumn("Bemerkung", "bemerkung");
    // wiedervorlageList.setContextMenu(new
    // WiedervorlageMenu(wiedervorlageList));
    return arbeitseinsatzList;
  }

  public Part getLehrgaengeTable() throws RemoteException
  {
    if (lehrgaengeList != null)
    {
      return lehrgaengeList;
    }
    DBService service = Einstellungen.getDBService();
    DBIterator lehrgaenge = service.createList(Lehrgang.class);
    lehrgaenge.addFilter("mitglied = " + getMitglied().getID());
    lehrgaengeList = new TablePart(lehrgaenge,
        new LehrgangAction(getMitglied()));
    lehrgaengeList.setRememberColWidths(true);
    lehrgaengeList.setRememberOrder(true);

    lehrgaengeList.addColumn("Lehrgangsart", "lehrgangsart");
    lehrgaengeList.addColumn("von/am", "von", new DateFormatter(
        new JVDateFormatTTMMJJJJ()));
    lehrgaengeList.addColumn("bis", "bis", new DateFormatter(
        new JVDateFormatTTMMJJJJ()));
    lehrgaengeList.addColumn("Veranstalter", "veranstalter");
    lehrgaengeList.addColumn("Ergebnis", "ergebnis");
    lehrgaengeList.setContextMenu(new LehrgangMenu());
    return lehrgaengeList;
  }

  public DateInput getGeburtsdatumvon()
  {
    if (geburtsdatumvon != null)
    {
      return geburtsdatumvon;
    }
    Date d = null;
    String tmp = settings.getString("mitglied.geburtsdatumvon", null);
    if (tmp != null)
    {
      try
      {
        d = new JVDateFormatTTMMJJJJ().parse(tmp);
      }
      catch (ParseException e)
      {
        //
      }
    }
    this.geburtsdatumvon = new DateInput(d, new JVDateFormatTTMMJJJJ());
    this.geburtsdatumvon.setTitle("Geburtsdatum");
    this.geburtsdatumvon.setText("Beginn des Geburtszeitraumes");
    this.geburtsdatumvon.addListener(new Listener()
    {

      public void handleEvent(Event event)
      {
        Date date = (Date) geburtsdatumvon.getValue();
        if (date == null)
        {
          return;
        }
      }
    });
    geburtsdatumvon.setName(JVereinPlugin.getI18n().tr("Geburtsdatum von"));
    return geburtsdatumvon;
  }

  public DateInput getGeburtsdatumbis()
  {
    if (geburtsdatumbis != null)
    {
      return geburtsdatumbis;
    }
    Date d = null;
    String tmp = settings.getString("mitglied.geburtsdatumbis", null);
    if (tmp != null)
    {
      try
      {
        d = new JVDateFormatTTMMJJJJ().parse(tmp);
      }
      catch (ParseException e)
      {
        //
      }
    }
    this.geburtsdatumbis = new DateInput(d, new JVDateFormatTTMMJJJJ());
    this.geburtsdatumbis.setTitle("Geburtsdatum");
    this.geburtsdatumbis.setText("Ende des Geburtszeitraumes");
    this.geburtsdatumbis.addListener(new Listener()
    {

      public void handleEvent(Event event)
      {
        Date date = (Date) geburtsdatumbis.getValue();
        if (date == null)
        {
          return;
        }
      }
    });
    geburtsdatumbis.setName(JVereinPlugin.getI18n().tr("Geburtsdatum bis"));
    return geburtsdatumbis;
  }

  public DateInput getSterbedatumvon()
  {
    if (sterbedatumvon != null)
    {
      return sterbedatumvon;
    }
    Date d = null;
    String tmp = settings.getString("mitglied.sterbedatumvon", null);
    if (tmp != null)
    {
      try
      {
        d = new JVDateFormatTTMMJJJJ().parse(tmp);
      }
      catch (ParseException e)
      {
        //
      }
    }
    this.sterbedatumvon = new DateInput(d, new JVDateFormatTTMMJJJJ());
    this.sterbedatumvon.setTitle("Sterbedatum");
    this.sterbedatumvon.setText("Beginn des Sterbezeitraumes");
    this.sterbedatumvon.addListener(new Listener()
    {

      public void handleEvent(Event event)
      {
        Date date = (Date) sterbedatumvon.getValue();
        if (date == null)
        {
          return;
        }
      }
    });
    sterbedatumvon.setName(JVereinPlugin.getI18n().tr("Sterbedatum von"));
    return sterbedatumvon;
  }

  public DateInput getSterbedatumbis()
  {
    if (sterbedatumbis != null)
    {
      return sterbedatumbis;
    }
    Date d = null;
    String tmp = settings.getString("mitglied.sterbedatumbis", null);
    if (tmp != null)
    {
      try
      {
        d = new JVDateFormatTTMMJJJJ().parse(tmp);
      }
      catch (ParseException e)
      {
        //
      }
    }
    this.sterbedatumbis = new DateInput(d, new JVDateFormatTTMMJJJJ());
    this.sterbedatumbis.setTitle("Sterbedatum");
    this.sterbedatumbis.setText("Ende des Sterbezeitraumes");
    this.sterbedatumbis.addListener(new Listener()
    {

      public void handleEvent(Event event)
      {
        Date date = (Date) sterbedatumbis.getValue();
        if (date == null)
        {
          return;
        }
      }
    });
    sterbedatumbis.setName(JVereinPlugin.getI18n().tr("Sterbedatum bis"));
    return sterbedatumbis;
  }

  public DateInput getEintrittvon()
  {
    if (eintrittvon != null)
    {
      return eintrittvon;
    }
    Date d = null;
    String tmp = settings.getString("mitglied.eintrittvon", null);
    if (tmp != null)
    {
      try
      {
        d = new JVDateFormatTTMMJJJJ().parse(tmp);
      }
      catch (ParseException e)
      {
        //
      }
    }
    this.eintrittvon = new DateInput(d, new JVDateFormatTTMMJJJJ());
    this.eintrittvon.setTitle("Eintrittsdatum");
    this.eintrittvon.setText("Beginn des Eintrittszeitraumes");
    this.eintrittvon.addListener(new Listener()
    {

      public void handleEvent(Event event)
      {
        Date date = (Date) eintrittvon.getValue();
        if (date == null)
        {
          return;
        }
      }
    });
    eintrittvon.setName(JVereinPlugin.getI18n().tr("Eintritt von"));
    return eintrittvon;
  }

  public boolean isEintrittbisAktiv()
  {
    return eintrittbis != null;
  }

  public DateInput getEintrittbis()
  {
    if (eintrittbis != null)
    {
      return eintrittbis;
    }
    Date d = null;
    String tmp = settings.getString("mitglied.eintrittbis", null);
    if (tmp != null)
    {
      try
      {
        d = new JVDateFormatTTMMJJJJ().parse(tmp);
      }
      catch (ParseException e)
      {
        //
      }
    }
    this.eintrittbis = new DateInput(d, new JVDateFormatTTMMJJJJ());
    this.eintrittbis.setTitle("Eintrittsdatum");
    this.eintrittbis.setText("Ende des Eintrittszeitraumes");
    this.eintrittbis.addListener(new Listener()
    {

      public void handleEvent(Event event)
      {
        Date date = (Date) eintrittbis.getValue();
        if (date == null)
        {
          return;
        }
      }
    });
    eintrittbis.setName(JVereinPlugin.getI18n().tr("Eintritt bis"));
    return eintrittbis;
  }

  public DateInput getAustrittvon()
  {
    if (austrittvon != null)
    {
      return austrittvon;
    }
    Date d = null;
    String tmp = settings.getString("mitglied.austrittvon", null);
    if (tmp != null)
    {
      try
      {
        d = new JVDateFormatTTMMJJJJ().parse(tmp);
      }
      catch (ParseException e)
      {
        //
      }
    }
    this.austrittvon = new DateInput(d, new JVDateFormatTTMMJJJJ());
    this.austrittvon.setTitle("Austrittsdatum");
    this.austrittvon.setText("Beginn des Austrittszeitraumes");
    this.austrittvon.addListener(new Listener()
    {

      public void handleEvent(Event event)
      {
        Date date = (Date) austrittvon.getValue();
        if (date == null)
        {
          return;
        }
      }
    });
    austrittvon.setName(JVereinPlugin.getI18n().tr("Austritt von"));
    return austrittvon;
  }

  public boolean isAustrittbisAktiv()
  {
    return austrittbis != null;
  }

  public DateInput getAustrittbis()
  {
    if (austrittbis != null)
    {
      return austrittbis;
    }
    Date d = null;
    String tmp = settings.getString("mitglied.austrittbis", null);
    if (tmp != null)
    {
      try
      {
        d = new JVDateFormatTTMMJJJJ().parse(tmp);
      }
      catch (ParseException e)
      {
        //
      }
    }
    this.austrittbis = new DateInput(d, new JVDateFormatTTMMJJJJ());
    this.austrittbis.setTitle("Austrittsdatum");
    this.austrittbis.setText("Ende des Austrittszeitraumes");
    this.austrittbis.addListener(new Listener()
    {

      public void handleEvent(Event event)
      {
        Date date = (Date) austrittbis.getValue();
        if (date == null)
        {
          return;
        }
      }
    });
    austrittbis.setName(JVereinPlugin.getI18n().tr("Austritt bis"));
    return austrittbis;
  }

  public DateInput getStichtag()
  {
    if (stichtag != null)
    {
      return stichtag;
    }
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.MONTH, Calendar.DECEMBER);
    cal.set(Calendar.DAY_OF_MONTH, 31);
    Date d = new Date(cal.getTimeInMillis());
    this.stichtag = new DateInput(d, new JVDateFormatTTMMJJJJ());
    this.stichtag.setTitle("Stichtag");
    this.stichtag.addListener(new Listener()
    {

      public void handleEvent(Event event)
      {
        Date date = (Date) stichtag.getValue();
        if (date == null)
        {
          return;
        }
      }
    });
    return stichtag;
  }

  public SelectInput getJubeljahr()
  {
    if (jubeljahr != null)
    {
      return jubeljahr;
    }
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.YEAR, -2);
    Integer[] jubeljahre = new Integer[5];
    for (int i = 0; i < 5; i++)
    {
      jubeljahre[i] = cal.get(Calendar.YEAR);
      cal.add(Calendar.YEAR, 1);
    }
    jubeljahr = new SelectInput(jubeljahre, jubeljahre[2]);
    return jubeljahr;
  }

  public SelectInput getJubelArt()
  {
    if (jubelart != null)
    {
      return jubelart;
    }
    String[] ja = { JUBELART_MITGLIEDSCHAFT, JUBELART_ALTER };
    jubelart = new SelectInput(ja, JUBELART_MITGLIEDSCHAFT);
    return jubelart;
  }

  public DialogInput getEigenschaftenAuswahl() throws RemoteException
  {
    String tmp = settings.getString("mitglied.eigenschaften", "");
    final EigenschaftenAuswahlDialog d = new EigenschaftenAuswahlDialog(tmp,
        false);
    d.addCloseListener(new EigenschaftenListener());

    StringTokenizer stt = new StringTokenizer(tmp, ",");
    StringBuilder text = new StringBuilder();
    while (stt.hasMoreElements())
    {
      if (text.length() > 0)
      {
        text.append(", ");
      }
      try
      {
        Eigenschaft ei = (Eigenschaft) Einstellungen.getDBService()
            .createObject(Eigenschaft.class, stt.nextToken());
        text.append(ei.getBezeichnung());
      }
      catch (ObjectNotFoundException e)
      {
        //
      }
    }
    eigenschaftenabfrage = new DialogInput(text.toString(), d);
    eigenschaftenabfrage.setName(JVereinPlugin.getI18n().tr("Eigenschaften"));
    eigenschaftenabfrage.addListener(new Listener()
    {

      public void handleEvent(Event event)
      {
        d.setDefaults(settings.getString("mitglied.eigenschaften", "xxx"));
      }
    });
    return eigenschaftenabfrage;
  }

  public DialogInput getZusatzfelderAuswahl()
  {
    if (zusatzfelderabfrage != null)
    {
      return zusatzfelderabfrage;
    }
    final ZusatzfelderAuswahlDialog d = new ZusatzfelderAuswahlDialog(settings);
    d.addCloseListener(new ZusatzfelderListener());

    zusatzfelderabfrage = new DialogInput("", d);
    setZusatzfelderAuswahl();
    zusatzfelderabfrage.setName(JVereinPlugin.getI18n().tr("Zusatzfelder"));
    return zusatzfelderabfrage;
  }

  public Input getAusgabe()
  {
    if (ausgabe != null)
    {
      return ausgabe;
    }
    String[] ausg = { "PDF", "CSV" };
    ausgabe = new SelectInput(ausg, "PDF");
    ausgabe.setName(JVereinPlugin.getI18n().tr("Ausgabe"));
    return ausgabe;
  }

  public Input getSortierung()
  {
    if (sortierung != null)
    {
      return sortierung;
    }
    String[] sort = { "Name, Vorname", "Eintrittsdatum", "Geburtsdatum",
        "Geburtstagsliste" };
    sortierung = new SelectInput(sort, "Name, Vorname");
    sortierung.setName(JVereinPlugin.getI18n().tr("Sortierung"));
    return sortierung;
  }

  public boolean isMitgliedStatusAktiv()
  {
    return status != null;
  }

  public IntegerInput getSuchExterneMitgliedsnummer()
  {
    if (suchexternemitgliedsnummer != null)
    {
      return suchexternemitgliedsnummer;
    }
    suchexternemitgliedsnummer = new IntegerInput(-1);
    return suchexternemitgliedsnummer;
  }

  public Input getMitgliedStatus()
  {
    if (status != null)
    {
      return status;
    }
    status = new SelectInput(new String[] { "Angemeldet", "Abgemeldet",
        "An- und Abgemeldete" }, settings.getString("status.mitglied",
        "Angemeldete"));
    status.setName(JVereinPlugin.getI18n().tr("Mitgliedschaft"));
    return status;
  }

  public CheckboxInput getOhneMail()
  {
    if (ohneMail != null)
    {
      return ohneMail;
    }
    ohneMail = new CheckboxInput(false);
    ohneMail.setName(JVereinPlugin.getI18n().tr("Ohne Mailadresse"));
    return ohneMail;
  }

  public Button getStartAuswertungButton()
  {
    Button b = new Button("starten", new Action()
    {

      public void handleAction(Object context) throws ApplicationException
      {
        try
        {
          starteAuswertung();
        }
        catch (RemoteException e)
        {
          Logger.error(e.getMessage());
          throw new ApplicationException(
              "Fehler beim Start der Mitgliederauswertung");
        }
      }
    }, null, true, "go.png"); // "true" defines this button as the default
    // button
    return b;
  }

  public Button getStartStatistikButton()
  {
    Button b = new Button("starten", new Action()
    {

      public void handleAction(Object context) throws ApplicationException
      {
        try
        {
          starteStatistik();
        }
        catch (RemoteException e)
        {
          throw new ApplicationException(e);
        }
      }
    }, null, true, "go.png"); // "true" defines this button as the default
    // button
    return b;
  }

  public Button getStartJubilaeenButton()
  {
    Button b = new Button("Start", new Action()
    {

      public void handleAction(Object context) throws ApplicationException
      {
        try
        {
          starteJubilaeenListe();
        }
        catch (RemoteException e)
        {
          Logger.error("Fehler:", e);
          throw new ApplicationException(e);
        }
      }
    }, null, true, "go.png"); // "true" defines this button as the default
    // button
    return b;
  }

  public Button getZusatzbetragNeu()
  {
    return new Button("Neu", new ZusatzbetraegeAction(getMitglied()), null,
        false, "document-new.png");
  }

  public Button getWiedervorlageNeu()
  {
    return new Button("Neu", new WiedervorlageAction(getMitglied()), null,
        false, "document-new.png");
  }

  public Button getArbeitseinsatzNeu()
  {
    return new Button("Neu", new ArbeitseinsatzAction(getMitglied()), null,
        false, "document-new.png");
  }

  public Button getLehrgangNeu()
  {
    return new Button("Neu", new LehrgangAction(getMitglied()), null, false,
        "document-new.png");
  }

  public TablePart getMitgliedTable(String anfangsbuchstabe, int atyp,
      Action detailaction) throws RemoteException
  {
    TablePart part;
    saveDefaults();
    part = new TablePart(new MitgliedQuery(this, false).get(anfangsbuchstabe,
        atyp), detailaction);
    new MitgliedSpaltenauswahl().setColumns(part, atyp);
    part.setContextMenu(new MitgliedMenu(detailaction));
    part.setMulti(true);
    part.setRememberColWidths(true);
    part.setRememberOrder(true);
    part.setRememberState(true);
    return part;
  }

  /**
   * Default-Werte f�r die MitgliederSuchView speichern.
   * 
   * @throws RemoteException
   */
  public void saveDefaults() throws RemoteException
  {
    if (status != null)
    {
      settings.setAttribute("status.mitglied", (String) getMitgliedStatus()
          .getValue());
    }

    if (geburtsdatumvon != null)
    {
      Date tmp = (Date) getGeburtsdatumvon().getValue();
      if (tmp != null)
      {
        settings.setAttribute("mitglied.geburtsdatumvon",
            new JVDateFormatTTMMJJJJ().format(tmp));
      }
      else
      {
        settings.setAttribute("mitglied.geburtsdatumvon", "");
      }
    }
    if (auswertungUeberschrift != null)
    {
      String tmp = (String) getAuswertungUeberschrift().getValue();
      if (tmp != null)
      {
        settings.setAttribute("auswertung.ueberschrift", tmp);
      }
      else
      {
        settings.setAttribute("auswertung.ueberschrift", "");
      }
    }

    if (geburtsdatumbis != null)
    {
      Date tmp = (Date) getGeburtsdatumbis().getValue();
      if (tmp != null)
      {
        settings.setAttribute("mitglied.geburtsdatumbis",
            new JVDateFormatTTMMJJJJ().format(tmp));
      }
      else
      {
        settings.setAttribute("mitglied.geburtsdatumbis", "");
      }
    }

    if (sterbedatumvon != null)
    {
      Date tmp = (Date) getSterbedatumvon().getValue();
      if (tmp != null)
      {
        settings.setAttribute("mitglied.sterbedatumvon",
            new JVDateFormatTTMMJJJJ().format(tmp));
      }
      else
      {
        settings.setAttribute("mitglied.sterbedatumvon", "");
      }
    }

    if (sterbedatumbis != null)
    {
      Date tmp = (Date) getSterbedatumbis().getValue();
      if (tmp != null)
      {
        settings.setAttribute("mitglied.sterbedatumbis",
            new JVDateFormatTTMMJJJJ().format(tmp));
      }
      else
      {
        settings.setAttribute("mitglied.sterbedatumbis", "");
      }
    }

    if (eintrittvon != null)
    {
      Date tmp = (Date) getEintrittvon().getValue();
      if (tmp != null)
      {
        settings.setAttribute("mitglied.eintrittvon",
            new JVDateFormatTTMMJJJJ().format(tmp));
      }
      else
      {
        settings.setAttribute("mitglied.eintrittvon", "");
      }
    }

    if (eintrittbis != null)
    {
      Date tmp = (Date) getEintrittbis().getValue();
      if (tmp != null)
      {
        settings.setAttribute("mitglied.eintrittbis",
            new JVDateFormatTTMMJJJJ().format(tmp));
      }
      else
      {
        settings.setAttribute("mitglied.eintrittbis", "");
      }
    }

    if (austrittvon != null)
    {
      Date tmp = (Date) getAustrittvon().getValue();
      if (tmp != null)
      {
        settings.setAttribute("mitglied.austrittvon",
            new JVDateFormatTTMMJJJJ().format(tmp));
      }
      else
      {
        settings.setAttribute("mitglied.austrittvon", "");
      }
    }

    if (austrittbis != null)
    {
      Date tmp = (Date) getAustrittbis().getValue();
      if (tmp != null)
      {
        settings.setAttribute("mitglied.austrittbis",
            new JVDateFormatTTMMJJJJ().format(tmp));
      }
      else
      {
        settings.setAttribute("mitglied.austrittbis", "");
      }
    }

    if (eigenschaftenAuswahlTree != null)
    {
      StringBuffer tmp = new StringBuffer();
      for (Object o : eigenschaftenAuswahlTree.getItems())
      {
        EigenschaftenNode node = (EigenschaftenNode) o;
        if (node.getNodeType() == EigenschaftenNode.EIGENSCHAFTEN)
        {
          if (tmp.length() > 0)
          {
            tmp.append(",");
          }
          tmp.append(node.getEigenschaft().getID());
        }
      }
      settings.setAttribute("mitglied.eigenschaften", tmp.toString());
    }

    if (beitragsgruppeausw != null)
    {
      Beitragsgruppe tmpbg = (Beitragsgruppe) getBeitragsgruppeAusw()
          .getValue();
      if (tmpbg != null)
      {
        settings.setAttribute("mitglied.beitragsgruppe", tmpbg.getID());
      }
      else
      {
        settings.setAttribute("mitglied.beitragsgruppe", "");
      }
    }
  }

  public String getEigenschaftenString()
  {
    return settings.getString("mitglied.eigenschaften", "");
  }

  public TreePart getEigenschaftenTree() throws RemoteException
  {
    if (eigenschaftenTree != null)
    {
      return eigenschaftenTree;
    }
    eigenschaftenTree = new TreePart(new EigenschaftenNode(mitglied), null);
    eigenschaftenTree.setCheckable(true);
    eigenschaftenTree.addSelectionListener(new EigenschaftListener(
        eigenschaftenTree));
    eigenschaftenTree.setFormatter(new EigenschaftTreeFormatter());
    return eigenschaftenTree;
  }

  public void handleStore()
  {
    try
    {
      Mitglied m = getMitglied();

      if (eigenschaftenTree != null)
      {
        HashMap<String, Boolean> pflichtgruppen = new HashMap<String, Boolean>();
        DBIterator it = Einstellungen.getDBService().createList(
            EigenschaftGruppe.class);
        it.addFilter("pflicht = ?", new Object[] { Boolean.TRUE });
        while (it.hasNext())
        {
          EigenschaftGruppe eg = (EigenschaftGruppe) it.next();
          pflichtgruppen.put(eg.getID(), Boolean.valueOf(false));
        }
        for (Object o1 : eigenschaftenTree.getItems())
        {
          if (o1 instanceof EigenschaftenNode)
          {
            EigenschaftenNode node = (EigenschaftenNode) o1;
            if (node.getNodeType() == EigenschaftenNode.EIGENSCHAFTEN)
            {
              Eigenschaft ei = (Eigenschaft) node.getObject();
              pflichtgruppen.put(ei.getEigenschaftGruppeId() + "",
                  Boolean.valueOf(true));
            }
          }
        }
        for (String key : pflichtgruppen.keySet())
        {
          if (!pflichtgruppen.get(key))
          {
            EigenschaftGruppe eg = (EigenschaftGruppe) Einstellungen
                .getDBService().createObject(EigenschaftGruppe.class, key);
            throw new ApplicationException("In der Eigenschaftengruppe \""
                + eg.getBezeichnung() + "\" fehlt ein Eintrag!");
          }
        }
        // Max eine Eigenschaft pro Gruppe
        HashMap<String, Boolean> max1gruppen = new HashMap<String, Boolean>();
        it = Einstellungen.getDBService().createList(EigenschaftGruppe.class);
        it.addFilter("max1 = ?", new Object[] { Boolean.TRUE });
        while (it.hasNext())
        {
          EigenschaftGruppe eg = (EigenschaftGruppe) it.next();
          max1gruppen.put(eg.getID(), Boolean.valueOf(false));
        }
        for (Object o1 : eigenschaftenTree.getItems())
        {
          if (o1 instanceof EigenschaftenNode)
          {
            EigenschaftenNode node = (EigenschaftenNode) o1;
            if (node.getNodeType() == EigenschaftenNode.EIGENSCHAFTEN)
            {
              Eigenschaft ei = (Eigenschaft) node.getObject();
              Boolean m1 = max1gruppen.get(ei.getEigenschaftGruppe().getID());
              if (m1 != null)
              {
                if (m1)
                {
                  throw new ApplicationException(
                      "In der Eigenschaftengruppe \""
                          + ei.getEigenschaftGruppe().getBezeichnung()
                          + "\" mehr als ein Eintrag markiert!");
                }
                else
                {
                  max1gruppen.put(ei.getEigenschaftGruppe().getID(),
                      Boolean.valueOf(true));
                }
              }
            }
          }
        }

      }

      if (adresstyp != null)
      {
        Adresstyp at = (Adresstyp) getAdresstyp().getValue();
        m.setAdresstyp(new Integer(at.getID()));
      }
      else
      {
        m.setAdresstyp(1);
      }
      m.setAdressierungszusatz((String) getAdressierungszusatz().getValue());
      m.setAustritt((Date) getAustritt().getValue());
      m.setAnrede((String) getAnrede().getValue());
      GenericObject o = (GenericObject) getBeitragsgruppe(true).getValue();
      if (adresstyp == null)
      {
        try
        {
          Beitragsgruppe bg = (Beitragsgruppe) o;
          m.setBeitragsgruppe(new Integer(bg.getID()));
          if (bg.getBeitragsArt() != ArtBeitragsart.FAMILIE_ANGEHOERIGER)
          {
            m.setZahlerID(null);
          }
        }
        catch (NullPointerException e)
        {
          throw new ApplicationException("Beitragsgruppe fehlt");
        }
      }
      if (Einstellungen.getEinstellung().getIndividuelleBeitraege())
      {
        m.setIndividuellerBeitrag((Double) getIndividuellerBeitrag().getValue());
      }
      Zahlungsweg zw = (Zahlungsweg) getZahlungsweg().getValue();
      m.setZahlungsweg(zw.getKey());
      Zahlungsrhytmus zr = (Zahlungsrhytmus) getZahlungsrhytmus().getValue();
      m.setZahlungsrhytmus(zr.getKey());
      m.setBlz((String) getBlz().getValue());
      m.setEintritt((Date) getEintritt().getValue());
      m.setEmail((String) getEmail().getValue());
      if (Einstellungen.getEinstellung().getExterneMitgliedsnummer())
      {
        if (externemitgliedsnummer != null)
        {
          m.setExterneMitgliedsnummer((Integer) getExterneMitgliedsnummer()
              .getValue());
        }
      }
      else
      {
        m.setExterneMitgliedsnummer(null);
      }

      if (m.getPersonenart().equals("n"))
      {
        m.setGeburtsdatum((Date) getGeburtsdatum().getValue());
        m.setGeschlecht((String) getGeschlecht().getValue());
      }
      m.setKonto((String) getKonto().getValue());
      m.setKontoinhaber((String) getKontoinhaber().getValue());
      m.setKuendigung((Date) getKuendigung().getValue());
      m.setSterbetag((Date) getSterbetag().getValue());
      m.setName(getName(false).getText());
      m.setOrt((String) getOrt().getValue());
      m.setPlz((String) getPlz().getValue());
      m.setStaat((String) getStaat().getValue());
      m.setStrasse(getStrasse().getText());
      m.setTelefondienstlich((String) getTelefondienstlich().getValue());
      m.setTelefonprivat((String) getTelefonprivat().getValue());
      m.setHandy((String) getHandy().getValue());
      m.setTitel((String) getTitel().getValue());
      m.setVermerk1((String) getVermerk1().getValue());
      m.setVermerk2((String) getVermerk2().getValue());
      m.setVorname(getVorname().getText());
      if (m.getID() == null)
      {
        m.setEingabedatum();
      }
      m.setLetzteAenderung();
      m.store();

      if (m.getAdresstyp().getJVereinid() == 1
          && Einstellungen.getEinstellung().getMitgliedfoto())
      {
        Mitgliedfoto f = null;
        DBIterator it = Einstellungen.getDBService().createList(
            Mitgliedfoto.class);
        it.addFilter("mitglied = ?", new Object[] { m.getID() });
        if (it.size() > 0)
        {
          f = (Mitgliedfoto) it.next();
          if (foto == null)
          {
            f.delete();
          }
          else
          {
            f.setFoto((byte[]) foto.getValue());
            f.store();
          }
        }
        else
        {
          f = (Mitgliedfoto) Einstellungen.getDBService().createObject(
              Mitgliedfoto.class, null);
          f.setMitglied(m);
          f.setFoto((byte[]) foto.getValue());
          f.store();
        }
      }
      if (eigenschaftenTree != null)
      {
        if (!getMitglied().isNewObject())
        {
          DBIterator it = Einstellungen.getDBService().createList(
              Eigenschaften.class);
          it.addFilter("mitglied = ?", new Object[] { getMitglied().getID() });
          while (it.hasNext())
          {
            Eigenschaften ei = (Eigenschaften) it.next();
            ei.delete();
          }
        }
        for (Object o1 : eigenschaftenTree.getItems())
        {
          if (o1 instanceof EigenschaftenNode)
          {
            EigenschaftenNode node = (EigenschaftenNode) o1;
            if (node.getNodeType() == EigenschaftenNode.EIGENSCHAFTEN)
            {
              Eigenschaften eig = (Eigenschaften) Einstellungen.getDBService()
                  .createObject(Eigenschaften.class, null);
              eig.setEigenschaft(node.getEigenschaft().getID());
              eig.setMitglied(getMitglied().getID());
              eig.store();
            }
          }
        }
      }

      if (zusatzfelder != null)
      {
        for (Input ti : zusatzfelder)
        {
          // Felddefinition ermitteln
          DBIterator it0 = Einstellungen.getDBService().createList(
              Felddefinition.class);
          it0.addFilter("label = ?", new Object[] { ti.getName() });
          Felddefinition fd = (Felddefinition) it0.next();
          // Ist bereits ein Datensatz f�r diese Definiton vorhanden ?
          DBIterator it = Einstellungen.getDBService().createList(
              Zusatzfelder.class);
          it.addFilter("mitglied =?", new Object[] { m.getID() });
          it.addFilter("felddefinition=?", new Object[] { fd.getID() });
          Zusatzfelder zf = null;
          if (it.size() > 0)
          {
            zf = (Zusatzfelder) it.next();
          }
          else
          {
            zf = (Zusatzfelder) Einstellungen.getDBService().createObject(
                Zusatzfelder.class, null);
          }
          zf.setMitglied(new Integer(m.getID()));
          zf.setFelddefinition(new Integer(fd.getID()));
          switch (fd.getDatentyp())
          {
            case Datentyp.ZEICHENFOLGE:
              zf.setFeld((String) ti.getValue());
              break;
            case Datentyp.DATUM:
              zf.setFeldDatum((Date) ti.getValue());
              break;
            case Datentyp.GANZZAHL:
              zf.setFeldGanzzahl((Integer) ti.getValue());
              break;
            case Datentyp.WAEHRUNG:
              if (ti.getValue() != null)
              {
                zf.setFeldWaehrung(new BigDecimal((Double) ti.getValue()));
              }
              else
              {
                zf.setFeldWaehrung(null);
              }
              break;
            case Datentyp.JANEIN:
              zf.setFeldJaNein((Boolean) ti.getValue());
              break;
            default:
              zf.setFeld((String) ti.getValue());
              break;
          }
          zf.store();
        }
      }
      String successtext = "";
      if (m.getAdresstyp().getJVereinid() == 1)
      {
        successtext = "Mitglied gespeichert";
      }
      else
      {
        successtext = "Adresse gespeichert";
      }
      GUI.getStatusBar().setSuccessText(successtext);
    }
    catch (ApplicationException e)
    {
      GUI.getStatusBar().setErrorText(e.getMessage());
    }
    catch (RemoteException e)
    {
      String fehler = "Fehler bei Speichern des Mitgliedes";
      Logger.error(fehler, e);
      GUI.getStatusBar().setErrorText(fehler);
    }
  }

  public TreePart getEigenschaftenAuswahlTree(String vorbelegung,
      boolean ohnePflicht) throws RemoteException
  {
    eigenschaftenAuswahlTree = new TreePart(new EigenschaftenNode(vorbelegung,
        ohnePflicht), null);
    eigenschaftenAuswahlTree.setCheckable(true);
    eigenschaftenAuswahlTree.addSelectionListener(new EigenschaftListener(
        eigenschaftenAuswahlTree));
    eigenschaftenAuswahlTree.setFormatter(new EigenschaftTreeFormatter());
    return eigenschaftenAuswahlTree;
  }

  public TreePart getFamilienbeitraegeTree() throws RemoteException
  {
    familienbeitragtree = new TreePart(new FamilienbeitragNode(),
        new MitgliedDetailAction());
    familienbeitragtree.addColumn("Name", "name");
    familienbeitragtree.setContextMenu(new FamilienbeitragMenu());
    familienbeitragtree.setRememberColWidths(true);
    familienbeitragtree.setRememberOrder(true);
    this.fbc = new FamilienbeitragMessageConsumer();
    Application.getMessagingFactory().registerMessageConsumer(this.fbc);
    return familienbeitragtree;
  }

  @SuppressWarnings("unchecked")
  private void starteAuswertung() throws RemoteException
  {
    saveDefaults();
    ArrayList list = null;
    list = new MitgliedQuery(this, true).get(1);
    try
    {
      String subtitle = "";
      if (getMitgliedStatus().getValue().equals(
          JVereinPlugin.getI18n().tr("Abgemeldet")))
      {
        subtitle += "Abgemeldet ";
      }
      if (geburtsdatumvon.getValue() != null)
      {
        Date d = (Date) geburtsdatumvon.getValue();
        subtitle += "Geburtsdatum von " + new JVDateFormatTTMMJJJJ().format(d)
            + "  ";
      }
      if (geburtsdatumbis.getValue() != null)
      {
        Date d = (Date) geburtsdatumbis.getValue();
        subtitle += "Geburtsdatum bis " + new JVDateFormatTTMMJJJJ().format(d)
            + "  ";
      }
      if (eintrittvon.getValue() != null)
      {
        Date d = (Date) eintrittvon.getValue();
        subtitle += "Eintritt von " + new JVDateFormatTTMMJJJJ().format(d)
            + "  ";
      }
      if (eintrittbis.getValue() != null)
      {
        Date d = (Date) eintrittbis.getValue();
        subtitle += "Eintritt bis " + new JVDateFormatTTMMJJJJ().format(d)
            + "  ";
      }
      if (austrittvon.getValue() != null)
      {
        Date d = (Date) austrittvon.getValue();
        subtitle += "Austritt von " + new JVDateFormatTTMMJJJJ().format(d)
            + "  ";
      }
      if (austrittbis.getValue() != null)
      {
        Date d = (Date) austrittbis.getValue();
        subtitle += "Austritt bis " + new JVDateFormatTTMMJJJJ().format(d)
            + "  ";
      }
      if (sterbedatumvon.getValue() != null)
      {
        Date d = (Date) sterbedatumvon.getValue();
        subtitle += "Sterbetag von " + new JVDateFormatTTMMJJJJ().format(d)
            + "  ";
      }
      if (sterbedatumbis.getValue() != null)
      {
        Date d = (Date) sterbedatumbis.getValue();
        subtitle += "Sterbedatum bis " + new JVDateFormatTTMMJJJJ().format(d)
            + "  ";
      }
      if (getMitgliedStatus().getValue().equals(
          JVereinPlugin.getI18n().tr("Angemeldet"))
          && austrittvon.getValue() == null
          && austrittbis.getValue() == null
          && sterbedatumvon.getValue() == null
          && sterbedatumbis.getValue() == null)
      {
        subtitle += "nur Angemeldete, keine Ausgetretenen (nur lfd. Jahr)  ";
      }
      if (beitragsgruppeausw.getValue() != null)
      {
        Beitragsgruppe bg = (Beitragsgruppe) beitragsgruppeausw.getValue();
        subtitle += "nur Beitragsgruppe " + bg.getBezeichnung();
      }

      String sort = (String) sortierung.getValue();
      String dateinamensort = "";
      if (sort.equals("Name, Vorname"))
      {
        dateinamensort = "name";
      }
      else if (sort.equals("Eintrittsdatum"))
      {
        dateinamensort = "eintrittsdatum";
      }
      else if (sort.equals("Geburtsdatum"))
      {
        dateinamensort = "geburtsdatum";
      }
      else if (sort.equals("Geburtstagsliste"))
      {
        dateinamensort = "geburtstagsliste";
      }

      FileDialog fd = new FileDialog(GUI.getShell(), SWT.SAVE);
      fd.setText("Ausgabedatei w�hlen.");

      String path = settings.getString("lastdir",
          System.getProperty("user.home"));
      if (path != null && path.length() > 0)
      {
        fd.setFilterPath(path);
      }
      String ausgformat = (String) ausgabe.getValue();
      fd.setFileName(new Dateiname("auswertung", dateinamensort, Einstellungen
          .getEinstellung().getDateinamenmuster(), ausgformat).get());
      fd.setFilterExtensions(new String[] { "*." + ausgformat });

      String s = fd.open();
      if (s == null || s.length() == 0)
      {
        return;
      }
      if (!s.endsWith(ausgformat))
      {
        s = s + "." + ausgformat;
      }
      final File file = new File(s);
      if (ausgformat.equals("PDF"))
      {
        String ueberschrift = (String) auswertungUeberschrift.getValue();
        if (ueberschrift.length() > 0)
        {
          subtitle = ueberschrift;
        }
        auswertungMitgliedPDF(list, file, subtitle);
      }
      if (ausgformat.equals("CSV"))
      {
        auswertungMitgliedCSV(list, file);
      }
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  public Settings getSettings()
  {
    return settings;
  }

  private void starteStatistik() throws RemoteException
  {
    FileDialog fd = new FileDialog(GUI.getShell(), SWT.SAVE);
    fd.setText("Ausgabedatei w�hlen.");
    fd.setFilterExtensions(new String[] { "*.PDF" });
    Settings settings = new Settings(this.getClass());

    String path = settings
        .getString("lastdir", System.getProperty("user.home"));
    if (path != null && path.length() > 0)
    {
      fd.setFilterPath(path);
    }
    fd.setFileName(new Dateiname("statistik", Einstellungen.getEinstellung()
        .getDateinamenmuster(), "PDF").get());

    String s = fd.open();

    if (s == null || s.length() == 0)
    {
      return;
    }
    if (!s.toUpperCase().endsWith("PDF"))
    {
      s = s + ".PDF";
    }

    final File file = new File(s);
    final Date sticht = (Date) stichtag.getValue();

    BackgroundTask t = new BackgroundTask()
    {

      public void run(ProgressMonitor monitor) throws ApplicationException
      {
        new MitgliederStatistik(file, monitor, sticht);
      }

      public void interrupt()
      {
        //
      }

      public boolean isInterrupted()
      {
        return false;
      }
    };
    Application.getController().start(t);

  }

  private void starteJubilaeenListe() throws RemoteException
  {
    FileDialog fd = new FileDialog(GUI.getShell(), SWT.SAVE);
    fd.setText("Ausgabedatei w�hlen.");
    fd.setFilterExtensions(new String[] { "*.PDF" });
    Settings settings = new Settings(this.getClass());
    String path = settings
        .getString("lastdir", System.getProperty("user.home"));
    if (path != null && path.length() > 0)
    {
      fd.setFilterPath(path);
    }
    fd.setFileName(new Dateiname((String) getJubelArt().getValue(),
        Einstellungen.getEinstellung().getDateinamenmuster(), "PDF").get());
    String s = fd.open();

    if (s == null || s.length() == 0)
    {
      return;
    }
    if (!s.toUpperCase().endsWith("PDF"))
    {
      s = s + ".PDF";
    }

    final File file = new File(s);
    settings.setAttribute("lastdir", file.getParent());
    final Integer jahr = (Integer) jubeljahr.getValue();
    final String art = (String) jubelart.getValue();

    BackgroundTask t = new BackgroundTask()
    {

      public void run(ProgressMonitor monitor) throws ApplicationException
      {
        new Jubilaeenliste(file, monitor, jahr, art);
      }

      public void interrupt()
      {
        //
      }

      public boolean isInterrupted()
      {
        return false;
      }
    };
    Application.getController().start(t);

  }

  private void auswertungMitgliedPDF(final ArrayList<Mitglied> list,
      final File file, final String subtitle)
  {
    BackgroundTask t = new BackgroundTask()
    {

      public void run(ProgressMonitor monitor) throws ApplicationException
      {
        try
        {
          new MitgliedAuswertungPDF(list, file, monitor, subtitle);
          monitor.setPercentComplete(100);
          monitor.setStatus(ProgressMonitor.STATUS_DONE);
          GUI.getStatusBar().setSuccessText("Auswertung gestartet");
          GUI.getCurrentView().reload();
        }
        catch (ApplicationException ae)
        {
          Logger.error("", ae);
          monitor.setStatusText(ae.getMessage());
          monitor.setStatus(ProgressMonitor.STATUS_ERROR);
          GUI.getStatusBar().setErrorText(ae.getMessage());
          throw ae;
        }
        catch (Exception re)
        {
          Logger.error("", re);
          monitor.setStatusText(re.getMessage());
          monitor.setStatus(ProgressMonitor.STATUS_ERROR);
          GUI.getStatusBar().setErrorText(re.getMessage());
          throw new ApplicationException(re);
        }
      }

      public void interrupt()
      {
        //
      }

      public boolean isInterrupted()
      {
        return false;
      }
    };
    Application.getController().start(t);
  }

  private void auswertungMitgliedCSV(final ArrayList<Mitglied> list,
      final File file)
  {
    BackgroundTask t = new BackgroundTask()
    {

      public void run(ProgressMonitor monitor) throws ApplicationException
      {
        try
        {
          if (settings.getBoolean("auswertung.csv.kompatibilitaet", false))
          {
            new MitgliedAuswertungCSValt(list, file, monitor);
          }
          else
          {
            new MitgliedAuswertungCSV(list, file, monitor);
          }
          monitor.setPercentComplete(100);
          monitor.setStatus(ProgressMonitor.STATUS_DONE);
          GUI.getStatusBar().setSuccessText("Auswertung gestartet");
          GUI.getCurrentView().reload();
        }
        catch (ApplicationException ae)
        {
          monitor.setStatusText(ae.getMessage());
          monitor.setStatus(ProgressMonitor.STATUS_ERROR);
          GUI.getStatusBar().setErrorText(ae.getMessage());
          throw ae;
        }
      }

      public void interrupt()
      {
        //
      }

      public boolean isInterrupted()
      {
        return false;
      }
    };
    Application.getController().start(t);
  }

  public void setZusatzfelderAuswahl()
  {
    int selected = settings.getInt("zusatzfelder.selected", 0);
    if (selected == 0)
    {
      zusatzfelderabfrage.setText("kein Feld ausgew�hlt");
    }
    else if (selected == 1)
    {
      zusatzfelderabfrage.setText("1 Feld ausgew�hlt");
    }
    else
    {
      zusatzfelderabfrage.setText(selected + " Felder ausgew�hlt");
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

  /**
   * Listener, der die Auswahl der Eigenschaften ueberwacht.
   */
  private class EigenschaftenListener implements Listener
  {

    @SuppressWarnings("unchecked")
    public void handleEvent(Event event)
    {
      if (event == null || event.data == null)
      {
        return;
      }
      ArrayList<Object> sel = (ArrayList<Object>) event.data;
      StringBuilder id = new StringBuilder();
      StringBuilder text = new StringBuilder();
      for (Object o : sel)
      {
        if (text.length() > 0)
        {
          id.append(",");
          text.append(", ");
        }
        EigenschaftenNode node = (EigenschaftenNode) o;
        try
        {
          id.append(node.getEigenschaft().getID());
          text.append(node.getEigenschaft().getBezeichnung());
        }
        catch (RemoteException e)
        {
          Logger.error("Fehler", e);
        }
      }
      eigenschaftenabfrage.setText(text.toString());
      settings.setAttribute("mitglied.eigenschaften", id.toString());
    }
  }

  /**
   * Listener, der die Auswahl der Zusatzfelder ueberwacht.
   */
  private class ZusatzfelderListener implements Listener
  {
    public void handleEvent(Event event)
    {
      if (event == null || event.data == null)
      {
        return;
      }
      int selected = settings.getInt("zusatzfelder.selected", 0);
      zusatzfelderabfrage.setText(selected > 0 ? selected
          + " Felder ausgew�hlt" : "kein Feld ausgew�hlt");
    }
  }

  public static class EigenschaftTreeFormatter implements TreeFormatter
  {

    public void format(TreeItem item)
    {
      EigenschaftenNode eigenschaftitem = (EigenschaftenNode) item.getData();
      if (eigenschaftitem.getNodeType() == EigenschaftenNode.ROOT
          || eigenschaftitem.getNodeType() == EigenschaftenNode.EIGENSCHAFTGRUPPE)
      {
        //
      }
      else
      {
        if (eigenschaftitem.getEigenschaften() != null
            || eigenschaftitem.isPreset())
        {
          item.setChecked(true);
        }
        else
        {
          item.setChecked(false);
        }
      }
    }
  }

  static class EigenschaftListener implements Listener
  {

    private TreePart tree;

    public EigenschaftListener(TreePart tree)
    {
      this.tree = tree;
    }

    public void handleEvent(Event event)
    {
      // "o" ist das Objekt, welches gerade markiert
      // wurde oder die Checkbox geaendert wurde.
      GenericObjectNode o = (GenericObjectNode) event.data;

      // Da der Listener sowohl dann aufgerufen wird,j
      // nur nur eine Zeile selektiert wurde als auch,
      // wenn die Checkbox geaendert wurde, musst du jetzt
      // noch ersteres ausfiltern - die Checkboxen sollen
      // ja nicht geaendert werden, wenn nur eine Zeile
      // selektiert aber die Checkbox nicht geaendert wurde.
      // Hierzu schreibe ich in event.detail einen Int-Wert.
      // event.detail = -1 // Nur selektiert
      // event.detail = 1 // Checkbox aktiviert
      // event.detail = 0 // Checkbox deaktiviert

      // Folgende Abfrage deaktiviert wegen Problemen mit Windows
      // if (event.detail == -1)
      // {
      // return;
      // }
      try
      {
        if (o.getChildren() == null)
        {
          return;
        }
        List children = PseudoIterator.asList(o.getChildren());
        boolean b = event.detail > 0;
        tree.setChecked(children.toArray(new Object[children.size()]), b);
      }
      catch (RemoteException e)
      {
        e.printStackTrace();
      }
    }
  }

  /**
   * Wird benachrichtigt um die Anzeige zu aktualisieren.
   */
  private class FamilienbeitragMessageConsumer implements MessageConsumer
  {

    /**
     * @see de.willuhn.jameica.messaging.MessageConsumer#autoRegister()
     */
    public boolean autoRegister()
    {
      return false;
    }

    /**
     * @see de.willuhn.jameica.messaging.MessageConsumer#getExpectedMessageTypes()
     */
    public Class<?>[] getExpectedMessageTypes()
    {
      return new Class[] { FamilienbeitragMessage.class };
    }

    /**
     * @see de.willuhn.jameica.messaging.MessageConsumer#handleMessage(de.willuhn.jameica.messaging.Message)
     */
    public void handleMessage(final Message message) throws Exception
    {
      GUI.getDisplay().syncExec(new Runnable()
      {

        public void run()
        {
          try
          {
            if (familienbeitragtree == null)
            {
              // Eingabe-Feld existiert nicht. Also abmelden
              Application.getMessagingFactory().unRegisterMessageConsumer(
                  FamilienbeitragMessageConsumer.this);
              return;
            }
            familienbeitragtree.setRootObject(new FamilienbeitragNode());
          }
          catch (Exception e)
          {
            // Wenn hier ein Fehler auftrat, deregistrieren wir uns wieder
            Logger.error("unable to refresh saldo", e);
            Application.getMessagingFactory().unRegisterMessageConsumer(
                FamilienbeitragMessageConsumer.this);
          }
        }

      });
    }
  }

}
