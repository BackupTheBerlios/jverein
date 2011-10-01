/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/control/LehrgangControl.java,v $
 * $Revision: 1.7 $
 * $Date: 2011/10/01 21:42:56 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/
package de.jost_net.JVerein.gui.control;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.LehrgangAction;
import de.jost_net.JVerein.gui.menu.LehrgangMenu;
import de.jost_net.JVerein.rmi.Lehrgang;
import de.jost_net.JVerein.rmi.Lehrgangsart;
import de.jost_net.JVerein.rmi.Mitglied;
import de.jost_net.JVerein.util.JVDateFormatTTMMJJJJ;
import de.willuhn.datasource.rmi.DBIterator;
import de.willuhn.jameica.gui.AbstractControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.Part;
import de.willuhn.jameica.gui.formatter.DateFormatter;
import de.willuhn.jameica.gui.formatter.Formatter;
import de.willuhn.jameica.gui.input.DateInput;
import de.willuhn.jameica.gui.input.Input;
import de.willuhn.jameica.gui.input.SelectInput;
import de.willuhn.jameica.gui.input.TextInput;
import de.willuhn.jameica.gui.parts.TablePart;
import de.willuhn.jameica.system.Settings;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

public class LehrgangControl extends AbstractControl
{
  private TablePart lehrgaengeList;

  private SelectInput lehrgangsart;

  private DateInput von = null;

  private DateInput bis = null;

  private TextInput veranstalter = null;

  private TextInput ergebnis = null;

  private Lehrgang lehrg = null;

  // Elemente f�r die Auswertung

  private SelectInput suchlehrgangsart = null;

  private DateInput datumvon = null;

  private DateInput datumbis = null;

  private Settings settings = null;

  public LehrgangControl(AbstractView view)
  {
    super(view);
    settings = new Settings(this.getClass());
    settings.setStoreWhenRead(true);
  }

  public Lehrgang getLehrgang()
  {
    if (lehrg != null)
    {
      return lehrg;
    }
    lehrg = (Lehrgang) getCurrentObject();
    return lehrg;
  }

  public SelectInput getLehrgangsart() throws RemoteException
  {
    if (lehrgangsart != null)
    {
      return lehrgangsart;
    }
    DBIterator it = Einstellungen.getDBService().createList(Lehrgangsart.class);
    it.setOrder("order by bezeichnung");
    lehrgangsart = new SelectInput(it, getLehrgang().getLehrgangsart());
    lehrgangsart.setPleaseChoose("Bitte ausw�hlen");
    lehrgangsart.addListener(new Listener()
    {
      public void handleEvent(Event event)
      {
        Lehrgangsart la = (Lehrgangsart) lehrgangsart.getValue();
        try
        {
          if (la != null)
          {
            getVon().setValue(la.getVon());
            getBis().setValue(la.getBis());
            getVeranstalter().setValue(la.getVeranstalter());
          }
        }
        catch (RemoteException e)
        {
          e.printStackTrace();
        }
      }
    });

    return lehrgangsart;
  }

  public DateInput getVon() throws RemoteException
  {
    if (von != null)
    {
      return von;
    }

    Date d = getLehrgang().getVon();

    this.von = new DateInput(d, new JVDateFormatTTMMJJJJ());
    this.von.setTitle("Datum");
    this.von.setText("Bitte (Beginn-)Datum w�hlen");
    this.von.addListener(new Listener()
    {
      public void handleEvent(Event event)
      {
        Date date = (Date) von.getValue();
        if (date == null)
        {
          return;
        }
      }
    });
    return von;
  }

  public DateInput getBis() throws RemoteException
  {
    if (bis != null)
    {
      return bis;
    }

    Date d = getLehrgang().getBis();

    this.bis = new DateInput(d, new JVDateFormatTTMMJJJJ());
    this.bis.setTitle("Datum");
    this.bis.setText("Bitte Ende-Datum w�hlen");
    this.bis.addListener(new Listener()
    {
      public void handleEvent(Event event)
      {
        Date date = (Date) bis.getValue();
        if (date == null)
        {
          return;
        }
      }
    });
    return bis;
  }

  public Input getVeranstalter() throws RemoteException
  {
    if (veranstalter != null)
    {
      return veranstalter;
    }
    veranstalter = new TextInput(getLehrgang().getVeranstalter(), 50);
    return veranstalter;
  }

  public Input getErgebnis() throws RemoteException
  {
    if (ergebnis != null)
    {
      return ergebnis;
    }
    ergebnis = new TextInput(getLehrgang().getErgebnis(), 50);
    return ergebnis;
  }

  public SelectInput getSuchLehrgangsart() throws RemoteException
  {
    if (suchlehrgangsart != null)
    {
      return suchlehrgangsart;
    }
    DBIterator it = Einstellungen.getDBService().createList(Lehrgangsart.class);
    it.setOrder("order by bezeichnung");
    Lehrgang letztesuche = (Lehrgang) Einstellungen.getDBService()
        .createObject(Lehrgang.class,
            settings.getString("suchlehrgangsart", null));
    suchlehrgangsart = new SelectInput(it, letztesuche);
    suchlehrgangsart.setPleaseChoose("Bitte ausw�hlen");
    suchlehrgangsart.addListener(new FilterListener());
    return suchlehrgangsart;
  }

  public DateInput getDatumvon()
  {
    if (datumvon != null)
    {
      return datumvon;
    }
    Date d = null;
    String tmp = settings.getString("datum.von", null);
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
    this.datumvon = new DateInput(d, new JVDateFormatTTMMJJJJ());
    this.datumvon.setTitle("Datum von");
    this.datumvon.setText("Datum von");
    this.datumvon.addListener(new Listener()
    {

      public void handleEvent(Event event)
      {
        Date date = (Date) datumvon.getValue();
        if (date == null)
        {
          return;
        }
      }
    });
    datumvon.addListener(new FilterListener());
    return datumvon;
  }

  public DateInput getDatumbis()
  {
    if (datumbis != null)
    {
      return datumbis;
    }
    Date d = null;
    String tmp = settings.getString("datum.bis", null);
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
    this.datumbis = new DateInput(d, new JVDateFormatTTMMJJJJ());
    this.datumbis.setTitle("Datum bis");
    this.datumbis.setText("Datum bis");
    this.datumbis.addListener(new Listener()
    {

      public void handleEvent(Event event)
      {
        Date date = (Date) datumbis.getValue();
        if (date == null)
        {
          return;
        }
      }
    });
    datumbis.addListener(new FilterListener());
    return datumbis;
  }

  public void handleStore()
  {
    try
    {
      Lehrgang l = getLehrgang();
      Lehrgangsart la = (Lehrgangsart) getLehrgangsart().getValue();
      l.setLehrgangsart(new Integer(la.getID()));
      l.setVon((Date) getVon().getValue());
      l.setBis((Date) getBis().getValue());
      l.setVeranstalter((String) getVeranstalter().getValue());
      l.setErgebnis((String) getErgebnis().getValue());
      l.store();
      GUI.getStatusBar().setSuccessText("Lehrgang gespeichert");
    }
    catch (ApplicationException e)
    {
      GUI.getStatusBar().setErrorText(e.getMessage());
    }
    catch (RemoteException e)
    {
      String fehler = "Fehler bei speichern des Lehrgangs";
      Logger.error(fehler, e);
      GUI.getStatusBar().setErrorText(fehler);
    }
  }

  private void refresh()
  {

    try
    {
      saveDefaults();
      if (lehrgaengeList == null)
      {
        return;
      }
      lehrgaengeList.removeAll();
      DBIterator lehrgaenge = getIterator();
      while (lehrgaenge.hasNext())
      {
        Lehrgang lg = (Lehrgang) lehrgaenge.next();
        lehrgaengeList.addItem(lg);
      }
    }
    catch (RemoteException e1)
    {
      e1.printStackTrace();
    }
  }

  private DBIterator getIterator() throws RemoteException
  {
    DBIterator lehrgaenge = Einstellungen.getDBService().createList(
        Lehrgang.class);
    if (getSuchLehrgangsart().getValue() != null)
    {
      Lehrgangsart la = (Lehrgangsart) getSuchLehrgangsart().getValue();
      lehrgaenge.addFilter("lehrgangsart = ?", new Object[] { la.getID() });
    }
    if (getDatumvon().getValue() != null)
    {
      lehrgaenge.addFilter("von >= ?", new Object[] { (Date) getDatumvon()
          .getValue() });
    }
    if (getDatumbis().getValue() != null)
    {
      lehrgaenge.addFilter("bis <= ?", new Object[] { (Date) getDatumbis()
          .getValue() });
    }
    return lehrgaenge;
  }

  /**
   * Default-Werte speichern.
   * 
   * @throws RemoteException
   */
  public void saveDefaults() throws RemoteException
  {
    if (this.suchlehrgangsart != null)
    {
      Lehrgangsart la = (Lehrgangsart) getSuchLehrgangsart().getValue();
      if (la != null)
      {
        settings.setAttribute("suchlehrgangsart", la.getID());
      }
      else
      {
        settings.setAttribute("suchlehrgangsart", "");
      }
    }
    if (this.datumvon != null)
    {
      Date tmp = (Date) getDatumvon().getValue();
      if (tmp != null)
      {
        settings.setAttribute("datum.von",
            new JVDateFormatTTMMJJJJ().format(tmp));
      }
      else
      {
        settings.setAttribute("datum.von", "");
      }
    }

    if (this.datumbis != null)
    {
      Date tmp = (Date) getDatumbis().getValue();
      if (tmp != null)
      {
        settings.setAttribute("datum.bis",
            new JVDateFormatTTMMJJJJ().format(tmp));
      }
      else
      {
        settings.setAttribute("datum.bis", "");
      }
    }

  }

  public Part getLehrgaengeList() throws RemoteException
  {
    DBIterator lehrgaenge = getIterator();
    if (lehrgaengeList == null)
    {
      lehrgaengeList = new TablePart(lehrgaenge, new LehrgangAction(null));
      lehrgaengeList.addColumn(JVereinPlugin.getI18n().tr("Name"), "mitglied",
          new Formatter()
          {
            public String format(Object o)
            {
              Mitglied m = (Mitglied) o;
              if (m == null)
                return null;
              String name = null;
              try
              {
                name = m.getNameVorname();
              }
              catch (RemoteException e)
              {
                e.printStackTrace();
              }
              return name;
            }
          });
      lehrgaengeList.addColumn("Lehrgangsart", "lehrgangsart");
      lehrgaengeList.addColumn(JVereinPlugin.getI18n().tr("von/am"), "von",
          new DateFormatter(new JVDateFormatTTMMJJJJ()));
      lehrgaengeList.addColumn(JVereinPlugin.getI18n().tr("bis"), "bis",
          new DateFormatter(new JVDateFormatTTMMJJJJ()));
      lehrgaengeList.addColumn(JVereinPlugin.getI18n().tr("Veranstalter"),
          "veranstalter");
      lehrgaengeList.addColumn(JVereinPlugin.getI18n().tr("Ergebnis"),
          "ergebnis");
      lehrgaengeList.setContextMenu(new LehrgangMenu());
      lehrgaengeList.setRememberColWidths(true);
      lehrgaengeList.setRememberOrder(true);
      lehrgaengeList.setSummary(true);
    }
    else
    {
      lehrgaengeList.removeAll();
      while (lehrgaenge.hasNext())
      {
        lehrgaengeList.addItem(lehrgaenge.next());
      }
    }
    return lehrgaengeList;
  }

  private class FilterListener implements Listener
  {

    public void handleEvent(Event event)
    {
      if (event.type != SWT.Selection && event.type != SWT.FocusOut)
      {
        return;
      }
      refresh();
    }
  }

}
