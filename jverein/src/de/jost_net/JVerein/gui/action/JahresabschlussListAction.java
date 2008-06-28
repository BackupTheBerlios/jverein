/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/JahresabschlussListAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/06/28 16:56:05 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: JahresabschlussListAction.java,v $
 * Revision 1.1  2008/06/28 16:56:05  jost
 * Neu: Jahresabschluss
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.gui.view.JahresabschlussListView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class JahresabschlussListAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    GUI.startView(JahresabschlussListView.class.getName(), null);
  }
}
