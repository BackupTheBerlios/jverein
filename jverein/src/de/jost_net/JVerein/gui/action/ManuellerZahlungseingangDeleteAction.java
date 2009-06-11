/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/Attic/ManuellerZahlungseingangDeleteAction.java,v $
 * $Revision: 1.2 $
 * $Date: 2009/06/11 21:02:05 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: ManuellerZahlungseingangDeleteAction.java,v $
 * Revision 1.2  2009/06/11 21:02:05  jost
 * Vorbereitung I18N
 *
 * Revision 1.1  2007/03/13 19:56:20  jost
 * Neu: Manueller Zahlungseingang.
 *
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import java.rmi.RemoteException;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.rmi.ManuellerZahlungseingang;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.dialogs.YesNoDialog;
import de.willuhn.jameica.gui.parts.TablePart;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

/**
 * Loeschen eines ManuellenZahlungseingangs.
 */
public class ManuellerZahlungseingangDeleteAction implements Action
{
  private TablePart table;

  public ManuellerZahlungseingangDeleteAction(TablePart table)
  {
    this.table = table;
  }

  public void handleAction(Object context) throws ApplicationException
  {
    if (context == null || !(context instanceof ManuellerZahlungseingang))
    {
      throw new ApplicationException(JVereinPlugin.getI18n().tr(
          "Keinen manuellen Zahlungseingang ausgew�hlt"));
    }
    try
    {
      ManuellerZahlungseingang mz = (ManuellerZahlungseingang) context;
      if (mz.isNewObject())
      {
        return;
      }
      YesNoDialog d = new YesNoDialog(YesNoDialog.POSITION_CENTER);
      d.setTitle(JVereinPlugin.getI18n().tr("Posten l�schen"));
      d.setText(JVereinPlugin.getI18n().tr(
          "Wollen Sie diesen Posten wirklich l�schen?"));

      try
      {
        Boolean choice = (Boolean) d.open();
        if (!choice.booleanValue())
          return;
      }
      catch (Exception e)
      {
        Logger.error(JVereinPlugin.getI18n().tr(
            "Fehler beim L�schen des ManuellenZahlungseingangs"), e);
        return;
      }
      table.removeItem(mz);
      mz.delete();
      GUI.getStatusBar().setSuccessText(
          JVereinPlugin.getI18n().tr("Posten gel�scht."));
    }
    catch (RemoteException e)
    {
      String fehler = JVereinPlugin.getI18n().tr(
          "Fehler beim L�schen des manuellen Zahlungseingangs");
      GUI.getStatusBar().setErrorText(fehler);
      Logger.error(fehler, e);
    }
  }
}
