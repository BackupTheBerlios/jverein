/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/parts/Attic/TerminePart.java,v $
 * $Revision: 1.5 $
 * $Date: 2011/10/01 21:44:54 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/

package de.jost_net.JVerein.gui.parts;

import java.util.List;

import de.jost_net.JVerein.JVereinPlugin;
import de.willuhn.jameica.gui.calendar.AppointmentProvider;
import de.willuhn.jameica.gui.calendar.AppointmentProviderRegistry;
import de.willuhn.jameica.gui.calendar.CalendarPart;
import de.willuhn.jameica.plugin.AbstractPlugin;
import de.willuhn.jameica.system.Application;

/**
 * Zeigt die anstehenden Termine in JVerein an.
 */
public class TerminePart extends CalendarPart
{

  public TerminePart()
  {
    // Wir laden automatisch die Termin-Provider.
    AbstractPlugin plugin = Application.getPluginLoader().getPlugin(
        JVereinPlugin.class);

    List<AppointmentProvider> list = AppointmentProviderRegistry
        .getAppointmentProviders(plugin);

    for (AppointmentProvider p : list)
    {
      addAppointmentProvider(p);
    }
  }
}