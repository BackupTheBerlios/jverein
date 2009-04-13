/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/LehrgaengeListeAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2009/04/13 11:37:46 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: LehrgaengeListeAction.java,v $
 * Revision 1.1  2009/04/13 11:37:46  jost
 * Neu: Lehrgänge
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.gui.view.LehrgaengeListeView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class LehrgaengeListeAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    GUI.startView(LehrgaengeListeView.class.getName(), null);
  }
}
