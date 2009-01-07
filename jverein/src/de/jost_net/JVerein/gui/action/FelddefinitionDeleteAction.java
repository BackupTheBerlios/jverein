/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/FelddefinitionDeleteAction.java,v $
 * $Revision: 1.2 $
 * $Date: 2009/01/07 19:38:23 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: FelddefinitionDeleteAction.java,v $
 * Revision 1.2  2009/01/07 19:38:23  jost
 * MySQL-Kompatibilit�t hergestellt.
 *
 * Revision 1.1  2008/04/10 18:57:04  jost
 * Neu: Benutzerdefinierte Datenfelder
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import java.rmi.RemoteException;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.rmi.Felddefinition;
import de.jost_net.JVerein.rmi.Zusatzfelder;
import de.willuhn.datasource.rmi.DBIterator;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.dialogs.YesNoDialog;
import de.willuhn.jameica.gui.parts.TablePart;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

/**
 * Loeschen einer Felddefiniton
 */
public class FelddefinitionDeleteAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    if (context instanceof TablePart)
    {
      TablePart tp = (TablePart) context;
      context = tp.getSelection();
    }
    if (context == null || !(context instanceof Felddefinition))
    {
      throw new ApplicationException("Keine Felddefinition ausgew�hlt");
    }
    try
    {
      Felddefinition fd = (Felddefinition) context;
      DBIterator it = Einstellungen.getDBService().createList(Zusatzfelder.class);
      it.addFilter("felddefinition=?", new Object[] { fd.getID() });
      it.addFilter("feld is not null");
      it.addFilter("feld <>''");
      if (it.size() > 0)
      {
        throw new ApplicationException(
            "Dieses Feld ist noch bei einem Mitglied gespeichert");
      }
      if (fd.isNewObject())
      {
        return;
      }
      YesNoDialog d = new YesNoDialog(YesNoDialog.POSITION_CENTER);
      d.setTitle("Felddefinition l�schen");
      d.setText("Wollen Sie diese Felddefinition wirklich l�schen?");

      try
      {
        Boolean choice = (Boolean) d.open();
        if (!choice.booleanValue())
        {
          return;
        }
      }
      catch (Exception e)
      {
        Logger.error("Fehler beim L�schen der Felddefinition", e);
        return;
      }
      
      fd.delete();
      GUI.getStatusBar().setSuccessText("Felddefinition gel�scht.");
    }
    catch (RemoteException e)
    {
      String fehler = "Fehler beim L�schen der Felddefinition";
      GUI.getStatusBar().setErrorText(fehler);
      Logger.error(fehler, e);
    }
  }
}
