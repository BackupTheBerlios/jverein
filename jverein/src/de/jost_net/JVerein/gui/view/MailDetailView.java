/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/MailDetailView.java,v $
 * $Revision: 1.11 $
 * $Date: 2011/10/01 21:46:34 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import java.io.File;
import java.io.FileInputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Text;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.control.MailControl;
import de.jost_net.JVerein.gui.dialogs.MailEmpfaengerAuswahlDialog;
import de.jost_net.JVerein.rmi.MailAnhang;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.parts.Button;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.gui.util.Color;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

public class MailDetailView extends AbstractView
{

  @Override
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

    addLabel("Anhang", comp, GridData.VERTICAL_ALIGN_BEGINNING);
    Composite comp4 = new Composite(comp, SWT.NONE);
    GridData gd4 = new GridData(GridData.FILL_HORIZONTAL);
    gd4.heightHint = 90;
    comp4.setLayoutData(gd4);
    GridLayout gl4 = new GridLayout();
    gl4.marginWidth = 0;
    comp4.setLayout(gl4);
    control.getAnhang().paint(comp4);

    Composite comp5 = new Composite(comp, SWT.NONE);
    GridData gd5 = new GridData(GridData.HORIZONTAL_ALIGN_END);
    gd5.horizontalSpan = 2;
    comp5.setLayoutData(gd3);
    GridLayout gl5 = new GridLayout();
    gl5.marginWidth = 0;
    comp5.setLayout(gl5);
    Button addAttachment = new Button("    Anlage    ", new Action()
    {

      public void handleAction(Object context) throws ApplicationException
      {
        FileDialog fd = new FileDialog(GUI.getShell(), SWT.OPEN);
        fd.setFilterPath(System.getProperty("user.home"));
        fd.setText("Bitte w�hlen Sie einen Anhang aus.");
        String f = fd.open();
        if (f != null)
        {
          try
          {
            MailAnhang anh = (MailAnhang) Einstellungen.getDBService()
                .createObject(MailAnhang.class, null);
            anh.setDateiname(f.substring(f.lastIndexOf(System
                .getProperty("file.separator")) + 1));
            File file = new File(f);
            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()];
            fis.read(buffer);
            anh.setAnhang(buffer);
            control.addAnhang(anh);
            fis.close();
          }
          catch (Exception e)
          {
            Logger.error("", e);
            throw new ApplicationException(e);
          }
        }
      }
    });
    addAttachment.paint(comp5);

    ButtonArea buttons = new ButtonArea(this.getParent(), 3);
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

  @Override
  public String getHelp()
  {
    return "<form><p><span color=\"header\" font=\"header\">Mails</span></p>"
        + "<p><b>TIPP! </b>Mit einem Rechtsklick auf eine Mail-Adresse k�nnen alle Variable angezeigt werden.</p>"
        + "</form>";
  }

}
