/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/ZusatzbetraegelisteView.java,v $
 * $Revision: 1.12 $
 * $Date: 2011/10/01 21:46:34 $
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
import de.jost_net.JVerein.gui.control.ZusatzbetragControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.gui.util.LabelGroup;

public class ZusatzbetraegelisteView extends AbstractView
{

  @Override
  public void bind() throws Exception
  {
    GUI.getView().setTitle(
        JVereinPlugin.getI18n().tr("Liste der Zusatzbetr�ge"));

    final ZusatzbetragControl control = new ZusatzbetragControl(this);

    LabelGroup group = new LabelGroup(getParent(), JVereinPlugin.getI18n().tr(
        "Ausf�hrungstag"));
    group.addLabelPair(JVereinPlugin.getI18n().tr("Ausf�hrungstag"),
        control.getAusfuehrungSuch());

    control.getZusatzbetraegeList().paint(this.getParent());

    ButtonArea buttons = new ButtonArea(this.getParent(), 2);
    buttons.addButton(control.getPDFAusgabeButton());
    buttons.addButton(JVereinPlugin.getI18n().tr("Hilfe"),
        new DokumentationAction(), DokumentationUtil.ZUSATZBETRAEGE, false,
        "help-browser.png");
  }

  @Override
  public String getHelp()
  {
    return "<form><p><span color=\"header\" font=\"header\">Liste der Zusatzbetr�ge</span></p>"
        + "<p>Anzeige der Zusatzbetr�ge. Filterung auf alle, aktive, noch nicht ausgef�hrte und zu einem "
        + "bestimmten Datum ausgef�hrte Zusatzbetr�ge. Die angezeigte Liste kann als PDF-Dokument "
        + "ausgegeben werden.</p></form>";
  }
}
