/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/Attic/ManuellerZahlungseingangDatumSetzenAction.java,v $
 * $Revision: 1.3 $
 * $Date: 2009/11/26 19:50:19 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: ManuellerZahlungseingangDatumSetzenAction.java,v $
 * Revision 1.3  2009/11/26 19:50:19  jost
 * Mehrfachauswahl erm�glicht.
 *
 * Revision 1.2  2009/06/11 21:02:05  jost
 * Vorbereitung I18N
 *
 * Revision 1.1  2007/03/13 19:56:02  jost
 * Neu: Manueller Zahlungseingang.
 *
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import java.rmi.RemoteException;
import java.util.Date;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.rmi.ManuellerZahlungseingang;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.dialogs.CalendarDialog;
import de.willuhn.jameica.gui.parts.TablePart;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

/**
 * Datum des Zahlungseingangs setzen.
 */
public class ManuellerZahlungseingangDatumSetzenAction implements Action
{
  private Date date = null;

  private TablePart table;

  public ManuellerZahlungseingangDatumSetzenAction(TablePart table)
  {
    super();
    this.table = table;
  }

  public void handleAction(Object context) throws ApplicationException
  {
    if (context == null)
    {
      throw new ApplicationException(JVereinPlugin.getI18n().tr(
          "Keinen ManuellenZahlungseingang ausgew�hlt"));
    }

    Object[] objects = null;

    if (context instanceof Object[])
    {
      objects = (Object[]) context;
    }
    else
    {
      objects = new Object[] { context };
    }

    try
    {
      ManuellerZahlungseingang mz = (ManuellerZahlungseingang) objects[0];
      if (mz.isNewObject())
      {
        return;
      }
      CalendarDialog cd = new CalendarDialog(CalendarDialog.POSITION_MOUSE);
      cd.setTitle(JVereinPlugin.getI18n().tr("Datum des Zahlungseinganges"));
      cd.addCloseListener(new Listener()
      {
        public void handleEvent(Event event)
        {
          if (event == null || event.data == null
              || !(event.data instanceof Date))
            return;

          date = (Date) event.data;
        }
      });
      try
      {
        cd.open();
        for (Object cont : objects)
        {
          mz = (ManuellerZahlungseingang) cont;
          int ind = table.removeItem(mz);
          mz.setEingangsdatum(date);
          mz.store();
          table.addItem(mz, ind);
        }
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }

      GUI.getStatusBar().setSuccessText(
          JVereinPlugin.getI18n().tr("Datum eingetragen."));
    }
    catch (RemoteException e)
    {
      String fehler = JVereinPlugin.getI18n().tr(
          "Fehler beim setzen des Zahlungseingangsdatums");
      GUI.getStatusBar().setErrorText(fehler);
      Logger.error(fehler, e);
    }
  }
}
