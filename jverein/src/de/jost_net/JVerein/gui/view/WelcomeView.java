/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/Attic/WelcomeView.java,v $
 * $Revision: 1.5 $
 * $Date: 2010/10/15 09:58:25 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: WelcomeView.java,v $
 * Revision 1.5  2010/10/15 09:58:25  jost
 * Code aufger�umt
 *
 * Revision 1.4  2010-10-07 19:49:23  jost
 * Hilfe in die View verlagert.
 *
 * Revision 1.3  2009/06/11 21:03:39  jost
 * Vorbereitung I18N
 *
 * Revision 1.2  2007/02/23 20:27:42  jost
 * Mail- und Webadresse im Header korrigiert.
 *
 * Revision 1.1  2006/09/20 15:39:10  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.jost_net.JVerein.JVereinPlugin;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.util.LabelGroup;

/**
 * JVerein-Begr��ungsbildschirm.
 * 
 * @author heiner jostkleigrewe
 */
public class WelcomeView extends AbstractView
{

  @Override
  public void bind() throws Exception
  {
    GUI.getView().setTitle(
        JVereinPlugin.getI18n().tr("Vereinsverwaltung unter Jamaica"));
    LabelGroup group = new LabelGroup(this.getParent(),
        JVereinPlugin.getI18n().tr("welcome"));
    group.addText(JVereinPlugin.getI18n().tr("Diese Seite ist leer"), false);
  }

  // TODO getHelp()

}
