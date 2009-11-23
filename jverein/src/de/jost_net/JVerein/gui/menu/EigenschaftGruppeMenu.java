/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/menu/EigenschaftGruppeMenu.java,v $
 * $Revision: 1.2 $
 * $Date: 2009/11/23 20:40:07 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: EigenschaftGruppeMenu.java,v $
 * Revision 1.2  2009/11/23 20:40:07  jost
 * Neuer Menüpunkt: neu
 *
 * Revision 1.1  2009/11/17 20:58:20  jost
 * Neu: Eigenschaft und EigenschaftGruppe
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.menu;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.EigenschaftGruppeDeleteAction;
import de.jost_net.JVerein.gui.action.EigenschaftGruppeDetailAction;
import de.willuhn.jameica.gui.parts.CheckedContextMenuItem;
import de.willuhn.jameica.gui.parts.ContextMenu;
import de.willuhn.jameica.gui.parts.ContextMenuItem;

/**
 * Kontext-Menu zu den EigenschaftenGruppen.
 */
public class EigenschaftGruppeMenu extends ContextMenu
{

  /**
   * Erzeugt ein Kontext-Menu fuer die Liste der EigenschaftenGruppen
   */
  public EigenschaftGruppeMenu()
  {
    addItem(new ContextMenuItem(JVereinPlugin.getI18n().tr("neu"),
        new EigenschaftGruppeDetailAction(true), "document-new.png"));
    addItem(new CheckedContextMenuItem(
        JVereinPlugin.getI18n().tr("löschen..."),
        new EigenschaftGruppeDeleteAction(), "user-trash.png"));
  }
}
