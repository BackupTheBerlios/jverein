/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/boxes/FirstStart.java,v $
 * $Revision: 1.5 $
 * $Date: 2009/06/11 21:02:17 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: FirstStart.java,v $
 * Revision 1.5  2009/06/11 21:02:17  jost
 * Vorbereitung I18N
 *
 * Revision 1.4  2009/05/31 12:26:26  jost
 * Erl�uternder Text wurde eingef�gt.
 *
 * Revision 1.3  2007/12/29 19:09:22  jost
 * Explizite Höhe der Box vorgegeben.
 *
 * Revision 1.2  2007/12/28 15:54:36  jost
 * Button-Leiste vervollständigt.
 *
 * Revision 1.1  2007/12/28 13:09:38  jost
 * Neue FirstStart-Box
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.boxes;

import java.rmi.RemoteException;

import org.eclipse.swt.widgets.Composite;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.BeitragsgruppeSucheAction;
import de.jost_net.JVerein.gui.action.EinstellungenAction;
import de.jost_net.JVerein.gui.action.StammdatenAction;
import de.willuhn.jameica.gui.boxes.AbstractBox;
import de.willuhn.jameica.gui.parts.FormTextPart;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.hbci.HBCI;
import de.willuhn.jameica.plugin.Manifest;
import de.willuhn.jameica.system.Application;

/**
 * Hilfe-Seite fuer den ersten Start.
 */
public class FirstStart extends AbstractBox
{

  public boolean isActive()
  {
    // Diese Box kann nur beim ersten Start ausgewaehlt/angezeigt werden.
    return Einstellungen.isFirstStart();
  }

  public boolean getDefaultEnabled()
  {
    return Einstellungen.isFirstStart();
  }

  public int getDefaultIndex()
  {
    return 0;
  }

  public String getName()
  {
    return JVereinPlugin.getI18n().tr("JVerein: Erste Schritte");
  }

  public boolean isEnabled()
  {
    // Diese Box kann nur beim ersten erfolgreichen Start ausgewaehlt/angezeigt
    // werden.
    Manifest mf = Application.getPluginLoader().getManifest(HBCI.class);
    return mf.isInstalled() && Einstellungen.isFirstStart();
  }

  public void paint(Composite parent) throws RemoteException
  {
    FormTextPart text = new FormTextPart();
    text
        .setText("<form><p><span color=\"header\" font=\"header\">"
            + JVereinPlugin.getI18n().tr("Herzlich willkommen")
            + "</span></p>"
            + "<p>"
            + JVereinPlugin
                .getI18n()
                .tr(
                    "JVerein wird zum ersten Mal gestartet. Die Stammdaten des Vereins "
                        + "(Name, eigene Bankverbindung) "
                        + "und die Beitragsgruppe(n) sind zu erfassen. Au�erdem kann das "
                        + "Verhalten von JVerein durch die Einstellungen beeinflusst werden.")
            + "</p></form>");

    text.paint(parent);

    ButtonArea buttons = new ButtonArea(parent, 3);
    buttons.addButton(JVereinPlugin.getI18n().tr("Stammdaten"),
        new StammdatenAction(), null, true);
    buttons.addButton(JVereinPlugin.getI18n().tr("Beitragsgruppen"),
        new BeitragsgruppeSucheAction(), null);
    buttons.addButton(JVereinPlugin.getI18n().tr("Einstellungen"),
        new EinstellungenAction(), null);
  }

  public int getHeight()
  {
    return 160;
  }
}
