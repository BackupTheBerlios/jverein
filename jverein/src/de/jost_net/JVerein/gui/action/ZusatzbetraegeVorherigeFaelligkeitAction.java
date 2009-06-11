/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/ZusatzbetraegeVorherigeFaelligkeitAction.java,v $
 * $Revision: 1.2 $
 * $Date: 2009/06/11 21:02:05 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: ZusatzbetraegeVorherigeFaelligkeitAction.java,v $
 * Revision 1.2  2009/06/11 21:02:05  jost
 * Vorbereitung I18N
 *
 * Revision 1.1  2008/12/22 21:08:01  jost
 * Zusatzabbuchung->Zusatzbetrag
 *
 * Revision 1.1  2007/03/30 13:20:45  jost
 * Neu
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import java.rmi.RemoteException;
import java.util.Date;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.rmi.Zusatzbetrag;
import de.jost_net.JVerein.util.Datum;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.dialogs.YesNoDialog;
import de.willuhn.jameica.gui.parts.TablePart;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

/**
 * Vorheriges F�lligkeitsdatum eines Zusatzbetrages setzen.
 */
public class ZusatzbetraegeVorherigeFaelligkeitAction implements Action
{
  private TablePart table;

  public ZusatzbetraegeVorherigeFaelligkeitAction(TablePart table)
  {
    this.table = table;
  }

  public void handleAction(Object context) throws ApplicationException
  {
    if (context == null || !(context instanceof Zusatzbetrag))
    {
      throw new ApplicationException(JVereinPlugin.getI18n().tr(
          "Kein Zusatzbetrag ausgew�hlt"));
    }
    try
    {
      Zusatzbetrag z = (Zusatzbetrag) context;
      if (z.isNewObject())
      {
        return;
      }
      YesNoDialog d = new YesNoDialog(YesNoDialog.POSITION_CENTER);
      d.setTitle(JVereinPlugin.getI18n().tr("Vorherige F�lligkeit setzen"));
      d.setText(JVereinPlugin.getI18n().tr(
          "Wollen Sie das vorherige F�lligkeitsdatum setzen?"));
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
                JVereinPlugin
                    .getI18n()
                    .tr(
                        "Fehler beim Setzen des vorherigen F�lligkeitsdatums des Zusatzbetrages"),
                e);
        return;
      }

      Date vorh = Datum.subtractInterval(z.getFaelligkeit(), z.getIntervall(),
          z.getStartdatum());
      if (vorh == null)
      {
        GUI.getStatusBar().setErrorText(
            JVereinPlugin.getI18n().tr(
                "Datum kann nicht weiter zur�ckgesetzt werden"));
      }
      else
      {
        int ind = table.removeItem(z);
        z.setFaelligkeit(vorh);
        z.store();
        table.addItem(z, ind);
        GUI.getStatusBar().setSuccessText(
            JVereinPlugin.getI18n().tr("F�lligkeitsdatum gesetzt."));
      }
    }
    catch (RemoteException e)
    {
      String fehler = JVereinPlugin.getI18n().tr(
          "Fehler beim Zur�cksetzen des Ausf�hrungsdatums des Zusatzbetrages.");
      GUI.getStatusBar().setErrorText(fehler);
      Logger.error(fehler, e);
    }
  }
}
