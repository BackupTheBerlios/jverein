/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/AdresstypListView.java,v $
 * $Revision: 1.2 $
 * $Date: 2011/04/07 19:28:47 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: AdresstypListView.java,v $
 * Revision 1.2  2011/04/07 19:28:47  jost
 * Neue Zur�ckbutton-Mimik aus Jameica
 *
 * Revision 1.1  2011-01-27 22:21:53  jost
 * Neu: Speicherung von weiteren Adressen in der Mitgliedertabelle
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.AdresstypAction;
import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.control.AdresstypControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.util.ButtonArea;

public class AdresstypListView extends AbstractView
{

  @Override
  public void bind() throws Exception
  {
    GUI.getView().setTitle(JVereinPlugin.getI18n().tr("Adresstypen"));

    AdresstypControl control = new AdresstypControl(this);

    control.getAdresstypList().paint(this.getParent());

    ButtonArea buttons = new ButtonArea(this.getParent(), 2);
    buttons.addButton(JVereinPlugin.getI18n().tr("Hilfe"),
        new DokumentationAction(), DokumentationUtil.ADRESSTYPEN, false,
        "help-browser.png");
    buttons.addButton(JVereinPlugin.getI18n().tr("neu"), new AdresstypAction(),
        null, false, "document-new.png");
  }

  @Override
  public String getHelp()
  {
    return "<form><p><span color=\"header\" font=\"header\">Adresstypen</span></p>"
        + "<p>JVerein gibt die Adresstypen Mitglied und Spender automatisch vor. Weitere Adresstypen "
        + "(Beispiele: Lieferanten, Trainer) k�nnen eingerichtet werden.</p>"
        + "</form>";
  }
}
