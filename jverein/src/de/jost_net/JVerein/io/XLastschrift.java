/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/io/XLastschrift.java,v $
 * $Revision: 1.2 $
 * $Date: 2011/02/23 18:02:27 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: XLastschrift.java,v $
 * Revision 1.2  2011/02/23 18:02:27  jost
 * Neu: Kompakte Abbuchung
 *
 * Revision 1.1  2011-02-12 09:40:32  jost
 * Vorbereitung kompakte Abbuchung
 *
 **********************************************************************/
package de.jost_net.JVerein.io;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class XLastschrift
{
  private ArrayList<String> zahlungspflichtiger = new ArrayList<String>();

  private ArrayList<String> verwendungszweck = new ArrayList<String>();

  private double betrag;

  private int blz;

  private long konto;

  public final static DecimalFormat DECIMALFORMAT = new DecimalFormat(
      "###,##0.00");

  public XLastschrift()
  {
  }

  public void addZahlungspflichtigen(String zahlungspflichtiger)
  {
    this.zahlungspflichtiger.add(zahlungspflichtiger);
  }

  public String getZahlungspflichtigen(int pos)
  {
    return this.zahlungspflichtiger.get(pos);
  }

  public int getAnzahlZahlungspflichtige()
  {
    return this.zahlungspflichtiger.size();
  }

  public void addVerwendungszweck(String verwendungszweck)
  {
    this.verwendungszweck.add(verwendungszweck);
  }

  public String getVerwendungszweck(int pos)
  {
    return verwendungszweck.get(pos);
  }

  public void modifyVerwendungszweck(int pos, String verwendungszweck,
      double betrag)
  {
    String btrg = DECIMALFORMAT.format(betrag);
    int restlaenge = 27 - btrg.length() - 1;
    if (verwendungszweck.length() < restlaenge)
    {
      while (verwendungszweck.length() < restlaenge)
      {
        verwendungszweck += " ";
      }
    }
    else
    {
      verwendungszweck = verwendungszweck.substring(0, restlaenge);
    }
    this.verwendungszweck.set(pos, verwendungszweck + " " + btrg);
  }

  public int getAnzahlVerwendungszwecke()
  {
    return verwendungszweck.size();
  }

  public void setBetrag(double betrag)
  {
    this.betrag = betrag;
  }

  public double getBetrag()
  {
    return betrag;
  }

  public void setBlz(int blz)
  {
    this.blz = blz;
  }

  public int getBlz()
  {
    return this.blz;
  }

  public void setKonto(long konto)
  {
    this.konto = konto;
  }

  public long getKonto()
  {
    return this.konto;
  }

  public String getBankverbindung()
  {
    return this.blz + "" + this.konto;
  }

  public void add(XLastschrift ls)
  {
    betrag = betrag + ls.getBetrag();
    for (int i = 0; i < ls.getAnzahlVerwendungszwecke(); i++)
    {
      addVerwendungszweck(ls.getVerwendungszweck(i));
    }
  }

  public String toString()
  {
    StringBuilder zpfl = new StringBuilder();
    for (String z : zahlungspflichtiger)
    {
      zpfl.append(z);
    }
    return "BLZ: " + getBlz() + ", Konto: " + getKonto() + ", Betrag: "
        + getBetrag() + ", Zahlungspflichtiger: " + zpfl.toString()
        + ", Verwendungszweck: " + verwendungszweck;
  }
}
