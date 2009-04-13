/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/LehrgangsartListeView.java,v $
 * $Revision: 1.1 $
 * $Date: 2009/04/13 11:40:14 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: LehrgangsartListeView.java,v $
 * Revision 1.1  2009/04/13 11:40:14  jost
 * Neu: Lehrgänge
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.action.LehrgangsartAction;
import de.jost_net.JVerein.gui.control.LehrgangsartControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.internal.buttons.Back;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.util.ApplicationException;

public class LehrgangsartListeView extends AbstractView
{
  public void bind() throws Exception
  {
    GUI.getView().setTitle("Lehrgangsarten");

    LehrgangsartControl control = new LehrgangsartControl(this);

    control.getLehrgangsartList().paint(this.getParent());

    ButtonArea buttons = new ButtonArea(this.getParent(), 3);
    buttons.addButton(new Back(false));
    buttons.addButton("Hilfe", new DokumentationAction(),
        DokumentationUtil.LEHRGANG, false, "help-browser.png");
    buttons.addButton("neu", new LehrgangsartAction(), null, false,
        "document-new.png");
  }

  public void unbind() throws ApplicationException
  {
  }
}
