/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/dialogs/MitgliedskontoAuswahlDialog.java,v $
 * $Revision: 1.6 $
 * $Date: 2011/01/08 10:45:18 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe 
 * basiert auf dem KontoAuswahlDialog aus Hibiscus
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: MitgliedskontoAuswahlDialog.java,v $
 * Revision 1.6  2011/01/08 10:45:18  jost
 * Erzeugung Sollbuchung bei Zuordnung des Mitgliedskontos
 *
 * Revision 1.5  2010-10-15 09:58:26  jost
 * Code aufger�umt
 *
 * Revision 1.4  2010-09-12 19:59:25  jost
 * Mitgliedskontoauswahl kann r�ckg�ngig gemacht werden.
 *
 * Revision 1.3  2010-08-16 20:16:58  jost
 * Neu: Mahnung
 *
 * Revision 1.2  2010-08-08 11:32:29  jost
 * Nicht-Case-Sensitive-Suche
 *
 * Revision 1.1  2010-07-25 18:32:23  jost
 * Neu: Mitgliedskonto
 *
 **********************************************************************/

package de.jost_net.JVerein.gui.dialogs;

import org.eclipse.swt.SWT;
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
import de.willuhn.jameica.gui.parts.TablePart;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.gui.util.LabelGroup;
import de.willuhn.jameica.gui.util.TabGroup;
import de.willuhn.jameica.system.OperationCanceledException;

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

  public MitgliedskontoAuswahlDialog(int position, Buchung buchung)
  {
    super(position, true);
    settings = new de.willuhn.jameica.system.Settings(this.getClass());
    settings.setStoreWhenRead(true);

    super.setSize(600, 400);
    this.setTitle(JVereinPlugin.getI18n().tr("Mitgliedskonto-Auswahl"));
    this.buchung = buchung;
    // choosen = this.buchung.getMitgliedskonto();
    control = new MitgliedskontoControl(null);
  }

  @Override
  protected void paint(Composite parent) throws Exception
  {
    TabFolder folder = new TabFolder(parent, SWT.NONE);
    TabGroup tabNurIst = new TabGroup(folder, "nur Ist");
    LabelGroup group = new LabelGroup(tabNurIst.getComposite(), JVereinPlugin
        .getI18n().tr("Auswahl des Mitgliedskontos"));

    if (text == null || text.length() == 0)
    {
      text = JVereinPlugin.getI18n().tr(
          "Bitte w�hlen Sie das gew�nschte Mitgliedskonto aus.");
    }
    group.addText(text, true);
    control.getSuchName().setValue(buchung.getName());
    group.addInput(control.getSuchName());
    group.addInput(control.getDifferenz("Fehlbetrag"));

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
    tabNurIst.addPart(mitgliedskontolist);
    TabGroup tabSollIst = new TabGroup(folder, "Soll + Ist");
    LabelGroup group2 = new LabelGroup(tabSollIst.getComposite(), JVereinPlugin
        .getI18n().tr("Auswahl des Mitgliedskontos"));

    if (text == null || text.length() == 0)
    {
      text = JVereinPlugin.getI18n().tr(
          "Bitte w�hlen Sie das gew�nschte Mitgliedskonto aus.");
    }
    group2.addText(text, true);
    control.getSuchName2().setValue(buchung.getName());
    group2.addInput(control.getSuchName2());

    Action action2 = new Action()
    {

      public void handleAction(Object context)
      {
        if (context == null || !(context instanceof Mitglied))
        {
          return;
        }
        choosen =  context;
        close();
      }
    };
    mitgliedlist = control.getMitgliedskontoList2(action2, null);
    tabSollIst.addPart(mitgliedlist);

    ButtonArea b = new ButtonArea(parent, 4);
    b.addButton(i18n.tr(JVereinPlugin.getI18n().tr("�bernehmen")), new Action()
    {

      public void handleAction(Object context)
      {
        Object o = mitgliedskontolist.getSelection();
        if (o == null || !(o instanceof Mitgliedskonto))
        {
          return;
        }
        choosen = (Mitgliedskonto) o;
        close();
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
        throw new OperationCanceledException();
      }
    }, null, false, "process-stop.png");
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
