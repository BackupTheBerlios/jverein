/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/menu/FelddefinitionMenu.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/04/10 18:58:57 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: FelddefinitionMenu.java,v $
 * Revision 1.1  2008/04/10 18:58:57  jost
 * Neu: Benutzerdefinierte Datenfelder
 *
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.menu;

import de.jost_net.JVerein.gui.action.FelddefinitionDeleteAction;
import de.willuhn.jameica.gui.parts.CheckedContextMenuItem;
import de.willuhn.jameica.gui.parts.ContextMenu;

/**
 * Kontext-Menu zu den Felddefinitionen.
 */
public class FelddefinitionMenu extends ContextMenu
{

  /**
   * Erzeugt ein Kontext-Menu fuer die Liste der Beitragsgruppen.
   */
  public FelddefinitionMenu()
  {
    addItem(new CheckedContextMenuItem("L�schen...",
        new FelddefinitionDeleteAction()));
  }
}
