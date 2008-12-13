/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/keys/Formularart.java,v $
 * $Revision: 1.2 $
 * $Date: 2008/12/13 16:24:04 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: Formularart.java,v $
 * Revision 1.2  2008/12/13 16:24:04  jost
 * equals()-Methode implementiert.
 *
 * Revision 1.1  2008/11/29 13:13:06  jost
 * Refactoring: Code-Optimierung
 *
 **********************************************************************/
package de.jost_net.JVerein.keys;

import java.util.ArrayList;

/**
 * Formularart
 */
public class Formularart
{
  public static final int SPENDENBESCHEINIGUNG = 1;

  public static final int RECHNUNG = 2;

  private int formularart;

  public Formularart(int key)
  {
    this.formularart = key;
  }

  public int getKey()
  {
    return formularart;
  }

  public String getText()
  {
    return get(formularart);
  }

  public static String get(int key)
  {
    switch (key)
    {
      case SPENDENBESCHEINIGUNG:
        return "Spendenbescheinigung";
      case RECHNUNG:
        return "Rechnung";
      default:
        return null;
    }
  }

  public static ArrayList<Formularart> getArray()
  {
    ArrayList<Formularart> ret = new ArrayList<Formularart>();
    ret.add(new Formularart(SPENDENBESCHEINIGUNG));
    ret.add(new Formularart(RECHNUNG));
    return ret;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (obj instanceof Formularart)
    {
      Formularart v = (Formularart) obj;
      return (getKey() == v.getKey());
    }
    return false;
  }

  @Override
  public String toString()
  {
    return get(formularart);
  }
}
