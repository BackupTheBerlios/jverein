/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/SpendenbescheinigungAutoNeuView.java,v $
 * $Revision: 1.4 $
 * $Date: 2011/06/20 15:12:20 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: SpendenbescheinigungAutoNeuView.java,v $
 * Revision 1.4  2011/06/20 15:12:20  jost
 * Bei der automatischen Erstellung von Spendenbescheinigungen wird das Formular mit vorgegeben.
 *
 * Revision 1.3  2011-04-07 19:35:47  jost
 * Neue Zur�ckbutton-Mimik aus Jameica
 *
 * Revision 1.2  2011-03-09 22:16:41  jost
 * Einschr�nkung auf ein Jahr.
 *
 * Revision 1.1  2011-03-07 21:05:05  jost
 * Neu:  Automatische Spendenbescheinigungen
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.control.SpendenbescheinigungAutoNeuControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.parts.ButtonArea;
import de.willuhn.jameica.gui.util.LabelGroup;

public class SpendenbescheinigungAutoNeuView extends AbstractView
{

  @Override
  public void bind() throws Exception
  {
    GUI.getView().setTitle(
        JVereinPlugin.getI18n().tr(
            "Spendenbescheinigungen automatisch neu erzeugen"));

    SpendenbescheinigungAutoNeuControl control = new SpendenbescheinigungAutoNeuControl(
        this);

    LabelGroup group = new LabelGroup(getParent(), JVereinPlugin.getI18n().tr(
        "Jahr"));
    group.addLabelPair("Jahr", control.getJahr());
    group.addLabelPair("Formular", control.getFormular());

    control.getSpendenbescheinigungTree().paint(this.getParent());

    ButtonArea buttons = new ButtonArea();
    buttons.addButton(control.getSpendenbescheinigungErstellenButton());
    buttons.addButton(JVereinPlugin.getI18n().tr("Hilfe"),
        new DokumentationAction(), DokumentationUtil.SPENDENBESCHEINIGUNG,
        false, "help-browser.png");
    buttons.paint(getParent());
  }

  @Override
  public String getHelp()
  {
    return "<form><p><span color=\"header\" font=\"header\">Spendenbescheinigungen</span></p>"
        + "</form>";
  }
}
