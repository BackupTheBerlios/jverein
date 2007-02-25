/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/server/KursteilnehmerImpl.java,v $
 * $Revision: 1.1 $
 * $Date: 2007/02/25 19:14:53 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: KursteilnehmerImpl.java,v $
 * Revision 1.1  2007/02/25 19:14:53  jost
 * Neu: Kursteilnehmer
 *
 **********************************************************************/
package de.jost_net.JVerein.server;

import java.rmi.RemoteException;
import java.util.Date;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.rmi.Kursteilnehmer;
import de.willuhn.datasource.db.AbstractDBObject;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

public class KursteilnehmerImpl extends AbstractDBObject implements Kursteilnehmer
{
  private static final long serialVersionUID = 1L;

  public KursteilnehmerImpl() throws RemoteException
  {
    super();
  }

  protected String getTableName()
  {
    return "kursteilnehmer";
  }

  public String getPrimaryAttribute() throws RemoteException
  {
    return "id";
  }

  protected void deleteCheck() throws ApplicationException
  {
  }

  protected void insertCheck() throws ApplicationException
  {
    try
    {
      plausi();
    }
    catch (RemoteException e)
    {
      String fehler = "Kursteilnehmer kann nicht gespeichert werden. Siehe system log";
      Logger.error(fehler, e);
      throw new ApplicationException(fehler);
    }
  }

  private void plausi() throws RemoteException, ApplicationException
  {
    if (getName() == null || getName().length() == 0)
    {
      throw new ApplicationException("Bitte Namen eingeben");
    }
    if (getVZweck1() == null || getVZweck1().length() == 0)
    {
      throw new ApplicationException("Bitte Verwendungszweck 1 eingeben");
    }
    if (getGeburtsdatum() == null)
    {
      throw new ApplicationException("Bitte Geburtsdatum eingeben");
    }
    if (getBlz() == null || getBlz().length() != 8)
    {
      throw new ApplicationException("Bitte BLZ eingeben");
    }
    if (getKonto() == null || getKonto().length() == 0)
    {
      throw new ApplicationException("Bitte Konto eingeben");
    }
    if (getBetrag() <= 0)
    {
      throw new ApplicationException("Bitte Betrag > 0 eingeben");
    }
    if (!Einstellungen.checkAccountCRC(getBlz(), getKonto()))
    {
      throw new ApplicationException(
          "Ung�ltige BLZ/Kontonummer. Bitte pr�fen Sie Ihre Eingaben.");
    }
  }

  protected void updateCheck() throws ApplicationException
  {
    try
    {
      plausi();
    }
    catch (RemoteException e)
    {
      String fehler = "Kursteilnehmer kann nicht gespeichert werden. Siehe system log";
      Logger.error(fehler, e);
      throw new ApplicationException(fehler);
    }
  }

  protected Class getForeignObject(String field) throws RemoteException
  {
    return null;
  }

  public String getName() throws RemoteException
  {
    return (String) getAttribute("name");
  }

  public void setName(String name) throws RemoteException
  {
    setAttribute("name", name);
  }

  public String getBlz() throws RemoteException
  {
    return (String) getAttribute("blz");
  }

  public void setBlz(String blz) throws RemoteException
  {
    setAttribute("blz", blz);
  }

  public String getKonto() throws RemoteException
  {
    return (String) getAttribute("konto");
  }

  public void setKonto(String konto) throws RemoteException
  {
    setAttribute("konto", konto);
  }

  public Date getGeburtsdatum() throws RemoteException
  {
    return (Date) getAttribute("geburtsdatum");
  }

  public void setGeburtsdatum(Date geburtsdatum) throws RemoteException
  {
    setAttribute("geburtsdatum", geburtsdatum);
  }

  public void setGeburtsdatum(String geburtsdatum) throws RemoteException
  {
    setAttribute("geburtsdatum", toDate(geburtsdatum));
  }

  public String getGeschlecht() throws RemoteException
  {
    return (String) getAttribute("geschlecht");
  }

  public void setGeschlecht(String geschlecht) throws RemoteException
  {
    setAttribute("geschlecht", geschlecht);
  }

  public String getVZweck1() throws RemoteException
  {
    return (String) getAttribute("vzweck1");
  }

  public void setVZweck1(String vzweck1) throws RemoteException
  {
    setAttribute("vzweck1", vzweck1);
  }

  public String getVZweck2() throws RemoteException
  {
    return (String) getAttribute("vzweck2");
  }

  public void setVZweck2(String vzweck2) throws RemoteException
  {
    setAttribute("vzweck2", vzweck2);
  }

  public void setEingabedatum() throws RemoteException
  {
    setAttribute("eingabedatum", new Date());
  }

  public Date getEingabedatum() throws RemoteException
  {
    return (Date) getAttribute("eingabedatum");
  }

  public void setAbbudatum() throws RemoteException
  {
    setAttribute("abbudatum", new Date());
  }

  public Date getAbbudatum() throws RemoteException
  {
    return (Date) getAttribute("abbudatum");
  }

  public double getBetrag() throws RemoteException
  {
    Double d = (Double) getAttribute("betrag");
    if (d == null)
      return 0;
    return d.doubleValue();
  }

  public void setBetrag(double d) throws RemoteException
  {
    setAttribute("betrag", new Double(d));
  }

  public Object getAttribute(String fieldName) throws RemoteException
  {
    return super.getAttribute(fieldName);
  }

  private Date toDate(String datum)
  {
    Date d = null;

    try
    {
      d = Einstellungen.DATEFORMAT.parse(datum);
    }
    catch (Exception e)
    {
      //
    }
    return d;
  }
}
