/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/BuchungsklasseListView.java,v $
 * $Revision: 1.6 $
 * $Date: 2011/04/07 19:30:12 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: BuchungsklasseListView.java,v $
 * Revision 1.6  2011/04/07 19:30:12  jost
 * Neue Zur�ckbutton-Mimik aus Jameica
 *
 * Revision 1.5  2011-01-15 09:46:49  jost
 * Tastatursteuerung wegen Problemen mit Jameica/Hibiscus wieder entfernt.
 *
 * Revision 1.4  2010-10-15 09:58:25  jost
 * Code aufger�umt
 *
 * Revision 1.3  2010-10-07 19:49:24  jost
 * Hilfe in die View verlagert.
 *
 * Revision 1.2  2010-08-23 13:39:33  jost
 * Optimierung Tastatursteuerung
 *
 * Revision 1.1  2009/09/10 18:18:22  jost
 * neu: Buchungsklassen
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.BuchungsklasseAction;
import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.control.BuchungsklasseControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.util.ButtonArea;

public class BuchungsklasseListView extends AbstractView
{

  @Override
  public void bind() throws Exception
  {
    GUI.getView().setTitle(JVereinPlugin.getI18n().tr("Buchungsklassen"));

    BuchungsklasseControl control = new BuchungsklasseControl(this);

    control.getBuchungsklasseList().paint(this.getParent());

    ButtonArea buttons = new ButtonArea(this.getParent(), 2);
    buttons.addButton(JVereinPlugin.getI18n().tr("Hilfe"),
        new DokumentationAction(), DokumentationUtil.BUCHUNGSKLASSEN, false,
        "help-browser.png");
    buttons.addButton(JVereinPlugin.getI18n().tr("neu"),
        new BuchungsklasseAction(), null, false, "document-new.png");
  }

  @Override
  public String getHelp()
  {
    return "<form><p><span color=\"header\" font=\"header\">Buchungsklasse</span></p>"
        + "<p>Buchungsklassen dienen der Gruppierung von Buchungsarten. Z. B. k�nnen die "
        + "Buchungsarten nach Ideeller Betrieb, Wirtschaftlicher Betrieb und Verm�gen gruppiert werden.</p>"
        + "<p>Die Buchungsklassen sind den Buchungsarten zuzuordnen.</p></form>";
  }
}
