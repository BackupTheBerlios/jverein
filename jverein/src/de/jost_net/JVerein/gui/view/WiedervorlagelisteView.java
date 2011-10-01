/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/WiedervorlagelisteView.java,v $
 * $Revision: 1.13 $
 * $Date: 2011/10/01 21:46:33 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.action.WiedervorlageListeAction;
import de.jost_net.JVerein.gui.parts.WiedervorlageList;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.util.ButtonArea;

public class WiedervorlagelisteView extends AbstractView
{

  @Override
  public void bind() throws Exception
  {
    GUI.getView().setTitle(JVereinPlugin.getI18n().tr("Wiedervorlagen"));
    new WiedervorlageList(new WiedervorlageListeAction())
        .getWiedervorlageList().paint(this.getParent());
    ButtonArea buttons = new ButtonArea(this.getParent(), 2);
    buttons.addButton(JVereinPlugin.getI18n().tr("Hilfe"),
        new DokumentationAction(), DokumentationUtil.WIEDERVORLAGE, false,
        "help-browser.png");

  }

  @Override
  public String getHelp()
  {
    return "<form><p><span color=\"header\" font=\"header\">Wiedervorlage</span></p>"
        + "<p>In dieser Liste werden die Wiedervorlagen aller Mitglieder angezeigt. "
        + "Durch einen Rechtsklick kann entweder ein Erledigungsvermerk gesetzt werden "
        + "oder der Wiedervorlagetermin wird gel�scht.</p></form>";
  }
}
