/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/HibiscusKontenImportAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/05/22 06:46:13 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: HibiscusKontenImportAction.java,v $
 * Revision 1.1  2008/05/22 06:46:13  jost
 * Buchführung
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import java.rmi.RemoteException;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.gui.control.KontoControl;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.hbci.HBCI;
import de.willuhn.jameica.hbci.gui.dialogs.KontoAuswahlDialog;
import de.willuhn.jameica.hbci.rmi.Konto;
import de.willuhn.jameica.system.Application;
import de.willuhn.jameica.system.OperationCanceledException;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;
import de.willuhn.util.I18N;

/**
 * Loeschen einer Buchungsart.
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
    final I18N i18n = Application.getPluginLoader().getPlugin(HBCI.class)
        .getResources().getI18N();

    if (context == null)
    {
      // 1) Wir zeigen einen Dialog an, in dem der User das Konto auswaehlt
      KontoAuswahlDialog d = new KontoAuswahlDialog(
          KontoAuswahlDialog.POSITION_CENTER);
      try
      {
        context = d.open();
      }
      catch (OperationCanceledException oce)
      {
        GUI.getStatusBar().setErrorText(i18n.tr("Vorgang abgebrochen"));
        return;
      }
      catch (Exception e)
      {
        Logger.error("error while choosing konto", e);
        GUI.getStatusBar().setErrorText(
            i18n.tr("Fehler bei der Auswahl des Kontos."));
      }
    }

    if (context == null || !(context instanceof Konto))
      throw new ApplicationException(i18n.tr("Kein Konto ausgew�hlt"));

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
          "Konto " + k.getKontonummer() + " importiert.");
    }
    catch (RemoteException e)
    {
      throw new ApplicationException(e.getMessage());
    }
  }
}
