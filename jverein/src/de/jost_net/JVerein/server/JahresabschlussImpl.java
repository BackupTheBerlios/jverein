/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/server/JahresabschlussImpl.java,v $
 * $Revision: 1.3 $
 * $Date: 2009/02/07 20:32:39 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: JahresabschlussImpl.java,v $
 * Revision 1.3  2009/02/07 20:32:39  jost
 * Bugfix: Anfangsbestand kann auch innerhalb des Gesch�ftsjahres liegen.
 *
 * Revision 1.2  2008/11/29 13:16:04  jost
 * Refactoring: Warnungen beseitigt.
 *
 * Revision 1.1  2008/06/28 17:07:25  jost
 * Neu: Jahresabschluss
 *
 **********************************************************************/
package de.jost_net.JVerein.server;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.Date;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.rmi.Anfangsbestand;
import de.jost_net.JVerein.rmi.Buchung;
import de.jost_net.JVerein.rmi.Jahresabschluss;
import de.jost_net.JVerein.rmi.Konto;
import de.jost_net.JVerein.util.Geschaeftsjahr;
import de.willuhn.datasource.db.AbstractDBObject;
import de.willuhn.datasource.rmi.DBIterator;
import de.willuhn.util.ApplicationException;

public class JahresabschlussImpl extends AbstractDBObject implements
    Jahresabschluss
{
  private static final long serialVersionUID = 1L;

  public JahresabschlussImpl() throws RemoteException
  {
    super();
  }

  protected String getTableName()
  {
    return "jahresabschluss";
  }

  public String getPrimaryAttribute() throws RemoteException
  {
    return "id";
  }

  protected void deleteCheck() throws ApplicationException
  {
    try
    {
      DBIterator it = Einstellungen.getDBService().createList(
          Jahresabschluss.class);
      it.addFilter("von > ?", new Object[] { getVon() });
      if (it.hasNext())
      {
        throw new ApplicationException(
            "Jahresabschluss kann nicht gel�scht werden. Es existieren neuere Abschl�sse!");
      }
    }
    catch (RemoteException e)
    {
      String msg = "Jahresabschluss kann nicht gel�scht werden. Siehe system log";
      throw new ApplicationException(msg);
    }

  }

  protected void insertCheck() throws ApplicationException
  {
    try
    {
      if (hasBuchungenOhneBuchungsart())
      {
        throw new ApplicationException(
            "Achtung! Es existieren noch Buchungen ohne Buchungsart. Kein Abschluss m�glich!");
      }
      if (getName() == null || getName().length() == 0)
      {
        throw new ApplicationException(
            "Name des Verantwortlichen f�r den Abschluss fehlt");
      }
      Konto k = (Konto) Einstellungen.getDBService().createObject(Konto.class,
          null);
      Geschaeftsjahr gj = new Geschaeftsjahr(getVon());
      DBIterator it = k.getKontenEinesJahres(gj);
      while (it.hasNext())
      {
        Konto k1 = (Konto) it.next();
        DBIterator anfangsbestaende = Einstellungen.getDBService().createList(
            Anfangsbestand.class);
        anfangsbestaende.addFilter("konto = ?", new Object[] { k1.getID() });
        anfangsbestaende.addFilter("datum >= ?", new Object[] { gj
            .getBeginnGeschaeftsjahr() });
        if (!anfangsbestaende.hasNext())
        {
          throw new ApplicationException("F�r Konto " + k1.getNummer() + " "
              + k1.getBezeichnung() + " fehlt der Anfangsbestand.");
        }
      }
    }
    catch (ParseException e)
    {
      throw new ApplicationException("Ung�ltiges von-Datum");
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      String msg = "Jahresabschluss kann nicht gespeichert werden. Siehe system log";
      throw new ApplicationException(msg);
    }
  }

  protected void updateCheck() throws ApplicationException
  {
    insertCheck();
  }

  private boolean hasBuchungenOhneBuchungsart() throws RemoteException
  {
    DBIterator it = Einstellungen.getDBService().createList(Buchung.class);
    it.addFilter("datum >= ?", new Object[] { getVon() });
    it.addFilter("datum <= ?", new Object[] { getBis() });
    it.addFilter("buchungsart is null");
    return it.hasNext();
  }

  @SuppressWarnings("unchecked")
  protected Class getForeignObject(String field) throws RemoteException
  {
    return null;
  }

  public Date getVon() throws RemoteException
  {
    return (Date) getAttribute("von");
  }

  public void setVon(Date von) throws RemoteException
  {
    setAttribute("von", von);
  }

  public Date getBis() throws RemoteException
  {
    return (Date) getAttribute("bis");
  }

  public void setBis(Date bis) throws RemoteException
  {
    setAttribute("bis", bis);
  }

  public Date getDatum() throws RemoteException
  {
    return (Date) getAttribute("datum");
  }

  public void setDatum(Date datum) throws RemoteException
  {
    setAttribute("datum", datum);
  }

  public String getName() throws RemoteException
  {
    return (String) getAttribute("name");
  }

  public void setName(String name) throws RemoteException
  {
    setAttribute("name", name);
  }

  public Object getAttribute(String fieldName) throws RemoteException
  {
    return super.getAttribute(fieldName);
  }
}
