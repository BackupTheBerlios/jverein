/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/AnfangsbestandListAction.java,v $
 * $Revision: 1.2 $
 * $Date: 2010/10/15 09:58:03 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: AnfangsbestandListAction.java,v $
 * Revision 1.2  2010/10/15 09:58:03  jost
 * Code aufger‰umt
 *
 * Revision 1.1  2008-05-22 06:45:20  jost
 * Buchf√ºhrung
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.gui.view.AnfangsbestandListView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;

public class AnfangsbestandListAction implements Action
{

  public void handleAction(Object context)
  {
    GUI.startView(AnfangsbestandListView.class.getName(), null);
  }
}
