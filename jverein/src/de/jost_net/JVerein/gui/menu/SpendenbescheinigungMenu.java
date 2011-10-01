/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/menu/SpendenbescheinigungMenu.java,v $
 * $Revision: 1.5 $
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
import de.jost_net.JVerein.gui.action.SpendenbescheinigungDeleteAction;
import de.jost_net.JVerein.gui.action.SpendenbescheinigungDuplizierenAction;
import de.willuhn.jameica.gui.parts.CheckedContextMenuItem;
import de.willuhn.jameica.gui.parts.ContextMenu;

/**
 * Kontext-Menu zu den Spendenbescheinigungen.
 */
public class SpendenbescheinigungMenu extends ContextMenu
{

  /**
   * Erzeugt ein Kontext-Menu fuer die Liste der Spendenbescheinigungen.
   */
  public SpendenbescheinigungMenu()
  {
    addItem(new CheckedContextMenuItem(JVereinPlugin.getI18n().tr(
        "als Vorlage f�r neue Spende"),
        new SpendenbescheinigungDuplizierenAction(), "edit-copy.png"));
    addItem(new CheckedContextMenuItem(
        JVereinPlugin.getI18n().tr("l�schen..."),
        new SpendenbescheinigungDeleteAction(), "user-trash.png"));
  }
}
