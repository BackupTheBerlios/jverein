/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/AdressenSucheView.java,v $
 * $Revision: 1.1 $
 * $Date: 2011/01/27 22:21:53 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: AdressenSucheView.java,v $
 * Revision 1.1  2011/01/27 22:21:53  jost
 * Neu: Speicherung von weiteren Adressen in der Mitgliedertabelle
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import java.rmi.RemoteException;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.AdresseDetailAction;
import de.jost_net.JVerein.gui.control.MitgliedControl;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.util.LabelGroup;

public class AdressenSucheView extends AbstractPersonenSucheView
{

  public String getTitle()
  {
    return "Adressen suchen";
  }

  public void getFilter(MitgliedControl control) throws RemoteException
  {
    LabelGroup group = new LabelGroup(getParent(), JVereinPlugin.getI18n().tr(
        "Filter"));
    group.addLabelPair("Adresstyp", control.getSuchAdresstyp());
  }

  public int getAdresstyp()
  {
    return 2;
  }

  public Action getDetailAction()
  {
    return new AdresseDetailAction();
  }

}