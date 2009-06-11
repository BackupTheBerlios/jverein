/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/WiedervorlageDeleteAction.java,v $
 * $Revision: 1.2 $
 * $Date: 2009/06/11 21:02:05 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: WiedervorlageDeleteAction.java,v $
 * Revision 1.2  2009/06/11 21:02:05  jost
 * Vorbereitung I18N
 *
 * Revision 1.1  2007/05/07 19:24:08  jost
 * Neu: Wiedervorlage
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import java.rmi.RemoteException;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.rmi.Wiedervorlage;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.dialogs.YesNoDialog;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

/**
 * Loeschen einer Wiedervorlage.
 */
public class WiedervorlageDeleteAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    if (context == null || !(context instanceof Wiedervorlage))
    {
      throw new ApplicationException(JVereinPlugin.getI18n().tr(
          "Keine Wiedervorlage ausgewählt"));
    }
    try
    {
      Wiedervorlage w = (Wiedervorlage) context;
      if (w.isNewObject())
      {
        return;
      }
      YesNoDialog d = new YesNoDialog(YesNoDialog.POSITION_CENTER);
      d.setTitle(JVereinPlugin.getI18n().tr("Wiedervorlage löschen"));
      d.setText(JVereinPlugin.getI18n().tr(
          "Wollen Sie diese Wiedervorlage wirklich löschen?"));
      try
      {
        Boolean choice = (Boolean) d.open();
        if (!choice.booleanValue())
          return;
      }
      catch (Exception e)
      {
        Logger.error(JVereinPlugin.getI18n().tr(
            "Fehler beim Löschen der Wiedervorlage"), e);
        return;
      }

      w.delete();
      GUI.getStatusBar().setSuccessText(
          JVereinPlugin.getI18n().tr("Wiedervorlage gelöscht."));
    }
    catch (RemoteException e)
    {
      String fehler = JVereinPlugin.getI18n().tr(
          "Fehler beim Löschen der Wiedervorlage.");
      GUI.getStatusBar().setErrorText(fehler);
      Logger.error(fehler, e);
    }
  }
}
