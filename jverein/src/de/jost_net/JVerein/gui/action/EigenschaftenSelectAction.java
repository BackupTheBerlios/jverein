/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/Attic/EigenschaftenSelectAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/01/25 16:00:54 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: EigenschaftenSelectAction.java,v $
 * Revision 1.1  2008/01/25 16:00:54  jost
 * Neu: Eigenschaften des Mitgliedes
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import java.rmi.RemoteException;

import de.jost_net.JVerein.gui.control.EigenschaftenControl;
import de.jost_net.JVerein.gui.control.HilfsEigenschaft;
import de.willuhn.jameica.gui.Action;
import de.willuhn.util.ApplicationException;

public class EigenschaftenSelectAction implements Action
{
  private EigenschaftenControl control;

  public EigenschaftenSelectAction(EigenschaftenControl control)
  {
    this.control = control;
  }

  public void handleAction(Object context) throws ApplicationException
  {
    HilfsEigenschaft ei = null;
    if (context != null && context instanceof HilfsEigenschaft)
    {
      ei = (HilfsEigenschaft) context;
      try
      {
        control.getNeu().setValue(ei.getEigenschaft());
      }
      catch (RemoteException e)
      {
        e.printStackTrace();
      }
    }
  }
}
