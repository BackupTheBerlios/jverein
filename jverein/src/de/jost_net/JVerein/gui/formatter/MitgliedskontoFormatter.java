/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/formatter/MitgliedskontoFormatter.java,v $
 * $Revision: 1.1 $
 * $Date: 2010/07/25 18:32:34 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: MitgliedskontoFormatter.java,v $
 * Revision 1.1  2010/07/25 18:32:34  jost
 * Neu: Mitgliedskonto
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.formatter;

import java.rmi.RemoteException;

import de.jost_net.JVerein.rmi.Mitgliedskonto;
import de.willuhn.jameica.gui.formatter.Formatter;

public class MitgliedskontoFormatter implements Formatter
{
  public String format(Object o)
  {
    if (o instanceof Mitgliedskonto)
    {
      Mitgliedskonto mk = (Mitgliedskonto) o;
      try
      {
        return mk.getMitglied().getNameVorname();
      }
      catch (RemoteException e)
      {
        //
      }
    }
    return "";
  }
}
