/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/BuchungsklasseDeleteAction.java,v $
 * $Revision: 1.3 $
 * $Date: 2011/10/01 21:26:57 $
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
import de.jost_net.JVerein.rmi.Buchungsklasse;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.dialogs.YesNoDialog;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

/**
 * L�schen einer Buchungsklasse.
 */
public class BuchungsklasseDeleteAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    if (context == null || !(context instanceof Buchungsklasse))
    {
      throw new ApplicationException(JVereinPlugin.getI18n().tr(
          "Keine Buchungsklasse ausgew�hlt"));
    }
    try
    {
      Buchungsklasse b = (Buchungsklasse) context;
      if (b.isNewObject())
      {
        return;
      }
      YesNoDialog d = new YesNoDialog(YesNoDialog.POSITION_CENTER);
      d.setTitle(JVereinPlugin.getI18n().tr("Buchungsklasse l�schen"));
      d.setText(JVereinPlugin.getI18n().tr(
          "Wollen Sie diese Buchungsklasse wirklich l�schen?"));
      try
      {
        Boolean choice = (Boolean) d.open();
        if (!choice.booleanValue())
          return;
      }
      catch (Exception e)
      {
        Logger.error(
            JVereinPlugin.getI18n()
                .tr("Fehler beim L�schen der Buchungsklasse"), e);
        return;
      }

      b.delete();
      GUI.getStatusBar().setSuccessText(
          JVereinPlugin.getI18n().tr("Buchungsklasse gel�scht."));
    }
    catch (RemoteException e)
    {
      String fehler = JVereinPlugin.getI18n().tr(
          "Fehler beim L�schen der Buchungsklasse.");
      GUI.getStatusBar().setErrorText(fehler);
      Logger.error(fehler, e);
    }
  }
}
