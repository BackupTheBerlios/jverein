/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/server/BuchungsklasseImpl.java,v $
 * $Revision: 1.4 $
 * $Date: 2011/02/12 09:42:33 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: BuchungsklasseImpl.java,v $
 * Revision 1.4  2011/02/12 09:42:33  jost
 * Statische Codeanalyse mit Findbugs
 *
 * Revision 1.3  2010-11-13 09:29:39  jost
 * Warnings entfernt.
 *
 * Revision 1.2  2010-10-15 09:58:27  jost
 * Code aufger�umt
 *
 * Revision 1.1  2009-09-10 18:19:58  jost
 * neu: Buchungsklassen
 *
 **********************************************************************/
package de.jost_net.JVerein.server;

import java.rmi.RemoteException;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.rmi.Buchungsklasse;
import de.willuhn.datasource.db.AbstractDBObject;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

public class BuchungsklasseImpl extends AbstractDBObject implements
    Buchungsklasse
{
  private static final long serialVersionUID = 500102542884220658L;

  public BuchungsklasseImpl() throws RemoteException
  {
    super();
  }

  @Override
  protected String getTableName()
  {
    return "buchungsklasse";
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
          "Buchungsklasse kann nicht gespeichert werden. Siehe system log");
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

  @Override
  public Object getAttribute(String fieldName) throws RemoteException
  {
    return super.getAttribute(fieldName);
  }
}
