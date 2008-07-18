/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/SpendenbescheinigungListeAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/07/18 20:08:50 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: SpendenbescheinigungListeAction.java,v $
 * Revision 1.1  2008/07/18 20:08:50  jost
 * Neu: Spendenbescheinigung
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.gui.view.SpendenbescheinigungListeView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class SpendenbescheinigungListeAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    GUI.startView(SpendenbescheinigungListeView.class.getName(), null);
  }
}
