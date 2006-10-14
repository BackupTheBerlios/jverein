/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/menu/BuchungMenu.java,v $
 * $Revision: 1.1 $
 * $Date: 2006/10/14 16:11:34 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * jost@berlios.de
 * jverein.berlios.de
 * $Log: BuchungMenu.java,v $
 * Revision 1.1  2006/10/14 16:11:34  jost
 * Buchungen löschen eingeführt
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.menu;

import de.jost_net.JVerein.gui.action.BuchungDeleteAction;
import de.willuhn.jameica.gui.parts.CheckedContextMenuItem;
import de.willuhn.jameica.gui.parts.ContextMenu;

/**
 * Kontext-Menu zu den Zusatzabbuchungen.
 */
public class BuchungMenu extends ContextMenu
{

  /**
   * Erzeugt ein Kontext-Menu fuer die Liste der Zusatzabbuchungen.
   */
  public BuchungMenu()
  {
    addItem(new CheckedContextMenuItem("Löschen...", new BuchungDeleteAction()));
  }
}
