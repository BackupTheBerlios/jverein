/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/menu/ArbeitseinsatzMenu.java,v $
 * $Revision: 1.2 $
 * $Date: 2010/11/22 20:59:19 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: ArbeitseinsatzMenu.java,v $
 * Revision 1.2  2010/11/22 20:59:19  jost
 * Überflüssigen Code entfernt.
 *
 * Revision 1.1  2010-11-17 04:50:13  jost
 * Erster Code zum Thema Arbeitseinsatz
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.menu;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.ArbeitseinsatzDeleteAction;
import de.willuhn.jameica.gui.parts.CheckedContextMenuItem;
import de.willuhn.jameica.gui.parts.ContextMenu;

/**
 * Kontext-Menu zu den Arbeitseinsätzen.
 */
public class ArbeitseinsatzMenu extends ContextMenu
{

  /**
   * Erzeugt ein Kontext-Menu fuer die Arbeitseinsätze.
   */
  public ArbeitseinsatzMenu()
  {
    addItem(new CheckedContextMenuItem(
        JVereinPlugin.getI18n().tr("löschen..."),
        new ArbeitseinsatzDeleteAction(), "user-trash.png"));
  }
}
