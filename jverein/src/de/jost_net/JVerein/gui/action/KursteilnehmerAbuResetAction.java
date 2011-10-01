/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/KursteilnehmerAbuResetAction.java,v $
 * $Revision: 1.3 $
 * $Date: 2011/10/01 21:39:59 $
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
import de.jost_net.JVerein.rmi.Kursteilnehmer;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.dialogs.YesNoDialog;
import de.willuhn.jameica.gui.parts.TablePart;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

/**
 * Ausf�hrungsdatum der Abbuchung eines Kursteilnehmers entfernen.
 */
public class KursteilnehmerAbuResetAction implements Action
{
  private TablePart table;

  public KursteilnehmerAbuResetAction(TablePart table)
  {
    this.table = table;
  }

  public void handleAction(Object context) throws ApplicationException
  {
    if (context == null || !(context instanceof Kursteilnehmer))
    {
      throw new ApplicationException(JVereinPlugin.getI18n().tr(
          "Kein Kursteilnehmer ausgew�hlt"));
    }
    try
    {
      Kursteilnehmer kt = (Kursteilnehmer) context;
      if (kt.isNewObject())
      {
        return;
      }
      YesNoDialog d = new YesNoDialog(YesNoDialog.POSITION_CENTER);
      d.setTitle(JVereinPlugin.getI18n().tr("Abbuchungsdatum zur�cksetzen"));
      d.setText(JVereinPlugin
          .getI18n()
          .tr("Wollen Sie das Ausf�hrungsdatum der Abbuchung wirklich zur�cksetzen?"));
      try
      {
        Boolean choice = (Boolean) d.open();
        if (!choice.booleanValue())
          return;
      }
      catch (Exception e)
      {
        Logger.error(
            JVereinPlugin.getI18n().tr(
                "Fehler beim Reset des Abbuchungsdatums des Kursteilnehmers"),
            e);
        return;
      }
      int ind = table.removeItem(kt);
      kt.resetAbbudatum();
      kt.store();
      table.addItem(kt, ind);

      GUI.getStatusBar().setSuccessText(
          JVereinPlugin.getI18n().tr("Abbuchungsdatum zur�ckgesetzt."));
    }
    catch (RemoteException e)
    {
      String fehler = JVereinPlugin.getI18n().tr(
          "Fehler beim Zur�cksetzen des Abbuchungsdatum des Kursteilnehmers.");
      GUI.getStatusBar().setErrorText(fehler);
      Logger.error(fehler, e);
    }
  }
}
