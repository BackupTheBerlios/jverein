/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/MitgliedDeleteAction.java,v $
 * $Revision: 1.4 $
 * $Date: 2011/10/01 21:40:00 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import java.rmi.RemoteException;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.rmi.Mitglied;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.dialogs.YesNoDialog;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

/**
 * Loeschen eines Mitgliedes.
 */
public class MitgliedDeleteAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    if (context == null || !(context instanceof Mitglied))
    {
      throw new ApplicationException(JVereinPlugin.getI18n().tr(
          "Kein Mitglied ausgew�hlt"));
    }
    try
    {
      Mitglied m = (Mitglied) context;
      if (m.isNewObject())
      {
        return;
      }
      YesNoDialog d = new YesNoDialog(YesNoDialog.POSITION_CENTER);
      d.setTitle(JVereinPlugin.getI18n().tr("Mitglied l�schen"));
      d.setText(JVereinPlugin.getI18n().tr(
          "Wollen Sie dieses Mitglied wirklich l�schen?"));

      try
      {
        Boolean choice = (Boolean) d.open();
        if (!choice.booleanValue())
          return;
      }
      catch (Exception e)
      {
        Logger
            .error(
                JVereinPlugin.getI18n()
                    .tr("Fehler beim L�schen des Mitgliedes"), e);
        return;
      }
      m.delete();
      GUI.getStatusBar().setSuccessText(
          JVereinPlugin.getI18n().tr("Mitglied gel�scht."));
    }
    catch (RemoteException e)
    {
      String fehler = JVereinPlugin.getI18n().tr(
          "Fehler beim L�schen des Mitgliedes");
      GUI.getStatusBar().setErrorText(fehler);
      Logger.error(fehler, e);
    }
  }
}
