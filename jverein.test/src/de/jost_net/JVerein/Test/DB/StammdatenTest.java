/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein.test/src/de/jost_net/JVerein/Test/DB/Attic/StammdatenTest.java,v $
 * $Revision: 1.1 $
 * $Date: 2009/07/01 07:02:20 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: StammdatenTest.java,v $
 * Revision 1.1  2009/07/01 07:02:20  jost
 * Initial
 *
 **********************************************************************/
package de.jost_net.JVerein.Test.DB;

import java.util.logging.Logger;

import de.jost_net.JVerein.Test.AbstractTest;
import de.jost_net.JVerein.rmi.Stammdaten;
import de.willuhn.datasource.rmi.DBIterator;

public class StammdatenTest extends AbstractTest
{
  private static Logger logger = Logger.getLogger(Stammdaten.class.getName());

  public void testCreateStammdaten() throws Exception
  {
    logger.info("Teste Erstellung der Stammdaten");
    Stammdaten st = (Stammdaten) getDBService().createObject(Stammdaten.class,
        null);
    st.setName("JVerein");
    st.setAltersgruppen("0-6,7-12,13-16,17-25,26-99");
    st.setBlz("12345678");
    st.setKonto("1234567890");
    st.setAltersjubilaeen("65,70,75,80,85,90,91,92,93,94,95,96,97,98,99,100");

    logger.info("Teste Speichern der Stammdaten");
    st.store();
    assertNotNull(st.getID());
    logger.info("Teste auf Vorhandensein in Iterator");
    DBIterator i = getDBService().createList(Stammdaten.class);
    assertNotNull(i.contains(st));

  }

  /**
   * Constructor for KontoImplTest.
   * 
   * @param arg0
   */
  public StammdatenTest(String arg0)
  {
    super(arg0);
  }

}
