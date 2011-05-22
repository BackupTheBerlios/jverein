/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/server/ZusatzbetragImpl.java,v $
 * $Revision: 1.8 $
 * $Date: 2011/05/22 08:36:12 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: ZusatzbetragImpl.java,v $
 * Revision 1.8  2011/05/22 08:36:12  jost
 * Neu: Buchungstext2 f�r Zusatzbetr�ge
 *
 * Revision 1.7  2011-02-12 09:43:37  jost
 * Statische Codeanalyse mit Findbugs
 *
 * Revision 1.6  2010-11-25 19:34:04  jost
 * Bugfix bei Zusatzbetr�gen ohne Intervall
 *
 * Revision 1.5  2010-11-13 09:31:24  jost
 * Warnings entfernt.
 *
 * Revision 1.4  2010-10-15 09:58:27  jost
 * Code aufger�umt
 *
 * Revision 1.3  2009-06-11 21:04:23  jost
 * Vorbereitung I18N
 *
 * Revision 1.2  2009/02/12 22:17:26  jost
 * Vermeidung NPE
 *
 * Revision 1.1  2008/12/22 21:23:10  jost
 * Zusatzabbuchung->Zusatzbetrag
 *
 * Revision 1.7  2008/12/19 12:25:52  jost
 * Bugfix Fälligkeitsberechung
 *
 * Revision 1.6  2008/11/29 13:17:37  jost
 * Refactoring: Warnungen beseitigt.
 *
 * Revision 1.5  2008/09/22 20:26:03  jost
 * Plausi korrigiert
 *
 * Revision 1.4  2007/03/30 13:26:12  jost
 * Wiederkehrende Zusatzabbuchungen.
 *
 * Revision 1.3  2007/02/23 20:28:42  jost
 * Mail- und Webadresse im Header korrigiert.
 *
 * Revision 1.2  2006/12/20 20:25:44  jost
 * Patch von Ullrich Sch�fer, der die Primitive vs. Object Problematik adressiert.
 *
 * Revision 1.1  2006/09/20 15:39:48  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.JVerein.server;

import java.rmi.RemoteException;
import java.util.Date;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.keys.IntervallZusatzzahlung;
import de.jost_net.JVerein.rmi.Mitglied;
import de.jost_net.JVerein.rmi.Zusatzbetrag;
import de.jost_net.JVerein.util.Datum;
import de.willuhn.datasource.db.AbstractDBObject;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

public class ZusatzbetragImpl extends AbstractDBObject implements Zusatzbetrag
{
  private static final long serialVersionUID = 1L;

  public ZusatzbetragImpl() throws RemoteException
  {
    super();
  }

  @Override
  protected String getTableName()
  {
    return "zusatzabbuchung";
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
      if (getStartdatum() == null)
      {
        throw new ApplicationException(JVereinPlugin.getI18n().tr(
            "Bitte Startdatum eingeben"));
      }
      if (getFaelligkeit() == null)
      {
        throw new ApplicationException(JVereinPlugin.getI18n().tr(
            "Bitte n�chste F�lligkeit eingeben"));
      }
      if (getIntervall() == null)
      {
        throw new ApplicationException(JVereinPlugin.getI18n().tr(
            "Bitte Intervall eingeben"));
      }
      if (getBuchungstext() == null || getBuchungstext().length() == 0)
      {
        throw new ApplicationException(JVereinPlugin.getI18n().tr(
            "Bitte Buchungstext eingeben"));
      }
      if (getEndedatum() != null)
      {
        if (!Datum
            .isImInterval(getStartdatum(), getEndedatum(), getIntervall()))
        {
          throw new ApplicationException(JVereinPlugin.getI18n().tr(
              "Endedatum liegt nicht im Intervall"));
        }
      }
      if (getFaelligkeit().getTime() < getStartdatum().getTime())
      {
        throw new ApplicationException(JVereinPlugin.getI18n().tr(
            "Das F�lligkeitsdatum darf nicht vor dem Startdatum liegen"));
      }
      if (!Datum
          .isImInterval(getStartdatum(), getFaelligkeit(), getIntervall()))
      {
        throw new ApplicationException(JVereinPlugin.getI18n().tr(
            "N�chste F�lligkeit liegt nicht im Intervall"));
      }
      if (getBetrag() <= 0)
      {
        throw new ApplicationException(JVereinPlugin.getI18n().tr(
            "Betrag nicht g�ltig"));
      }
    }
    catch (RemoteException e)
    {
      String fehler = JVereinPlugin.getI18n().tr(
          "Zusatzbetrag kann nicht gespeichert werden. Siehe system log");
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
    if ("mitglied".equals(arg0))
    {
      return Mitglied.class;
    }
    return null;
  }

  public Mitglied getMitglied() throws RemoteException
  {
    return (Mitglied) getAttribute("mitglied");
  }

  public void setMitglied(int mitglied) throws RemoteException
  {
    setAttribute("mitglied", Integer.valueOf(mitglied));
  }

  public Date getFaelligkeit() throws RemoteException
  {
    return (Date) getAttribute("faelligkeit");
  }

  public void setFaelligkeit(Date faelligkeit) throws RemoteException
  {
    setAttribute("faelligkeit", faelligkeit);
  }

  public String getBuchungstext() throws RemoteException
  {
    return (String) getAttribute("buchungstext");
  }

  public void setBuchungstext(String buchungstext) throws RemoteException
  {
    setAttribute("buchungstext", buchungstext);
  }

  public String getBuchungstext2() throws RemoteException
  {
    return (String) getAttribute("buchungstext2");
  }

  public void setBuchungstext2(String buchungstext2) throws RemoteException
  {
    setAttribute("buchungstext2", buchungstext2);
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

  public Date getAusfuehrung() throws RemoteException
  {
    return (Date) getAttribute("ausfuehrung");
  }

  public Date getStartdatum() throws RemoteException
  {
    return (Date) getAttribute("startdatum");
  }

  public void setStartdatum(Date value) throws RemoteException
  {
    setAttribute("startdatum", value);
  }

  public Integer getIntervall() throws RemoteException
  {
    return (Integer) getAttribute("intervall");
  }

  public String getIntervallText() throws RemoteException
  {
    return IntervallZusatzzahlung.get(getIntervall());
  }

  public void setIntervall(Integer value) throws RemoteException
  {
    setAttribute("intervall", value);
  }

  public Date getEndedatum() throws RemoteException
  {
    return (Date) getAttribute("endedatum");
  }

  public void setEndedatum(Date value) throws RemoteException
  {
    setAttribute("endedatum", value);
  }

  public void setAusfuehrung(Date ausfuehrung) throws RemoteException
  {
    setAttribute("ausfuehrung", ausfuehrung);
  }

  @Override
  public Object getAttribute(String fieldName) throws RemoteException
  {
    if (fieldName.equals("intervalltext"))
    {
      return getIntervallText();
    }
    if (fieldName.equals("aktiv"))
    {
      return isAktiv();
    }
    return super.getAttribute(fieldName);
  }

  public boolean isAktiv() throws RemoteException
  {
    // Wenn der Auftrag noch nie ausgef�hrt wurde, ist er auszuf�hren
    // if (getAusfuehrung() == null)
    // {
    // return true;
    // }
    // Einmalige Ausf�hrung
    if (getIntervall().intValue() == IntervallZusatzzahlung.KEIN)
    {
      // Ist das Ausf�hrungsdatum gesetzt?
      if (getAusfuehrung() == null)
      {
        if (getFaelligkeit().getTime() <= Datum.getHeute().getTime())
        {
          return true;
        }
        else
        {
          return false;
        }
      }
      else
      {
        // ja: nicht mehr ausf�hren
        return false;
      }
    }

    // Wenn das Endedatum gesetzt ist und das Ausf�hrungsdatum liegt hinter
    // dem Endedatum: nicht mehr ausf�hren
    if (getEndedatum() != null && getAusfuehrung() != null
        && getAusfuehrung().getTime() >= getEndedatum().getTime())
    {
      return false;
    }
    if (getFaelligkeit().getTime() <= Datum.getHeute().getTime())
    {
      return true;
    }

    return false;
  }
}
