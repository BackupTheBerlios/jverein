/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/AnfangsbestandListView.java,v $
 * $Revision: 1.12 $
 * $Date: 2011/10/01 21:45:50 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.AnfangsbestandNeuAction;
import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.control.AnfangsbestandControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.util.ButtonArea;

public class AnfangsbestandListView extends AbstractView
{

  @Override
  public void bind() throws Exception
  {
    GUI.getView().setTitle(JVereinPlugin.getI18n().tr("Anfangsbest�nde"));

    AnfangsbestandControl control = new AnfangsbestandControl(this);

    control.getAnfangsbestandList().paint(this.getParent());

    ButtonArea buttons = new ButtonArea(this.getParent(), 2);
    buttons.addButton(JVereinPlugin.getI18n().tr("Hilfe"),
        new DokumentationAction(), DokumentationUtil.ANFANGSBESTAENDE, false,
        "help-browser.png");
    buttons.addButton(JVereinPlugin.getI18n().tr("neu"),
        new AnfangsbestandNeuAction(), null, true, "document-new.png");
  }

  @Override
  public String getHelp()
  {
    return "<form><p><span color=\"header\" font=\"header\">Anfangsbest�nde</span></p>"
        + "<p>F�r jedes Konto ist zu Beginn des Gesch�ftsjahres der Anfangsbestand zu "
        + "speichern. Die Buchungen sind in chronologisch korrekter Reihenfolge vorzunehmen.</p> "
        + "<p>Durch einen Doppelklick kann ein Anfangsbestand korrigiert werden. Mit einem "
        + "Klick auf neu wird ein neuer Anfangsbestand aufgenommen. Durch einen Rechtsklick "
        + "�ffnet sich ein Kontextmen�, mit dem ein Anfangsbestand gel�scht werden kann.</p>"
        + "</form>";
  }
}
