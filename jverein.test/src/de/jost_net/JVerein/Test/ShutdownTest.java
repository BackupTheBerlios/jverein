/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein.test/src/de/jost_net/JVerein/Test/ShutdownTest.java,v $
 * $Revision: 1.1 $
 * $Date: 2009/07/01 07:02:20 $
 * $Author: jost $
 * $Locker:  $
 * $State: Exp $
 *
 * Copyright (c) by willuhn.webdesign
 * All rights reserved
 *
 **********************************************************************/
package de.jost_net.JVerein.Test;

import junit.framework.TestCase;

/**
 */
public class ShutdownTest extends TestCase
{

  /**
   * Constructor for ShutdownTest.
   * 
   * @param arg0
   */
  public ShutdownTest(String arg0)
  {
    super(arg0);
  }

  public void testShutdown() throws Exception
  {
    System.exit(0);
  }

}

/**********************************************************************
 * $Log: ShutdownTest.java,v $
 * Revision 1.1  2009/07/01 07:02:20  jost
 * Initial
 * Revision 1.1 2004/07/15 23:30:41 willuhn
 * 
 * @N Unit-Tests fuer Turnus
 * 
 **********************************************************************/
