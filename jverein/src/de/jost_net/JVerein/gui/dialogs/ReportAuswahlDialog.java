/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/dialogs/Attic/ReportAuswahlDialog.java,v $
 * $Revision: 1.1 $
 * $Date: 2009/03/26 20:59:48 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe 
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: ReportAuswahlDialog.java,v $
 * Revision 1.1  2009/03/26 20:59:48  jost
 * Neu: Reports - Erste Version
 *
 **********************************************************************/

package de.jost_net.JVerein.gui.dialogs;

import org.eclipse.swt.widgets.Composite;

import de.jost_net.JVerein.gui.control.ReportControl;
import de.jost_net.JVerein.keys.Reportart;
import de.jost_net.JVerein.rmi.Konto;
import de.jost_net.JVerein.rmi.Report;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.dialogs.AbstractDialog;
import de.willuhn.jameica.gui.parts.TablePart;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.gui.util.LabelGroup;
import de.willuhn.jameica.system.OperationCanceledException;
import de.willuhn.util.ApplicationException;

/**
 * Ein Dialog, �ber den man einen Report ausw�hlen kann.
 */
public class ReportAuswahlDialog extends AbstractDialog
{
  private Report choosen = null;

  private Reportart art;

  ReportControl control;

  public ReportAuswahlDialog(int position, Reportart art)
  {
    super(position);
    this.setTitle("Report-Auswahl");
    this.art = art;
    control = new ReportControl(null);
  }

  protected void paint(Composite parent) throws Exception
  {
    LabelGroup group = new LabelGroup(parent, "Report");

    group.addText("w�hlen Sie den Report aus", true);

    new Action()
    {
      public void handleAction(Object context) throws ApplicationException
      {
        if (context == null || !(context instanceof Konto))
        {
          return;
        }
        choosen = (Report) context;
        close();
      }
    };
    final TablePart reports = control.getReportList(art);
    reports.setContextMenu(null);
    reports.setMulti(false);
    reports.setSummary(false);
    reports.paint(parent);

    ButtonArea b = new ButtonArea(parent, 2);
    b.addButton("�bernehmen", new Action()
    {
      public void handleAction(Object context) throws ApplicationException
      {
        Object o = reports.getSelection();
        if (o == null || !(o instanceof Report))
        {
          return;
        }
        choosen = (Report) o;
        close();
      }
    });
    b.addButton("Abbrechen", new Action()
    {
      public void handleAction(Object context) throws ApplicationException
      {
        throw new OperationCanceledException();
      }
    });
  }

  /**
   * Liefert den ausgewaehlten Report zur�ck oder <code>null</code> wenn der
   * Abbrechen-Knopf gedrueckt wurde.
   */
  protected Object getData() throws Exception
  {
    return choosen;
  }

}
