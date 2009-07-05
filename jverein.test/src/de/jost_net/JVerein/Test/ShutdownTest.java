/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein.test/src/de/jost_net/JVerein/Test/ShutdownTest.java,v $
 * $Revision: 1.2 $
 * $Date: 2009/07/05 13:40:25 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: ShutdownTest.java,v $
 * Revision 1.2  2009/07/05 13:40:25  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.JVerein.Test;

import junit.framework.TestCase;

import org.eclipse.swtbot.swt.finder.SWTBot;

public class ShutdownTest extends TestCase
{
  public ShutdownTest(String arg0)
  {
    super(arg0);
  }

  public void testShutdown() throws Exception
  {
    SWTBot bot = new SWTBot();
    bot.menu("Datei").menu("Beenden").click();
  }

}

/**********************************************************************
 * $Log: ShutdownTest.java,v $
 * Revision 1.2  2009/07/05 13:40:25  jost
 * *** empty log message ***
 * Revision 1.1 2009/07/01 07:02:20 jost Initial
 * Revision 1.1 2004/07/15 23:30:41 willuhn
 * 
 * @N Unit-Tests fuer Turnus
 * 
 **********************************************************************/
