/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/Attic/AuswertungBuchungenAction.java,v $
 * $Revision: 1.2 $
 * $Date: 2006/10/18 06:01:26 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * jost@berlios.de
 * jverein.berlios.de
 * $Log: AuswertungBuchungenAction.java,v $
 * Revision 1.2  2006/10/18 06:01:26  jost
 * �berfl�ssige Import-Statements entfernt.
 *
 * Revision 1.1  2006/10/14 06:02:16  jost
 * Erweiterung um Buchungsauswertung
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import java.rmi.RemoteException;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.gui.view.AuswertungBuchungView;
import de.jost_net.JVerein.rmi.Buchung;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class AuswertungBuchungenAction implements Action
{

  public void handleAction(Object context) throws ApplicationException
  {

    Buchung b = null;

    if (context != null && (context instanceof Buchung))
    {
      b = (Buchung) context;
    }
    else
    {
      try
      {
        b = (Buchung) Einstellungen.getDBService().createObject(Buchung.class,
            null);
      }
      catch (RemoteException e)
      {
        throw new ApplicationException(
            "Fehler beim erzeugen eines neuen Buchung-Objectes", e);
      }
    }

    GUI.startView(AuswertungBuchungView.class.getName(), b);
  }

}
