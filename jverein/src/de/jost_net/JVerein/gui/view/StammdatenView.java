/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/Attic/StammdatenView.java,v $
 * $Revision: 1.8 $
 * $Date: 2009/01/20 19:15:19 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: StammdatenView.java,v $
 * Revision 1.8  2009/01/20 19:15:19  jost
 * neu: Back-Button mit Icon
 *
 * Revision 1.7  2008/09/21 08:45:58  jost
 * Neu: Altersjubliäen
 *
 * Revision 1.6  2008/05/24 14:04:08  jost
 * Redatkionelle Änderung
 *
 * Revision 1.5  2008/01/01 19:52:59  jost
 * Erweiterung um Hilfe-Funktion
 *
 * Revision 1.4  2007/12/22 08:26:35  jost
 * Neu: Jubiläenliste
 *
 * Revision 1.3  2007/02/23 20:27:42  jost
 * Mail- und Webadresse im Header korrigiert.
 *
 * Revision 1.2  2006/10/29 07:49:29  jost
 * Neu: Mitgliederstatistik
 *
 * Revision 1.1  2006/09/20 15:39:10  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.control.StammdatenControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.internal.buttons.Back;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.gui.util.LabelGroup;
import de.willuhn.util.ApplicationException;

public class StammdatenView extends AbstractView
{
  public void bind() throws Exception
  {
    GUI.getView().setTitle("Stammdaten");

    final StammdatenControl control = new StammdatenControl(this);

    LabelGroup group = new LabelGroup(getParent(), "Stammdaten");
    group.addLabelPair("Name", control.getName());
    group.addLabelPair("Bankleitzahl", control.getBlz());
    group.addLabelPair("Konto", control.getKonto());
    group.addLabelPair("Altersgruppen", control.getAltersgruppen());
    group.addLabelPair("Jubil�en", control.getJubilaeen());
    group.addLabelPair("Altersjubil�en", control.getAltersjubilaeen());

    ButtonArea buttons = new ButtonArea(getParent(), 3);
    buttons.addButton(new Back(false));
    buttons.addButton("Hilfe", new DokumentationAction(),
        DokumentationUtil.STAMMDATEN);
    buttons.addButton("Speichern", new Action()
    {
      public void handleAction(Object context) throws ApplicationException
      {
        control.handleStore();
      }
    }, null, true);
  }

  public void unbind() throws ApplicationException
  {
  }
}
