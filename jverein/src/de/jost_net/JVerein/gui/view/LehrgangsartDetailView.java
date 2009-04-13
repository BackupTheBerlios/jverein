/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/LehrgangsartDetailView.java,v $
 * $Revision: 1.1 $
 * $Date: 2009/04/13 11:40:14 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: LehrgangsartDetailView.java,v $
 * Revision 1.1  2009/04/13 11:40:14  jost
 * Neu: Lehrgänge
 *

 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.control.LehrgangsartControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.internal.buttons.Back;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.gui.util.LabelGroup;
import de.willuhn.util.ApplicationException;

public class LehrgangsartDetailView extends AbstractView
{
  public void bind() throws Exception
  {
    GUI.getView().setTitle("Lehrgangsart");

    final LehrgangsartControl control = new LehrgangsartControl(this);

    LabelGroup group = new LabelGroup(getParent(), "Lehrgangsart");
    group.addLabelPair("Bezeichnung", control.getBezeichnung());
    group.addLabelPair("von/am", control.getVon());
    group.addLabelPair("bis", control.getBis());
    group.addLabelPair("Veranstalter", control.getVeranstalter());

    ButtonArea buttons = new ButtonArea(getParent(), 4);
    buttons.addButton(new Back(false));
    buttons.addButton("Hilfe", new DokumentationAction(),
        DokumentationUtil.LEHRGANG, false, "help-browser.png");
    buttons.addButton("Speichern", new Action()
    {
      public void handleAction(Object context) throws ApplicationException
      {
        control.handleStore();
      }
    }, null, true, "document-save.png");
  }

  public void unbind() throws ApplicationException
  {
  }
}
