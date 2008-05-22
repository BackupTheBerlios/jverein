/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/BuchungsartDeleteAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/05/22 06:46:00 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: BuchungsartDeleteAction.java,v $
 * Revision 1.1  2008/05/22 06:46:00  jost
 * BuchfÃ¼hrung
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import java.rmi.RemoteException;

import de.jost_net.JVerein.rmi.Buchungsart;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.dialogs.YesNoDialog;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

/**
 * Loeschen einer Buchungsart.
 */
public class BuchungsartDeleteAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    if (context == null || !(context instanceof Buchungsart))
    {
      throw new ApplicationException("Keine Buchungsart ausgewählt");
    }
    try
    {
      Buchungsart b = (Buchungsart) context;
      if (b.isNewObject())
      {
        return;
      }
      YesNoDialog d = new YesNoDialog(YesNoDialog.POSITION_CENTER);
      d.setTitle("Buchungsart löschen");
      d.setText("Wollen Sie diese Buchungsart wirklich löschen?");
      try
      {
        Boolean choice = (Boolean) d.open();
        if (!choice.booleanValue())
          return;
      }
      catch (Exception e)
      {
        Logger.error("Fehler beim Löschen der Buchungsart", e);
        return;
      }

      b.delete();
      GUI.getStatusBar().setSuccessText("Buchungsart gelöscht.");
    }
    catch (RemoteException e)
    {
      String fehler = "Fehler beim Löschen der Buchungsart.";
      GUI.getStatusBar().setErrorText(fehler);
      Logger.error(fehler, e);
    }
  }
}
