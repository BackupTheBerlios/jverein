/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/FamilienbeitragView.java,v $
 * $Revision: 1.2 $
 * $Date: 2011/08/01 18:27:57 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: FamilienbeitragView.java,v $
 * Revision 1.2  2011/08/01 18:27:57  jost
 * Funktionalit�t aus FamilienbeitragControl in MitgliedControl verschoben.
 *
 * Revision 1.1  2011-07-24 18:04:27  jost
 * Neu: Auflistung Familienbeitr�ge
 *
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
        + "</form>";
  }
}
