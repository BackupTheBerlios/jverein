/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/keys/Spendenart.java,v $
 * $Revision: 1.1 $
 * $Date: 2011/03/13 13:48:47 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: Spendenart.java,v $
 * Revision 1.1  2011/03/13 13:48:47  jost
 * Neu: Sachspenden.
 *
 **********************************************************************/
package de.jost_net.JVerein.keys;

import java.util.ArrayList;

import de.jost_net.JVerein.JVereinPlugin;

/**
 * Abrechnungsausgabe
 */
public class Spendenart
{
  public static final int GELDSPENDE = 1;

  public static final int SACHSPENDE = 2;

  private int art;

  public Spendenart(int key)
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
      case GELDSPENDE:
        return JVereinPlugin.getI18n().tr("Geldspende");
      case SACHSPENDE:
        return JVereinPlugin.getI18n().tr("Sachspende");
      default:
        return null;
    }
  }

  public static ArrayList<Spendenart> getArray()
  {
    ArrayList<Spendenart> ret = new ArrayList<Spendenart>();
    ret.add(new Spendenart(GELDSPENDE));
    ret.add(new Spendenart(SACHSPENDE));
    return ret;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (obj instanceof Spendenart)
    {
      Spendenart v = (Spendenart) obj;
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
