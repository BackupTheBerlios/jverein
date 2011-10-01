/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/FormularfelderListeAction.java,v $
 * $Revision: 1.3 $
 * $Date: 2011/10/01 21:33:39 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.JVereinPlugin;
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
      throw new ApplicationException(JVereinPlugin.getI18n().tr(
          "Kein Formular zur Anzeige ausgewählt"));
    }
    GUI.startView(FormularfelderListeView.class.getName(), f);
  }
}
