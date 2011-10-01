/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/LehrgaengeListeView.java,v $
 * $Revision: 1.9 $
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
import de.jost_net.JVerein.gui.control.LehrgangControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.gui.util.LabelGroup;

public class LehrgaengeListeView extends AbstractView
{

  @Override
  public void bind() throws Exception
  {
    GUI.getView().setTitle(JVereinPlugin.getI18n().tr("Lehrg�nge"));

    LehrgangControl control = new LehrgangControl(this);

    LabelGroup group = new LabelGroup(getParent(), JVereinPlugin.getI18n().tr(
        "Filter"));
    group.addLabelPair(JVereinPlugin.getI18n().tr("Lehrgangsart"),
        control.getSuchLehrgangsart());
    group.addLabelPair(JVereinPlugin.getI18n().tr("Datum von"),
        control.getDatumvon());
    group.addLabelPair(JVereinPlugin.getI18n().tr("Datum bis"),
        control.getDatumbis());

    control.getLehrgaengeList().paint(this.getParent());
    ButtonArea buttons = new ButtonArea(this.getParent(), 1);
    buttons.addButton(JVereinPlugin.getI18n().tr("Hilfe"),
        new DokumentationAction(), DokumentationUtil.LEHRGANG, false,
        "help-browser.png");
  }

  @Override
  public String getHelp()
  {
    return "<form><p><span color=\"header\" font=\"header\">Liste der Lehrg�nge</span></p>"
        + "<p>Mit einem Rechtsklick kann ein Eintrag gel�scht werden.</p></form>";
  }
}
