/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/JahressaldoAction.java,v $
 * $Revision: 1.3 $
 * $Date: 2011/10/01 21:35:21 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.gui.view.JahressaldoView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;

public class JahressaldoAction implements Action
{

  public void handleAction(Object context)
  {
    GUI.startView(JahressaldoView.class.getName(), null);
  }
}
