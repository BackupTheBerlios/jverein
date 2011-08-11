/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/KontoauszugView.java,v $
 * $Revision: 1.1 $
 * $Date: 2011/08/11 15:23:43 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: KontoauszugView.java,v $
 * Revision 1.1  2011/08/11 15:23:43  jost
 * Neu: Kontoauszug
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.control.MitgliedskontoControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.util.ButtonArea;

public class KontoauszugView extends AbstractView
{

  @Override
  public void bind() throws Exception
  {
    GUI.getView().setTitle(JVereinPlugin.getI18n().tr("Kontoauszug"));

    final MitgliedskontoControl control = new MitgliedskontoControl(this);

    ButtonArea buttons = new ButtonArea(this.getParent(), 2);
    buttons.addButton(JVereinPlugin.getI18n().tr("Hilfe"),
        new DokumentationAction(), DokumentationUtil.MAHNUNG, false,
        "help-browser.png");
    buttons
        .addButton(control.getStartKontoauszugButton(this.getCurrentObject()));
  }

  @Override
  public String getHelp()
  {
    return "<form><p><span color=\"header\" font=\"header\">Kontoauszug</span></p>"
        + "<p>Alle Buchungen des Mitgliedskontos werden in einem Kontoauszug im PDF-Format ausgegegen.</p>"
        + "</form>";
  }
}
