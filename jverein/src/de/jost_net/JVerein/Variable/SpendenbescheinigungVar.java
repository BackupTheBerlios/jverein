/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/Variable/SpendenbescheinigungVar.java,v $
 * $Revision: 1.1 $
 * $Date: 2011/05/27 18:49:42 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * heiner@jverein.de
 * www.jverein.de
 * All rights reserved
 * $Log: SpendenbescheinigungVar.java,v $
 * Revision 1.1  2011/05/27 18:49:42  jost
 * Vereinheitlichung Variable
 *
 * Revision 1.2  2011-05-07 05:47:57  jost
 * Neue Variable folgejahr
 *
 * Revision 1.1  2011-05-06 15:03:54  jost
 * Neue Variablenmimik
 *
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
