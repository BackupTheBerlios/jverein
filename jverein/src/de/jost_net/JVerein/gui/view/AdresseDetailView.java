/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/AdresseDetailView.java,v $
 * $Revision: 1.1 $
 * $Date: 2011/01/27 22:21:53 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: AdresseDetailView.java,v $
 * Revision 1.1  2011/01/27 22:21:53  jost
 * Neu: Speicherung von weiteren Adressen in der Mitgliedertabelle
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

public class AdresseDetailView extends AbstractAdresseDetailView
{

  public String getTitle()
  {
    return "Adressdaten";
  }

  public boolean isMitgliedDetail()
  {
    return false;
  }
}
