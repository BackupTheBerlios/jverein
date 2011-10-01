/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/DokumentationAction.java,v $
 * $Revision: 1.5 $
 * $Date: 2011/10/01 21:28:53 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.internal.action.Program;
import de.willuhn.jameica.messaging.StatusBarMessage;
import de.willuhn.jameica.system.Application;
import de.willuhn.util.ApplicationException;

public class DokumentationAction implements Action
{

  public void handleAction(Object context)
  {
    final Object cont = context;
    GUI.getDisplay().asyncExec(new Runnable()
    {

      public void run()
      {
        try
        {
          if (cont instanceof String)
          {
            new Program().handleAction(cont);
          }
          else
          {
            new Program()
                .handleAction("http://www.jverein.de/index.php5?title=Dokumentation");
          }
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
