/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/FormularfelderListeAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/07/18 20:07:42 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: FormularfelderListeAction.java,v $
 * Revision 1.1  2008/07/18 20:07:42  jost
 * Neu: Formulare
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.gui.view.FormularfelderListeView;
import de.jost_net.JVerein.rmi.Formular;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class FormularfelderListeAction implements Action
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
      throw new ApplicationException("Kein Formular zur Anzeige ausgewählt");
    }
    GUI.startView(FormularfelderListeView.class.getName(), f);
  }
}
