/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/EigenschaftDetailAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2009/11/17 20:54:28 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: EigenschaftDetailAction.java,v $
 * Revision 1.1  2009/11/17 20:54:28  jost
 * Neu: Eigenschaft und EigenschaftGruppe
 *
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import java.rmi.RemoteException;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.view.EigenschaftDetailView;
import de.jost_net.JVerein.rmi.Eigenschaft;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class EigenschaftDetailAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    Eigenschaft ei = null;

    if (context != null && (context instanceof Eigenschaft))
    {
      ei = (Eigenschaft) context;
    }
    else
    {
      try
      {
        ei = (Eigenschaft) Einstellungen.getDBService().createObject(
            Eigenschaft.class, null);
      }
      catch (RemoteException e)
      {
        throw new ApplicationException(JVereinPlugin.getI18n().tr(
            "Fehler bei der Erzeugung der neuen Eigenschaft"), e);
      }
    }
    GUI.startView(EigenschaftDetailView.class.getName(), ei);
  }
}
