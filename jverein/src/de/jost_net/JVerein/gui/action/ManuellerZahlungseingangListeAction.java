/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/Attic/ManuellerZahlungseingangListeAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2007/03/13 19:56:35 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: ManuellerZahlungseingangListeAction.java,v $
 * Revision 1.1  2007/03/13 19:56:35  jost
 * Neu: Manueller Zahlungseingang.
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.gui.view.ManuellerZahlungseingangListeView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class ManuellerZahlungseingangListeAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    GUI.startView(ManuellerZahlungseingangListeView.class.getName(), null);
  }
}