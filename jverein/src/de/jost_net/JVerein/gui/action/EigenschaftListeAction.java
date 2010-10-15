/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/EigenschaftListeAction.java,v $
 * $Revision: 1.2 $
 * $Date: 2010/10/15 09:58:03 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: EigenschaftListeAction.java,v $
 * Revision 1.2  2010/10/15 09:58:03  jost
 * Code aufgeräumt
 *
 * Revision 1.1  2009-11-17 20:55:52  jost
 * Neu: Eigenschaft und EigenschaftGruppe
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.gui.view.EigenschaftListeView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;

public class EigenschaftListeAction implements Action
{

  public void handleAction(Object context)
  {
    GUI.startView(EigenschaftListeView.class.getName(), null);
  }
}
