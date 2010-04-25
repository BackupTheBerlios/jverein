/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/Attic/AbrechnunslaeufeListAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2010/04/25 13:53:30 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: AbrechnunslaeufeListAction.java,v $
 * Revision 1.1  2010/04/25 13:53:30  jost
 * Vorarbeiten Mitgliedskonto
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.gui.view.AbrechnungslaeufeListView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class AbrechnunslaeufeListAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    GUI.startView(AbrechnungslaeufeListView.class.getName(), null);
  }
}
