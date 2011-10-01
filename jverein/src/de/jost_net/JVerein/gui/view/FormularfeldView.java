/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/FormularfeldView.java,v $
 * $Revision: 1.10 $
 * $Date: 2011/10/01 21:45:49 $
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
import de.jost_net.JVerein.gui.control.FormularfeldControl;
import de.jost_net.JVerein.rmi.Formularfeld;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.gui.util.LabelGroup;

public class FormularfeldView extends AbstractView
{

  @Override
  public void bind() throws Exception
  {
    GUI.getView().setTitle(JVereinPlugin.getI18n().tr("Formularfeld"));
    Formularfeld ff = (Formularfeld) getCurrentObject();

    final FormularfeldControl control = new FormularfeldControl(this,
        ff.getFormular());

    LabelGroup group = new LabelGroup(getParent(), JVereinPlugin.getI18n().tr(
        "Formularfeld"));
    group.addLabelPair(JVereinPlugin.getI18n().tr("Name"), control.getName());
    group.addLabelPair(JVereinPlugin.getI18n().tr("von links"), control.getX());
    group.addLabelPair(JVereinPlugin.getI18n().tr("von unten"), control.getY());
    group.addLabelPair(JVereinPlugin.getI18n().tr("Font"), control.getFont());
    group.addLabelPair(JVereinPlugin.getI18n().tr("Font-H�he"),
        control.getFontsize());

    ButtonArea buttons = new ButtonArea(getParent(), 3);
    buttons.addButton(JVereinPlugin.getI18n().tr("Hilfe"),
        new DokumentationAction(), DokumentationUtil.FORMULARE, false,
        "help-browser.png");
    buttons.addButton(JVereinPlugin.getI18n().tr("speichern"), new Action()
    {

      public void handleAction(Object context)
      {
        control.handleStore();
      }
    }, null, true, "document-save.png");
  }

  @Override
  public String getHelp()
  {
    return "<form><p><span color=\"header\" font=\"header\">Formularfeld</span></p>"
        + "<p>Je nach Formulartyp k�nnen unterschiedliche Formularfelder ausgew�hlt werden. "
        + "Jedem Formulartyp ist eine Koordinate mitzugeben. Dabei handelt es sich um die "
        + "Position von links und von unten in Millimetern. Zus�tzlich sind der Zeichensatz "
        + "und die H�he des Zeichens anzugeben.</p></form>";
  }
}
