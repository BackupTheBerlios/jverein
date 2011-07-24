/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/FamilienbeitragAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2011/07/24 18:02:17 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: FamilienbeitragAction.java,v $
 * Revision 1.1  2011/07/24 18:02:17  jost
 * Neu: Auflistung Familienbeiträge
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.gui.view.FamilienbeitragView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;

public class FamilienbeitragAction implements Action
{

  public void handleAction(Object context)
  {
    GUI.startView(FamilienbeitragView.class.getName(), null);
  }
}
