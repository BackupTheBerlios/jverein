/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/AnfangsbestandListAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/05/22 06:45:20 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: AnfangsbestandListAction.java,v $
 * Revision 1.1  2008/05/22 06:45:20  jost
 * Buchführung
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.gui.view.AnfangsbestandListView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class AnfangsbestandListAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    GUI.startView(AnfangsbestandListView.class.getName(), null);
  }
}
