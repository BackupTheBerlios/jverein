/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/boxes/Attic/Wiedervorlage.java,v $
 * $Revision: 1.4 $
 * $Date: 2009/06/11 21:02:17 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: Wiedervorlage.java,v $
 * Revision 1.4  2009/06/11 21:02:17  jost
 * Vorbereitung I18N
 *
 * Revision 1.3  2007/12/29 19:09:41  jost
 * Explizite Höhe der Box vorgegeben.
 *
 * Revision 1.2  2007/12/28 13:10:22  jost
 * Kommentare entfernt und hinzugefügt.
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.boxes;

import java.rmi.RemoteException;

import org.eclipse.swt.widgets.Composite;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.WiedervorlageListeAction;
import de.jost_net.JVerein.gui.parts.WiedervorlageList;
import de.willuhn.jameica.gui.boxes.AbstractBox;
import de.willuhn.jameica.gui.boxes.Box;

public class Wiedervorlage extends AbstractBox implements Box
{
  public Wiedervorlage()
  {
    super();
  }

  public String getName()
  {
    return JVereinPlugin.getI18n().tr("JVerein: Wiedervorlage");
  }

  public int getDefaultIndex()
  {
    return 3;
  }

  public boolean getDefaultEnabled()
  {
    return false;
  }

  public void paint(Composite parent) throws RemoteException
  {
    new WiedervorlageList(new WiedervorlageListeAction())
        .getWiedervorlageList().paint(parent);
  }

  public boolean isActive()
  {
    return super.isActive();
  }

  public int getHeight()
  {
    return 130;
  }

}
