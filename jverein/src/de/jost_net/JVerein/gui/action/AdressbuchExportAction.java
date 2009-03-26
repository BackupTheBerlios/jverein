/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/AdressbuchExportAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2009/03/26 20:54:48 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: AdressbuchExportAction.java,v $
 * Revision 1.1  2009/03/26 20:54:48  jost
 * Neu: Adressbuchexport
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.gui.view.AdressbuchExportView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class AdressbuchExportAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    GUI.startView(AdressbuchExportView.class.getName(), null);
  }
}
