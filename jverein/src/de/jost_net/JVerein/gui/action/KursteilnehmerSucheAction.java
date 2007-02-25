/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/KursteilnehmerSucheAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2007/02/25 19:11:53 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: KursteilnehmerSucheAction.java,v $
 * Revision 1.1  2007/02/25 19:11:53  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class KursteilnehmerSucheAction implements Action
{

  public void handleAction(Object context) throws ApplicationException
  {
    GUI.startView(de.jost_net.JVerein.gui.view.KursteilnehmerSucheView.class
        .getName(), null);
  }

}
