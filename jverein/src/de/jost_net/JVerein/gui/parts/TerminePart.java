/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/parts/Attic/TerminePart.java,v $
 * $Revision: 1.4 $
 * $Date: 2011/01/27 22:20:31 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: TerminePart.java,v $
 * Revision 1.4  2011/01/27 22:20:31  jost
 * Anpassung an neue Appointmentprovider
 *
 * Revision 1.3  2010-11-27 17:57:27  jost
 * Überflüssigen Import entfernt.
 *
 * Revision 1.2  2010-11-26 08:11:06  jost
 * Änderung von Olaf übernommen.
 *
 * Revision 1.1  2010-11-25 15:11:45  jost
 * Initial Commit
 *
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