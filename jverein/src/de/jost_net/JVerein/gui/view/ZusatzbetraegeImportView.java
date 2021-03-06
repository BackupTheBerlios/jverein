/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/ZusatzbetraegeImportView.java,v $
 * $Revision: 1.7 $
 * $Date: 2011/10/01 21:46:34 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.dialogs.ImportDialog;
import de.jost_net.JVerein.io.IZusatzbetraegeImport;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.parts.Button;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

public class ZusatzbetraegeImportView extends AbstractView
{
  @Override
  public void bind() throws Exception
  {

    GUI.getView().setTitle(JVereinPlugin.getI18n().tr("Zusatzbeträge-Import"));

    ButtonArea buttons = new ButtonArea(this.getParent(), 2);
    buttons.addButton(JVereinPlugin.getI18n().tr("Hilfe"),
        new DokumentationAction(), DokumentationUtil.ZUSATZBETRAEGEIMPORT,
        false, "help-browser.png");
    Button button = new Button(JVereinPlugin.getI18n().tr("importieren"),
        new Action()
        {
          public void handleAction(Object context) throws ApplicationException
          {
            ImportDialog dialog = new ImportDialog(null,
                IZusatzbetraegeImport.class);
            try
            {
              dialog.open();
            }
            catch (Exception e)
            {
              Logger.error("Fehler beim Import", e);
              throw new ApplicationException("Fehler aufgetreten: "
                  + e.getMessage());
            }
          }
        }, null, true, "go.png");
    buttons.addButton(button);
  }

  @Override
  public String getHelp()
  {
    return "<form><p><span color=\"header\" font=\"header\">Zusatzbeträge importieren</span></p>"
        + "<p>In anderen Programmen erzeugte Zusatzbeträge können importiert werden. Der Aufbau der "
        + "Datei ist in der Hilfe beschrieben.</p></form>";
  }
}
