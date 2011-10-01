/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/BuchungView.java,v $
 * $Revision: 1.26 $
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
import de.jost_net.JVerein.gui.action.BuchungNeuAction;
import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.control.BuchungsControl;
import de.jost_net.JVerein.gui.control.DokumentControl;
import de.jost_net.JVerein.rmi.Buchung;
import de.jost_net.JVerein.rmi.BuchungDokument;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.gui.util.LabelGroup;
import de.willuhn.jameica.gui.util.ScrolledContainer;

public class BuchungView extends AbstractView
{

  @Override
  public void bind() throws Exception
  {
    GUI.getView().setTitle(JVereinPlugin.getI18n().tr("Buchung"));

    final BuchungsControl control = new BuchungsControl(this);

    ScrolledContainer scrolled = new ScrolledContainer(getParent());

    LabelGroup grKontoauszug = new LabelGroup(scrolled.getComposite(),
        JVereinPlugin.getI18n().tr("Buchung"));
    grKontoauszug.addLabelPair(JVereinPlugin.getI18n().tr("Buchungsnummer"),
        control.getID());
    grKontoauszug.addLabelPair(JVereinPlugin.getI18n().tr("Umsatz-ID"),
        control.getUmsatzid());
    grKontoauszug.addLabelPair(JVereinPlugin.getI18n().tr("Konto"),
        control.getKonto(true));
    grKontoauszug.addLabelPair(JVereinPlugin.getI18n().tr("Name"),
        control.getName());
    grKontoauszug.addLabelPair(JVereinPlugin.getI18n().tr("Betrag"),
        control.getBetrag());
    grKontoauszug.addLabelPair(JVereinPlugin.getI18n().tr("Verwendungszweck"),
        control.getZweck());
    grKontoauszug.addLabelPair(
        JVereinPlugin.getI18n().tr("Verwendungszweck 2"), control.getZweck2());
    grKontoauszug.addLabelPair(JVereinPlugin.getI18n().tr("Datum"),
        control.getDatum());
    grKontoauszug.addLabelPair(JVereinPlugin.getI18n().tr("Art"),
        control.getArt());
    if (Einstellungen.getEinstellung().getMitgliedskonto())
    {
      grKontoauszug.addLabelPair(JVereinPlugin.getI18n().tr("Mitgliedskonto"),
          control.getMitgliedskonto());
    }
    grKontoauszug.addLabelPair(JVereinPlugin.getI18n().tr("Kommentar"),
        control.getKommentar());

    LabelGroup grBuchungsinfos = new LabelGroup(scrolled.getComposite(),
        JVereinPlugin.getI18n().tr("Buchungsinfos"));
    grBuchungsinfos.addLabelPair(JVereinPlugin.getI18n().tr("Buchungsart"),
        control.getBuchungsart());
    grBuchungsinfos.addLabelPair(JVereinPlugin.getI18n().tr("Auszugsnummer"),
        control.getAuszugsnummer());
    grBuchungsinfos.addLabelPair(JVereinPlugin.getI18n().tr("Blattnummer"),
        control.getBlattnummer());

    if (JVereinPlugin.isArchiveServiceActive())
    {
      Buchung bu = (Buchung) control.getCurrentObject();
      if (!bu.isNewObject())
      {
        LabelGroup grDokument = new LabelGroup(scrolled.getComposite(),
            "Dokumente");
        BuchungDokument budo = (BuchungDokument) Einstellungen.getDBService()
            .createObject(BuchungDokument.class, null);
        budo.setReferenz(new Integer(bu.getID()));
        DokumentControl dcontrol = new DokumentControl(this, "buchungen");
        grDokument.addPart(dcontrol.getDokumenteList(budo));
        ButtonArea butts = new ButtonArea(grDokument.getComposite(), 1);
        butts.addButton(dcontrol.getNeuButton(budo));
      }
    }

    ButtonArea buttons = new ButtonArea(scrolled.getComposite(), 3);
    buttons.addButton(JVereinPlugin.getI18n().tr("Hilfe"),
        new DokumentationAction(), DokumentationUtil.BUCHUNGEN, false,
        "help-browser.png");
    buttons.addButton(JVereinPlugin.getI18n().tr("neu"),
        new BuchungNeuAction(), null, false, "document-new.png");
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
    return "<form><p><span color=\"header\" font=\"header\">Buchung</span></p>"
        + "<p>Zuordnung einer Buchungsart zu einer Buchung.</p></form>";
  }
}
