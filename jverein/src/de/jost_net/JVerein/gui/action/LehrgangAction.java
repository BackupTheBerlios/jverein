/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/LehrgangAction.java,v $
 * $Revision: 1.4 $
 * $Date: 2011/10/01 21:39:59 $
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
import de.jost_net.JVerein.gui.view.LehrgangView;
import de.jost_net.JVerein.rmi.Lehrgang;
import de.jost_net.JVerein.rmi.Mitglied;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class LehrgangAction implements Action
{
  private Mitglied m;

  public LehrgangAction(Mitglied m)
  {
    super();
    this.m = m;
  }

  public void handleAction(Object context) throws ApplicationException
  {
    Lehrgang l = null;

    if (context != null && (context instanceof Lehrgang))
    {
      l = (Lehrgang) context;
    }
    else
    {
      try
      {
        l = (Lehrgang) Einstellungen.getDBService().createObject(
            Lehrgang.class, null);
        if (m != null)
        {
          if (m.getID() == null)
          {
            throw new ApplicationException(
                "Neues Mitglied bitte erst speichern. Dann k�nnen Lehrg�nge aufgenommen werden.");
          }

          l.setMitglied(new Integer(m.getID()).intValue());
        }
      }
      catch (RemoteException e)
      {
        throw new ApplicationException(JVereinPlugin.getI18n().tr(
            "Fehler bei der Erzeugung eines neuen Lehrgangs"), e);
      }
    }
    GUI.startView(LehrgangView.class.getName(), l);
  }
}
