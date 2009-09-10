/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/BuchungsklasseListAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2009/09/10 18:16:34 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: BuchungsklasseListAction.java,v $
 * Revision 1.1  2009/09/10 18:16:34  jost
 * neu: Buchungsklassen
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.gui.view.BuchungsklasseListView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class BuchungsklasseListAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    GUI.startView(BuchungsklasseListView.class.getName(), null);
  }
}
