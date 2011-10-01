/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/server/BuchungsartImpl.java,v $
 * $Revision: 1.16 $
 * $Date: 2011/10/01 21:50:35 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/
package de.jost_net.JVerein.server;

import java.rmi.RemoteException;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.rmi.Buchungsart;
import de.jost_net.JVerein.rmi.Buchungsklasse;
import de.jost_net.JVerein.server.Cache;
import de.willuhn.datasource.db.AbstractDBObject;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

public class BuchungsartImpl extends AbstractDBObject implements Buchungsart
{

  private static final long serialVersionUID = 500102542884220658L;

  public BuchungsartImpl() throws RemoteException
  {
    super();
  }

  @Override
  protected String getTableName()
  {
    return "buchungsart";
  }

  @Override
  public String getPrimaryAttribute()
  {
    return "bezeichnung";
  }

  @Override
  protected void deleteCheck()
  {
    //
  }

  @Override
  protected void insertCheck() throws ApplicationException
  {
    try
    {
      if (getBezeichnung() == null || getBezeichnung().length() == 0)
      {
        throw new ApplicationException(JVereinPlugin.getI18n().tr(
            "Bitte Bezeichnung eingeben"));
      }
      if (getNummer() < 0)
      {
        throw new ApplicationException(JVereinPlugin.getI18n().tr(
            "Nummer nicht g�ltig"));
      }
    }
    catch (RemoteException e)
    {
      String fehler = JVereinPlugin.getI18n().tr(
          "Buchungsart kann nicht gespeichert werden. Siehe system log");
      Logger.error(fehler, e);
      throw new ApplicationException(fehler);
    }
  }

  @Override
  protected void updateCheck() throws ApplicationException
  {
    insertCheck();
  }

  @Override
  protected Class<?> getForeignObject(String arg0)
  {
    if ("buchungsklasse".equals(arg0))
    {
      return Buchungsklasse.class;
    }
    return null;
  }

  public String getBezeichnung() throws RemoteException
  {
    return (String) getAttribute("bezeichnung");
  }

  public void setBezeichnung(String bezeichnung) throws RemoteException
  {
    setAttribute("bezeichnung", bezeichnung);
  }

  public int getNummer() throws RemoteException
  {
    Integer i = (Integer) getAttribute("nummer");
    if (i == null)
      return 0;
    return i.intValue();
  }

  public void setNummer(int i) throws RemoteException
  {
    setAttribute("nummer", Integer.valueOf(i));
  }

  public int getArt() throws RemoteException
  {
    Integer i = (Integer) getAttribute("art");
    if (i == null)
    {
      return 0;
    }
    return i.intValue();
  }

  public void setArt(int art) throws RemoteException
  {
    setAttribute("art", art);
  }

  public Buchungsklasse getBuchungsklasse() throws RemoteException
  {
    return (Buchungsklasse) getAttribute("buchungsklasse");
  }

  public int getBuchungsklasseId() throws RemoteException
  {
    return Integer.parseInt(getBuchungsklasse().getID());
  }

  public void setBuchungsklasse(Integer buchungsklasse) throws RemoteException
  {
    setAttribute("buchungsklasse", buchungsklasse);
  }

  public Boolean getSpende() throws RemoteException
  {
    return Util.getBoolean(getAttribute("spende"));
  }

  public void setSpende(Boolean spende) throws RemoteException
  {
    setAttribute("spende", Boolean.valueOf(spende));
  }

  public void delete() throws RemoteException, ApplicationException
  {
    super.delete();
    Cache.get(Buchungsart.class, false).remove(this); // Aus Cache loeschen
  }

  public void store() throws RemoteException, ApplicationException
  {
    super.store();
    Cache.get(Buchungsart.class, false).put(this); // Cache aktualisieren
  }
}
