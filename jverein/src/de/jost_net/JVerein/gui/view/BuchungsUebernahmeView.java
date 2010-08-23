/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/BuchungsUebernahmeView.java,v $
 * $Revision: 1.10 $
 * $Date: 2010/08/23 13:39:31 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: BuchungsUebernahmeView.java,v $
 * Revision 1.10  2010/08/23 13:39:31  jost
 * Optimierung Tastatursteuerung
 *
 * Revision 1.9  2009/06/11 21:03:39  jost
 * Vorbereitung I18N
 *
 * Revision 1.8  2009/01/20 20:09:24  jost
 * neue Icons
 *
 * Revision 1.7  2009/01/20 19:15:19  jost
 * neu: Back-Button mit Icon
 *
 * Revision 1.6  2008/05/24 14:04:08  jost
 * Redatkionelle Änderung
 *
 * Revision 1.5  2008/05/22 06:53:03  jost
 * Buchführung
 *
 * Revision 1.4  2008/03/16 07:36:29  jost
 * Reaktivierung Buchführung
 *
 * Revision 1.2  2007/02/23 20:27:28  jost
 * Mail- und Webadresse im Header korrigiert.
 *
 * Revision 1.1  2006/09/20 15:39:10  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.control.BuchungsuebernahmeControl;
import de.jost_net.JVerein.gui.internal.buttons.Back;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.gui.util.LabelGroup;
import de.willuhn.util.ApplicationException;

public class BuchungsUebernahmeView extends AbstractView
{
  public void bind() throws Exception
  {
    GUI.getView().setTitle(
        JVereinPlugin.getI18n().tr("Buchungen aus Hibiscus �bernehmen"));

    final BuchungsuebernahmeControl control = new BuchungsuebernahmeControl(
        this);

    LabelGroup group = new LabelGroup(getParent(), JVereinPlugin.getI18n().tr(
        "Auswahl"));
    group.addLabelPair(JVereinPlugin.getI18n().tr("Konto"), control.getKonto());
    ButtonArea suchButton = new ButtonArea(group.getComposite(), 1);
    suchButton.addButton(control.getSuchButton());

    control.getBuchungsList().paint(this.getParent());

    ButtonArea buttons = new ButtonArea(this.getParent(), 3);
    buttons.addButton(new Back(false));
    buttons.addButton(JVereinPlugin.getI18n().tr("&Hilfe"),
        new DokumentationAction(), DokumentationUtil.BUCHUNGENAUSHIBISCUS,
        false, "help-browser.png");
    buttons.addButton(control.getUebernahmeButton());
  }

  public void unbind() throws ApplicationException
  {
  }
}
