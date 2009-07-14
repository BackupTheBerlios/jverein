/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein.test/src/de/jost_net/JVerein/Test/DB/Attic/StammdatenDBTest.java,v $
 * $Revision: 1.1 $
 * $Date: 2009/07/05 13:40:40 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: StammdatenDBTest.java,v $
 * Revision 1.1  2009/07/05 13:40:40  jost
 * *** empty log message ***
 *
 * Revision 1.2  2009/07/01 07:19:49  jost
 * Jameica-Default-Logger verwenden
 *
 * Revision 1.1  2009/07/01 07:02:20  jost
 * Initial
 *
 **********************************************************************/
package de.jost_net.JVerein.Test.DB;

import java.io.IOException;
import java.net.MalformedURLException;

import com.lowagie.text.DocumentException;

import de.jost_net.JVerein.Test.AbstractDBTest;
import de.jost_net.JVerein.rmi.Stammdaten;
import de.willuhn.datasource.rmi.DBIterator;
import de.willuhn.logging.Logger;

public class StammdatenDBTest extends AbstractDBTest
{
  public StammdatenDBTest(String arg0) throws DocumentException,
      MalformedURLException, IOException
  {
    super(arg0);
  }

  public void testCreateStammdaten() throws Exception
  {
    Logger.info("Teste Erstellung der Stammdaten");
    Stammdaten st = (Stammdaten) getDBService().createObject(Stammdaten.class,
        null);
    st.setName("JVerein");
    st.setAltersgruppen("0-6,7-12,13-16,17-25,26-99");
    st.setBlz("12345678");
    st.setKonto("1234567890");
    st.setAltersjubilaeen("65,70,75,80,85,90,91,92,93,94,95,96,97,98,99,100");

    Logger.info("Teste Speichern der Stammdaten");
    st.store();
    assertNotNull(st.getID());
    Logger.info("Teste auf Vorhandensein in Iterator");
    DBIterator i = getDBService().createList(Stammdaten.class);
    assertNotNull(i.contains(st));
  }

  public String getUseCaseName()
  {
    return "...";
  }

  public String getDescription()
  {
    return "...";
  }
}