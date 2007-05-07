/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/WiedervorlagelisteView.java,v $
 * $Revision: 1.1 $
 * $Date: 2007/05/07 19:26:20 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: WiedervorlagelisteView.java,v $
 * Revision 1.1  2007/05/07 19:26:20  jost
 * Neu: Wiedervorlage
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.gui.action.BackAction;
import de.jost_net.JVerein.gui.control.WiedervorlageControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.util.ApplicationException;

public class WiedervorlagelisteView extends AbstractView
{
  public void bind() throws Exception
  {
    GUI.getView().setTitle("Wiedervorlagen");

    final WiedervorlageControl control = new WiedervorlageControl(this);

    control.getWiedervorlageList().paint(this.getParent());
    ButtonArea buttons = new ButtonArea(this.getParent(), 2);
    buttons.addButton("<< Zurück", new BackAction());

  }

  public void unbind() throws ApplicationException
  {
  }

}
