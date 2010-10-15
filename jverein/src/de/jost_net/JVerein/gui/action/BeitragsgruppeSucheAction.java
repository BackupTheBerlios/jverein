/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/BeitragsgruppeSucheAction.java,v $
 * $Revision: 1.3 $
 * $Date: 2010/10/15 09:58:01 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: BeitragsgruppeSucheAction.java,v $
 * Revision 1.3  2010/10/15 09:58:01  jost
 * Code aufgeräumt
 *
 * Revision 1.2  2007-02-23 20:25:42  jost
 * Mail- und Webadresse im Header korrigiert.
 *
 * Revision 1.1  2006/09/20 15:38:12  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.gui.view.BeitragsgruppeSucheView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;

public class BeitragsgruppeSucheAction implements Action
{

  public void handleAction(Object context)
  {
    GUI.startView(BeitragsgruppeSucheView.class.getName(), null);
  }
}
