/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/JahresabschlussView.java,v $
 * $Revision: 1.11 $
 * $Date: 2011/04/07 19:30:12 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: JahresabschlussView.java,v $
 * Revision 1.11  2011/04/07 19:30:12  jost
 * Neue Zur�ckbutton-Mimik aus Jameica
 *
 * Revision 1.10  2011-01-15 09:46:49  jost
 * Tastatursteuerung wegen Problemen mit Jameica/Hibiscus wieder entfernt.
 *
 * Revision 1.9  2010-10-15 09:58:25  jost
 * Code aufger�umt
 *
 * Revision 1.8  2010-10-07 19:49:24  jost
 * Hilfe in die View verlagert.
 *
 * Revision 1.7  2010-09-13 18:42:04  jost
 * Anfangsbest�nde beim Jahresabschluss setzen und bei der L�schung auch l�schen.
 *
 * Revision 1.6  2010-08-23 13:39:32  jost
 * Optimierung Tastatursteuerung
 *
 * Revision 1.5  2009/06/11 21:03:39  jost
 * Vorbereitung I18N
 *
 * Revision 1.4  2009/01/20 20:09:24  jost
 * neue Icons
 *
 * Revision 1.3  2009/01/20 19:15:19  jost
 * neu: Back-Button mit Icon
 *
 * Revision 1.2  2008/07/09 13:18:03  jost
 * Überflüssige Imports entfernt.
 *
 * Revision 1.1  2008/06/28 16:59:41  jost
 * Neu: Jahresabschluss
 *
 * Revision 1.2  2008/05/24 14:04:08  jost
 * Redatkionelle Änderung
 *
 * Revision 1.1  2008/05/22 06:52:26  jost
 * Buchführung
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.control.JahresabschlussControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.gui.util.LabelGroup;

public class JahresabschlussView extends AbstractView
{

  @Override
  public void bind() throws Exception
  {
    GUI.getView().setTitle(JVereinPlugin.getI18n().tr("Jahresabschluss"));

    final JahresabschlussControl control = new JahresabschlussControl(this);

    LabelGroup group = new LabelGroup(getParent(), JVereinPlugin.getI18n().tr(
        "Jahresabschluss"));
    group.addLabelPair(JVereinPlugin.getI18n().tr("von"), control.getVon());
    group.addLabelPair(JVereinPlugin.getI18n().tr("bis"), control.getBis());
    group.addLabelPair(JVereinPlugin.getI18n().tr("Datum"), control.getDatum());
    group.addLabelPair(JVereinPlugin.getI18n().tr("Name"), control.getName());
    group.addLabelPair(JVereinPlugin.getI18n().tr("Anfangsbest�nde Folgejahr"),
        control.getAnfangsbestaende());
    group.addPart(control.getJahresabschlussSaldo());

    ButtonArea buttons = new ButtonArea(getParent(), 2);
    buttons.addButton(JVereinPlugin.getI18n().tr("Hilfe"),
        new DokumentationAction(), DokumentationUtil.JAHRESABSCHLUSS, false,
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
