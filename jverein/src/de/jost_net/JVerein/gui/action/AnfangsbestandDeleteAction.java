/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/AnfangsbestandDeleteAction.java,v $
 * $Revision: 1.6 $
 * $Date: 2011/10/01 21:19:26 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import java.rmi.RemoteException;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.rmi.Anfangsbestand;
import de.jost_net.JVerein.rmi.Jahresabschluss;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.dialogs.YesNoDialog;
import de.willuhn.jameica.gui.parts.TablePart;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

/**
 * L�schen eines Anfangsbestandes
 */
public class AnfangsbestandDeleteAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    if (context instanceof TablePart)
    {
      TablePart tp = (TablePart) context;
      context = tp.getSelection();
    }
    if (context == null || !(context instanceof Anfangsbestand))
    {
      throw new ApplicationException(JVereinPlugin.getI18n().tr(
          "Keinen Anfangsbestand ausgew�hlt"));
    }
    try
    {
      Anfangsbestand a = (Anfangsbestand) context;
      if (a.isNewObject())
      {
        return;
      }
      try
      {
        Jahresabschluss ja = a.getJahresabschluss();
        if (ja != null)
        {
          throw new ApplicationException(JVereinPlugin.getI18n().tr(
              "Anfangsbestand ist bereits abgeschlossen."));
        }
      }
      catch (RemoteException e)
      {
        throw new ApplicationException(e.getMessage());
      }

      YesNoDialog d = new YesNoDialog(YesNoDialog.POSITION_CENTER);
      d.setTitle(JVereinPlugin.getI18n().tr("Anfangsbestand l�schen"));
      d.setText(JVereinPlugin.getI18n().tr(
          "Wollen Sie diesen Anfangsbestand wirklich l�schen?"));

      try
      {
        Boolean choice = (Boolean) d.open();
        if (!choice.booleanValue())
          return;
      }
      catch (Exception e)
      {
        Logger.error(JVereinPlugin.getI18n().tr(
            "Fehler beim L�schen des Anfangsbestandes: {0}",
            new String[] { e.getMessage() }));
        return;
      }
      a.delete();
      GUI.getStatusBar().setSuccessText(
          JVereinPlugin.getI18n().tr("Anfangsbestand gel�scht."));
    }
    catch (RemoteException e)
    {
      String fehler = JVereinPlugin.getI18n().tr(
          "Fehler beim L�schen des Anfangsbestandes");
      GUI.getStatusBar().setErrorText(fehler);
      Logger.error(fehler, e);
    }
  }
}
