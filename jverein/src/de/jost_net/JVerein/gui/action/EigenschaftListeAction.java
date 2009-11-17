/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/EigenschaftListeAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2009/11/17 20:55:52 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: EigenschaftListeAction.java,v $
 * Revision 1.1  2009/11/17 20:55:52  jost
 * Neu: Eigenschaft und EigenschaftGruppe
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.gui.view.EigenschaftListeView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class EigenschaftListeAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    GUI.startView(EigenschaftListeView.class.getName(), null);
  }
}
