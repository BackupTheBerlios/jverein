/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/MitgliedskontoListeAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2010/07/25 18:29:30 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: MitgliedskontoListeAction.java,v $
 * Revision 1.1  2010/07/25 18:29:30  jost
 * Neu: Mitgliedskonto
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.gui.view.MitgliedskontoListeView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class MitgliedskontoListeAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    GUI.startView(MitgliedskontoListeView.class.getName(), null);
  }
}
