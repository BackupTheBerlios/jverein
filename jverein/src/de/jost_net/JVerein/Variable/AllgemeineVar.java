/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/Variable/AllgemeineVar.java,v $
 * $Revision: 1.2 $
 * $Date: 2011/05/07 05:47:57 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * heiner@jverein.de
 * www.jverein.de
 * All rights reserved
 * $Log: AllgemeineVar.java,v $
 * Revision 1.2  2011/05/07 05:47:57  jost
 * Neue Variable folgejahr
 *
 * Revision 1.1  2011-05-06 15:03:54  jost
 * Neue Variablenmimik
 *
 **********************************************************************/
package de.jost_net.JVerein.Variable;

public enum AllgemeineVar
{
  AKTUELLERMONAT("aktuellermonat"), //
  FOLGEJAHR("folgejahr"), //
  FOLGEMONAT("folgemonat"), //
  TAGESDATUM("tagesdatum"), //
  VORMONAT("vormonat"); //

  private String name;

  AllgemeineVar(String name)
  {
    this.name = name;
  }

  public String getName()
  {
    return name;
  }
}
