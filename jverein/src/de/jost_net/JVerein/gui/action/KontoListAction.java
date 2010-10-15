/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/KontoListAction.java,v $
 * $Revision: 1.2 $
 * $Date: 2010/10/15 09:58:01 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: KontoListAction.java,v $
 * Revision 1.2  2010/10/15 09:58:01  jost
 * Code aufger‰umt
 *
 * Revision 1.1  2008-05-22 06:47:00  jost
 * Buchf√ºhrung
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.gui.view.KontoListView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;

public class KontoListAction implements Action
{

  public void handleAction(Object context)
  {
    GUI.startView(KontoListView.class.getName(), null);
  }
}
