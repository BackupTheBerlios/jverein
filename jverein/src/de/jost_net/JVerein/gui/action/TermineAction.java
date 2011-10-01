/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/Attic/TermineAction.java,v $
 * $Revision: 1.2 $
 * $Date: 2011/10/01 21:40:00 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
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
 * Revision 1.2  2011/10/01 21:40:00  jost
 * Log-Einträge entfernt. Zeigt Eclipse-History-View viel besser an. Macht den Quelltext schlanker.
 * Revision 1.1 2010-11-25 15:11:30 jost Initial
 * Commit Revision 1.1 2010-11-19 18:37:19 willuhn
 * 
 * @N Erste Version der Termin-View mit Appointment-Providern
 * 
 **********************************************************************/
