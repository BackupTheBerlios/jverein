/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/BuchungsklasseSaldoView.java,v $
 * $Revision: 1.9 $
 * $Date: 2011/04/07 19:30:12 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: BuchungsklasseSaldoView.java,v $
 * Revision 1.9  2011/04/07 19:30:12  jost
 * Neue Zur�ckbutton-Mimik aus Jameica
 *
 * Revision 1.8  2011-01-15 09:46:48  jost
 * Tastatursteuerung wegen Problemen mit Jameica/Hibiscus wieder entfernt.
 *
 * Revision 1.7  2010-10-15 09:58:24  jost
 * Code aufger�umt
 *
 * Revision 1.6  2010-10-07 19:49:23  jost
 * Hilfe in die View verlagert.
 *
 * Revision 1.5  2010-08-23 13:39:31  jost
 * Optimierung Tastatursteuerung
 *
 * Revision 1.4  2010/02/23 21:16:02  jost
 * Individueller Zeitraum
 *
 * Revision 1.3  2009/09/16 21:36:35  jost
 * Tabelle nur so gro� wie n�tig
 *
 * Revision 1.2  2009/09/14 19:13:58  jost
 * �berfl�ssiges Import-Statement entfernt.
 *
 * Revision 1.1  2009/09/10 18:18:32  jost
 * neu: Buchungsklassen
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.control.BuchungsklasseSaldoControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.parts.Button;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.gui.util.LabelGroup;
import de.willuhn.util.ApplicationException;

public class BuchungsklasseSaldoView extends AbstractView
{

  @Override
  public void bind() throws Exception
  {
    GUI.getView().setTitle(JVereinPlugin.getI18n().tr("Buchungsklassen-Saldo"));

    final BuchungsklasseSaldoControl control = new BuchungsklasseSaldoControl(
        this);

    LabelGroup group = new LabelGroup(getParent(), JVereinPlugin.getI18n().tr(
        "Zeitraum"));
    group
        .addLabelPair(JVereinPlugin.getI18n().tr("von"), control.getDatumvon());
    group
        .addLabelPair(JVereinPlugin.getI18n().tr("bis"), control.getDatumbis());

    ButtonArea buttons = new ButtonArea(this.getParent(), 1);
    Button button = new Button(JVereinPlugin.getI18n().tr("suchen"),
        new Action()
        {

          public void handleAction(Object context) throws ApplicationException
          {
            control.getSaldoList();
          }
        }, null, true, "system-search.png");
    buttons.addButton(button);

    LabelGroup group2 = new LabelGroup(getParent(), JVereinPlugin.getI18n().tr(
        "Saldo"), true);
    group2.addPart(control.getSaldoList());

    ButtonArea buttons2 = new ButtonArea(this.getParent(), 2);
    buttons2.addButton(JVereinPlugin.getI18n().tr("Hilfe"),
        new DokumentationAction(), DokumentationUtil.JAHRESSALDO, false,
        "help-browser.png");
    buttons2.addButton(control.getStartAuswertungButton());
  }

  @Override
  public String getHelp()
  {
    return "<form><p><span color=\"header\" font=\"header\">Buchungsklassen-Saldo</span></p>"
        + "</form>";
  }
}
