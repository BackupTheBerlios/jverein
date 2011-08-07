/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/Variable/MitgliedskontoMap.java,v $
 * $Revision: 1.2 $
 * $Date: 2011/08/07 08:17:48 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * heiner@jverein.de
 * www.jverein.de
 * All rights reserved
 * $Log: MitgliedskontoMap.java,v $
 * Revision 1.2  2011/08/07 08:17:48  jost
 * Bugfix neue Datenfelder
 *
 * Revision 1.1  2011-05-27 18:48:45  jost
 * Vereinheitlichung Variable
 *
 **********************************************************************/
package de.jost_net.JVerein.Variable;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import de.jost_net.JVerein.gui.control.FormularfeldControl;
import de.jost_net.JVerein.rmi.Mitgliedskonto;

public class MitgliedskontoMap
{

  public MitgliedskontoMap()
  {

  }

  public Map<String, Object> getMap(ArrayList<Mitgliedskonto> mk,
      Map<String, Object> inma) throws RemoteException
  {
    Map<String, Object> map = null;
    if (inma == null)
    {
      map = new HashMap<String, Object>();
    }
    else
    {
      map = inma;
    }

    ArrayList<Date> buda = new ArrayList<Date>();
    ArrayList<String> zg = new ArrayList<String>();
    ArrayList<String> zg1 = new ArrayList<String>();
    ArrayList<String> zg2 = new ArrayList<String>();
    ArrayList<Double> betrag = new ArrayList<Double>();
    double summe = 0;
    for (Mitgliedskonto mkto : mk)
    {
      buda.add(mkto.getDatum());
      zg.add(mkto.getZweck1() + " " + mkto.getZweck2());
      zg1.add(mkto.getZweck1());
      zg2.add(mkto.getZweck2());
      betrag.add(new Double(mkto.getBetrag()));
      summe += mkto.getBetrag();
    }
    if (buda.size() > 1)
    {
      zg1.add("Summe");
      zg.add("Summe");
      betrag.add(summe);
    }
    map.put(FormularfeldControl.BUCHUNGSDATUM, buda.toArray());
    map.put(FormularfeldControl.ZAHLUNGSGRUND, zg.toArray());
    map.put(FormularfeldControl.ZAHLUNGSGRUND1, zg1.toArray());
    map.put(FormularfeldControl.ZAHLUNGSGRUND2, zg2.toArray());
    map.put(FormularfeldControl.BETRAG, betrag.toArray());
    map.put(MitgliedskontoVar.BUCHUNGSDATUM.getName(), buda.toArray());
    map.put(MitgliedskontoVar.ZAHLUNGSGRUND.getName(), zg.toArray());
    map.put(MitgliedskontoVar.ZAHLUNGSGRUND1.getName(), zg1.toArray());
    map.put(MitgliedskontoVar.ZAHLUNGSGRUND2.getName(), zg2.toArray());
    map.put(MitgliedskontoVar.BETRAG.getName(), betrag.toArray());

    return map;
  }
}
