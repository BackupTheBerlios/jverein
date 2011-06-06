/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/dialogs/EigenschaftenAuswahlDialog.java,v $
 * $Revision: 1.10 $
 * $Date: 2011/06/06 19:16:37 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: EigenschaftenAuswahlDialog.java,v $
 * Revision 1.10  2011/06/06 19:16:37  jost
 * Neu: Funktion zur gleichzeitigen Zuordnung einer Eigenschaft an viele Mitglieder
 *
 * Revision 1.9  2010-10-15 09:58:26  jost
 * Code aufger�umt
 *
 * Revision 1.8  2010-04-08 17:56:56  jost
 * Bugfix
 *
 * Revision 1.7  2010/03/27 20:10:05  jost
 * EigenschaftenAuswahl �berarbeitet.
 *
 * Revision 1.6  2010/02/01 20:58:46  jost
 * Vermeidung Warnings.
 *
 * Revision 1.5  2009/12/06 21:40:39  jost
 * �berfl�ssigen Code entfernt.
 *
 * Revision 1.4  2009/11/19 19:44:02  jost
 * Bugfix Eigenschaften
 *
 * Revision 1.3  2009/11/17 20:57:34  jost
 * Neu: Eigenschaft und EigenschaftGruppe
 *
 * Revision 1.2  2009/06/11 21:02:41  jost
 * Vorbereitung I18N
 *
 * Revision 1.1  2008/01/25 16:02:57  jost
 * Neu: Eigenschaften des Mitgliedes
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.dialogs;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.eclipse.swt.widgets.Composite;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.control.MitgliedControl;
import de.jost_net.JVerein.server.EigenschaftenNode;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.dialogs.AbstractDialog;
import de.willuhn.jameica.gui.parts.TreePart;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.gui.util.LabelGroup;
import de.willuhn.logging.Logger;

/**
 * Dialog, zur Auswahl von Eigenschaften eines Mitglied.
 */
public class EigenschaftenAuswahlDialog extends AbstractDialog
{

  private MitgliedControl control;

  private String defaults = null;

  private boolean ohnePflicht;

  private ArrayList<EigenschaftenNode> retval = new ArrayList<EigenschaftenNode>();

  /**
   * Eigenschaften oder Eigenschaftengruppen ausw�hlen
   * 
   * @param defaults
   *          Liste der Eigenschaften-IDs durch Komma separiert.
   */
  public EigenschaftenAuswahlDialog(String defaults, boolean ohnePflicht)
  {
    super(EigenschaftenAuswahlDialog.POSITION_CENTER);
    this.setSize(400, 400);
    this.ohnePflicht = ohnePflicht;
    setTitle(JVereinPlugin.getI18n().tr("Eigenschaften ausw�hlen "));
    control = new MitgliedControl(null);
    this.setDefaults(defaults);
  }

  /**
   * Speichert die Default-Werte.
   * 
   * @param defaults
   */
  public void setDefaults(String defaults)
  {
    this.defaults = defaults != null ? defaults : "";
  }

  @Override
  protected void paint(Composite parent) throws RemoteException
  {
    final TreePart tree = control.getEigenschaftenAuswahlTree(this.defaults,
        ohnePflicht);

    LabelGroup group = new LabelGroup(parent, JVereinPlugin.getI18n().tr(
        "Eigenschaften"), true);

    group.addPart(tree);

    ButtonArea buttons = new ButtonArea(parent, 2);
    buttons.addButton(i18n.tr(JVereinPlugin.getI18n().tr("OK")), new Action()
    {

      public void handleAction(Object context)
      {
        try
        {
          retval = new ArrayList<EigenschaftenNode>();
          ArrayList<?> checkednodes = (ArrayList<?>) tree.getItems();
          for (Object o : checkednodes)
          {
            EigenschaftenNode checkedNode = (EigenschaftenNode) o;
            if (checkedNode.getNodeType() == EigenschaftenNode.EIGENSCHAFTEN)
            {
              retval.add(checkedNode);
            }
          }
        }
        catch (RemoteException e)
        {
          Logger.error("Fehler", e);
        }
        close();
      }
    });
  }

  @Override
  protected Object getData()
  {
    return retval;
  }
}
