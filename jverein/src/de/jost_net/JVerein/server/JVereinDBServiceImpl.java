/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/server/JVereinDBServiceImpl.java,v $
 * $Revision: 1.2 $
 * $Date: 2007/02/23 20:28:42 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: JVereinDBServiceImpl.java,v $
 * Revision 1.2  2007/02/23 20:28:42  jost
 * Mail- und Webadresse im Header korrigiert.
 *
 * Revision 1.1  2006/09/20 15:39:48  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.JVerein.server;

import java.rmi.RemoteException;

import de.jost_net.JVerein.JVereinPlugin;
import de.willuhn.datasource.db.EmbeddedDBServiceImpl;
import de.willuhn.jameica.system.Application;

public class JVereinDBServiceImpl extends EmbeddedDBServiceImpl
{
  private static final long serialVersionUID = -8164119243234450453L;

  public JVereinDBServiceImpl() throws RemoteException
  {
    super(Application.getPluginLoader().getPlugin(JVereinPlugin.class)
        .getResources().getWorkPath()
        + "/db/db.conf", "exampleuser", "examplepassword");

    this.setClassFinder(Application.getClassLoader().getClassFinder());
  }
}
