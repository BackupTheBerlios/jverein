/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/AdresseDeleteAction.java,v $
 * $Revision: 1.2 $
 * $Date: 2011/03/13 18:26:56 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: AdresseDeleteAction.java,v $
 * Revision 1.2  2011/03/13 18:26:56  jost
 * redakt. Kommentare
 *
 * Revision 1.1  2011-01-27 22:15:33  jost
 * Neu: Speicherung von weiteren Adressen in der Mitgliedertabelle
 *
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
 * L�schen einer Adresse.
 */
public class AdresseDeleteAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    if (context == null || !(context instanceof Mitglied))
    {
      throw new ApplicationException(JVereinPlugin.getI18n().tr(
          "Keine Adresse ausgew�hlt"));
    }
    try
    {
      Mitglied m = (Mitglied) context;
      if (m.isNewObject())
      {
        return;
      }
      YesNoDialog d = new YesNoDialog(YesNoDialog.POSITION_CENTER);
      d.setTitle(JVereinPlugin.getI18n().tr("Adresse l�schen"));
      d.setText(JVereinPlugin.getI18n().tr(
          "Wollen Sie diese Adresse wirklich l�schen?"));

      try
      {
        Boolean choice = (Boolean) d.open();
        if (!choice.booleanValue())
          return;
      }
      catch (Exception e)
      {
        Logger.error(
            JVereinPlugin.getI18n().tr("Fehler beim L�schen der Adresse"), e);
        return;
      }
      m.delete();
      GUI.getStatusBar().setSuccessText(
          JVereinPlugin.getI18n().tr("Adresse gel�scht."));
    }
    catch (RemoteException e)
    {
      String fehler = JVereinPlugin.getI18n().tr(
          "Fehler beim L�schen der Adresse");
      GUI.getStatusBar().setErrorText(fehler);
      Logger.error(fehler, e);
    }
  }
}
