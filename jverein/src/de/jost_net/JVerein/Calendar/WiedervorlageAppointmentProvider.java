/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/Calendar/WiedervorlageAppointmentProvider.java,v $
 * $Revision: 1.7 $
 * $Date: 2011/10/06 18:10:36 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/
package de.jost_net.JVerein.Calendar;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.graphics.RGB;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.MitgliedDetailAction;
import de.jost_net.JVerein.rmi.Wiedervorlage;
import de.willuhn.datasource.rmi.DBIterator;
import de.willuhn.jameica.gui.calendar.Appointment;
import de.willuhn.jameica.gui.calendar.AppointmentProvider;
import de.willuhn.logging.Logger;
import de.willuhn.util.I18N;

/**
 * Implementierung eines Termin-Providers fuer Wiedervorlagen
 */
public class WiedervorlageAppointmentProvider implements AppointmentProvider
{

  private final static I18N i18n = JVereinPlugin.getI18n();

  /**
   * @see de.willuhn.jameica.gui.calendar.AppointmentProvider#getAppointments(java.util.Date,
   *      java.util.Date)
   */
  public List<Appointment> getAppointments(Date from, Date to)
  {
    try
    {
      DBIterator list = Einstellungen.getDBService().createList(
          de.jost_net.JVerein.rmi.Wiedervorlage.class);
      Calendar cal = Calendar.getInstance();
      cal.setTime(from);
      if (from != null)
        list.addFilter("datum >= ?",
            new Object[] { new java.sql.Date(from.getTime()) });
      list.addFilter("datum <= ?",
          new Object[] { new java.sql.Date(to.getTime()) });
      list.setOrder("ORDER BY day(datum)");

      List<Appointment> result = new LinkedList<Appointment>();
      while (list.hasNext())
      {
        result.add(new MyAppointment((Wiedervorlage) list.next()));
      }
      return result;
    }
    catch (Exception e)
    {
      Logger.error("unable to load data", e);
    }
    return null;
  }

  /**
   * @see de.willuhn.jameica.gui.calendar.AppointmentProvider#getName()
   */
  public String getName()
  {
    return i18n.tr("Wiedervorlagen");
  }

  /**
   * Hilfsklasse zum Anzeigen und Oeffnen des Appointments.
   */
  private static class MyAppointment implements Appointment
  {

    private Wiedervorlage w = null;

    private MyAppointment(Wiedervorlage w)
    {
      this.w = w;
    }

    /**
     * @see de.willuhn.jameica.gui.calendar.Appointment#execute()
     */
    public void execute()
    {
      try
      {
        new MitgliedDetailAction().handleAction(w.getMitglied());
      }
      catch (Exception e)
      {
        Logger.error("Fehler", e);
      }
    }

    /**
     * @see de.willuhn.jameica.gui.calendar.Appointment#getDate()
     */
    public Date getDate()
    {
      try
      {
        return w.getDatum();
      }
      catch (Exception e)
      {
        Logger.error("unable to read date", e);
      }
      return null;
    }

    /**
     * @see de.willuhn.jameica.gui.calendar.Appointment#getDescription()
     */
    public String getDescription()
    {
      try
      {
        return i18n.tr("Wiedervorlage:" + w.getMitglied().getNameVorname()
            + ": " + w.getVermerk());
      }
      catch (RemoteException re)
      {
        Logger.error("unable to build description", re);
        return null;
      }
    }

    /**
     * @see de.willuhn.jameica.gui.calendar.Appointment#getName()
     */
    public String getName()
    {
      try
      {
        return w.getVermerk();
      }
      catch (RemoteException re)
      {
        Logger.error("unable to build name", re);
        return i18n.tr("Wiedervorlage");
      }
    }

    /**
     * @see de.willuhn.jameica.gui.calendar.Appointment#getColor()
     */
    public RGB getColor()
    {
      return new RGB(31, 32, 255);
    }

    /**
     * @see de.willuhn.jameica.gui.calendar.AbstractAppointment#getUid()
     */
    public String getUid()
    {
      try
      {
        return "jverein.wiedervorlage." + w.getID();
      }
      catch (RemoteException re)
      {
        Logger.error("unable to create uid", re);
        return "*Error*";
      }
    }

    /**
     * @see de.willuhn.jameica.gui.calendar.AbstractAppointment#hasAlarm()
     */
    public boolean hasAlarm()
    {
      return false;
    }

  }
}
