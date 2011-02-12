/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/WiedervorlageAction.java,v $
 * $Revision: 1.3 $
 * $Date: 2011/02/12 14:52:08 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: WiedervorlageAction.java,v $
 * Revision 1.3  2011/02/12 14:52:08  jost
 * Warnung wenn bei einem neuen Mitglied ein neuer Satz aufgenommen werden soll.
 *
 * Revision 1.2  2009/06/11 21:02:05  jost
 * Vorbereitung I18N
 *
 * Revision 1.1  2007/05/07 19:23:41  jost
 * Neu: Wiedervorlage
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import java.rmi.RemoteException;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.view.WiedervorlageView;
import de.jost_net.JVerein.rmi.Mitglied;
import de.jost_net.JVerein.rmi.Wiedervorlage;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class WiedervorlageAction implements Action
{
  private Mitglied m;

  public WiedervorlageAction(Mitglied m)
  {
    super();
    this.m = m;
  }

  public void handleAction(Object context) throws ApplicationException
  {
    Wiedervorlage w = null;

    if (context != null && (context instanceof Wiedervorlage))
    {
      w = (Wiedervorlage) context;
    }
    else
    {
      try
      {
        w = (Wiedervorlage) Einstellungen.getDBService().createObject(
            Wiedervorlage.class, null);
        if (m != null)
        {
          if (m.getID() == null)
          {
            throw new ApplicationException(
                "Neues Mitglied bitte erst speichern. Dann k�nnen Wiedervorlagen aufgenommen werden.");
          }
          w.setMitglied(new Integer(m.getID()).intValue());
        }
      }
      catch (RemoteException e)
      {
        throw new ApplicationException(JVereinPlugin.getI18n().tr(
            "Fehler bei der Erzeugung einer neuen Wiedervorlage"), e);
      }
    }
    GUI.startView(WiedervorlageView.class.getName(), w);
  }
}
