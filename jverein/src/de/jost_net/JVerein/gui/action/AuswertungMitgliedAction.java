/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/AuswertungMitgliedAction.java,v $
 * $Revision: 1.4 $
 * $Date: 2011/03/13 18:28:07 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: AuswertungMitgliedAction.java,v $
 * Revision 1.4  2011/03/13 18:28:07  jost
 * redakt. Fehlermeldung
 *
 * Revision 1.3  2009/06/11 21:02:05  jost
 * Vorbereitung I18N
 *
 * Revision 1.2  2007/02/23 20:25:42  jost
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
import de.jost_net.JVerein.gui.view.AuswertungMitgliedView;
import de.jost_net.JVerein.rmi.Mitglied;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class AuswertungMitgliedAction implements Action
{

  public void handleAction(Object context) throws ApplicationException
  {

    Mitglied m = null;

    if (context != null && (context instanceof Mitglied))
    {
      m = (Mitglied) context;
    }
    else
    {
      try
      {
        m = (Mitglied) Einstellungen.getDBService().createObject(
            Mitglied.class, null);
      }
      catch (RemoteException e)
      {
        throw new ApplicationException(JVereinPlugin.getI18n().tr(
            "Fehler beim erzeugen eines neuen Mitglied-Objektes: {0}",
            new String[] { e.getMessage() }));
      }
    }

    GUI.startView(AuswertungMitgliedView.class.getName(), m);
  }

}
