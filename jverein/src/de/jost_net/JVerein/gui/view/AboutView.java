/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/AboutView.java,v $
 * $Revision: 1.4 $
 * $Date: 2008/12/27 15:18:30 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: AboutView.java,v $
 * Revision 1.4  2008/12/27 15:18:30  jost
 * Zusätzliche Infos werden ausgegeben.
 *
 * Revision 1.3  2007/12/01 17:46:22  jost
 * Redaktionelle Änderungen und Aufnahme der Datenbankversion
 *
 * Revision 1.2  2007/02/23 20:27:28  jost
 * Mail- und Webadresse im Header korrigiert.
 *
 * Revision 1.1  2006/09/20 15:39:10  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import org.eclipse.swt.widgets.Composite;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.rmi.Version;
import de.willuhn.jameica.gui.dialogs.AbstractDialog;
import de.willuhn.jameica.gui.input.LabelInput;
import de.willuhn.jameica.gui.parts.FormTextPart;
import de.willuhn.jameica.gui.util.LabelGroup;
import de.willuhn.jameica.plugin.AbstractPlugin;
import de.willuhn.jameica.system.Application;

public class AboutView extends AbstractDialog
{

  public AboutView(int position)
  {
    super(position);
    this.setTitle("�ber...");
  }

  protected void paint(Composite parent) throws Exception
  {
    FormTextPart text = new FormTextPart();
    text.setText("<form>"
        + "<p><b>Plugin f�r die Vereinsverwaltung unter Jameica</b></p>"
        + "<br/>Licence: GPL (http://www.gnu.org/copyleft/gpl.html)"
        + "<br/><p>Copyright by Heiner Jostkleigrewe [heiner@jverein.de]</p>"
        + "<p>http://www.jverein.de</p>" + "</form>");

    text.paint(parent);

    LabelGroup group = new LabelGroup(parent, " Information ");

    AbstractPlugin p = Application.getPluginLoader().getPlugin(
        JVereinPlugin.class);

    group.addLabelPair("Version", new LabelInput(""
        + p.getManifest().getVersion()));

    group.addLabelPair("Build-Date", new LabelInput(""
        + p.getManifest().getBuildDate()));

    group.addLabelPair("Build-Nr", new LabelInput(""
        + p.getManifest().getBuildnumber()));

    Version v = (Version) Einstellungen.getDBService().createObject(
        Version.class, "1");
    group
        .addLabelPair("Datenbank-Version", new LabelInput("" + v.getVersion()));
    group.addLabelPair("Arbeitsverzeichnis", new LabelInput(""
        + p.getResources().getWorkPath()));

  }

  protected Object getData() throws Exception
  {
    return null;
  }
}
