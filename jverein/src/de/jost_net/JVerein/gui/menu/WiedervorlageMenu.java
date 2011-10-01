/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/menu/WiedervorlageMenu.java,v $
 * $Revision: 1.5 $
 * $Date: 2011/10/01 21:44:28 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/
package de.jost_net.JVerein.gui.menu;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.WiedervorlageDeleteAction;
import de.jost_net.JVerein.gui.action.WiedervorlageErledigungAction;
import de.willuhn.jameica.gui.parts.CheckedContextMenuItem;
import de.willuhn.jameica.gui.parts.ContextMenu;
import de.willuhn.jameica.gui.parts.TablePart;

/**
 * Kontext-Menu zu den Wiedervorlagen.
 */
public class WiedervorlageMenu extends ContextMenu
{

  /**
   * Erzeugt ein Kontext-Menu fuer die Wiedervorlagen.
   */
  public WiedervorlageMenu(TablePart table)
  {
    addItem(new CheckedContextMenuItem(JVereinPlugin.getI18n().tr("erledigt"),
        new WiedervorlageErledigungAction(table), "emblem-default.png"));
    addItem(new CheckedContextMenuItem(
        JVereinPlugin.getI18n().tr("löschen..."),
        new WiedervorlageDeleteAction(), "user-trash.png"));
  }
}
