/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/Attic/WelcomeView.java,v $
 * $Revision: 1.1 $
 * $Date: 2006/09/20 15:39:10 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * jost@berlios.de
 * jverein.berlios.de
 * $Log: WelcomeView.java,v $
 * Revision 1.1  2006/09/20 15:39:10  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.util.LabelGroup;
import de.willuhn.util.ApplicationException;

/**
 * JVerein-Begrüßungsbildschirm.
 * 
 * @author heiner jostkleigrewe
 */
public class WelcomeView extends AbstractView
{
  public void bind() throws Exception
  {
    GUI.getView().setTitle("Vereinsverwaltung unter Jamaica");
    LabelGroup group = new LabelGroup(this.getParent(), "welcome");
    group.addText("Diese Seite ist leer", false);
  }

  public void unbind() throws ApplicationException
  {
  }
}
