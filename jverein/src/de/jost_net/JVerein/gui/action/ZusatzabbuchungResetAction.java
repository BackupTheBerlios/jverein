/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/Attic/ZusatzabbuchungResetAction.java,v $
 * $Revision: 1.2 $
 * $Date: 2007/02/23 20:26:00 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: ZusatzabbuchungResetAction.java,v $
 * Revision 1.2  2007/02/23 20:26:00  jost
 * Mail- und Webadresse im Header korrigiert.
 *
 * Revision 1.1  2006/09/20 15:38:12  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import java.rmi.RemoteException;

import de.jost_net.JVerein.rmi.Zusatzabbuchung;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.dialogs.YesNoDialog;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

/**
 * Ausführungsdatum einer Zusatzabbuchung entfernen.
 */
public class ZusatzabbuchungResetAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    if (context == null || !(context instanceof Zusatzabbuchung))
    {
      throw new ApplicationException("Keine Zusatzabbuchung ausgewählt");
    }
    try
    {
      Zusatzabbuchung z = (Zusatzabbuchung) context;
      if (z.isNewObject())
      {
        return;
      }
      YesNoDialog d = new YesNoDialog(YesNoDialog.POSITION_CENTER);
      d.setTitle("Ausführungsdatum zurücksetzen");
      d
          .setText("Wollen Sie das Ausführungsdatum dieser Zusatzabbuchung wirklich zurücksetzen?");
      try
      {
        Boolean choice = (Boolean) d.open();
        if (!choice.booleanValue())
          return;
      }
      catch (Exception e)
      {
        Logger.error("Fehler beim Reset der Zusatzbuchung", e);
        return;
      }

      z.setAusfuehrung(null);
      z.store();
      GUI.getStatusBar().setSuccessText("Ausführungsdatum zurückgesetzt.");
    }
    catch (RemoteException e)
    {
      String fehler = "Fehler beim Zurücksetzen des Ausführungsdatums der Zusatzabbuchung.";
      GUI.getStatusBar().setErrorText(fehler);
      Logger.error(fehler, e);
    }
  }
}
