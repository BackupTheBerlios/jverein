/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/HibiscusKontenImportAction.java,v $
 * $Revision: 1.5 $
 * $Date: 2011/10/01 21:34:25 $
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
import de.jost_net.JVerein.gui.control.KontoControl;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.hbci.gui.dialogs.KontoAuswahlDialog;
import de.willuhn.jameica.hbci.rmi.Konto;
import de.willuhn.jameica.system.OperationCanceledException;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

/**
 * Hibiscus-Konten importieren
 */
public class HibiscusKontenImportAction implements Action
{
  private KontoControl control;

  public HibiscusKontenImportAction(KontoControl control)
  {
    this.control = control;
  }

  public void handleAction(Object context) throws ApplicationException
  {
    if (context == null)
    {
      // 1) Wir zeigen einen Dialog an, in dem der User das Konto ausw�hlt
      KontoAuswahlDialog d = new KontoAuswahlDialog(
          KontoAuswahlDialog.POSITION_CENTER);
      try
      {
        context = d.open();
      }
      catch (OperationCanceledException oce)
      {
        GUI.getStatusBar().setErrorText(
            JVereinPlugin.getI18n().tr("Vorgang abgebrochen"));
        return;
      }
      catch (Exception e)
      {
        Logger.error("error while choosing konto", e);
        GUI.getStatusBar().setErrorText(
            JVereinPlugin.getI18n().tr("Fehler bei der Auswahl des Kontos."));
      }
    }

    if (context == null || !(context instanceof Konto))
      throw new ApplicationException(JVereinPlugin.getI18n().tr(
          "Kein Konto ausgew�hlt"));

    final Konto k = (Konto) context;
    try
    {
      de.jost_net.JVerein.rmi.Konto jvereinkonto = (de.jost_net.JVerein.rmi.Konto) Einstellungen
          .getDBService().createObject(de.jost_net.JVerein.rmi.Konto.class,
              null);
      jvereinkonto.setNummer(k.getKontonummer());
      jvereinkonto.setBezeichnung(k.getBezeichnung());
      jvereinkonto.setHibiscusId(new Integer(k.getID()));
      jvereinkonto.store();
      control.refreshTable();
      GUI.getStatusBar().setSuccessText(
          JVereinPlugin.getI18n().tr("Konto {0} importiert.",
              new String[] { k.getKontonummer() }));
    }
    catch (RemoteException e)
    {
      throw new ApplicationException(e.getMessage());
    }
  }
}
