/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/server/KontoImpl.java,v $
 * $Revision: 1.4 $
 * $Date: 2008/11/29 13:16:26 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: KontoImpl.java,v $
 * Revision 1.4  2008/11/29 13:16:26  jost
 * Refactoring: Warnungen beseitigt.
 *
 * Revision 1.3  2008/06/28 17:07:46  jost
 * Neu: Jahresabschluss
 *
 * Revision 1.2  2008/05/26 18:59:17  jost
 * Neu: Eröffnungsdatum
 *
 * Revision 1.1  2008/05/22 06:56:28  jost
 * Buchführung
 *
 **********************************************************************/
package de.jost_net.JVerein.server;

import java.rmi.RemoteException;
import java.util.Date;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.rmi.Konto;
import de.jost_net.JVerein.util.Geschaeftsjahr;
import de.willuhn.datasource.db.AbstractDBObject;
import de.willuhn.datasource.rmi.DBIterator;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

public class KontoImpl extends AbstractDBObject implements Konto
{
  private static final long serialVersionUID = 1L;

  public KontoImpl() throws RemoteException
  {
    super();
  }

  protected String getTableName()
  {
    return "konto";
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
      if (getBezeichnung() == null || getBezeichnung().length() == 0)
      {
        throw new ApplicationException("Bitte Bezeichnung eingeben");
      }
      if (getNummer() == null || getNummer().length() == 0)
      {
        throw new ApplicationException("Bitte Nummer eingeben");
      }
    }
    catch (RemoteException e)
    {
      Logger.error("insert check of konto failed", e);
      throw new ApplicationException(
          "Konto kann nicht gespeichert werden. Siehe system log");
    }
  }

  protected void updateCheck() throws ApplicationException
  {
    insertCheck();
  }

  @SuppressWarnings("unchecked")
  protected Class getForeignObject(String arg0) throws RemoteException
  {
    return null;
  }

  public String getNummer() throws RemoteException
  {
    return (String) getAttribute("nummer");
  }

  public void setNummer(String nummer) throws RemoteException
  {
    setAttribute("nummer", nummer);
  }

  public String getBezeichnung() throws RemoteException
  {
    return (String) getAttribute("bezeichnung");
  }

  public void setBezeichnung(String bezeichnung) throws RemoteException
  {
    setAttribute("bezeichnung", bezeichnung);
  }

  public Date getEroeffnung() throws RemoteException
  {
    return (Date) getAttribute("eroeffnung");
  }

  public void setEroeffnung(Date eroeffnungdatum) throws RemoteException
  {
    setAttribute("eroeffnung", eroeffnungdatum);
  }

  public Date getAufloesung() throws RemoteException
  {
    return (Date) getAttribute("aufloesung");
  }

  public void setAufloesung(Date aufloesungsdatum) throws RemoteException
  {
    setAttribute("aufloesung", aufloesungsdatum);
  }

  public Integer getHibiscusId() throws RemoteException
  {
    return (Integer) getAttribute("hibiscusid");
  }

  public void setHibiscusId(Integer id) throws RemoteException
  {
    setAttribute("hibiscusid", id);
  }

  public Object getAttribute(String fieldName) throws RemoteException
  {
    return super.getAttribute(fieldName);
  }

  public DBIterator getKontenEinesJahres(Geschaeftsjahr gj)
      throws RemoteException
  {
    DBIterator konten = Einstellungen.getDBService().createList(Konto.class);
    konten.addFilter("(eroeffnung is null or eroeffnung <= ?)",
        new Object[] { gj.getEndeGeschaeftsjahr() });
    konten.addFilter("(aufloesung is null or year(aufloesung) = ? or "
        + "aufloesung >= ? )", new Object[] { gj.getBeginnGeschaeftsjahrjahr(),
        gj.getEndeGeschaeftsjahr() });
    konten.setOrder("order by bezeichnung");
    return konten;
  }

}
