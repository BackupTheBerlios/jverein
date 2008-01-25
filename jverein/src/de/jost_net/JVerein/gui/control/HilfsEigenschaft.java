/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/control/Attic/HilfsEigenschaft.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/01/25 16:02:16 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: HilfsEigenschaft.java,v $
 * Revision 1.1  2008/01/25 16:02:16  jost
 * Neu: Eigenschaften des Mitgliedes
 *
 **********************************************************************/package de.jost_net.JVerein.gui.control;

import java.io.Serializable;
import java.rmi.RemoteException;

import de.willuhn.datasource.GenericObject;

public class HilfsEigenschaft implements Serializable, GenericObject
{
  private static final long serialVersionUID = -7674540336633201857L;

  private String eigenschaft;

  public HilfsEigenschaft(String eigenschaft)
  {
    this.eigenschaft = eigenschaft;
  }

  public boolean equals(GenericObject other) throws RemoteException
  {
    return other.getAttribute("eigenschaft").equals(eigenschaft);
  }

  public Object getAttribute(String name) throws RemoteException
  {
    if (name.equals("eigenschaft"))
    {
      return eigenschaft;
    }
    return null;
  }

  public String[] getAttributeNames() throws RemoteException
  {
    return new String[] { "eigenschaft" };
  }

  public String getID() throws RemoteException
  {
    return (eigenschaft);
  }

  public String getEigenschaft() throws RemoteException
  {
    return eigenschaft;
  }

  public String getPrimaryAttribute() throws RemoteException
  {
    return "eigenschaft";
  }
}
