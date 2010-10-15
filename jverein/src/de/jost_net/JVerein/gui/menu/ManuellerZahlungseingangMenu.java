/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/menu/Attic/ManuellerZahlungseingangMenu.java,v $
 * $Revision: 1.7 $
 * $Date: 2010/10/15 09:58:29 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: ManuellerZahlungseingangMenu.java,v $
 * Revision 1.7  2010/10/15 09:58:29  jost
 * Code aufger�umt
 *
 * Revision 1.6  2010-07-28 07:26:46  jost
 * deprecated
 *
 * Revision 1.5  2009/06/11 21:03:02  jost
 * Vorbereitung I18N
 *
 * Revision 1.4  2008/12/30 10:53:15  jost
 * Fehlende Icons ergänzt.
 *
 * Revision 1.3  2008/12/22 21:14:45  jost
 * Icons ins Menü aufgenommen.
 *
 * Revision 1.2  2007/03/20 07:56:54  jost
 * Probleme beim Encoding.
 *
 * Revision 1.1  2007/03/13 19:57:01  jost
 * Neu: Manueller Zahlungseingang.
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.menu;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.ManuellerZahlungseingangDatumLoeschenAction;
import de.jost_net.JVerein.gui.action.ManuellerZahlungseingangDatumSetzenAction;
import de.jost_net.JVerein.gui.action.ManuellerZahlungseingangDeleteAction;
import de.willuhn.jameica.gui.parts.CheckedContextMenuItem;
import de.willuhn.jameica.gui.parts.ContextMenu;
import de.willuhn.jameica.gui.parts.TablePart;

/**
 * Kontext-Menu zu den ManuellenZahlungseing�ngen.
 * @deprecated In Version 1.5 entfernen
 */
@Deprecated
public class ManuellerZahlungseingangMenu extends ContextMenu
{

  /**
   * Erzeugt ein Kontext-Menu fuer die Liste der ManuellenZahlungseing�nge.
   */
  public ManuellerZahlungseingangMenu(TablePart table)
  {
    addItem(new CheckedContextMenuItem(JVereinPlugin.getI18n().tr(
        "Zahlungseingangsdatum setzen ..."),
        new ManuellerZahlungseingangDatumSetzenAction(table),
        "office-calendar.png"));
    addItem(new CheckedContextMenuItem(JVereinPlugin.getI18n().tr(
        "Zahlungseingangsdatum l�schen ..."),
        new ManuellerZahlungseingangDatumLoeschenAction(table),
        "user-trash.png"));
    addItem(new CheckedContextMenuItem(
        JVereinPlugin.getI18n().tr("l�schen..."),
        new ManuellerZahlungseingangDeleteAction(table), "user-trash.png"));
  }
}
