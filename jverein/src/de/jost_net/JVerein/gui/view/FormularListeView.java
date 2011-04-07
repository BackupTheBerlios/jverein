/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/FormularListeView.java,v $
 * $Revision: 1.9 $
 * $Date: 2011/04/07 19:30:12 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: FormularListeView.java,v $
 * Revision 1.9  2011/04/07 19:30:12  jost
 * Neue Zur�ckbutton-Mimik aus Jameica
 *
 * Revision 1.8  2011-01-15 09:46:49  jost
 * Tastatursteuerung wegen Problemen mit Jameica/Hibiscus wieder entfernt.
 *
 * Revision 1.7  2010-10-15 09:58:24  jost
 * Code aufger�umt
 *
 * Revision 1.6  2010-10-07 19:49:24  jost
 * Hilfe in die View verlagert.
 *
 * Revision 1.5  2010-08-23 13:39:32  jost
 * Optimierung Tastatursteuerung
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
 * Revision 1.1  2008/07/18 20:14:19  jost
 * Neu: Formulare
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.action.FormularAction;
import de.jost_net.JVerein.gui.control.FormularControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.util.ButtonArea;

public class FormularListeView extends AbstractView
{

  @Override
  public void bind() throws Exception
  {
    GUI.getView().setTitle(JVereinPlugin.getI18n().tr("Formulare"));

    FormularControl control = new FormularControl(this);

    control.getFormularList().paint(this.getParent());

    ButtonArea buttons = new ButtonArea(this.getParent(), 2);
    buttons.addButton(JVereinPlugin.getI18n().tr("Hilfe"),
        new DokumentationAction(), DokumentationUtil.FORMULARE, false,
        "help-browser.png");
    buttons.addButton(JVereinPlugin.getI18n().tr("neu"), new FormularAction(),
        null, false, "document-new.png");
  }

  @Override
  public String getHelp()
  {
    return "<form><p><span color=\"header\" font=\"header\">Formulare</span></p>"
        + "<p> Alle verf�gbaren Formulare werden aufgelistet.</p>"
        + "<p>Durch einen Doppelklick auf ein Formular wird die Detailansicht zur "
        + "Bearbeitung ge�ffnet.</p>"
        + "<p> Mit einem rechten Mausklick �ffnet sich ein Kontext-Men�. Damit k�nnen "
        + "die Formularfelder bearbeitet werden. Das Formular kann angezeigt und "
        + "gel�scht werden.</p></form>";
  }
}
