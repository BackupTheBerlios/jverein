/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/menu/LehrgangMenu.java,v $
 * $Revision: 1.2 $
 * $Date: 2009/06/11 21:03:02 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: LehrgangMenu.java,v $
 * Revision 1.2  2009/06/11 21:03:02  jost
 * Vorbereitung I18N
 *
 * Revision 1.1  2009/04/13 11:39:46  jost
 * Neu: Lehrg�nge
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.menu;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.LehrgangDeleteAction;
import de.willuhn.jameica.gui.parts.CheckedContextMenuItem;
import de.willuhn.jameica.gui.parts.ContextMenu;

/**
 * Kontext-Menu zu den Lehrg�ngen
 */
public class LehrgangMenu extends ContextMenu
{

  /**
   * Erzeugt ein Kontext-Menu fuer die Liste der Lehrg�nge.
   */
  public LehrgangMenu()
  {
    addItem(new CheckedContextMenuItem(
        JVereinPlugin.getI18n().tr("l�schen..."), new LehrgangDeleteAction(),
        "user-trash.png"));
  }
}
