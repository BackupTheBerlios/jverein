/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/keys/Beitragsmodel.java,v $
 * $Revision: 1.3 $
 * $Date: 2009/06/11 21:04:03 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: Beitragsmodel.java,v $
 * Revision 1.3  2009/06/11 21:04:03  jost
 * Vorbereitung I18N
 *
 * Revision 1.2  2008/12/13 16:23:55  jost
 * equals()-Methode implementiert.
 *
 * Revision 1.1  2008/11/29 13:12:56  jost
 * Refactoring: Code-Optimierung
 *
 **********************************************************************/
package de.jost_net.JVerein.keys;

import java.util.ArrayList;

import de.jost_net.JVerein.JVereinPlugin;

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
        return JVereinPlugin.getI18n().tr("j�hrlich");
      case HALBJAEHRLICH:
        return JVereinPlugin.getI18n().tr("halbj�hrlich");
      case VIERTELJAEHRLICH:
        return JVereinPlugin.getI18n().tr("viertelj�hrlich");
      case MONATLICH:
        return JVereinPlugin.getI18n().tr("monatlich");
      case MONATLICH12631:
        return JVereinPlugin
            .getI18n()
            .tr(
                "monatlich mit monatl., viertel-, halb- oder j�hrlicher Zahlungsweise");
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
