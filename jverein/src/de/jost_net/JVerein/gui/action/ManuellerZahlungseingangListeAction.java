/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/Attic/ManuellerZahlungseingangListeAction.java,v $
 * $Revision: 1.2 $
 * $Date: 2010/07/28 07:25:48 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: ManuellerZahlungseingangListeAction.java,v $
 * Revision 1.2  2010/07/28 07:25:48  jost
 * deprecated
 *
 * Revision 1.1  2007/03/13 19:56:35  jost
 * Neu: Manueller Zahlungseingang.
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.gui.view.ManuellerZahlungseingangListeView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

/**
 * @deprecated In Version 1.5 ausmustern
 */
public class ManuellerZahlungseingangListeAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    GUI.startView(ManuellerZahlungseingangListeView.class.getName(), null);
  }
}
