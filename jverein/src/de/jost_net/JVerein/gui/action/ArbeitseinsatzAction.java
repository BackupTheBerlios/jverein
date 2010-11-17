/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/ArbeitseinsatzAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2010/11/17 04:48:29 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: ArbeitseinsatzAction.java,v $
 * Revision 1.1  2010/11/17 04:48:29  jost
 * Erster Code zum Thema Arbeitseinsatz
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import java.rmi.RemoteException;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.view.ArbeitseinsatzDetailView;
import de.jost_net.JVerein.rmi.Arbeitseinsatz;
import de.jost_net.JVerein.rmi.Mitglied;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class ArbeitseinsatzAction implements Action
{
  private Mitglied m;

  public ArbeitseinsatzAction(Mitglied m)
  {
    super();
    this.m = m;
  }

  public void handleAction(Object context) throws ApplicationException
  {
    Arbeitseinsatz aeins = null;

    if (context != null && (context instanceof Arbeitseinsatz))
    {
      aeins = (Arbeitseinsatz) context;
    }
    else
    {
      try
      {
        aeins = (Arbeitseinsatz) Einstellungen.getDBService().createObject(
            Arbeitseinsatz.class, null);
        if (m != null)
        {
          aeins.setMitglied(new Integer(m.getID()).intValue());
        }
      }
      catch (RemoteException e)
      {
        throw new ApplicationException(JVereinPlugin.getI18n().tr(
            "Fehler bei der Erzeugung eines neuen Arbeitseinsatzes"), e);
      }
    }
    GUI.startView(ArbeitseinsatzDetailView.class.getName(), aeins);
  }
}
