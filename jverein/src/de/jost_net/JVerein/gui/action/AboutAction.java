/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/AboutAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2006/09/20 15:38:12 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * jost@berlios.de
 * jverein.berlios.de
 * $Log: AboutAction.java,v $
 * Revision 1.1  2006/09/20 15:38:12  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.gui.view.AboutView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.dialogs.AbstractDialog;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

public class AboutAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    try
    {
      new AboutView(AbstractDialog.POSITION_CENTER).open();
    }
    catch (Exception e)
    {
      String fehler = "Fehler beim �ffnen des AboutView-Dialoges";
      Logger.error(fehler, e);
      throw new ApplicationException(fehler);
    }
  }
}
