/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/Attic/StammdatenAction.java,v $
 * $Revision: 1.2 $
 * $Date: 2007/02/23 20:26:00 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: StammdatenAction.java,v $
 * Revision 1.2  2007/02/23 20:26:00  jost
 * Mail- und Webadresse im Header korrigiert.
 *
 * Revision 1.1  2006/09/20 15:38:12  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import java.rmi.RemoteException;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.gui.view.StammdatenView;
import de.jost_net.JVerein.rmi.Stammdaten;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class StammdatenAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    Stammdaten s = null;

    if (context != null && (context instanceof Stammdaten))
    {
      s = (Stammdaten) context;
    }
    else
    {
      try
      {
        try
        {
          s = (Stammdaten) Einstellungen.getDBService().createObject(
              Stammdaten.class, "0");
        }
        catch (RemoteException e)
        {
          s = (Stammdaten) Einstellungen.getDBService().createObject(
              Stammdaten.class, null);
        }
      }
      catch (RemoteException e)
      {
        throw new ApplicationException(
            "Fehler bei der Erzeugung eines Stammdatenobjektes aus der DB", e);
      }
    }
    GUI.startView(StammdatenView.class.getName(), s);
  }

}
