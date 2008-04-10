/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/FelddefinitionenAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/04/10 18:57:49 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: FelddefinitionenAction.java,v $
 * Revision 1.1  2008/04/10 18:57:49  jost
 * Neu: Benutzerdefinierte Datenfelder
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.gui.view.FelddefinitionenUebersichtView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class FelddefinitionenAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    GUI.startView(FelddefinitionenUebersichtView.class.getName(), null);
  }
}
