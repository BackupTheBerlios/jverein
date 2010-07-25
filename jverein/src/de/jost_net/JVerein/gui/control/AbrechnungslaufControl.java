/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/control/AbrechnungslaufControl.java,v $
 * $Revision: 1.2 $
 * $Date: 2010/07/25 18:30:44 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: AbrechnungslaufControl.java,v $
 * Revision 1.2  2010/07/25 18:30:44  jost
 * Finetuning
 *
 * Revision 1.1  2010/05/18 20:19:04  jost
 * Anpassung Klassenname
 *
 * Revision 1.1  2010/04/25 13:54:14  jost
 * Vorarbeiten Mitgliedskonto
 *
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.control;

import java.rmi.RemoteException;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.gui.formatter.AbrechnungsmodusFormatter;
import de.jost_net.JVerein.gui.formatter.JaNeinFormatter;
import de.jost_net.JVerein.gui.menu.AbrechnungslaufMenu;
import de.jost_net.JVerein.rmi.Abrechnungslauf;
import de.willuhn.datasource.rmi.DBIterator;
import de.willuhn.datasource.rmi.DBService;
import de.willuhn.jameica.gui.AbstractControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.Part;
import de.willuhn.jameica.gui.formatter.DateFormatter;
import de.willuhn.jameica.gui.parts.Column;
import de.willuhn.jameica.gui.parts.TablePart;

public class AbrechnungslaufControl extends AbstractControl
{
  private de.willuhn.jameica.system.Settings settings;

  private Abrechnungslauf abrl;

  private TablePart abrechnungslaufList;

  public AbrechnungslaufControl(AbstractView view)
  {
    super(view);
    settings = new de.willuhn.jameica.system.Settings(this.getClass());
    settings.setStoreWhenRead(true);
  }

  public Abrechnungslauf getAbrechnungslaeufe()
  {
    if (abrl != null)
    {
      return abrl;
    }
    abrl = (Abrechnungslauf) getCurrentObject();
    return abrl;
  }

  public void handleStore()
  {
    //
  }

  public Part getAbrechungslaeufeList() throws RemoteException
  {
    DBService service = Einstellungen.getDBService();
    DBIterator abrechnungslaeufe = service.createList(Abrechnungslauf.class);
    abrechnungslaeufe.setOrder("ORDER BY datum DESC");

    if (abrechnungslaufList == null)
    {
      abrechnungslaufList = new TablePart(abrechnungslaeufe, null);
      abrechnungslaufList.addColumn("Nr", "nr");
      abrechnungslaufList.addColumn("Datum", "datum", new DateFormatter(
          Einstellungen.DATEFORMAT));
      abrechnungslaufList.addColumn("Modus", "modus",
          new AbrechnungsmodusFormatter(), false, Column.ALIGN_LEFT);
      abrechnungslaufList.addColumn("Stichtag", "stichtag", new DateFormatter(
          Einstellungen.DATEFORMAT));
      abrechnungslaufList.addColumn("Eingabedatum", "eingabedatum",
          new DateFormatter(Einstellungen.DATEFORMAT));
      abrechnungslaufList.addColumn("Zahlungsgrund", "zahlungsgrund");
      abrechnungslaufList.addColumn("Zusatzbetr�ge", "zusatzbetraege",
          new JaNeinFormatter());
      abrechnungslaufList.addColumn("Kursteilnehmer", "kursteilnehmer",
          new JaNeinFormatter());
      abrechnungslaufList.setContextMenu(new AbrechnungslaufMenu());
      abrechnungslaufList.setRememberColWidths(true);
      abrechnungslaufList.setRememberOrder(true);
      abrechnungslaufList.setSummary(true);
    }
    else
    {
      abrechnungslaufList.removeAll();
      while (abrechnungslaeufe.hasNext())
      {
        abrechnungslaufList.addItem((Abrechnungslauf) abrechnungslaeufe.next());
      }
    }
    return abrechnungslaufList;
  }

  private void refresh()
  {
    if (abrechnungslaufList == null)
    {
      return;
    }
    try
    {
      abrechnungslaufList.removeAll();
      DBIterator abrl = Einstellungen.getDBService().createList(
          Abrechnungslauf.class);
      while (abrl.hasNext())
      {
        Abrechnungslauf abl = (Abrechnungslauf) abrl.next();
        abrechnungslaufList.addItem(abl);
      }
    }
    catch (RemoteException e1)
    {
      e1.printStackTrace();
    }
  }
}
