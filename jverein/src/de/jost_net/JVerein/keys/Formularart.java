/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/keys/Formularart.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/11/29 13:13:06 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: Formularart.java,v $
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

  public String toString()
  {
    return get(formularart);
  }
}
