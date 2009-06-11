/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/server/FormularImpl.java,v $
 * $Revision: 1.3 $
 * $Date: 2009/06/11 21:04:24 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: FormularImpl.java,v $
 * Revision 1.3  2009/06/11 21:04:24  jost
 * Vorbereitung I18N
 *
 * Revision 1.2  2008/11/29 13:15:55  jost
 * Refactoring: Warnungen beseitigt.
 *
 * Revision 1.1  2008/07/18 20:18:12  jost
 * Neu: Formulare
 *
 **********************************************************************/
package de.jost_net.JVerein.server;

import java.rmi.RemoteException;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.rmi.Formular;
import de.willuhn.datasource.db.AbstractDBObject;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

public class FormularImpl extends AbstractDBObject implements Formular
{
  private static final long serialVersionUID = 1603994510932244220L;

  public FormularImpl() throws RemoteException
  {
    super();
  }

  protected String getTableName()
  {
    return "formular";
  }

  public String getPrimaryAttribute() throws RemoteException
  {
    return "bezeichnung";
  }

  protected void deleteCheck() throws ApplicationException
  {
  }

  protected void insertCheck() throws ApplicationException
  {
    try
    {
      if (getInhalt() == null)
      {
        throw new ApplicationException(JVereinPlugin.getI18n().tr(
            "Bitte g�ltigen Dateinamen angeben!"));
      }
    }
    catch (RemoteException e)
    {
      Logger.error("Fehler", e);
    }
    updateCheck();
  }

  protected void updateCheck() throws ApplicationException
  {
    try
    {
      if (getBezeichnung() == null || getBezeichnung().length() == 0)
      {
        throw new ApplicationException(JVereinPlugin.getI18n().tr(
            "Bitte Bezeichnung eingeben"));
      }
    }
    catch (RemoteException e)
    {
      String fehler = JVereinPlugin.getI18n().tr(
          "Formularfeld kann nicht gespeichert werden. Siehe system log");
      Logger.error(fehler, e);
      throw new ApplicationException(fehler);
    }
  }

  @SuppressWarnings("unchecked")
  protected Class getForeignObject(String arg0) throws RemoteException
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

  public byte[] getInhalt() throws RemoteException
  {
    return (byte[]) this.getAttribute("inhalt");
  }

  public void setInhalt(byte[] inhalt) throws RemoteException
  {
    setAttribute("inhalt", inhalt);
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

  public Object getAttribute(String fieldName) throws RemoteException
  {
    return super.getAttribute(fieldName);
  }

}
