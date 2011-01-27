/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/AdresseDetailAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2011/01/27 22:15:49 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: AdresseDetailAction.java,v $
 * Revision 1.1  2011/01/27 22:15:49  jost
 * Neu: Speicherung von weiteren Adressen in der Mitgliedertabelle
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.dialogs.PersonenartDialog;
import de.jost_net.JVerein.gui.view.AdresseDetailView;
import de.jost_net.JVerein.rmi.Mitglied;
import de.jost_net.JVerein.rmi.Mitgliedskonto;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class AdresseDetailAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    Mitglied m = null;
    try
    {
      if (context != null && (context instanceof Mitglied))
      {
        m = (Mitglied) context;
      }
      else if (context != null && (context instanceof Mitgliedskonto))
      {
        Mitgliedskonto mk = (Mitgliedskonto) context;
        m = mk.getMitglied();
      }
      else
      {
        m = (Mitglied) Einstellungen.getDBService().createObject(
            Mitglied.class, null);
        if (Einstellungen.getEinstellung().getJuristischePersonen())
        {
          PersonenartDialog pad = new PersonenartDialog(
              PersonenartDialog.POSITION_CENTER);
          String pa = (String) pad.open();
          m.setPersonenart(pa);
        }
        else
        {
          m.setPersonenart("n");
        }
      }
    }
    catch (Exception e)
    {
      throw new ApplicationException(JVereinPlugin.getI18n().tr(
          "Fehler bei der Erzeugung einer neuen Adresse"), e);
    }
    GUI.startView(new AdresseDetailView(), m);
  }
}
