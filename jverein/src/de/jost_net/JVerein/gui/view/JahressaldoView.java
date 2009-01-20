/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/JahressaldoView.java,v $
 * $Revision: 1.3 $
 * $Date: 2009/01/20 19:15:19 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: JahressaldoView.java,v $
 * Revision 1.3  2009/01/20 19:15:19  jost
 * neu: Back-Button mit Icon
 *
 * Revision 1.2  2008/06/28 16:59:57  jost
 * Vereinheitlichung des Jahressaldos
 *
 * Revision 1.1  2008/05/25 19:36:55  jost
 * Neu: Jahressaldo
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.control.JahressaldoControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.internal.buttons.Back;
import de.willuhn.jameica.gui.parts.Button;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.gui.util.LabelGroup;
import de.willuhn.util.ApplicationException;

public class JahressaldoView extends AbstractView
{
  public void bind() throws Exception
  {
    GUI.getView().setTitle("Jahressaldo");

    final JahressaldoControl control = new JahressaldoControl(this);

    LabelGroup group = new LabelGroup(getParent(), "Jahr");
    group.addLabelPair("Jahr", control.getSuchJahr());

    ButtonArea buttons = new ButtonArea(this.getParent(), 1);
    Button button = new Button("suchen", new Action()
    {
      public void handleAction(Object context) throws ApplicationException
      {
        control.getSaldoList();
      }
    }, null, true);
    buttons.addButton(button);

    LabelGroup group2 = new LabelGroup(getParent(), "Saldo");
    group2.addPart(control.getSaldoList());

    ButtonArea buttons2 = new ButtonArea(this.getParent(), 3);
    buttons2.addButton(new Back(false));
    buttons2.addButton("Hilfe", new DokumentationAction(),
        DokumentationUtil.JAHRESSALDO);
    buttons2.addButton(control.getStartAuswertungButton());
  }

  public void unbind() throws ApplicationException
  {
  }
}
