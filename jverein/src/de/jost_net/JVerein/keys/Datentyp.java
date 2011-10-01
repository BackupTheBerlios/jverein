/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/keys/Datentyp.java,v $
 * $Revision: 1.3 $
 * $Date: 2011/10/01 21:48:34 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/
package de.jost_net.JVerein.keys;

import java.util.ArrayList;

import de.jost_net.JVerein.JVereinPlugin;

/**
 * Datentyp
 */
public class Datentyp
{
  public static final int ZEICHENFOLGE = 1;

  public static final int DATUM = 2;

  public static final int GANZZAHL = 3;

  public static final int WAEHRUNG = 5;

  public static final int JANEIN = 6;

  private int datentyp;

  public Datentyp(int datentyp)
  {
    this.datentyp = datentyp;
  }

  public int getKey()
  {
    return datentyp;
  }

  public String getText()
  {
    return get(datentyp);
  }

  public static String get(int key)
  {
    switch (key)
    {
      case ZEICHENFOLGE:
        return JVereinPlugin.getI18n().tr("Zeichenfolge");
      case DATUM:
        return JVereinPlugin.getI18n().tr("Datum");
      case GANZZAHL:
        return JVereinPlugin.getI18n().tr("Ganzzahl");
      case WAEHRUNG:
        return JVereinPlugin.getI18n().tr("W�hrung");
      case JANEIN:
        return JVereinPlugin.getI18n().tr("Ja/Nein");
      default:
        return null;
    }
  }

  public static ArrayList<Datentyp> getArray()
  {
    ArrayList<Datentyp> ret = new ArrayList<Datentyp>();
    ret.add(new Datentyp(ZEICHENFOLGE));
    ret.add(new Datentyp(DATUM));
    ret.add(new Datentyp(GANZZAHL));
    ret.add(new Datentyp(WAEHRUNG));
    ret.add(new Datentyp(JANEIN));
    return ret;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (obj instanceof Datentyp)
    {
      Datentyp v = (Datentyp) obj;
      return (getKey() == v.getKey());
    }
    return false;
  }

  @Override
  public int hashCode()
  {
    return datentyp;
  }

  @Override
  public String toString()
  {
    return get(datentyp);
  }
}
