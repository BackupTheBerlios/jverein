/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/menu/Attic/ZusatzabbuchungMenu.java,v $
 * $Revision: 1.3 $
 * $Date: 2007/03/30 13:25:11 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: ZusatzabbuchungMenu.java,v $
 * Revision 1.3  2007/03/30 13:25:11  jost
 * Wiederkehrende Zusatzabbuchungen.
 *
 * Revision 1.2  2007/02/23 20:26:58  jost
 * Mail- und Webadresse im Header korrigiert.
 *
 * Revision 1.1  2006/09/20 15:38:47  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.menu;

import de.jost_net.JVerein.gui.action.ZusatzabbuchungDeleteAction;
import de.jost_net.JVerein.gui.action.ZusatzabbuchungNaechsteFaelligkeitAction;
import de.jost_net.JVerein.gui.action.ZusatzabbuchungResetAction;
import de.jost_net.JVerein.gui.action.ZusatzabbuchungVorherigeFaelligkeitAction;
import de.willuhn.jameica.gui.parts.CheckedContextMenuItem;
import de.willuhn.jameica.gui.parts.ContextMenu;
import de.willuhn.jameica.gui.parts.ContextMenuItem;
import de.willuhn.jameica.gui.parts.TablePart;

/**
 * Kontext-Menu zu den Zusatzabbuchungen.
 */
public class ZusatzabbuchungMenu extends ContextMenu
{

  /**
   * Erzeugt ein Kontext-Menu fuer die Liste der Zusatzabbuchungen.
   */
  public ZusatzabbuchungMenu(TablePart table)
  {
    addItem(new CheckedContextMenuItem("Vorheriges Fälligkeitsdatum",
        new ZusatzabbuchungVorherigeFaelligkeitAction(table)));
    addItem(new CheckedContextMenuItem("Nächstes Fälligkeitsdatum",
        new ZusatzabbuchungNaechsteFaelligkeitAction(table)));
    addItem(ContextMenuItem.SEPARATOR);
    addItem(new CheckedContextMenuItem("Erneut ausführen",
        new ZusatzabbuchungResetAction(table)));
    addItem(new CheckedContextMenuItem("Löschen...",
        new ZusatzabbuchungDeleteAction()));
  }
}
