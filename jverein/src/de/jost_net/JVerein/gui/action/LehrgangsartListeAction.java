/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/LehrgangsartListeAction.java,v $
 * $Revision: 1.2 $
 * $Date: 2010/10/15 09:58:03 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: LehrgangsartListeAction.java,v $
 * Revision 1.2  2010/10/15 09:58:03  jost
 * Code aufgeräumt
 *
 * Revision 1.1  2009-04-13 11:39:01  jost
 * Neu: Lehrgänge
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.gui.view.LehrgangsartListeView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;

public class LehrgangsartListeAction implements Action
{

  public void handleAction(Object context)
  {
    GUI.startView(LehrgangsartListeView.class.getName(), null);
  }
}
