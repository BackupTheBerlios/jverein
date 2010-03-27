/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/server/MailAnhangImpl.java,v $
 * $Revision: 1.1 $
 * $Date: 2010/02/15 17:24:31 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: MailAnhangImpl.java,v $
 * Revision 1.1  2010/02/15 17:24:31  jost
 * Mail-Anhang implementiert
 *
 **********************************************************************/
package de.jost_net.JVerein.server;

import java.rmi.RemoteException;

import de.jost_net.JVerein.rmi.Mail;
import de.jost_net.JVerein.rmi.MailAnhang;
import de.willuhn.datasource.db.AbstractDBObject;
import de.willuhn.util.ApplicationException;

public class MailAnhangImpl extends AbstractDBObject implements MailAnhang,
    Comparable<MailAnhang>
{
  private static final long serialVersionUID = 1L;

  public MailAnhangImpl() throws RemoteException
  {
    super();
  }

  protected String getTableName()
  {
    return "mailanhang";
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
    // try
    // {
    // if (getBetreff() == null || getBetreff().length() == 0)
    // {
    // throw new ApplicationException(JVereinPlugin.getI18n().tr(
    // "Bitte Betreff eingeben"));
    // }
    // }
    // catch (RemoteException e)
    // {
    // Logger.error("insert check of mailvorlage failed", e);
    // throw new ApplicationException(JVereinPlugin.getI18n().tr(
    // "MailVorlage kann nicht gespeichert werden. Siehe system log"));
    // }
  }

  protected void updateCheck() throws ApplicationException
  {
    insertCheck();
  }

  @SuppressWarnings("unchecked")
  protected Class getForeignObject(String arg0) throws RemoteException
  {
    if ("mail".equals(arg0))
    {
      return Mail.class;
    }
    return null;
  }

  public Mail getMail() throws RemoteException
  {
    return (Mail) getAttribute("mail");
  }

  public void setMail(Mail mail) throws RemoteException
  {
    setAttribute("mail", mail);
  }

  public String getDateiname() throws RemoteException
  {
    return (String) getAttribute("dateiname");
  }

  public void setDateiname(String dateiname) throws RemoteException
  {
    setAttribute("dateiname", dateiname);
  }

  public byte[] getAnhang() throws RemoteException
  {
    return (byte[]) this.getAttribute("anhang");
  }

  public void setAnhang(byte[] anhang) throws RemoteException
  {
    setAttribute("anhang", anhang);
  }

  public Object getAttribute(String fieldName) throws RemoteException
  {
    return super.getAttribute(fieldName);
  }

  public int compareTo(MailAnhang o)
  {
    try
    {
      return getDateiname().compareTo(o.getDateiname());
    }
    catch (RemoteException e)
    {
      //
    }
    return 0;
  }

}