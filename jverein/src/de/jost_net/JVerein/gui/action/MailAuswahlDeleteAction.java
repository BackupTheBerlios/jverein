/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/MailAuswahlDeleteAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2010/02/01 20:57:02 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: MailAuswahlDeleteAction.java,v $
 * Revision 1.1  2010/02/01 20:57:02  jost
 * Neu: Einfache Mailfunktion
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import java.rmi.RemoteException;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.control.MailControl;
import de.jost_net.JVerein.rmi.MailEmpfaenger;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

/**
 * Loeschen einer Mail
 */
public class MailAuswahlDeleteAction implements Action
{
  private MailControl control = null;

  public MailAuswahlDeleteAction(MailControl control)
  {
    this.control = control;
  }

  public void handleAction(Object context) throws ApplicationException
  {
    if (context == null || !(context instanceof MailEmpfaenger))
    {
      throw new ApplicationException(JVereinPlugin.getI18n().tr(
          "Keinen Empfänger ausgewählt"));
    }
    try
    {
      MailEmpfaenger me = (MailEmpfaenger) context;
      control.removeEmpfaenger(me);
    }
    catch (RemoteException e)
    {
      String fehler = JVereinPlugin.getI18n().tr(
          "Fehler beim entfernen des MailEmpfängers");
      GUI.getStatusBar().setErrorText(fehler);
      Logger.error(fehler, e);
    }
  }
}
