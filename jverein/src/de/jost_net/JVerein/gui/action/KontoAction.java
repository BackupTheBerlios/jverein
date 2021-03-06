/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/KontoAction.java,v $
 * $Revision: 1.3 $
 * $Date: 2011/10/01 21:35:50 $
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
import de.jost_net.JVerein.gui.view.KontoView;
import de.jost_net.JVerein.rmi.Konto;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class KontoAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    Konto k = null;

    if (context != null && (context instanceof Konto))
    {
      k = (Konto) context;
    }
    else
    {
      try
      {
        k = (Konto) Einstellungen.getDBService()
            .createObject(Konto.class, null);
      }
      catch (RemoteException e)
      {
        throw new ApplicationException(JVereinPlugin.getI18n().tr(
            "Fehler bei der Speicherung eines neuen Kontos"), e);
      }
    }
    GUI.startView(KontoView.class.getName(), k);
  }
}
