/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/server/Attic/ManuellerZahlungseingangImpl.java,v $
 * $Revision: 1.1 $
 * $Date: 2007/03/13 19:58:53 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: ManuellerZahlungseingangImpl.java,v $
 * Revision 1.1  2007/03/13 19:58:53  jost
 * Neu: Manueller Zahlungseingang.
 *
 **********************************************************************/
package de.jost_net.JVerein.server;

import java.rmi.RemoteException;
import java.util.Date;

import de.jost_net.JVerein.rmi.ManuellerZahlungseingang;
import de.willuhn.datasource.db.AbstractDBObject;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

public class ManuellerZahlungseingangImpl extends AbstractDBObject implements
    ManuellerZahlungseingang
{
  private static final long serialVersionUID = 1L;

  public ManuellerZahlungseingangImpl() throws RemoteException
  {
    super();
  }

  protected String getTableName()
  {
    return "manuellerzahlungseingang";
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
      String fehler = "ManuellerZahlungseingang kann nicht gespeichert werden. Siehe system log";
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
    if (getBetrag() <= 0)
    {
      throw new ApplicationException("Bitte Betrag > 0 eingeben");
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
      String fehler = "ManuellerZahlungseingang kann nicht gespeichert werden. Siehe system log";
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

  public void setEingangsdatum(Date datum) throws RemoteException
  {
    setAttribute("eingangsdatum", datum);
  }

  public Date getEingangsdatum() throws RemoteException
  {
    return (Date) getAttribute("eingangsdatum");
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
}