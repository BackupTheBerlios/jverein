/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/BuchungsklasseSaldoAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2009/09/10 18:16:47 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: BuchungsklasseSaldoAction.java,v $
 * Revision 1.1  2009/09/10 18:16:47  jost
 * neu: Buchungsklassen
 *
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.gui.view.BuchungsklasseSaldoView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class BuchungsklasseSaldoAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    GUI.startView(BuchungsklasseSaldoView.class.getName(), null);
  }
}
