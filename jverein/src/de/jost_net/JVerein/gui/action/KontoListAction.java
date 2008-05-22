/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/KontoListAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/05/22 06:47:00 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: KontoListAction.java,v $
 * Revision 1.1  2008/05/22 06:47:00  jost
 * Buchführung
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.gui.view.KontoListView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class KontoListAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    GUI.startView(KontoListView.class.getName(), null);
  }
}
