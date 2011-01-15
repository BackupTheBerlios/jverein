/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/DokumentView.java,v $
 * $Revision: 1.3 $
 * $Date: 2011/01/15 09:46:48 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: DokumentView.java,v $
 * Revision 1.3  2011/01/15 09:46:48  jost
 * Tastatursteuerung wegen Problemen mit Jameica/Hibiscus wieder entfernt.
 *
 * Revision 1.2  2010-12-14 21:42:03  jost
 * Neu: Speicherung von Dokumenten
 *
 * Revision 1.1  2010-12-12 08:13:14  jost
 * Neu: Speicherung von Dokumenten
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.control.DokumentControl;
import de.jost_net.JVerein.gui.internal.buttons.Back;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.gui.util.LabelGroup;
import de.willuhn.jameica.gui.util.ScrolledContainer;

public class DokumentView extends AbstractView
{
  private String verzeichnis;

  public DokumentView(String verzeichnis)
  {
    this.verzeichnis = verzeichnis;
  }

  @Override
  public void bind() throws Exception
  {
    GUI.getView().setTitle(JVereinPlugin.getI18n().tr("Dokument"));

    final DokumentControl control = new DokumentControl(this, verzeichnis);

    ScrolledContainer scrolled = new ScrolledContainer(getParent());

    LabelGroup grDokument = new LabelGroup(scrolled.getComposite(),
        JVereinPlugin.getI18n().tr("Dokument"));
    grDokument.addLabelPair(JVereinPlugin.getI18n().tr("Datei"), control
        .getDatei());
    grDokument.addLabelPair(JVereinPlugin.getI18n().tr("Datum"), control
        .getDatum());
    grDokument.addLabelPair(JVereinPlugin.getI18n().tr("Bemerkung"), control
        .getBemerkung());
    ButtonArea buttons = new ButtonArea(getParent(), 3);
    buttons.addButton(new Back(false));
    buttons.addButton(JVereinPlugin.getI18n().tr("Hilfe"),
        new DokumentationAction(), DokumentationUtil.BUCHUNGEN, false,
        "help-browser.png");
    buttons.addButton(control.getSpeichernButton(verzeichnis + "."));
  }

  @Override
  public String getHelp()
  {
    return "<form><p><span color=\"header\" font=\"header\">Dokument</span></p>";
  }
}
