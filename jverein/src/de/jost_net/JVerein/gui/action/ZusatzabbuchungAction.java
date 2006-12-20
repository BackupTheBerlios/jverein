/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/Attic/ZusatzabbuchungAction.java,v $
 * $Revision: 1.2 $
 * $Date: 2006/12/20 20:25:44 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * jost@berlios.de
 * jverein.berlios.de
 * $Log: ZusatzabbuchungAction.java,v $
 * Revision 1.2  2006/12/20 20:25:44  jost
 * Patch von Ullrich Sch�fer, der die Primitive vs. Object Problematik adressiert.
 *
 * Revision 1.1  2006/09/20 15:38:12  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import java.rmi.RemoteException;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.gui.view.ZusatzabbuchungView;
import de.jost_net.JVerein.rmi.Mitglied;
import de.jost_net.JVerein.rmi.Zusatzabbuchung;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class ZusatzabbuchungAction implements Action
{
  private Mitglied m;

  public ZusatzabbuchungAction(Mitglied m)
  {
    super();
    this.m = m;
  }

  public void handleAction(Object context) throws ApplicationException
  {
    Zusatzabbuchung z = null;

    if (context != null && (context instanceof Zusatzabbuchung))
    {
      z = (Zusatzabbuchung) context;
    }
    else
    {
      try
      {
        z = (Zusatzabbuchung) Einstellungen.getDBService().createObject(
            Zusatzabbuchung.class, null);
        if (m != null)
        {
          z.setMitglied(new Integer(m.getID()).intValue());
        }
      }
      catch (RemoteException e)
      {
        throw new ApplicationException(
            "Fehler bei der Erzeugung einer neuen Zusatzabbuchung", e);
      }
    }
    GUI.startView(ZusatzabbuchungView.class.getName(), z);
  }
}
