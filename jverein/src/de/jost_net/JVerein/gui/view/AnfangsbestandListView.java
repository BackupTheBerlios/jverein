/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/AnfangsbestandListView.java,v $
 * $Revision: 1.8 $
 * $Date: 2010/10/15 09:58:23 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: AnfangsbestandListView.java,v $
 * Revision 1.8  2010/10/15 09:58:23  jost
 * Code aufger�umt
 *
 * Revision 1.7  2010-10-07 19:49:23  jost
 * Hilfe in die View verlagert.
 *
 * Revision 1.6  2010-08-23 13:39:31  jost
 * Optimierung Tastatursteuerung
 *
 * Revision 1.5  2009/06/11 21:03:39  jost
 * Vorbereitung I18N
 *
 * Revision 1.4  2009/01/20 20:09:24  jost
 * neue Icons
 *
 * Revision 1.3  2009/01/20 19:14:06  jost
 * neu: Back-Button mit Icon
 *
 * Revision 1.2  2008/05/24 14:04:08  jost
 * Redatkionelle Änderung
 *
 * Revision 1.1  2008/05/22 06:52:14  jost
 * Buchführung
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.AnfangsbestandNeuAction;
import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.control.AnfangsbestandControl;
import de.jost_net.JVerein.gui.internal.buttons.Back;
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

    ButtonArea buttons = new ButtonArea(this.getParent(), 3);
    buttons.addButton(new Back(false));
    buttons.addButton(JVereinPlugin.getI18n().tr("&Hilfe"),
        new DokumentationAction(), DokumentationUtil.ANFANGSBESTAENDE, false,
        "help-browser.png");
    buttons.addButton(JVereinPlugin.getI18n().tr("&neu"),
        new AnfangsbestandNeuAction(), null, true, "document-new.png");
  }

  @Override
  public String getHelp()
  {
    return "<form><p><span color=\"header\" font=\"header\">Anfangsbest�nde</span></p>"
        + "<p>F�r jedes Konto ist zu Beginn des Gesch�fsjahres der Anfangsbestand zu "
        + "speichern. Die Buchungen sind in chronologisch korrekter Reihenfolge vorzunehmen.</p> "
        + "<p>Durch einen Doppelklick kann ein Anfangsbestand korrigiert werden. Mit einem "
        + "Klick auf neu wird ein neuer Anfangsbestand aufgenommen. Durch einen Rechtsklick "
        + "�ffnet sich ein Kontextmen�, mit dem ein Anfangsbestand gel�scht werden kann.</p>"
        + "</form>";
  }
}
