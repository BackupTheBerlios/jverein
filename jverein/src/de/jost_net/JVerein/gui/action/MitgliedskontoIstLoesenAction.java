/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/MitgliedskontoIstLoesenAction.java,v $
 * $Revision: 1.2 $
 * $Date: 2011/07/22 12:27:45 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: MitgliedskontoIstLoesenAction.java,v $
 * Revision 1.2  2011/07/22 12:27:45  jost
 * Sofortige Aktualisierung der Anzeige nach Ist-L�schung im Mitgliedskonto. Patch von Julian.
 *
 * Revision 1.1  2011-05-05 19:50:20  jost
 * Neu: Istbuchungen k�nnen vom Mitgliedskonto gel�st werden.
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import java.rmi.RemoteException;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.Messaging.MitgliedskontoMessage;
import de.jost_net.JVerein.gui.control.MitgliedskontoNode;
import de.jost_net.JVerein.rmi.Buchung;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.dialogs.YesNoDialog;
import de.willuhn.jameica.system.Application;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

public class MitgliedskontoIstLoesenAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    YesNoDialog d = new YesNoDialog(YesNoDialog.POSITION_CENTER);
    d.setTitle("Istbuchung vom Mitgliedskonto l�sen");
    d.setText("Wollen Sie die Istbuchung wirklich vom Mitgliedskonto l�sen?");

    try
    {
      Boolean choice = (Boolean) d.open();
      if (!choice.booleanValue())
      {
        return;
      }
    }
    catch (Exception e)
    {
      Logger.error("Fehler beim l�sen der Istbuchung vom Mitgliedskonto", e);
      return;
    }
    MitgliedskontoNode mkn = null;
    Buchung bu = null;

    if (context != null && (context instanceof MitgliedskontoNode))
    {
      mkn = (MitgliedskontoNode) context;
      try
      {
        bu = (Buchung) Einstellungen.getDBService().createObject(Buchung.class,
            mkn.getID());
        bu.setMitgliedskonto(null);
        bu.store();
        GUI.getStatusBar().setSuccessText(
            "Istbuchung vom Mitgliedskonto gel�st.");
        Application.getMessagingFactory().sendMessage(
                new MitgliedskontoMessage(mkn.getMitglied()));
      }
      catch (RemoteException e)
      {
        throw new ApplicationException(
            "Fehler beim l�sen der Istbuchung vom Mitgliedskonto");
      }
    }
  }
}
