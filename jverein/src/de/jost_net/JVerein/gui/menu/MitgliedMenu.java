/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/menu/MitgliedMenu.java,v $
 * $Revision: 1.1 $
 * $Date: 2007/08/30 19:48:53 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: MitgliedMenu.java,v $
 * Revision 1.1  2007/08/30 19:48:53  jost
 * Neues Kontext-MenÃ¼
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.menu;

import de.jost_net.JVerein.gui.action.MitgliedDeleteAction;
import de.willuhn.jameica.gui.parts.CheckedContextMenuItem;
import de.willuhn.jameica.gui.parts.ContextMenu;

/**
 * Kontext-Menu zu den Beitragsgruppen.
 */
public class MitgliedMenu extends ContextMenu
{

  /**
   * Erzeugt ein Kontext-Menu fuer die Liste der Mitglieder.
   */
  public MitgliedMenu()
  {
    addItem(new CheckedContextMenuItem("Löschen...", new MitgliedDeleteAction()));
  }
}
