/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/Attic/RechnungDetailAction.java,v $
 * $Revision: 1.2 $
 * $Date: 2010/10/15 09:58:02 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: RechnungDetailAction.java,v $
 * Revision 1.2  2010/10/15 09:58:02  jost
 * Code aufgeräumt
 *
 * Revision 1.1  2008-09-16 18:27:00  jost
 * Neu: Rechnung
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.gui.view.RechnungDetailView;
import de.jost_net.JVerein.rmi.Abrechnung;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;

public class RechnungDetailAction implements Action
{

  public void handleAction(Object context)
  {
    Abrechnung a = null;

    if (context != null && (context instanceof Abrechnung))
    {
      a = (Abrechnung) context;
    }
    GUI.startView(RechnungDetailView.class.getName(), a);
  }
}
