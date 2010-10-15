/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/BuchungsartListAction.java,v $
 * $Revision: 1.5 $
 * $Date: 2010/10/15 09:58:02 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: BuchungsartListAction.java,v $
 * Revision 1.5  2010/10/15 09:58:02  jost
 * Code aufger‰umt
 *
 * Revision 1.4  2008-03-16 07:35:03  jost
 * Reaktivierung Buchf√ºhrung
 *
 * Revision 1.2  2007/02/23 20:25:42  jost
 * Mail- und Webadresse im Header korrigiert.
 *
 * Revision 1.1  2006/09/20 15:38:12  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.gui.view.BuchungsartListView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;

public class BuchungsartListAction implements Action
{

  public void handleAction(Object context)
  {
    GUI.startView(BuchungsartListView.class.getName(), null);
  }
}
