/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/WiedervorlageListeAction.java,v $
 * $Revision: 1.2 $
 * $Date: 2010/10/15 09:58:01 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: WiedervorlageListeAction.java,v $
 * Revision 1.2  2010/10/15 09:58:01  jost
 * Code aufgeräumt
 *
 * Revision 1.1  2007-05-07 19:24:40  jost
 * Neu: Wiedervorlage
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.gui.view.WiedervorlagelisteView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;

public class WiedervorlageListeAction implements Action
{

  public void handleAction(Object context)
  {
    GUI.startView(WiedervorlagelisteView.class.getName(), null);
  }
}
