/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/server/EigenschaftImpl.java,v $
 * $Revision: 1.1 $
 * $Date: 2009/11/17 21:03:50 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: EigenschaftImpl.java,v $
 * Revision 1.1  2009/11/17 21:03:50  jost
 * Neu: Eigenschaft und EigenschaftGruppe
 *
 **********************************************************************/
package de.jost_net.JVerein.server;

import java.rmi.RemoteException;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.rmi.Eigenschaft;
import de.jost_net.JVerein.rmi.EigenschaftGruppe;
import de.willuhn.datasource.db.AbstractDBObject;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

public class EigenschaftImpl extends AbstractDBObject implements Eigenschaft
{
  private static final long serialVersionUID = 1L;

  public EigenschaftImpl() throws RemoteException
  {
    super();
  }

  protected String getTableName()
  {
    return "eigenschaft";
  }

  public String getPrimaryAttribute() throws RemoteException
  {
    return "id";
  }

  protected void deleteCheck() throws ApplicationException
  {
    insertCheck();
  }

  protected void insertCheck() throws ApplicationException
  {
    try
    {
      plausi();
    }
    catch (RemoteException e)
    {
      Logger.error("insert check of eigenschaft failed", e);
      throw new ApplicationException(JVereinPlugin.getI18n().tr(
          "Eigenschaft kann nicht gespeichert werden. Siehe system log"));
    }
  }

  private void plausi() throws RemoteException, ApplicationException
  {
    if (getBezeichnung() == null)
    {
      throw new ApplicationException(JVereinPlugin.getI18n().tr(
          "Bitte Bezeichnung eingeben"));
    }
  }

  protected void updateCheck() throws ApplicationException
  {
    insertCheck();
  }

  @SuppressWarnings("unchecked")
  protected Class getForeignObject(String field) throws RemoteException
  {
    if ("eigenschaftgruppe".equals(field))
    {
      return EigenschaftGruppe.class;
    }
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

  public EigenschaftGruppe getEigenschaftGruppe() throws RemoteException
  {
    return (EigenschaftGruppe) getAttribute("eigenschaftgruppe");
  }

  public int getEigenschaftGruppeId() throws RemoteException
  {
    return Integer.parseInt(getEigenschaftGruppe().getID());
  }

  public void setEigenschaftGruppe(Integer eigenschaftgruppe)
      throws RemoteException
  {
    setAttribute("eigenschaftgruppe", eigenschaftgruppe);
  }

  public Object getAttribute(String fieldName) throws RemoteException
  {
    return super.getAttribute(fieldName);
  }

}