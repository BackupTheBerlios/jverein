/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/BuchungNeuAction.java,v $
 * $Revision: 1.3 $
 * $Date: 2011/10/01 21:25:28 $
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
import de.jost_net.JVerein.gui.view.BuchungView;
import de.jost_net.JVerein.rmi.Buchung;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;

public class BuchungNeuAction implements Action
{

  public void handleAction(Object context)
  {
    Buchung buch;
    try
    {
      buch = (Buchung) Einstellungen.getDBService().createObject(Buchung.class,
          null);
      GUI.startView(BuchungView.class, buch);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }
}
