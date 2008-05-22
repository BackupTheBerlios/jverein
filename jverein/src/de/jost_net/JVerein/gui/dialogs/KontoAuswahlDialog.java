/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/dialogs/KontoAuswahlDialog.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/05/22 06:49:47 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe 
 * basiert auf dem KontoAuswahlDialog aus Hibiscus
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: KontoAuswahlDialog.java,v $
 * Revision 1.1  2008/05/22 06:49:47  jost
 * Buchführung
 *
 **********************************************************************/

package de.jost_net.JVerein.gui.dialogs;

import org.eclipse.swt.widgets.Composite;

import de.jost_net.JVerein.gui.parts.KontoList;
import de.jost_net.JVerein.rmi.Konto;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.dialogs.AbstractDialog;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.gui.util.LabelGroup;
import de.willuhn.jameica.system.OperationCanceledException;
import de.willuhn.util.ApplicationException;

/**
 * Ein Dialog, ueber den man ein Konto auswaehlen kann.
 */
public class KontoAuswahlDialog extends AbstractDialog
{
  private String text = null;

  private Konto choosen = null;

  public KontoAuswahlDialog(int position)
  {
    super(position);
    this.setTitle("Konto-Auswahl");
  }

  protected void paint(Composite parent) throws Exception
  {
    LabelGroup group = new LabelGroup(parent, "Verf�gbare Konten");

    if (text == null || text.length() == 0)
    {
      text = "Bitte w�hlen Sie das gew�nschte Konto aus.";
    }
    group.addText(text, true);

    Action a = new Action()
    {
      public void handleAction(Object context) throws ApplicationException
      {
        if (context == null || !(context instanceof Konto))
        {
          return;
        }
        choosen = (Konto) context;
        close();
      }
    };
    final KontoList konten = new de.jost_net.JVerein.gui.parts.KontoList(a);
    konten.setContextMenu(null);
    konten.setMulti(false);
    konten.setSummary(false);
    konten.paint(parent);

    ButtonArea b = new ButtonArea(parent, 2);
    b.addButton(i18n.tr("�bernehmen"), new Action()
    {
      public void handleAction(Object context) throws ApplicationException
      {
        Object o = konten.getSelection();
        if (o == null || !(o instanceof Konto))
          return;

        choosen = (Konto) o;
        close();
      }
    });
    b.addButton(i18n.tr("Abbrechen"), new Action()
    {
      public void handleAction(Object context) throws ApplicationException
      {
        throw new OperationCanceledException();
      }
    });
  }

  /**
   * Liefert das ausgewaehlte Konto zurueck oder <code>null</code> wenn der
   * Abbrechen-Knopf gedrueckt wurde.
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
