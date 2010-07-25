/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/menu/AbrechnungslaufMenu.java,v $
 * $Revision: 1.1 $
 * $Date: 2010/07/25 18:35:45 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: AbrechnungslaufMenu.java,v $
 * Revision 1.1  2010/07/25 18:35:45  jost
 * Neu: Mitgliedskonto
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.menu;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.AbrechnungslaufDeleteAction;
import de.willuhn.jameica.gui.parts.CheckedContextMenuItem;
import de.willuhn.jameica.gui.parts.ContextMenu;

/**
 * Kontext-Menu zu den Abrechnungsl�ufen
 */
public class AbrechnungslaufMenu extends ContextMenu
{

  /**
   * Erzeugt ein Kontext-Menu fuer die Liste der Abrechnungl�ufe
   */
  public AbrechnungslaufMenu()
  {
    addItem(new CheckedContextMenuItem(
        JVereinPlugin.getI18n().tr("l�schen..."),
        new AbrechnungslaufDeleteAction(), "user-trash.png"));
  }
}