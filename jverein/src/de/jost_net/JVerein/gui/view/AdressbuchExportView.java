/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/AdressbuchExportView.java,v $
 * $Revision: 1.7 $
 * $Date: 2011/04/07 19:28:47 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: AdressbuchExportView.java,v $
 * Revision 1.7  2011/04/07 19:28:47  jost
 * Neue Zur�ckbutton-Mimik aus Jameica
 *
 * Revision 1.6  2011-01-15 09:46:47  jost
 * Tastatursteuerung wegen Problemen mit Jameica/Hibiscus wieder entfernt.
 *
 * Revision 1.5  2010-10-15 09:58:24  jost
 * Code aufger�umt
 *
 * Revision 1.4  2010-10-07 19:49:22  jost
 * Hilfe in die View verlagert.
 *
 * Revision 1.3  2010-08-23 13:39:31  jost
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
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.gui.util.LabelGroup;

public class AdressbuchExportView extends AbstractView
{

  @Override
  public void bind() throws Exception
  {
    GUI.getView().setTitle(JVereinPlugin.getI18n().tr("Adressbuch-Export"));

    final AdressbuchExportControl control = new AdressbuchExportControl(this);

    LabelGroup group = new LabelGroup(getParent(), JVereinPlugin.getI18n().tr(
        "Parameter"));
    group.addLabelPair(JVereinPlugin.getI18n().tr("nur mit Email"),
        control.getNurEmail());
    group.addLabelPair(JVereinPlugin.getI18n().tr("Encoding"),
        control.getEncoding());
    group.addLabelPair(JVereinPlugin.getI18n().tr("Trennzeichen"),
        control.getTrennzeichen());

    ButtonArea buttons = new ButtonArea(this.getParent(), 2);
    buttons.addButton(JVereinPlugin.getI18n().tr("Hilfe"),
        new DokumentationAction(), DokumentationUtil.ADRESSBUCHEXPORT, false,
        "help-browser.png");
    buttons.addButton(control.getStartButton());
  }

  @Override
  public String getHelp()
  {
    return "<form><p><span color=\"header\" font=\"header\">Adressbuchexport</span></p>"
        + "<p>Export der Mitgliederdaten f�r den Import in ein Adressbuch.</p>"
        + "<p>Das Trennzeichen und das Encoding k�nnen bestimmt werden. Es kann "
        + "festgelegt werden, ob alle Mitglieder oder nur die Mitglieder mit Email-Adresse "
        + "ausgegeben werden.</p></form>";
  }
}
