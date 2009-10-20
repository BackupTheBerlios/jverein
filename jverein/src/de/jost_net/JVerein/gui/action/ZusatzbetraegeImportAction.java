/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/ZusatzbetraegeImportAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2009/10/20 17:57:17 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: ZusatzbetraegeImportAction.java,v $
 * Revision 1.1  2009/10/20 17:57:17  jost
 * Neu: Import von Zusatzbeträgen
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.gui.view.ZusatzbetraegeImportView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class ZusatzbetraegeImportAction implements Action
{

  public void handleAction(Object context) throws ApplicationException
  {
    GUI.startView(ZusatzbetraegeImportView.class.getName(), null);
  }

}
