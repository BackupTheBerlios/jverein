/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/MailVorlageDetailAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2010/02/01 20:57:35 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: MailVorlageDetailAction.java,v $
 * Revision 1.1  2010/02/01 20:57:35  jost
 * Neu: Einfache Mailfunktion
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import java.rmi.RemoteException;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.view.MailVorlageDetailView;
import de.jost_net.JVerein.rmi.MailVorlage;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class MailVorlageDetailAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    MailVorlage mv = null;

    if (context != null && (context instanceof MailVorlage))
    {
      mv = (MailVorlage) context;
    }
    else
    {
      try
      {
        mv = (MailVorlage) Einstellungen.getDBService().createObject(
            MailVorlage.class, null);
      }
      catch (RemoteException e)
      {
        throw new ApplicationException(JVereinPlugin.getI18n().tr(
            "Fehler bei der Erzeugung der neuen MailVorlage"), e);
      }
    }
    GUI.startView(MailVorlageDetailView.class.getName(), mv);
  }
}
