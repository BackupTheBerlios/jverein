/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/KontoView.java,v $
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
import de.jost_net.JVerein.gui.control.KontoControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.gui.util.LabelGroup;

public class KontoView extends AbstractView
{

  @Override
  public void bind() throws Exception
  {
    GUI.getView().setTitle(JVereinPlugin.getI18n().tr("Konto"));

    final KontoControl control = new KontoControl(this);

    LabelGroup group = new LabelGroup(getParent(), JVereinPlugin.getI18n().tr(
        "Konto"));
    group.addLabelPair(JVereinPlugin.getI18n().tr("Nummer"),
        control.getNummer());
    group.addLabelPair(JVereinPlugin.getI18n().tr("Bezeichnung"),
        control.getBezeichnung());
    group.addLabelPair(JVereinPlugin.getI18n().tr("Konto-Er�ffnung"),
        control.getEroeffnung());
    group.addLabelPair(JVereinPlugin.getI18n().tr("Konto-Aufl�sung"),
        control.getAufloesung());
    group.addLabelPair(JVereinPlugin.getI18n().tr("Hibiscus-Konto"),
        control.getHibiscusId());

    ButtonArea buttons = new ButtonArea(getParent(), 2);
    buttons.addButton(JVereinPlugin.getI18n().tr("Hilfe"),
        new DokumentationAction(), DokumentationUtil.KONTEN, false,
        "help-browser.png");
    buttons.addButton(JVereinPlugin.getI18n().tr("speichern"), new Action()
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
    return "<form><p><span color=\"header\" font=\"header\">Konto</span></p>"
        + "<p>Kontonummer, Bezeichnung und Datum der Aufl�sung des Kontos k�nnen gespeichert "
        + "werden.</p>"
        + "<p>F�r Hibiscus-Konten wird zus�tzlich die Hibiscus-ID des Kontos gespeichert.</p>"
        + "</form>";
  }
}
