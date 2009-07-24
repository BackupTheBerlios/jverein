/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/AnfangsbestandDeleteAction.java,v $
 * $Revision: 1.4 $
 * $Date: 2009/07/24 20:15:54 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: AnfangsbestandDeleteAction.java,v $
 * Revision 1.4  2009/07/24 20:15:54  jost
 * *** empty log message ***
 *
 * Revision 1.3  2009/06/11 21:01:06  jost
 * Vorbereitung I18N
 *
 * Revision 1.2  2008/06/28 16:54:26  jost
 * Löschung nur, wenn kein Jahresabschluss vorliegt.
 *
 * Revision 1.1  2008/05/22 06:44:49  jost
 * Buchführung
 *
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
 * Loeschen eines Anfangsbestandes
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
