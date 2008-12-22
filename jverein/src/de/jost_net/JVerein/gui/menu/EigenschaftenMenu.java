/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/menu/Attic/EigenschaftenMenu.java,v $
 * $Revision: 1.2 $
 * $Date: 2008/12/22 21:13:05 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: EigenschaftenMenu.java,v $
 * Revision 1.2  2008/12/22 21:13:05  jost
 * Icons ins Menü aufgenommen.
 *
 * Revision 1.1  2008/01/25 16:03:45  jost
 * Neu: Eigenschaften des Mitgliedes
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.menu;

import de.jost_net.JVerein.gui.action.EigenschaftenDeleteAction;
import de.jost_net.JVerein.gui.action.EigenschaftenNewAction;
import de.jost_net.JVerein.gui.control.EigenschaftenControl;
import de.jost_net.JVerein.rmi.Mitglied;
import de.willuhn.jameica.gui.parts.CheckedContextMenuItem;
import de.willuhn.jameica.gui.parts.ContextMenu;
import de.willuhn.jameica.gui.parts.ContextMenuItem;

/**
 * Kontext-Menu zu den Eigenschaften.
 */
public class EigenschaftenMenu extends ContextMenu
{

  /**
   * Erzeugt ein Kontext-Menu fuer die Liste der Eigenschaften.
   */
  public EigenschaftenMenu(EigenschaftenControl control, Mitglied m)
  {
    addItem(new ContextMenuItem("Neu ...", new EigenschaftenNewAction(control,
        m), "document-new.png"));
    addItem(new CheckedContextMenuItem("L�schen",
        new EigenschaftenDeleteAction(control), "user-trash.png"));
  }
}
