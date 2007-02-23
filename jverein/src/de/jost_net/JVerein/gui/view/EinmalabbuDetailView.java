/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/Attic/EinmalabbuDetailView.java,v $
 * $Revision: 1.1 $
 * $Date: 2007/02/23 20:27:28 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: EinmalabbuDetailView.java,v $
 * Revision 1.1  2007/02/23 20:27:28  jost
 * Mail- und Webadresse im Header korrigiert.
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.gui.action.BackAction;
import de.jost_net.JVerein.gui.action.EinmalabbuDeleteAction;
import de.jost_net.JVerein.gui.action.EinmalabbuDetailAction;
import de.jost_net.JVerein.gui.control.EinmalabbuControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.gui.util.LabelGroup;
import de.willuhn.util.ApplicationException;

public class EinmalabbuDetailView extends AbstractView
{
  public void bind() throws Exception
  {
    GUI.getView().setTitle("Daten für die Einmalabbuchung");

    final EinmalabbuControl control = new EinmalabbuControl(this);

    LabelGroup grGrund = new LabelGroup(getParent(), "Einmal-Abbuchung");
    grGrund.getComposite().setSize(290, 190);
    grGrund.addLabelPair("Name", control.getName());
    grGrund.addLabelPair("Verwendungszweck 1", control.getVZweck1());
    grGrund.addLabelPair("Verwendungszweck 2", control.getVZweck2());
    grGrund.addLabelPair("BLZ", control.getBlz());
    grGrund.addLabelPair("Konto", control.getKonto());
    grGrund.addLabelPair("Betrag", control.getBetrag());
    grGrund.addLabelPair("Geburtsdatum", control.getGeburtsdatum());
    ButtonArea buttons = new ButtonArea(getParent(), 4);

    buttons.addButton("<< Zurück", new BackAction());
    buttons.addButton("Neu", new EinmalabbuDetailAction());
    buttons.addButton("Löschen", new EinmalabbuDeleteAction(), control
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
