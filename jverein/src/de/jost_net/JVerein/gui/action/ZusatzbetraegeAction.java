/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/ZusatzbetraegeAction.java,v $
 * $Revision: 1.3 $
 * $Date: 2011/02/12 14:52:19 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: ZusatzbetraegeAction.java,v $
 * Revision 1.3  2011/02/12 14:52:19  jost
 * Warnung wenn bei einem neuen Mitglied ein neuer Satz aufgenommen werden soll.
 *
 * Revision 1.2  2009/06/11 21:02:05  jost
 * Vorbereitung I18N
 *
 * Revision 1.1  2008/12/22 21:06:50  jost
 * Zusatzabbuchung->Zusatzbetrag
 *
 * Revision 1.3  2007/02/23 20:26:00  jost
 * Mail- und Webadresse im Header korrigiert.
 *
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
              "Neues Mitglied bitte erst speichern. Dann k�nnen Zusatzbetr�ge aufgenommen werden.");
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
