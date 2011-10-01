/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/io/BLZUpdate.java,v $
 * $Revision: 1.2 $
 * $Date: 2011/10/01 21:46:58 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/
package de.jost_net.JVerein.io;

import java.rmi.RemoteException;

import de.jost_net.JVerein.rmi.Mitglied;
import de.willuhn.datasource.GenericObject;

public class BLZUpdate implements GenericObject
{
  private String id;

  private Mitglied mitglied;

  private String newBLZ;

  public BLZUpdate(Mitglied mitglied, String newBLZ) throws RemoteException
  {
    this.id = mitglied.getID();
    this.mitglied = mitglied;
    this.newBLZ = newBLZ;
  }

  public boolean equals(GenericObject arg0) throws RemoteException
  {
    return id.equals(arg0.getID());
  }

  public Object getAttribute(String arg0) throws RemoteException
  {
    if (arg0.equals("id"))
    {
      return id;
    }
    else if (arg0.equals("mitglied"))
    {
      return mitglied;
    }
    else if (arg0.equals("namevorname"))
    {
      return mitglied.getNameVorname();
    }
    else if (arg0.equals("oldblz"))
    {
      return mitglied.getBlz();
    }
    else if (arg0.equals("newblz"))
    {
      return newBLZ;
    }
    return null;
  }

  public String[] getAttributeNames() throws RemoteException
  {
    return new String[] { "id", "mitglied", "namevorname", "newblz", "oldblz" };
  }

  public String getID() throws RemoteException
  {
    return id;
  }

  public String getPrimaryAttribute() throws RemoteException
  {
    return mitglied.getNameVorname();
  }

  public String toString()
  {
    return newBLZ;
  }

}
