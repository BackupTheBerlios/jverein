/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/menu/EigenschaftMenu.java,v $
 * $Revision: 1.1 $
 * $Date: 2009/11/17 20:58:32 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: EigenschaftMenu.java,v $
 * Revision 1.1  2009/11/17 20:58:32  jost
 * Neu: Eigenschaft und EigenschaftGruppe
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.menu;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.EigenschaftDeleteAction;
import de.willuhn.jameica.gui.parts.CheckedContextMenuItem;
import de.willuhn.jameica.gui.parts.ContextMenu;

/**
 * Kontext-Menu zur Eigenschaft.
 */
public class EigenschaftMenu extends ContextMenu
{

  /**
   * Erzeugt ein Kontext-Menu fuer die Liste der Eigenschaften
   */
  public EigenschaftMenu()
  {
    addItem(new CheckedContextMenuItem(
        JVereinPlugin.getI18n().tr("löschen..."),
        new EigenschaftDeleteAction(), "user-trash.png"));
  }
}
