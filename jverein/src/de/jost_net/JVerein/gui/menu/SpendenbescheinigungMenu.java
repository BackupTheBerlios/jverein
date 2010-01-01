/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/menu/SpendenbescheinigungMenu.java,v $
 * $Revision: 1.4 $
 * $Date: 2010/01/01 21:40:17 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: SpendenbescheinigungMenu.java,v $
 * Revision 1.4  2010/01/01 21:40:17  jost
 * Existierende Spendenbescheinigung kann als Vorlage f�r eine neue Spendenbescheinigung verwendet werden.
 *
 * Revision 1.3  2009/06/11 21:03:02  jost
 * Vorbereitung I18N
 *
 * Revision 1.2  2008/12/22 21:15:24  jost
 * Icons ins Menü aufgenommen.
 *
 * Revision 1.1  2008/07/19 19:24:58  jost
 * Neu: Spendenbescheinigung
 *
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
