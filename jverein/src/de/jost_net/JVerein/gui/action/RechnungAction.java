/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/Attic/RechnungAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/09/16 18:26:29 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: RechnungAction.java,v $
 * Revision 1.1  2008/09/16 18:26:29  jost
 * Neu: Rechnung
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.gui.view.RechnungView;
import de.jost_net.JVerein.rmi.Abrechnung;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class RechnungAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    if (context != null && context instanceof Abrechnung)
    {
      Abrechnung abr = (Abrechnung) context;
      GUI.startView(RechnungView.class.getName(), abr);
    }
    else if (context != null && context instanceof Abrechnung[])
    {
      Abrechnung[] abr = (Abrechnung[]) context;
      GUI.startView(RechnungView.class.getName(), abr);
    }
  }
}
