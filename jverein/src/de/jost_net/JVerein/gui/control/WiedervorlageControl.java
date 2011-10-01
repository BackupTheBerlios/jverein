/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/control/WiedervorlageControl.java,v $
 * $Revision: 1.6 $
 * $Date: 2011/10/01 21:42:56 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/
package de.jost_net.JVerein.gui.control;

import java.rmi.RemoteException;
import java.util.Date;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import de.jost_net.JVerein.rmi.Wiedervorlage;
import de.jost_net.JVerein.util.JVDateFormatTTMMJJJJ;
import de.willuhn.jameica.gui.AbstractControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.input.DateInput;
import de.willuhn.jameica.gui.input.Input;
import de.willuhn.jameica.gui.input.TextInput;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

public class WiedervorlageControl extends AbstractControl
{
  private DateInput datum = null;

  private Input vermerk = null;

  private DateInput erledigung = null;

  private Wiedervorlage wvl = null;

  public WiedervorlageControl(AbstractView view)
  {
    super(view);
  }

  public Wiedervorlage getWiedervorlage()
  {
    if (wvl != null)
    {
      return wvl;
    }
    wvl = (Wiedervorlage) getCurrentObject();
    return wvl;
  }

  public DateInput getDatum(boolean withFocus) throws RemoteException
  {
    if (datum != null)
    {
      return datum;
    }

    Date d = getWiedervorlage().getDatum();

    this.datum = new DateInput(d, new JVDateFormatTTMMJJJJ());
    this.datum.setTitle("Datum");
    this.datum.setText("Bitte Wiedervorlagedatum w�hlen");
    this.datum.addListener(new Listener()
    {
      public void handleEvent(Event event)
      {
        Date date = (Date) datum.getValue();
        if (date == null)
        {
          return;
        }
      }
    });
    if (withFocus)
    {
      datum.focus();
    }
    return datum;
  }

  public Input getVermerk() throws RemoteException
  {
    if (vermerk != null)
    {
      return vermerk;
    }
    vermerk = new TextInput(getWiedervorlage().getVermerk(), 50);
    vermerk.setMandatory(true);
    return vermerk;
  }

  public DateInput getErledigung() throws RemoteException
  {
    if (erledigung != null)
    {
      return erledigung;
    }

    Date d = getWiedervorlage().getErledigung();

    this.erledigung = new DateInput(d, new JVDateFormatTTMMJJJJ());
    this.erledigung.setTitle("Erledigung");
    this.erledigung.setText("Bitte Erledigungsdatum w�hlen");
    this.erledigung.addListener(new Listener()
    {
      public void handleEvent(Event event)
      {
        Date date = (Date) erledigung.getValue();
        if (date == null)
        {
          return;
        }
      }
    });
    return erledigung;
  }

  public void handleStore()
  {
    try
    {
      Wiedervorlage w = getWiedervorlage();
      w.setDatum((Date) getDatum(false).getValue());
      w.setVermerk((String) getVermerk().getValue());
      w.setErledigung((Date) getErledigung().getValue());
      w.store();
      GUI.getStatusBar().setSuccessText("Wiedervorlage gespeichert");
    }
    catch (ApplicationException e)
    {
      GUI.getStatusBar().setErrorText(e.getMessage());
    }
    catch (RemoteException e)
    {
      String fehler = "Fehler bei speichern der Wiedervorlage";
      Logger.error(fehler, e);
      GUI.getStatusBar().setErrorText(fehler);
    }
  }

}
