/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/AdressenSucheView.java,v $
 * $Revision: 1.2 $
 * $Date: 2011/01/29 19:30:52 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: AdressenSucheView.java,v $
 * Revision 1.2  2011/01/29 19:30:52  jost
 * Feinschliff
 *
 * Revision 1.1  2011-01-27 22:21:53  jost
 * Neu: Speicherung von weiteren Adressen in der Mitgliedertabelle
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import java.rmi.RemoteException;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.AdresseDetailAction;
import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.rmi.Adresstyp;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.input.Input;
import de.willuhn.jameica.gui.parts.Button;
import de.willuhn.jameica.gui.util.LabelGroup;

public class AdressenSucheView extends AbstractAdresseSucheView
{
  public AdressenSucheView() throws RemoteException
  {
    control.getSuchAdresstyp(2).getValue();
    Adresstyp at = (Adresstyp) Einstellungen.getDBService().createObject(
        Adresstyp.class, "2");
    control.getSuchAdresstyp(2).setValue(at);
  }

  public String getTitle()
  {
    return "Adressen suchen";
  }

  public void getFilter() throws RemoteException
  {
    LabelGroup group = new LabelGroup(getParent(), JVereinPlugin.getI18n().tr(
        "Filter"));
    Input adrtyp = control.getSuchAdresstyp(2);
    Adresstyp at = (Adresstyp) Einstellungen.getDBService().createObject(
        Adresstyp.class, "2");
    control.getSuchAdresstyp(2).setValue(at);
    adrtyp.addListener(new FilterListener());
    group.addLabelPair("Adresstyp", adrtyp);
  }

  public Action getDetailAction()
  {
    return new AdresseDetailAction();
  }

  public Button getHilfeButton()
  {
    return new Button(JVereinPlugin.getI18n().tr("Hilfe"),
        new DokumentationAction(), DokumentationUtil.ADRESSEN, false,
        "help-browser.png");
  }
}