/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/FamilienbeitragView.java,v $
 * $Revision: 1.4 $
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
import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.control.MitgliedControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.util.ButtonArea;

public class FamilienbeitragView extends AbstractView
{

  @Override
  public void bind() throws Exception
  {
    GUI.getView().setTitle(JVereinPlugin.getI18n().tr("Familienbeitrag"));

    final MitgliedControl control = new MitgliedControl(this);

    control.getFamilienbeitraegeTree().paint(this.getParent());

    ButtonArea buttons = new ButtonArea(getParent(), 2);
    buttons.addButton(JVereinPlugin.getI18n().tr("Hilfe"),
        new DokumentationAction(), DokumentationUtil.ANFANGSBESTAENDE, false,
        "help-browser.png");
  }

  @Override
  public String getHelp()
  {
    return "<form><p><span color=\"header\" font=\"header\">Familienbeitr�ge</span></p>"
        + "<p>Doppelklick �ffnet das Mitglied. Rechtsklick bietet die M�glichkeit, das Mitglied aus dem Famlilienverband zu entfernen.</p>"
        + "</form>";
  }
}
