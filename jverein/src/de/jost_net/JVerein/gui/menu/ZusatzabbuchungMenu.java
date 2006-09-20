/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/menu/Attic/ZusatzabbuchungMenu.java,v $
 * $Revision: 1.1 $
 * $Date: 2006/09/20 15:38:47 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * jost@berlios.de
 * jverein.berlios.de
 * $Log: ZusatzabbuchungMenu.java,v $
 * Revision 1.1  2006/09/20 15:38:47  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.menu;

import de.jost_net.JVerein.gui.action.ZusatzabbuchungDeleteAction;
import de.jost_net.JVerein.gui.action.ZusatzabbuchungResetAction;
import de.willuhn.jameica.gui.parts.CheckedContextMenuItem;
import de.willuhn.jameica.gui.parts.ContextMenu;

/**
 * Kontext-Menu zu den Zusatzabbuchungen.
 */
public class ZusatzabbuchungMenu extends ContextMenu
{

  /**
   * Erzeugt ein Kontext-Menu fuer die Liste der Zusatzabbuchungen.
   */
  public ZusatzabbuchungMenu()
  {
    addItem(new CheckedContextMenuItem("Erneut ausführen",
        new ZusatzabbuchungResetAction()));
    addItem(new CheckedContextMenuItem("Löschen...",
        new ZusatzabbuchungDeleteAction()));
  }
}
