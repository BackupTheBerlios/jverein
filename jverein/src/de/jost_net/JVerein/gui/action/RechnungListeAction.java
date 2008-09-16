/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/Attic/RechnungListeAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/09/16 18:27:11 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: RechnungListeAction.java,v $
 * Revision 1.1  2008/09/16 18:27:11  jost
 * Neu: Rechnung
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.gui.view.RechnungListeView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class RechnungListeAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    GUI.startView(RechnungListeView.class.getName(), null);
  }
}
