/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/Attic/ReportListeAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2009/03/26 20:55:47 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: ReportListeAction.java,v $
 * Revision 1.1  2009/03/26 20:55:47  jost
 * Neu: Reports - Erste Version
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.gui.view.ReportListeView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class ReportListeAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    GUI.startView(ReportListeView.class.getName(), null /*
                                                         * new
                                                         * Reportart(Reportart
                                                         * .KURSTEILNEHMER)
                                                         */);
  }
}
