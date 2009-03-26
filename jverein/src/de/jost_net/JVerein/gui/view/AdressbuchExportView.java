/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/AdressbuchExportView.java,v $
 * $Revision: 1.1 $
 * $Date: 2009/03/26 21:01:43 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: AdressbuchExportView.java,v $
 * Revision 1.1  2009/03/26 21:01:43  jost
 * Neu: Adressbuchexport
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.control.AdressbuchExportControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.internal.buttons.Back;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.gui.util.LabelGroup;
import de.willuhn.util.ApplicationException;

public class AdressbuchExportView extends AbstractView
{
  public void bind() throws Exception
  {
    GUI.getView().setTitle("Adressbuch-Export");

    final AdressbuchExportControl control = new AdressbuchExportControl(this);

    LabelGroup group = new LabelGroup(getParent(), "Parameter");
    group.addLabelPair("nur mit Email", control.getNurEmail());
    group.addLabelPair("Encoding", control.getEncoding());
    group.addLabelPair("Trennzeichen", control.getTrennzeichen());

    ButtonArea buttons = new ButtonArea(this.getParent(), 3);
    buttons.addButton(new Back(false));
    buttons.addButton("Hilfe", new DokumentationAction(),
        DokumentationUtil.ADRESSBUCHEXPORT, false, "help-browser.png");
    buttons.addButton(control.getStartButton());
  }

  public void unbind() throws ApplicationException
  {
  }
}
