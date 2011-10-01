/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/formatter/BuchungsartFormatter.java,v $
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

import de.jost_net.JVerein.rmi.Buchungsart;
import de.willuhn.jameica.gui.formatter.Formatter;

public class BuchungsartFormatter implements Formatter
{
  public String format(Object o)
  {
    Buchungsart ba = (Buchungsart) o;
    if (ba == null)
    {
      return null;
    }
    String bez = null;
    try
    {
      bez = ba.getBezeichnung();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    return bez;
  }
}
