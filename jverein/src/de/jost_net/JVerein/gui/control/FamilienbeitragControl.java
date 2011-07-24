/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/control/Attic/FamilienbeitragControl.java,v $
 * $Revision: 1.1 $
 * $Date: 2011/07/24 18:03:20 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: FamilienbeitragControl.java,v $
 * Revision 1.1  2011/07/24 18:03:20  jost
 * Neu: Auflistung Familienbeiträge
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.control;

import java.rmi.RemoteException;

import de.jost_net.JVerein.gui.action.MitgliedDetailAction;
import de.willuhn.jameica.gui.AbstractControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.parts.TreePart;
import de.willuhn.jameica.system.Settings;

public class FamilienbeitragControl extends AbstractControl
{
  private TreePart familienbeitragtree;

  private Settings settings = null;

  public FamilienbeitragControl(AbstractView view)
  {
    super(view);
    settings = new Settings(this.getClass());
    settings.setStoreWhenRead(true);
  }

  public TreePart getFamilienbeitraegeTree() throws RemoteException
  {
    familienbeitragtree = new TreePart(new FamilienbeitragNode(),
        new MitgliedDetailAction());
    familienbeitragtree.addColumn("Name", "name");
    // lehrgaengeList.setContextMenu(new LehrgangMenu());
    familienbeitragtree.setRememberColWidths(true);
    familienbeitragtree.setRememberOrder(true);
    return familienbeitragtree;
  }

}
