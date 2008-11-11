/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/EinstellungenView.java,v $
 * $Revision: 1.12 $
 * $Date: 2008/11/11 20:05:09 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: EinstellungenView.java,v $
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

import de.jost_net.JVerein.gui.action.BackAction;
import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.control.EinstellungControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
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
    GUI.getView().setTitle("Einstellungen");

    final EinstellungControl control = new EinstellungControl(this);

    ScrolledContainer cont = new ScrolledContainer(getParent());
    TabFolder folder = new TabFolder(cont.getComposite(), SWT.NONE);
    folder.setLayoutData(new GridData(GridData.FILL_BOTH));
    folder.setBackground(Color.BACKGROUND.getSWTColor());

    TabGroup tabAnzeige = new TabGroup(folder, "Anzeige");
    LabelGroup group = new LabelGroup(tabAnzeige.getComposite(), "Anzeige");
    group.addLabelPair("Geburtsdatum Pflichtfeld", control
        .getGeburtsdatumPflicht());
    group.addLabelPair("Eintrittsdatum Pflichtfeld", control
        .getEintrittsdatumPflicht());
    group.addLabelPair("Kommunikationsdaten anzeigen", control
        .getKommunikationsdaten());
    group.addLabelPair("Zusatzabbuchungen anzeigen *", control
        .getZusatzabbuchung());
    group.addLabelPair("Vermerke anzeigen", control.getVermerke());
    group.addLabelPair("Wiedervorlage anzeigen *", control.getWiedervorlage());
    group
        .addLabelPair("Kursteilnehmer anzeigen *", control.getKursteilnehmer());
    group.addLabelPair("externe Mitgliedsnummer", control
        .getExterneMitgliedsnummer());
    group.addHeadline("* �nderung erfordert Neustart");

    TabGroup tabBeitraege = new TabGroup(folder, "Beitr�ge");
    LabelGroup groupAbu = new LabelGroup(tabBeitraege.getComposite(),
        "Beitr�ge");
    groupAbu.addLabelPair("Beitragsmodel", control.getBeitragsmodel());

    TabGroup tabDateinamen = new TabGroup(folder, "Dateinamen");
    LabelGroup groupDatei = new LabelGroup(tabDateinamen.getComposite(),
        "Dateinamen");
    groupDatei.addLabelPair("Muster", control.getDateinamenmuster());
    groupDatei.addText("a$ = Aufgabe, d$ = Datum, s$ = Sortierung, z$ = Zeit",
        true);

    TabGroup tabBuchfuehrung = new TabGroup(folder, "Buchf�hrung");
    LabelGroup groupBuchfuehrung = new LabelGroup(tabBuchfuehrung
        .getComposite(), "Buchf�hrung");
    groupBuchfuehrung.addLabelPair("Beginn Gesch�ftsjahr (TT.MM.)", control
        .getBeginnGeschaeftsjahr());

    TabGroup tabRechnungen = new TabGroup(folder, "Rechnungen");
    LabelGroup groupRechnungen = new LabelGroup(tabRechnungen.getComposite(),
        "Rechnungen");
    groupRechnungen.addLabelPair("f�r Zahlungsweg Abbuchung", control
        .getRechnungFuerAbbuchung());
    groupRechnungen.addLabelPair("f�r Zahlungsweg �berweisung", control
        .getRechnungFuerUeberweisung());
    groupRechnungen.addLabelPair("f�r Zahlungsweg Barzahlung", control
        .getRechnungFuerBarzahlung());

    ButtonArea buttons = new ButtonArea(getParent(), 3);
    buttons.addButton("<< Zur�ck", new BackAction());
    buttons.addButton("Hilfe", new DokumentationAction(),
        DokumentationUtil.EINSTELLUNGEN);
    buttons.addButton("Speichern", new Action()
    {
      public void handleAction(Object context) throws ApplicationException
      {
        control.handleStore();
      }
    }, null, true);
  }

  public void unbind() throws ApplicationException
  {
  }
}
