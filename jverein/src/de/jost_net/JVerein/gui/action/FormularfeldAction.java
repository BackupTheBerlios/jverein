/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/FormularfeldAction.java,v $
 * $Revision: 1.3 $
 * $Date: 2011/10/01 21:33:11 $
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
import de.jost_net.JVerein.gui.view.FormularfeldView;
import de.jost_net.JVerein.rmi.Formular;
import de.jost_net.JVerein.rmi.Formularfeld;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

public class FormularfeldAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    Formularfeld ff = null;
    Formular f = null;

    if (context != null && (context instanceof Formular))
    {
      f = (Formular) context;
    }
    if (context != null && (context instanceof Formularfeld))
    {
      ff = (Formularfeld) context;
    }
    else
    {
      try
      {
        ff = (Formularfeld) Einstellungen.getDBService().createObject(
            Formularfeld.class, null);
        ff.setFormular(f);
      }
      catch (RemoteException e)
      {
        Logger.error("Fehler", e);
        throw new ApplicationException(JVereinPlugin.getI18n().tr(
            "Fehler bei der Erzeugung eines neuen Formularfeldes"), e);
      }
    }
    GUI.startView(FormularfeldView.class.getName(), ff);
  }
}
