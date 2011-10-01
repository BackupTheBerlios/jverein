/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/Variable/AllgemeineVar.java,v $
 * $Revision: 1.3 $
 * $Date: 2011/10/01 21:52:00 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * heiner@jverein.de
 * www.jverein.de
 * All rights reserved
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
