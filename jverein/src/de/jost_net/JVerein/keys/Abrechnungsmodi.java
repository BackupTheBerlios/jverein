/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/keys/Abrechnungsmodi.java,v $
 * $Revision: 1.2 $
 * $Date: 2010/05/18 20:23:26 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: Abrechnungsmodi.java,v $
 * Revision 1.2  2010/05/18 20:23:26  jost
 * �berfl�ssige Imports entfernt.
 *
 * Revision 1.1  2010/04/25 13:56:11  jost
 * Vorarbeiten Mitgliedskonto
 *
 **********************************************************************/
package de.jost_net.JVerein.keys;

import de.jost_net.JVerein.JVereinPlugin;

/**
 * Abrechnungsmodi
 */
public class Abrechnungsmodi
{
  public static final int KEINBEITRAG = 0;

  public static final int JAEHRLICH = 1;

  public static final int HALBJAEHRLICH = 2;

  public static final int VIERTELJAEHRLICH = 3;

  public static final int MONATLICH = 4;

  public static final int JA = 5;

  public static final int HA = 6;

  public static final int VI = 7;

  public static final int JAHAVIMO = 8;

  public static final int JAVIMO = 9;

  public static final int HAVIMO = 10;

  public static final int VIMO = 11;

  public static final int MO = 12;

  public static final int EINGETRETENEMITGLIEDER = 99;

  private int abrechnungsmodus;

  public Abrechnungsmodi(int abrechnungsmodus)
  {
    this.abrechnungsmodus = abrechnungsmodus;
  }

  public int getKey()
  {
    return abrechnungsmodus;
  }

  public String getText()
  {
    return get(abrechnungsmodus);
  }

  public static String get(int key)
  {
    switch (key)
    {
      case KEINBEITRAG:
        return JVereinPlugin.getI18n().tr("keine Beitragsabrechnung");
      case JAEHRLICH:
        return JVereinPlugin.getI18n().tr("Jahresbeitr�ge");
      case HALBJAEHRLICH:
        return JVereinPlugin.getI18n().tr("Halbjahresbeitr�ge");
      case VIERTELJAEHRLICH:
        return JVereinPlugin.getI18n().tr("Vierteljahresbeitr�ge");
      case MONATLICH:
      case MO:
        return JVereinPlugin.getI18n().tr("Monatsbeitr�ge");
      case VI:
        return JVereinPlugin.getI18n().tr("Viertelj�hrlich");
      case HA:
        return JVereinPlugin.getI18n().tr("Halbj�hrlich");
      case JA:
        return JVereinPlugin.getI18n().tr("J�hrlich");
      case JAHAVIMO:
        return JVereinPlugin.getI18n().tr(
            "Jahres-, Halbjahres-, Vierteljahres- und Monatsbeitr�ge");
      case JAVIMO:
        return JVereinPlugin.getI18n().tr(
            "Jahres-, Vierteljahres- und Monatsbeitr�ge");
      case HAVIMO:
        return JVereinPlugin.getI18n().tr(
            "Halbjahres-, Vierteljahres- und Monatsbeitr�ge");
      case VIMO:
        return JVereinPlugin.getI18n().tr("Vierteljahres- und Monatsbeitr�ge");
      case EINGETRETENEMITGLIEDER:
        return JVereinPlugin.getI18n().tr("eingetretene Mitglieder");
      default:
        return null;
    }
  }

  @Override
  public boolean equals(Object obj)
  {
    if (obj instanceof Formularart)
    {
      Abrechnungsmodi v = (Abrechnungsmodi) obj;
      return (getKey() == v.getKey());
    }
    return false;
  }

  @Override
  public String toString()
  {
    return get(abrechnungsmodus);
  }
}
