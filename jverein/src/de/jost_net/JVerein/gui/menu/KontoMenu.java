/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/menu/KontoMenu.java,v $
 * $Revision: 1.2 $
 * $Date: 2008/12/22 21:14:19 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: KontoMenu.java,v $
 * Revision 1.2  2008/12/22 21:14:19  jost
 * Icons ins Menü aufgenommen.
 *
 * Revision 1.1  2008/05/22 06:51:08  jost
 * Buchführung
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.menu;

import de.jost_net.JVerein.gui.action.AnfangsbestandNeuAction;
import de.jost_net.JVerein.gui.action.KontoDeleteAction;
import de.willuhn.jameica.gui.parts.CheckedContextMenuItem;
import de.willuhn.jameica.gui.parts.ContextMenu;

/**
 * Kontext-Menu zu den Konten.
 */
public class KontoMenu extends ContextMenu
{

  /**
   * Erzeugt ein Kontext-Menu fuer die Liste der Konten.
   */
  public KontoMenu()
  {
    addItem(new CheckedContextMenuItem("Anfangsbestand",
        new AnfangsbestandNeuAction(), "document-new.png"));
    addItem(new CheckedContextMenuItem("L�schen...", new KontoDeleteAction(),
        "user-trash.png"));
  }
}
