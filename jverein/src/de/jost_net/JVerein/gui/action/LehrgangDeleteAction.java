/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/LehrgangDeleteAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2009/04/13 11:38:18 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: LehrgangDeleteAction.java,v $
 * Revision 1.1  2009/04/13 11:38:18  jost
 * Neu: Lehrg�nge
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import java.rmi.RemoteException;

import de.jost_net.JVerein.rmi.Lehrgang;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.dialogs.YesNoDialog;
import de.willuhn.jameica.gui.parts.TablePart;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

/**
 * Loeschen eines Lehrgangs
 */
public class LehrgangDeleteAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    if (context instanceof TablePart)
    {
      TablePart tp = (TablePart) context;
      context = tp.getSelection();
    }
    if (context == null || !(context instanceof Lehrgang))
    {
      throw new ApplicationException("Kein Lehrgang ausgew�hlt");
    }
    try
    {
      Lehrgang l = (Lehrgang) context;
      if (l.isNewObject())
      {
        return;
      }

      YesNoDialog d = new YesNoDialog(YesNoDialog.POSITION_CENTER);
      d.setTitle("Lehrgang l�schen");
      d.setText("Wollen Sie diesen Lehrgang wirklich l�schen?");

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
        Logger.error("Fehler beim L�schen eines Lehrgangs", e);
        return;
      }
      l.delete();
      GUI.getStatusBar().setSuccessText("Lehrgang gel�scht.");
    }
    catch (RemoteException e)
    {
      String fehler = "Fehler beim L�schen eines Lehrgangs";
      GUI.getStatusBar().setErrorText(fehler);
      Logger.error(fehler, e);
    }
  }
}
