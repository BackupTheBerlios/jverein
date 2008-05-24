/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/AnfangsbestandListView.java,v $
 * $Revision: 1.2 $
 * $Date: 2008/05/24 14:04:08 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: AnfangsbestandListView.java,v $
 * Revision 1.2  2008/05/24 14:04:08  jost
 * Redatkionelle Änderung
 *
 * Revision 1.1  2008/05/22 06:52:14  jost
 * Buchführung
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.gui.action.AnfangsbestandNeuAction;
import de.jost_net.JVerein.gui.action.BackAction;
import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.control.AnfangsbestandControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.util.ApplicationException;

public class AnfangsbestandListView extends AbstractView
{
  public void bind() throws Exception
  {
    GUI.getView().setTitle("Anfangsbest�nde");

    AnfangsbestandControl control = new AnfangsbestandControl(this);

    control.getAnfangsbestandList().paint(this.getParent());

    ButtonArea buttons = new ButtonArea(this.getParent(), 3);
    buttons.addButton("<< Zur�ck", new BackAction());
    buttons.addButton("Hilfe", new DokumentationAction(),
        DokumentationUtil.ANFANGSBESTAENDE);
    buttons.addButton("neu", new AnfangsbestandNeuAction(), null);
  }

  public void unbind() throws ApplicationException
  {
  }
}
