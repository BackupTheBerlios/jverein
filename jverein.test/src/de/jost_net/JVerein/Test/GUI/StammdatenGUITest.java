/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein.test/src/de/jost_net/JVerein/Test/GUI/Attic/StammdatenGUITest.java,v $
 * $Revision: 1.2 $
 * $Date: 2009/07/06 07:18:20 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: StammdatenGUITest.java,v $
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
package de.jost_net.JVerein.Test.GUI;

import java.io.IOException;
import java.net.MalformedURLException;

import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotLabel;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

import com.lowagie.text.DocumentException;

import de.jost_net.JVerein.Test.AbstractGUITest;
import de.willuhn.logging.Logger;

public class StammdatenGUITest extends AbstractGUITest
{
  public StammdatenGUITest(String arg0) throws DocumentException,
      MalformedURLException, IOException
  {
    super(arg0);
  }

  public void testStammdaten001() throws Exception
  {
    usecaseDescription.setName("Stammdaten001");
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

    SWTBot bot = new SWTBot();
    assertNotNull(bot);
    SWTBotTree mntree = bot.tree(0);
    SWTBotTreeItem mnt = mntree.getTreeItem("Jameica");
    mnt.getNode("JVerein").getNode("Administration").getNode("Stammdaten")
        .click();
    bot.textWithLabel("Name").setText("JVerein-Club");
    bot.textWithLabel("Bankleitzahl").setText("12345678");
    bot.textWithLabel("Konto").setText("1234567890");
    bot.textWithLabel("Altersgruppen").setText(
        "0-6,7-12,13-16,17-18,19-25,26-99");
    bot.button("speichern").click();
    SWTBotLabel l = bot.labelWithId("SWTBot", "STATUSBAR");
    assertEquals("Stammdaten gespeichert ", l.getText());
    this.writeDocument();
  }

}
