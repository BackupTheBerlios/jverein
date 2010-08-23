/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/LehrgangsartDetailView.java,v $
 * $Revision: 1.4 $
 * $Date: 2010/08/23 13:39:32 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: LehrgangsartDetailView.java,v $
 * Revision 1.4  2010/08/23 13:39:32  jost
 * Optimierung Tastatursteuerung
 *
 * Revision 1.3  2009/07/24 20:22:11  jost
 * Focus auf erstes Feld setzen.
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
import de.jost_net.JVerein.gui.control.LehrgangsartControl;
import de.jost_net.JVerein.gui.internal.buttons.Back;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.gui.util.LabelGroup;
import de.willuhn.util.ApplicationException;

public class LehrgangsartDetailView extends AbstractView
{
  public void bind() throws Exception
  {
    GUI.getView().setTitle(JVereinPlugin.getI18n().tr("Lehrgangsart"));

    final LehrgangsartControl control = new LehrgangsartControl(this);

    LabelGroup group = new LabelGroup(getParent(), JVereinPlugin.getI18n().tr(
        "Lehrgangsart"));
    group.addLabelPair(JVereinPlugin.getI18n().tr("Bezeichnung"), control
        .getBezeichnung(true));
    group.addLabelPair(JVereinPlugin.getI18n().tr("von/am"), control.getVon());
    group.addLabelPair(JVereinPlugin.getI18n().tr("bis"), control.getBis());
    group.addLabelPair(JVereinPlugin.getI18n().tr("Veranstalter"), control
        .getVeranstalter());

    ButtonArea buttons = new ButtonArea(getParent(), 4);
    buttons.addButton(new Back(false));
    buttons.addButton(JVereinPlugin.getI18n().tr("&Hilfe"),
        new DokumentationAction(), DokumentationUtil.LEHRGANG, false,
        "help-browser.png");
    buttons.addButton(JVereinPlugin.getI18n().tr("&speichern"), new Action()
    {
      public void handleAction(Object context) throws ApplicationException
      {
        control.handleStore();
      }
    }, null, true, "document-save.png");
  }

  public void unbind() throws ApplicationException
  {
  }
}
