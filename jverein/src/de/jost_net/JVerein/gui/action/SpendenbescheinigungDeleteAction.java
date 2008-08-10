/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/SpendenbescheinigungDeleteAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/07/19 19:23:39 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: SpendenbescheinigungDeleteAction.java,v $
 * Revision 1.1  2008/07/19 19:23:39  jost
 * Neu: Spendenbescheinigung
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import java.rmi.RemoteException;

import de.jost_net.JVerein.rmi.Formular;
import de.jost_net.JVerein.rmi.Spendenbescheinigung;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.dialogs.YesNoDialog;
import de.willuhn.jameica.gui.parts.TablePart;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

/**
 * Loeschen einer Spendenbescheinigung
 */
public class SpendenbescheinigungDeleteAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    if (context instanceof TablePart)
    {
      TablePart tp = (TablePart) context;
      context = tp.getSelection();
    }
    if (context == null || !(context instanceof Spendenbescheinigung))
    {
      throw new ApplicationException("Keine Spendenbescheinigung ausgew�hlt");
    }
    try
    {
      Spendenbescheinigung spb = (Spendenbescheinigung) context;
      if (spb.isNewObject())
      {
        return;
      }

      YesNoDialog d = new YesNoDialog(YesNoDialog.POSITION_CENTER);
      d.setTitle("Spendenbescheinigung l�schen");
      d.setText("Wollen Sie die Spendenbescheinigung wirklich l�schen?");

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
        Logger.error("Fehler beim L�schen der Spendenbescheinigung", e);
        return;
      }
      spb.delete();
      GUI.getStatusBar().setSuccessText("Spendenbescheinigung gel�scht.");
    }
    catch (RemoteException e)
    {
      String fehler = "Fehler beim L�schen der Spendenbescheinigung";
      GUI.getStatusBar().setErrorText(fehler);
      Logger.error(fehler, e);
    }
  }
}