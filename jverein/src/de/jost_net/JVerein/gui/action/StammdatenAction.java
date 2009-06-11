/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/Attic/StammdatenAction.java,v $
 * $Revision: 1.4 $
 * $Date: 2009/06/11 21:02:05 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: StammdatenAction.java,v $
 * Revision 1.4  2009/06/11 21:02:05  jost
 * Vorbereitung I18N
 *
 * Revision 1.3  2007/12/28 13:09:23  jost
 * Bugfix beim erzeugen eines Stammdaten-Objektes
 *
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
import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.view.StammdatenView;
import de.jost_net.JVerein.rmi.Stammdaten;
import de.willuhn.datasource.rmi.DBIterator;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class StammdatenAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    Stammdaten stamm = null;

    if (context != null && (context instanceof Stammdaten))
    {
      stamm = (Stammdaten) context;
    }
    else
    {
      try
      {
        DBIterator list = Einstellungen.getDBService().createList(
            Stammdaten.class);
        if (list.size() > 0)
        {
          stamm = (Stammdaten) list.next();
        }
        else
        {
          stamm = (Stammdaten) Einstellungen.getDBService().createObject(
              Stammdaten.class, null);
        }
      }
      catch (RemoteException e)
      {
        throw new ApplicationException(JVereinPlugin.getI18n().tr(
            "Fehler bei der Erzeugung eines Stammdatenobjektes aus der DB"), e);
      }
    }
    GUI.startView(StammdatenView.class.getName(), stamm);
  }

}
