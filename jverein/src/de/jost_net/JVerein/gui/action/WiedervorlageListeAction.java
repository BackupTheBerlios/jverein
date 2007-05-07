/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/WiedervorlageListeAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2007/05/07 19:24:40 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: WiedervorlageListeAction.java,v $
 * Revision 1.1  2007/05/07 19:24:40  jost
 * Neu: Wiedervorlage
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.gui.view.WiedervorlagelisteView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class WiedervorlageListeAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    GUI.startView(WiedervorlagelisteView.class.getName(), null);
  }
}
