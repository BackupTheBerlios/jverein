/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/parts/JahressaldoList.java,v $
 * $Revision: 1.4 $
 * $Date: 2009/06/11 21:03:24 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: JahressaldoList.java,v $
 * Revision 1.4  2009/06/11 21:03:24  jost
 * Vorbereitung I18N
 *
 * Revision 1.3  2009/03/01 18:00:20  jost
 * Zus�tzliche Zeile "�berschuss/Verlust"
 *
 * Revision 1.2  2008/11/16 16:57:37  jost
 * Speicherung der Einstellung von Property-Datei in die Datenbank verschoben.
 *
 * Revision 1.1  2008/06/28 16:59:00  jost
 * Neu: Jahresabschluss
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.parts;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.ArrayList;

import org.eclipse.swt.widgets.Composite;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.io.SaldoZeile;
import de.jost_net.JVerein.rmi.Konto;
import de.jost_net.JVerein.util.Geschaeftsjahr;
import de.willuhn.datasource.GenericIterator;
import de.willuhn.datasource.GenericObject;
import de.willuhn.datasource.pseudo.PseudoIterator;
import de.willuhn.datasource.rmi.DBIterator;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.Part;
import de.willuhn.jameica.gui.formatter.CurrencyFormatter;
import de.willuhn.jameica.gui.parts.Column;
import de.willuhn.jameica.gui.parts.TablePart;
import de.willuhn.util.ApplicationException;

public class JahressaldoList extends TablePart implements Part
{
  private TablePart saldoList;

  private Geschaeftsjahr gj = null;

  public JahressaldoList(Action action, int jahr) throws RemoteException
  {
    super(action);
    try
    {
      gj = new Geschaeftsjahr(jahr);
    }
    catch (ParseException e)
    {
      e.printStackTrace();
    }
  }

  public JahressaldoList(Action action, Geschaeftsjahr gj)
  {
    super(action);
    this.gj = gj;
  }

  public Part getSaldoList() throws ApplicationException
  {
    ArrayList<SaldoZeile> zeile = null;
    try
    {
      zeile = getInfo();

      if (saldoList == null)
      {
        GenericIterator gi = PseudoIterator.fromArray((GenericObject[]) zeile
            .toArray(new GenericObject[zeile.size()]));

        saldoList = new TablePart(gi, null);
        saldoList.addColumn(JVereinPlugin.getI18n().tr("Kontonummer"),
            "kontonummer", null, false, Column.ALIGN_RIGHT);
        saldoList.addColumn(JVereinPlugin.getI18n().tr("Bezeichnung"),
            "kontobezeichnung");
        saldoList.addColumn(JVereinPlugin.getI18n().tr("Anfangsbestand"),
            "anfangsbestand", new CurrencyFormatter("",
                Einstellungen.DECIMALFORMAT), false, Column.ALIGN_RIGHT);
        saldoList.addColumn(JVereinPlugin.getI18n().tr("Einnahmen"),
            "einnahmen",
            new CurrencyFormatter("", Einstellungen.DECIMALFORMAT), false,
            Column.ALIGN_RIGHT);
        saldoList.addColumn(JVereinPlugin.getI18n().tr("Ausgaben"), "ausgaben",
            new CurrencyFormatter("", Einstellungen.DECIMALFORMAT), false,
            Column.ALIGN_RIGHT);
        saldoList.addColumn(JVereinPlugin.getI18n().tr("Umbuchungen"),
            "umbuchungen", new CurrencyFormatter("",
                Einstellungen.DECIMALFORMAT), false, Column.ALIGN_RIGHT);
        saldoList.addColumn(JVereinPlugin.getI18n().tr("Endbestand"),
            "endbestand",
            new CurrencyFormatter("", Einstellungen.DECIMALFORMAT), false,
            Column.ALIGN_RIGHT);
        saldoList.addColumn(JVereinPlugin.getI18n().tr("Bemerkung"),
            "bemerkung");
        saldoList.setRememberColWidths(true);
        saldoList.setSummary(false);
      }
      else
      {
        saldoList.removeAll();
        for (SaldoZeile sz : zeile)
        {
          saldoList.addItem(sz);
        }
      }
    }
    catch (RemoteException e)
    {
      throw new ApplicationException(JVereinPlugin.getI18n().tr(
          "Fehler aufgetreten")
          + e.getMessage());
    }
    catch (ParseException e)
    {
      throw new ApplicationException(JVereinPlugin.getI18n().tr(
          "Fehler aufgetreten")
          + e.getMessage());
    }
    return saldoList;
  }

  public ArrayList<SaldoZeile> getInfo() throws RemoteException, ParseException
  {
    ArrayList<SaldoZeile> zeile = new ArrayList<SaldoZeile>();
    Konto k = (Konto) Einstellungen.getDBService().createObject(Konto.class,
        null);
    DBIterator konten = k.getKontenEinesJahres(gj);
    double anfangsbestand = 0;
    double einnahmen = 0;
    double ausgaben = 0;
    double umbuchungen = 0;
    double endbestand = 0;
    double jahressaldo = 0;
    if (gj != null)
    {
      while (konten.hasNext())
      {
        SaldoZeile sz = new SaldoZeile(gj, (Konto) konten.next());
        anfangsbestand += (Double) sz.getAttribute("anfangsbestand");
        einnahmen += (Double) sz.getAttribute("einnahmen");
        ausgaben += (Double) sz.getAttribute("ausgaben");
        umbuchungen += (Double) sz.getAttribute("umbuchungen");
        endbestand += (Double) sz.getAttribute("endbestand");
        jahressaldo += (Double) sz.getAttribute("endbestand")
            - (Double) sz.getAttribute("anfangsbestand");
        zeile.add(sz);
      }
    }
    k = (Konto) Einstellungen.getDBService().createObject(Konto.class, null);
    k.setNummer("");
    k.setBezeichnung(JVereinPlugin.getI18n().tr("Summe"));
    zeile.add(new SaldoZeile(k, anfangsbestand, einnahmen, ausgaben,
        umbuchungen, endbestand));
    k = (Konto) Einstellungen.getDBService().createObject(Konto.class, null);
    k.setNummer("");
    k.setBezeichnung(JVereinPlugin.getI18n().tr("�berschuss/Verlust(-)"));
    zeile.add(new SaldoZeile(k, null, null, null, null, jahressaldo));

    return zeile;
  }

  public void setGeschaeftsjahr(Geschaeftsjahr gj)
  {
    this.gj = gj;
  }

  public void removeAll()
  {
    saldoList.removeAll();
  }

  public void addItem(SaldoZeile sz) throws RemoteException
  {
    saldoList.addItem(sz);
  }

  public synchronized void paint(Composite parent) throws RemoteException
  {
    super.paint(parent);
  }

}
