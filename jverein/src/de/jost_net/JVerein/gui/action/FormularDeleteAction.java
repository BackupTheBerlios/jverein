/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/FormularDeleteAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/07/18 20:06:34 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: FormularDeleteAction.java,v $
 * Revision 1.1  2008/07/18 20:06:34  jost
 * Neu: Formulare
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import java.rmi.RemoteException;

import de.jost_net.JVerein.rmi.Formular;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.dialogs.YesNoDialog;
import de.willuhn.jameica.gui.parts.TablePart;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

/**
 * Loeschen eines Formulares
 */
public class FormularDeleteAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    if (context instanceof TablePart)
    {
      TablePart tp = (TablePart) context;
      context = tp.getSelection();
    }
    if (context == null || !(context instanceof Formular))
    {
      throw new ApplicationException("Kein Formular ausgew�hlt");
    }
    try
    {
      Formular f = (Formular) context;
      if (f.isNewObject())
      {
        return;
      }

      YesNoDialog d = new YesNoDialog(YesNoDialog.POSITION_CENTER);
      d.setTitle("Formular l�schen");
      d.setText("Wollen Sie dieses Formular wirklich l�schen?");

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
        Logger.error("Fehler beim L�schen des Formulares", e);
        return;
      }
      f.delete();
      GUI.getStatusBar().setSuccessText("Formular gel�scht.");
    }
    catch (RemoteException e)
    {
      String fehler = "Fehler beim L�schen des Formulares";
      GUI.getStatusBar().setErrorText(fehler);
      Logger.error(fehler, e);
    }
  }
}
