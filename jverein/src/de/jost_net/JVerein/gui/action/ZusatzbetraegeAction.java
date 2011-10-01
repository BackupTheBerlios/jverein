/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/ZusatzbetraegeAction.java,v $
 * $Revision: 1.4 $
 * $Date: 2011/10/01 21:40:00 $
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
import de.jost_net.JVerein.gui.view.ZusatzbetragView;
import de.jost_net.JVerein.rmi.Mitglied;
import de.jost_net.JVerein.rmi.Zusatzbetrag;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class ZusatzbetraegeAction implements Action
{
  private Mitglied m;

  public ZusatzbetraegeAction(Mitglied m)
  {
    super();
    this.m = m;
  }

  public void handleAction(Object context) throws ApplicationException
  {
    Zusatzbetrag z = null;

    if (context != null && (context instanceof Zusatzbetrag))
    {
      z = (Zusatzbetrag) context;
    }
    else
    {
      try
      {
        z = (Zusatzbetrag) Einstellungen.getDBService().createObject(
            Zusatzbetrag.class, null);
        if (m.getID() == null)
        {
          throw new ApplicationException(
              "Neues Mitglied bitte erst speichern. Dann können Zusatzbeträge aufgenommen werden.");
        }

        if (m != null)
        {
          z.setMitglied(new Integer(m.getID()).intValue());
        }
      }
      catch (RemoteException e)
      {
        throw new ApplicationException(JVereinPlugin.getI18n().tr(
            "Fehler bei der Erzeugung eines neuen Zusatzbetrages"), e);
      }
    }
    GUI.startView(ZusatzbetragView.class.getName(), z);
  }
}
