/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/EinstellungenAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2007/08/22 20:42:41 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: EinstellungenAction.java,v $
 * Revision 1.1  2007/08/22 20:42:41  jost
 * Bug #011762
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.gui.view.EinstellungenView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class EinstellungenAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    GUI.startView(EinstellungenView.class.getName(), null);
  }
}
