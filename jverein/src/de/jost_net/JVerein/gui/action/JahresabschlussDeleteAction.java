/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/JahresabschlussDeleteAction.java,v $
 * $Revision: 1.2 $
 * $Date: 2009/06/11 21:02:05 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: JahresabschlussDeleteAction.java,v $
 * Revision 1.2  2009/06/11 21:02:05  jost
 * Vorbereitung I18N
 *
 * Revision 1.1  2008/06/28 16:55:43  jost
 * Neu: Jahresabschluss
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import java.rmi.RemoteException;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.rmi.Jahresabschluss;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.dialogs.YesNoDialog;
import de.willuhn.jameica.gui.parts.TablePart;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

/**
 * Loeschen eines Jahresabschlusses
 */
public class JahresabschlussDeleteAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    if (context instanceof TablePart)
    {
      TablePart tp = (TablePart) context;
      context = tp.getSelection();
    }
    if (context == null || !(context instanceof Jahresabschluss))
    {
      throw new ApplicationException(JVereinPlugin.getI18n().tr(
          "Keinen Jahresabschluss ausgew�hlt"));
    }
    try
    {
      Jahresabschluss a = (Jahresabschluss) context;
      if (a.isNewObject())
      {
        return;
      }
      YesNoDialog d = new YesNoDialog(YesNoDialog.POSITION_CENTER);
      d.setTitle(JVereinPlugin.getI18n().tr("Jahresabschluss l�schen"));
      d.setText(JVereinPlugin.getI18n().tr(
          "Wollen Sie diesen Jahresabschluss wirklich l�schen?"));

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
        Logger.error(JVereinPlugin.getI18n().tr(
            "Fehler beim L�schen des Jahresabschlusses"), e);
        return;
      }
      a.delete();
      GUI.getStatusBar().setSuccessText(
          JVereinPlugin.getI18n().tr("Jahresabschluss gel�scht."));
    }
    catch (RemoteException e)
    {
      String fehler = JVereinPlugin.getI18n().tr(
          "Fehler beim L�schen des Jahresabschlusses");
      GUI.getStatusBar().setErrorText(fehler);
      Logger.error(fehler, e);
    }
  }
}
