/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/Attic/TermineView.java,v $
 * $Revision: 1.1 $
 * $Date: 2010/11/25 15:11:52 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: TermineView.java,v $
 * Revision 1.1  2010/11/25 15:11:52  jost
 * Initial Commit
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import java.util.Date;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.parts.TerminePart;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.internal.buttons.Back;
import de.willuhn.jameica.gui.parts.ButtonArea;
import de.willuhn.util.I18N;

/**
 * Zeigt einen Terminkalender an.
 */
public class TermineView extends AbstractView
{

  private final static I18N i18n = JVereinPlugin.getI18n();

  private static Date currentDate = null;

  private TerminePart termine = null;

  /**
   * @see de.willuhn.jameica.gui.AbstractView#bind()
   */
  @Override
  public void bind() throws Exception
  {

    GUI.getView().setTitle(i18n.tr("Termine"));

    this.termine = new TerminePart();
    this.termine.setCurrentDate(currentDate);
    this.termine.paint(getParent());

    ButtonArea buttons = new ButtonArea();
    buttons.addButton(new Back(false));
    buttons.paint(getParent());
  }

  /**
   * @see de.willuhn.jameica.gui.AbstractView#unbind()
   */
  @Override
  public void unbind()
  {
    currentDate = this.termine.getCurrentDate();
  }
}
