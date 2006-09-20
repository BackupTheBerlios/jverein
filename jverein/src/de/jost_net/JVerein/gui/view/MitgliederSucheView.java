/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/MitgliederSucheView.java,v $
 * $Revision: 1.1 $
 * $Date: 2006/09/20 15:39:10 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * jost@berlios.de
 * jverein.berlios.de
 * $Log: MitgliederSucheView.java,v $
 * Revision 1.1  2006/09/20 15:39:10  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.TabFolder;

import de.jost_net.JVerein.gui.action.BackAction;
import de.jost_net.JVerein.gui.action.MitgliedDetailAction;
import de.jost_net.JVerein.gui.control.MitgliedControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.parts.TablePart;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.gui.util.Color;
import de.willuhn.jameica.gui.util.TabGroup;
import de.willuhn.util.ApplicationException;

public class MitgliederSucheView extends AbstractView
{

  public void bind() throws Exception
  {
    GUI.getView().setTitle("Suche Mitglied");

    MitgliedControl control = new MitgliedControl(this);

    TabFolder folder = new TabFolder(getParent(), SWT.NONE);
    folder.setLayoutData(new GridData(GridData.FILL_BOTH));
    folder.setBackground(Color.BACKGROUND.getSWTColor());

    TabGroup[] tab = new TabGroup[26];
    TablePart[] p = new TablePart[26];
    for (int i = 'A'; i <= 'Z'; i++)
    {
      char[] zeichen = { (char) i };
      tab[i - 'A'] = new TabGroup(folder, new String(zeichen));
      control.getMitgliedTable(p[i - 'A'], new String(zeichen)).paint(
          tab[i - 'A'].getComposite());
    }
    TabGroup tab1 = new TabGroup(folder, "�");
    TablePart p1 = null;
    control.getMitgliedTable(p1, "�").paint(tab1.getComposite());

    TabGroup tab2 = new TabGroup(folder, "�");
    TablePart p2 = null;
    control.getMitgliedTable(p2, "�").paint(tab2.getComposite());

    TabGroup tab3 = new TabGroup(folder, "�");
    TablePart p3 = null;
    control.getMitgliedTable(p3, "�").paint(tab3.getComposite());

    ButtonArea buttons = new ButtonArea(this.getParent(), 2);
    buttons.addButton("Neu", new MitgliedDetailAction());
    buttons.addButton("<< Zur�ck", new BackAction());
  }

  public void unbind() throws ApplicationException
  {
  }
}
