/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/JUnit/AllClasses.java,v $
 * $Revision: 1.1 $
 * $Date: 2011/02/12 09:40:48 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: AllClasses.java,v $
 * Revision 1.1  2011/02/12 09:40:48  jost
 * Vorbereitung kompakte Abbuchung
 *
 **********************************************************************/
package de.jost_net.JVerein.JUnit;

import junit.framework.Test;
import junit.framework.TestSuite;
import de.jost_net.JVerein.io.XLastschriftenTest;

public class AllClasses
{
  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(XLastschriftenTest.class);
    return suite;

  }
}
