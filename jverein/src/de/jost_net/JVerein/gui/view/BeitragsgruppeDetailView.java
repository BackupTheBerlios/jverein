/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/BeitragsgruppeDetailView.java,v $
 * $Revision: 1.10 $
 * $Date: 2009/07/24 20:20:30 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: BeitragsgruppeDetailView.java,v $
 * Revision 1.10  2009/07/24 20:20:30  jost
 * Focus auf erstes Feld setzen.
 *
 * Revision 1.9  2009/06/11 21:03:39  jost
 * Vorbereitung I18N
 *
 * Revision 1.8  2009/01/20 20:09:24  jost
 * neue Icons
 *
 * Revision 1.7  2009/01/20 19:14:45  jost
 * neu: Back-Button mit Icon
 *
 * Revision 1.6  2008/05/24 14:04:08  jost
 * Redatkionelle Änderung
 *
 * Revision 1.5  2008/01/01 19:48:33  jost
 * Erweiterung um Hilfe-Funktion
 *
 * Revision 1.4  2007/03/25 17:00:12  jost
 * Beitragsart aufgenommen.
 *
 * Revision 1.3  2007/02/23 20:27:28  jost
 * Mail- und Webadresse im Header korrigiert.
 *
 * Revision 1.2  2006/10/29 07:49:11  jost
 * Redaktionelle �nderung
 *
 * Revision 1.1  2006/09/20 15:39:10  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.BeitragsgruppeSucheAction;
import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.control.BeitragsgruppeControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.internal.buttons.Back;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.gui.util.LabelGroup;
import de.willuhn.util.ApplicationException;

public class BeitragsgruppeDetailView extends AbstractView
{
  public void bind() throws Exception
  {
    GUI.getView().setTitle(JVereinPlugin.getI18n().tr("Beitragsgruppe"));

    final BeitragsgruppeControl control = new BeitragsgruppeControl(this);

    LabelGroup group = new LabelGroup(getParent(), JVereinPlugin.getI18n().tr(
        "Beitrag"));
    group.addLabelPair(JVereinPlugin.getI18n().tr("Bezeichnung"), control
        .getBezeichnung(true));
    group.addLabelPair(JVereinPlugin.getI18n().tr("Betrag"), control
        .getBetrag());
    group.addLabelPair(JVereinPlugin.getI18n().tr("Beitragsart"), control
        .getBeitragsArt());

    ButtonArea buttons = new ButtonArea(getParent(), 4);
    buttons.addButton(new Back(false));
    buttons.addButton(JVereinPlugin.getI18n().tr("Hilfe"),
        new DokumentationAction(), DokumentationUtil.BEITRAGSGRUPPEN, false,
        "help-browser.png");
    buttons.addButton(JVereinPlugin.getI18n().tr("suche"),
        new BeitragsgruppeSucheAction());
    buttons.addButton(JVereinPlugin.getI18n().tr("speichern"), new Action()
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
