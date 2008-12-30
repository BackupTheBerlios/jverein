/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/menu/WiedervorlageMenu.java,v $
 * $Revision: 1.3 $
 * $Date: 2008/12/30 10:53:26 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: WiedervorlageMenu.java,v $
 * Revision 1.3  2008/12/30 10:53:26  jost
 * Fehlende Icons ergänzt.
 *
 * Revision 1.2  2008/12/22 21:15:41  jost
 * Icons ins Menü aufgenommen.
 *
 * Revision 1.1  2007/05/07 19:25:43  jost
 * Neu: Wiedervorlage
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.menu;

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
    addItem(new CheckedContextMenuItem("Erledigung",
        new WiedervorlageErledigungAction(table), "emblem-default.png"));
    addItem(new CheckedContextMenuItem("L�schen...",
        new WiedervorlageDeleteAction(), "user-trash.png"));
  }
}
