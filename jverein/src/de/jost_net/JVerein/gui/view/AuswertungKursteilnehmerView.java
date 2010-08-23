/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/AuswertungKursteilnehmerView.java,v $
 * $Revision: 1.7 $
 * $Date: 2010/08/23 13:39:32 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: AuswertungKursteilnehmerView.java,v $
 * Revision 1.7  2010/08/23 13:39:32  jost
 * Optimierung Tastatursteuerung
 *
 * Revision 1.6  2009/06/11 21:03:39  jost
 * Vorbereitung I18N
 *
 * Revision 1.5  2009/01/20 20:09:24  jost
 * neue Icons
 *
 * Revision 1.4  2009/01/20 19:14:25  jost
 * neu: Back-Button mit Icon
 *
 * Revision 1.3  2008/05/24 14:04:08  jost
 * Redatkionelle Änderung
 *
 * Revision 1.2  2008/01/01 19:47:45  jost
 * Erweiterung um Hilfe-Funktion
 *
 * Revision 1.1  2007/05/26 16:26:30  jost
 * Neu: Auswertung Kursteilnehmer
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.control.KursteilnehmerControl;
import de.jost_net.JVerein.gui.internal.buttons.Back;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.gui.util.LabelGroup;
import de.willuhn.util.ApplicationException;

public class AuswertungKursteilnehmerView extends AbstractView
{
  public void bind() throws Exception
  {
    GUI.getView().setTitle(
        JVereinPlugin.getI18n().tr("Auswertung Kursteilnehmer"));

    final KursteilnehmerControl control = new KursteilnehmerControl(this);

    LabelGroup grAbu = new LabelGroup(getParent(), JVereinPlugin.getI18n().tr(
        "Abbuchungsdatum"));
    grAbu.addLabelPair(JVereinPlugin.getI18n().tr("von"), control
        .getAbbuchungsdatumvon());
    grAbu.addLabelPair(JVereinPlugin.getI18n().tr("bis"), control
        .getAbbuchungsdatumbis());

    ButtonArea buttons = new ButtonArea(getParent(), 3);

    buttons.addButton(new Back(false));
    buttons.addButton(JVereinPlugin.getI18n().tr("&Hilfe"),
        new DokumentationAction(), DokumentationUtil.AUSWERTUNGKURSTEILNEHMER,
        false, "help-browser.png");
    buttons.addButton(control.getStartAuswertungButton());

  }

  public void unbind() throws ApplicationException
  {
  }
}
