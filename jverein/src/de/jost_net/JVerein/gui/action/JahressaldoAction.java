/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/JahressaldoAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/05/25 19:36:00 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: JahressaldoAction.java,v $
 * Revision 1.1  2008/05/25 19:36:00  jost
 * Neu: Jahressaldo
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.gui.view.JahressaldoView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class JahressaldoAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    GUI.startView(JahressaldoView.class.getName(), null);
  }
}
