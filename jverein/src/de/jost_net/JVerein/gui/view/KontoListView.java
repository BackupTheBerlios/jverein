/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/KontoListView.java,v $
 * $Revision: 1.3 $
 * $Date: 2009/01/20 19:15:19 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: KontoListView.java,v $
 * Revision 1.3  2009/01/20 19:15:19  jost
 * neu: Back-Button mit Icon
 *
 * Revision 1.2  2008/05/24 14:04:08  jost
 * Redatkionelle Änderung
 *
 * Revision 1.1  2008/05/22 06:53:57  jost
 * Buchführung: Beginn des Geschäftsjahres
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.action.HibiscusKontenImportAction;
import de.jost_net.JVerein.gui.action.KontoAction;
import de.jost_net.JVerein.gui.control.KontoControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.internal.buttons.Back;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.util.ApplicationException;

public class KontoListView extends AbstractView
{
  public void bind() throws Exception
  {
    GUI.getView().setTitle("Konten");

    KontoControl control = new KontoControl(this);

    control.getKontenList().paint(this.getParent());

    ButtonArea buttons = new ButtonArea(this.getParent(), 4);
    buttons.addButton(new Back(false));
    buttons.addButton("Hilfe", new DokumentationAction(),
        DokumentationUtil.KONTEN);

    buttons.addButton("Hibiscus-Import",
        new HibiscusKontenImportAction(control));
    buttons.addButton("neu", new KontoAction(), null);
  }

  public void unbind() throws ApplicationException
  {
  }
}
