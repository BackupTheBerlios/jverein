/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/keys/Zahlungsrhytmus.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/11/29 13:13:26 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: Zahlungsrhytmus.java,v $
 * Revision 1.1  2008/11/29 13:13:26  jost
 * Refactoring: Code-Optimierung
 *
 **********************************************************************/
package de.jost_net.JVerein.keys;

import java.util.ArrayList;

/**
 * Schlüssel Zahlungsrhytmus
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
        return "jährlich";
      case Zahlungsrhytmus.HALBJAEHRLICH:
        return "halbjährlich";
      case Zahlungsrhytmus.VIERTELJAEHRLICH:
        return "vierteljährlich";
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

  public String toString()
  {
    return get(zahlungsrhytmus);
  }
}
