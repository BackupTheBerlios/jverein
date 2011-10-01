/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/Variable/MitgliedskontoVar.java,v $
 * $Revision: 1.2 $
 * $Date: 2011/10/01 21:52:00 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * heiner@jverein.de
 * www.jverein.de
 * All rights reserved
 **********************************************************************/
package de.jost_net.JVerein.Variable;

public enum MitgliedskontoVar
{
  ZAHLUNGSGRUND("mitgliedskonto_zahlungsgrund"), //
  ZAHLUNGSGRUND1("mitgliedskonto_zahlungsgrund1"), //
  ZAHLUNGSGRUND2("mitgliedskonto_zahlungsgrund2"), //
  BUCHUNGSDATUM("mitgliedskonto_buchungsdatum"), //
  BETRAG("mitgliedskonto_betrag");

  private String name;

  MitgliedskontoVar(String name)
  {
    this.name = name;
  }

  public String getName()
  {
    return name;
  }
}
