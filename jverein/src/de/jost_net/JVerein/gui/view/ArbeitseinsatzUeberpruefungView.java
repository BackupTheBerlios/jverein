/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/ArbeitseinsatzUeberpruefungView.java,v $
 * $Revision: 1.3 $
 * $Date: 2010/11/27 15:20:41 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: ArbeitseinsatzUeberpruefungView.java,v $
 * Revision 1.3  2010/11/27 15:20:41  jost
 * CSV-Ausgabe
 *
 * Revision 1.2  2010-11-27 10:56:48  jost
 * PDF-Ausgabe
 *
 * Revision 1.1  2010-11-22 21:00:20  jost
 * Initial Commit
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.control.ArbeitseinsatzControl;
import de.jost_net.JVerein.gui.internal.buttons.Back;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.parts.Button;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.gui.util.LabelGroup;
import de.willuhn.util.ApplicationException;

public class ArbeitseinsatzUeberpruefungView extends AbstractView
{

  @Override
  public void bind() throws Exception
  {
    GUI.getView().setTitle(
        JVereinPlugin.getI18n().tr("Arbeitsdienst-�berpr�fung"));

    final ArbeitseinsatzControl control = new ArbeitseinsatzControl(this);

    LabelGroup group = new LabelGroup(getParent(), JVereinPlugin.getI18n().tr(
        "Filter"));
    group.addLabelPair(JVereinPlugin.getI18n().tr("Jahr"), control
        .getSuchJahr());
    group.addLabelPair(JVereinPlugin.getI18n().tr("Auswertung"), control
        .getAuswertungSchluessel());

    ButtonArea buttons = new ButtonArea(this.getParent(), 1);
    Button button = new Button(JVereinPlugin.getI18n().tr("&suchen"),
        new Action()
        {

          public void handleAction(Object context) throws ApplicationException
          {
            control.getArbeitseinsatzUeberpruefungList();
          }
        }, null, true, "system-search.png");
    buttons.addButton(button);

    LabelGroup group2 = new LabelGroup(getParent(), JVereinPlugin.getI18n().tr(
        "Arbeitseins�tze"));
    group2.addPart(control.getArbeitseinsatzUeberpruefungList());

    ButtonArea buttons2 = new ButtonArea(this.getParent(), 4);
    buttons2.addButton(new Back(false));
    buttons2.addButton(control.getPDFAusgabeButton());
    buttons2.addButton(control.getCSVAusgabeButton());
    buttons2.addButton(JVereinPlugin.getI18n().tr("&Hilfe"),
        new DokumentationAction(), DokumentationUtil.ARBEITSEINSATZ, false,
        "help-browser.png");
  }

  @Override
  public String getHelp()
  {
    return "<form><p><span color=\"header\" font=\"header\">Jahressaldo</span></p>"
        + "</form>";
  }
}
