/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/control/EinstellungControl.java,v $
 * $Revision: 1.4 $
 * $Date: 2007/12/02 13:39:31 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: EinstellungControl.java,v $
 * Revision 1.4  2007/12/02 13:39:31  jost
 * Neu: Beitragsmodelle
 *
 * Revision 1.3  2007/12/01 17:45:51  jost
 * Wegfall Standardtab für die Suche
 *
 * Revision 1.2  2007/08/23 18:42:51  jost
 * Standard-Tab für die Mitglieder-Suche
 *
 * Revision 1.1  2007/08/22 20:42:56  jost
 * Bug #011762
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.control;

import java.rmi.RemoteException;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.gui.input.BeitragsmodelInput;
import de.willuhn.jameica.gui.AbstractControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.input.CheckboxInput;
import de.willuhn.jameica.gui.input.SelectInput;

public class EinstellungControl extends AbstractControl
{

  private CheckboxInput geburtsdatumpflicht;

  private CheckboxInput eintrittsdatumpflicht;

  private CheckboxInput kommunikationsdaten;

  private CheckboxInput zusatzabbuchung;

  private CheckboxInput vermerke;

  private CheckboxInput wiedervorlage;

  private CheckboxInput kursteilnehmer;

  private SelectInput beitragsmodel;

  public EinstellungControl(AbstractView view)
  {
    super(view);
  }

  public CheckboxInput getGeburtsdatumPflicht() throws RemoteException
  {
    if (geburtsdatumpflicht != null)
    {
      return geburtsdatumpflicht;
    }
    geburtsdatumpflicht = new CheckboxInput(Einstellungen
        .isGeburtsdatumPflicht());
    return geburtsdatumpflicht;
  }

  public CheckboxInput getEintrittsdatumPflicht() throws RemoteException
  {
    if (eintrittsdatumpflicht != null)
    {
      return eintrittsdatumpflicht;
    }
    eintrittsdatumpflicht = new CheckboxInput(Einstellungen
        .isEintrittsdatumPflicht());
    return eintrittsdatumpflicht;
  }

  public CheckboxInput getKommunikationsdaten() throws RemoteException
  {
    if (kommunikationsdaten != null)
    {
      return kommunikationsdaten;
    }
    kommunikationsdaten = new CheckboxInput(Einstellungen
        .isKommunikationsdaten());
    return kommunikationsdaten;
  }

  public CheckboxInput getZusatzabbuchung() throws RemoteException
  {
    if (zusatzabbuchung != null)
    {
      return zusatzabbuchung;
    }
    zusatzabbuchung = new CheckboxInput(Einstellungen.isZusatzabbuchung());
    return zusatzabbuchung;
  }

  public CheckboxInput getVermerke() throws RemoteException
  {
    if (vermerke != null)
    {
      return vermerke;
    }
    vermerke = new CheckboxInput(Einstellungen.isVermerke());
    return vermerke;
  }

  public CheckboxInput getWiedervorlage() throws RemoteException
  {
    if (wiedervorlage != null)
    {
      return wiedervorlage;
    }
    wiedervorlage = new CheckboxInput(Einstellungen.isWiedervorlage());
    return wiedervorlage;
  }

  public CheckboxInput getKursteilnehmer() throws RemoteException
  {
    if (kursteilnehmer != null)
    {
      return kursteilnehmer;
    }
    kursteilnehmer = new CheckboxInput(Einstellungen.isKursteilnehmer());
    return kursteilnehmer;
  }

  public SelectInput getBeitragsmodel() throws RemoteException
  {
    if (beitragsmodel != null)
    {
      return beitragsmodel;
    }
    beitragsmodel = new BeitragsmodelInput(Einstellungen.getBeitragsmodel());
    return beitragsmodel;
  }

  public void handleStore()
  {
    Boolean _geburtsdatumpflicht = (Boolean) geburtsdatumpflicht.getValue();
    Boolean _eintrittsdatumpflicht = (Boolean) eintrittsdatumpflicht.getValue();
    Boolean _kommunikationsdaten = (Boolean) kommunikationsdaten.getValue();
    Boolean _zusatzabbuchung = (Boolean) zusatzabbuchung.getValue();
    Boolean _vermerke = (Boolean) vermerke.getValue();
    Boolean _wiedervorlage = (Boolean) wiedervorlage.getValue();
    Boolean _kursteilnehmer = (Boolean) kursteilnehmer.getValue();
    Integer _beitragsmodel = (Integer)beitragsmodel.getValue();
    Einstellungen.setGeburtsdatumPflicht(_geburtsdatumpflicht.booleanValue());
    Einstellungen.setEintrittsdatumPflicht(_eintrittsdatumpflicht
        .booleanValue());
    Einstellungen.setKommunikationsdaten(_kommunikationsdaten.booleanValue());
    Einstellungen.setZusatzabbuchungen(_zusatzabbuchung.booleanValue());
    Einstellungen.setVermerke(_vermerke.booleanValue());
    Einstellungen.setWiedervorlage(_wiedervorlage.booleanValue());
    Einstellungen.setKursteilnehmer(_kursteilnehmer.booleanValue());
    Einstellungen.setBeitragsmodel(_beitragsmodel.intValue());
    GUI.getStatusBar().setSuccessText("Einstellungen gespeichert");
  }
}
