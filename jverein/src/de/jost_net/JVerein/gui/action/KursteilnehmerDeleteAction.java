/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/KursteilnehmerDeleteAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2007/02/25 19:11:24 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: KursteilnehmerDeleteAction.java,v $
 * Revision 1.1  2007/02/25 19:11:24  jost
 * Neu: Kursteilnehmer
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import java.rmi.RemoteException;

import de.jost_net.JVerein.rmi.Kursteilnehmer;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.dialogs.YesNoDialog;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

/**
 * Loeschen einer Einmal-Abbuchung.
 */
public class KursteilnehmerDeleteAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    if (context == null || !(context instanceof Kursteilnehmer))
    {
      throw new ApplicationException("Keinen Kursteilnehmer ausgewählt");
    }
    try
    {
      Kursteilnehmer kt = (Kursteilnehmer) context;
      if (kt.isNewObject())
      {
        return;
      }
      YesNoDialog d = new YesNoDialog(YesNoDialog.POSITION_CENTER);
      d.setTitle("Kursteilnehmer löschen");
      d.setText("Wollen Sie diesen Kursteilnehmer wirklich löschen?");

      try
      {
        Boolean choice = (Boolean) d.open();
        if (!choice.booleanValue())
          return;
      }
      catch (Exception e)
      {
        Logger.error("Fehler beim Löschen des Kursteilnehmers", e);
        return;
      }
      kt.delete();
      GUI.getStatusBar().setSuccessText("Kursteilnehmer gelöscht.");
    }
    catch (RemoteException e)
    {
      String fehler = "Fehler beim Löschen des Kursteilnehmers";
      GUI.getStatusBar().setErrorText(fehler);
      Logger.error(fehler, e);
    }
  }
}
