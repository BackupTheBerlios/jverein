/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/JubilaeenAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2007/12/22 08:24:57 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: JubilaeenAction.java,v $
 * Revision 1.1  2007/12/22 08:24:57  jost
 * Neu: Jubiläenliste
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.gui.view.JubilaeenView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class JubilaeenAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    GUI.startView(JubilaeenView.class.getName(), null);
  }
}
