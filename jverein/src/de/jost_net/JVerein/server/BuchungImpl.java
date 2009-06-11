/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/server/BuchungImpl.java,v $
 * $Revision: 1.10 $
 * $Date: 2009/06/11 21:04:23 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: BuchungImpl.java,v $
 * Revision 1.10  2009/06/11 21:04:23  jost
 * Vorbereitung I18N
 *
 * Revision 1.9  2008/12/03 22:01:02  jost
 * Erweiterung um Auszugs- und Blattnummer
 *
 * Revision 1.8  2008/11/29 13:15:09  jost
 * Refactoring: Warnungen beseitigt.
 *
 * Revision 1.7  2008/06/28 17:07:15  jost
 * Bearbeiten nur, wenn kein Jahresabschluss vorliegt.
 *
 * Revision 1.6  2008/05/24 16:40:39  jost
 * Wegfall der Spalte Saldo
 *
 * Revision 1.5  2008/05/22 06:56:05  jost
 * Buchführung
 *
 * Revision 1.4  2008/03/16 07:38:12  jost
 * Reaktivierung Buchführung
 *
 * Revision 1.2  2007/02/23 20:28:41  jost
 * Mail- und Webadresse im Header korrigiert.
 *
 * Revision 1.1  2006/09/20 15:39:48  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.JVerein.server;

import java.rmi.RemoteException;
import java.util.Date;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.rmi.Buchung;
import de.jost_net.JVerein.rmi.Buchungsart;
import de.jost_net.JVerein.rmi.Jahresabschluss;
import de.jost_net.JVerein.rmi.Konto;
import de.willuhn.datasource.db.AbstractDBObject;
import de.willuhn.datasource.rmi.DBIterator;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

public class BuchungImpl extends AbstractDBObject implements Buchung
{
  private static final long serialVersionUID = 1L;

  public BuchungImpl() throws RemoteException
  {
    super();
  }

  protected String getTableName()
  {
    return "buchung";
  }

  public String getPrimaryAttribute() throws RemoteException
  {
    return "id";
  }

  protected void deleteCheck() throws ApplicationException
  {
    insertCheck();
  }

  protected void insertCheck() throws ApplicationException
  {
    try
    {
      plausi();
    }
    catch (RemoteException e)
    {
      Logger.error("insert check of buchung failed", e);
      throw new ApplicationException(JVereinPlugin.getI18n().tr(
          "Buchung kann nicht gespeichert werden. Siehe system log"));
    }
  }

  private void plausi() throws RemoteException, ApplicationException
  {
    if (getKonto() == null)
    {
      throw new ApplicationException(JVereinPlugin.getI18n().tr(
          "Bitte Konto eingeben"));
    }
    if (getDatum() == null)
    {
      throw new ApplicationException(JVereinPlugin.getI18n().tr(
          "Bitte Datum eingeben"));
    }
    Jahresabschluss ja = getJahresabschluss();
    if (ja != null)
    {
      throw new ApplicationException(
          JVereinPlugin
              .getI18n()
              .tr(
                  "Buchung kann nicht gespeichert werden. Zeitraum ist bereits abgeschlossen!"));
    }
  }

  protected void updateCheck() throws ApplicationException
  {
    insertCheck();
  }

  @SuppressWarnings("unchecked")
  protected Class getForeignObject(String field) throws RemoteException
  {
    if ("buchungsart".equals(field))
    {
      return Buchungsart.class;
    }
    else if ("konto".equals(field))
    {
      return Konto.class;
    }
    return null;
  }

  public Integer getUmsatzid() throws RemoteException
  {

    return (Integer) getAttribute("umsatzid");
  }

  public void setUmsatzid(Integer umsatzid) throws RemoteException
  {
    setAttribute("umsatzid", umsatzid);
  }

  public Konto getKonto() throws RemoteException
  {
    return (Konto) getAttribute("konto");
  }

  public void setKonto(Konto konto) throws RemoteException
  {
    setAttribute("konto", konto);
  }

  public Integer getAuszugsnummer() throws RemoteException
  {
    return (Integer) getAttribute("auszugsnummer");
  }

  public void setAuszugsnummer(Integer auszugsnummer) throws RemoteException
  {
    setAttribute("auszugsnummer", auszugsnummer);
  }

  public Integer getBlattnummer() throws RemoteException
  {
    return (Integer) getAttribute("blattnummer");
  }

  public void setBlattnummer(Integer blattnummer) throws RemoteException
  {
    setAttribute("blattnummer", blattnummer);
  }

  public String getName() throws RemoteException
  {
    return (String) getAttribute("name");
  }

  public void setName(String name) throws RemoteException
  {
    setAttribute("name", name);
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

  public String getZweck() throws RemoteException
  {
    return (String) getAttribute("zweck");
  }

  public void setZweck(String zweck) throws RemoteException
  {
    setAttribute("zweck", zweck);
  }

  public String getZweck2() throws RemoteException
  {
    return (String) getAttribute("zweck2");
  }

  public void setZweck2(String zweck2) throws RemoteException
  {
    setAttribute("zweck2", zweck2);
  }

  public Date getDatum() throws RemoteException
  {
    return (Date) getAttribute("datum");
  }

  public void setDatum(Date datum) throws RemoteException
  {
    setAttribute("datum", datum);
  }

  public void setDatum(String datum) throws RemoteException
  {
    setAttribute("datum", toDate(datum));
  }

  public String getArt() throws RemoteException
  {
    return (String) getAttribute("art");
  }

  public void setArt(String art) throws RemoteException
  {
    setAttribute("art", art);
  }

  public String getKommentar() throws RemoteException
  {
    return (String) getAttribute("kommentar");
  }

  public void setKommentar(String kommentar) throws RemoteException
  {
    setAttribute("kommentar", kommentar);
  }

  public Buchungsart getBuchungsart() throws RemoteException
  {
    return (Buchungsart) getAttribute("buchungsart");
  }

  public int getBuchungsartId() throws RemoteException
  {
    return Integer.parseInt(getBuchungsart().getID());
  }

  public void setBuchungsart(Integer buchungsart) throws RemoteException
  {
    setAttribute("buchungsart", buchungsart);
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

  public Jahresabschluss getJahresabschluss() throws RemoteException
  {
    DBIterator it = Einstellungen.getDBService().createList(
        Jahresabschluss.class);
    it.addFilter("von <= ?", new Object[] { (Date) getDatum() });
    it.addFilter("bis >= ?", new Object[] { (Date) getDatum() });
    if (it.hasNext())
    {
      Jahresabschluss ja = (Jahresabschluss) it.next();
      return ja;
    }
    return null;
  }
}
