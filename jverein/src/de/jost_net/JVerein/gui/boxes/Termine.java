/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/boxes/Attic/Termine.java,v $
 * $Revision: 1.2 $
 * $Date: 2011/03/28 18:06:55 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: Termine.java,v $
 * Revision 1.2  2011/03/28 18:06:55  jost
 * Überflüssigen Code entfernt.
 *
 * Revision 1.1  2011-03-18 19:15:03  jost
 * Neu: JVerein-Termine auf der Startseite
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.boxes;

import java.rmi.RemoteException;
import java.util.Date;

import org.eclipse.swt.widgets.Composite;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.parts.TerminePart;
import de.willuhn.jameica.gui.boxes.AbstractBox;
import de.willuhn.jameica.gui.boxes.Box;

public class Termine extends AbstractBox implements Box
{
  private TerminePart termine = null;

  private static Date currentDate = null;

  public Termine()
  {
    super();
    currentDate = new Date();
  }

  public String getName()
  {
    return JVereinPlugin.getI18n().tr("JVerein: Termine");
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
    this.termine = new TerminePart();
    this.termine.setCurrentDate(currentDate);
    this.termine.paint(parent);
  }

  @Override
  public boolean isActive()
  {
    return super.isActive();
  }

  @Override
  public int getHeight()
  {
    return 500;
  }

}
