/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/FormularListeAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/07/18 20:08:05 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: FormularListeAction.java,v $
 * Revision 1.1  2008/07/18 20:08:05  jost
 * Neu: Formulare
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.gui.view.FormularListeView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class FormularListeAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    GUI.startView(FormularListeView.class.getName(), null);
  }
}
