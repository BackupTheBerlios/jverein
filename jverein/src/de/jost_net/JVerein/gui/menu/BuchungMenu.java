/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/menu/BuchungMenu.java,v $
 * $Revision: 1.13 $
 * $Date: 2011/09/18 09:37:10 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: BuchungMenu.java,v $
 * Revision 1.13  2011/09/18 09:37:10  jost
 * Mehreren Buchungen ein Mitgliedskonto gleichzeitig zuordnen.
 *
 * Revision 1.12  2010-10-10 08:53:18  jost
 * Kontoauszugsinformationen en Bloc zuweisen
 *
 * Revision 1.11  2009/06/11 21:03:02  jost
 * Vorbereitung I18N
 *
 * Revision 1.10  2009/01/25 10:58:39  jost
 * Icons aufgenommen.
 *
 * Revision 1.9  2009/01/10 19:27:23  jost
 * Jameica 1.8-Kompatibilit�t hergestellt.
 *
 * Revision 1.8  2009/01/04 16:27:44  jost
 * Neu: F�r mehrere Buchungen gleichzeitig die Buchungsart festlegen.
 *
 * Revision 1.7  2008/12/22 21:12:25  jost
 * Icons ins Menü aufgenommen.
 *
 * Revision 1.6  2008/12/06 10:50:07  jost
 * Bearbeiten aufgenommen
 *
 * Revision 1.5  2008/05/22 06:50:44  jost
 * Buchführung
 *
 * Revision 1.4  2008/03/16 07:36:10  jost
 * Reaktivierung Buchführung
 *
 * Revision 1.2  2007/02/23 20:26:58  jost
 * Mail- und Webadresse im Header korrigiert.
 *
 * Revision 1.1  2006/10/14 16:11:34  jost
 * Buchungen l�schen eingef�hrt
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.menu;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.BuchungAction;
import de.jost_net.JVerein.gui.action.BuchungBuchungsartZuordnungAction;
import de.jost_net.JVerein.gui.action.BuchungDeleteAction;
import de.jost_net.JVerein.gui.action.BuchungKontoauszugZuordnungAction;
import de.jost_net.JVerein.gui.action.BuchungMitgliedskontoZuordnungAction;
import de.jost_net.JVerein.gui.action.BuchungNeuAction;
import de.jost_net.JVerein.gui.control.BuchungsControl;
import de.willuhn.jameica.gui.parts.CheckedContextMenuItem;
import de.willuhn.jameica.gui.parts.CheckedSingleContextMenuItem;
import de.willuhn.jameica.gui.parts.ContextMenu;
import de.willuhn.jameica.gui.parts.ContextMenuItem;

/**
 * Kontext-Menu zu den Buchungen.
 */
public class BuchungMenu extends ContextMenu
{

  /**
   * Erzeugt ein Kontext-Menu fuer die Liste der Buchungen.
   */
  public BuchungMenu()
  {
    this(null);
  }

  public BuchungMenu(BuchungsControl control)
  {
    addItem(new ContextMenuItem(JVereinPlugin.getI18n().tr("neu"),
        new BuchungNeuAction(), "document-new.png"));
    addItem(new CheckedSingleContextMenuItem(JVereinPlugin.getI18n().tr(
        "bearbeiten"), new BuchungAction(), "edit.png"));
    addItem(new CheckedContextMenuItem(JVereinPlugin.getI18n().tr(
        "Buchungsart zuordnen"),
        new BuchungBuchungsartZuordnungAction(control), "zuordnung.png"));
    addItem(new CheckedContextMenuItem(JVereinPlugin.getI18n().tr(
        "Mitgliedskonto zuordnen"), new BuchungMitgliedskontoZuordnungAction(
        control), "human_folder_public.png"));
    addItem(new CheckedContextMenuItem(JVereinPlugin.getI18n().tr(
        "Kontoauszug zuordnen"),
        new BuchungKontoauszugZuordnungAction(control), "zuordnung.png"));
    addItem(new CheckedContextMenuItem(
        JVereinPlugin.getI18n().tr("l�schen..."), new BuchungDeleteAction(),
        "user-trash.png"));
  }
}
