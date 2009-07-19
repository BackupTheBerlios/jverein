/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein.test/src/de/jost_net/JVerein/Test/Tests/StammdatenATest.java,v $
 * $Revision: 1.1 $
 * $Date: 2009/07/19 20:48:15 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: StammdatenATest.java,v $
 * Revision 1.1  2009/07/19 20:48:15  jost
 * *** empty log message ***
 *
 * Revision 1.2  2009/07/06 07:18:20  jost
 * *** empty log message ***
 *
 * Revision 1.1  2009/07/05 13:40:48  jost
 * *** empty log message ***
 *
 * Revision 1.2  2009/07/01 07:19:49  jost
 * Jameica-Default-Logger verwenden
 *
 * Revision 1.1  2009/07/01 07:02:20  jost
 * Initial
 *
 **********************************************************************/
package de.jost_net.JVerein.Test.Tests;

import java.io.IOException;
import java.net.MalformedURLException;

import com.lowagie.text.DocumentException;

import de.jost_net.JVerein.Test.AbstractTest;
import de.jost_net.JVerein.Test.UseCaseDescription;
import de.jost_net.JVerein.rmi.Stammdaten;
import de.willuhn.datasource.rmi.DBIterator;
import de.willuhn.logging.Logger;

public class StammdatenATest extends AbstractTest
{
  public StammdatenATest(String arg0) throws DocumentException,
      MalformedURLException, IOException
  {
    super(arg0);
  }

  public void testStammdaten001() throws Exception
  {
    usecaseDescription = new UseCaseDescription("Stammdaten001",
        "Eingabe der Stammdaten");
    usecaseDescription.setPreCondition("keine");
    usecaseDescription
        .addExecution("Name des Vereins eingeben (Pflichtfeld, nicht mehr als 27 Zeichen)");
    usecaseDescription
        .addExecution("Bankleitzahl eingeben (bedingtes Pflichtfeld, 8 Zeichen)");
    usecaseDescription
        .addExecution("Konto Nummer eingeben (bedingtes Pflichtfeld, nicht mehr als 10 Zeichen)");
    usecaseDescription
        .addExecution("Altersgruppen eingeben (Format \"0-6,7-10\")\"");
    usecaseDescription.addExecution("Jubiläen eingeben (Format \"10,20\")");
    usecaseDescription.addExecution("Altersjubiläen (Format \"10,20\")");
    usecaseDescription
        .addExecution("Den Button \"speichern\" betätigen oder Taste \"Enter\" drücken");
    usecaseDescription.setExpectedResult("Die Stammdaten werden gespeichert");
    Logger.info(this.getName());

    this.navigate("JVerein", "Administration", "Stammdaten");

    bot.textWithLabel("Name").setText("JVerein-Club");
    bot.textWithLabel("Bankleitzahl").setText("12345678");
    bot.textWithLabel("Konto").setText("1234567890");
    bot.textWithLabel("Altersgruppen").setText(
        "0-6,7-12,13-16,17-18,19-25,26-99");
    bot.button("speichern").click();
    assertEquals(this.getMessage().getText(), "Stammdaten gespeichert");
    this.writeDocument();
  }
  // public void testCreateStammdaten() throws Exception
  // {
  // Logger.info("Teste Erstellung der Stammdaten");
  // Stammdaten st = (Stammdaten) getDBService().createObject(Stammdaten.class,
  // null);
  // st.setName("JVerein");
  // st.setAltersgruppen("0-6,7-12,13-16,17-25,26-99");
  // st.setBlz("12345678");
  // st.setKonto("1234567890");
  // st.setAltersjubilaeen("65,70,75,80,85,90,91,92,93,94,95,96,97,98,99,100");
  //
  // Logger.info("Teste Speichern der Stammdaten");
  // st.store();
  // assertNotNull(st.getID());
  // Logger.info("Teste auf Vorhandensein in Iterator");
  // DBIterator i = getDBService().createList(Stammdaten.class);
  // assertNotNull(i.contains(st));
  // }

}
