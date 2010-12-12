/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/menu/DokumentMenu.java,v $
 * $Revision: 1.1 $
 * $Date: 2010/12/12 08:11:43 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: DokumentMenu.java,v $
 * Revision 1.1  2010/12/12 08:11:43  jost
 * Neu: Speicherung von Dokumenten
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.menu;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.DokumentDeleteAction;
import de.jost_net.JVerein.gui.action.DokumentShowAction;
import de.willuhn.jameica.gui.parts.CheckedContextMenuItem;
import de.willuhn.jameica.gui.parts.ContextMenu;
import de.willuhn.jameica.gui.parts.ContextMenuItem;

/**
 * Kontext-Menu zu den Dokumenten.
 */
public class DokumentMenu extends ContextMenu
{

  public DokumentMenu()
  {
    new ContextMenuItem();
    addItem(new CheckedContextMenuItem(JVereinPlugin.getI18n().tr("anzeigen"),
        new DokumentShowAction(), "show.png"));
    addItem(ContextMenuItem.SEPARATOR);
    addItem(new CheckedContextMenuItem(
        JVereinPlugin.getI18n().tr("löschen..."), new DokumentDeleteAction(),
        "user-trash.png"));
  }
}
