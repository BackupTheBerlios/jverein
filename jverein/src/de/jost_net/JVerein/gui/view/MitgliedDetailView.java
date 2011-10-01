/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/MitgliedDetailView.java,v $
 * $Revision: 1.56 $
 * $Date: 2011/10/01 21:46:33 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
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
