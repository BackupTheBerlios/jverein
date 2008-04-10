/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/FelddefinitionDetailAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/04/10 18:57:24 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: FelddefinitionDetailAction.java,v $
 * Revision 1.1  2008/04/10 18:57:24  jost
 * Neu: Benutzerdefinierte Datenfelder
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import java.rmi.RemoteException;

import de.jost_net.JVerein.Einstellungen;
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
        throw new ApplicationException(
            "Fehler bei der Erzeugung der neuen Felddefinition", e);
      }
    }
    GUI.startView(FelddefinitionDetailView.class.getName(), f);
  }
}
