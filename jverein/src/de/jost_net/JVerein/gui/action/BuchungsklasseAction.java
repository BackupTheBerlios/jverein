/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/BuchungsklasseAction.java,v $
 * $Revision: 1.2 $
 * $Date: 2011/10/01 21:26:43 $
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
import de.jost_net.JVerein.gui.view.BuchungsklasseView;
import de.jost_net.JVerein.rmi.Buchungsklasse;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class BuchungsklasseAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    Buchungsklasse b = null;

    if (context != null && (context instanceof Buchungsklasse))
    {
      b = (Buchungsklasse) context;
    }
    else
    {
      try
      {
        b = (Buchungsklasse) Einstellungen.getDBService().createObject(
            Buchungsklasse.class, null);
      }
      catch (RemoteException e)
      {
        throw new ApplicationException(JVereinPlugin.getI18n().tr(
            "Fehler bei der Erzeugung einer neuen Buchungsklasse"), e);
      }
    }
    GUI.startView(BuchungsklasseView.class.getName(), b);
  }
}
