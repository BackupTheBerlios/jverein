/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/ZusatzbetragView.java,v $
 * $Revision: 1.14 $
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
import de.jost_net.JVerein.gui.action.MitgliedDetailAction;
import de.jost_net.JVerein.gui.action.ZusatzbetraegeDeleteAction;
import de.jost_net.JVerein.gui.control.ZusatzbetragControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.parts.ButtonArea;
import de.willuhn.jameica.gui.util.LabelGroup;

public class ZusatzbetragView extends AbstractView
{

  @Override
  public void bind() throws Exception
  {
    GUI.getView().setTitle(JVereinPlugin.getI18n().tr("Zusatzbetrag"));
    final ZusatzbetragControl control = new ZusatzbetragControl(this);

    LabelGroup group = new LabelGroup(getParent(), JVereinPlugin.getI18n().tr(
        "Zusatzbetrag"));
    group.addLabelPair(JVereinPlugin.getI18n().tr("Startdatum"),
        control.getStartdatum(true));
    group.addLabelPair(JVereinPlugin.getI18n().tr("n�chste F�lligkeit"),
        control.getFaelligkeit());
    group.addLabelPair(JVereinPlugin.getI18n().tr("Intervall"),
        control.getIntervall());
    group.addLabelPair(JVereinPlugin.getI18n().tr("Endedatum"),
        control.getEndedatum());
    group.addLabelPair(JVereinPlugin.getI18n().tr("Buchungstext 1"),
        control.getBuchungstext());
    group.addLabelPair(JVereinPlugin.getI18n().tr("Buchungstext 2"),
        control.getBuchungstext2());
    group.addLabelPair(JVereinPlugin.getI18n().tr("Betrag"),
        control.getBetrag());

    ButtonArea buttons = new ButtonArea();
    buttons.addButton(JVereinPlugin.getI18n().tr("Hilfe"),
        new DokumentationAction(), DokumentationUtil.ZUSATZBETRAEGE, false,
        "help-browser.png");
    buttons.addButton("Mitglied", new MitgliedDetailAction(), control
        .getZusatzbetrag().getMitglied(), false, "system-users.png");
    buttons.addButton(JVereinPlugin.getI18n().tr("l�schen"),
        new ZusatzbetraegeDeleteAction(), control.getZusatzbetrag(), false,
        "user-trash.png");
    buttons.addButton(JVereinPlugin.getI18n().tr("speichern"), new Action()
    {

      public void handleAction(Object context)
      {
        control.handleStore();
      }
    }, null, true, "document-save.png");
    buttons.paint(getParent());
  }

  // TODO getHelp()
}
