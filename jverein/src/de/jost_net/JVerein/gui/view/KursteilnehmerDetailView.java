/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/KursteilnehmerDetailView.java,v $
 * $Revision: 1.5 $
 * $Date: 2009/01/20 20:09:24 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: KursteilnehmerDetailView.java,v $
 * Revision 1.5  2009/01/20 20:09:24  jost
 * neue Icons
 *
 * Revision 1.4  2009/01/20 19:15:19  jost
 * neu: Back-Button mit Icon
 *
 * Revision 1.3  2008/05/24 14:04:08  jost
 * Redatkionelle Änderung
 *
 * Revision 1.2  2008/01/01 19:51:47  jost
 * Erweiterung um Hilfe-Funktion
 *
 * Revision 1.1  2007/02/25 19:13:34  jost
 * Neu: Kursteilnehmer
 *
 * Revision 1.1  2007/02/23 20:27:28  jost
 * Mail- und Webadresse im Header korrigiert.
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.action.KursteilnehmerDeleteAction;
import de.jost_net.JVerein.gui.action.KursteilnehmerDetailAction;
import de.jost_net.JVerein.gui.control.KursteilnehmerControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.internal.buttons.Back;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.gui.util.LabelGroup;
import de.willuhn.util.ApplicationException;

public class KursteilnehmerDetailView extends AbstractView
{
  public void bind() throws Exception
  {
    GUI.getView().setTitle("Kursteilnehmer");

    final KursteilnehmerControl control = new KursteilnehmerControl(this);

    LabelGroup grGrund = new LabelGroup(getParent(), "Daten f�r die Abbuchung");
    grGrund.getComposite().setSize(290, 190);
    grGrund.addLabelPair("Name", control.getName());
    grGrund.addLabelPair("Verwendungszweck 1", control.getVZweck1());
    grGrund.addLabelPair("Verwendungszweck 2", control.getVZweck2());
    grGrund.addLabelPair("BLZ", control.getBlz());
    grGrund.addLabelPair("Konto", control.getKonto());
    grGrund.addLabelPair("Betrag", control.getBetrag());

    LabelGroup grStatistik = new LabelGroup(getParent(), "Statistik");
    grStatistik.getComposite().setSize(290, 190);
    grStatistik.addLabelPair("Geburtsdatum", control.getGeburtsdatum());
    grStatistik.addLabelPair("Geschlecht", control.getGeschlecht());

    ButtonArea buttons = new ButtonArea(getParent(), 5);

    buttons.addButton(new Back(false));
    buttons.addButton("Hilfe", new DokumentationAction(),
        DokumentationUtil.KURSTEILNEHMER, false, "help-browser.png");
    buttons.addButton("Neu", new KursteilnehmerDetailAction(), null, false,
        "document-new.png");
    buttons.addButton("L�schen", new KursteilnehmerDeleteAction(), control
        .getCurrentObject(), false, "user-trash.png");
    buttons.addButton("Speichern", new Action()
    {
      public void handleAction(Object context) throws ApplicationException
      {
        control.handleStore();
      }
    }, null, true, "document-save.png");
  }

  public void unbind() throws ApplicationException
  {
  }

}
