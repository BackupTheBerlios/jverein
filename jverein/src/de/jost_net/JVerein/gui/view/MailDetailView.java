/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/MailDetailView.java,v $
 * $Revision: 1.1 $
 * $Date: 2010/02/01 21:00:49 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: MailDetailView.java,v $
 * Revision 1.1  2010/02/01 21:00:49  jost
 * Neu: Einfache Mailfunktion
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.control.MailControl;
import de.jost_net.JVerein.gui.dialogs.MailEmpfaengerAuswahlDialog;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.internal.buttons.Back;
import de.willuhn.jameica.gui.parts.Button;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.gui.util.Color;
import de.willuhn.util.ApplicationException;

public class MailDetailView extends AbstractView
{
  public void bind() throws Exception
  {
    GUI.getView().setTitle(JVereinPlugin.getI18n().tr("JVerein-Mail"));

    final MailControl control = new MailControl(this);

    Composite comp = new Composite(this.getParent(), SWT.NONE);
    comp.setLayoutData(new GridData(GridData.FILL_BOTH));

    GridLayout layout = new GridLayout(2, false);
    comp.setLayout(layout);

    addLabel("Empf�nger", comp, GridData.VERTICAL_ALIGN_BEGINNING);
    Composite comp2 = new Composite(comp, SWT.NONE);
    GridData gd2 = new GridData(GridData.FILL_HORIZONTAL);
    gd2.heightHint = 100;
    comp2.setLayoutData(gd2);
    GridLayout gl2 = new GridLayout();
    gl2.marginWidth = 0;
    comp2.setLayout(gl2);
    control.getEmpfaenger().paint(comp2);

    Composite comp3 = new Composite(comp, SWT.NONE);
    GridData gd3 = new GridData(GridData.HORIZONTAL_ALIGN_END);
    gd3.horizontalSpan = 2;
    comp3.setLayoutData(gd3);
    GridLayout gl3 = new GridLayout();
    gl3.marginWidth = 0;
    comp3.setLayout(gl3);
    Button add = new Button("Hinzuf�gen", new Action()
    {
      public void handleAction(Object context) throws ApplicationException
      {
        MailEmpfaengerAuswahlDialog mead = new MailEmpfaengerAuswahlDialog(
            control, MailEmpfaengerAuswahlDialog.POSITION_CENTER);
        try
        {
          mead.open();
        }
        catch (Exception e)
        {
          throw new ApplicationException(e.getMessage());
        }
      }
    });
    add.paint(comp3);

    addLabel("Betreff", comp, GridData.VERTICAL_ALIGN_CENTER);
    control.getBetreff().paint(comp);
    addLabel("Text", comp, GridData.VERTICAL_ALIGN_BEGINNING);
    control.getTxt().paint(comp);

    ButtonArea buttons = new ButtonArea(this.getParent(), 4);
    buttons.addButton(new Back(false));
    buttons.addButton(JVereinPlugin.getI18n().tr("Hilfe"),
        new DokumentationAction(), DokumentationUtil.MAIL, false,
        "help-browser.png");
    buttons.addButton(control.getMailSpeichernButton());
    buttons.addButton(control.getMailSendButton());
  }

  private void addLabel(String name, Composite parent, int align)
  {
    Text text = new Text(parent, SWT.NONE);
    text.setBackground(Color.BACKGROUND.getSWTColor());
    text.setText(name);
    text.setLayoutData(new GridData(align));
  }

  //
  // LabelGroup group = new LabelGroup(getParent(), JVereinPlugin.getI18n().tr(
  // "Mail"));
  // ScrolledContainer sc1 = new ScrolledContainer(group.getComposite());
  // control.getEmpfaenger().paint(sc1.getComposite());
  //
  // group.addPart(control.getEmpfaenger());
  // group.addInput(control.getBetreff());
  // group.addInput(control.getTxt());
  //
  // ButtonArea buttons = new ButtonArea(getParent(), 3);
  // buttons.addButton(new Back(false));
  // buttons.addButton(JVereinPlugin.getI18n().tr("Hilfe"),
  // new DokumentationAction(), DokumentationUtil.MAIL, false,
  // "help-browser.png");
  //
  // buttons.addButton(control.getMailSendButton());
  // }

  public void unbind() throws ApplicationException
  {
  }
}