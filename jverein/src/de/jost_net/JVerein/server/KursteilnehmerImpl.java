/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/server/KursteilnehmerImpl.java,v $
 * $Revision: 1.6 $
 * $Date: 2010/10/15 09:58:28 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: KursteilnehmerImpl.java,v $
 * Revision 1.6  2010/10/15 09:58:28  jost
 * Code aufger�umt
 *
 * Revision 1.5  2009-06-20 12:33:53  jost
 * Vereinheitlichung der Bezeichner
 *
 * Revision 1.4  2009/06/11 21:04:23  jost
 * Vorbereitung I18N
 *
 * Revision 1.3  2008/11/29 13:16:35  jost
 * Refactoring: Warnungen beseitigt.
 *
 * Revision 1.2  2007/03/21 12:10:43  jost
 * Neu: Abbuchungsdatum beim Kursteilnehmer kann zurückgesetzt werden.
 *
 * Revision 1.1  2007/02/25 19:14:53  jost
 * Neu: Kursteilnehmer
 *
 **********************************************************************/
package de.jost_net.JVerein.server;

import java.rmi.RemoteException;
import java.util.Date;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.rmi.Kursteilnehmer;
import de.willuhn.datasource.db.AbstractDBObject;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

public class KursteilnehmerImpl extends AbstractDBObject implements
    Kursteilnehmer
{

  private static final long serialVersionUID = 1L;

  public KursteilnehmerImpl() throws RemoteException
  {
    super();
  }

  @Override
  protected String getTableName()
  {
    return "kursteilnehmer";
  }

  @Override
  public String getPrimaryAttribute()
  {
    return "id";
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
      plausi();
    }
    catch (RemoteException e)
    {
      String fehler = JVereinPlugin.getI18n().tr(
          "Kursteilnehmer kann nicht gespeichert werden. Siehe system log");
      Logger.error(fehler, e);
      throw new ApplicationException(fehler);
    }
  }

  private void plausi() throws RemoteException, ApplicationException
  {
    if (getName() == null || getName().length() == 0)
    {
      throw new ApplicationException(JVereinPlugin.getI18n().tr(
          "Bitte Namen eingeben"));
    }
    if (getVZweck1() == null || getVZweck1().length() == 0)
    {
      throw new ApplicationException(JVereinPlugin.getI18n().tr(
          "Bitte Verwendungszweck 1 eingeben"));
    }
    if (getGeburtsdatum() == null)
    {
      throw new ApplicationException(JVereinPlugin.getI18n().tr(
          "Bitte Geburtsdatum eingeben"));
    }
    if (getBlz() == null || getBlz().length() != 8)
    {
      throw new ApplicationException(JVereinPlugin.getI18n().tr(
          "Bitte Bankleitzahl eingeben"));
    }
    if (getKonto() == null || getKonto().length() == 0)
    {
      throw new ApplicationException(JVereinPlugin.getI18n().tr(
          "Bitte Konto eingeben"));
    }
    if (getBetrag() <= 0)
    {
      throw new ApplicationException(JVereinPlugin.getI18n().tr(
          "Bitte Betrag gr��er als 0 eingeben"));
    }
    if (!Einstellungen.checkAccountCRC(getBlz(), getKonto()))
    {
      throw new ApplicationException(JVereinPlugin.getI18n().tr(
          "Ung�ltige BLZ/Kontonummer. Bitte pr�fen Sie Ihre Eingaben."));
    }
  }

  @Override
  protected void updateCheck() throws ApplicationException
  {
    try
    {
      plausi();
    }
    catch (RemoteException e)
    {
      String fehler = JVereinPlugin.getI18n().tr(
          "Kursteilnehmer kann nicht gespeichert werden. Siehe system log");
      Logger.error(fehler, e);
      throw new ApplicationException(fehler);
    }
  }

  @Override
  protected Class getForeignObject(String field)
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

  public void resetAbbudatum() throws RemoteException
  {
    setAttribute("abbudatum", null);
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

  @Override
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
