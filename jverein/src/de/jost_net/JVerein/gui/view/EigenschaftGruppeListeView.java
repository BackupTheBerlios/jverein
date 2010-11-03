/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/EigenschaftGruppeListeView.java,v $
 * $Revision: 1.6 $
 * $Date: 2010/11/03 21:32:44 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: EigenschaftGruppeListeView.java,v $
 * Revision 1.6  2010/11/03 21:32:44  jost
 * Redakt.
 *
 * Revision 1.5  2010-10-15 09:58:25  jost
 * Code aufger�umt
 *
 * Revision 1.4  2010-10-07 19:49:23  jost
 * Hilfe in die View verlagert.
 *
 * Revision 1.3  2010-08-23 13:39:32  jost
 * Optimierung Tastatursteuerung
 *
 * Revision 1.2  2009/11/23 20:40:35  jost
 * Neuer Men�punkt: neu
 *
 * Revision 1.1  2009/11/17 21:00:18  jost
 * Neu: Eigenschaft und EigenschaftGruppe
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.action.EigenschaftGruppeDeleteAction;
import de.jost_net.JVerein.gui.action.EigenschaftGruppeDetailAction;
import de.jost_net.JVerein.gui.control.EigenschaftGruppeControl;
import de.jost_net.JVerein.gui.internal.buttons.Back;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.util.ButtonArea;

public class EigenschaftGruppeListeView extends AbstractView
{

  @Override
  public void bind() throws Exception
  {
    GUI.getView().setTitle(JVereinPlugin.getI18n().tr("Eigenschaften Gruppen"));

    EigenschaftGruppeControl control = new EigenschaftGruppeControl(this);

    control.getEigenschaftGruppeList().paint(this.getParent());

    ButtonArea buttons = new ButtonArea(this.getParent(), 4);
    buttons.addButton(new Back(false));
    buttons.addButton(JVereinPlugin.getI18n().tr("&Hilfe"),
        new DokumentationAction(), DokumentationUtil.EIGENSCHAFTGRUPPE, false,
        "help-browser.png");
    buttons.addButton(JVereinPlugin.getI18n().tr("&l�schen"),
        new EigenschaftGruppeDeleteAction(),
        control.getEigenschaftGruppeList(), false, "user-trash.png");
    buttons.addButton(JVereinPlugin.getI18n().tr("&neu"),
        new EigenschaftGruppeDetailAction(true), null, false,
        "document-new.png");
  }

  // TODO getHelp()

}
