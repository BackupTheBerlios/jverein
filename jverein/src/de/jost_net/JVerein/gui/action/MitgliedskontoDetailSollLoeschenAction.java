/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/MitgliedskontoDetailSollLoeschenAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2010/07/25 18:29:01 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: MitgliedskontoDetailSollLoeschenAction.java,v $
 * Revision 1.1  2010/07/25 18:29:01  jost
 * Neu: Mitgliedskonto
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import java.rmi.RemoteException;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.Messaging.MitgliedskontoMessage;
import de.jost_net.JVerein.gui.control.MitgliedskontoNode;
import de.jost_net.JVerein.rmi.Mitglied;
import de.jost_net.JVerein.rmi.Mitgliedskonto;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.dialogs.YesNoDialog;
import de.willuhn.jameica.system.Application;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

public class MitgliedskontoDetailSollLoeschenAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    YesNoDialog d = new YesNoDialog(YesNoDialog.POSITION_CENTER);
    d.setTitle("Sollbuchung l�schen");
    d.setText("Wollen Sie die Sollbuchung wirklich l�schen?");

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
      Logger.error("Fehler beim l�schen einer Sollbuchung", e);
      return;
    }
    MitgliedskontoNode mkn = null;
    Mitgliedskonto mk = null;

    if (context != null && (context instanceof MitgliedskontoNode))
    {
      mkn = (MitgliedskontoNode) context;
      try
      {
        mk = (Mitgliedskonto) Einstellungen.getDBService().createObject(
            Mitgliedskonto.class, mkn.getID());
        Mitglied mitglied = mk.getMitglied();
        mk.delete();
        GUI.getStatusBar().setSuccessText("Sollbuchung gel�scht.");
        Application.getMessagingFactory().sendMessage(
            new MitgliedskontoMessage(mitglied));
      }
      catch (RemoteException e)
      {
        throw new ApplicationException(
            "Fehler bei der Erzeugung einer Mitgliedskonto-Buchung");
      }
    }
  }
}
