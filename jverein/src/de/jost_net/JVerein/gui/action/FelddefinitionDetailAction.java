/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/FelddefinitionDetailAction.java,v $
 * $Revision: 1.3 $
 * $Date: 2011/10/01 21:32:03 $
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
import de.jost_net.JVerein.gui.view.FelddefinitionDetailView;
import de.jost_net.JVerein.rmi.Felddefinition;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class FelddefinitionDetailAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    Felddefinition f = null;

    if (context != null && (context instanceof Felddefinition))
    {
      f = (Felddefinition) context;
    }
    else
    {
      try
      {
        f = (Felddefinition) Einstellungen.getDBService().createObject(
            Felddefinition.class, null);
      }
      catch (RemoteException e)
      {
        throw new ApplicationException(JVereinPlugin.getI18n().tr(
            "Fehler bei der Erzeugung der neuen Felddefinition"), e);
      }
    }
    GUI.startView(FelddefinitionDetailView.class.getName(), f);
  }
}
