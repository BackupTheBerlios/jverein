/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/menu/FormularfeldMenu.java,v $
 * $Revision: 1.2 $
 * $Date: 2008/12/22 21:13:39 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: FormularfeldMenu.java,v $
 * Revision 1.2  2008/12/22 21:13:39  jost
 * Icons ins MenÃ¼ aufgenommen.
 *
 * Revision 1.1  2008/07/18 20:11:01  jost
 * Neu: Formulare
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.menu;

import de.jost_net.JVerein.gui.action.FormularfeldDeleteAction;
import de.willuhn.jameica.gui.parts.CheckedContextMenuItem;
import de.willuhn.jameica.gui.parts.ContextMenu;

/**
 * Kontext-Menu zu den Formularen.
 */
public class FormularfeldMenu extends ContextMenu
{

  /**
   * Erzeugt ein Kontext-Menu fuer die Liste der Formularfelder.
   */
  public FormularfeldMenu()
  {
    addItem(new CheckedContextMenuItem("Löschen...",
        new FormularfeldDeleteAction(), "user-trash.png"));
  }
}
