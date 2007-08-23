/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/menu/BeitragsgruppeMenu.java,v $
 * $Revision: 1.1 $
 * $Date: 2007/08/23 19:24:45 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: BeitragsgruppeMenu.java,v $
 * Revision 1.1  2007/08/23 19:24:45  jost
 * Bug #11819 - Beitragsgruppen können jetzt gelöscht werden
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.menu;

import de.jost_net.JVerein.gui.action.BeitragsgruppeDeleteAction;
import de.willuhn.jameica.gui.parts.CheckedContextMenuItem;
import de.willuhn.jameica.gui.parts.ContextMenu;
import de.willuhn.jameica.gui.parts.TablePart;

/**
 * Kontext-Menu zu den Beitragsgruppen.
 */
public class BeitragsgruppeMenu extends ContextMenu
{

  /**
   * Erzeugt ein Kontext-Menu fuer die Liste der Beitragsgruppen.
   */
  public BeitragsgruppeMenu()
  {
    addItem(new CheckedContextMenuItem("L�schen...",
        new BeitragsgruppeDeleteAction()));
  }
}
