/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/MitgliedSucheAction.java,v $
 * $Revision: 1.5 $
 * $Date: 2011/01/27 22:17:19 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: MitgliedSucheAction.java,v $
 * Revision 1.5  2011/01/27 22:17:19  jost
 * Neu: Speicherung von weiteren Adressen in der Mitgliedertabelle
 *
 * Revision 1.4  2009/06/11 21:02:05  jost
 * Vorbereitung I18N
 *
 * Revision 1.3  2008/01/25 16:01:23  jost
 * Neu: Eigenschaften des Mitgliedes
 *
 * Revision 1.2  2007/02/23 20:26:00  jost
 * Mail- und Webadresse im Header korrigiert.
 *
 * Revision 1.1  2006/09/20 15:38:12  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.gui.view.MitgliederSucheView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class MitgliedSucheAction implements Action
{

  public MitgliedSucheAction()
  {
  }

  public void handleAction(Object context) throws ApplicationException
  {
    GUI.startView(MitgliederSucheView.class.getName(), null);
  }

}
