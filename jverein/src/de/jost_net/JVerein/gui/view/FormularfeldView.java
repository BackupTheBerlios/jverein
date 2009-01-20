/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/FormularfeldView.java,v $
 * $Revision: 1.2 $
 * $Date: 2009/01/20 19:15:19 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: FormularfeldView.java,v $
 * Revision 1.2  2009/01/20 19:15:19  jost
 * neu: Back-Button mit Icon
 *
 * Revision 1.1  2008/07/18 20:13:25  jost
 * Neu: Formulare
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.control.FormularfeldControl;
import de.jost_net.JVerein.rmi.Formularfeld;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.internal.buttons.Back;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.gui.util.LabelGroup;
import de.willuhn.util.ApplicationException;

public class FormularfeldView extends AbstractView
{
  public void bind() throws Exception
  {
    GUI.getView().setTitle("Formularfeld");
    Formularfeld ff = (Formularfeld) getCurrentObject();

    final FormularfeldControl control = new FormularfeldControl(this, ff
        .getFormular());

    LabelGroup group = new LabelGroup(getParent(), "Formularfeld");
    group.addLabelPair("Name", control.getName());
    group.addLabelPair("von links", control.getX());
    group.addLabelPair("von unten", control.getY());
    group.addLabelPair("Font", control.getFont());
    group.addLabelPair("Font-H�he", control.getFontsize());

    ButtonArea buttons = new ButtonArea(getParent(), 4);
    buttons.addButton(new Back(false));
    buttons.addButton("Hilfe", new DokumentationAction(),
        DokumentationUtil.FORMULARE);
    buttons.addButton("Speichern", new Action()
    {
      public void handleAction(Object context) throws ApplicationException
      {
        control.handleStore();
      }
    }, null, true);
  }

  public void unbind() throws ApplicationException
  {
  }
}
