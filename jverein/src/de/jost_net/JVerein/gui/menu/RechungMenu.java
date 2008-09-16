/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/menu/Attic/RechungMenu.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/09/16 18:51:44 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: RechungMenu.java,v $
 * Revision 1.1  2008/09/16 18:51:44  jost
 * Neu: Rechnung
 *
 **********************************************************************/

package de.jost_net.JVerein.gui.menu;

import de.jost_net.JVerein.gui.action.RechnungDeleteAction;
import de.jost_net.JVerein.gui.action.RechnungAction;
import de.willuhn.jameica.gui.parts.CheckedContextMenuItem;
import de.willuhn.jameica.gui.parts.ContextMenu;

/**
 * Kontext-Menu zu den Rechnungen.
 */
public class RechungMenu extends ContextMenu
{

  /**
   * Erzeugt ein Kontext-Menu fuer die Liste der Rechnungen.
   */
  public RechungMenu()
  {
    addItem(new CheckedContextMenuItem("Rechnung...", new RechnungAction()));
    addItem(new CheckedContextMenuItem("L�schen...",
        new RechnungDeleteAction()));
  }
}
