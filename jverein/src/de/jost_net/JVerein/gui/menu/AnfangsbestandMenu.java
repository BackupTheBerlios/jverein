/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/menu/AnfangsbestandMenu.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/05/22 06:50:30 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: AnfangsbestandMenu.java,v $
 * Revision 1.1  2008/05/22 06:50:30  jost
 * BuchfÃ¼hrung
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.menu;

import de.jost_net.JVerein.gui.action.AnfangsbestandDeleteAction;
import de.willuhn.jameica.gui.parts.CheckedContextMenuItem;
import de.willuhn.jameica.gui.parts.ContextMenu;

/**
 * Kontext-Menu zu den Anfangsbeständen.
 */
public class AnfangsbestandMenu extends ContextMenu
{

  /**
   * Erzeugt ein Kontext-Menu fuer die Liste der Zusatzabbuchungen.
   */
  public AnfangsbestandMenu()
  {
    addItem(new CheckedContextMenuItem("Löschen...",
        new AnfangsbestandDeleteAction()));
  }
}
