/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/formatter/EigenschaftGruppeFormatter.java,v $
 * $Revision: 1.1 $
 * $Date: 2009/11/17 20:57:56 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: EigenschaftGruppeFormatter.java,v $
 * Revision 1.1  2009/11/17 20:57:56  jost
 * Neu: Eigenschaft und EigenschaftGruppe
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.formatter;

import java.rmi.RemoteException;

import de.jost_net.JVerein.rmi.EigenschaftGruppe;
import de.willuhn.jameica.gui.formatter.Formatter;

public class EigenschaftGruppeFormatter implements Formatter
{
  public String format(Object o)
  {
    EigenschaftGruppe eg = (EigenschaftGruppe) o;
    if (eg == null)
    {
      return null;
    }
    String bez = null;
    try
    {
      bez = eg.getBezeichnung();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return bez;
  }
}
