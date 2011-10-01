/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/SpendenAction.java,v $
 * $Revision: 1.2 $
 * $Date: 2011/10/01 21:40:00 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 **********************************************************************/

package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.gui.view.SpendenView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

/**
 * Action zum Starten der Spenden-View.
 */
public class SpendenAction implements Action
{

  /**
   * @see de.willuhn.jameica.gui.Action#handleAction(java.lang.Object)
   */
  public void handleAction(Object context) throws ApplicationException
  {
    GUI.startView(SpendenView.class, null);
  }

}

/**********************************************************************
 * $Log: SpendenAction.java,v $
 * Revision 1.2  2011/10/01 21:40:00  jost
 * Log-Einträge entfernt. Zeigt Eclipse-History-View viel besser an. Macht den Quelltext schlanker.
 * Revision 1.1 2011-07-24 18:02:54 jost Neu:
 * Spenden für JVerein Revision 1.1 2010-08-20 12:42:02 willuhn
 * 
 * @N Neuer Spenden-Aufruf. Ich bin gespannt, ob das klappt ;)
 * 
 **********************************************************************/
