/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/JahresabschlussListAction.java,v $
 * $Revision: 1.2 $
 * $Date: 2010/10/15 09:58:01 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: JahresabschlussListAction.java,v $
 * Revision 1.2  2010/10/15 09:58:01  jost
 * Code aufgeräumt
 *
 * Revision 1.1  2008-06-28 16:56:05  jost
 * Neu: Jahresabschluss
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.gui.view.JahresabschlussListView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;

public class JahresabschlussListAction implements Action
{

  public void handleAction(Object context)
  {
    GUI.startView(JahresabschlussListView.class.getName(), null);
  }
}
