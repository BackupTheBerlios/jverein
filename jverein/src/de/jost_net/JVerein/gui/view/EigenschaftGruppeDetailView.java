/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/EigenschaftGruppeDetailView.java,v $
 * $Revision: 1.6 $
 * $Date: 2010/11/03 21:32:25 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: EigenschaftGruppeDetailView.java,v $
 * Revision 1.6  2010/11/03 21:32:25  jost
 * Redakt.
 *
 * Revision 1.5  2010-10-15 09:58:25  jost
 * Code aufger�umt
 *
 * Revision 1.4  2010-10-07 19:49:24  jost
 * Hilfe in die View verlagert.
 *
 * Revision 1.3  2010-09-09 18:50:40  jost
 * Eigenschaftengruppen k�nnen jetzt auch das Merkmal "Pflicht" haben. Dann mu� mindestens eine Eigenschaft ausgew�hlt werden.
 *
 * Revision 1.2  2010-08-23 13:39:32  jost
 * Optimierung Tastatursteuerung
 *
 * Revision 1.1  2009/11/17 21:00:04  jost
 * Neu: Eigenschaft und EigenschaftGruppe
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.action.EigenschaftGruppeListeAction;
import de.jost_net.JVerein.gui.control.EigenschaftGruppeControl;
import de.jost_net.JVerein.gui.internal.buttons.Back;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.gui.util.LabelGroup;

public class EigenschaftGruppeDetailView extends AbstractView
{

  @Override
  public void bind() throws Exception
  {
    GUI.getView().setTitle(JVereinPlugin.getI18n().tr("Eigenschaften-Gruppe"));

    final EigenschaftGruppeControl control = new EigenschaftGruppeControl(this);

    LabelGroup group = new LabelGroup(getParent(), JVereinPlugin.getI18n().tr(
        "Eigenschaften-Gruppe"));
    group.addLabelPair(JVereinPlugin.getI18n().tr("Bezeichnung"),
        control.getBezeichnung());
    group.addLabelPair(JVereinPlugin.getI18n().tr("Pflicht"),
        control.getPflicht());

    ButtonArea buttons = new ButtonArea(getParent(), 4);
    buttons.addButton(new Back(false));
    buttons.addButton(JVereinPlugin.getI18n().tr("&Hilfe"),
        new DokumentationAction(), DokumentationUtil.EIGENSCHAFTGRUPPE, false,
        "help-browser.png");
    buttons.addButton(JVereinPlugin.getI18n().tr("s&uche"),
        new EigenschaftGruppeListeAction(), null, false, "system-search.png");
    buttons.addButton(JVereinPlugin.getI18n().tr("&speichern"), new Action()
    {

      public void handleAction(Object context)
      {
        control.handleStore();
      }
    }, null, true, "document-save.png");
  }

  @Override
  public String getHelp()
  {
    return "<form><p><span color=\"header\" font=\"header\">Eigenschaften Gruppen</span></p>"
        + "</form>";
  }
}
