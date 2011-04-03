/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/LehrgaengeListeView.java,v $
 * $Revision: 1.7 $
 * $Date: 2011/04/03 07:36:38 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: LehrgaengeListeView.java,v $
 * Revision 1.7  2011/04/03 07:36:38  jost
 * Neu: Filter f�r die Lehrgangs�bersicht
 *
 * Revision 1.6  2011-01-15 09:46:48  jost
 * Tastatursteuerung wegen Problemen mit Jameica/Hibiscus wieder entfernt.
 *
 * Revision 1.5  2010-10-15 09:58:23  jost
 * Code aufger�umt
 *
 * Revision 1.4  2010-10-07 19:49:23  jost
 * Hilfe in die View verlagert.
 *
 * Revision 1.3  2010-08-23 13:39:32  jost
 * Optimierung Tastatursteuerung
 *
 * Revision 1.2  2009/06/11 21:03:39  jost
 * Vorbereitung I18N
 *
 * Revision 1.1  2009/04/13 11:40:14  jost
 * Neu: Lehrg�nge
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.control.LehrgangControl;
import de.jost_net.JVerein.gui.internal.buttons.Back;
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
    ButtonArea buttons = new ButtonArea(this.getParent(), 2);
    buttons.addButton(new Back(false));
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
