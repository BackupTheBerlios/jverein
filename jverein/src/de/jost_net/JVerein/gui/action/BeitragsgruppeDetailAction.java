/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/BeitragsgruppeDetailAction.java,v $
 * $Revision: 1.4 $
 * $Date: 2011/10/01 21:22:56 $
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
import de.jost_net.JVerein.gui.view.BeitragsgruppeDetailView;
import de.jost_net.JVerein.rmi.Beitragsgruppe;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class BeitragsgruppeDetailAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    Beitragsgruppe b = null;

    if (context != null && (context instanceof Beitragsgruppe))
    {
      b = (Beitragsgruppe) context;
    }
    else
    {
      try
      {
        b = (Beitragsgruppe) Einstellungen.getDBService().createObject(
            Beitragsgruppe.class, null);
      }
      catch (RemoteException e)
      {
        throw new ApplicationException(JVereinPlugin.getI18n().tr(
            "Fehler bei der Erzeugung der neuen Beitragsgruppe"), e);
      }
    }
    GUI.startView(BeitragsgruppeDetailView.class.getName(), b);
  }
}
