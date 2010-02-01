/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/menu/MailAuswahlMenu.java,v $
 * $Revision: 1.1 $
 * $Date: 2010/02/01 20:59:39 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: MailAuswahlMenu.java,v $
 * Revision 1.1  2010/02/01 20:59:39  jost
 * Neu: Einfache Mailfunktion
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.menu;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.MailAuswahlDeleteAction;
import de.jost_net.JVerein.gui.control.MailControl;
import de.willuhn.jameica.gui.parts.CheckedContextMenuItem;
import de.willuhn.jameica.gui.parts.ContextMenu;

/**
 * Kontext-Menu zur MailEmpfänger-Auswahl.
 */
public class MailAuswahlMenu extends ContextMenu
{

  public MailAuswahlMenu(MailControl control)
  {
    addItem(new CheckedContextMenuItem(JVereinPlugin.getI18n().tr("entfernen"),
        new MailAuswahlDeleteAction(control), "user-trash.png"));
  }
}
