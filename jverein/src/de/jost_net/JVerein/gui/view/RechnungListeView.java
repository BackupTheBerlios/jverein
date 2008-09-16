/**********************************************************************
* $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/Attic/RechnungListeView.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/09/16 18:52:35 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: RechnungListeView.java,v $
 * Revision 1.1  2008/09/16 18:52:35  jost
 * Neu: Rechnung
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.gui.action.BackAction;
import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.control.RechnungControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.util.ApplicationException;

public class RechnungListeView extends AbstractView
{
  public void bind() throws Exception
  {
    GUI.getView().setTitle("Abrechnungen");

    final RechnungControl control = new RechnungControl(this);

    control.getAbrechungList().paint(this.getParent());

    ButtonArea buttons = new ButtonArea(this.getParent(), 3);
    buttons.addButton("<< Zurück", new BackAction());
    buttons.addButton("Drucken", new Action()
    {
      public void handleAction(Object context) throws ApplicationException
      {
        GUI.startView(RechnungView.class.getName(), null);
      }
    });
    buttons.addButton("Hilfe", new DokumentationAction(),
        DokumentationUtil.RECHNUNG);
  }

  public void unbind() throws ApplicationException
  {
  }

}
