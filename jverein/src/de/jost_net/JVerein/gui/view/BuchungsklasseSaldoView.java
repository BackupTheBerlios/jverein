/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/BuchungsklasseSaldoView.java,v $
 * $Revision: 1.1 $
 * $Date: 2009/09/10 18:18:32 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: BuchungsklasseSaldoView.java,v $
 * Revision 1.1  2009/09/10 18:18:32  jost
 * neu: Buchungsklassen
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.control.BuchungsklasseSaldoControl;
import de.jost_net.JVerein.gui.control.JahressaldoControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.internal.buttons.Back;
import de.willuhn.jameica.gui.parts.Button;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.gui.util.LabelGroup;
import de.willuhn.util.ApplicationException;

public class BuchungsklasseSaldoView extends AbstractView
{
  public void bind() throws Exception
  {
    GUI.getView().setTitle(JVereinPlugin.getI18n().tr("Buchungsklassen-Saldo"));

    final BuchungsklasseSaldoControl control = new BuchungsklasseSaldoControl(
        this);

    LabelGroup group = new LabelGroup(getParent(), JVereinPlugin.getI18n().tr(
        "Jahr"));
    group.addLabelPair(JVereinPlugin.getI18n().tr("Jahr"), control
        .getSuchJahr());

    ButtonArea buttons = new ButtonArea(this.getParent(), 1);
    Button button = new Button(JVereinPlugin.getI18n().tr("suchen"),
        new Action()
        {
          public void handleAction(Object context) throws ApplicationException
          {
            control.getSaldoList();
          }
        }, null, true, "system-search.png");
    buttons.addButton(button);

    LabelGroup group2 = new LabelGroup(getParent(), JVereinPlugin.getI18n().tr(
        "Saldo"));
    group2.addPart(control.getSaldoList());

    ButtonArea buttons2 = new ButtonArea(this.getParent(), 3);
    buttons2.addButton(new Back(false));
    buttons2.addButton(JVereinPlugin.getI18n().tr("Hilfe"),
        new DokumentationAction(), DokumentationUtil.JAHRESSALDO, false,
        "help-browser.png");
    buttons2.addButton(control.getStartAuswertungButton());
  }

  public void unbind() throws ApplicationException
  {
  }
}
