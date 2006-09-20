/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/AbbuchungView.java,v $
 * $Revision: 1.1 $
 * $Date: 2006/09/20 15:39:10 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * jost@berlios.de
 * jverein.berlios.de
 * $Log: AbbuchungView.java,v $
 * Revision 1.1  2006/09/20 15:39:10  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.gui.action.BackAction;
import de.jost_net.JVerein.gui.control.AbbuchungControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.gui.util.LabelGroup;
import de.willuhn.util.ApplicationException;

public class AbbuchungView extends AbstractView
{
  public void bind() throws Exception
  {
    GUI.getView().setTitle("Abbuchung");

    final AbbuchungControl control = new AbbuchungControl(this);

    LabelGroup group = new LabelGroup(getParent(), "Parameter");
    group.addLabelPair("Jahresabbuchung", control.getJahresabbuchung());
    group.addLabelPair("Von Eingabedatum", control.getVondatum());
    group.addLabelPair("Zahlungsgrund", control.getZahlungsgrund());
    group.addLabelPair("Zusatzabbuchung", control.getZusatzabbuchung());

    ButtonArea buttons = new ButtonArea(this.getParent(), 2);
    buttons.addButton(control.getStartButton());
    buttons.addButton("<< Zurück", new BackAction());

  }

  public void unbind() throws ApplicationException
  {
  }
}
