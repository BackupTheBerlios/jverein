/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/LehrgaengeListeAction.java,v $
 * $Revision: 1.2 $
 * $Date: 2010/10/15 09:58:02 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: LehrgaengeListeAction.java,v $
 * Revision 1.2  2010/10/15 09:58:02  jost
 * Code aufgeräumt
 *
 * Revision 1.1  2009-04-13 11:37:46  jost
 * Neu: Lehrgänge
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.gui.view.LehrgaengeListeView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;

public class LehrgaengeListeAction implements Action
{

  public void handleAction(Object context)
  {
    GUI.startView(LehrgaengeListeView.class.getName(), null);
  }
}
