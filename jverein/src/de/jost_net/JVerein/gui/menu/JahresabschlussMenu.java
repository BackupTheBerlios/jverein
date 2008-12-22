/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/menu/JahresabschlussMenu.java,v $
 * $Revision: 1.2 $
 * $Date: 2008/12/22 21:14:03 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: JahresabschlussMenu.java,v $
 * Revision 1.2  2008/12/22 21:14:03  jost
 * Icons ins Menü aufgenommen.
 *
 * Revision 1.1  2008/06/28 16:58:24  jost
 * Neu: Jahresabschluss
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.menu;

import de.jost_net.JVerein.gui.action.JahresabschlussDeleteAction;
import de.willuhn.jameica.gui.parts.CheckedContextMenuItem;
import de.willuhn.jameica.gui.parts.ContextMenu;

/**
 * Kontext-Menu zu den Jahresabschl�ssen.
 */
public class JahresabschlussMenu extends ContextMenu
{

  /**
   * Erzeugt ein Kontext-Menu fuer die Liste der Jahresabschl�sse
   */
  public JahresabschlussMenu()
  {
    addItem(new CheckedContextMenuItem("L�schen...",
        new JahresabschlussDeleteAction(), "user-trash.png"));
  }
}
