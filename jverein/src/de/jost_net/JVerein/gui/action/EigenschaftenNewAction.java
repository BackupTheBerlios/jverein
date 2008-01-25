/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/Attic/EigenschaftenNewAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/01/25 15:59:47 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: EigenschaftenNewAction.java,v $
 * Revision 1.1  2008/01/25 15:59:47  jost
 * Neu: Eigenschaften des Mitgliedes
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.gui.control.EigenschaftenControl;
import de.jost_net.JVerein.gui.dialogs.EigenschaftenNeuDialog;
import de.jost_net.JVerein.rmi.Mitglied;
import de.willuhn.jameica.gui.Action;
import de.willuhn.util.ApplicationException;

public class EigenschaftenNewAction implements Action
{
  private Mitglied m;

  private EigenschaftenControl control;

  public EigenschaftenNewAction(EigenschaftenControl control, Mitglied m)
  {
    this.control = control;
    this.m = m;
  }

  public void handleAction(Object context) throws ApplicationException
  {
    try
    {
      EigenschaftenNeuDialog ned = new EigenschaftenNeuDialog(m);
      ned.open();
      control.refreshTable();
    }
    catch (Exception e)
    {
      throw new ApplicationException(
          "Diaglog zur Eingabe neuer Eigenschaften kann nicht ge�ffnet werden ("
              + e.getMessage() + ")");
    }
  }
}
