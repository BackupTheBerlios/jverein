/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/menu/LehrgangsartMenu.java,v $
 * $Revision: 1.1 $
 * $Date: 2009/04/13 11:39:46 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: LehrgangsartMenu.java,v $
 * Revision 1.1  2009/04/13 11:39:46  jost
 * Neu: Lehrg�nge
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.menu;

import de.jost_net.JVerein.gui.action.LehrgangsartDeleteAction;
import de.willuhn.jameica.gui.parts.CheckedContextMenuItem;
import de.willuhn.jameica.gui.parts.ContextMenu;

/**
 * Kontext-Menu zu den Lehrgangsarten.
 */
public class LehrgangsartMenu extends ContextMenu
{

  /**
   * Erzeugt ein Kontext-Menu fuer die Liste der Lehrgangsarten.
   */
  public LehrgangsartMenu()
  {
    addItem(new CheckedContextMenuItem("L�schen...",
        new LehrgangsartDeleteAction(), "user-trash.png"));
  }
}
