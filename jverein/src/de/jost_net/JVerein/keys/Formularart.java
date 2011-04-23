/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/keys/Formularart.java,v $
 * $Revision: 1.6 $
 * $Date: 2011/04/23 06:56:56 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: Formularart.java,v $
 * Revision 1.6  2011/04/23 06:56:56  jost
 * Neu: Freie Formulare
 *
 * Revision 1.5  2011-02-12 09:41:26  jost
 * Statische Codeanalyse mit Findbugs
 *
 * Revision 1.4  2010-08-16 20:17:58  jost
 * Neu: Mahnung
 *
 * Revision 1.3  2009/06/11 21:04:03  jost
 * Vorbereitung I18N
 *
 * Revision 1.2  2008/12/13 16:24:04  jost
 * equals()-Methode implementiert.
 *
 * Revision 1.1  2008/11/29 13:13:06  jost
 * Refactoring: Code-Optimierung
 *
 **********************************************************************/
package de.jost_net.JVerein.keys;

import java.util.ArrayList;

import de.jost_net.JVerein.JVereinPlugin;

/**
 * Formularart
 */
public class Formularart
{
  public static final int SPENDENBESCHEINIGUNG = 1;

  public static final int RECHNUNG = 2;

  public static final int MAHNUNG = 3;
  public static final int FREIESFORMULAR = 4;

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
        return JVereinPlugin.getI18n().tr("Spendenbescheinigung");
      case RECHNUNG:
        return JVereinPlugin.getI18n().tr("Rechnung");
      case MAHNUNG:
        return JVereinPlugin.getI18n().tr("Mahnung");
      case FREIESFORMULAR:
        return JVereinPlugin.getI18n().tr("Freies Formular");
      default:
        return null;
    }
  }

  public static ArrayList<Formularart> getArray()
  {
    ArrayList<Formularart> ret = new ArrayList<Formularart>();
    ret.add(new Formularart(SPENDENBESCHEINIGUNG));
    ret.add(new Formularart(RECHNUNG));
    ret.add(new Formularart(MAHNUNG));
    ret.add(new Formularart(FREIESFORMULAR));
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
  public int hashCode()
  {
    return formularart;
  }

  @Override
  public String toString()
  {
    return get(formularart);
  }
}
