/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/JahresabschlussView.java,v $
 * $Revision: 1.2 $
 * $Date: 2008/07/09 13:18:03 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: JahresabschlussView.java,v $
 * Revision 1.2  2008/07/09 13:18:03  jost
 * Überflüssige Imports entfernt.
 *
 * Revision 1.1  2008/06/28 16:59:41  jost
 * Neu: Jahresabschluss
 *
 * Revision 1.2  2008/05/24 14:04:08  jost
 * Redatkionelle Änderung
 *
 * Revision 1.1  2008/05/22 06:52:26  jost
 * Buchführung
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.gui.action.BackAction;
import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.control.JahresabschlussControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.gui.util.LabelGroup;
import de.willuhn.util.ApplicationException;

public class JahresabschlussView extends AbstractView
{
  public void bind() throws Exception
  {
    GUI.getView().setTitle("Jahresabschluss");

    final JahresabschlussControl control = new JahresabschlussControl(this);

    LabelGroup group = new LabelGroup(getParent(), "Jahresabschluss");
    group.addLabelPair("von", control.getVon());
    group.addLabelPair("bis", control.getBis());
    group.addLabelPair("Datum", control.getDatum());
    group.addLabelPair("Name", control.getName());
    group.addPart(control.getJahresabschlussSaldo());

    ButtonArea buttons = new ButtonArea(getParent(), 3);
    buttons.addButton("<< Zur�ck", new BackAction());
    buttons.addButton("Hilfe", new DokumentationAction(),
        DokumentationUtil.JAHRESABSCHLUSS);
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
