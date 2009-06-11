/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/menu/KursteilnehmerMenu.java,v $
 * $Revision: 1.4 $
 * $Date: 2009/06/11 21:03:02 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: KursteilnehmerMenu.java,v $
 * Revision 1.4  2009/06/11 21:03:02  jost
 * Vorbereitung I18N
 *
 * Revision 1.3  2008/12/22 21:14:32  jost
 * Icons ins Menü aufgenommen.
 *
 * Revision 1.2  2007/03/21 12:10:00  jost
 * Neu: Abbuchungsdatum beim Kursteilnehmer kann zurückgesetzt werden.
 *
 * Revision 1.1  2007/02/25 19:12:44  jost
 * Neu: Kursteilnehmer
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.menu;

import de.jost_net.JVerein.JVereinPlugin;
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
    addItem(new CheckedContextMenuItem(JVereinPlugin.getI18n().tr(
        "Abbuchungsdatum l�schen..."), new KursteilnehmerAbuResetAction(table),
        "user-trash.png"));
    addItem(new CheckedContextMenuItem(
        JVereinPlugin.getI18n().tr("l�schen..."),
        new KursteilnehmerDeleteAction(), "user-trash.png"));
  }
}
