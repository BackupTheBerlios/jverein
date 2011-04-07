/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/BuchungsklasseView.java,v $
 * $Revision: 1.7 $
 * $Date: 2011/04/07 19:30:11 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: BuchungsklasseView.java,v $
 * Revision 1.7  2011/04/07 19:30:11  jost
 * Neue Zur�ckbutton-Mimik aus Jameica
 *
 * Revision 1.6  2011-01-15 09:46:47  jost
 * Tastatursteuerung wegen Problemen mit Jameica/Hibiscus wieder entfernt.
 *
 * Revision 1.5  2010-10-15 09:58:23  jost
 * Code aufger�umt
 *
 * Revision 1.4  2010-10-07 19:49:22  jost
 * Hilfe in die View verlagert.
 *
 * Revision 1.3  2010-08-23 13:39:31  jost
 * Optimierung Tastatursteuerung
 *
 * Revision 1.2  2009/09/10 18:46:46  jost
 * Hilfe aufgenommen
 *
 * Revision 1.1  2009/09/10 18:18:45  jost
 * neu: Buchungsklassen
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.control.BuchungsklasseControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.gui.util.LabelGroup;

public class BuchungsklasseView extends AbstractView
{

  @Override
  public void bind() throws Exception
  {
    GUI.getView().setTitle(JVereinPlugin.getI18n().tr("Buchungsklasse"));

    final BuchungsklasseControl control = new BuchungsklasseControl(this);

    LabelGroup group = new LabelGroup(getParent(), JVereinPlugin.getI18n().tr(
        "Buchungsklasse"));
    group.addLabelPair(JVereinPlugin.getI18n().tr("Nummer"),
        control.getNummer(true));
    group.addLabelPair(JVereinPlugin.getI18n().tr("Bezeichnung"),
        control.getBezeichnung());

    ButtonArea buttons = new ButtonArea(getParent(), 2);
    buttons.addButton(JVereinPlugin.getI18n().tr("Hilfe"),
        new DokumentationAction(), DokumentationUtil.BUCHUNGSKLASSEN, false,
        "help-browser.png");

    buttons.addButton(JVereinPlugin.getI18n().tr("speichern"), new Action()
    {

      public void handleAction(Object context)
      {
        control.handleStore();
      }
    }, null, true, "document-save.png");
  }

  // TODO getHelp()

}
