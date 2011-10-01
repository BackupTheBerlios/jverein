/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/dialogs/MitgliedskontoAuswahlDialog.java,v $
 * $Revision: 1.14 $
 * $Date: 2011/10/01 21:43:35 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe 
 * basiert auf dem KontoAuswahlDialog aus Hibiscus
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/

package de.jost_net.JVerein.gui.dialogs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.control.MitgliedskontoControl;
import de.jost_net.JVerein.gui.view.DokumentationUtil;
import de.jost_net.JVerein.rmi.Buchung;
import de.jost_net.JVerein.rmi.Mitglied;
import de.jost_net.JVerein.rmi.Mitgliedskonto;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.dialogs.AbstractDialog;
import de.willuhn.jameica.gui.parts.ButtonArea;
import de.willuhn.jameica.gui.parts.TablePart;
import de.willuhn.jameica.gui.util.Container;
import de.willuhn.jameica.gui.util.SimpleContainer;
import de.willuhn.jameica.gui.util.TabGroup;

/**
 * Ein Dialog, ueber den man ein Mitgliedskonto auswaehlen kann.
 */
public class MitgliedskontoAuswahlDialog extends AbstractDialog
{

  private de.willuhn.jameica.system.Settings settings;

  private String text = null;

  private Object choosen = null;

  private MitgliedskontoControl control;

  private TablePart mitgliedskontolist = null;

  private TablePart mitgliedlist = null;

  private Buchung buchung;

  public MitgliedskontoAuswahlDialog(Buchung buchung)
  {
    super(MitgliedskontoAuswahlDialog.POSITION_MOUSE, true);
    settings = new de.willuhn.jameica.system.Settings(this.getClass());
    settings.setStoreWhenRead(true);

    this.setSize(600, 400);
    this.setTitle(JVereinPlugin.getI18n().tr("Mitgliedskonto-Auswahl"));
    this.buchung = buchung;
    control = new MitgliedskontoControl(null);
  }

  @Override
  protected void paint(Composite parent) throws Exception
  {
    TabFolder folder = new TabFolder(parent, SWT.NONE);
    folder.setLayoutData(new GridData(GridData.FILL_BOTH));

    TabGroup tabNurIst = new TabGroup(folder, "nur Ist", false, 1);
    Container grNurIst = new SimpleContainer(tabNurIst.getComposite());
    grNurIst.addHeadline(JVereinPlugin.getI18n().tr(
        "Auswahl des Mitgliedskontos"));
    if (text == null || text.length() == 0)
    {
      text = JVereinPlugin.getI18n().tr(
          "Bitte w�hlen Sie das gew�nschte Mitgliedskonto aus.");
    }
    grNurIst.addText(text, true);
    control.getSuchName().setValue(buchung.getName());
    grNurIst.addLabelPair("Name", control.getSuchName());
    grNurIst.addLabelPair("Differenz", control.getDifferenz("Fehlbetrag"));
    Action action = new Action()
    {

      public void handleAction(Object context)
      {
        if (context == null || !(context instanceof Mitgliedskonto))
        {
          return;
        }
        choosen = context;
        close();
      }
    };
    mitgliedskontolist = control.getMitgliedskontoList(action, null);
    mitgliedskontolist.paint(tabNurIst.getComposite());

    //
    TabGroup tabSollIst = new TabGroup(folder, "Soll u. Ist", true, 1);
    Container grSollIst = new SimpleContainer(tabSollIst.getComposite());
    grSollIst.addHeadline(JVereinPlugin.getI18n().tr(
        "Auswahl des Mitgliedskontos"));

    if (text == null || text.length() == 0)
    {
      text = JVereinPlugin.getI18n().tr(
          "Bitte w�hlen Sie das gew�nschte Mitgliedskonto aus.");
    }
    grSollIst.addText(text, true);
    control.getSuchName2(true).setValue(buchung.getName());
    grSollIst.addLabelPair("Name", control.getSuchName2(false));
    grSollIst.addInput(control.getSpezialSuche());

    final Action action2 = new Action()
    {
      public void handleAction(Object context)
      {
        if (context == null || !(context instanceof Mitglied))
        {
          return;
        }
        choosen = context;
        close();
      }
    };
    mitgliedlist = control.getMitgliedskontoList2(action2, null);
    mitgliedlist.paint(tabSollIst.getComposite());

    ButtonArea b = new ButtonArea();

    b.addButton(i18n.tr(JVereinPlugin.getI18n().tr("�bernehmen")), new Action()
    {
      public void handleAction(Object context)
      {
        Object o = mitgliedskontolist.getSelection();

        if (o instanceof Mitgliedskonto)
        {
          choosen = o;
          close();
        }
        else
        {
          o = mitgliedlist.getSelection();

          if (o instanceof Mitglied)
          {
            choosen = o;
            close();
          }
        }
        return;
      }
    }, null, true, "emblem-default.png");

    b.addButton(i18n.tr(JVereinPlugin.getI18n().tr("entfernen")), new Action()
    {

      public void handleAction(Object context)
      {
        choosen = null;
        close();
      }
    }, null, true, "edit-undo.png");
    b.addButton(JVereinPlugin.getI18n().tr("Hilfe"), new DokumentationAction(),
        DokumentationUtil.MITGLIEDSKONTO_AUSWAHL, false, "help-browser.png");

    b.addButton(i18n.tr("abbrechen"), new Action()
    {

      public void handleAction(Object context)
      {
        close();
      }
    }, null, false, "process-stop.png");
    b.paint(parent);
  }

  /**
   * Liefert das ausgewaehlte Mitgliedskonto zurueck oder <code>null</code> wenn
   * der Abbrechen-Knopf gedrueckt wurde.
   * 
   * @see de.willuhn.jameica.gui.dialogs.AbstractDialog#getData()
   */
  @Override
  protected Object getData() throws Exception
  {
    return choosen;
  }

  /**
   * Optionale Angabe des anzuzeigenden Textes. Wird hier kein Wert gesetzt,
   * wird ein Standard-Text angezeigt.
   * 
   * @param text
   */
  public void setText(String text)
  {
    this.text = text;
  }
}
