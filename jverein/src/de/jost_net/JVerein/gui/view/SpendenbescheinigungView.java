/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/SpendenbescheinigungView.java,v $
 * $Revision: 1.13 $
 * $Date: 2011/03/13 13:47:29 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: SpendenbescheinigungView.java,v $
 * Revision 1.13  2011/03/13 13:47:29  jost
 * Neu: Standardformular und Sachspenden.
 *
 * Revision 1.12  2011-01-15 09:46:48  jost
 * Tastatursteuerung wegen Problemen mit Jameica/Hibiscus wieder entfernt.
 *
 * Revision 1.11  2010-10-15 09:58:25  jost
 * Code aufger�umt
 *
 * Revision 1.10  2010-10-07 19:49:23  jost
 * Hilfe in die View verlagert.
 *
 * Revision 1.9  2010-08-23 13:39:32  jost
 * Optimierung Tastatursteuerung
 *
 * Revision 1.8  2010/01/03 08:58:53  jost
 * Neu-Button aufgenommen.
 *
 * Revision 1.7  2009/07/24 20:22:53  jost
 * Focus auf erstes Feld setzen.
 *
 * Revision 1.6  2009/06/11 21:03:39  jost
 * Vorbereitung I18N
 *
 * Revision 1.5  2009/01/27 18:51:03  jost
 * *** empty log message ***
 *
 * Revision 1.4  2009/01/26 18:48:09  jost
 * Neu: Ersatz Aufwendungen
 *
 * Revision 1.3  2009/01/20 20:09:24  jost
 * neue Icons
 *
 * Revision 1.2  2009/01/20 19:15:19  jost
 * neu: Back-Button mit Icon
 *
 * Revision 1.1  2008/07/18 20:15:38  jost
 * Neu: Spendenbescheinigung
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.action.SpendenbescheinigungAction;
import de.jost_net.JVerein.gui.control.SpendenbescheinigungControl;
import de.jost_net.JVerein.gui.internal.buttons.Back;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.gui.util.ColumnLayout;
import de.willuhn.jameica.gui.util.ScrolledContainer;
import de.willuhn.jameica.gui.util.SimpleContainer;

public class SpendenbescheinigungView extends AbstractView
{

  @Override
  public void bind() throws Exception
  {
    GUI.getView().setTitle(JVereinPlugin.getI18n().tr("Spendenbescheinigung"));

    final SpendenbescheinigungControl control = new SpendenbescheinigungControl(
        this);
    ScrolledContainer scrolled = new ScrolledContainer(getParent());

    ColumnLayout cols1 = new ColumnLayout(scrolled.getComposite(), 2);
    SimpleContainer left = new SimpleContainer(cols1.getComposite());

    left.addHeadline("Spendenart");
    left.addLabelPair(JVereinPlugin.getI18n().tr("Spendenart"),
        control.getSpendenart());

    left.addHeadline(JVereinPlugin.getI18n().tr("Spender"));
    left.addLabelPair(JVereinPlugin.getI18n().tr("Zeile 1"),
        control.getZeile1(true));
    left.addLabelPair(JVereinPlugin.getI18n().tr("Zeile 2"),
        control.getZeile2());
    left.addLabelPair(JVereinPlugin.getI18n().tr("Zeile 3"),
        control.getZeile3());
    left.addLabelPair(JVereinPlugin.getI18n().tr("Zeile 4"),
        control.getZeile4());
    left.addLabelPair(JVereinPlugin.getI18n().tr("Zeile 5"),
        control.getZeile5());
    left.addLabelPair(JVereinPlugin.getI18n().tr("Zeile 6"),
        control.getZeile6());
    left.addLabelPair(JVereinPlugin.getI18n().tr("Zeile 7"),
        control.getZeile7());

    left.addHeadline(JVereinPlugin.getI18n().tr("Datum"));
    left.addLabelPair(JVereinPlugin.getI18n().tr("Spende"),
        control.getSpendedatum());
    left.addLabelPair(JVereinPlugin.getI18n().tr("Bescheinigung"),
        control.getBescheinigungsdatum());

    SimpleContainer right = new SimpleContainer(cols1.getComposite());

    right.addHeadline(JVereinPlugin.getI18n().tr("Betrag"));
    right.addLabelPair(JVereinPlugin.getI18n().tr("Betrag"),
        control.getBetrag());

    right.addHeadline(JVereinPlugin.getI18n().tr("Ersatz f�r Aufwendungen"));
    right.addLabelPair(JVereinPlugin.getI18n().tr("Ersatz f�r Aufwendungen"),
        control.getErsatzAufwendungen());

    right.addHeadline(JVereinPlugin.getI18n().tr("Formular"));
    right.addLabelPair(JVereinPlugin.getI18n().tr("Formular"),
        control.getFormular());

    right.addHeadline(JVereinPlugin.getI18n().tr(
        "Zusatzinformationen Sachspenden"));
    right.addLabelPair(JVereinPlugin.getI18n().tr("Bezeichnung Sachzuwendung"),
        control.getBezeichnungSachzuwendung());
    right.addLabelPair(JVereinPlugin.getI18n().tr("Herkunft"),
        control.getHerkunftSpende());
    right.addLabelPair(JVereinPlugin.getI18n().tr("Unterlagen Wertermittlung"),
        control.getUnterlagenWertermittlung());

    ButtonArea buttons = new ButtonArea(getParent(), 6);
    buttons.addButton(new Back(false));
    buttons.addButton(JVereinPlugin.getI18n().tr("Hilfe"),
        new DokumentationAction(), DokumentationUtil.SPENDENBESCHEINIGUNG,
        false, "help-browser.png");
    buttons.addButton(control.getPDFStandardButton());
    buttons.addButton(control.getPDFIndividuellButton());
    buttons.addButton(JVereinPlugin.getI18n().tr("neu"),
        new SpendenbescheinigungAction(), null, false, "document-new.png");
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
    return "<form><p><span color=\"header\" font=\"header\">Spendenbescheinigung</span></p>"
        + "</form>";
  }
}
