/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/ZusatzbetraegeResetAction.java,v $
 * $Revision: 1.2 $
 * $Date: 2009/06/11 21:02:05 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: ZusatzbetraegeResetAction.java,v $
 * Revision 1.2  2009/06/11 21:02:05  jost
 * Vorbereitung I18N
 *
 * Revision 1.1  2008/12/22 21:07:48  jost
 * Zusatzabbuchung->Zusatzbetrag
 *
 * Revision 1.3  2007/03/30 13:20:16  jost
 * Tabelle updaten.
 *
 * Revision 1.2  2007/02/23 20:26:00  jost
 * Mail- und Webadresse im Header korrigiert.
 *
 * Revision 1.1  2006/09/20 15:38:12  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import java.rmi.RemoteException;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.rmi.Zusatzbetrag;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.dialogs.YesNoDialog;
import de.willuhn.jameica.gui.parts.TablePart;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

/**
 * Ausf�hrungsdatum eines Zusatzbetrages entfernen.
 */
public class ZusatzbetraegeResetAction implements Action
{
  private TablePart table;

  public ZusatzbetraegeResetAction(TablePart table)
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
      d.setTitle(JVereinPlugin.getI18n().tr("Ausf�hrungsdatum zur�cksetzen"));
      d
          .setText(JVereinPlugin
              .getI18n()
              .tr(
                  "Wollen Sie das Ausf�hrungsdatum dieses Zusatzbetrages wirklich zur�cksetzen?"));
      try
      {
        Boolean choice = (Boolean) d.open();
        if (!choice.booleanValue())
          return;
      }
      catch (Exception e)
      {
        Logger.error(JVereinPlugin.getI18n().tr(
            "Fehler beim Reset des Zusatzbetrages"), e);
        return;
      }
      int ind = table.removeItem(z);
      z.setAusfuehrung(null);
      z.store();
      table.addItem(z, ind);
      GUI.getStatusBar().setSuccessText(
          JVereinPlugin.getI18n().tr("Ausf�hrungsdatum zur�ckgesetzt."));
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
