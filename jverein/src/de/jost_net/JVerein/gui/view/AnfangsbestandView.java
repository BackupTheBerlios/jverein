/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/AnfangsbestandView.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/05/22 06:52:26 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: AnfangsbestandView.java,v $
 * Revision 1.1  2008/05/22 06:52:26  jost
 * Buchführung
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.gui.action.BackAction;
import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.control.AnfangsbestandControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.gui.util.LabelGroup;
import de.willuhn.util.ApplicationException;

public class AnfangsbestandView extends AbstractView
{
  public void bind() throws Exception
  {
    GUI.getView().setTitle("Anfangsbestand");

    final AnfangsbestandControl control = new AnfangsbestandControl(this);

    LabelGroup group = new LabelGroup(getParent(), "Anfangsbestand");
    group.addLabelPair("Konto", control.getKonto());
    group.addLabelPair("Datum", control.getDatum());
    if (control.getAnfangsbestand().getID() != null)
    {
      control.getDatum().setEnabled(false);
    }
    group.addLabelPair("Betrag", control.getBetrag());

    ButtonArea buttons = new ButtonArea(getParent(), 3);
    buttons.addButton("<< Zur�ck", new BackAction());
    buttons.addButton("Hilfe", new DokumentationAction(),
        DokumentationUtil.anfangsbestaende);
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
