/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/MailListeAction.java,v $
 * $Revision: 1.2 $
 * $Date: 2010/10/15 09:58:02 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: MailListeAction.java,v $
 * Revision 1.2  2010/10/15 09:58:02  jost
 * Code aufgeräumt
 *
 * Revision 1.1  2010-02-01 20:57:35  jost
 * Neu: Einfache Mailfunktion
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.gui.view.MailUebersichtView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;

public class MailListeAction implements Action
{

  public void handleAction(Object context)
  {
    GUI.startView(MailUebersichtView.class.getName(), null);
  }
}
