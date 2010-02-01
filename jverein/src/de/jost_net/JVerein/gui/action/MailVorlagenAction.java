/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/MailVorlagenAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2010/02/01 20:57:35 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: MailVorlagenAction.java,v $
 * Revision 1.1  2010/02/01 20:57:35  jost
 * Neu: Einfache Mailfunktion
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.gui.view.MailVorlagenUebersichtView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class MailVorlagenAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    GUI.startView(MailVorlagenUebersichtView.class.getName(), null);
  }
}
