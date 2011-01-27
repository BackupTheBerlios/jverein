/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/MitgliedDetailView.java,v $
 * $Revision: 1.55 $
 * $Date: 2011/01/27 22:22:46 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: MitgliedDetailView.java,v $
 * Revision 1.55  2011/01/27 22:22:46  jost
 * Neu: Speicherung von weiteren Adressen in der Mitgliedertabelle
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

public class MitgliedDetailView extends AbstractAdresseDetailView
{

  public String getTitle()
  {
    return "Daten des Mitgliedes";
  }

  public boolean isMitgliedDetail()
  {
    return true;
  }

}
