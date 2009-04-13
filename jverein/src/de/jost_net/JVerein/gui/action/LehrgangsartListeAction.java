/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/LehrgangsartListeAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2009/04/13 11:39:01 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: LehrgangsartListeAction.java,v $
 * Revision 1.1  2009/04/13 11:39:01  jost
 * Neu: Lehrgänge
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.gui.view.LehrgangsartListeView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class LehrgangsartListeAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    GUI.startView(LehrgangsartListeView.class.getName(), null);
  }
}
