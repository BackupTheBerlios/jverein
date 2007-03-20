/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/menu/Attic/ManuellerZahlungseingangMenu.java,v $
 * $Revision: 1.2 $
 * $Date: 2007/03/20 07:56:54 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: ManuellerZahlungseingangMenu.java,v $
 * Revision 1.2  2007/03/20 07:56:54  jost
 * Probleme beim Encoding.
 *
 * Revision 1.1  2007/03/13 19:57:01  jost
 * Neu: Manueller Zahlungseingang.
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.menu;

import de.jost_net.JVerein.gui.action.ManuellerZahlungseingangDatumLoeschenAction;
import de.jost_net.JVerein.gui.action.ManuellerZahlungseingangDatumSetzenAction;
import de.jost_net.JVerein.gui.action.ManuellerZahlungseingangDeleteAction;
import de.willuhn.jameica.gui.parts.CheckedContextMenuItem;
import de.willuhn.jameica.gui.parts.ContextMenu;
import de.willuhn.jameica.gui.parts.TablePart;

/**
 * Kontext-Menu zu den ManuellenZahlungseing�ngen.
 */
public class ManuellerZahlungseingangMenu extends ContextMenu
{

  /**
   * Erzeugt ein Kontext-Menu fuer die Liste der ManuellenZahlungseing�nge.
   */
  public ManuellerZahlungseingangMenu(TablePart table)
  {
    addItem(new CheckedContextMenuItem("Zahlungseingangsdatum setzen ...",
        new ManuellerZahlungseingangDatumSetzenAction(table)));
    addItem(new CheckedContextMenuItem("Zahlungseingangsdatum l�schen ...",
        new ManuellerZahlungseingangDatumLoeschenAction(table)));
    addItem(new CheckedContextMenuItem("L�schen...",
        new ManuellerZahlungseingangDeleteAction(table)));
  }
}
