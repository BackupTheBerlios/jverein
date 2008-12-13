/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/keys/Zahlungsrhytmus.java,v $
 * $Revision: 1.2 $
 * $Date: 2008/12/13 16:25:05 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: Zahlungsrhytmus.java,v $
 * Revision 1.2  2008/12/13 16:25:05  jost
 * equals()-Methode implementiert.
 *
 * Revision 1.1  2008/11/29 13:13:26  jost
 * Refactoring: Code-Optimierung
 *
 **********************************************************************/
package de.jost_net.JVerein.keys;

import java.util.ArrayList;

/**
 * Schl�ssel Zahlungsrhytmus
 */
public class Zahlungsrhytmus
{
  public static final int JAEHRLICH = 12;

  public static final int HALBJAEHRLICH = 6;

  public static final int VIERTELJAEHRLICH = 3;

  public static final int MONATLICH = 1;

  private int zahlungsrhytmus;

  public Zahlungsrhytmus(int key)
  {
    this.zahlungsrhytmus = key;
  }

  public int getKey()
  {
    return zahlungsrhytmus;
  }

  public String getText()
  {
    return get(zahlungsrhytmus);
  }

  public static String get(int key)
  {
    switch (key)
    {
      case Zahlungsrhytmus.JAEHRLICH:
        return "j�hrlich";
      case Zahlungsrhytmus.HALBJAEHRLICH:
        return "halbj�hrlich";
      case Zahlungsrhytmus.VIERTELJAEHRLICH:
        return "viertelj�hrlich";
      case Zahlungsrhytmus.MONATLICH:
        return "monatlich";
      default:
        return null;
    }
  }

  public static ArrayList<Zahlungsrhytmus> getArray()
  {
    ArrayList<Zahlungsrhytmus> ret = new ArrayList<Zahlungsrhytmus>();
    ret.add(new Zahlungsrhytmus(JAEHRLICH));
    ret.add(new Zahlungsrhytmus(HALBJAEHRLICH));
    ret.add(new Zahlungsrhytmus(VIERTELJAEHRLICH));
    ret.add(new Zahlungsrhytmus(MONATLICH));
    return ret;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (obj instanceof Zahlungsrhytmus)
    {
      Zahlungsrhytmus v = (Zahlungsrhytmus) obj;
      return (getKey() == v.getKey());
    }
    return false;
  }

  @Override
  public String toString()
  {
    return get(zahlungsrhytmus);
  }
}
