/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/AuswertungMitgliedView.java,v $
 * $Revision: 1.9 $
 * $Date: 2009/03/26 21:02:08 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: AuswertungMitgliedView.java,v $
 * Revision 1.9  2009/03/26 21:02:08  jost
 * Neu: Reports - Erste Version
 *
 * Revision 1.8  2009/01/20 20:09:24  jost
 * neue Icons
 *
 * Revision 1.7  2009/01/20 19:14:35  jost
 * neu: Back-Button mit Icon
 *
 * Revision 1.6  2008/11/11 20:47:48  jost
 * 2spaltiges Layout und Selektion nach Geschlecht
 *
 * Revision 1.5  2008/05/24 14:04:08  jost
 * Redatkionelle Änderung
 *
 * Revision 1.4  2008/01/27 09:42:22  jost
 * Erweiterung der Auswertung um Eigenschaften
 *
 * Revision 1.3  2008/01/01 19:48:12  jost
 * Erweiterung um Hilfe-Funktion
 *
 * Revision 1.2  2007/02/23 20:27:28  jost
 * Mail- und Webadresse im Header korrigiert.
 *
 * Revision 1.1  2006/09/20 15:39:10  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.control.MitgliedControl;
import de.jost_net.JVerein.rmi.JVereinDBService;
import de.jost_net.JVerein.server.DBSupportH2Impl;
import de.jost_net.JVerein.server.DBSupportMcKoiImpl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.input.SelectInput;
import de.willuhn.jameica.gui.internal.buttons.Back;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.gui.util.ColumnLayout;
import de.willuhn.jameica.gui.util.LabelGroup;
import de.willuhn.jameica.gui.util.SimpleContainer;
import de.willuhn.util.ApplicationException;

public class AuswertungMitgliedView extends AbstractView
{
  public void bind() throws Exception
  {
    GUI.getView().setTitle("Auswertung Mitgliedsdaten");

    final MitgliedControl control = new MitgliedControl(this);

    LabelGroup group = new LabelGroup(getParent(), "Filter");

    ColumnLayout cl = new ColumnLayout(group.getComposite(), 2);
    SimpleContainer left = new SimpleContainer(cl.getComposite());

    if (!JVereinDBService.SETTINGS.getString("database.driver",
        DBSupportH2Impl.class.getName()).equals(
        DBSupportMcKoiImpl.class.getName()))
    {
      left.addLabelPair("Eigenschaften", control.getEigenschaftenAuswahl());
    }

    left.addLabelPair("Geburtsdatum von", control.getGeburtsdatumvon());
    left.addLabelPair("Geburtsdatum bis", control.getGeburtsdatumbis());

    SelectInput inpGeschlecht = control.getGeschlecht();
    inpGeschlecht.setMandatory(false);
    left.addLabelPair("Geschlecht", inpGeschlecht);

    SimpleContainer right = new SimpleContainer(cl.getComposite());
    right.addLabelPair("Eintritt von", control.getEintrittvon());
    right.addLabelPair("Eintritt bis", control.getEintrittbis());

    right.addLabelPair("Austritt von", control.getAustrittvon());
    right.addLabelPair("Austritt bis", control.getAustrittbis());

    right.addLabelPair("Beitragsgruppe", control.getBeitragsgruppeAusw());

    right.addLabelPair("Ausgabe", control.getAusgabe());
    right.addLabelPair("Sortierung", control.getSortierung());

    ButtonArea buttons = new ButtonArea(getParent(), 4);

    buttons.addButton(new Back(false));
    buttons.addButton("Hilfe", new DokumentationAction(),
        DokumentationUtil.AUSWERTUNGMITGLIEDER, false, "help-browser.png");
    buttons.addButton(control.getStartAuswertungButton());
   }

  public void unbind() throws ApplicationException
  {
  }
}
