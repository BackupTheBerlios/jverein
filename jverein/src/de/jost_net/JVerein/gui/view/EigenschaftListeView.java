/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/EigenschaftListeView.java,v $
 * $Revision: 1.3 $
 * $Date: 2010/08/23 13:39:31 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: EigenschaftListeView.java,v $
 * Revision 1.3  2010/08/23 13:39:31  jost
 * Optimierung Tastatursteuerung
 *
 * Revision 1.2  2009/11/23 20:41:45  jost
 * Bugfix L�sch-Button
 *
 * Revision 1.1  2009/11/17 21:00:42  jost
 * Neu: Eigenschaft und EigenschaftGruppe
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.action.EigenschaftDeleteAction;
import de.jost_net.JVerein.gui.action.EigenschaftDetailAction;
import de.jost_net.JVerein.gui.control.EigenschaftControl;
import de.jost_net.JVerein.gui.internal.buttons.Back;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.util.ApplicationException;

public class EigenschaftListeView extends AbstractView
{
  public void bind() throws Exception
  {
    GUI.getView().setTitle(JVereinPlugin.getI18n().tr("Eigenschaften"));

    EigenschaftControl control = new EigenschaftControl(this);

    control.getEigenschaftList().paint(this.getParent());

    ButtonArea buttons = new ButtonArea(this.getParent(), 4);
    buttons.addButton(new Back(false));
    buttons.addButton(JVereinPlugin.getI18n().tr("&Hilfe"),
        new DokumentationAction(), DokumentationUtil.EIGENSCHAFT, false,
        "help-browser.png");
    buttons.addButton(JVereinPlugin.getI18n().tr("&l�schen"),
        new EigenschaftDeleteAction(), control.getEigenschaftList(), false,
        "user-trash.png");
    buttons.addButton(JVereinPlugin.getI18n().tr("&neu"),
        new EigenschaftDetailAction(true), null, false, "document-new.png");
  }

  public void unbind() throws ApplicationException
  {
  }
}
