/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/JahresabschlussDetailAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/06/28 16:55:55 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: JahresabschlussDetailAction.java,v $
 * Revision 1.1  2008/06/28 16:55:55  jost
 * Neu: Jahresabschluss
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import java.rmi.RemoteException;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.gui.view.JahresabschlussView;
import de.jost_net.JVerein.rmi.Jahresabschluss;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class JahresabschlussDetailAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    Jahresabschluss ja = null;

    if (context != null && (context instanceof Jahresabschluss))
    {
      ja = (Jahresabschluss) context;
    }
    else
    {
      try
      {
        ja = (Jahresabschluss) Einstellungen.getDBService().createObject(
            Jahresabschluss.class, null);
      }
      catch (RemoteException e)
      {
        throw new ApplicationException(
            "Fehler bei der Erzeugung eines neuen Jahresabschlusses", e);
      }
    }
    GUI.startView(JahresabschlussView.class.getName(), ja);
  }
}
