/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/ArbeitseinsatzUeberpruefungAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2010/11/22 20:57:21 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: ArbeitseinsatzUeberpruefungAction.java,v $
 * Revision 1.1  2010/11/22 20:57:21  jost
 * Initial Commit
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.gui.view.ArbeitseinsatzUeberpruefungView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;

public class ArbeitseinsatzUeberpruefungAction implements Action
{

  public void handleAction(Object context)
  {
    GUI.startView(ArbeitseinsatzUeberpruefungView.class.getName(), null);
  }
}
