/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/FelddefinitionenUebersichtView.java,v $
 * $Revision: 1.7 $
 * $Date: 2010/10/07 19:49:22 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: FelddefinitionenUebersichtView.java,v $
 * Revision 1.7  2010/10/07 19:49:22  jost
 * Hilfe in die View verlagert.
 *
 * Revision 1.6  2010-08-23 13:39:31  jost
 * Optimierung Tastatursteuerung
 *
 * Revision 1.5  2009/06/11 21:03:39  jost
 * Vorbereitung I18N
 *
 * Revision 1.4  2009/01/20 20:09:24  jost
 * neue Icons
 *
 * Revision 1.3  2009/01/20 19:15:19  jost
 * neu: Back-Button mit Icon
 *
 * Revision 1.2  2008/05/24 14:04:08  jost
 * Redatkionelle Änderung
 *
 * Revision 1.1  2008/04/10 19:00:05  jost
 * Neu: Benutzerdefinierte Datenfelder
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.action.FelddefinitionDetailAction;
import de.jost_net.JVerein.gui.control.FelddefinitionControl;
import de.jost_net.JVerein.gui.internal.buttons.Back;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.util.ApplicationException;

public class FelddefinitionenUebersichtView extends AbstractView
{
  public void bind() throws Exception
  {
    GUI.getView().setTitle(JVereinPlugin.getI18n().tr("Felddefinitionen"));

    FelddefinitionControl control = new FelddefinitionControl(this);

    control.getFelddefinitionTable().paint(this.getParent());

    ButtonArea buttons = new ButtonArea(this.getParent(), 4);
    buttons.addButton(new Back(false));
    buttons.addButton(JVereinPlugin.getI18n().tr("&Hilfe"),
        new DokumentationAction(), DokumentationUtil.FELDDEFINITIONEN, false,
        "help-browser.png");
    // buttons.addButton("L�schen", new BeitragsgruppeDeleteAction(), control
    // .getBeitragsgruppeTable());
    buttons.addButton(JVereinPlugin.getI18n().tr("&neu"),
        new FelddefinitionDetailAction(), null, false, "document-new.png");
  }

  public void unbind() throws ApplicationException
  {
  }

  @Override
  public String getHelp()
  {
    return "<form><p><span color=\"header\" font=\"header\">Felddefinitionen</span></p>"
        + "</form>";
  }
}
