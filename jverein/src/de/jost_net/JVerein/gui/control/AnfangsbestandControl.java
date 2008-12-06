/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/control/AnfangsbestandControl.java,v $
 * $Revision: 1.3 $
 * $Date: 2008/12/06 16:45:40 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: AnfangsbestandControl.java,v $
 * Revision 1.3  2008/12/06 16:45:40  jost
 * Debug-Meldung entfernt.
 *
 * Revision 1.2  2008/07/12 11:55:16  jost
 * Bugfix beim Speichern des Anfangsbestandes.
 *
 * Revision 1.1  2008/05/22 06:47:13  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.control;

import java.rmi.RemoteException;
import java.util.Date;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.gui.action.AnfangsbestandDetailAction;
import de.jost_net.JVerein.gui.menu.AnfangsbestandMenu;
import de.jost_net.JVerein.rmi.Anfangsbestand;
import de.jost_net.JVerein.rmi.Konto;
import de.willuhn.datasource.rmi.DBIterator;
import de.willuhn.datasource.rmi.DBService;
import de.willuhn.jameica.gui.AbstractControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.Part;
import de.willuhn.jameica.gui.formatter.CurrencyFormatter;
import de.willuhn.jameica.gui.formatter.DateFormatter;
import de.willuhn.jameica.gui.input.DateInput;
import de.willuhn.jameica.gui.input.DecimalInput;
import de.willuhn.jameica.gui.input.TextInput;
import de.willuhn.jameica.gui.parts.TablePart;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

public class AnfangsbestandControl extends AbstractControl
{
  private de.willuhn.jameica.system.Settings settings;

  private TablePart anfangsbestandList;

  private TextInput konto;

  private DateInput datum;

  private DecimalInput betrag;

  private Anfangsbestand anfangsbestand;

  public AnfangsbestandControl(AbstractView view)
  {
    super(view);
    settings = new de.willuhn.jameica.system.Settings(this.getClass());
    settings.setStoreWhenRead(true);
  }

  public Anfangsbestand getAnfangsbestand()
  {
    if (anfangsbestand != null)
    {
      return anfangsbestand;
    }
    anfangsbestand = (Anfangsbestand) getCurrentObject();
    return anfangsbestand;
  }

  public TextInput getKonto() throws RemoteException
  {
    if (konto != null)
    {
      return konto;
    }
    konto = new TextInput((String) getAnfangsbestand().getKonto().getNummer(),
        10);
    konto.setEnabled(false);
    return konto;
  }

  public DateInput getDatum() throws RemoteException
  {
    if (datum != null)
    {
      return datum;
    }
    datum = new DateInput(getAnfangsbestand().getDatum());
    return datum;
  }

  public DecimalInput getBetrag() throws RemoteException
  {
    if (betrag != null)
    {
      return betrag;
    }
    betrag = new DecimalInput(getAnfangsbestand().getBetrag(),
        Einstellungen.DECIMALFORMAT);
    return betrag;
  }

  /**
   * This method stores the project using the current values.
   */
  public void handleStore()
  {
    try
    {
      Anfangsbestand a = getAnfangsbestand();
      DBIterator konten = Einstellungen.getDBService().createList(Konto.class);
      konten.addFilter("nummer = ?", new Object[] { (String) getKonto()
          .getValue() });
      if (konten.size() == 0)
      {
        throw new RemoteException("Konto nicht gefunden");
      }
      if (konten.size() > 1)
      {
        throw new RemoteException(
            "Mehrere Konten mit gleicher Nummer sind nicht zul�ssig!");
      }
      Konto k = (Konto) konten.next();
      a.setKonto(k);
      a.setDatum((Date) getDatum().getValue());
      a.setBetrag((Double) getBetrag().getValue());
      a.store();
      GUI.getStatusBar().setSuccessText("Anfangsbestand gespeichert");
    }
    catch (RemoteException e)
    {
      String fehler = "Fehler bei speichern des Anfangsbestandes";
      Logger.error(fehler, e);
      GUI.getStatusBar().setErrorText(fehler);
    }
    catch (ApplicationException e)
    {
      GUI.getStatusBar().setErrorText(e.getMessage());
    }
  }

  public Part getAnfangsbestandList() throws RemoteException
  {
    DBService service = Einstellungen.getDBService();
    DBIterator anfangsbestaende = service.createList(Anfangsbestand.class);
    anfangsbestaende.setOrder("ORDER BY konto, datum desc");

    anfangsbestandList = new TablePart(anfangsbestaende,
        new AnfangsbestandDetailAction());
    anfangsbestandList.addColumn("Konto", "kontotext");
    anfangsbestandList.addColumn("Datum", "datum", new DateFormatter(
        Einstellungen.DATEFORMAT));
    anfangsbestandList.addColumn("Betrag", "betrag", new CurrencyFormatter("",
        Einstellungen.DECIMALFORMAT));
    anfangsbestandList.setRememberColWidths(true);
    anfangsbestandList.setContextMenu(new AnfangsbestandMenu());
    anfangsbestandList.setRememberOrder(true);
    anfangsbestandList.setSummary(false);
    return anfangsbestandList;
  }

  public void refreshTable() throws RemoteException
  {
    anfangsbestandList.removeAll();
    DBIterator anfangsbestaende = Einstellungen.getDBService().createList(
        Anfangsbestand.class);
    anfangsbestaende.setOrder("ORDER BY konto, datum desc");
    while (anfangsbestaende.hasNext())
    {
      anfangsbestandList.addItem((Anfangsbestand) anfangsbestaende.next());
    }
  }

}
