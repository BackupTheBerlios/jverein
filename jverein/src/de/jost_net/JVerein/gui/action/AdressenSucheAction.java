/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/AdressenSucheAction.java,v $
 * $Revision: 1.2 $
 * $Date: 2011/10/01 21:18:29 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.gui.view.AdressenSucheView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class AdressenSucheAction implements Action
{

  public AdressenSucheAction()
  {
  }

  public void handleAction(Object context) throws ApplicationException
  {
    GUI.startView(AdressenSucheView.class.getName(), null);
  }

}
