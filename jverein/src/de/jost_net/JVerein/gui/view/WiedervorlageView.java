/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/WiedervorlageView.java,v $
 *  * $Revision: 1.2 $
 * $Date: 2008/01/01 19:53:33 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: WiedervorlageView.java,v $
 * Revision 1.2  2008/01/01 19:53:33  jost
 * Erweiterung um Hilfe-Funktion
 *
 * Revision 1.1  2007/05/07 19:26:35  jost
 * Neu: Wiedervorlage
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.gui.action.BackAction;
import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.control.WiedervorlageControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.gui.util.LabelGroup;
import de.willuhn.util.ApplicationException;

public class WiedervorlageView extends AbstractView
{
  public void bind() throws Exception
  {
    GUI.getView().setTitle("Wiedervorlage");
    final WiedervorlageControl control = new WiedervorlageControl(this);

    LabelGroup group = new LabelGroup(getParent(), "Wiedervorlage");
    group.addLabelPair("Datum", control.getDatum());
    group.addLabelPair("Vermerk", control.getVermerk());
    group.addLabelPair("Erledigung", control.getErledigung());

    ButtonArea buttons = new ButtonArea(getParent(), 3);
    buttons.addButton("<< Zur�ck", new BackAction());
    buttons.addButton("Hilfe", new DokumentationAction(),
        DokumentationUtil.wiedervorlage);
    buttons.addButton("Speichern", new Action()
    {
      public void handleAction(Object context) throws ApplicationException
      {
        control.handleStore();
      }
    }, null, true);
  }

  public void unbind() throws ApplicationException
  {
  }
}
