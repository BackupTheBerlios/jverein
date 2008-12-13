/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/keys/Beitragsmodel.java,v $
 * $Revision: 1.2 $
 * $Date: 2008/12/13 16:23:55 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: Beitragsmodel.java,v $
 * Revision 1.2  2008/12/13 16:23:55  jost
 * equals()-Methode implementiert.
 *
 * Revision 1.1  2008/11/29 13:12:56  jost
 * Refactoring: Code-Optimierung
 *
 **********************************************************************/
package de.jost_net.JVerein.keys;

import java.util.ArrayList;

/**
 * Abrechnungsausgabe
 */
public class Beitragsmodel
{
  public static final int JAEHRLICH = 1;

  public static final int HALBJAEHRLICH = 2;

  public static final int VIERTELJAEHRLICH = 3;

  public static final int MONATLICH = 4;

  public static final int MONATLICH12631 = 5;

  private int model;

  public Beitragsmodel(int key)
  {
    this.model = key;
  }

  public int getKey()
  {
    return model;
  }

  public String getText()
  {
    return get(model);
  }

  public static String get(int key)
  {
    switch (key)
    {
      case JAEHRLICH:
        return "jährlich";
      case HALBJAEHRLICH:
        return "halbjährlich";
      case VIERTELJAEHRLICH:
        return "vierteljährlich";
      case MONATLICH:
        return "monatlich";
      case MONATLICH12631:
        return "monatlich mit monatl., viertel-, halb- oder jährlicher Zahlungsweise";
      default:
        return null;
    }
  }

  public static ArrayList<Beitragsmodel> getArray()
  {
    ArrayList<Beitragsmodel> ret = new ArrayList<Beitragsmodel>();
    ret.add(new Beitragsmodel(JAEHRLICH));
    ret.add(new Beitragsmodel(HALBJAEHRLICH));
    ret.add(new Beitragsmodel(VIERTELJAEHRLICH));
    ret.add(new Beitragsmodel(MONATLICH));
    ret.add(new Beitragsmodel(MONATLICH12631));
    return ret;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (obj instanceof Beitragsmodel)
    {
      Beitragsmodel v = (Beitragsmodel) obj;
      return (getKey() == v.getKey());
    }
    return false;
  }

  @Override
  public String toString()
  {
    return get(model);
  }
}
