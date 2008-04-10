/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/FelddefinitionenUebersichtView.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/04/10 19:00:05 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: FelddefinitionenUebersichtView.java,v $
 * Revision 1.1  2008/04/10 19:00:05  jost
 * Neu: Benutzerdefinierte Datenfelder
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.gui.action.BackAction;
import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.action.FelddefinitionDetailAction;
import de.jost_net.JVerein.gui.control.FelddefinitionControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.util.ApplicationException;

public class FelddefinitionenUebersichtView extends AbstractView
{
  public void bind() throws Exception
  {
    GUI.getView().setTitle("Felddefinitionen");

    FelddefinitionControl control = new FelddefinitionControl(this);

    control.getFelddefinitionTable().paint(this.getParent());

    ButtonArea buttons = new ButtonArea(this.getParent(), 4);
    buttons.addButton("<< Zur�ck", new BackAction());
    buttons.addButton("Hilfe", new DokumentationAction(),
        DokumentationUtil.felddefinitionen);
    // buttons.addButton("L�schen", new BeitragsgruppeDeleteAction(), control
    // .getBeitragsgruppeTable());
    buttons.addButton("Neu", new FelddefinitionDetailAction());
  }

  public void unbind() throws ApplicationException
  {
  }
}
