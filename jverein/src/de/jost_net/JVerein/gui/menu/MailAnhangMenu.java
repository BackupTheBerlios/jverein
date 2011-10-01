/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/menu/MailAnhangMenu.java,v $
 * $Revision: 1.2 $
 * $Date: 2011/10/01 21:44:29 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/
package de.jost_net.JVerein.gui.menu;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.MailAnhangDeleteAction;
import de.jost_net.JVerein.gui.control.MailControl;
import de.willuhn.jameica.gui.parts.CheckedContextMenuItem;
import de.willuhn.jameica.gui.parts.ContextMenu;

/**
 * Kontext-Menu zu Mailanhängen.
 */
public class MailAnhangMenu extends ContextMenu
{

  public MailAnhangMenu(MailControl control)
  {
    addItem(new CheckedContextMenuItem(JVereinPlugin.getI18n().tr("entfernen"),
        new MailAnhangDeleteAction(control), "user-trash.png"));
  }
}
