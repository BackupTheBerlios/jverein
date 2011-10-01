/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/EinstellungenView.java,v $
 * $Revision: 1.50 $
 * $Date: 2011/10/01 21:45:49 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
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
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.gui.util.Color;
import de.willuhn.jameica.gui.util.ColumnLayout;
import de.willuhn.jameica.gui.util.LabelGroup;
import de.willuhn.jameica.gui.util.ScrolledContainer;
import de.willuhn.jameica.gui.util.SimpleContainer;
import de.willuhn.jameica.gui.util.TabGroup;

public class EinstellungenView extends AbstractView
{

  @Override
  public void bind() throws Exception
  {
    GUI.getView().setTitle(JVereinPlugin.getI18n().tr("Einstellungen"));

    final EinstellungControl control = new EinstellungControl(this);

    ScrolledContainer cont = new ScrolledContainer(getParent());
    TabFolder folder = new TabFolder(cont.getComposite(), SWT.NONE);
    folder.setLayoutData(new GridData(GridData.FILL_BOTH));
    folder.setBackground(Color.BACKGROUND.getSWTColor());

    TabGroup tabAllgemein = new TabGroup(folder, JVereinPlugin.getI18n().tr(
        "Allgemein"));
    tabAllgemein.addHeadline("Allgemein");
    tabAllgemein.addLabelPair(JVereinPlugin.getI18n().tr("Name"),
        control.getName(true));
    tabAllgemein.addLabelPair(JVereinPlugin.getI18n().tr("Stra�e"),
        control.getStrasse());
    tabAllgemein.addLabelPair(JVereinPlugin.getI18n().tr("PLZ"),
        control.getPlz());
    tabAllgemein.addLabelPair(JVereinPlugin.getI18n().tr("Ort"),
        control.getOrt());
    tabAllgemein.addLabelPair(JVereinPlugin.getI18n().tr("Bankleitzahl"),
        control.getBlz());
    tabAllgemein.addLabelPair(JVereinPlugin.getI18n().tr("Konto"),
        control.getKonto());
    tabAllgemein.addHeadline("Spendenbescheinigung");
    tabAllgemein.addLabelPair(JVereinPlugin.getI18n().tr("Finanzamt"),
        control.getFinanzamt());
    tabAllgemein.addLabelPair(JVereinPlugin.getI18n().tr("Steuernummer"),
        control.getSteuernummer());
    tabAllgemein.addLabelPair(JVereinPlugin.getI18n().tr("Bescheiddatum"),
        control.getBescheiddatum());
    tabAllgemein.addLabelPair(JVereinPlugin.getI18n()
        .tr("vorl�ufiger Bescheid"), control.getVorlaeufig());
    tabAllgemein.addLabelPair(JVereinPlugin.getI18n().tr("Vorl�ufig ab"),
        control.getVorlaeufigab());
    tabAllgemein.addLabelPair(JVereinPlugin.getI18n().tr("beg�nstigter Zweck"),
        control.getBeguenstigterzweck());
    tabAllgemein.addLabelPair(
        JVereinPlugin.getI18n().tr(
            "Mitgliedsbeitr�ge d�rfen bescheinigt werden"),
        control.getMitgliedsbetraege());

    TabGroup tabAnzeige = new TabGroup(folder, JVereinPlugin.getI18n().tr(
        "Anzeige"));

    LabelGroup group = new LabelGroup(tabAnzeige.getComposite(), JVereinPlugin
        .getI18n().tr("Anzeige"));
    ColumnLayout cols1 = new ColumnLayout(group.getComposite(), 2);
    SimpleContainer left = new SimpleContainer(cols1.getComposite());

    left.addLabelPair(JVereinPlugin.getI18n().tr("Geburtsdatum Pflichtfeld"),
        control.getGeburtsdatumPflicht());
    left.addLabelPair(JVereinPlugin.getI18n().tr("Eintrittsdatum Pflichtfeld"),
        control.getEintrittsdatumPflicht());
    left.addLabelPair(JVereinPlugin.getI18n().tr("Sterbedatum"),
        control.getSterbedatum());
    left.addLabelPair(JVereinPlugin.getI18n()
        .tr("Kommunikationsdaten anzeigen"), control.getKommunikationsdaten());
    left.addLabelPair(JVereinPlugin.getI18n().tr("Zusatzbetr�ge anzeigen")
        + "*", control.getZusatzbetrag());
    left.addLabelPair(JVereinPlugin.getI18n().tr("Vermerke anzeigen"),
        control.getVermerke());
    left.addLabelPair(JVereinPlugin.getI18n()
        .tr("Wiedervorlage anzeigen" + "*"), control.getWiedervorlage());
    left.addLabelPair(JVereinPlugin.getI18n().tr(
        "Kursteilnehmer anzeigen" + "*"), control.getKursteilnehmer());
    left.addLabelPair(JVereinPlugin.getI18n().tr("Lehrg�nge anzeigen" + "*"),
        control.getLehrgaenge());
    left.addLabelPair(JVereinPlugin.getI18n()
        .tr("Juristische Personen erlaubt"), control.getJuristischePersonen());
    left.addLabelPair(JVereinPlugin.getI18n().tr("Mitgliedskonten *"),
        control.getMitgliedskonto());
    left.addLabelPair(JVereinPlugin.getI18n().tr("Mitgliedsfoto *"),
        control.getMitgliedfoto());
    SimpleContainer right = new SimpleContainer(cols1.getComposite());
    right.addLabelPair(JVereinPlugin.getI18n().tr("zus�tzliche Adressen *"),
        control.getZusatzadressen());
    right.addLabelPair(JVereinPlugin.getI18n().tr("Auslandsadressen *"),
        control.getAuslandsadressen());
    right.addLabelPair(JVereinPlugin.getI18n().tr("Arbeitseinsatz *"),
        control.getArbeitseinsatz());
    right.addLabelPair(JVereinPlugin.getI18n().tr("Dokumentenspeicherung *"),
        control.getDokumentenspeicherung());
    right.addLabelPair(JVereinPlugin.getI18n().tr("individuelle Beitr�ge *"),
        control.getIndividuelleBeitraege());
    right.addLabelPair(JVereinPlugin.getI18n().tr("externe Mitgliedsnummer"),
        control.getExterneMitgliedsnummer());
    right
        .addLabelPair(
            JVereinPlugin.getI18n().tr(
                "Verz�gerungszeit Suche (in Millisekunden)"),
            control.getDelaytime());
    right.addHeadline("* "
        + JVereinPlugin.getI18n().tr("�nderung erfordert Neustart"));

    TabGroup tabBeitraege = new TabGroup(folder, JVereinPlugin.getI18n().tr(
        "Beitr�ge"));
    LabelGroup groupAbu = new LabelGroup(tabBeitraege.getComposite(),
        JVereinPlugin.getI18n().tr("Beitr�ge"));
    groupAbu.addLabelPair(JVereinPlugin.getI18n().tr("Beitragsmodel"),
        control.getBeitragsmodel());
    groupAbu.addInput(control.getZahlungsrhytmus());
    groupAbu.addInput(control.getZahlungsweg());

    TabGroup tabDateinamen = new TabGroup(folder, JVereinPlugin.getI18n().tr(
        "Dateinamen"));
    LabelGroup groupDatei = new LabelGroup(tabDateinamen.getComposite(),
        JVereinPlugin.getI18n().tr("Dateinamen"));
    groupDatei.addLabelPair(JVereinPlugin.getI18n().tr("Muster"),
        control.getDateinamenmuster());
    groupDatei.addText("a$ = Aufgabe, d$ = Datum, s$ = Sortierung, z$ = Zeit",
        true);

    TabGroup tabBuchfuehrung = new TabGroup(folder, JVereinPlugin.getI18n().tr(
        "Buchf�hrung"));
    LabelGroup groupBuchfuehrung = new LabelGroup(
        tabBuchfuehrung.getComposite(), JVereinPlugin.getI18n().tr(
            "Buchf�hrung"));
    groupBuchfuehrung.addLabelPair(
        JVereinPlugin.getI18n().tr("Beginn Gesch�ftsjahr (TT.MM.)"),
        control.getBeginnGeschaeftsjahr());

    TabGroup tabRechnungen = new TabGroup(folder, JVereinPlugin.getI18n().tr(
        "Rechnungen"));
    LabelGroup groupRechnungen = new LabelGroup(tabRechnungen.getComposite(),
        JVereinPlugin.getI18n().tr("Rechnungen"));
    groupRechnungen.addLabelPair(JVereinPlugin.getI18n().tr("Text Abbuchung"),
        control.getRechnungTextAbbuchung());
    groupRechnungen.addLabelPair(
        JVereinPlugin.getI18n().tr("Text �berweisung"),
        control.getRechnungTextUeberweisung());
    groupRechnungen.addLabelPair(JVereinPlugin.getI18n().tr("Text Bar"),
        control.getRechnungTextBar());

    TabGroup tabTabellen = new TabGroup(folder, JVereinPlugin.getI18n().tr(
        "Tabellen"));

    TabFolder folderTabellen = new TabFolder(tabTabellen.getComposite(),
        SWT.NONE);

    TabGroup tabMitglieder = new TabGroup(folderTabellen, JVereinPlugin
        .getI18n().tr("Mitglieder"));
    LabelGroup groupMitglieder = new LabelGroup(tabMitglieder.getComposite(),
        JVereinPlugin.getI18n().tr("Trefferliste Mitglieder"));
    control.getSpaltendefinitionTable(groupMitglieder.getComposite());

    TabGroup tabMail = new TabGroup(folder, JVereinPlugin.getI18n().tr("Mail"));
    LabelGroup groupMail = new LabelGroup(tabMail.getComposite(), JVereinPlugin
        .getI18n().tr("Mail"));
    groupMail.addLabelPair("Server", control.getSmtpServer());
    groupMail.addLabelPair("Port", control.getSmtpPort());
    groupMail.addLabelPair("Benutzer", control.getSmtpAuthUser());
    groupMail.addLabelPair("Passwort", control.getSmtpAuthPwd());
    groupMail.addLabelPair("Absenderadresse", control.getSmtpFromAddress());
    groupMail.addLabelPair("SSL verwenden", control.getSmtpSsl());
    groupMail.addLabelPair("Starttls verwenden", control.getSmtpStarttls());

    TabGroup tabStatistik = new TabGroup(folder, JVereinPlugin.getI18n().tr(
        "Statistik"));
    LabelGroup grStatistik = new LabelGroup(tabStatistik.getComposite(),
        JVereinPlugin.getI18n().tr("Statistik"));
    grStatistik.addLabelPair(JVereinPlugin.getI18n().tr("Altersgruppen"),
        control.getAltersgruppen());
    grStatistik.addLabelPair(JVereinPlugin.getI18n().tr("Jubil�en"),
        control.getJubilaeen());
    grStatistik.addLabelPair(JVereinPlugin.getI18n().tr("Altersjubil�en"),
        control.getAltersjubilaeen());

    ButtonArea buttons = new ButtonArea(getParent(), 2);
    buttons.addButton(JVereinPlugin.getI18n().tr("Hilfe"),
        new DokumentationAction(), DokumentationUtil.EINSTELLUNGEN, false,
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
    return "<form><p><span color=\"header\" font=\"header\">Einstellungen</span></p>"
        + "<p>Anzeige: In diesem Bereich kann gesteuert werden, welche Datenfelder "
        + "angezeigt werden.</p>"
        + "Beitragsmodell:"
        + "<li>j�hrlich fester Beitrag</li>"
        + "<li>halbj�hrlich fester Beitrag</li>"
        + "<li>viertelj�hrlich fester Beitrag</li>"
        + "<li>monatlich fester Beitrag</li>"
        + "<li>Monatlicher Beitrag mit j�hrlicher, halbj�hrlicher, viertelj�hrlicher oder monatlicher Zahlungsweise.</li>"
        + "</form>";
  }
}
