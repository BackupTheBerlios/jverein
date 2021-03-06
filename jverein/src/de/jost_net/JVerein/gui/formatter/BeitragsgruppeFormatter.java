/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/formatter/BeitragsgruppeFormatter.java,v $
 * $Revision: 1.2 $
 * $Date: 2011/10/01 21:43:53 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/
package de.jost_net.JVerein.gui.formatter;

import java.rmi.RemoteException;

import de.jost_net.JVerein.rmi.Beitragsgruppe;
import de.willuhn.jameica.gui.formatter.Formatter;

public class BeitragsgruppeFormatter implements Formatter
{
  public String format(Object o)
  {
    Beitragsgruppe beitragsgruppe = (Beitragsgruppe) o;
    try
    {
      return beitragsgruppe.getBezeichnung();
    }
    catch (RemoteException e)
    {
      return e.getMessage();
    }
  }
}
