/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/menu/SpendenbescheinigungMenu.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/07/19 19:24:58 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: SpendenbescheinigungMenu.java,v $
 * Revision 1.1  2008/07/19 19:24:58  jost
 * Neu: Spendenbescheinigung
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.menu;

import de.jost_net.JVerein.gui.action.SpendenbescheinigungDeleteAction;
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
    addItem(new CheckedContextMenuItem("Löschen...",
        new SpendenbescheinigungDeleteAction()));
  }
}
