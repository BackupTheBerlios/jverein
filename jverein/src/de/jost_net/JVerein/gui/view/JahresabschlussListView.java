/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/JahresabschlussListView.java,v $
 * $Revision: 1.10 $
 * $Date: 2011/10/01 21:46:33 $
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
import de.jost_net.JVerein.gui.action.JahresabschlussDetailAction;
import de.jost_net.JVerein.gui.control.JahresabschlussControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.util.ButtonArea;

public class JahresabschlussListView extends AbstractView
{

  @Override
  public void bind() throws Exception
  {
    GUI.getView().setTitle(JVereinPlugin.getI18n().tr("Jahresabschl�sse"));

    JahresabschlussControl control = new JahresabschlussControl(this);

    control.getJahresabschlussList().paint(this.getParent());

    ButtonArea buttons = new ButtonArea(this.getParent(), 2);
    buttons.addButton(JVereinPlugin.getI18n().tr("Hilfe"),
        new DokumentationAction(), DokumentationUtil.ANFANGSBESTAENDE, false,
        "help-browser.png");
    buttons.addButton(JVereinPlugin.getI18n().tr("neu"),
        new JahresabschlussDetailAction(), null, false, "document-new.png");
  }

  @Override
  public String getHelp()
  {
    return "<form><p><span color=\"header\" font=\"header\">Jahresabschl�sse</span></p>"
        + "</form>";
  }
}
