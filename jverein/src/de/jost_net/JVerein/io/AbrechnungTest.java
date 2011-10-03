/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/io/AbrechnungTest.java,v $
 * $Revision: 1.1 $
 * $Date: 2011/10/03 11:55:44 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/
package de.jost_net.JVerein.io;

import junit.framework.TestCase;

public class AbrechnungTest extends TestCase
{

  public void test01()
  {
    assertEquals(25, Abrechnung.dtaus27("Müller, Rüdiger").length());
    assertEquals(27, Abrechnung.dtaus27("Meier, Hans").length());
    assertEquals(27, Abrechnung.dtaus27("123456789012345678901234567890"));
  }

}
