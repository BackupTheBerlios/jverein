/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/menu/MitgliedMenu.java,v $
 * $Revision: 1.2 $
 * $Date: 2007/08/31 05:36:00 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: MitgliedMenu.java,v $
 * Revision 1.2  2007/08/31 05:36:00  jost
 * Jetzt auch bearbeiten über das Context-Menü
 *
 * Revision 1.1  2007/08/30 19:48:53  jost
 * Neues Kontext-Menü
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.menu;

import de.jost_net.JVerein.gui.action.MitgliedDeleteAction;
import de.jost_net.JVerein.gui.action.MitgliedDetailAction;
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
    addItem(new CheckedContextMenuItem("Bearbeiten", new MitgliedDetailAction()));
    addItem(new CheckedContextMenuItem("L�schen...", new MitgliedDeleteAction()));
  }
}
