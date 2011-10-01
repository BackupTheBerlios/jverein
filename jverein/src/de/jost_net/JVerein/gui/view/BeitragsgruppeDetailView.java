/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/BeitragsgruppeDetailView.java,v $
 * $Revision: 1.21 $
 * $Date: 2011/10/01 21:45:50 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import java.rmi.RemoteException;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.BeitragsgruppeSucheAction;
import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.control.BeitragsgruppeControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.dialogs.AbstractDialog;
import de.willuhn.jameica.gui.dialogs.YesNoDialog;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.gui.util.LabelGroup;
import de.willuhn.util.ApplicationException;

public class BeitragsgruppeDetailView extends AbstractView
{

  final BeitragsgruppeControl control = new BeitragsgruppeControl(this);

  @Override
  public void bind() throws Exception
  {
    GUI.getView().setTitle(JVereinPlugin.getI18n().tr("Beitragsgruppe"));

    LabelGroup group = new LabelGroup(getParent(), JVereinPlugin.getI18n().tr(
        "Beitrag"));
    group.addLabelPair(JVereinPlugin.getI18n().tr("Bezeichnung"),
        control.getBezeichnung(true));
    group.addLabelPair(JVereinPlugin.getI18n().tr("Betrag"),
        control.getBetrag());
    group.addLabelPair(JVereinPlugin.getI18n().tr("Beitragsart"),
        control.getBeitragsArt());
    group.addLabelPair(JVereinPlugin.getI18n().tr("Buchungsart"),
        control.getBuchungsart());

    if (Einstellungen.getEinstellung().getArbeitseinsatz())
    {
      LabelGroup groupAe = new LabelGroup(getParent(), JVereinPlugin.getI18n()
          .tr("Arbeitseinsatz"));
      groupAe.addLabelPair(JVereinPlugin.getI18n().tr("Stunden"),
          control.getArbeitseinsatzStunden());
      groupAe.addLabelPair(JVereinPlugin.getI18n().tr("Betrag"),
          control.getArbeitseinsatzBetrag());
    }

    ButtonArea buttons = new ButtonArea(getParent(), 3);
    buttons.addButton(JVereinPlugin.getI18n().tr("Hilfe"),
        new DokumentationAction(), DokumentationUtil.BEITRAGSGRUPPEN, false,
        "help-browser.png");
    buttons.addButton(JVereinPlugin.getI18n().tr("suche"),
        new BeitragsgruppeSucheAction(), null, false, "system-search.png");
    buttons.addButton(JVereinPlugin.getI18n().tr("speichern"), new Action()
    {

      public void handleAction(Object context)
      {
        control.handleStore();
      }
    }, null, true, "document-save.png");
  }

  @Override
  public String getHelp()
  {
    return "<form><p><span color=\"header\" font=\"header\">Beitragsgruppe</span></p>"
        + "<p>Die Bezeichung und die H�he des Beitrages sind zu erfassen.</p>"
        + "<p>Durch die Kennzeichnung als Familientarif k�nnen dem Mitglied die Mitglieder "
        + "zugeordnet, die zur Familie geh�ren.</p>" + "</form>";
  }

  public void unbind() throws ApplicationException
  {
    try
    {
      if (control.getBezeichnung(true).hasChanged()
          || control.getBetrag().hasChanged())
      {
        YesNoDialog dialog = new YesNoDialog(AbstractDialog.POSITION_CENTER);
        dialog.setText("Soll die �nderung gespeichert werden?");
        try
        {
          Boolean yesno = (Boolean) dialog.open();
          if (yesno)
          {
            throw new ApplicationException("�nderungen bitte speichern.");
          }
        }
        catch (Exception e)
        {
          throw new ApplicationException(e);
        }
      }
    }
    catch (RemoteException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
