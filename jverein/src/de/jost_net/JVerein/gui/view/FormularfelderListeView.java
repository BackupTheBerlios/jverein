/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/FormularfelderListeView.java,v $
 * $Revision: 1.10 $
 * $Date: 2011/04/07 19:30:12 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: FormularfelderListeView.java,v $
 * Revision 1.10  2011/04/07 19:30:12  jost
 * Neue Zur�ckbutton-Mimik aus Jameica
 *
 * Revision 1.9  2011-01-15 09:46:48  jost
 * Tastatursteuerung wegen Problemen mit Jameica/Hibiscus wieder entfernt.
 *
 * Revision 1.8  2010-10-15 09:58:24  jost
 * Code aufger�umt
 *
 * Revision 1.7  2010-10-07 19:49:23  jost
 * Hilfe in die View verlagert.
 *
 * Revision 1.6  2010-08-23 13:39:31  jost
 * Optimierung Tastatursteuerung
 *
 * Revision 1.5  2010-08-10 05:40:38  jost
 * Formularanzeige eingebaut
 *
 * Revision 1.4  2009/06/11 21:03:39  jost
 * Vorbereitung I18N
 *
 * Revision 1.3  2009/01/20 20:09:24  jost
 * neue Icons
 *
 * Revision 1.2  2009/01/20 19:15:19  jost
 * neu: Back-Button mit Icon
 *
 * Revision 1.1  2008/07/18 20:12:44  jost
 * Neu: Formulare
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.action.FormularAnzeigeAction;
import de.jost_net.JVerein.gui.action.FormularfeldAction;
import de.jost_net.JVerein.gui.control.FormularfeldControl;
import de.jost_net.JVerein.rmi.Formular;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.util.ButtonArea;

public class FormularfelderListeView extends AbstractView
{

  @Override
  public void bind() throws Exception
  {
    GUI.getView().setTitle(JVereinPlugin.getI18n().tr("Formularfelder"));

    FormularfeldControl control = new FormularfeldControl(this,
        (Formular) getCurrentObject());
    control.getFormularfeldList().paint(this.getParent());

    ButtonArea buttons = new ButtonArea(this.getParent(), 3);
    buttons.addButton(JVereinPlugin.getI18n().tr("Hilfe"),
        new DokumentationAction(), DokumentationUtil.FORMULARE, false,
        "help-browser.png");
    buttons.addButton(JVereinPlugin.getI18n().tr("anzeigen"),
        new FormularAnzeigeAction(), getCurrentObject(), false, "edit.png");
    buttons
        .addButton(JVereinPlugin.getI18n().tr("neu"), new FormularfeldAction(),
            getCurrentObject(), false, "document-new.png");
  }

  @Override
  public String getHelp()
  {
    return "<form><p><span color=\"header\" font=\"header\">Formularfelder</span></p>"
        + "<p>Jedem Formular m�ssen Formularfelder zugeordnet werden. Mit <i>neu</i> "
        + "wird ein neues Formularfeld aufgenommen. Mit einem Doppelklick �ffnet "
        + "sich das Bearbeitungsfenster f�r ein Formularfeld. Durch einen Rechtsklick "
        + "erscheint ein Kontextmen�. Damit k�nnen Formularfelder gel�scht werden.</p>"
        + "</form>";
  }
}
