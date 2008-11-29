/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/keys/Beitragsmodel.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/11/29 13:12:56 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: Beitragsmodel.java,v $
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
        return "j�hrlich";
      case HALBJAEHRLICH:
        return "halbj�hrlich";
      case VIERTELJAEHRLICH:
        return "viertelj�hrlich";
      case MONATLICH:
        return "monatlich";
      case MONATLICH12631:
        return "monatlich mit monatl., viertel-, halb- oder j�hrlicher Zahlungsweise";
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

  public String toString()
  {
    return get(model);
  }
}
