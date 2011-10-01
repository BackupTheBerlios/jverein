/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/keys/IntervallZusatzzahlung.java,v $
 * $Revision: 1.5 $
 * $Date: 2011/10/01 21:48:33 $
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
 * Intervall Zusatzzahlungen
 */
public class IntervallZusatzzahlung
{
  public static final int KEIN = 0;

  public static final int MONATLICH = 1;

  public static final int ZWEIMONATLICH = 2;

  public static final int VIERTELJAEHRLICH = 3;

  public static final int HALBJAEHRLICH = 6;

  public static final int JAEHRLICH = 12;

  private int intervall;

  public IntervallZusatzzahlung(int key)
  {
    this.intervall = key;
  }

  public int getKey()
  {
    return intervall;
  }

  public String getText()
  {
    return get(intervall);
  }

  public static String get(int key)
  {
    switch (key)
    {
      case KEIN:
        return JVereinPlugin.getI18n().tr("kein");
      case MONATLICH:
        return JVereinPlugin.getI18n().tr("monatlich");
      case ZWEIMONATLICH:
        return JVereinPlugin.getI18n().tr("zweimonatlich");
      case VIERTELJAEHRLICH:
        return JVereinPlugin.getI18n().tr("viertelj�hrlich");
      case HALBJAEHRLICH:
        return JVereinPlugin.getI18n().tr("halbj�hrlich");
      case JAEHRLICH:
        return JVereinPlugin.getI18n().tr("j�hrlich");
      default:
        return null;
    }
  }

  public static ArrayList<IntervallZusatzzahlung> getArray()
  {
    ArrayList<IntervallZusatzzahlung> ret = new ArrayList<IntervallZusatzzahlung>();
    ret.add(new IntervallZusatzzahlung(KEIN));
    ret.add(new IntervallZusatzzahlung(MONATLICH));
    ret.add(new IntervallZusatzzahlung(ZWEIMONATLICH));
    ret.add(new IntervallZusatzzahlung(VIERTELJAEHRLICH));
    ret.add(new IntervallZusatzzahlung(HALBJAEHRLICH));
    ret.add(new IntervallZusatzzahlung(JAEHRLICH));
    ret.add(new IntervallZusatzzahlung(MONATLICH));
    return ret;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (obj instanceof IntervallZusatzzahlung)
    {
      IntervallZusatzzahlung v = (IntervallZusatzzahlung) obj;
      return (getKey() == v.getKey());
    }
    return false;
  }

  @Override
  public int hashCode()
  {
    return intervall;
  }

  @Override
  public String toString()
  {
    return get(intervall);
  }
}
