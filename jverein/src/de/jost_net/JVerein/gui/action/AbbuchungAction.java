/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/AbbuchungAction.java,v $
 * $Revision: 1.4 $
 * $Date: 2011/10/01 21:16:30 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.gui.view.AbbuchungView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;

public class AbbuchungAction implements Action
{

  public void handleAction(Object context)
  {
    GUI.startView(AbbuchungView.class.getName(), null);
  }
}
