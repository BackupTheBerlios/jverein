/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/SpendenbescheinigungAutoNeuAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2011/03/07 21:03:01 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: SpendenbescheinigungAutoNeuAction.java,v $
 * Revision 1.1  2011/03/07 21:03:01  jost
 * Neu:  Automatische Spendenbescheinigungen
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.gui.view.SpendenbescheinigungAutoNeuView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;

public class SpendenbescheinigungAutoNeuAction implements Action
{

  public void handleAction(Object context)
  {
    GUI.startView(SpendenbescheinigungAutoNeuView.class.getName(), null);
  }
}
