/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/EinstellungenView.java,v $
 * $Revision: 1.19 $
 * $Date: 2009/06/11 21:03:39 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: EinstellungenView.java,v $
 * Revision 1.19  2009/06/11 21:03:39  jost
 * Vorbereitung I18N
 *
 * Revision 1.18  2009/04/25 05:29:21  jost
 * Neu: Juristische Personen  k�nnen als Mitglied gespeichert werden.
 *
 * Revision 1.17  2009/04/13 11:40:14  jost
 * Neu: Lehrg�nge
 *
 * Revision 1.16  2009/01/20 20:09:24  jost
 * neue Icons
 *
 * Revision 1.15  2009/01/20 19:15:19  jost
 * neu: Back-Button mit Icon
 *
 * Revision 1.14  2008/12/22 21:17:26  jost
 * Zusatzabbuchung->Zusatzbetrag
 *
 * Revision 1.13  2008/11/29 13:11:38  jost
 * Neu: Konfiguration der Spalten einer Tabelle
 *
 * Revision 1.12  2008/11/11 20:05:09  jost
 * Anzeige neu gruppiert und mit Scrollbars versehen.
 *
 * Revision 1.11  2008/08/10 12:36:22  jost
 * Abbuchung -> Abrechnung
 * Vorbereitung der Rechnungserstellung
 *
 * Revision 1.10  2008/05/24 14:04:08  jost
 * Redatkionelle Änderung
 *
 * Revision 1.9  2008/05/22 06:53:43  jost
 * Buchführung: Beginn des Geschäftsjahres
 *
 * Revision 1.8  2008/03/08 19:29:22  jost
 * Neu: Externe Mitgliedsnummer
 *
 * Revision 1.7  2008/01/01 19:50:58  jost
 * Erweiterung um Hilfe-Funktion
 *
 * Revision 1.6  2008/01/01 13:14:15  jost
 * Neu: Dateinamenmuster
 *
 * Revision 1.5  2007/12/02 13:43:16  jost
 * Neu: Beitragsmodelle
 *
 * Revision 1.4  2007/12/01 17:46:46  jost
 * Wegfall Standardtab für die Suche
 *
 * Revision 1.3  2007/08/23 19:25:50  jost
 * Header korrigiert.
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.TabFolder;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.control.EinstellungControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.internal.buttons.Back;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.gui.util.Color;
import de.willuhn.jameica.gui.util.LabelGroup;
import de.willuhn.jameica.gui.util.ScrolledContainer;
import de.willuhn.jameica.gui.util.TabGroup;
import de.willuhn.util.ApplicationException;

public class EinstellungenView extends AbstractView
{
  public void bind() throws Exception
  {
    GUI.getView().setTitle(JVereinPlugin.getI18n().tr("Einstellungen"));

    final EinstellungControl control = new EinstellungControl(this);

    ScrolledContainer cont = new ScrolledContainer(getParent());
    TabFolder folder = new TabFolder(cont.getComposite(), SWT.NONE);
    folder.setLayoutData(new GridData(GridData.FILL_BOTH));
    folder.setBackground(Color.BACKGROUND.getSWTColor());

    TabGroup tabAnzeige = new TabGroup(folder, JVereinPlugin.getI18n().tr(
        "Anzeige"));
    LabelGroup group = new LabelGroup(tabAnzeige.getComposite(), JVereinPlugin
        .getI18n().tr("Anzeige"));
    group.addLabelPair(JVereinPlugin.getI18n().tr("Geburtsdatum Pflichtfeld"),
        control.getGeburtsdatumPflicht());
    group.addLabelPair(
        JVereinPlugin.getI18n().tr("Eintrittsdatum Pflichtfeld"), control
            .getEintrittsdatumPflicht());
    group.addLabelPair(JVereinPlugin.getI18n().tr(
        "Kommunikationsdaten anzeigen"), control.getKommunikationsdaten());
    group.addLabelPair(JVereinPlugin.getI18n().tr("Zusatzbetr�ge anzeigen")
        + "*", control.getZusatzbetrag());
    group.addLabelPair(JVereinPlugin.getI18n().tr("Vermerke anzeigen"), control
        .getVermerke());
    group.addLabelPair(JVereinPlugin.getI18n().tr(
        "Wiedervorlage anzeigen" + "*"), control.getWiedervorlage());
    group.addLabelPair(JVereinPlugin.getI18n().tr(
        "Kursteilnehmer anzeigen" + "*"), control.getKursteilnehmer());
    group.addLabelPair(JVereinPlugin.getI18n().tr("Lehrg�nge anzeigen" + "*"),
        control.getLehrgaenge());
    group.addLabelPair(JVereinPlugin.getI18n().tr(
        "Juristische Personen erlaubt"), control.getJuristischePersonen());
    group.addLabelPair(JVereinPlugin.getI18n().tr("externe Mitgliedsnummer"),
        control.getExterneMitgliedsnummer());
    group.addHeadline("* "
        + JVereinPlugin.getI18n().tr("�nderung erfordert Neustart"));

    TabGroup tabBeitraege = new TabGroup(folder, JVereinPlugin.getI18n().tr(
        "Beitr�ge"));
    LabelGroup groupAbu = new LabelGroup(tabBeitraege.getComposite(),
        JVereinPlugin.getI18n().tr("Beitr�ge"));
    groupAbu.addLabelPair(JVereinPlugin.getI18n().tr("Beitragsmodel"), control
        .getBeitragsmodel());

    TabGroup tabDateinamen = new TabGroup(folder, JVereinPlugin.getI18n().tr(
        "Dateinamen"));
    LabelGroup groupDatei = new LabelGroup(tabDateinamen.getComposite(),
        JVereinPlugin.getI18n().tr("Dateinamen"));
    groupDatei.addLabelPair(JVereinPlugin.getI18n().tr("Muster"), control
        .getDateinamenmuster());
    groupDatei.addText("a$ = Aufgabe, d$ = Datum, s$ = Sortierung, z$ = Zeit",
        true);

    TabGroup tabBuchfuehrung = new TabGroup(folder, JVereinPlugin.getI18n().tr(
        "Buchf�hrung"));
    LabelGroup groupBuchfuehrung = new LabelGroup(tabBuchfuehrung
        .getComposite(), JVereinPlugin.getI18n().tr("Buchf�hrung"));
    groupBuchfuehrung.addLabelPair(JVereinPlugin.getI18n().tr(
        "Beginn Gesch�ftsjahr (TT.MM.)"), control.getBeginnGeschaeftsjahr());

    TabGroup tabRechnungen = new TabGroup(folder, JVereinPlugin.getI18n().tr(
        "Rechnungen"));
    LabelGroup groupRechnungen = new LabelGroup(tabRechnungen.getComposite(),
        JVereinPlugin.getI18n().tr("Rechnungen"));
    groupRechnungen.addLabelPair(JVereinPlugin.getI18n().tr(
        "f�r Zahlungsweg Abbuchung"), control.getRechnungFuerAbbuchung());
    groupRechnungen.addLabelPair(JVereinPlugin.getI18n().tr(
        "f�r Zahlungsweg �berweisung"), control.getRechnungFuerUeberweisung());
    groupRechnungen.addLabelPair(JVereinPlugin.getI18n().tr(
        "f�r Zahlungsweg Barzahlung"), control.getRechnungFuerBarzahlung());

    TabGroup tabTabellen = new TabGroup(folder, JVereinPlugin.getI18n().tr(
        "Tabellen"));

    TabFolder folderTabellen = new TabFolder(tabTabellen.getComposite(),
        SWT.NONE);

    TabGroup tabMitglieder = new TabGroup(folderTabellen, JVereinPlugin
        .getI18n().tr("Mitglieder"));
    LabelGroup groupMitglieder = new LabelGroup(tabMitglieder.getComposite(),
        JVereinPlugin.getI18n().tr("Trefferliste Mitglieder"));
    control.getSpaltendefinitionTable(groupMitglieder.getComposite());

    ButtonArea buttons = new ButtonArea(getParent(), 3);
    buttons.addButton(new Back(false));
    buttons.addButton(JVereinPlugin.getI18n().tr("Hilfe"),
        new DokumentationAction(), DokumentationUtil.EINSTELLUNGEN, false,
        "help-browser.png");
    buttons.addButton(JVereinPlugin.getI18n().tr("speichern"), new Action()
    {
      public void handleAction(Object context) throws ApplicationException
      {
        control.handleStore();
      }
    }, null, true, "document-save.png");
  }

  public void unbind() throws ApplicationException
  {
  }
}
