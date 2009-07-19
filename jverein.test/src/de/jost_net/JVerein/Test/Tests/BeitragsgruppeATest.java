/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein.test/src/de/jost_net/JVerein/Test/Tests/BeitragsgruppeATest.java,v $
 * $Revision: 1.1 $
 * $Date: 2009/07/19 20:48:15 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: BeitragsgruppeATest.java,v $
 * Revision 1.1  2009/07/19 20:48:15  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.JVerein.Test.Tests;

import java.io.IOException;
import java.net.MalformedURLException;

import com.lowagie.text.DocumentException;

import de.jost_net.JVerein.Test.AbstractTest;
import de.jost_net.JVerein.Test.UseCaseDescription;
import de.willuhn.logging.Logger;

public class BeitragsgruppeATest extends AbstractTest
{
  public BeitragsgruppeATest(String arg0) throws DocumentException,
      MalformedURLException, IOException
  {
    super(arg0);
  }

  public void testBeitragsgruppe001() throws Exception
  {
    usecaseDescription = new UseCaseDescription("Beitragsgruppe001",
        "Eingabe einer neuen Beitragsgruppe für Erwachsene");
    usecaseDescription.setPreCondition("keine");
    usecaseDescription
        .addExecution("Bezeichnung der Beitragsgruppe eingeben. (Pflichtfeld, max. 30 Zeichen)");
    usecaseDescription
        .addExecution("Betrag eingeben. (Pflichtfeld, Format ##0,00)");
    usecaseDescription.addExecution("Beitragsart auswählen. Hier: Normal");
    usecaseDescription.setExpectedResult("Die Beitragsgruppe wird gespeichert");
    Logger.info(this.getName());

    this.navigate("JVerein", "Administration", "Beitragsgruppe");
    bot.button("neu").click();
    bot.textWithLabel("Bezeichnung").setText("Erwachsene");
    bot.textWithLabel("Betrag").setText("50,00");
    bot.ccomboBoxWithLabel("Beitragsart").setSelection("Normal");
    bot.button("speichern").click();
    assertEquals(this.getMessage().getText(), "Beitragsgruppe gespeichert");
    this.writeDocument();
  }

  public void testBeitragsgruppe002() throws Exception
  {
    usecaseDescription = new UseCaseDescription("Beitragsgruppe002",
        "Eingabe einer neuen Beitragsgruppe für Kinder und Jugendliche");
    usecaseDescription.setPreCondition("keine");
    usecaseDescription
        .addExecution("Bezeichnung der Beitragsgruppe eingeben. (Pflichtfeld, max. 30 Zeichen)");
    usecaseDescription
        .addExecution("Betrag eingeben. (Pflichtfeld, Format ##0,00)");
    usecaseDescription.addExecution("Beitragsart auswählen. Hier: Normal");
    usecaseDescription.setExpectedResult("Die Beitragsgruppe wird gespeichert");
    Logger.info(this.getName());

    this.navigate("JVerein", "Administration", "Beitragsgruppe");
    bot.button("neu").click();
    bot.textWithLabel("Bezeichnung").setText("Kinder und Jugendliche");
    bot.textWithLabel("Betrag").setText("25,00");
    bot.ccomboBoxWithLabel("Beitragsart").setSelection("Normal");
    bot.button("speichern").click();
    assertEquals(this.getMessage().getText(), "Beitragsgruppe gespeichert");
    this.writeDocument();
  }

  public void testBeitragsgruppe003() throws Exception
  {
    usecaseDescription = new UseCaseDescription("Beitragsgruppe003",
        "Eingabe einer neuen Beitragsgruppe für Familien (Zahler)");
    usecaseDescription.setPreCondition("keine");
    usecaseDescription
        .addExecution("Bezeichnung der Beitragsgruppe eingeben. (Pflichtfeld, max. 30 Zeichen)");
    usecaseDescription
        .addExecution("Betrag eingeben. (Pflichtfeld, Format ##0,00)");
    usecaseDescription
        .addExecution("Beitragsart auswählen. Hier: Familie: Zahler");
    usecaseDescription.setExpectedResult("Die Beitragsgruppe wird gespeichert");
    Logger.info(this.getName());

    this.navigate("JVerein", "Administration", "Beitragsgruppe");
    bot.button("neu").click();
    bot.textWithLabel("Bezeichnung").setText("Familienbeitrag");
    bot.textWithLabel("Betrag").setText("120,00");
    bot.ccomboBoxWithLabel("Beitragsart").setSelection("Familie: Zahler");
    bot.button("speichern").click();
    assertEquals(this.getMessage().getText(), "Beitragsgruppe gespeichert");
    this.writeDocument();
  }

  public void testBeitragsgruppe004() throws Exception
  {
    usecaseDescription = new UseCaseDescription("Beitragsgruppe004",
        "Eingabe einer neuen Beitragsgruppe für Familien (nicht zahlende Angehörige)");
    usecaseDescription.setPreCondition("keine");
    usecaseDescription
        .addExecution("Bezeichnung der Beitragsgruppe eingeben. (Pflichtfeld, max. 30 Zeichen)");
    usecaseDescription
        .addExecution("Betrag eingeben. (Pflichtfeld, Format ##0,00)");
    usecaseDescription
        .addExecution("Beitragsart auswählen. Hier: Familie: Angehöriger");
    usecaseDescription.setExpectedResult("Die Beitragsgruppe wird gespeichert");
    Logger.info(this.getName());

    this.navigate("JVerein", "Administration", "Beitragsgruppe");
    bot.button("neu").click();
    bot.textWithLabel("Bezeichnung").setText("Familienbeitrag (Angehöriger)");
    bot.textWithLabel("Betrag").setText("0,00");
    bot.ccomboBoxWithLabel("Beitragsart").setSelection("Familie: Angehöriger");
    bot.button("speichern").click();
    assertEquals(this.getMessage().getText(), "Beitragsgruppe gespeichert");
    this.writeDocument();
  }

}
