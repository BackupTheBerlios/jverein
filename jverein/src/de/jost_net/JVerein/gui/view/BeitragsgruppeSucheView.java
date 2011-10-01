/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/BeitragsgruppeSucheView.java,v $
 * $Revision: 1.15 $
 * $Date: 2011/10/01 21:45:50 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.BeitragsgruppeDeleteAction;
import de.jost_net.JVerein.gui.action.BeitragsgruppeDetailAction;
import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.control.BeitragsgruppeControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.util.ButtonArea;

public class BeitragsgruppeSucheView extends AbstractView
{

  @Override
  public void bind() throws Exception
  {
    GUI.getView().setTitle(JVereinPlugin.getI18n().tr("Beitragsgruppen"));

    BeitragsgruppeControl control = new BeitragsgruppeControl(this);

    control.getBeitragsgruppeTable().paint(this.getParent());

    ButtonArea buttons = new ButtonArea(this.getParent(), 3);
    buttons.addButton(JVereinPlugin.getI18n().tr("Hilfe"),
        new DokumentationAction(), DokumentationUtil.BEITRAGSGRUPPEN, false,
        "help-browser.png");
    buttons.addButton(JVereinPlugin.getI18n().tr("l�schen"),
        new BeitragsgruppeDeleteAction(), control.getBeitragsgruppeTable(),
        false, "user-trash.png");
    buttons.addButton(JVereinPlugin.getI18n().tr("neu"),
        new BeitragsgruppeDetailAction(), null, false, "document-new.png");
  }

  @Override
  public String getHelp()
  {
    return "<form><p><span color=\"header\" font=\"header\">Beitragsgruppe</span></p>"
        + "<p>Alle Beitragsgruppen werden angezeigt. Durch einen Doppelklick kann eine "
        + "Beitragsgruppe zur Bearbeitung ausgew�hlt werden.</p></form>";
  }
}
