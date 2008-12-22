/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/menu/KursteilnehmerMenu.java,v $
 * $Revision: 1.3 $
 * $Date: 2008/12/22 21:14:32 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: KursteilnehmerMenu.java,v $
 * Revision 1.3  2008/12/22 21:14:32  jost
 * Icons ins MenÃ¼ aufgenommen.
 *
 * Revision 1.2  2007/03/21 12:10:00  jost
 * Neu: Abbuchungsdatum beim Kursteilnehmer kann zurÃ¼ckgesetzt werden.
 *
 * Revision 1.1  2007/02/25 19:12:44  jost
 * Neu: Kursteilnehmer
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.menu;

import de.jost_net.JVerein.gui.action.KursteilnehmerAbuResetAction;
import de.jost_net.JVerein.gui.action.KursteilnehmerDeleteAction;
import de.willuhn.jameica.gui.parts.CheckedContextMenuItem;
import de.willuhn.jameica.gui.parts.ContextMenu;
import de.willuhn.jameica.gui.parts.TablePart;

/**
 * Kontext-Menu zu den Kursteilnehmer.
 */
public class KursteilnehmerMenu extends ContextMenu
{
  /**
   * Erzeugt ein Kontext-Menu fuer die Liste der Kursteilnehmer.
   */
  public KursteilnehmerMenu(TablePart table)
  {
    addItem(new CheckedContextMenuItem("Abbuchungsdatum löschen...",
        new KursteilnehmerAbuResetAction(table), "user-trash.png"));
    addItem(new CheckedContextMenuItem("Löschen...",
        new KursteilnehmerDeleteAction(), "user-trash.png"));
  }
}
