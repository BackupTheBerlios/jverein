/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/keys/Zahlungsweg.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/11/29 13:13:36 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: Zahlungsweg.java,v $
 * Revision 1.1  2008/11/29 13:13:36  jost
 * Refactoring: Code-Optimierung
 *
 **********************************************************************/
package de.jost_net.JVerein.keys;

import java.util.ArrayList;

/**
 * Schlüssel Zahlungsweg
 */
public class Zahlungsweg
{
  public static final int ABBUCHUNG = 1;

  public static final int ÜBERWEISUNG = 2;

  public static final int BARZAHLUNG = 3;

  private int zahlungsweg;

  public Zahlungsweg(int key)
  {
    this.zahlungsweg = key;
  }

  public int getKey()
  {
    return zahlungsweg;
  }

  public String getText()
  {
    return get(zahlungsweg);
  }

  public static String get(int key)
  {
    switch (key)
    {
      case ABBUCHUNG:
        return "Abbuchung";
      case ÜBERWEISUNG:
        return "Überweisung";
      case BARZAHLUNG:
        return "Barzahlung";
      default:
        return null;
    }
  }

  public static ArrayList<Zahlungsweg> getArray()
  {
    ArrayList<Zahlungsweg> ret = new ArrayList<Zahlungsweg>();
    ret.add(new Zahlungsweg(ABBUCHUNG));
    ret.add(new Zahlungsweg(ÜBERWEISUNG));
    ret.add(new Zahlungsweg(BARZAHLUNG));
    return ret;
  }

  public String toString()
  {
    return get(zahlungsweg);
  }
}
