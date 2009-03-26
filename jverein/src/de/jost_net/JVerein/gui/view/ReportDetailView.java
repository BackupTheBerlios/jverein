/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/Attic/ReportDetailView.java,v $
 * $Revision: 1.1 $
 * $Date: 2009/03/26 21:03:24 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: ReportDetailView.java,v $
 * Revision 1.1  2009/03/26 21:03:24  jost
 * Neu: Reports - Erste Version
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.control.ReportControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.internal.buttons.Back;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.gui.util.LabelGroup;
import de.willuhn.util.ApplicationException;

public class ReportDetailView extends AbstractView
{
  public void bind() throws Exception
  {
    GUI.getView().setTitle("Report");

    final ReportControl control = new ReportControl(this);

    LabelGroup group = new LabelGroup(getParent(), "Report");
    group.addLabelPair("Bezeichnung", control.getBezeichnung());
    group.addLabelPair("Art", control.getArt());
    group.addLabelPair("Datei", control.getDatei());

    ButtonArea buttons = new ButtonArea(getParent(), 4);
    buttons.addButton(new Back(false));
    buttons.addButton("Hilfe", new DokumentationAction(),
        DokumentationUtil.REPORTS, false, "help-browser.png");
    buttons.addButton("Speichern", new Action()
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
