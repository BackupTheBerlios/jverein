/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/Attic/TermineAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2010/11/25 15:11:30 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: TermineAction.java,v $
 * Revision 1.1  2010/11/25 15:11:30  jost
 * Initial Commit
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.gui.view.TermineView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;

/**
 * Startet die View mit den Terminen.
 */
public class TermineAction implements Action
{

  /**
   * @see de.willuhn.jameica.gui.Action#handleAction(java.lang.Object)
   */
  public void handleAction(Object context)
  {
    GUI.startView(TermineView.class, context);
  }
}

/**********************************************************************
 * $Log: TermineAction.java,v $
 * Revision 1.1  2010/11/25 15:11:30  jost
 * Initial Commit
 * Revision 1.1 2010-11-19 18:37:19 willuhn
 * 
 * @N Erste Version der Termin-View mit Appointment-Providern
 * 
 **********************************************************************/
