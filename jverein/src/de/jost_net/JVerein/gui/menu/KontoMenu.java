/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/menu/KontoMenu.java,v $
 * $Revision: 1.4 $
 * $Date: 2011/10/01 21:44:28 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/
package de.jost_net.JVerein.gui.menu;

import de.jost_net.JVerein.JVereinPlugin;
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
    addItem(new CheckedContextMenuItem(JVereinPlugin.getI18n().tr(
        "Anfangsbestand"), new AnfangsbestandNeuAction(), "document-new.png"));
    addItem(new CheckedContextMenuItem(
        JVereinPlugin.getI18n().tr("l�schen..."), new KontoDeleteAction(),
        "user-trash.png"));
  }
}
