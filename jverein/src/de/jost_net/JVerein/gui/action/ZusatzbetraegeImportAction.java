/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/ZusatzbetraegeImportAction.java,v $
 * $Revision: 1.3 $
 * $Date: 2011/10/01 21:40:00 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.gui.view.ZusatzbetraegeImportView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;

public class ZusatzbetraegeImportAction implements Action
{

  public void handleAction(Object context)
  {
    GUI.startView(ZusatzbetraegeImportView.class.getName(), null);
  }

}
