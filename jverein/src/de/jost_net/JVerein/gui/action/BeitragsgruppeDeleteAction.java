/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/BeitragsgruppeDeleteAction.java,v $
 * $Revision: 1.5 $
 * $Date: 2009/06/21 08:51:42 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: BeitragsgruppeDeleteAction.java,v $
 * Revision 1.5  2009/06/21 08:51:42  jost
 * Bessere Fehlermeldung bei der L�schung von Beitragsgruppen, denen noch Mitglieder zugeordnet sind. Siehe #15892
 *
 * Revision 1.4  2009/06/11 21:02:05  jost
 * Vorbereitung I18N
 *
 * Revision 1.3  2008/01/26 16:21:03  jost
 * Debug-Message entfernt.
 *
 * Revision 1.2  2007/08/30 19:47:17  jost
 * Jetzt auch über einen Button auslösbar.
 *
 * Revision 1.1  2007/08/23 19:24:05  jost
 * Bug #11819 - Beitragsgruppen können jetzt gelöscht werden
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import java.rmi.RemoteException;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.rmi.Beitragsgruppe;
import de.jost_net.JVerein.rmi.Mitglied;
import de.willuhn.datasource.rmi.DBIterator;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.dialogs.YesNoDialog;
import de.willuhn.jameica.gui.parts.TablePart;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

/**
 * Loeschen einer Beitragsgruppe
 */
public class BeitragsgruppeDeleteAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    if (context instanceof TablePart)
    {
      TablePart tp = (TablePart) context;
      context = tp.getSelection();
    }
    if (context == null || !(context instanceof Beitragsgruppe))
    {
      throw new ApplicationException(JVereinPlugin.getI18n().tr(
          "Keine Beitragsgruppe ausgew�hlt"));
    }
    try
    {
      Beitragsgruppe bg = (Beitragsgruppe) context;
      if (bg.isNewObject())
      {
        return;
      }
      DBIterator mitgl = Einstellungen.getDBService()
          .createList(Mitglied.class);
      mitgl.addFilter("beitragsgruppe = ?", new Object[] { bg.getID() });
      if (mitgl.size() > 0)
      {
        throw new ApplicationException(
            JVereinPlugin
                .getI18n()
                .tr(
                    "Beitragsgruppe \"{0}\" kann nicht gel�scht werden. {1} Mitglied(er) sind dieser Beitragsgruppe zugeordnet.",
                    new String[] { bg.getBezeichnung(), mitgl.size() + "" }));
      }
      YesNoDialog d = new YesNoDialog(YesNoDialog.POSITION_CENTER);
      d.setTitle(JVereinPlugin.getI18n().tr("Beitragsgruppe l�schen"));
      d.setText(JVereinPlugin.getI18n().tr(
          "Wollen Sie diese Beitragsgruppe wirklich l�schen?"));

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
            "Fehler beim L�schen der Beitragsgruppe: [0}",
            new String[] { e.getMessage() }));
        return;
      }
      bg.delete();
      GUI.getStatusBar().setSuccessText(
          JVereinPlugin.getI18n().tr("Beitragsgruppe gel�scht."));
    }
    catch (RemoteException e)
    {
      String fehler = JVereinPlugin.getI18n().tr(
          "Fehler beim L�schen der Beitragsgruppe");
      GUI.getStatusBar().setErrorText(fehler);
      GUI.getView().setErrorText(fehler);
      Logger.error(fehler, e);
    }
  }
}
