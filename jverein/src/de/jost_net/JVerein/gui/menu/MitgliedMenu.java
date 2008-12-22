/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/menu/MitgliedMenu.java,v $
 * $Revision: 1.4 $
 * $Date: 2008/12/22 21:14:57 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: MitgliedMenu.java,v $
 * Revision 1.4  2008/12/22 21:14:57  jost
 * Icons ins Menü aufgenommen.
 *
 * Revision 1.3  2008/07/18 20:11:31  jost
 * Neu: Spendenbescheinigung
 *
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
import de.jost_net.JVerein.gui.action.SpendenbescheinigungAction;
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
    addItem(new CheckedContextMenuItem("Bearbeiten",
        new MitgliedDetailAction(), "edit.png"));
    addItem(new CheckedContextMenuItem("L�schen...",
        new MitgliedDeleteAction(), "user-trash.png"));
    addItem(new CheckedContextMenuItem("Spendenbescheinigung",
        new SpendenbescheinigungAction(), "rechnung.png"));
  }
}
