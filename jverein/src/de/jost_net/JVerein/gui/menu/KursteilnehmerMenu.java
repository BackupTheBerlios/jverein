/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/menu/KursteilnehmerMenu.java,v $
 * $Revision: 1.1 $
 * $Date: 2007/02/25 19:12:44 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: KursteilnehmerMenu.java,v $
 * Revision 1.1  2007/02/25 19:12:44  jost
 * Neu: Kursteilnehmer
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.menu;

import de.jost_net.JVerein.gui.action.KursteilnehmerDeleteAction;
import de.willuhn.jameica.gui.parts.CheckedContextMenuItem;
import de.willuhn.jameica.gui.parts.ContextMenu;

/**
 * Kontext-Menu zu den Kursteilnehmer.
 */
public class KursteilnehmerMenu extends ContextMenu
{

  /**
   * Erzeugt ein Kontext-Menu fuer die Liste der Kursteilnehmer.
   */
  public KursteilnehmerMenu()
  {
    addItem(new CheckedContextMenuItem("Löschen...",
        new KursteilnehmerDeleteAction()));
  }
}
