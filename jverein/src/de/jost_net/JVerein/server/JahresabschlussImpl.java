/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/server/JahresabschlussImpl.java,v $
 * $Revision: 1.7 $
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
import java.text.ParseException;
import java.util.Date;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.JVereinPlugin;
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

  @Override
  protected String getTableName()
  {
    return "jahresabschluss";
  }

  @Override
  public String getPrimaryAttribute()
  {
    return "id";
  }

  @Override
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
            JVereinPlugin
                .getI18n()
                .tr("Jahresabschluss kann nicht gel�scht werden. Es existieren neuere Abschl�sse!"));
      }
    }
    catch (RemoteException e)
    {
      String msg = JVereinPlugin.getI18n().tr(
          "Jahresabschluss kann nicht gel�scht werden. Siehe system log");
      throw new ApplicationException(msg);
    }

  }

  @Override
  protected void insertCheck() throws ApplicationException
  {
    try
    {
      if (hasBuchungenOhneBuchungsart())
      {
        throw new ApplicationException(
            JVereinPlugin
                .getI18n()
                .tr("Achtung! Es existieren noch Buchungen ohne Buchungsart. Kein Abschluss m�glich!"));
      }
      if (getName() == null || getName().length() == 0)
      {
        throw new ApplicationException(JVereinPlugin.getI18n().tr(
            "Name des Verantwortlichen f�r den Abschluss fehlt"));
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
        anfangsbestaende.addFilter("datum >= ?",
            new Object[] { gj.getBeginnGeschaeftsjahr() });
        if (!anfangsbestaende.hasNext())
        {
          throw new ApplicationException(JVereinPlugin.getI18n().tr(
              "F�r Konto {0} {1} fehlt der Anfangsbestand.",
              new String[] { k1.getNummer(), k1.getBezeichnung() }));
        }
      }
    }
    catch (ParseException e)
    {
      throw new ApplicationException(JVereinPlugin.getI18n().tr(
          "Ung�ltiges von-Datum"));
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      String msg = JVereinPlugin.getI18n().tr(
          "Jahresabschluss kann nicht gespeichert werden. Siehe system log");
      throw new ApplicationException(msg);
    }
  }

  @Override
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

  @Override
  protected Class<?> getForeignObject(String field)
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

  @Override
  public Object getAttribute(String fieldName) throws RemoteException
  {
    return super.getAttribute(fieldName);
  }
}
