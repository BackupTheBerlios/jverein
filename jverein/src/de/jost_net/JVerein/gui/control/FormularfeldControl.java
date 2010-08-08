/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/control/FormularfeldControl.java,v $
 * $Revision: 1.7 $
 * $Date: 2010/08/08 19:32:26 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: FormularfeldControl.java,v $
 * Revision 1.7  2010/08/08 19:32:26  jost
 * neues Feld Summe
 *
 * Revision 1.6  2009/04/10 17:45:50  jost
 * Zus�tzliche Datenfelder f�r die Rechnungserstellung
 *
 * Revision 1.5  2009/01/26 18:47:34  jost
 * Neu: Ersatz Aufwendungen
 *
 * Revision 1.4  2008/11/29 13:07:42  jost
 * Refactoring: Code-Optimierung
 *
 * Revision 1.3  2008/10/01 14:17:12  jost
 * Warnungen entfernt
 *
 * Revision 1.2  2008/09/16 18:27:24  jost
 * Refactoring Formularaufbereitung
 *
 * Revision 1.1  2008/07/18 20:09:31  jost
 * Neu: Formulare
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.control;

import java.rmi.RemoteException;
import java.util.ArrayList;

import com.lowagie.text.pdf.BaseFont;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.gui.action.FormularfeldAction;
import de.jost_net.JVerein.gui.menu.FormularfeldMenu;
import de.jost_net.JVerein.keys.Formularart;
import de.jost_net.JVerein.rmi.Formular;
import de.jost_net.JVerein.rmi.Formularfeld;
import de.willuhn.datasource.rmi.DBIterator;
import de.willuhn.datasource.rmi.DBService;
import de.willuhn.jameica.gui.AbstractControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.Part;
import de.willuhn.jameica.gui.input.DecimalInput;
import de.willuhn.jameica.gui.input.IntegerInput;
import de.willuhn.jameica.gui.input.SelectInput;
import de.willuhn.jameica.gui.parts.TablePart;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

public class FormularfeldControl extends AbstractControl
{
  private de.willuhn.jameica.system.Settings settings;

  private TablePart formularfelderList;

  private SelectInput name;

  private DecimalInput x;

  private DecimalInput y;

  private SelectInput font;

  private IntegerInput fontsize;

  private Formular formular;

  private Formularfeld formularfeld;

  public static final String EMPFAENGER = "Empf�nger";

  public static final String TAGESDATUM = "Tagesdatum";

  public static final String ZAHLUNGSGRUND1 = "Zahlungsgrund 1";

  public static final String ZAHLUNGSGRUND2 = "Zahlungsgrund 2";

  public static final String BUCHUNGSDATUM = "Buchungsdatum";

  public static final String BETRAG = "Betrag";
  
  public static final String SUMME = "Summe";

  public static final String ZAHLUNGSWEG = "Zahlungsweg";

  public static final String ID = "ID";

  public static final String EXTERNEMITGLIEDSNUMMER = "externe Mitgliedsnummer";

  public static final String ANREDE = "Anrede";

  public static final String TITEL = "Titel";

  public static final String NAME = "Name";

  public static final String VORNAME = "Vorname";

  public static final String ADRESSIERUNGSZUSATZ = "Adressierungszusatz";

  public static final String STRASSE = "Strasse";

  public static final String PLZ = "PLZ";

  public static final String ORT = "Ort";

  public static final String ZAHLUNGSRHYTMUS = "Zahlungsrhytmus";

  public static final String BLZ = "Bankleitzahl";

  public static final String KONTO = "Konto";

  public static final String KONTOINHABER = "Kontoinhaber";

  public static final String GEBURTSDATUM = "Geburtsdatum";

  public static final String GESCHLECHT = "Geschlecht";

  public static final String TELEFONPRIVAT = "Telefon privat";

  public static final String TELEFONDIENSTLICH = "Telefon dienstlich";

  public static final String HANDY = "Handy";

  public static final String EMAIL = "Email";

  public static final String EINTRITT = "Eintritt";

  public static final String BEITRAGSGRUPPE = "Beitragsgruppe";

  public static final String AUSTRITT = "Austritt";

  public static final String KUENDIGUNG = "K�ndigung";

  public FormularfeldControl(AbstractView view, Formular formular)
  {
    super(view);
    settings = new de.willuhn.jameica.system.Settings(this.getClass());
    settings.setStoreWhenRead(true);
    this.formular = formular;
  }

  public Formularfeld getFormularfeld()
  {
    if (formularfeld != null)
    {
      return formularfeld;
    }
    formularfeld = (Formularfeld) getCurrentObject();
    return formularfeld;
  }

  public Formular getFormular()
  {
    return formular;
  }

  public SelectInput getName() throws RemoteException
  {
    if (name != null)
    {
      return name;
    }
    ArrayList<String> namen = new ArrayList<String>();
    namen.add(TAGESDATUM);
    namen.add(EMPFAENGER);
    if (formular.getArt() == Formularart.SPENDENBESCHEINIGUNG)
    {
      namen.add("Bescheinigungsdatum");
      namen.add("Betrag");
      namen.add("Betrag in Worten");
      namen.add("Spendedatum");
      namen.add("ErsatzAufwendungen");
    }
    if (formular.getArt() == Formularart.RECHNUNG)
    {
      namen.add(ZAHLUNGSGRUND1);
      namen.add(ZAHLUNGSGRUND2);
      namen.add(BUCHUNGSDATUM);
      namen.add(BETRAG);
      namen.add(SUMME);
      namen.add(ZAHLUNGSWEG);
      namen.add(ID);
      namen.add(EXTERNEMITGLIEDSNUMMER);
      namen.add(ANREDE);
      namen.add(TITEL);
      namen.add(NAME);
      namen.add(VORNAME);
      namen.add(ADRESSIERUNGSZUSATZ);
      namen.add(STRASSE);
      namen.add(PLZ);
      namen.add(ORT);
      namen.add(ZAHLUNGSRHYTMUS);
      namen.add(BLZ);
      namen.add(KONTO);
      namen.add(KONTOINHABER);
      namen.add(GEBURTSDATUM);
      namen.add(GESCHLECHT);
      namen.add(TELEFONPRIVAT);
      namen.add(TELEFONDIENSTLICH);
      namen.add(HANDY);
      namen.add(EMAIL);
      namen.add(EINTRITT);
      namen.add(BEITRAGSGRUPPE);
      namen.add(AUSTRITT);
      namen.add(KUENDIGUNG);
    }
    name = new SelectInput(namen, (String) getFormularfeld().getName());
    return name;
  }

  public DecimalInput getX() throws RemoteException
  {
    if (x != null)
    {
      return x;
    }
    x = new DecimalInput((Double) getFormularfeld().getX(),
        Einstellungen.DECIMALFORMAT);
    x.setComment("Millimeter");
    return x;
  }

  public DecimalInput getY() throws RemoteException
  {
    if (y != null)
    {
      return y;
    }
    y = new DecimalInput((Double) getFormularfeld().getY(),
        Einstellungen.DECIMALFORMAT);
    y.setComment("Millimeter");
    return y;
  }

  public SelectInput getFont() throws RemoteException
  {
    if (font != null)
    {
      return font;
    }
    ArrayList<String> fonts = new ArrayList<String>();
    fonts.add(BaseFont.HELVETICA);
    fonts.add(BaseFont.HELVETICA_BOLD);
    fonts.add(BaseFont.HELVETICA_BOLDOBLIQUE);
    fonts.add(BaseFont.HELVETICA_OBLIQUE);
    fonts.add(BaseFont.TIMES_ROMAN);
    fonts.add(BaseFont.TIMES_BOLD);
    fonts.add(BaseFont.TIMES_ITALIC);
    fonts.add(BaseFont.TIMES_BOLDITALIC);
    fonts.add(BaseFont.COURIER);
    fonts.add(BaseFont.COURIER_BOLD);
    fonts.add(BaseFont.COURIER_OBLIQUE);
    fonts.add(BaseFont.COURIER_BOLDOBLIQUE);
    font = new SelectInput(fonts, getFormularfeld().getFont());
    return font;
  }

  public IntegerInput getFontsize() throws RemoteException
  {
    if (fontsize != null)
    {
      return fontsize;
    }
    fontsize = new IntegerInput(getFormularfeld().getFontsize());
    return fontsize;
  }

  /**
   * This method stores the project using the current values.
   */
  public void handleStore()
  {
    try
    {
      Formularfeld f = getFormularfeld();
      f.setFormular(getFormular());
      f.setName((String) getName().getValue());
      f.setX((Double) getX().getValue());
      f.setY((Double) getY().getValue());
      f.setFont((String) getFont().getValue());
      f.setFontsize((Integer) getFontsize().getValue());
      f.store();

      GUI.getStatusBar().setSuccessText("Formularfeld gespeichert");
    }
    catch (RemoteException e)
    {
      String fehler = "Fehler beim Speichern des Formularfeldes";
      Logger.error(fehler, e);
      GUI.getStatusBar().setErrorText(fehler);
    }
    catch (ApplicationException e)
    {
      GUI.getStatusBar().setErrorText(e.getMessage());
    }
  }

  public Part getFormularfeldList() throws RemoteException
  {
    DBService service = Einstellungen.getDBService();
    DBIterator formularfelder = service.createList(Formularfeld.class);
    formularfelder.addFilter("formular = ?", new Object[] { formular.getID() });
    formularfelder.setOrder("ORDER BY x, y");

    formularfelderList = new TablePart(formularfelder, new FormularfeldAction());
    formularfelderList.addColumn("Name", "name");
    formularfelderList.addColumn("von links", "x");
    formularfelderList.addColumn("von unten", "y");
    formularfelderList.addColumn("Font", "font");
    formularfelderList.addColumn("Fonth�he", "fontsize");

    formularfelderList.setRememberColWidths(true);
    formularfelderList.setContextMenu(new FormularfeldMenu());
    formularfelderList.setRememberOrder(true);
    formularfelderList.setSummary(false);
    return formularfelderList;
  }

  public void refreshTable() throws RemoteException
  {
    formularfelderList.removeAll();
    DBIterator formularfelder = Einstellungen.getDBService().createList(
        Formularfeld.class);
    formularfelder.addFilter("formular = ?", new Object[] { formular.getID() });
    formularfelder.setOrder("ORDER BY x, y");
    while (formularfelder.hasNext())
    {
      formularfelderList.addItem((Formular) formularfelder.next());
    }
  }

}
