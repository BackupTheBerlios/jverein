/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/Attic/ZusatzabbuchunglisteView.java,v $
 * $Revision: 1.1 $
 * $Date: 2006/09/20 15:39:10 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * jost@berlios.de
 * jverein.berlios.de
 * $Log: ZusatzabbuchunglisteView.java,v $
 * Revision 1.1  2006/09/20 15:39:10  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.gui.action.BackAction;
import de.jost_net.JVerein.gui.control.ZusatzabbuchungControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.gui.util.LabelGroup;
import de.willuhn.util.ApplicationException;

public class ZusatzabbuchunglisteView extends AbstractView
{
  public void bind() throws Exception
  {
    GUI.getView().setTitle("Liste der Zusatzabbuchungen");

    final ZusatzabbuchungControl control = new ZusatzabbuchungControl(this);

    LabelGroup group = new LabelGroup(getParent(), "Ausf�hrungstag");
    group.addLabelPair("Ausf�hrungstag", control.getAusf�hrungSuch());

    ButtonArea buttons = new ButtonArea(this.getParent(), 2);
    // Button button = new Button("suchen", new Action()
    // {
    // public void handleAction(Object context) throws ApplicationException
    // {
    // try
    // {
    // control.getBuchungsList();
    // }
    // catch (RemoteException e)
    // {
    // e.printStackTrace();
    // }
    // }
    // }, null, true);
    //
    // buttons.addButton(button);
    buttons.addButton("<< Zur�ck", new BackAction());

    control.getZusatzabbuchungsList().paint(this.getParent());
  }

  public void unbind() throws ApplicationException
  {
  }

}
