/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/FormularListeView.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/07/18 20:14:19 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: FormularListeView.java,v $
 * Revision 1.1  2008/07/18 20:14:19  jost
 * Neu: Formulare
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.gui.action.BackAction;
import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.action.FormularAction;
import de.jost_net.JVerein.gui.control.FormularControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.util.ApplicationException;

public class FormularListeView extends AbstractView
{
  public void bind() throws Exception
  {
    GUI.getView().setTitle("Formulare");

    FormularControl control = new FormularControl(this);

    control.getFormularList().paint(this.getParent());

    ButtonArea buttons = new ButtonArea(this.getParent(), 3);
    buttons.addButton("<< Zurück", new BackAction());
    buttons.addButton("Hilfe", new DokumentationAction(),
        DokumentationUtil.FORMULARE);
    buttons.addButton("neu", new FormularAction(), null);
  }

  public void unbind() throws ApplicationException
  {
  }
}
