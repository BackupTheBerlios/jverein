/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/AbbuchungView.java,v $
 * $Revision: 1.33 $
 * $Date: 2011/10/01 21:45:51 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.AbrechnunslaufListAction;
import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.control.AbbuchungControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.gui.util.LabelGroup;
import de.willuhn.util.ApplicationException;

public class AbbuchungView extends AbstractView
{

  @Override
  public void bind() throws Exception
  {
    if (Einstellungen.getEinstellung().getName() == null
        || Einstellungen.getEinstellung().getName().length() == 0
        || Einstellungen.getEinstellung().getKonto() == null
        || Einstellungen.getEinstellung().getKonto().length() == 0)
    {
      throw new ApplicationException(
          JVereinPlugin
              .getI18n()
              .tr("Name des Vereins oder Bankverbindung fehlt.Bitte unter Administration|Einstellungen erfassen."));
    }

    GUI.getView().setTitle("Abrechnung");

    final AbbuchungControl control = new AbbuchungControl(this);

    LabelGroup group = new LabelGroup(getParent(), JVereinPlugin.getI18n().tr(
        "Parameter"));
    group.addLabelPair(JVereinPlugin.getI18n().tr("Modus"),
        control.getAbbuchungsmodus());
    group.addLabelPair(JVereinPlugin.getI18n().tr("Stichtag"),
        control.getStichtag());
    group.addLabelPair(JVereinPlugin.getI18n().tr("Von Eingabedatum"),
        control.getVondatum());
    group.addLabelPair(
        JVereinPlugin.getI18n().tr("Zahlungsgrund f�r Beitr�ge"),
        control.getZahlungsgrund());
    group.addLabelPair(JVereinPlugin.getI18n().tr("Zusatzbetr�ge"),
        control.getZusatzbetrag());
    if (!Einstellungen.getEinstellung().getZusatzbetrag())
    {
      control.getZusatzbetrag().setEnabled(false);
    }
    group.addLabelPair(JVereinPlugin.getI18n().tr("Kursteilnehmer"),
        control.getKursteilnehmer());
    group.addLabelPair(JVereinPlugin.getI18n().tr("Kompakte Abbuchung"),
        control.getKompakteAbbuchung());
    group.addLabelPair(JVereinPlugin.getI18n().tr("Dtaus-Datei drucken"),
        control.getDtausPrint());

    if (!Einstellungen.getEinstellung().getKursteilnehmer())
    {
      control.getKursteilnehmer().setEnabled(false);
    }
    group.addLabelPair(JVereinPlugin.getI18n().tr("Abbuchungsausgabe"),
        control.getAbbuchungsausgabe());
    group.addSeparator();
    group
        .addText(
            JVereinPlugin
                .getI18n()
                .tr("*) f�r die Berechnung, ob ein Mitglied bereits eingetreten oder ausgetreten ist. "
                    + "�blicherweise 1.1. des Jahres."), true);

    ButtonArea buttons = new ButtonArea(this.getParent(), 3);
    if (Einstellungen.getEinstellung().getMitgliedskonto())
    {
      buttons.addButton("R�ckg�ngig", new AbrechnunslaufListAction(), null,
          false, "edit-undo.png");
    }
    buttons.addButton(JVereinPlugin.getI18n().tr("Hilfe"),
        new DokumentationAction(), DokumentationUtil.ABRECHNUNG, false,
        "help-browser.png");
    buttons.addButton(control.getStartButton());
  }

  @Override
  public String getHelp()
  {
    return "<form><p><span color=\"header\" font=\"header\">Abbuchung</span> </p>"
        + "<p>Zun�chst ist der Modus auszuw�hlen. Die Auswahlm�glichkeiten "
        + "richten sich nach dem ausgew�hlten Beitragsmodell (siehe Einstellungen).</p>"
        + "<p>Der Stichtag wird  zur Pr�fung herangezogen, ob die Mitgliedschaft schon/noch besteht "
        + "und damit eine Abrechnung generiert muss. Liegt das Eintrittsdatum vor dem Stichtag und "
        + "das Austrittsdatum nach dem Stichtag, wird das Mitglied ber�cksichtigt.</p>"
        + "<p>Der angegebene Verwendungszweck wird bei allen Mitgliedsbeitrags-Buchungen "
        + "eingetragen. </p>"
        + "<p>  Es kann angegeben werden, ob Zusatzabbuchungen und Kursteilnehmer ber�cksichtigt "
        + " werden sollen.</p></form>";
  }
}
