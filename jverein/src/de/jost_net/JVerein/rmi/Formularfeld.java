/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/rmi/Formularfeld.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/07/18 20:16:32 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: Formularfeld.java,v $
 * Revision 1.1  2008/07/18 20:16:32  jost
 * Neu: Formulare
 *
 **********************************************************************/
package de.jost_net.JVerein.rmi;

import java.rmi.RemoteException;

import de.willuhn.datasource.rmi.DBObject;

public interface Formularfeld extends DBObject
{
  public Formular getFormular() throws RemoteException;

  public void setFormular(Formular formular) throws RemoteException;

  public String getName() throws RemoteException;

  public void setName(String name) throws RemoteException;

  public Double getX() throws RemoteException;

  public void setX(Double x) throws RemoteException;

  public Double getY() throws RemoteException;

  public void setY(Double y) throws RemoteException;

  public String getFont() throws RemoteException;

  public void setFont(String font) throws RemoteException;

  public Integer getFontsize() throws RemoteException;

  public void setFontsize(Integer fontsize) throws RemoteException;

  public Integer getFontstyle() throws RemoteException;

  public void setFontstyle(Integer fontstyle) throws RemoteException;
}
