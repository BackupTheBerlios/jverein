/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/rmi/MitgliedAddress.java,v $
 * $Revision: 1.1 $
 * $Date: 2011/04/03 11:02:36 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: MitgliedAddress.java,v $
 * Revision 1.1  2011/04/03 11:02:36  jost
 * Neu: JVerein-Mitglieder mit Bankverbindung werden in das Hibiscus-Adressbuch übernommen.
 *
 **********************************************************************/
package de.jost_net.JVerein.rmi;

import java.rmi.RemoteException;

import de.willuhn.jameica.hbci.rmi.Address;

public class MitgliedAddress implements Address
{
  private String kontonummer = null;

  private String blz = null;

  private String name = null;

  private String kommentar = null;

  private String bic = null;

  private String iban = null;

  private String kategorie = null;

  public MitgliedAddress()
  {
    //
  }

  public MitgliedAddress(String kontonummer, String blz, String name,
      String kommentar, String bic, String iban, String kategorie)
  {
    this.kontonummer = kontonummer;
    this.blz = blz;
    this.name = name;
    this.kommentar = kommentar;
    this.bic = bic;
    this.iban = iban;
    this.kategorie = kategorie;
  }

  public String getKontonummer() throws RemoteException
  {
    return kontonummer;
  }

  public String getBlz() throws RemoteException
  {
    return blz;
  }

  public String getName() throws RemoteException
  {
    return name;
  }

  public String getKommentar() throws RemoteException
  {
    return kommentar;
  }

  public String getBic() throws RemoteException
  {
    return bic;
  }

  public String getIban() throws RemoteException
  {
    return iban;
  }

  public String getKategorie() throws RemoteException
  {
    return kategorie;
  }

}
