/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/server/FelddefinitionImpl.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/04/10 19:03:24 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: FelddefinitionImpl.java,v $
 * Revision 1.1  2008/04/10 19:03:24  jost
 * Neu: Benutzerdefinierte Datenfelder
 *
 **********************************************************************/
package de.jost_net.JVerein.server;

import java.rmi.RemoteException;

import de.jost_net.JVerein.Einstellungen;
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

  protected String getTableName()
  {
    return "felddefinition";
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
      if (getName() == null || getName().length() == 0)
      {
        throw new ApplicationException("Bitte Namen des Feldes eingeben");
      }
      setName(getName().toLowerCase());
      String validChars = "abcdefghijklmnopqrstuvwxyz01234567890_";
      String testString = getName();
      for (int i = 0; i < testString.length(); i++)
      {
        char c = testString.charAt(i);
        if (validChars.indexOf(c) == -1)
          throw new ApplicationException("Ung�ltiges Zeichen (" + c
              + ") im Feldnamen an Position " + i);
      }
      Mitglied m = (Mitglied) Einstellungen.getDBService().createObject(
          Mitglied.class, null);
      String[] namen = m.getAttributeNames();
      for (String s : namen)
      {
        if (getName().equals(s))
        {
          throw new ApplicationException(getName()
              + " ist ein reservierter Name und darf nicht verwendet werden.");
        }
      }
      if (getLaenge() < 1 || getLaenge() > 1000)
      {
        throw new ApplicationException("Ung�ltige Feldl�nge");
      }
    }
    catch (RemoteException e)
    {
      Logger.error("insert check of felddefinition failed", e);
      throw new ApplicationException(
          "Felddefinition kann nicht gespeichert werden. Siehe system log");
    }
  }

  protected void updateCheck() throws ApplicationException
  {
    insertCheck();
  }

  protected Class getForeignObject(String arg0) throws RemoteException
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

  public Object getAttribute(String fieldName) throws RemoteException
  {
    return super.getAttribute(fieldName);
  }
}