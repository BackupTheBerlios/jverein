/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/Attic/ReportAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2009/03/26 20:55:25 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: ReportAction.java,v $
 * Revision 1.1  2009/03/26 20:55:25  jost
 * Neu: Reports - Erste Version
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import java.rmi.RemoteException;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.gui.view.ReportDetailView;
import de.jost_net.JVerein.rmi.Report;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class ReportAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    Report r = null;

    if (context != null && (context instanceof Report))
    {
      r = (Report) context;
    }
    else
    {
      try
      {
        r = (Report) Einstellungen.getDBService().createObject(Report.class,
            null);
      }
      catch (RemoteException e)
      {
        throw new ApplicationException(
            "Fehler bei der Erzeugung eines neuen Report", e);
      }
    }
    GUI.startView(ReportDetailView.class.getName(), r);
  }
}
