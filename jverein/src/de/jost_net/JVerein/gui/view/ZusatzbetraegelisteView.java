/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/ZusatzbetraegelisteView.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/12/22 21:18:27 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: ZusatzbetraegelisteView.java,v $
 * Revision 1.1  2008/12/22 21:18:27  jost
 * Zusatzabbuchung->Zusatzbetrag
 *
 * Revision 1.6  2008/05/24 14:04:08  jost
 * Redatkionelle Änderung
 *
 * Revision 1.5  2008/01/01 19:53:45  jost
 * Erweiterung um Hilfe-Funktion
 *
 * Revision 1.4  2007/02/23 20:27:42  jost
 * Mail- und Webadresse im Header korrigiert.
 *
 * Revision 1.3  2006/12/20 20:25:44  jost
 * Patch von Ullrich Sch�fer, der die Primitive vs. Object Problematik adressiert.
 *
 * Revision 1.2  2006/10/07 14:47:25  jost
 * Alten Code entfernt
 *
 * Revision 1.1  2006/09/20 15:39:10  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.gui.action.BackAction;
import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.control.ZusatzbetragControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.gui.util.LabelGroup;
import de.willuhn.util.ApplicationException;

public class ZusatzbetraegelisteView extends AbstractView
{
  public void bind() throws Exception
  {
    GUI.getView().setTitle("Liste der Zusatzbetr�ge");

    final ZusatzbetragControl control = new ZusatzbetragControl(this);

    LabelGroup group = new LabelGroup(getParent(), "Ausf�hrungstag");
    group.addLabelPair("Ausf�hrungstag", control.getAusfuehrungSuch());

    ButtonArea buttons = new ButtonArea(this.getParent(), 2);
    buttons.addButton("<< Zur�ck", new BackAction());
    buttons.addButton("Hilfe", new DokumentationAction(),
        DokumentationUtil.ZUSATZABBUCHUNGEN);
    control.getZusatzbetraegeList().paint(this.getParent());
  }

  public void unbind() throws ApplicationException
  {
  }

}