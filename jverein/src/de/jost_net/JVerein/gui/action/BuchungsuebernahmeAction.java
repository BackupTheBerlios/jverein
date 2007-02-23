/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/BuchungsuebernahmeAction.java,v $
 * $Revision: 1.2 $
 * $Date: 2007/02/23 20:25:42 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
* $Log: BuchungsuebernahmeAction.java,v $
* Revision 1.2  2007/02/23 20:25:42  jost
* Mail- und Webadresse im Header korrigiert.
*
 * Revision 1.1  2006/09/20 15:38:12  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.gui.view.BuchungsUebernahmeView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class BuchungsuebernahmeAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    GUI.startView(BuchungsUebernahmeView.class.getName(), null);
  }

}
