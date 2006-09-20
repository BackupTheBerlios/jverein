/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/BeitragsgruppeDetailAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2006/09/20 15:38:12 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * jost@berlios.de
 * jverein.berlios.de
 * $Log: BeitragsgruppeDetailAction.java,v $
 * Revision 1.1  2006/09/20 15:38:12  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import java.rmi.RemoteException;

import de.jost_net.JVerein.Einstellungen;
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
        throw new ApplicationException(
            "Fehler bei der Erzeugung der neuen Beitragsgruppe", e);
      }
    }
    GUI.startView(BeitragsgruppeDetailView.class.getName(), b);
  }
}
