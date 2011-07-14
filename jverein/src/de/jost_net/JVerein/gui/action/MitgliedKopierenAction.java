/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/MitgliedKopierenAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2011/07/14 19:37:27 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: MitgliedKopierenAction.java,v $
 * Revision 1.1  2011/07/14 19:37:27  jost
 * Neu: Mitglied kopieren
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.view.AdresseDetailView;
import de.jost_net.JVerein.gui.view.MitgliedDetailView;
import de.jost_net.JVerein.rmi.Mitglied;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class MitgliedKopierenAction implements Action
{

  public void handleAction(Object context) throws ApplicationException
  {
    if (context == null || !(context instanceof Mitglied))
    {
      throw new ApplicationException("kein Mitglied ausgewählt");
    }
    Mitglied m = null;
    try
    {
      m = (Mitglied) context;
      m.setID(null);
      if (m.getAdresstyp().getJVereinid() == 1)
      {
        GUI.startView(new MitgliedDetailView(), m);
      }
      else
      {
        GUI.startView(new AdresseDetailView(), m);
      }
    }
    catch (Exception e)
    {
      throw new ApplicationException(JVereinPlugin.getI18n().tr(
          "Fehler beim kopieren eines Mitgliedes"), e);
    }
  }
}
