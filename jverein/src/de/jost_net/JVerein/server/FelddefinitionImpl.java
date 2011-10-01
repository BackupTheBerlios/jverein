/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/server/FelddefinitionImpl.java,v $
 * $Revision: 1.8 $
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

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.keys.Datentyp;
import de.jost_net.JVerein.rmi.Felddefinition;
import de.jost_net.JVerein.rmi.Mitglied;
import de.willuhn.datasource.db.AbstractDBObject;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

public class FelddefinitionImpl extends AbstractDBObject implements
    Felddefinition
{

  private static final long serialVersionUID = 1L;

  public FelddefinitionImpl() throws RemoteException
  {
    super();
  }

  @Override
  protected String getTableName()
  {
    return "felddefinition";
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
      if (getName() == null || getName().length() == 0)
      {
        throw new ApplicationException(JVereinPlugin.getI18n().tr(
            "Bitte Namen des Feldes eingeben"));
      }
      setName(getName().toLowerCase());
      String validChars = "abcdefghijklmnopqrstuvwxyz0123456789_";
      String testString = getName();
      for (int i = 0; i < testString.length(); i++)
      {
        char c = testString.charAt(i);
        if (validChars.indexOf(c) == -1)
          throw new ApplicationException(JVereinPlugin.getI18n().tr(
              "Ung�ltiges Zeichen ({0}) im Feldnamen an Position {1}",
              new String[] { c + "", i + "" }));
      }
      Mitglied m = (Mitglied) Einstellungen.getDBService().createObject(
          Mitglied.class, null);
      String[] namen = m.getAttributeNames();
      for (String s : namen)
      {
        if (getName().equals(s))
        {
          throw new ApplicationException(JVereinPlugin.getI18n().tr(
              "{0} ist ein reservierter Name und darf nicht verwendet werden.",
              getName()));
        }
      }
      if (getDatentyp() == Datentyp.ZEICHENFOLGE
          && (getLaenge() < 1 || getLaenge() > 1000))
      {
        throw new ApplicationException(JVereinPlugin.getI18n().tr(
            "Ung�ltige Feldl�nge"));
      }
    }
    catch (RemoteException e)
    {
      Logger.error("insert check of felddefinition failed", e);
      throw new ApplicationException(JVereinPlugin.getI18n().tr(
          "Felddefinition kann nicht gespeichert werden. Siehe system log"));
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

  public String getName() throws RemoteException
  {
    return (String) getAttribute("name");
  }

  public void setName(String name) throws RemoteException
  {
    setAttribute("name", name);
  }

  public String getLabel() throws RemoteException
  {
    return (String) getAttribute("label");
  }

  public void setLabel(String label) throws RemoteException
  {
    setAttribute("label", label);
  }

  public int getDatentyp() throws RemoteException
  {
    Integer i = (Integer) getAttribute("datentyp");
    if (i == null)
    {
      return 0;
    }
    else
    {
      return i;
    }
  }

  public void setDatentyp(int datentyp) throws RemoteException
  {
    setAttribute("datentyp", datentyp);
  }

  public int getLaenge() throws RemoteException
  {
    Integer i = (Integer) getAttribute("laenge");
    if (i == null)
    {
      return 0;
    }
    else
    {
      return i;
    }
  }

  public void setLaenge(int laenge) throws RemoteException
  {
    setAttribute("laenge", laenge);
  }

  @Override
  public Object getAttribute(String fieldName) throws RemoteException
  {
    return super.getAttribute(fieldName);
  }
}
