/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/control/BuchungsuebernahmeControl.java,v $
 * $Revision: 1.17 $
 * $Date: 2011/01/11 17:41:10 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: BuchungsuebernahmeControl.java,v $
 * Revision 1.17  2011/01/11 17:41:10  jost
 * Keine Vormerkposten �bernehmen.
 *
 * Revision 1.16  2010-12-31 16:44:13  jost
 * Bug 17827 gefixed
 *
 * Revision 1.15  2010-11-13 09:23:27  jost
 * Warnings entfernt.
 *
 * Revision 1.14  2010-10-15 09:58:26  jost
 * Code aufger�umt
 *
 * Revision 1.13  2010-10-10 06:36:14  jost
 * Bugfix "leere Kontoauswahl".
 *
 * Revision 1.12  2010-08-23 13:33:14  jost
 * Optimierung Tastatursteuerung
 *
 * Revision 1.11  2009/12/17 19:23:50  jost
 * Mehrfache Buchungs�bernahme verhindert.
 *
 * Revision 1.10  2009/01/22 18:23:35  jost
 * neue Icons
 *
 * Revision 1.9  2009/01/20 20:09:24  jost
 * neue Icons
 *
 * Revision 1.8  2009/01/04 16:27:14  jost
 * Neu: F�r mehrere Buchungen gleichzeitig die Buchungsart festlegen.
 *
 * Revision 1.7  2008/11/29 13:06:41  jost
 * Refactoring: Warnungen beseitigt.
 *
 * Revision 1.6  2008/05/24 16:39:17  jost
 * Wegfall der Spalte Saldo
 *
 * Revision 1.5  2008/05/22 06:48:07  jost
 * Buchführung
 *
 * Revision 1.4  2008/03/16 07:35:49  jost
 * Reaktivierung Buchführung
 *
 * Revision 1.2  2007/02/23 20:26:22  jost
 * Mail- und Webadresse im Header korrigiert.
 *
 * Revision 1.1  2006/09/20 15:38:30  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.control;

import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.gui.input.KontoauswahlInput;
import de.jost_net.JVerein.gui.menu.BuchungMenu;
import de.jost_net.JVerein.rmi.Buchung;
import de.jost_net.JVerein.rmi.Konto;
import de.willuhn.datasource.rmi.DBIterator;
import de.willuhn.datasource.rmi.DBService;
import de.willuhn.datasource.rmi.ResultSetExtractor;
import de.willuhn.jameica.gui.AbstractControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.Part;
import de.willuhn.jameica.gui.formatter.CurrencyFormatter;
import de.willuhn.jameica.gui.formatter.DateFormatter;
import de.willuhn.jameica.gui.input.DialogInput;
import de.willuhn.jameica.gui.parts.Button;
import de.willuhn.jameica.gui.parts.TablePart;
import de.willuhn.jameica.hbci.HBCI;
import de.willuhn.jameica.hbci.rmi.Umsatz;
import de.willuhn.jameica.system.Application;
import de.willuhn.jameica.system.Settings;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

public class BuchungsuebernahmeControl extends AbstractControl
{

  private Settings settings = null;

  private DialogInput konto = null;

  private TablePart buchungsList;

  public BuchungsuebernahmeControl(AbstractView view)
  {
    super(view);
    settings = new Settings(this.getClass());
    settings.setStoreWhenRead(true);
  }

  public DialogInput getKonto() throws RemoteException
  {
    if (konto != null)
    {
      return konto;
    }
    String kontoid = settings.getString("kontoid", "");
    konto = new KontoauswahlInput().getKontoAuswahl(false, kontoid);
    return konto;
  }

  public Button getSuchButton()
  {
    Button button = new Button("Suchen", new Action()
    {

      public void handleAction(Object context) throws ApplicationException
      {
        try
        {
          getBuchungsList();
          Konto k = (Konto) getKonto().getValue();
          settings.setAttribute("kontoid", k.getID());
        }
        catch (RemoteException e)
        {
          e.printStackTrace();
          throw new ApplicationException("Fehler beim Aufbau der Buchungsliste");
        }
      }
    }, null, false, "system-search.png");
    return button;
  }

  public Button getUebernahmeButton()
  {
    Button button = new Button("&�bernahme", new Action()
    {

      public void handleAction(Object context) throws ApplicationException
      {
        try
        {
          List<?> buchungen = buchungsList.getItems();
          for (int i = 0; i < buchungen.size(); i++)
          {
            Umsatz u = (Umsatz) buchungen.get(i);
            if ((u.getFlags() & Umsatz.FLAG_NOTBOOKED) == 0)
            {
              Buchung b = (Buchung) Einstellungen.getDBService().createObject(
                  Buchung.class, null);
              b.setUmsatzid(new Integer(u.getID()));
              b.setKonto((Konto) getKonto().getValue());
              b.setName(u.getGegenkontoName());
              b.setBetrag(u.getBetrag());
              b.setZweck(u.getZweck());
              b.setZweck2(u.getZweck2());
              b.setDatum(u.getDatum());
              b.setArt(u.getArt());
              b.setKommentar(u.getKommentar());
              b.store();
              buchungsList.removeItem(u);
            }
          }
          GUI.getStatusBar().setSuccessText("Daten �bernommen");
          GUI.getCurrentView().reload();
        }
        catch (ApplicationException ae)
        {
          GUI.getStatusBar().setErrorText(ae.getMessage());
          throw ae;
        }
        catch (Exception e)
        {
          Logger.error("error while reading objects from ", e);
          ApplicationException ae = new ApplicationException(
              "Fehler beim der �bernahme: " + e.getMessage(), e);
          GUI.getStatusBar().setErrorText(ae.getMessage());
          throw ae;
        }
      }
    }, null, true, "go.png");
    return button;
  }

  public Part getBuchungsList() throws RemoteException
  {
    Integer hibid = new Integer(-1);
    Integer jvid = new Integer(-1);
    Konto k = (Konto) getKonto().getValue();
    if (k != null && k.getHibiscusId() != null)
    {
      hibid = k.getHibiscusId();
      jvid = new Integer(k.getID());
    }
    DBService service = Einstellungen.getDBService();
    String sql = "select max(umsatzid) from buchung where konto = "
        + jvid.toString();

    ResultSetExtractor rs = new ResultSetExtractor()
    {

      public Object extract(ResultSet rs) throws SQLException
      {
        if (!rs.next())
        {
          return new Integer(0);
        }
        return new Integer(rs.getInt(1));
      }
    };
    Integer maximum = (Integer) service.execute(sql, new Object[] {}, rs);

    try
    {
      DBService hibservice = (DBService) Application.getServiceFactory()
          .lookup(HBCI.class, "database");
      DBIterator hibbuchungen = hibservice.createList(Umsatz.class);
      if (maximum.intValue() > 0)
      {
        hibbuchungen.addFilter("id >" + maximum);
      }
      hibbuchungen.addFilter("konto_id = ?", new Object[] { hibid });
      hibbuchungen.setOrder("ORDER BY id");

      if (buchungsList == null)
      {
        buchungsList = new TablePart(hibbuchungen, null);
        buchungsList.addColumn("Nr", "id");
        buchungsList.addColumn("Datum", "datum", new DateFormatter(
            Einstellungen.DATEFORMAT));
        buchungsList.addColumn("Name", "name");
        buchungsList.addColumn("Verwendungszweck", "zweck");
        buchungsList.addColumn("Verwendungszweck 2", "zweck2");
        buchungsList.addColumn("Betrag", "betrag", new CurrencyFormatter("",
            Einstellungen.DECIMALFORMAT));
        buchungsList.setContextMenu(new BuchungMenu());
        buchungsList.setRememberColWidths(true);
        buchungsList.setRememberOrder(true);
        buchungsList.setSummary(true);
      }
      else
      {
        buchungsList.removeAll();
        while (hibbuchungen.hasNext())
        {
          buchungsList.addItem(hibbuchungen.next());
        }
      }
    }
    catch (Exception e)
    {
      throw new RemoteException(e.getMessage());
    }
    return buchungsList;
  }

}
