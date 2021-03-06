/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/AbrechnungslaufDeleteAction.java,v $
 * $Revision: 1.6 $
 * $Date: 2011/10/01 21:17:04 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import java.rmi.RemoteException;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.rmi.Abrechnungslauf;
import de.jost_net.JVerein.rmi.Buchung;
import de.jost_net.JVerein.rmi.Jahresabschluss;
import de.jost_net.JVerein.rmi.Mitgliedskonto;
import de.jost_net.JVerein.util.JVDateFormatTTMMJJJJ;
import de.willuhn.datasource.rmi.DBIterator;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.dialogs.YesNoDialog;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

/**
 * L�schen eines Abrechnungslaufes
 */
public class AbrechnungslaufDeleteAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    if (context == null || !(context instanceof Abrechnungslauf))
    {
      throw new ApplicationException(JVereinPlugin.getI18n().tr(
          "Keinen Abrechnungslauf ausgew�hlt"));
    }
    try
    {
      Abrechnungslauf abrl = (Abrechnungslauf) context;
      if (abrl.isNewObject())
      {
        return;
      }

      YesNoDialog d = new YesNoDialog(YesNoDialog.POSITION_CENTER);
      d.setTitle(JVereinPlugin.getI18n().tr(
          "Abrechnungslauf " + abrl.getID() + " l�schen"));
      d.setText(JVereinPlugin.getI18n().tr(
          "Wollen Sie diesen Abrechnungslauf wirklich l�schen?"));

      try
      {
        Boolean choice = (Boolean) d.open();
        if (!choice.booleanValue())
        {
          return;
        }
      }
      catch (Exception e)
      {
        Logger.error(
            JVereinPlugin.getI18n().tr(
                "Fehler beim L�schen eines Abrechnungslaufes"), e);
        return;
      }
      DBIterator it = Einstellungen.getDBService().createList(Buchung.class);
      it.addFilter("abrechnungslauf = ?", new Object[] { abrl.getID() });
      while (it.hasNext())
      {
        Buchung bu = (Buchung) it.next();
        Buchung b = (Buchung) Einstellungen.getDBService().createObject(
            Buchung.class, bu.getID());
        Jahresabschluss ja = b.getJahresabschluss();
        if (ja != null)
        {
          throw new ApplicationException(JVereinPlugin.getI18n().tr(
              "Buchung wurde bereits am {0} von {1} abgeschlossen.",
              new String[] { new JVDateFormatTTMMJJJJ().format(ja.getDatum()),
                  ja.getName() }));
        }
        b.setMitgliedskontoID(null);
        b.store();
        b.delete();
      }
      it = Einstellungen.getDBService().createList(Mitgliedskonto.class);
      it.addFilter("abrechnungslauf = ?", new Object[] { abrl.getID() });
      while (it.hasNext())
      {
        Mitgliedskonto mkt = (Mitgliedskonto) it.next();
        DBIterator it2 = Einstellungen.getDBService().createList(Buchung.class);
        it2.addFilter("mitgliedskonto = ?", new Object[] { mkt.getID() });
        while (it2.hasNext())
        {
          Buchung bu = (Buchung) it2.next();
          Buchung b = (Buchung) Einstellungen.getDBService().createObject(
              Buchung.class, bu.getID());
          b.setMitgliedskontoID(null);
          b.store();
        }
        Mitgliedskonto mk = (Mitgliedskonto) Einstellungen.getDBService()
            .createObject(Mitgliedskonto.class, mkt.getID());
        mk.delete();
      }

      abrl.delete();
      GUI.getStatusBar().setSuccessText(
          JVereinPlugin.getI18n().tr("Abrechnungslauf gel�scht."));
    }
    catch (RemoteException e)
    {
      String fehler = JVereinPlugin.getI18n().tr(
          "Fehler beim L�schen eines Abrechnungslaufes");
      GUI.getStatusBar().setErrorText(fehler);
      Logger.error(fehler, e);
    }
  }
}
