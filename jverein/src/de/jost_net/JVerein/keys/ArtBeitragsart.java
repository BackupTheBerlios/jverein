/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/keys/ArtBeitragsart.java,v $
 * $Revision: 1.2 $
 * $Date: 2009/06/11 21:04:03 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: ArtBeitragsart.java,v $
 * Revision 1.2  2009/06/11 21:04:03  jost
 * Vorbereitung I18N
 *
 * Revision 1.1  2008/12/13 16:23:33  jost
 * Bugfix Beitragsart.
 *
 * Revision 1.1  2008/11/29 13:12:47  jost
 * Refactoring: Code-Optimierung
 *
 **********************************************************************/
package de.jost_net.JVerein.keys;

import java.util.ArrayList;

import de.jost_net.JVerein.JVereinPlugin;

/**
 * Art der Beitragsart
 */
public class ArtBeitragsart
{
  public static final int NORMAL = 0;

  public static final int FAMILIE_ZAHLER = 1;

  public static final int FAMILIE_ANGEHOERIGER = 2;

  private int art;

  public ArtBeitragsart(int key)
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
      case NORMAL:
        return JVereinPlugin.getI18n().tr("Normal");
      case FAMILIE_ZAHLER:
        return JVereinPlugin.getI18n().tr("Familie: Zahler");
      case FAMILIE_ANGEHOERIGER:
        return JVereinPlugin.getI18n().tr("Familie: Angeh�riger");
      default:
        return null;
    }
  }

  public static ArrayList<ArtBeitragsart> getArray()
  {
    ArrayList<ArtBeitragsart> ret = new ArrayList<ArtBeitragsart>();
    ret.add(new ArtBeitragsart(NORMAL));
    ret.add(new ArtBeitragsart(FAMILIE_ZAHLER));
    ret.add(new ArtBeitragsart(FAMILIE_ANGEHOERIGER));
    return ret;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (obj instanceof ArtBeitragsart)
    {
      ArtBeitragsart v = (ArtBeitragsart) obj;
      return (getKey() == v.getKey());
    }
    return false;
  }

  @Override
  public String toString()
  {
    return get(art);
  }
}
