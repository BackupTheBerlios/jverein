/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/menu/AnfangsbestandMenu.java,v $
 * $Revision: 1.2 $
 * $Date: 2008/12/22 21:10:37 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: AnfangsbestandMenu.java,v $
 * Revision 1.2  2008/12/22 21:10:37  jost
 * Icons ins MenÃ¼ aufgenommen.
 *
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
   * Erzeugt ein Kontext-Menu fuer die Liste der Zusatzbeträge.
   */
  public AnfangsbestandMenu()
  {
    addItem(new CheckedContextMenuItem("Löschen...",
        new AnfangsbestandDeleteAction(),"user-trash.png"));
  }
}
