/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/BuchungKontoauszugZuordnungAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2010/10/10 08:52:50 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: BuchungKontoauszugZuordnungAction.java,v $
 * Revision 1.1  2010/10/10 08:52:50  jost
 * Kontoauszugsinformationen en Bloc zuweisen
 *
 **********************************************************************/

package de.jost_net.JVerein.gui.action;

import java.rmi.RemoteException;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.control.BuchungsControl;
import de.jost_net.JVerein.gui.dialogs.BuchungsartZuordnungDialog;
import de.jost_net.JVerein.gui.dialogs.KontoauszugZuordnungDialog;
import de.jost_net.JVerein.rmi.Buchung;
import de.jost_net.JVerein.rmi.Buchungsart;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

/**
 * Kontoauszugsinformationen zuordnen.
 */
public class BuchungKontoauszugZuordnungAction implements Action
{
  private BuchungsControl control;

  public BuchungKontoauszugZuordnungAction(BuchungsControl control)
  {
    this.control = control;
  }

  public void handleAction(Object context) throws ApplicationException
  {
    if (context == null
        || (!(context instanceof Buchung) && !(context instanceof Buchung[])))
    {
      throw new ApplicationException(JVereinPlugin.getI18n().tr(
          "Keine Buchung(en) ausgew�hlt"));
    }
    try
    {
      Buchung[] b = null;
      if (context instanceof Buchung)
      {
        b = new Buchung[1];
        b[0] = (Buchung) context;
      }
      if (context instanceof Buchung[])
      {
        b = (Buchung[]) context;
      }
      if (b != null && b.length > 0 && b[0].isNewObject())
      {
        return;
      }
      try
      {
        KontoauszugZuordnungDialog kaz = new KontoauszugZuordnungDialog(
            BuchungsartZuordnungDialog.POSITION_MOUSE);
        kaz.open();
        Integer auszug = kaz.getAuszugValue();
        Integer blatt = kaz.getBlattValue();
        int counter = 0;

        for (Buchung buchung : b)
        {
          boolean protect = (buchung.getAuszugsnummer().intValue() > 0 || buchung
              .getBlattnummer().intValue() > 0)
              && !kaz.getOverride();
          if (protect)
          {
            counter++;
          }
          else
          {
            buchung.setAuszugsnummer(auszug);
            buchung.setBlattnummer(blatt);
            buchung.store();
          }
        }
        control.getBuchungsList();
        String protecttext = "";
        if (counter > 0)
        {
          protecttext = JVereinPlugin.getI18n().tr(
              ", {0} Buchungen wurden nicht �berschrieben. ",
              new String[] { counter + "" });
        }
        GUI.getStatusBar().setSuccessText(
            JVereinPlugin.getI18n().tr("Kontoauszugsinformationen zugeordnet")
                + protecttext);
      }
      catch (Exception e)
      {
        Logger.error("Fehler", e);
        GUI.getStatusBar().setErrorText(
            JVereinPlugin.getI18n().tr(
                "Fehler bei der Zuordnung der Kontoauszugsinformationen"));
        return;
      }
    }
    catch (RemoteException e)
    {
      String fehler = JVereinPlugin.getI18n().tr("Fehler beim Speichern.");
      GUI.getStatusBar().setErrorText(fehler);
      Logger.error(fehler, e);
    }
  }
}
