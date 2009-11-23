/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/EigenschaftGruppeDetailAction.java,v $
 * $Revision: 1.2 $
 * $Date: 2009/11/23 20:39:21 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: EigenschaftGruppeDetailAction.java,v $
 * Revision 1.2  2009/11/23 20:39:21  jost
 * Bugfix L�sch-Button
 *
 * Revision 1.1  2009/11/17 20:55:27  jost
 * Neu: Eigenschaft und EigenschaftGruppe
 *
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import java.rmi.RemoteException;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.view.EigenschaftGruppeDetailView;
import de.jost_net.JVerein.rmi.EigenschaftGruppe;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class EigenschaftGruppeDetailAction implements Action
{
  private boolean neu;

  public EigenschaftGruppeDetailAction(boolean neu)
  {
    this.neu = neu;
  }

  public void handleAction(Object context) throws ApplicationException
  {
    EigenschaftGruppe eg = null;
    if (neu)
    {
      context = null;
    }
    if (context != null && (context instanceof EigenschaftGruppe))
    {
      eg = (EigenschaftGruppe) context;
    }
    else
    {
      try
      {
        eg = (EigenschaftGruppe) Einstellungen.getDBService().createObject(
            EigenschaftGruppe.class, null);
      }
      catch (RemoteException e)
      {
        throw new ApplicationException(JVereinPlugin.getI18n().tr(
            "Fehler bei der Erzeugung der neuen EigenschaftGruppe"), e);
      }
    }
    GUI.startView(EigenschaftGruppeDetailView.class.getName(), eg);
  }
}
