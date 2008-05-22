/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/menu/KontoMenu.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/05/22 06:51:08 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: KontoMenu.java,v $
 * Revision 1.1  2008/05/22 06:51:08  jost
 * BuchfÃ¼hrung
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.menu;

import de.jost_net.JVerein.gui.action.AnfangsbestandNeuAction;
import de.jost_net.JVerein.gui.action.KontoDeleteAction;
import de.willuhn.jameica.gui.parts.CheckedContextMenuItem;
import de.willuhn.jameica.gui.parts.ContextMenu;

/**
 * Kontext-Menu zu den Zusatzabbuchungen.
 */
public class KontoMenu extends ContextMenu
{

  /**
   * Erzeugt ein Kontext-Menu fuer die Liste der Konten.
   */
  public KontoMenu()
  {
    addItem(new CheckedContextMenuItem("Anfangsbestand",
        new AnfangsbestandNeuAction()));
    addItem(new CheckedContextMenuItem("Löschen...", new KontoDeleteAction()));
  }
}
