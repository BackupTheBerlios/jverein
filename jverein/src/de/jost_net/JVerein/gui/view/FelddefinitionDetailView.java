/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/FelddefinitionDetailView.java,v $
 * $Revision: 1.7 $
 * $Date: 2010/01/01 18:38:54 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.action.FelddefinitionenAction;
import de.jost_net.JVerein.gui.control.FelddefinitionControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.internal.buttons.Back;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.gui.util.LabelGroup;
import de.willuhn.util.ApplicationException;

public class FelddefinitionDetailView extends AbstractView
{
  public void bind() throws Exception
  {
    GUI.getView().setTitle(JVereinPlugin.getI18n().tr("Felddefinition"));

    final FelddefinitionControl control = new FelddefinitionControl(this);

    LabelGroup group = new LabelGroup(getParent(), JVereinPlugin.getI18n().tr(
        "Felddefinition"));
    group.addLabelPair(JVereinPlugin.getI18n().tr("Name"), control
        .getName(true));
    group.addLabelPair(JVereinPlugin.getI18n().tr("Label"), control.getLabel());
    group.addLabelPair(JVereinPlugin.getI18n().tr("Datentyp"), control
        .getDatentyp());
    group
        .addLabelPair(JVereinPlugin.getI18n().tr("L�nge"), control.getLaenge());

    ButtonArea buttons = new ButtonArea(getParent(), 4);
    buttons.addButton(new Back(false));
    buttons.addButton(JVereinPlugin.getI18n().tr("Hilfe"),
        new DokumentationAction(), DokumentationUtil.FELDDEFINITIONEN, false,
        "help-browser.png");
    buttons.addButton(JVereinPlugin.getI18n().tr("�bersicht"),
        new FelddefinitionenAction());
    buttons.addButton(JVereinPlugin.getI18n().tr("speichern"), new Action()
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
