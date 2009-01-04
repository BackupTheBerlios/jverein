/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/BuchungBuchungsartZuordnungAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2009/01/04 16:26:03 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: BuchungBuchungsartZuordnungAction.java,v $
 * Revision 1.1  2009/01/04 16:26:03  jost
 * Neu: Für mehrere Buchungen gleichzeitig die Buchungsart festlegen.
 *
 **********************************************************************/

package de.jost_net.JVerein.gui.action;

import java.rmi.RemoteException;

import de.jost_net.JVerein.gui.control.BuchungsControl;
import de.jost_net.JVerein.gui.dialogs.BuchungsartZuordnungDialog;
import de.jost_net.JVerein.rmi.Buchung;
import de.jost_net.JVerein.rmi.Buchungsart;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

/**
 * Buchungsart zuordnen.
 */
public class BuchungBuchungsartZuordnungAction implements Action
{
  private BuchungsControl control;

  public BuchungBuchungsartZuordnungAction(BuchungsControl control)
  {
    this.control = control;
  }

  public void handleAction(Object context) throws ApplicationException
  {
    if (context == null
        || (!(context instanceof Buchung) && !(context instanceof Buchung[])))
    {
      throw new ApplicationException("Keine Buchung(en) ausgewählt");
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
        BuchungsartZuordnungDialog baz = new BuchungsartZuordnungDialog(
            BuchungsartZuordnungDialog.POSITION_MOUSE);
        baz.open();
        Buchungsart ba = baz.getBuchungsart();
        int counter = 0;

        for (Buchung buchung : b)
        {
          boolean protect = buchung.getBuchungsart() != null
              && !baz.getOverride();
          if (protect)
          {
            counter++;
          }
          else
          {
            buchung.setBuchungsart(new Integer(ba.getID()));
            buchung.store();
          }
        }
        control.getBuchungsList();
        String protecttext = "";
        if (counter > 0)
        {
          protecttext = ", " + counter
              + " Buchungen wurden nicht überschrieben. ";
        }
        GUI.getStatusBar().setSuccessText(
            "Buchungsarten zugeordnet" + protecttext);
      }
      catch (Exception e)
      {
        Logger.error("Fehler", e);
        GUI.getStatusBar().setErrorText(
            "Fehler bei der Zuordnung der Buchungsart");
        return;
      }
    }
    catch (RemoteException e)
    {
      String fehler = "Fehler beim Speichern.";
      GUI.getStatusBar().setErrorText(fehler);
      Logger.error(fehler, e);
    }
  }
}
