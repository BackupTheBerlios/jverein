/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/MailUebersichtView.java,v $
 * $Revision: 1.7 $
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
import de.jost_net.JVerein.gui.action.MailDetailAction;
import de.jost_net.JVerein.gui.control.MailControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.util.ButtonArea;

public class MailUebersichtView extends AbstractView
{

  @Override
  public void bind() throws Exception
  {
    GUI.getView().setTitle(JVereinPlugin.getI18n().tr("Mails"));

    MailControl control = new MailControl(this);

    control.getMailList().paint(this.getParent());

    ButtonArea buttons = new ButtonArea(this.getParent(), 2);
    buttons.addButton(JVereinPlugin.getI18n().tr("Hilfe"),
        new DokumentationAction(), DokumentationUtil.MAIL, false,
        "help-browser.png");
    buttons.addButton(JVereinPlugin.getI18n().tr("neu"),
        new MailDetailAction(), null, false, "document-new.png");
  }

  @Override
  public String getHelp()
  {
    return "<form><p><span color=\"header\" font=\"header\">�bersicht �ber die Mails</span></p></form>";
  }
}
