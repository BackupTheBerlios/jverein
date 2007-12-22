/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/JubilaeenView.java,v $
 * $Revision: 1.1 $
 * $Date: 2007/12/22 08:26:23 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: JubilaeenView.java,v $
 * Revision 1.1  2007/12/22 08:26:23  jost
 * Neu: Jubiläenliste
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.gui.action.BackAction;
import de.jost_net.JVerein.gui.control.MitgliedControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.gui.util.LabelGroup;
import de.willuhn.util.ApplicationException;

public class JubilaeenView extends AbstractView
{
  public void bind() throws Exception
  {
    GUI.getView().setTitle("Jubil�en");

    final MitgliedControl control = new MitgliedControl(this);

    LabelGroup group = new LabelGroup(getParent(), "Parameter");
    group.addLabelPair("Jahr", control.getJubeljahr());

    ButtonArea buttons = new ButtonArea(getParent(), 4);

    buttons.addButton("<< Zur�ck", new BackAction());
    buttons.addButton(control.getStartJubilaeenButton());
  }

  public void unbind() throws ApplicationException
  {
  }
}
