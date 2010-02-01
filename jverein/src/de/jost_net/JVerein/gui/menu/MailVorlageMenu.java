/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/menu/MailVorlageMenu.java,v $
 * $Revision: 1.1 $
 * $Date: 2010/02/01 20:59:39 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: MailVorlageMenu.java,v $
 * Revision 1.1  2010/02/01 20:59:39  jost
 * Neu: Einfache Mailfunktion
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.menu;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.MailVorlageDeleteAction;
import de.willuhn.jameica.gui.parts.CheckedContextMenuItem;
import de.willuhn.jameica.gui.parts.ContextMenu;

/**
 * Kontext-Menu zu den MailVorlagen.
 */
public class MailVorlageMenu extends ContextMenu
{

  public MailVorlageMenu()
  {
    addItem(new CheckedContextMenuItem(
        JVereinPlugin.getI18n().tr("löschen..."),
        new MailVorlageDeleteAction(), "user-trash.png"));
  }
}
