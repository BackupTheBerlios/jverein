/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/Attic/RechnungListeAction.java,v $
 * $Revision: 1.2 $
 * $Date: 2010/10/15 09:58:03 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: RechnungListeAction.java,v $
 * Revision 1.2  2010/10/15 09:58:03  jost
 * Code aufger�umt
 *
 * Revision 1.1  2008-09-16 18:27:11  jost
 * Neu: Rechnung
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.gui.view.RechnungListeView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;

public class RechnungListeAction implements Action
{

  public void handleAction(Object context)
  {
    GUI.startView(RechnungListeView.class.getName(), null);
  }
}
