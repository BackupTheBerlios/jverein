/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/parts/ArbeitseinsatzUeberpruefungList.java,v $
 * $Revision: 1.3 $
 * $Date: 2011/01/04 14:19:59 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: ArbeitseinsatzUeberpruefungList.java,v $
 * Revision 1.3  2011/01/04 14:19:59  jost
 * Einschr�nkung auf die Beitragsgruppen mit Arbeitseins�tzen.
 *
 * Revision 1.2  2010-11-24 21:56:31  jost
 * nur angemeldete Mitglieder
 *
 * Revision 1.1  2010-11-22 21:00:04  jost
 * Initial Commit
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.parts;

import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.swt.widgets.Composite;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.MitgliedDetailAction;
import de.jost_net.JVerein.gui.input.ArbeitseinsatzUeberpruefungInput;
import de.jost_net.JVerein.io.ArbeitseinsatzZeile;
import de.willuhn.datasource.GenericIterator;
import de.willuhn.datasource.GenericObject;
import de.willuhn.datasource.pseudo.PseudoIterator;
import de.willuhn.datasource.rmi.ResultSetExtractor;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.Part;
import de.willuhn.jameica.gui.formatter.CurrencyFormatter;
import de.willuhn.jameica.gui.parts.Column;
import de.willuhn.jameica.gui.parts.TablePart;
import de.willuhn.util.ApplicationException;

public class ArbeitseinsatzUeberpruefungList extends TablePart implements Part
{

  private TablePart arbeitseinsatzueberpruefungList;

  private int jahr;

  private int schluessel;

  public ArbeitseinsatzUeberpruefungList(Action action, int jahr, int schluessel)
      throws RemoteException
  {
    super(action);
    this.jahr = jahr;
    this.schluessel = schluessel;
  }

  public Part getArbeitseinsatzUeberpruefungList() throws ApplicationException
  {
    ArrayList<ArbeitseinsatzZeile> zeile = null;
    try
    {
      zeile = getInfo();

      if (arbeitseinsatzueberpruefungList == null)
      {
        GenericIterator gi = PseudoIterator.fromArray(zeile.toArray(new GenericObject[zeile.size()]));

        arbeitseinsatzueberpruefungList = new TablePart(gi,
            new MitgliedDetailAction());
        arbeitseinsatzueberpruefungList.addColumn(JVereinPlugin.getI18n().tr(
            "Name, Vorname"), "namevorname");
        arbeitseinsatzueberpruefungList.addColumn(JVereinPlugin.getI18n().tr(
            "Sollstunden"), "soll");
        arbeitseinsatzueberpruefungList.addColumn(JVereinPlugin.getI18n().tr(
            "Iststunden"), "ist");
        arbeitseinsatzueberpruefungList.addColumn(JVereinPlugin.getI18n().tr(
            "Differenz"), "differenz");
        arbeitseinsatzueberpruefungList.addColumn(JVereinPlugin.getI18n().tr(
            "Stundensatz"), "stundensatz", new CurrencyFormatter("",
            Einstellungen.DECIMALFORMAT), false, Column.ALIGN_RIGHT);
        arbeitseinsatzueberpruefungList.addColumn(JVereinPlugin.getI18n().tr(
            "Gesamtbetrag"), "gesamtbetrag", new CurrencyFormatter("",
            Einstellungen.DECIMALFORMAT), false, Column.ALIGN_RIGHT);
        arbeitseinsatzueberpruefungList.setRememberColWidths(true);
        arbeitseinsatzueberpruefungList.setSummary(true);
      }
      else
      {
        arbeitseinsatzueberpruefungList.removeAll();
        for (ArbeitseinsatzZeile az : zeile)
        {
          arbeitseinsatzueberpruefungList.addItem(az);
        }
      }
    }
    catch (RemoteException e)
    {
      throw new ApplicationException(JVereinPlugin.getI18n().tr(
          "Fehler aufgetreten")
          + e.getMessage());
    }
    return arbeitseinsatzueberpruefungList;
  }

  public ArrayList<ArbeitseinsatzZeile> getInfo() throws RemoteException
  {

    String sql = "select mitglied.id as id, arbeitseinsatzstunden  sollstunden, beitragsgruppe.arbeitseinsatzbetrag as betrag, sum(stunden)  iststunden from mitglied "
        + "  join beitragsgruppe on mitglied.beitragsgruppe = beitragsgruppe.id "
        + "  left join arbeitseinsatz on mitglied.id = arbeitseinsatz.mitglied and year(arbeitseinsatz.datum) = ? "
        + "where  (mitglied.eintritt is null or year(mitglied.eintritt) <= ?) and (mitglied.austritt is null or year(mitglied.austritt) > ?) and beitragsgruppe.arbeitseinsatzstunden is not null and beitragsgruppe.arbeitseinsatzstunden > 0 and ";

    if (schluessel == ArbeitseinsatzUeberpruefungInput.MINDERLEISTUNG)
    {
      sql += "    (arbeitseinsatzstunden is not null and (stunden < arbeitseinsatzstunden or stunden is null)) ";
    }

    if (schluessel == ArbeitseinsatzUeberpruefungInput.PASSENDELEISTUNG)
    {
      sql += "  (arbeitseinsatzstunden is not null and stunden = arbeitseinsatzstunden) ";
    }

    if (schluessel == ArbeitseinsatzUeberpruefungInput.MEHRLEISTUNG)
    {
      sql += "  (arbeitseinsatzstunden is not null and stunden > arbeitseinsatzstunden) ";
    }

    sql += "  group by mitglied.id "
        + "  order by mitglied.name, mitglied.vorname, mitglied.id ";

    ResultSetExtractor rs = new ResultSetExtractor()
    {

      public Object extract(ResultSet rs) throws SQLException
      {
        ArrayList<ArbeitseinsatzZeile> ergebnis = new ArrayList<ArbeitseinsatzZeile>();
        while (rs.next())
        {
          ArbeitseinsatzZeile z = new ArbeitseinsatzZeile(rs.getString("id"),
              rs.getDouble("sollstunden"), rs.getDouble("iststunden"),
              rs.getDouble("betrag"));
          ergebnis.add(z);
        }
        return ergebnis;
      }
    };
    return (ArrayList<ArbeitseinsatzZeile>) Einstellungen.getDBService().execute(
        sql, new Object[] { jahr, jahr, jahr}, rs);
  }

  @Override
  public void removeAll()
  {
    arbeitseinsatzueberpruefungList.removeAll();
  }

  public void addItem(ArbeitseinsatzZeile az) throws RemoteException
  {
    arbeitseinsatzueberpruefungList.addItem(az);
  }

  @Override
  public synchronized void paint(Composite parent) throws RemoteException
  {
    super.paint(parent);
  }

  public void setJahr(int jahr)
  {
    this.jahr = jahr;
  }

  public void setSchluessel(int schluessel)
  {
    this.schluessel = schluessel;
  }

}
