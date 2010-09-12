/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/input/KontoauswahlInput.java,v $
 * $Revision: 1.4 $
 * $Date: 2010/09/12 08:02:49 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: KontoauswahlInput.java,v $
 * Revision 1.4  2010/09/12 08:02:49  jost
 * Letztes Konto wird wieder vorgegeben.
 * Siehe auch http://www.jverein.de/forum/viewtopic.php?f=1&t=198
 *
 * Revision 1.3  2010-07-25 18:34:58  jost
 * Doc
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.input;

import java.rmi.RemoteException;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.dialogs.KontoAuswahlDialog;
import de.jost_net.JVerein.rmi.Konto;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.input.DialogInput;
import de.willuhn.logging.Logger;

public class KontoauswahlInput
{
  private DialogInput kontoAuswahl = null;

  private Konto konto;

  private de.willuhn.jameica.system.Settings settings;

  public KontoauswahlInput()
  {
    this(null);
  }

  public KontoauswahlInput(Konto konto)
  {
    this.konto = konto;
    settings = new de.willuhn.jameica.system.Settings(this.getClass());
    settings.setStoreWhenRead(true);

  }

  /**
   * Liefert ein Auswahlfeld fuer das Konto.
   * 
   * @return Auswahl-Feld.
   * @throws RemoteException
   */
  public DialogInput getKontoAuswahl() throws RemoteException
  {
    if (kontoAuswahl != null)
    {
      return kontoAuswahl;
    }
    KontoAuswahlDialog d = new KontoAuswahlDialog(
        KontoAuswahlDialog.POSITION_MOUSE);
    d.addCloseListener(new KontoListener());

    if (konto == null && settings.getString("kontoid", null) != null)
    {
      konto = (Konto) Einstellungen.getDBService().createObject(Konto.class,
          settings.getString("kontoid", null));
    }

    kontoAuswahl = new DialogInput(konto == null ? "" : konto.getNummer(), d);
    kontoAuswahl.setComment(konto == null ? "" : konto.getBezeichnung());
    kontoAuswahl.disableClientControl();
    kontoAuswahl.setValue(konto);
    kontoAuswahl.setMandatory(true);
    return kontoAuswahl;
  }

  /**
   * Listener, der die Auswahl des Kontos ueberwacht und die
   * Waehrungsbezeichnung hinter dem Betrag abhaengig vom ausgewaehlten Konto
   * anpasst.
   */
  private class KontoListener implements Listener
  {
    /**
     * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
     */
    public void handleEvent(Event event)
    {

      if (event == null || event.data == null)
      {
        return;
      }
      konto = (Konto) event.data;
      try
      {
        String b = konto.getBezeichnung();
        getKontoAuswahl().setText(konto.getNummer());
        getKontoAuswahl().setComment(b == null ? "" : b);
        settings.setAttribute("kontoid", konto.getID());
      }
      catch (RemoteException er)
      {
        String error = JVereinPlugin.getI18n().tr(
            "Fehler bei der Ermittlung der Kontobezeichnung");
        Logger.error(error, er);
        GUI.getStatusBar().setErrorText(error);
      }
    }
  }

}
