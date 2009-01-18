/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/menu/FormularMenu.java,v $
 * $Revision: 1.3 $
 * $Date: 2009/01/18 15:54:56 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: FormularMenu.java,v $
 * Revision 1.3  2009/01/18 15:54:56  jost
 * Icons aufgenommen.
 *
 * Revision 1.2  2008/12/22 21:13:51  jost
 * Icons ins MenÃ¼ aufgenommen.
 *
 * Revision 1.1  2008/07/18 20:11:17  jost
 * Neu: Formulare
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.menu;

import de.jost_net.JVerein.gui.action.FormularAnzeigeAction;
import de.jost_net.JVerein.gui.action.FormularDeleteAction;
import de.jost_net.JVerein.gui.action.FormularfelderListeAction;
import de.willuhn.jameica.gui.parts.CheckedContextMenuItem;
import de.willuhn.jameica.gui.parts.ContextMenu;
import de.willuhn.jameica.gui.parts.ContextMenuItem;

/**
 * Kontext-Menu zu den Formularen.
 */
public class FormularMenu extends ContextMenu
{

  /**
   * Erzeugt ein Kontext-Menu fuer die Liste der Formulare.
   */
  public FormularMenu()
  {
    addItem(new CheckedContextMenuItem("Formularfelder",
        new FormularfelderListeAction(), "rechnung.png"));
    addItem(new CheckedContextMenuItem("Anzeigen", new FormularAnzeigeAction(),
        "edit.png"));
    addItem(ContextMenuItem.SEPARATOR);
    addItem(new CheckedContextMenuItem("Löschen...",
        new FormularDeleteAction(), "user-trash.png"));
  }
}
