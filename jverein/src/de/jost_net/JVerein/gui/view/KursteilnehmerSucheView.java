/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/KursteilnehmerSucheView.java,v $
 * $Revision: 1.14 $
 * $Date: 2011/10/01 21:46:34 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import java.sql.ResultSet;
import java.sql.SQLException;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.action.KursteilnehmerDetailAction;
import de.jost_net.JVerein.gui.control.KursteilnehmerControl;
import de.willuhn.datasource.rmi.DBService;
import de.willuhn.datasource.rmi.ResultSetExtractor;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.gui.util.LabelGroup;

public class KursteilnehmerSucheView extends AbstractView
{

  @Override
  public void bind() throws Exception
  {
    GUI.getView().setTitle(JVereinPlugin.getI18n().tr("Suche Kursteilnehmer"));

    KursteilnehmerControl control = new KursteilnehmerControl(this);

    String sql = "select count(*) from kursteilnehmer";
    DBService service = Einstellungen.getDBService();
    ResultSetExtractor rs = new ResultSetExtractor()
    {

      public Object extract(ResultSet rs) throws SQLException
      {
        rs.next();
        return Long.valueOf(rs.getLong(1));
      }
    };
    Long anzahl = (Long) service.execute(sql, new Object[] {}, rs);

    LabelGroup group = new LabelGroup(getParent(), JVereinPlugin.getI18n().tr(
        "Filter"));
    group.addLabelPair(JVereinPlugin.getI18n().tr("Name"),
        control.getSuchname());
    group.addLabelPair(JVereinPlugin.getI18n().tr("Eingabedatum von"),
        control.getEingabedatumvon());
    group.addLabelPair(JVereinPlugin.getI18n().tr("Eingabedatum bis"),
        control.getEingabedatumbis());

    if (anzahl.longValue() > 0)
    {
      control.getKursteilnehmerTable().paint(getParent());
    }
    ButtonArea buttons = new ButtonArea(this.getParent(), 2);
    buttons.addButton(JVereinPlugin.getI18n().tr("Hilfe"),
        new DokumentationAction(), DokumentationUtil.KURSTEILNEHMER, false,
        "help-browser.png");
    buttons.addButton(JVereinPlugin.getI18n().tr("neu"),
        new KursteilnehmerDetailAction(), null, false, "document-new.png");
  }

  @Override
  public String getHelp()
  {
    return "<form><p><span color=\"header\" font=\"header\">Kursteilnehmer</span></p>"
        + "<p>Kursteilnehmer sind Personen, die nicht dem Verein angeh�ren und an Kursen teilnehmen. "
        + "Die Kursgeb�hr kann abgebucht werden und die Personen werden durch die Statistik erfa�t.</p>"
        + "<p>Unter Name wird der Name des Kontoinhabers eingegeben. In den Verwendungszweck-Feldern "
        + "wird die Kursbezeichnung und ggfls. der Name des Teilnehmers (falls nicht identisch mit dem "
        + "Kontoinhaber) eingetragen.</p></form>";
  }
}
