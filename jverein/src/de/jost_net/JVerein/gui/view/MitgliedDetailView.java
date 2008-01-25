/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/MitgliedDetailView.java,v $
 * $Revision: 1.12 $
 * $Date: 2008/01/25 16:04:08 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: MitgliedDetailView.java,v $
 * Revision 1.12  2008/01/25 16:04:08  jost
 * Neu: Eigenschaften des Mitgliedes
 *
 * Revision 1.11  2008/01/01 19:52:33  jost
 * Erweiterung um Hilfe-Funktion
 *
 * Revision 1.10  2007/12/02 13:43:29  jost
 * Neu: Beitragsmodelle
 *
 * Revision 1.9  2007/08/22 20:44:35  jost
 * Bug #011762
 *
 * Revision 1.8  2007/05/07 19:26:01  jost
 * Neu: Wiedervorlage
 *
 * Revision 1.7  2007/03/27 19:23:24  jost
 * Familienangeh�rige anzeigen
 *
 * Revision 1.6  2007/03/25 17:01:14  jost
 * Beitragsart aufgenommen.
 *
 * Revision 1.5  2007/03/10 20:28:32  jost
 * Neu: Zahlungsweg
 *
 * Revision 1.4  2007/03/10 13:43:44  jost
 * Vermerke eingeführt.
 *
 * Revision 1.3  2007/02/23 20:27:42  jost
 * Mail- und Webadresse im Header korrigiert.
 *
 * Revision 1.2  2006/10/18 06:01:26  jost
 * �berfl�ssige Import-Statements entfernt.
 *
 * Revision 1.1  2006/09/20 15:39:10  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.TabFolder;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.gui.action.BackAction;
import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.action.MitgliedDeleteAction;
import de.jost_net.JVerein.gui.action.MitgliedDetailAction;
import de.jost_net.JVerein.gui.control.EigenschaftenControl;
import de.jost_net.JVerein.gui.control.MitgliedControl;
import de.jost_net.JVerein.gui.input.BeitragsmodelInput;
import de.jost_net.JVerein.rmi.JVereinDBService;
import de.jost_net.JVerein.rmi.Mitglied;
import de.jost_net.JVerein.server.DBSupportH2Impl;
import de.jost_net.JVerein.server.DBSupportMcKoiImpl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.gui.util.Color;
import de.willuhn.jameica.gui.util.LabelGroup;
import de.willuhn.jameica.gui.util.TabGroup;
import de.willuhn.util.ApplicationException;

public class MitgliedDetailView extends AbstractView
{

  public void bind() throws Exception
  {
    GUI.getView().setTitle("Daten des Mitgliedes");

    final MitgliedControl control = new MitgliedControl(this);

    LabelGroup grGrund = new LabelGroup(getParent(), "Grunddaten");
    grGrund.getComposite().setSize(290, 190);
    grGrund.addLabelPair("Anrede", control.getAnrede());
    grGrund.addLabelPair("Titel", control.getTitel());
    grGrund.addLabelPair("Name", control.getName());
    grGrund.addLabelPair("Vorname", control.getVorname());
    grGrund.addLabelPair("Strasse", control.getStrasse());
    grGrund.addLabelPair("PLZ", control.getPlz());
    grGrund.addLabelPair("Ort", control.getOrt());
    grGrund.addLabelPair("Geburtsdatum", control.getGeburtsdatum());
    grGrund.addLabelPair("Geschlecht", control.getGeschlecht());

    TabFolder folder = new TabFolder(getParent(), SWT.NONE);
    folder.setLayoutData(new GridData(GridData.FILL_BOTH));
    folder.setBackground(Color.BACKGROUND.getSWTColor());

    TabGroup tab1 = new TabGroup(folder, "Bankverbindung");
    tab1.addLabelPair("Zahlungsweg", control.getZahlungsweg());
    if (Einstellungen.getBeitragsmodel() == BeitragsmodelInput.MONATLICH12631)
    {
      tab1.addLabelPair("Zahlungsrhytmus", control.getZahlungsrhytmus());
    }
    tab1.addLabelPair("BLZ", control.getBlz());
    tab1.addLabelPair("Konto", control.getKonto());
    tab1.addLabelPair("Kontoinhaber", control.getKontoinhaber());

    if (Einstellungen.isKommunikationsdaten())
    {
      TabGroup tab2 = new TabGroup(folder, "Kommunikation");
      tab2.addLabelPair("Telefon priv.", control.getTelefonprivat());
      tab2.addLabelPair("Telefon dienstl.", control.getTelefondienstlich());
      tab2.addLabelPair("eMail", control.getEmail());
    }
    TabGroup tab3 = new TabGroup(folder, "Mitgliedschaft");
    tab3.addLabelPair("Eintritt", control.getEintritt());
    tab3.addLabelPair("Betragsgruppe", control.getBeitragsgruppe());
    tab3.addLabelPair("Austritt", control.getAustritt());
    tab3.addLabelPair("K�ndigung", control.getKuendigung());
    tab3.addPart(control.getFamilienverband());

    if (Einstellungen.isZusatzabbuchung())
    {
      TabGroup tab4 = new TabGroup(folder, "Zusatzabbuchung");
      control.getZusatzabbuchungenTable().paint(tab4.getComposite());
      ButtonArea buttonszus = new ButtonArea(tab4.getComposite(), 1);
      buttonszus.addButton(control.getZusatzabbuchungNeu());
    }

    if (Einstellungen.isVermerke())
    {
      TabGroup tab5 = new TabGroup(folder, "Vermerke");
      tab5.addLabelPair("Vermerk 1", control.getVermerk1());
      tab5.addLabelPair("Vermerk 2", control.getVermerk2());
    }

    if (Einstellungen.isWiedervorlage())
    {
      TabGroup tab6 = new TabGroup(folder, "Wiedervorlage");
      control.getWiedervorlageTable().paint(tab6.getComposite());
      ButtonArea buttonswvl = new ButtonArea(tab6.getComposite(), 1);
      buttonswvl.addButton(control.getWiedervorlageNeu());
    }
    if (!JVereinDBService.SETTINGS.getString("database.driver",
        DBSupportH2Impl.class.getName()).equals(
        DBSupportMcKoiImpl.class.getName()))
    {
      TabGroup tab7 = new TabGroup(folder, "Eigenschaften");
      EigenschaftenControl econtrol = new EigenschaftenControl(this,
          (Mitglied) control.getCurrentObject());
      econtrol.getEigenschaftenTable().paint(tab7.getComposite());
      tab7.addText("Rechter Mausklick f�r Funktionen", false);
    }

    ButtonArea buttons = new ButtonArea(getParent(), 5);
    buttons.addButton("<< Zur�ck", new BackAction());
    buttons.addButton("Hilfe", new DokumentationAction(),
        DokumentationUtil.mitglied);
    buttons.addButton("Neu", new MitgliedDetailAction());
    buttons.addButton("L�schen", new MitgliedDeleteAction(), control
        .getCurrentObject());
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
