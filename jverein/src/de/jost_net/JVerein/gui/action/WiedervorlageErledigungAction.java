/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/WiedervorlageErledigungAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2007/05/07 19:24:23 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: WiedervorlageErledigungAction.java,v $
 * Revision 1.1  2007/05/07 19:24:23  jost
 * Neu: Wiedervorlage
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import java.rmi.RemoteException;
import java.util.Date;

import de.jost_net.JVerein.rmi.Wiedervorlage;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.parts.TablePart;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

/**
 * Erledigungsdatum einer Wiedervorlage setzen/zurücksetzen.
 */
public class WiedervorlageErledigungAction implements Action
{
  private TablePart table;

  public WiedervorlageErledigungAction(TablePart table)
  {
    this.table = table;
  }

  public void handleAction(Object context) throws ApplicationException
  {
    if (context == null || !(context instanceof Wiedervorlage))
    {
      throw new ApplicationException("Keine Wiedervorlage ausgewählt");
    }
    try
    {
      Wiedervorlage w = (Wiedervorlage) context;
      if (w.isNewObject())
      {
        return;
      }
      if (w.getErledigung() == null)
      {
        w.setErledigung(new Date());
      }
      else
      {
        w.setErledigung(null);
      }
      int ind = table.removeItem(w);
      w.store();
      table.addItem(w, ind);
      GUI.getStatusBar().setSuccessText("Erledigungsdatum bearbeitet.");
    }
    catch (RemoteException e)
    {
      String fehler = "Fehler beim Verändern des Erledigungsdatums.";
      GUI.getStatusBar().setErrorText(fehler);
      Logger.error(fehler, e);
    }
  }
}
