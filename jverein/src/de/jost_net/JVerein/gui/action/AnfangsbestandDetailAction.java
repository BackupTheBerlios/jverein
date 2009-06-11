/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/AnfangsbestandDetailAction.java,v $
 * $Revision: 1.3 $
 * $Date: 2009/06/11 21:01:30 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: AnfangsbestandDetailAction.java,v $
 * Revision 1.3  2009/06/11 21:01:30  jost
 * Vorbereitung I18N
 *
 * Revision 1.2  2008/06/28 16:55:00  jost
 * Bearbeiten nur, wenn kein Jahresabschluss vorliegt.
 *
 * Revision 1.1  2008/05/22 06:45:04  jost
 * Buchführung
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
import de.jost_net.JVerein.gui.view.AnfangsbestandView;
import de.jost_net.JVerein.rmi.Anfangsbestand;
import de.jost_net.JVerein.rmi.Jahresabschluss;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class AnfangsbestandDetailAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    Anfangsbestand a = null;

    if (context != null && (context instanceof Anfangsbestand))
    {
      a = (Anfangsbestand) context;
      try
      {
        Jahresabschluss ja = a.getJahresabschluss();
        if (ja != null)
        {
          throw new ApplicationException(JVereinPlugin.getI18n().tr(
              "Anfangsbestand ist bereits abgeschlossen."));
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
        a = (Anfangsbestand) Einstellungen.getDBService().createObject(
            Anfangsbestand.class, null);
      }
      catch (RemoteException e)
      {
        throw new ApplicationException(JVereinPlugin.getI18n().tr(
            "Fehler bei der Erzeugung eines neuen Anfangsbestandes: {0}",
            new String[] { e.getMessage() }));
      }
    }
    GUI.startView(AnfangsbestandView.class.getName(), a);
  }
}
