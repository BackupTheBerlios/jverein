/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/KontoauszugAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2011/08/11 15:22:56 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: KontoauszugAction.java,v $
 * Revision 1.1  2011/08/11 15:22:56  jost
 * Neu: Kontoauszug
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.gui.view.KontoauszugView;
import de.jost_net.JVerein.rmi.Mitglied;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;

public class KontoauszugAction implements Action
{

  public void handleAction(Object context)
  {
    if (context != null
        && (context instanceof Mitglied || context instanceof Mitglied[]))
    {
      GUI.startView(KontoauszugView.class.getName(), context);
    }
  }
}
