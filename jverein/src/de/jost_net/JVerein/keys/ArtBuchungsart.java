/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/keys/ArtBuchungsart.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/11/29 13:12:47 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: ArtBuchungsart.java,v $
 * Revision 1.1  2008/11/29 13:12:47  jost
 * Refactoring: Code-Optimierung
 *
 **********************************************************************/
package de.jost_net.JVerein.keys;

import java.util.ArrayList;

/**
 * Art der Buchungsart
 */
public class ArtBuchungsart
{
  public static final int EINNAHME = 0;

  public static final int AUSGABE = 1;

  public static final int UMBUCHUNG = 2;

  private int art;

  public ArtBuchungsart(int key)
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
      case EINNAHME:
        return "Einnahme";
      case AUSGABE:
        return "Ausgabe";
      case UMBUCHUNG:
        return "Umbuchung";
      default:
        return null;
    }
  }

  public static ArrayList<ArtBuchungsart> getArray()
  {
    ArrayList<ArtBuchungsart> ret = new ArrayList<ArtBuchungsart>();
    ret.add(new ArtBuchungsart(EINNAHME));
    ret.add(new ArtBuchungsart(AUSGABE));
    ret.add(new ArtBuchungsart(UMBUCHUNG));
    return ret;
  }

  public String toString()
  {
    return get(art);
  }
}
