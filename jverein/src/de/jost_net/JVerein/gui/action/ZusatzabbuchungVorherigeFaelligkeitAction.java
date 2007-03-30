/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/Attic/ZusatzabbuchungVorherigeFaelligkeitAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2007/03/30 13:20:45 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: ZusatzabbuchungVorherigeFaelligkeitAction.java,v $
 * Revision 1.1  2007/03/30 13:20:45  jost
 * Neu
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import java.rmi.RemoteException;
import java.util.Date;

import de.jost_net.JVerein.rmi.Zusatzabbuchung;
import de.jost_net.JVerein.util.Datum;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.dialogs.YesNoDialog;
import de.willuhn.jameica.gui.parts.TablePart;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

/**
 * Vorheriges F�lligkeitsdatum einer Zusatzabbuchung setzen.
 */
public class ZusatzabbuchungVorherigeFaelligkeitAction implements Action
{
  private TablePart table;

  public ZusatzabbuchungVorherigeFaelligkeitAction(TablePart table)
  {
    this.table = table;
  }

  public void handleAction(Object context) throws ApplicationException
  {
    if (context == null || !(context instanceof Zusatzabbuchung))
    {
      throw new ApplicationException("Keine Zusatzabbuchung ausgew�hlt");
    }
    try
    {
      Zusatzabbuchung z = (Zusatzabbuchung) context;
      if (z.isNewObject())
      {
        return;
      }
      YesNoDialog d = new YesNoDialog(YesNoDialog.POSITION_CENTER);
      d.setTitle("Vorherige F�lligkeit setzen");
      d.setText("Wollen Sie das vorherige F�lligkeitsdatum setzen?");
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
                "Fehler beim Setzen des vorherigen F�lligkeitsdatums der Zusatzbuchung",
                e);
        return;
      }

      Date vorh = Datum.subtractInterval(z.getFaelligkeit(), z.getIntervall(),
          z.getStartdatum());
      if (vorh == null)
      {
        GUI.getStatusBar().setErrorText(
            "Datum kann nicht weiter zur�ckgesetzt werden");
      }
      else
      {
        int ind = table.removeItem(z);
        z.setFaelligkeit(vorh);
        z.store();
        table.addItem(z, ind);
        GUI.getStatusBar().setSuccessText("F�lligkeitsdatum gesetzt.");
      }
    }
    catch (RemoteException e)
    {
      String fehler = "Fehler beim Zur�cksetzen des Ausf�hrungsdatums der Zusatzabbuchung.";
      GUI.getStatusBar().setErrorText(fehler);
      Logger.error(fehler, e);
    }
  }
}
