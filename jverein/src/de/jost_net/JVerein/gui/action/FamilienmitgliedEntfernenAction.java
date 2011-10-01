/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/FamilienmitgliedEntfernenAction.java,v $
 * $Revision: 1.2 $
 * $Date: 2011/10/01 21:31:38 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.gui.control.FamilienbeitragNode;
import de.jost_net.JVerein.gui.dialogs.FamilienmitgliedEntfernenDialog;
import de.willuhn.jameica.gui.Action;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

public class FamilienmitgliedEntfernenAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    if (context == null || !(context instanceof FamilienbeitragNode))
    {
      throw new ApplicationException("kein Familienmitglied ausgew�hlt");
    }
    FamilienbeitragNode fbn = (FamilienbeitragNode) context;
    FamilienmitgliedEntfernenDialog fed = new FamilienmitgliedEntfernenDialog(
        fbn);
    try
    {
      fed.open();
      // if (!choice.booleanValue())
      // {
      // return;
      // }
    }

    catch (Exception e)
    {
      Logger.error("Fehler", e);
      return;
    }
    // MitgliedskontoNode mkn = null;
    // Buchung bu = null;
    //
    // if (context != null && (context instanceof MitgliedskontoNode))
    // {
    // mkn = (MitgliedskontoNode) context;
    // try
    // {
    // bu = (Buchung) Einstellungen.getDBService().createObject(Buchung.class,
    // mkn.getID());
    // bu.setMitgliedskonto(null);
    // bu.store();
    // GUI.getStatusBar().setSuccessText(
    // "Istbuchung vom Mitgliedskonto gel�st.");
    // Application.getMessagingFactory().sendMessage(
    // new MitgliedskontoMessage(mkn.getMitglied()));
    // }
    // catch (RemoteException e)
    // {
    // throw new ApplicationException(
    // "Fehler beim l�sen der Istbuchung vom Mitgliedskonto");
    // }
    // }
  }
}
