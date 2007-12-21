/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/DokumentationAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2007/12/21 14:48:48 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: DokumentationAction.java,v $
 * Revision 1.1  2007/12/21 14:48:48  jost
 * PDF-Dokumentation -> Wiki
 *
 * Revision 1.1  2007/07/20 20:15:19  jost
 * Neu
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import java.io.File;

import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.internal.action.Program;
import de.willuhn.jameica.messaging.StatusBarMessage;
import de.willuhn.jameica.system.Application;
import de.willuhn.util.ApplicationException;

public class DokumentationAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    GUI.getDisplay().asyncExec(new Runnable()
    {
      public void run()
      {
        try
        {
          new Program().handleAction(new File(
              "http://www.jverein.de/index.php5?title=Dokumentation"));
        }
        catch (ApplicationException ae)
        {
          Application.getMessagingFactory().sendMessage(
              new StatusBarMessage(ae.getLocalizedMessage(),
                  StatusBarMessage.TYPE_ERROR));
        }
      }
    });
  }
}
