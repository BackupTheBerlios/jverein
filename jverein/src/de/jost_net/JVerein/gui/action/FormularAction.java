/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/FormularAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/07/18 20:05:55 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: FormularAction.java,v $
 * Revision 1.1  2008/07/18 20:05:55  jost
 * Neu: Formulare
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import java.rmi.RemoteException;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.gui.view.FormularDetailView;
import de.jost_net.JVerein.rmi.Formular;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class FormularAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    Formular f = null;

    if (context != null && (context instanceof Formular))
    {
      f = (Formular) context;
    }
    else
    {
      try
      {
        f = (Formular) Einstellungen.getDBService().createObject(
            Formular.class, null);
      }
      catch (RemoteException e)
      {
        throw new ApplicationException(
            "Fehler bei der Erzeugung eines neuen Formulars", e);
      }
    }
    GUI.startView(FormularDetailView.class.getName(), f);
  }
}
