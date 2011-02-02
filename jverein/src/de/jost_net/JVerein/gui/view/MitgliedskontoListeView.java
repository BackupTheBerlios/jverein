/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/MitgliedskontoListeView.java,v $
 * $Revision: 1.9 $
 * $Date: 2011/02/02 16:23:50 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: MitgliedskontoListeView.java,v $
 * Revision 1.9  2011/02/02 16:23:50  jost
 * Status von "Differenz" wird gespeichert.
 *
 * Revision 1.8  2011-01-15 09:46:48  jost
 * Tastatursteuerung wegen Problemen mit Jameica/Hibiscus wieder entfernt.
 *
 * Revision 1.7  2010-10-15 09:58:24  jost
 * Code aufger�umt
 *
 * Revision 1.6  2010-10-07 19:49:23  jost
 * Hilfe in die View verlagert.
 *
 * Revision 1.5  2010-08-23 13:39:32  jost
 * Optimierung Tastatursteuerung
 *
 * Revision 1.4  2010-08-16 20:17:26  jost
 * Neu: Mahnung
 *
 * Revision 1.3  2010-08-15 19:01:29  jost
 * Rechnungen auch f�r einen vorgegebenen Zeitraum ausgeben.
 *
 * Revision 1.2  2010-08-04 10:41:16  jost
 * Prerelease Rechnung
 *
 * Revision 1.1  2010-07-25 18:43:30  jost
 * Neu: Mitgliedskonto
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.action.MitgliedDetailAction;
import de.jost_net.JVerein.gui.control.MitgliedskontoControl;
import de.jost_net.JVerein.gui.internal.buttons.Back;
import de.jost_net.JVerein.gui.menu.Mitgliedskonto2Menu;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.gui.util.LabelGroup;

public class MitgliedskontoListeView extends AbstractView
{

  @Override
  public void bind() throws Exception
  {
    GUI.getView().setTitle("Mitgliedskonten");

    final MitgliedskontoControl control = new MitgliedskontoControl(this);
    LabelGroup group = new LabelGroup(getParent(), JVereinPlugin.getI18n().tr(
        "Filter"));
    group.addInput(control.getSuchName());
    group.addLabelPair("von",
        control.getVondatum(MitgliedskontoControl.DATUM_MITGLIEDSKONTO));
    group.addLabelPair("bis",
        control.getBisdatum(MitgliedskontoControl.DATUM_MITGLIEDSKONTO));
    group.addLabelPair("Differenz", control.getDifferenz());

    control.getMitgliedskontoList(new MitgliedDetailAction(),
        new Mitgliedskonto2Menu()).paint(this.getParent());

    ButtonArea buttons2 = new ButtonArea(this.getParent(), 3);
    buttons2.addButton(new Back(false));
    buttons2.addButton(JVereinPlugin.getI18n().tr("Hilfe"),
        new DokumentationAction(), DokumentationUtil.MITGLIEDSKONTO_UEBERSICHT,
        false, "help-browser.png");
  }

  @Override
  public String getHelp()
  {
    return "<form><p><span color=\"header\" font=\"header\">Liste der Mitgliedskonto-Soll-Buchungen</span></p>"
        + "<p>Auflistung aller Mitgliedskonto-Soll-Buchungen. Die Daten k�nnen nach Datum und "
        + "Namen (auch Namensfragmente) gefiltert werden.</p></form>";
  }
}
