/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/JUnit/AllClasses.java,v $
 * $Revision: 1.3 $
 * $Date: 2011/10/03 11:56:01 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/
package de.jost_net.JVerein.JUnit;

import junit.framework.Test;
import junit.framework.TestSuite;
import de.jost_net.JVerein.io.AbrechnungTest;
import de.jost_net.JVerein.io.XLastschriftenTest;

public class AllClasses
{
  public static Test suite()
  {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(XLastschriftenTest.class);
    suite.addTestSuite(AbrechnungTest.class);
    return suite;

  }
}
