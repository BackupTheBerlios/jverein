/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/ZusatzbetraegeNaechsteFaelligkeitAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/12/22 21:07:33 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: ZusatzbetraegeNaechsteFaelligkeitAction.java,v $
 * Revision 1.1  2008/12/22 21:07:33  jost
 * Zusatzabbuchung->Zusatzbetrag
 *
 * Revision 1.1  2007/03/30 13:19:57  jost
 * Neu
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import java.rmi.RemoteException;
import java.util.Date;

import de.jost_net.JVerein.rmi.Zusatzbetrag;
import de.jost_net.JVerein.util.Datum;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.dialogs.YesNoDialog;
import de.willuhn.jameica.gui.parts.TablePart;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

/**
 * N�chstes F�lligkeitsdatum eines Zusatzbetrages setzen.
 */
public class ZusatzbetraegeNaechsteFaelligkeitAction implements Action
{
  private TablePart table;

  public ZusatzbetraegeNaechsteFaelligkeitAction(TablePart table)
  {
    this.table = table;
  }

  public void handleAction(Object context) throws ApplicationException
  {
    if (context == null || !(context instanceof Zusatzbetrag))
    {
      throw new ApplicationException("Kein Zusatzbetrag ausgew�hlt");
    }
    try
    {
      Zusatzbetrag z = (Zusatzbetrag) context;
      if (z.isNewObject())
      {
        return;
      }
      YesNoDialog d = new YesNoDialog(YesNoDialog.POSITION_CENTER);
      d.setTitle("N�chste F�lligkeit setzen");
      d.setText("Wollen Sie das n�chste F�lligkeitsdatum setzen?");
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
                "Fehler beim Setzen des n�chsten F�lligkeitsdatums des Zusatzbetrages",
                e);
        return;
      }

      Date vorh = Datum.addInterval(z.getFaelligkeit(), z.getIntervall(), z
          .getEndedatum());
      if (vorh == null)
      {
        GUI.getStatusBar().setErrorText(
            "Datum kann nicht weiter vorgesetzt werden");
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
      String fehler = "Fehler beim Zur�cksetzen des Ausf�hrungsdatums des Zusatzbetrages.";
      GUI.getStatusBar().setErrorText(fehler);
      Logger.error(fehler, e);
    }
  }
}