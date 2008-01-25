/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/Attic/EigenschaftenDeleteAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/01/25 15:59:22 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: EigenschaftenDeleteAction.java,v $
 * Revision 1.1  2008/01/25 15:59:22  jost
 * Neu: Eigenschaften des Mitgliedes
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import java.rmi.RemoteException;

import de.jost_net.JVerein.gui.control.EigenschaftenControl;
import de.jost_net.JVerein.rmi.Eigenschaften;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.dialogs.YesNoDialog;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

/**
 * Loeschen einer Eigenschaft.
 */
public class EigenschaftenDeleteAction implements Action
{
  private EigenschaftenControl control;

  public EigenschaftenDeleteAction(EigenschaftenControl control)
  {
    this.control = control;
  }

  public void handleAction(Object context) throws ApplicationException
  {
    if (context == null || !(context instanceof Eigenschaften))
    {
      throw new ApplicationException("Keine Eigenschaft ausgew�hlt");
    }
    try
    {
      Eigenschaften ei = (Eigenschaften) context;
      if (ei.isNewObject())
      {
        return;
      }
      YesNoDialog d = new YesNoDialog(YesNoDialog.POSITION_CENTER);
      d.setTitle("Eigenschaft l�schen");
      d.setText("Wollen Sie diese Eigenschaft wirklich l�schen?");

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
        Logger.error("Fehler beim L�schen der Eigenschaft", e);
        return;
      }
      ei.delete();
      control.refreshTable();
      GUI.getStatusBar().setSuccessText("Eigenschaft gel�scht.");
    }
    catch (RemoteException e)
    {
      String fehler = "Fehler beim L�schen der Eigenschaft";
      GUI.getStatusBar().setErrorText(fehler);
      Logger.error(fehler, e);
    }
  }
}
