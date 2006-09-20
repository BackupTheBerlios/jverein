/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/BeitragsgruppeSucheView.java,v $
 * $Revision: 1.1 $
 * $Date: 2006/09/20 15:39:10 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * jost@berlios.de
 * jverein.berlios.de
 * $Log: BeitragsgruppeSucheView.java,v $
 * Revision 1.1  2006/09/20 15:39:10  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.gui.action.BackAction;
import de.jost_net.JVerein.gui.action.BeitragsgruppeDetailAction;
import de.jost_net.JVerein.gui.control.BeitragsgruppeControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.util.ApplicationException;

public class BeitragsgruppeSucheView extends AbstractView
{
  public void bind() throws Exception
  {
    GUI.getView().setTitle("Beitragsgruppen");

    BeitragsgruppeControl control = new BeitragsgruppeControl(this);

    control.getBeitragsgruppeTable().paint(this.getParent());

    ButtonArea buttons = new ButtonArea(this.getParent(), 2);
    buttons.addButton("Neu", new BeitragsgruppeDetailAction());
    buttons.addButton("<< Zurück", new BackAction());
  }

  public void unbind() throws ApplicationException
  {
  }
}
