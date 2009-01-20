/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/dialogs/Attic/EigenschaftenNeuDialog.java,v $
 * $Revision: 1.3 $
 * $Date: 2009/01/20 20:09:24 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: EigenschaftenNeuDialog.java,v $
 * Revision 1.3  2009/01/20 20:09:24  jost
 * neue Icons
 *
 * Revision 1.2  2009/01/20 19:13:34  jost
 * neu: Back-Button mit Icon
 *
 * Revision 1.1  2008/01/25 16:03:19  jost
 * Neu: Eigenschaften des Mitgliedes
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.dialogs;

import java.rmi.RemoteException;

import org.eclipse.swt.widgets.Composite;

import de.jost_net.JVerein.gui.control.EigenschaftenControl;
import de.jost_net.JVerein.rmi.Mitglied;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.dialogs.AbstractDialog;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.gui.util.Color;
import de.willuhn.jameica.gui.util.LabelGroup;
import de.willuhn.util.ApplicationException;

/**
 * Dialog, zur Eingabe neuer Eigenschaften zu einem Mitglied.
 */
public class EigenschaftenNeuDialog extends AbstractDialog
{
  private EigenschaftenControl control;

  public EigenschaftenNeuDialog(Mitglied m) throws RemoteException
  {
    super(EigenschaftenNeuDialog.POSITION_CENTER);
    this.setSize(400, 400);
    setTitle("Neue Eigenschaften zu " + m.getNameVorname());
    control = new EigenschaftenControl(null, m);
  }

  protected void paint(Composite parent) throws Exception
  {
    LabelGroup group = new LabelGroup(parent, "Existierende Eigenschaften",
        true);
    group.addPart(control.getEigenschaftenNeuTable());
    group.addLabelPair("Neue Eigenschaft", control.getNeu());

    ButtonArea buttons = new ButtonArea(parent, 3);
    buttons.addButton("Zur�ck", new Action()
    {
      public void handleAction(Object context) throws ApplicationException
      {
        close();
      }
    }, "go-previous.png");
    buttons.addButton("Speichern", new Action()
    {
      public void handleAction(Object context) throws ApplicationException
      {
        String input = (String) control.getNeu().getValue();
        if (input.length() == 0)
        {
          throw new ApplicationException(
              "Bitte mit Doppelklick Eigenschaft ausw�hlen oder �ber die Tastatur eingeben");
        }
        try
        {
          control.handleStore();
          close();
        }
        catch (RemoteException e)
        {
          if (e.getCause().getMessage().indexOf("Index oder ") > -1)
          {
            control.getStatusbar().setColor(Color.ERROR);
            control.getStatusbar().setValue("Eigenschaft schon vorhanden");
          }
          else
          {
            throw new ApplicationException(e);
          }
        }
      }
    }, null, false, "document-save.png");
    LabelGroup status = new LabelGroup(parent, "");
    status.addLabelPair("", control.getStatusbar());
  }

  protected Object getData() throws Exception
  {
    return null;
  }
}
