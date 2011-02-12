/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/BuchungAction.java,v $
 * $Revision: 1.8 $
 * $Date: 2011/02/12 09:25:28 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: BuchungAction.java,v $
 * Revision 1.8  2011/02/12 09:25:28  jost
 * Statische Codeanalyse mit Findbugs
 *
 * Revision 1.7  2009/06/11 21:02:05  jost
 * Vorbereitung I18N
 *
 * Revision 1.6  2008/07/09 13:17:48  jost
 * Überflüssige Imports entfernt.
 *
 * Revision 1.5  2008/06/28 16:55:24  jost
 * Bearbeiten nur, wenn kein Jahresabschluss vorliegt.
 *
 * Revision 1.4  2008/03/16 07:34:30  jost
 * Reaktivierung Buchführung
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
import de.jost_net.JVerein.gui.view.BuchungView;
import de.jost_net.JVerein.rmi.Buchung;
import de.jost_net.JVerein.rmi.Jahresabschluss;
import de.jost_net.JVerein.util.JVDateFormatTTMMJJJJ;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class BuchungAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    Buchung b = null;

    if (context != null && (context instanceof Buchung))
    {
      b = (Buchung) context;
      try
      {
        Jahresabschluss ja = b.getJahresabschluss();
        if (ja != null)
        {
          throw new ApplicationException(JVereinPlugin.getI18n().tr(
              "Buchung wurde bereits am {0} von {1} abgeschlossen.",
              new String[] { new JVDateFormatTTMMJJJJ().format(ja.getDatum()),
                  ja.getName() }));
        }
      }
      catch (RemoteException e)
      {
        throw new ApplicationException(e.getMessage());
      }
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
        throw new ApplicationException(JVereinPlugin.getI18n().tr(
            "Fehler bei der Erzeugung einer neuen Buchung"), e);
      }
    }
    GUI.startView(BuchungView.class.getName(), b);
  }
}
