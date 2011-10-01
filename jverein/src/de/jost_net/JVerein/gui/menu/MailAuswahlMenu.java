/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/menu/MailAuswahlMenu.java,v $
 * $Revision: 1.3 $
 * $Date: 2011/10/01 21:44:28 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/
package de.jost_net.JVerein.gui.menu;

import java.rmi.RemoteException;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.MailAuswahlDeleteAction;
import de.jost_net.JVerein.gui.control.MailControl;
import de.jost_net.JVerein.gui.dialogs.ShowVariablesDialog;
import de.jost_net.JVerein.rmi.MailEmpfaenger;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.parts.CheckedContextMenuItem;
import de.willuhn.jameica.gui.parts.ContextMenu;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

/**
 * Kontext-Menu zur MailEmpf�nger-Auswahl.
 */
public class MailAuswahlMenu extends ContextMenu
{

  public MailAuswahlMenu(MailControl control)
  {
    final MailControl contr = control;
    addItem(new CheckedContextMenuItem(JVereinPlugin.getI18n().tr("Variable"),
        new Action()
        {

          public void handleAction(Object context) throws ApplicationException
          {
            if (context instanceof MailEmpfaenger)
            {
              MailEmpfaenger m = (MailEmpfaenger) context;
              try
              {
                new ShowVariablesDialog(contr.getVariables(m.getMitglied()));
              }
              catch (RemoteException e)
              {
                Logger.error("Fehler", e);
                throw new ApplicationException(e);
              }
            }
            else
            {
              Logger.error("ShowVariablesDiaglog: Ung�ltige Klasse: "
                  + context.getClass().getCanonicalName());
            }

          }

        }, "variable_view.gif"));
    addItem(new CheckedContextMenuItem(JVereinPlugin.getI18n().tr("entfernen"),
        new MailAuswahlDeleteAction(control), "user-trash.png"));
  }
}
