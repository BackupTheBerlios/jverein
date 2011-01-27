/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/AdressenSucheAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2011/01/27 22:16:06 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: AdressenSucheAction.java,v $
 * Revision 1.1  2011/01/27 22:16:06  jost
 * Neu: Speicherung von weiteren Adressen in der Mitgliedertabelle
 *
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
