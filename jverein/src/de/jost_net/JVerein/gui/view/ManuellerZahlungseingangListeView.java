/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/Attic/ManuellerZahlungseingangListeView.java,v $
 * $Revision: 1.1 $
 * $Date: 2007/03/13 19:57:17 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: ManuellerZahlungseingangListeView.java,v $
 * Revision 1.1  2007/03/13 19:57:17  jost
 * Neu: Manueller Zahlungseingang.
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.gui.action.BackAction;
import de.jost_net.JVerein.gui.control.ManuellerZahlungseingangControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.util.ApplicationException;

public class ManuellerZahlungseingangListeView extends AbstractView
{
  public void bind() throws Exception
  {
    GUI.getView().setTitle("�berwachung des manuellen Zahlungseingangs");

    ManuellerZahlungseingangControl control = new ManuellerZahlungseingangControl(
        this);

    control.getTable().paint(this.getParent());

    ButtonArea buttons = new ButtonArea(this.getParent(), 2);
    buttons.addButton("<< Zur�ck", new BackAction());
  }

  public void unbind() throws ApplicationException
  {
  }

}
