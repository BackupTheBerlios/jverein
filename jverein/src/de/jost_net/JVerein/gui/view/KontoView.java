/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/KontoView.java,v $
 * $Revision: 1.12 $
 * $Date: 2011/05/08 08:01:45 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: KontoView.java,v $
 * Revision 1.12  2011/05/08 08:01:45  jost
 * �nderung der Zuordnung des Hibiscus-Kontos erm�glicht.
 *
 * Revision 1.11  2011-04-07 19:34:54  jost
 * Neue Zur�ckbutton-Mimik aus Jameica
 *
 * Revision 1.10  2011-01-15 09:46:49  jost
 * Tastatursteuerung wegen Problemen mit Jameica/Hibiscus wieder entfernt.
 *
 * Revision 1.9  2010-10-15 09:58:24  jost
 * Code aufger�umt
 *
 * Revision 1.8  2010-10-07 19:49:24  jost
 * Hilfe in die View verlagert.
 *
 * Revision 1.7  2010-08-23 13:39:32  jost
 * Optimierung Tastatursteuerung
 *
 * Revision 1.6  2009/06/11 21:03:39  jost
 * Vorbereitung I18N
 *
 * Revision 1.5  2009/01/20 20:09:24  jost
 * neue Icons
 *
 * Revision 1.4  2009/01/20 19:15:19  jost
 * neu: Back-Button mit Icon
 *
 * Revision 1.3  2008/05/26 18:58:52  jost
 * Neu: Eröffnungsdatum
 *
 * Revision 1.2  2008/05/24 14:04:08  jost
 * Redatkionelle Änderung
 *
 * Revision 1.1  2008/05/22 06:54:09  jost
 * Buchführung: Beginn des Geschäftsjahres
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.control.KontoControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.gui.util.LabelGroup;

public class KontoView extends AbstractView
{

  @Override
  public void bind() throws Exception
  {
    GUI.getView().setTitle(JVereinPlugin.getI18n().tr("Konto"));

    final KontoControl control = new KontoControl(this);

    LabelGroup group = new LabelGroup(getParent(), JVereinPlugin.getI18n().tr(
        "Konto"));
    group.addLabelPair(JVereinPlugin.getI18n().tr("Nummer"),
        control.getNummer());
    group.addLabelPair(JVereinPlugin.getI18n().tr("Bezeichnung"),
        control.getBezeichnung());
    group.addLabelPair(JVereinPlugin.getI18n().tr("Konto-Er�ffnung"),
        control.getEroeffnung());
    group.addLabelPair(JVereinPlugin.getI18n().tr("Konto-Aufl�sung"),
        control.getAufloesung());
    group.addLabelPair(JVereinPlugin.getI18n().tr("Hibiscus-Konto"),
        control.getHibiscusId());

    ButtonArea buttons = new ButtonArea(getParent(), 2);
    buttons.addButton(JVereinPlugin.getI18n().tr("Hilfe"),
        new DokumentationAction(), DokumentationUtil.KONTEN, false,
        "help-browser.png");
    buttons.addButton(JVereinPlugin.getI18n().tr("speichern"), new Action()
    {

      public void handleAction(Object context)
      {
        control.handleStore();
      }
    }, null, true, "document-save.png");
  }

  @Override
  public String getHelp()
  {
    return "<form><p><span color=\"header\" font=\"header\">Konto</span></p>"
        + "<p>Kontonummer, Bezeichnung und Datum der Aufl�sung des Kontos k�nnen gespeichert "
        + "werden.</p>"
        + "<p>F�r Hibiscus-Konten wird zus�tzlich die Hibiscus-ID des Kontos gespeichert.</p>"
        + "</form>";
  }
}
