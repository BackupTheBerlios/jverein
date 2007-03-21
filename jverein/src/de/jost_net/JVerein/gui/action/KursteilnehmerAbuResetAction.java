/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/KursteilnehmerAbuResetAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2007/03/21 12:09:32 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: KursteilnehmerAbuResetAction.java,v $
 * Revision 1.1  2007/03/21 12:09:32  jost
 * Neu: Abbuchungsdatum beim Kursteilnehmer kann zurückgesetzt werden.
 *
 * Revision 1.2  2007/02/23 20:26:00  jost
 * Mail- und Webadresse im Header korrigiert.
 *
 * Revision 1.1  2006/09/20 15:38:12  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import java.rmi.RemoteException;

import de.jost_net.JVerein.rmi.Kursteilnehmer;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.dialogs.YesNoDialog;
import de.willuhn.jameica.gui.parts.TablePart;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

/**
 * Ausf�hrungsdatum der Abbuchung eines Kursteilnehmers entfernen.
 */
public class KursteilnehmerAbuResetAction implements Action
{
  private TablePart table;

  public KursteilnehmerAbuResetAction(TablePart table)
  {
    this.table = table;
  }

  public void handleAction(Object context) throws ApplicationException
  {
    if (context == null || !(context instanceof Kursteilnehmer))
    {
      throw new ApplicationException("Kein Kursteilnehmer ausgew�hlt");
    }
    try
    {
      Kursteilnehmer kt = (Kursteilnehmer) context;
      if (kt.isNewObject())
      {
        return;
      }
      YesNoDialog d = new YesNoDialog(YesNoDialog.POSITION_CENTER);
      d.setTitle("Abbuchungsdatum zur�cksetzen");
      d
          .setText("Wollen Sie das Ausf�hrungsdatum der Abbuchung wirklich zur�cksetzen?");
      try
      {
        Boolean choice = (Boolean) d.open();
        if (!choice.booleanValue())
          return;
      }
      catch (Exception e)
      {
        Logger.error(
            "Fehler beim Reset des Abbuchungsdatums des Kursteilnehmers", e);
        return;
      }
      int ind = table.removeItem(kt);
      kt.resetAbbudatum();
      kt.store();
      table.addItem(kt, ind);

      GUI.getStatusBar().setSuccessText("Abbuchungsdatum zur�ckgesetzt.");
    }
    catch (RemoteException e)
    {
      String fehler = "Fehler beim Zur�cksetzen des Abbuchungsdatum des Kursteilnehmers.";
      GUI.getStatusBar().setErrorText(fehler);
      Logger.error(fehler, e);
    }
  }
}
