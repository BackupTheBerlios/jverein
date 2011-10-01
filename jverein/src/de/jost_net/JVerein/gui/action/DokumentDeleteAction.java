/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/DokumentDeleteAction.java,v $
 * $Revision: 1.3 $
 * $Date: 2011/10/01 21:29:16 $
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
import de.jost_net.JVerein.rmi.AbstractDokument;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.dialogs.YesNoDialog;
import de.willuhn.jameica.gui.parts.TablePart;
import de.willuhn.jameica.messaging.QueryMessage;
import de.willuhn.jameica.system.Application;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

/**
 * L�schen von Dokumenten
 */
public class DokumentDeleteAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    if (context instanceof TablePart)
    {
      TablePart tp = (TablePart) context;
      context = tp.getSelection();
    }
    if (context == null || !(context instanceof AbstractDokument))
    {
      throw new ApplicationException(JVereinPlugin.getI18n().tr(
          "Kein Dokument ausgew�hlt"));
    }
    try
    {
      AbstractDokument ad = (AbstractDokument) context;
      if (ad.isNewObject())
      {
        return;
      }
      YesNoDialog d = new YesNoDialog(YesNoDialog.POSITION_CENTER);
      d.setTitle(JVereinPlugin.getI18n().tr("Dokument l�schen"));
      d.setText("Wollen Sie dieses Dokument wirklich l�schen?");

      try
      {
        Boolean choice = (Boolean) d.open();
        if (!choice.booleanValue())
        {
          return;
        }
        QueryMessage qm = new QueryMessage(ad.getUUID(), null);
        Application.getMessagingFactory()
            .getMessagingQueue("jameica.messaging.del").sendSyncMessage(qm);
        ad.delete();
      }
      catch (Exception e)
      {
        Logger.error(
            JVereinPlugin.getI18n().tr("Fehler beim L�schen des Dokuments"), e);
        return;
      }
      GUI.getStatusBar().setSuccessText(
          JVereinPlugin.getI18n().tr("Dokument gel�scht."));
    }
    catch (RemoteException e)
    {
      String fehler = JVereinPlugin.getI18n().tr(
          "Fehler beim L�schen des Dokuments");
      GUI.getStatusBar().setErrorText(fehler);
      Logger.error(fehler, e);
    }
  }
}
