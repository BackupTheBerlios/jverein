/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/AdressbuchExportView.java,v $
 * $Revision: 1.3 $
 * $Date: 2010/08/23 13:39:31 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: AdressbuchExportView.java,v $
 * Revision 1.3  2010/08/23 13:39:31  jost
 * Optimierung Tastatursteuerung
 *
 * Revision 1.2  2009/06/11 21:03:39  jost
 * Vorbereitung I18N
 *
 * Revision 1.1  2009/03/26 21:01:43  jost
 * Neu: Adressbuchexport
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.control.AdressbuchExportControl;
import de.jost_net.JVerein.gui.internal.buttons.Back;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.gui.util.LabelGroup;
import de.willuhn.util.ApplicationException;

public class AdressbuchExportView extends AbstractView
{
  public void bind() throws Exception
  {
    GUI.getView().setTitle(JVereinPlugin.getI18n().tr("Adressbuch-Export"));

    final AdressbuchExportControl control = new AdressbuchExportControl(this);

    LabelGroup group = new LabelGroup(getParent(), JVereinPlugin.getI18n().tr(
        "Parameter"));
    group.addLabelPair(JVereinPlugin.getI18n().tr("nur mit Email"), control
        .getNurEmail());
    group.addLabelPair(JVereinPlugin.getI18n().tr("Encoding"), control
        .getEncoding());
    group.addLabelPair(JVereinPlugin.getI18n().tr("Trennzeichen"), control
        .getTrennzeichen());

    ButtonArea buttons = new ButtonArea(this.getParent(), 3);
    buttons.addButton(new Back(false));
    buttons.addButton(JVereinPlugin.getI18n().tr("&Hilfe"),
        new DokumentationAction(), DokumentationUtil.ADRESSBUCHEXPORT, false,
        "help-browser.png");
    buttons.addButton(control.getStartButton());
  }

  public void unbind() throws ApplicationException
  {
  }
}
