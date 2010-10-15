/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/boxes/Attic/AktuelleGeburtstage.java,v $
 * $Revision: 1.2 $
 * $Date: 2010/10/15 09:58:30 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: AktuelleGeburtstage.java,v $
 * Revision 1.2  2010/10/15 09:58:30  jost
 * Code aufger�umt
 *
 * Revision 1.1  2009-07-14 07:28:31  jost
 * Neu: Box aktuelle Geburtstage
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.boxes;

import java.rmi.RemoteException;

import org.eclipse.swt.widgets.Composite;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.parts.AktuelleGeburtstageList;
import de.willuhn.jameica.gui.boxes.AbstractBox;
import de.willuhn.jameica.gui.boxes.Box;

public class AktuelleGeburtstage extends AbstractBox implements Box
{
  public AktuelleGeburtstage()
  {
    super();
  }

  public String getName()
  {
    return JVereinPlugin.getI18n().tr("JVerein: Aktuelle Geburtstage");
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
    new AktuelleGeburtstageList()
        .getAktuelleGeburtstageList().paint(parent);
  }

  @Override
  public boolean isActive()
  {
    return super.isActive();
  }

  @Override
  public int getHeight()
  {
    return 180;
  }

}
