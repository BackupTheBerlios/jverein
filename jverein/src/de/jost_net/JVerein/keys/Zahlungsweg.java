/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/keys/Zahlungsweg.java,v $
 * $Revision: 1.3 $
 * $Date: 2009/06/11 21:04:03 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: Zahlungsweg.java,v $
 * Revision 1.3  2009/06/11 21:04:03  jost
 * Vorbereitung I18N
 *
 * Revision 1.2  2008/12/13 16:25:14  jost
 * equals()-Methode implementiert.
 *
 * Revision 1.1  2008/11/29 13:13:36  jost
 * Refactoring: Code-Optimierung
 *
 **********************************************************************/
package de.jost_net.JVerein.keys;

import java.util.ArrayList;

import de.jost_net.JVerein.JVereinPlugin;

/**
 * Schl�ssel Zahlungsweg
 */
public class Zahlungsweg
{
  public static final int ABBUCHUNG = 1;

  public static final int �BERWEISUNG = 2;

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
        return JVereinPlugin.getI18n().tr("Abbuchung");
      case �BERWEISUNG:
        return JVereinPlugin.getI18n().tr("�berweisung");
      case BARZAHLUNG:
        return JVereinPlugin.getI18n().tr("Barzahlung");
      default:
        return null;
    }
  }

  public static ArrayList<Zahlungsweg> getArray()
  {
    ArrayList<Zahlungsweg> ret = new ArrayList<Zahlungsweg>();
    ret.add(new Zahlungsweg(ABBUCHUNG));
    ret.add(new Zahlungsweg(�BERWEISUNG));
    ret.add(new Zahlungsweg(BARZAHLUNG));
    return ret;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (obj instanceof Zahlungsweg)
    {
      Zahlungsweg v = (Zahlungsweg) obj;
      return (getKey() == v.getKey());
    }
    return false;
  }

  @Override
  public String toString()
  {
    return get(zahlungsweg);
  }
}
