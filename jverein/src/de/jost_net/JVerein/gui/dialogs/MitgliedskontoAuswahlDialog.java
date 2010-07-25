/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/dialogs/MitgliedskontoAuswahlDialog.java,v $
 * $Revision: 1.1 $
 * $Date: 2010/07/25 18:32:23 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe 
 * basiert auf dem KontoAuswahlDialog aus Hibiscus
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: MitgliedskontoAuswahlDialog.java,v $
 * Revision 1.1  2010/07/25 18:32:23  jost
 * Neu: Mitgliedskonto
 *
 **********************************************************************/

package de.jost_net.JVerein.gui.dialogs;

import java.rmi.RemoteException;

import org.eclipse.swt.widgets.Composite;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.DokumentationAction;
import de.jost_net.JVerein.gui.control.MitgliedskontoControl;
import de.jost_net.JVerein.gui.view.DokumentationUtil;
import de.jost_net.JVerein.rmi.Buchung;
import de.jost_net.JVerein.rmi.Mitgliedskonto;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.dialogs.AbstractDialog;
import de.willuhn.jameica.gui.parts.TablePart;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.gui.util.LabelGroup;
import de.willuhn.jameica.system.OperationCanceledException;
import de.willuhn.util.ApplicationException;

/**
 * Ein Dialog, ueber den man ein Mitgliedskonto auswaehlen kann.
 */
public class MitgliedskontoAuswahlDialog extends AbstractDialog
{
  private de.willuhn.jameica.system.Settings settings;

  private String text = null;

  private Mitgliedskonto choosen = null;

  private MitgliedskontoControl control;

  private TablePart mitgliedskontolist = null;

  private Buchung buchung;

  public MitgliedskontoAuswahlDialog(int position, Buchung buchung)
      throws RemoteException
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

  protected void paint(Composite parent) throws Exception
  {
    LabelGroup group = new LabelGroup(parent, JVereinPlugin.getI18n().tr(
        "Auswahl des Mitgliedskontos"));

    if (text == null || text.length() == 0)
    {
      text = JVereinPlugin.getI18n().tr(
          "Bitte w�hlen Sie das gew�nschte Mitgliedskonto aus.");
    }
    group.addText(text, true);
    control.getSuchName().setValue(buchung.getName());
    group.addInput(control.getSuchName());
    group.addInput(control.getOffenePosten());

    Action action = new Action()
    {
      public void handleAction(Object context) throws ApplicationException
      {
        if (context == null || !(context instanceof Mitgliedskonto))
        {
          return;
        }
        choosen = (Mitgliedskonto) context;
        close();
      }
    };
    // final MitgliedskontoList konten = new
    // de.jost_net.JVerein.gui.parts.MitgliedskontoList(
    // a);
    mitgliedskontolist = control.getMitgliedskontoList(action, null);
    mitgliedskontolist.paint(parent);
    // konten.setContextMenu(null);
    // konten.setMulti(false);
    // konten.setSummary(false);
    // konten.paint(parent);

    ButtonArea b = new ButtonArea(parent, 3);
    b.addButton(i18n.tr(JVereinPlugin.getI18n().tr("�bernehmen")), new Action()
    {
      public void handleAction(Object context) throws ApplicationException
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
    b.addButton(JVereinPlugin.getI18n().tr("Hilfe"), new DokumentationAction(),
        DokumentationUtil.MITGLIEDSKONTO_AUSWAHL, false, "help-browser.png");

    b.addButton(i18n.tr("abbrechen"), new Action()
    {
      public void handleAction(Object context) throws ApplicationException
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
