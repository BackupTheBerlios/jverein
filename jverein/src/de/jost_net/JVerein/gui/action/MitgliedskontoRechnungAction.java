/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/MitgliedskontoRechnungAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2010/08/04 10:39:50 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: MitgliedskontoRechnungAction.java,v $
 * Revision 1.1  2010/08/04 10:39:50  jost
 * Prerelease Rechnung
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.gui.view.MitgliedskontoRechnungView;
import de.jost_net.JVerein.gui.view.RechnungView;
import de.jost_net.JVerein.rmi.Abrechnung;
import de.jost_net.JVerein.rmi.Mitgliedskonto;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class MitgliedskontoRechnungAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    if (context != null && context instanceof Mitgliedskonto)
    {
      Mitgliedskonto mk = (Mitgliedskonto) context;
      GUI.startView(MitgliedskontoRechnungView.class.getName(), mk);
    }
    else if (context != null && context instanceof Mitgliedskonto[])
    {
      Mitgliedskonto[] mk = (Mitgliedskonto[]) context;
      GUI.startView(MitgliedskontoRechnungView.class.getName(), mk);
    }
  }
}
