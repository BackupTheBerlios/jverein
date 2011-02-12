/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/AbbuchungView.java,v $
 * $Revision: 1.28 $
 * $Date: 2011/02/12 09:36:03 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: AbbuchungView.java,v $
 * Revision 1.28  2011/02/12 09:36:03  jost
 * Stammdaten -> Einstellungen
 *
 * Revision 1.27  2011-01-15 09:46:49  jost
 * Tastatursteuerung wegen Problemen mit Jameica/Hibiscus wieder entfernt.
 *
 * Revision 1.26  2010-10-15 09:58:24  jost
 * Code aufger�umt
 *
 * Revision 1.25  2010-10-07 19:49:24  jost
 * Hilfe in die View verlagert.
 *
 * Revision 1.24  2010-08-23 13:38:02  jost
 * Optimierung Tastatursteuerung
 *
 * Revision 1.23  2010-07-25 18:41:49  jost
 * Neu: Mitgliedskonto
 *
 * Revision 1.22  2010/05/18 20:20:06  jost
 * Anpassung Klassenname
 *
 * Revision 1.21  2010/04/25 13:55:04  jost
 * Vorarbeiten Mitgliedskonto
 *
 * Revision 1.20  2009/06/11 21:03:39  jost
 * Vorbereitung I18N
 *
 * Revision 1.19  2009/01/27 18:50:31  jost
 * Import-Statement korrigiert
 *
 * Revision 1.18  2009/01/20 20:09:24  jost
 * neue Icons
 *
 * Revision 1.17  2009/01/20 19:13:51  jost
 * neu: Back-Button mit Icon
 *
 * Revision 1.16  2008/12/22 21:17:12  jost
 * Zusatzabbuchung->Zusatzbetrag
 *
 * Revision 1.15  2008/11/16 16:57:51  jost
 * Speicherung der Einstellung von Property-Datei in die Datenbank verschoben.
 *
 * Revision 1.14  2008/08/10 12:36:02  jost
 * Abbuchung -> Abrechnung
 * Vorbereitung der Rechnungserstellung
 *
 * Revision 1.13  2008/05/24 14:04:08  jost
 * Redatkionelle Änderung
 *
 * Revision 1.12  2008/01/31 19:39:37  jost
 * Berücksichtigung eines Stichtages für die Abbuchung
 *
 * Revision 1.11  2008/01/01 19:47:16  jost
 * Erweiterung um Hilfe-Funktion
 *
 * Revision 1.10  2007/12/26 18:13:19  jost
 * Lastschriften können jetzt als Einzellastschriften oder Sammellastschriften direkt in Hibuscus verbucht werden.
 *
 * Revision 1.9  2007/12/21 13:35:58  jost
 * Ausgabe der DTAUS-Datei im PDF-Format
 *
 * Revision 1.8  2007/12/02 13:41:18  jost
 * überflüssiges Import-Statement entfernt.
 *
 * Revision 1.7  2007/08/22 20:44:10  jost
 * Bug #011762
 *
 * Revision 1.6  2007/07/20 20:15:40  jost
 * Bessere Fehlermeldung
 *
 * Revision 1.5  2007/07/06 11:37:18  jost
 * Zur Kompatibilit�t: �nderung der Plausi.
 *
 * Revision 1.4  2007/02/25 19:13:05  jost
 * Neu: Kursteilnehmer
 *
 * Revision 1.3  2007/02/23 20:27:28  jost
 * Mail- und Webadresse im Header korrigiert.
 *
 * Revision 1.2  2006/10/21 09:18:54  jost
 * Zus�tzliche Plausi.
 *
 * Revision 1.1  2006/09/20 15:39:10  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.AbrechnunslaufListAction;
import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.control.AbbuchungControl;
import de.jost_net.JVerein.gui.internal.buttons.Back;
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

    ButtonArea buttons = new ButtonArea(this.getParent(), 4);
    buttons.addButton(new Back(false));
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
