/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/MailAnhangDeleteAction.java,v $
 * $Revision: 1.2 $
 * $Date: 2011/10/01 21:39:59 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import java.rmi.RemoteException;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.control.MailControl;
import de.jost_net.JVerein.rmi.MailAnhang;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

/**
 * L�schen eines Mailanhanges
 */
public class MailAnhangDeleteAction implements Action
{
  private MailControl control = null;

  public MailAnhangDeleteAction(MailControl control)
  {
    this.control = control;
  }

  public void handleAction(Object context) throws ApplicationException
  {
    if (context == null || !(context instanceof MailAnhang))
    {
      throw new ApplicationException(JVereinPlugin.getI18n().tr(
          "Keinen Mail-Anhang ausgew�hlt"));
    }
    try
    {
      MailAnhang ma = (MailAnhang) context;
      control.removeAnhang(ma);
    }
    catch (RemoteException e)
    {
      String fehler = JVereinPlugin.getI18n().tr(
          "Fehler beim entfernen eines Mailanhanges");
      GUI.getStatusBar().setErrorText(fehler);
      Logger.error(fehler, e);
    }
  }
}
