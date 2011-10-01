/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/LehrgangsartAction.java,v $
 * $Revision: 1.3 $
 * $Date: 2011/10/01 21:40:00 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import java.rmi.RemoteException;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.view.LehrgangsartDetailView;
import de.jost_net.JVerein.rmi.Lehrgangsart;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class LehrgangsartAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    Lehrgangsart l = null;

    if (context != null && (context instanceof Lehrgangsart))
    {
      l = (Lehrgangsart) context;
    }
    else
    {
      try
      {
        l = (Lehrgangsart) Einstellungen.getDBService().createObject(
            Lehrgangsart.class, null);
      }
      catch (RemoteException e)
      {
        throw new ApplicationException(JVereinPlugin.getI18n().tr(
            "Fehler bei der Erzeugung einer neuen Lehrgangsart"), e);
      }
    }
    GUI.startView(LehrgangsartDetailView.class.getName(), l);
  }
}
