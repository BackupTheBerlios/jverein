/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/control/SpendenbescheinigungControl.java,v $
 * $Revision: 1.17 $
 * $Date: 2011/03/17 19:46:01 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: SpendenbescheinigungControl.java,v $
 * Revision 1.17  2011/03/17 19:46:01  jost
 * �berfl�ssigen import entfernt.
 *
 * Revision 1.16  2011-03-13 13:44:46  jost
 * Neu: Spendenbescheinigungen als Standardformular
 * Neu: Spendenbescheinigung f�r Sachspenden.
 *
 * Revision 1.15  2011-02-12 09:32:49  jost
 * Statische Codeanalyse mit Findbugs
 *
 * Revision 1.14  2011-02-05 17:39:06  jost
 * Bugfix: Korrekte Positionierung des Betragsfeldes.
 *
 * Revision 1.13  2010-10-15 09:58:26  jost
 * Code aufger�umt
 *
 * Revision 1.12  2010-01-03 08:58:22  jost
 * Logo f�r PDF-Ausgabe
 *
 * Revision 1.11  2009/07/24 20:19:31  jost
 * Focus auf erstes Feld setzen.
 *
 * Revision 1.10  2009/06/22 18:14:21  jost
 * Einheitliche Ausgabe von Fehlermeldungen in der Statusbar
 *
 * Revision 1.9  2009/01/26 19:27:30  jost
 * Bugfix Pfad
 *
 * Revision 1.8  2009/01/26 18:47:54  jost
 * Neu: Ersatz Aufwendungen
 *
 * Revision 1.7  2008/12/13 16:22:41  jost
 * Bugfix Tagesdatum
 *
 * Revision 1.6  2008/11/29 13:08:17  jost
 * Refactoring: Code-Optimierung
 *
 * Revision 1.5  2008/11/16 16:57:20  jost
 * Speicherung der Einstellung von Property-Datei in die Datenbank verschoben.
 *
 * Revision 1.4  2008/09/16 18:50:54  jost
 * Refactoring Formularaufbereitung
 *
 * Revision 1.3  2008/07/23 19:40:30  jost
 * Bugfix ..PDF
 *
 * Revision 1.2  2008/07/19 19:24:44  jost
 * Korrektes Kontextmenü
 *
 * Revision 1.1  2008/07/18 20:09:46  jost
 * Neu: Spendenbescheinigung
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.control;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashMap;

import jonelo.NumericalChameleon.SpokenNumbers.GermanNumber;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.gui.action.SpendenbescheinigungAction;
import de.jost_net.JVerein.gui.input.FormularInput;
import de.jost_net.JVerein.gui.menu.SpendenbescheinigungMenu;
import de.jost_net.JVerein.io.FormularAufbereitung;
import de.jost_net.JVerein.io.Reporter;
import de.jost_net.JVerein.keys.Formularart;
import de.jost_net.JVerein.keys.HerkunftSpende;
import de.jost_net.JVerein.keys.Spendenart;
import de.jost_net.JVerein.rmi.Formular;
import de.jost_net.JVerein.rmi.Spendenbescheinigung;
import de.jost_net.JVerein.util.Dateiname;
import de.jost_net.JVerein.util.JVDateFormatTTMMJJJJ;
import de.willuhn.datasource.rmi.DBIterator;
import de.willuhn.datasource.rmi.DBService;
import de.willuhn.jameica.gui.AbstractControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.Part;
import de.willuhn.jameica.gui.formatter.CurrencyFormatter;
import de.willuhn.jameica.gui.formatter.DateFormatter;
import de.willuhn.jameica.gui.input.CheckboxInput;
import de.willuhn.jameica.gui.input.DateInput;
import de.willuhn.jameica.gui.input.DecimalInput;
import de.willuhn.jameica.gui.input.SelectInput;
import de.willuhn.jameica.gui.input.TextInput;
import de.willuhn.jameica.gui.parts.Button;
import de.willuhn.jameica.gui.parts.TablePart;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

public class SpendenbescheinigungControl extends AbstractControl
{

  private de.willuhn.jameica.system.Settings settings;

  private TablePart spbList;

  private SelectInput spendenart;

  private TextInput zeile1;

  private TextInput zeile2;

  private TextInput zeile3;

  private TextInput zeile4;

  private TextInput zeile5;

  private TextInput zeile6;

  private TextInput zeile7;

  private DateInput spendedatum;

  private DateInput bescheinigungsdatum;

  private DecimalInput betrag;

  private FormularInput formular;

  private CheckboxInput ersatzaufwendungen;

  private TextInput bezeichnungsachzuwendung;

  private SelectInput herkunftspende;

  private CheckboxInput unterlagenwertermittlung;

  private Spendenbescheinigung spendenbescheinigung;

  public SpendenbescheinigungControl(AbstractView view)
  {
    super(view);
    settings = new de.willuhn.jameica.system.Settings(this.getClass());
    settings.setStoreWhenRead(true);
  }

  public Spendenbescheinigung getSpendenbescheinigung()
  {
    if (spendenbescheinigung != null)
    {
      return spendenbescheinigung;
    }
    spendenbescheinigung = (Spendenbescheinigung) getCurrentObject();
    return spendenbescheinigung;
  }

  public SelectInput getSpendenart() throws RemoteException
  {
    if (spendenart != null)
    {
      return spendenart;
    }
    spendenart = new SelectInput(Spendenart.getArray(), new Spendenart(
        getSpendenbescheinigung().getSpendenart()));
    spendenart.addListener(new Listener()
    {

      public void handleEvent(Event event)
      {
        enableSachspende();
      }

    });

    return spendenart;
  }

  private void enableSachspende()
  {
    try
    {
      Spendenart spa = (Spendenart) getSpendenart().getValue();
      getBezeichnungSachzuwendung().setEnabled(
          spa.getKey() == Spendenart.SACHSPENDE);
      getHerkunftSpende().setEnabled(spa.getKey() == Spendenart.SACHSPENDE);
      getUnterlagenWertermittlung().setEnabled(
          spa.getKey() == Spendenart.SACHSPENDE);
    }
    catch (RemoteException e)
    {
      Logger.error("Fehler", e);
    }
  }

  public TextInput getZeile1(boolean withFocus) throws RemoteException
  {
    if (zeile1 != null)
    {
      return zeile1;
    }
    zeile1 = new TextInput(getSpendenbescheinigung().getZeile1(), 40);
    if (withFocus)
    {
      zeile1.focus();
    }
    return zeile1;
  }

  public TextInput getZeile2() throws RemoteException
  {
    if (zeile2 != null)
    {
      return zeile2;
    }
    zeile2 = new TextInput(getSpendenbescheinigung().getZeile2(), 40);
    return zeile2;
  }

  public TextInput getZeile3() throws RemoteException
  {
    if (zeile3 != null)
    {
      return zeile3;
    }
    zeile3 = new TextInput(getSpendenbescheinigung().getZeile3(), 40);
    return zeile3;
  }

  public TextInput getZeile4() throws RemoteException
  {
    if (zeile4 != null)
    {
      return zeile4;
    }
    zeile4 = new TextInput(getSpendenbescheinigung().getZeile4(), 40);
    return zeile4;
  }

  public TextInput getZeile5() throws RemoteException
  {
    if (zeile5 != null)
    {
      return zeile5;
    }
    zeile5 = new TextInput(getSpendenbescheinigung().getZeile5(), 40);
    return zeile5;
  }

  public TextInput getZeile6() throws RemoteException
  {
    if (zeile6 != null)
    {
      return zeile6;
    }
    zeile6 = new TextInput(getSpendenbescheinigung().getZeile6(), 40);
    return zeile6;
  }

  public TextInput getZeile7() throws RemoteException
  {
    if (zeile7 != null)
    {
      return zeile7;
    }
    zeile7 = new TextInput(getSpendenbescheinigung().getZeile7(), 40);
    return zeile7;
  }

  public DateInput getSpendedatum() throws RemoteException
  {
    if (spendedatum != null)
    {
      return spendedatum;
    }
    spendedatum = new DateInput(getSpendenbescheinigung().getSpendedatum());
    return spendedatum;
  }

  public DateInput getBescheinigungsdatum() throws RemoteException
  {
    if (bescheinigungsdatum != null)
    {
      return bescheinigungsdatum;
    }
    bescheinigungsdatum = new DateInput(getSpendenbescheinigung()
        .getBescheinigungsdatum());
    return bescheinigungsdatum;
  }

  public DecimalInput getBetrag() throws RemoteException
  {
    if (betrag != null)
    {
      return betrag;
    }
    betrag = new DecimalInput(getSpendenbescheinigung().getBetrag(),
        Einstellungen.DECIMALFORMAT);
    return betrag;
  }

  public SelectInput getFormular() throws RemoteException
  {
    if (formular != null)
    {
      return formular;
    }
    String def = null;
    if (getSpendenbescheinigung().getFormular() != null)
    {
      def = getSpendenbescheinigung().getFormular().getID();
    }
    formular = new FormularInput(Formularart.SPENDENBESCHEINIGUNG, def);
    return formular;
  }

  public CheckboxInput getErsatzAufwendungen() throws RemoteException
  {
    if (ersatzaufwendungen != null)
    {
      return ersatzaufwendungen;
    }
    ersatzaufwendungen = new CheckboxInput(getSpendenbescheinigung()
        .getErsatzAufwendungen());
    return ersatzaufwendungen;
  }

  public TextInput getBezeichnungSachzuwendung() throws RemoteException
  {
    if (bezeichnungsachzuwendung != null)
    {
      return bezeichnungsachzuwendung;
    }
    bezeichnungsachzuwendung = new TextInput(getSpendenbescheinigung()
        .getBezeichnungSachzuwendung(), 100);
    enableSachspende();
    return bezeichnungsachzuwendung;
  }

  public SelectInput getHerkunftSpende() throws RemoteException
  {
    if (herkunftspende != null)
    {
      return herkunftspende;
    }
    herkunftspende = new SelectInput(HerkunftSpende.getArray(),
        new HerkunftSpende(getSpendenbescheinigung().getHerkunftSpende()));
    enableSachspende();
    return herkunftspende;
  }

  public CheckboxInput getUnterlagenWertermittlung() throws RemoteException
  {
    if (unterlagenwertermittlung != null)
    {
      return unterlagenwertermittlung;
    }
    unterlagenwertermittlung = new CheckboxInput(getSpendenbescheinigung()
        .getUnterlagenWertermittlung());
    enableSachspende();
    return unterlagenwertermittlung;
  }

  /**
   * This method stores the project using the current values.
   */
  public void handleStore()
  {
    try
    {
      Spendenbescheinigung spb = getSpendenbescheinigung();
      Spendenart spa = (Spendenart) getSpendenart().getValue();
      spb.setSpendenart(spa.getKey());
      spb.setZeile1((String) getZeile1(false).getValue());
      spb.setZeile2((String) getZeile2().getValue());
      spb.setZeile3((String) getZeile3().getValue());
      spb.setZeile4((String) getZeile4().getValue());
      spb.setZeile5((String) getZeile5().getValue());
      spb.setZeile6((String) getZeile6().getValue());
      spb.setZeile7((String) getZeile7().getValue());
      spb.setSpendedatum((Date) getSpendedatum().getValue());
      spb.setBescheinigungsdatum((Date) getBescheinigungsdatum().getValue());
      spb.setBetrag((Double) getBetrag().getValue());
      spb.setErsatzAufwendungen((Boolean) getErsatzAufwendungen().getValue());
      spb.setBezeichnungSachzuwendung((String) getBezeichnungSachzuwendung()
          .getValue());
      spb.setFormular((Formular) getFormular().getValue());
      HerkunftSpende hsp = (HerkunftSpende) getHerkunftSpende().getValue();
      spb.setHerkunftSpende(hsp.getKey());
      spb.setUnterlagenWertermittlung((Boolean) getUnterlagenWertermittlung()
          .getValue());
      spb.store();

      GUI.getStatusBar().setSuccessText("Spendenbescheinigung gespeichert");
    }
    catch (ApplicationException e)
    {
      GUI.getStatusBar().setErrorText(e.getMessage());
    }
    catch (RemoteException e)
    {
      String fehler = "Fehler bei Speichern der Spendenbescheinigung";
      Logger.error(fehler, e);
      GUI.getStatusBar().setErrorText(fehler);
    }
  }

  public Button getPDFStandardButton()
  {
    Button b = new Button("PDF (Standard)", new Action()
    {

      public void handleAction(Object context) throws ApplicationException
      {
        try
        {
          generiereSpendenbescheinigungStandard();
        }
        catch (RemoteException e)
        {
          Logger.error(e.getMessage());
          throw new ApplicationException(
              "Fehler bei der Aufbereitung der Spendenbescheinigung");
        }
        catch (IOException e)
        {
          Logger.error(e.getMessage());
          throw new ApplicationException(
              "Fehler bei der Aufbereitung der Spendenbescheinigung");
        }
        catch (DocumentException e)
        {
          Logger.error(e.getMessage());
          throw new ApplicationException(
              "Fehler bei der Aufbereitung der Spendenbescheinigung");
        }
      }
    }, null, false, "acroread.png");
    return b;
  }

  public Button getPDFIndividuellButton()
  {
    Button b = new Button("PDF (Individuell)", new Action()
    {

      public void handleAction(Object context) throws ApplicationException
      {
        try
        {
          generiereSpendenbescheinigungIndividuell();
        }
        catch (RemoteException e)
        {
          Logger.error(e.getMessage());
          throw new ApplicationException(
              "Fehler bei der Aufbereitung der Spendenbescheinigung");
        }
        catch (IOException e)
        {
          Logger.error(e.getMessage());
          throw new ApplicationException(
              "Fehler bei der Aufbereitung der Spendenbescheinigung");
        }
      }
    }, null, false, "acroread.png");
    return b;
  }

  private void generiereSpendenbescheinigungStandard() throws IOException,
      DocumentException
  {
    FileDialog fd = new FileDialog(GUI.getShell(), SWT.SAVE);
    fd.setText("Ausgabedatei w�hlen.");
    String path = settings
        .getString("lastdir", System.getProperty("user.home"));
    if (path != null && path.length() > 0)
    {
      fd.setFilterPath(path);
    }
    fd.setFileName(new Dateiname("spendenbescheinigung", "", Einstellungen
        .getEinstellung().getDateinamenmuster(), "PDF").get());
    fd.setFilterExtensions(new String[] { "*.PDF" });

    String s = fd.open();
    if (s == null || s.length() == 0)
    {
      return;
    }
    if (!s.endsWith(".PDF"))
    {
      s = s + ".PDF";
    }
    final File file = new File(s);
    settings.setAttribute("lastdir", file.getParent());
    FileOutputStream fos = new FileOutputStream(file);
    Reporter rpt = new Reporter(fos, 80, 50, 50, 50);
    rpt.addHeaderColumn(
        "Aussteller (Bezeichnung und Anschrift der steuerbeg�nstigten Einrichtung)",
        Element.ALIGN_CENTER, 100, Color.LIGHT_GRAY);

    rpt.createHeader();
    rpt.addColumn("\n" + Einstellungen.getEinstellung().getName() + ", "
        + Einstellungen.getEinstellung().getStrasse() + ", "
        + Einstellungen.getEinstellung().getPlz() + " "
        + Einstellungen.getEinstellung().getOrt() + "\n ", Element.ALIGN_LEFT);
    rpt.closeTable();

    Spendenart spa = (Spendenart) getSpendenart().getValue();
    switch (spa.getKey())
    {
      case Spendenart.GELDSPENDE:
        rpt.add(
            "Best�tigung �ber Geldzuwendungen"
                + (Einstellungen.getEinstellung().getMitgliedsbetraege() ? "/Mitgliedsbeitrag"
                    : ""), 13);
        break;
      case Spendenart.SACHSPENDE:
        rpt.add("Best�tigung �ber Sachzuwendungen", 13);
        break;
    }
    rpt.add(
        "im Sinne des � 10b des Einkommenssteuergesetzes an eine der in � 5 Abs. 1 Nr. 9 des K�rperschaftssteuergesetzes "
            + "bezeichneten K�rperschaften, Personenvereinigungen oder Verm�gensmassen\n",
        10);

    rpt.addHeaderColumn("Name und Anschrift des Zuwendenden)",
        Element.ALIGN_CENTER, 100, Color.LIGHT_GRAY);
    rpt.createHeader();
    String zuwendender = (String) getZeile1(false).getValue() + "\n"
        + (String) getZeile2().getValue() + "\n"
        + (String) getZeile3().getValue() + "\n"
        + (String) getZeile4().getValue() + "\n"
        + (String) getZeile5().getValue() + "\n"
        + (String) getZeile6().getValue() + "\n"
        + (String) getZeile7().getValue() + "\n";
    rpt.addColumn(zuwendender, Element.ALIGN_LEFT);
    rpt.closeTable();

    switch (spa.getKey())
    {
      case Spendenart.GELDSPENDE:
        rpt.addHeaderColumn("Betrag der Zuwendung -in Ziffern-",
            Element.ALIGN_CENTER, 100, Color.LIGHT_GRAY);
        break;
      case Spendenart.SACHSPENDE:
        rpt.addHeaderColumn("Wert der Zuwendung -in Ziffern-",
            Element.ALIGN_CENTER, 100, Color.LIGHT_GRAY);
        break;
    }
    rpt.addHeaderColumn("-in Buchstaben-", Element.ALIGN_CENTER, 250,
        Color.LIGHT_GRAY);
    rpt.addHeaderColumn("Tag der Zuwendung", Element.ALIGN_CENTER, 50,
        Color.LIGHT_GRAY);
    rpt.createHeader();
    Double dWert = (Double) getBetrag().getValue();
    String sWert = "*" + Einstellungen.DECIMALFORMAT.format(dWert) + "*";
    rpt.addColumn(sWert, Element.ALIGN_CENTER);
    try
    {
      String betraginworten = GermanNumber.toString(dWert.longValue());
      betraginworten = "*" + betraginworten + "*";
      rpt.addColumn(betraginworten, Element.ALIGN_CENTER);
    }
    catch (Exception e)
    {
      Logger.error("Fehler", e);
      throw new RemoteException(
          "Fehler bei der Aufbereitung des Betrages in Worten");
    }
    String spendedatum = new JVDateFormatTTMMJJJJ()
        .format((Date) getSpendedatum().getValue());
    rpt.addColumn(spendedatum, Element.ALIGN_CENTER);
    rpt.closeTable();

    switch (spa.getKey())
    {
      case Spendenart.SACHSPENDE:
        rpt.addHeaderColumn(
            "Genaue Bezeichnung der Sachzuwendung mit Alter, Zustand, Kaufpreis usw.",
            Element.ALIGN_CENTER, 100, Color.LIGHT_GRAY);
        rpt.createHeader();
        rpt.addColumn((String) getBezeichnungSachzuwendung().getValue(),
            Element.ALIGN_LEFT);
        rpt.closeTable();
        HerkunftSpende hsp = (HerkunftSpende) getHerkunftSpende().getValue();
        switch (hsp.getKey())
        {
          case HerkunftSpende.BETRIEBSVERMOEGEN:
            rpt.add(
                "Die Sachzuwendung stammt nach den Angaben des Zuwendenden aus dem Betriebsverm�gen und ist "
                    + "mit dem Entnahmewert (ggf. mit dem niedrigeren gemeinen Wert) bewertet.\n\n",
                9);
            break;
          case HerkunftSpende.PRIVATVERMOEGEN:
            rpt.add(
                "Die Sachzuwendung stammt nach den Angaben des Zuwendenden aus dem Privatverm�gen.\n\n",
                9);
            break;
          case HerkunftSpende.KEINEANGABEN:
            rpt.add(
                "Der Zuwendende hat trotz Aufforderung keine Angaben zur Herkunft der Sachzuwendung gemacht.\n\n",
                9);
            break;
        }
        if ((Boolean) getUnterlagenWertermittlung().getValue())
        {
          rpt.add(
              "Geeignete Unterlagen, die zur Wertermittlung gedient haben, z. B. Rechnung, Gutachten, liegen vor.\n\n",
              9);
        }
    }

    String verzicht = "nein";
    if ((Boolean) getErsatzAufwendungen().getValue())
    {
      verzicht = "ja";
    }
    rpt.add("Es handelt sich um den Verzicht von Aufwendungen: " + verzicht
        + "\n\n", 9);

    if (!Einstellungen.getEinstellung().getVorlaeufig())
    {
      String txt = "Wir sind wegen F�rderung "
          + Einstellungen.getEinstellung().getBeguenstigterzweck()
          + " nach dem letzten uns zugegangenen Freistellungsbescheid bzw. nach der Anlage zum K�rperschaftssteuerbescheid des Finanzamtes "
          + Einstellungen.getEinstellung().getFinanzamt()
          + ", StNr. "
          + Einstellungen.getEinstellung().getSteuernummer()
          + ", vom "
          + new JVDateFormatTTMMJJJJ().format(Einstellungen.getEinstellung()
              .getBescheiddatum())
          + " nach � 5 Abs. 1 Nr. 9 des K�rperschaftsteuergesetzes von der K�rperschaftsteuer und nach � 3 Nr. 6 des Gewerbesteuergesetzes von der Gewerbesteuer befreit.";
      rpt.add(txt, 9);
    }
    else
    {
      String txt = "Wir sind wegen F�rderung "
          + Einstellungen.getEinstellung().getBeguenstigterzweck()
          + "durch vorl�ufige Bescheinigung des Finanzamtes"
          + Einstellungen.getEinstellung().getFinanzamt()
          + ", StNr. "
          + Einstellungen.getEinstellung().getSteuernummer()
          + ", vom "
          + new JVDateFormatTTMMJJJJ().format(Einstellungen.getEinstellung()
              .getBescheiddatum())
          + " ab "
          + new JVDateFormatTTMMJJJJ().format(Einstellungen.getEinstellung()
              .getVorlaeufigab())
          + " als steuerbeg�nstigten Zwecken dienend anerkannt.";
      rpt.add(txt, 9);
    }
    rpt.add("\n\nEs wird best�tigt, dass die Zuwendung nur zur F�rderung "
        + Einstellungen.getEinstellung().getBeguenstigterzweck()
        + " verwendet wird.\n", 9);
    if (!Einstellungen.getEinstellung().getMitgliedsbetraege()
        && spa.getKey() == Spendenart.GELDSPENDE)
    {
      rpt.add(
          "Es wird best�tigt, dass es sich nicht um einen Mitgliedsbeitrag i.S.v � 10b Abs. 1 Satz 2 Einkommensteuergesetzes handelt.",
          9);
    }
    rpt.add(
        "\n\n"
            + Einstellungen.getEinstellung().getOrt()
            + ", "
            + new JVDateFormatTTMMJJJJ().format((Date) getBescheinigungsdatum()
                .getValue()), 9);
    rpt.add(
        "\n\n\n\n.................................................................................\nUnterschrift des Zuwendungsempf�ngers",
        9);

    rpt.add("\n\nHinweis:", 9);
    rpt.add(
        "\nWer vors�tzlich oder grob fahrl�ssig eine unrichtige Zuwendungsbest�tigung erstellt oder wer veranlasst, dass "
            + "Zuwendungen nicht zu den in der Zuwendungsbest�tigung angegebenen steuerbeg�nstigten Zwecken verwendet "
            + "werden, haftet f�r die Steuer, die dem Fiskus durch einen etwaigen Abzug der Zuwendungen beim Zuwendenden "
            + "entgeht (� 10b Abs. 4 EStG, � 9 Abs. 3 KStG, � 9 Nr. 5 GewStG).\n\n"
            + "Diese Best�tigung wird nicht als Nachweis f�r die steuerliche Ber�cksichtigung der Zuwendung anerkannt, wenn das "
            + "Datum des Freistellungsbescheides l�nger als 5 Jahre bzw. das Datum der vorl�ufigen Bescheinigung l�nger als 3 Jahre "
            + "seit Ausstellung der Best�tigung zur�ckliegt (BMF vom 15.12.1994 - BStBl I S. 884).",
        8);
    rpt.close();
    fos.close();
  }

  private void generiereSpendenbescheinigungIndividuell() throws IOException
  {
    FileDialog fd = new FileDialog(GUI.getShell(), SWT.SAVE);
    fd.setText("Ausgabedatei w�hlen.");
    String path = settings
        .getString("lastdir", System.getProperty("user.home"));
    if (path != null && path.length() > 0)
    {
      fd.setFilterPath(path);
    }
    fd.setFileName(new Dateiname("spendenbescheinigung", "", Einstellungen
        .getEinstellung().getDateinamenmuster(), "PDF").get());
    fd.setFilterExtensions(new String[] { "*.PDF" });

    String s = fd.open();
    if (s == null || s.length() == 0)
    {
      return;
    }
    if (!s.endsWith(".PDF"))
    {
      s = s + ".PDF";
    }
    final File file = new File(s);
    settings.setAttribute("lastdir", file.getParent());
    Formular fo = (Formular) Einstellungen.getDBService().createObject(
        Formular.class, getSpendenbescheinigung().getFormular().getID());
    HashMap<String, Object> map = new HashMap<String, Object>();
    String empfaenger = (String) getZeile1(false).getValue() + "\n"
        + (String) getZeile2().getValue() + "\n"
        + (String) getZeile3().getValue() + "\n"
        + (String) getZeile4().getValue() + "\n"
        + (String) getZeile5().getValue() + "\n"
        + (String) getZeile6().getValue() + "\n"
        + (String) getZeile7().getValue() + "\n";
    map.put("Empf�nger", empfaenger);
    map.put("Betrag", getBetrag().getValue());
    Double dWert = (Double) getBetrag().getValue();
    try
    {
      String betraginworten = GermanNumber.toString(dWert.longValue());
      map.put("Betrag in Worten", "*" + betraginworten + "*");
    }
    catch (Exception e)
    {
      Logger.error("Fehler", e);
      throw new RemoteException(
          "Fehler bei der Aufbereitung des Betrages in Worten");
    }
    Date tmp = (Date) getBescheinigungsdatum().getValue();
    String bescheinigungsdatum = new JVDateFormatTTMMJJJJ().format(tmp);
    map.put("Bescheinigungsdatum", bescheinigungsdatum);
    tmp = (Date) getSpendedatum().getValue();
    String spendedatum = new JVDateFormatTTMMJJJJ().format(tmp);
    map.put("Spendedatum", spendedatum);
    String tagesdatum = new JVDateFormatTTMMJJJJ().format(new Date());
    map.put("Tagesdatum", tagesdatum);
    map.put("ErsatzAufwendungen",
        ((Boolean) ersatzaufwendungen.getValue() ? "X" : ""));
    FormularAufbereitung fa = new FormularAufbereitung(file);
    fa.writeForm(fo, map);
    fa.showFormular();

  }

  public Part getSpendenbescheinigungList() throws RemoteException
  {
    DBService service = Einstellungen.getDBService();
    DBIterator spendenbescheinigungen = service
        .createList(Spendenbescheinigung.class);
    spendenbescheinigungen.setOrder("ORDER BY bescheinigungsdatum desc");

    spbList = new TablePart(spendenbescheinigungen,
        new SpendenbescheinigungAction());
    spbList.addColumn("Bescheinigungsdatum", "bescheinigungsdatum",
        new DateFormatter(new JVDateFormatTTMMJJJJ()));
    spbList.addColumn("Spendedatum", "spendedatum", new DateFormatter(
        new JVDateFormatTTMMJJJJ()));
    spbList.addColumn("Betrag", "betrag", new CurrencyFormatter("",
        Einstellungen.DECIMALFORMAT));
    spbList.addColumn("Zeile 1", "zeile1");
    spbList.addColumn("Zeile 2", "zeile2");
    spbList.addColumn("Zeile 3", "zeile3");
    spbList.addColumn("Zeile 4", "zeile4");
    spbList.addColumn("Zeile 5", "zeile5");
    spbList.addColumn("Zeile 6", "zeile6");
    spbList.addColumn("Zeile 7", "zeile7");

    spbList.setRememberColWidths(true);
    spbList.setContextMenu(new SpendenbescheinigungMenu());
    spbList.setRememberOrder(true);
    spbList.setSummary(false);
    return spbList;
  }

  public void refreshTable() throws RemoteException
  {
    spbList.removeAll();
    DBIterator spendenbescheinigungen = Einstellungen.getDBService()
        .createList(Spendenbescheinigung.class);
    spendenbescheinigungen.setOrder("ORDER BY bescheinigungsdatum desc");
    while (spendenbescheinigungen.hasNext())
    {
      spbList.addItem(spendenbescheinigungen.next());
    }
  }

}
