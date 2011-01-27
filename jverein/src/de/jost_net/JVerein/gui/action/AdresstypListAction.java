/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/AdresstypListAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2011/01/27 22:16:41 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: AdresstypListAction.java,v $
 * Revision 1.1  2011/01/27 22:16:41  jost
 * Neu: Speicherung von weiteren Adressen in der Mitgliedertabelle
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.gui.view.AdresstypListView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;

public class AdresstypListAction implements Action
{

  public void handleAction(Object context)
  {
    GUI.startView(AdresstypListView.class.getName(), null);
  }
}
