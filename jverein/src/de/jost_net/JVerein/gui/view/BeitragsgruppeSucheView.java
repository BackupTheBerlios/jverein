/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/BeitragsgruppeSucheView.java,v $
 * $Revision: 1.5 $
 * $Date: 2008/01/01 19:49:05 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: BeitragsgruppeSucheView.java,v $
 * Revision 1.5  2008/01/01 19:49:05  jost
 * Erweiterung um Hilfe-Funktion
 *
 * Revision 1.4  2007/12/28 15:55:00  jost
 * Button-Leiste überarbeitet.
 *
 * Revision 1.3  2007/08/30 19:49:18  jost
 * Löschung über Knopf
 *
 * Revision 1.2  2007/02/23 20:27:28  jost
 * Mail- und Webadresse im Header korrigiert.
 *
 * Revision 1.1  2006/09/20 15:39:10  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.gui.action.BackAction;
import de.jost_net.JVerein.gui.action.BeitragsgruppeDeleteAction;
import de.jost_net.JVerein.gui.action.BeitragsgruppeDetailAction;
import de.jost_net.JVerein.gui.action.DokumentationAction;
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

    ButtonArea buttons = new ButtonArea(this.getParent(), 4);
    buttons.addButton("<< Zur�ck", new BackAction());
    buttons.addButton("Hilfe", new DokumentationAction(),
        DokumentationUtil.beitragsgruppen);
    buttons.addButton("L�schen", new BeitragsgruppeDeleteAction(), control
        .getBeitragsgruppeTable());
    buttons.addButton("Neu", new BeitragsgruppeDetailAction());
  }

  public void unbind() throws ApplicationException
  {
  }
}
