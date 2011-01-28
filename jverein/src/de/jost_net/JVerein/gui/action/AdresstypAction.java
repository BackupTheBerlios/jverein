/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/AdresstypAction.java,v $
 * $Revision: 1.2 $
 * $Date: 2011/01/28 17:03:07 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: AdresstypAction.java,v $
 * Revision 1.2  2011/01/28 17:03:07  jost
 * Keine Änderung von reservierten Adresstypen
 *
 * Revision 1.1  2011-01-27 22:16:19  jost
 * Neu: Speicherung von weiteren Adressen in der Mitgliedertabelle
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import java.rmi.RemoteException;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.view.AdresstypView;
import de.jost_net.JVerein.rmi.Adresstyp;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class AdresstypAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    Adresstyp at = null;

    if (context != null && (context instanceof Adresstyp))
    {
      at = (Adresstyp) context;
      try
      {
        if (at.getJVereinid() > 0)
        {
          throw new ApplicationException(
              "Dieser Adresstyp ist reserviert und darf durch den Benutzer nicht verändert werden.");
        }
      }
      catch (RemoteException e)
      {
        throw new ApplicationException("Fehler aufgetreten", e);
      }
    }
    else
    {
      try
      {
        at = (Adresstyp) Einstellungen.getDBService().createObject(
            Adresstyp.class, null);
      }
      catch (RemoteException e)
      {
        throw new ApplicationException(JVereinPlugin.getI18n().tr(
            "Fehler bei der Erzeugung eines neuen Adresstypen"), e);
      }
    }
    GUI.startView(AdresstypView.class.getName(), at);
  }
}
