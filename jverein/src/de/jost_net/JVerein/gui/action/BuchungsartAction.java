/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/BuchungsartAction.java,v $
 * $Revision: 1.6 $
 * $Date: 2011/10/01 21:25:49 $
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
import de.jost_net.JVerein.gui.view.BuchungsartView;
import de.jost_net.JVerein.rmi.Buchungsart;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class BuchungsartAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    Buchungsart b = null;

    if (context != null && (context instanceof Buchungsart))
    {
      b = (Buchungsart) context;
    }
    else
    {
      try
      {
        b = (Buchungsart) Einstellungen.getDBService().createObject(
            Buchungsart.class, null);
      }
      catch (RemoteException e)
      {
        throw new ApplicationException(JVereinPlugin.getI18n().tr(
            "Fehler bei der Erzeugung einer neuen Buchungsart"), e);
      }
    }
    GUI.startView(BuchungsartView.class.getName(), b);
  }
}
