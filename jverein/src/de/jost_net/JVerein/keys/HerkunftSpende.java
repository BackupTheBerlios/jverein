/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/keys/HerkunftSpende.java,v $
 * $Revision: 1.2 $
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
 * Herkunft Spende
 */
public class HerkunftSpende
{
  public static final int BETRIEBSVERMOEGEN = 1;

  public static final int PRIVATVERMOEGEN = 2;

  public static final int KEINEANGABEN = 3;

  private int art;

  public HerkunftSpende(int key)
  {
    this.art = key;
  }

  public int getKey()
  {
    return art;
  }

  public String getText()
  {
    return get(art);
  }

  public static String get(int key)
  {
    switch (key)
    {
      case BETRIEBSVERMOEGEN:
        return JVereinPlugin.getI18n().tr("Betriebsverm�gen");
      case PRIVATVERMOEGEN:
        return JVereinPlugin.getI18n().tr("Privatverm�gen");
      case KEINEANGABEN:
        return JVereinPlugin.getI18n().tr("keine Angaben");
      default:
        return null;
    }
  }

  public static ArrayList<HerkunftSpende> getArray()
  {
    ArrayList<HerkunftSpende> ret = new ArrayList<HerkunftSpende>();
    ret.add(new HerkunftSpende(BETRIEBSVERMOEGEN));
    ret.add(new HerkunftSpende(PRIVATVERMOEGEN));
    ret.add(new HerkunftSpende(KEINEANGABEN));
    return ret;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (obj instanceof HerkunftSpende)
    {
      HerkunftSpende v = (HerkunftSpende) obj;
      return (getKey() == v.getKey());
    }
    return false;
  }

  @Override
  public int hashCode()
  {
    return art;
  }

  @Override
  public String toString()
  {
    return get(art);
  }
}
