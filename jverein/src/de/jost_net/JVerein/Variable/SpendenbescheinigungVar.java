/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/Variable/SpendenbescheinigungVar.java,v $
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

public enum SpendenbescheinigungVar
{
  EMPFAENGER("spendenbescheinigung_empfaenger"), //
  BETRAG("spendenbescheinigung_betrag"), //
  BETRAGINWORTEN("spendenbescheinigung_betraginworten"), //
  BESCHEINIGUNGDATUM("spendenbescheinigung_datum"), //
  SPENDEDATUM("spendenbescheinigung_spendedatum"), //
  ERSATZAUFWENDUNGEN("spendenbescheinigung_ersatzaufwendungen"); //

  private String name;

  SpendenbescheinigungVar(String name)
  {
    this.name = name;
  }

  public String getName()
  {
    return name;
  }
}
