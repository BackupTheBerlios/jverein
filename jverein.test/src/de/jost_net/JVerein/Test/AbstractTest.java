/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein.test/src/de/jost_net/JVerein/Test/AbstractTest.java,v $
 * $Revision: 1.3 $
 * $Date: 2009/07/19 20:47:48 $
 * $Author: jost $
 *
 * Copyright (c) by willuhn.webdesign and  (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: AbstractTest.java,v $
 * Revision 1.3  2009/07/19 20:47:48  jost
 * *** empty log message ***
 *
 * Revision 1.2  2009/07/05 13:40:25  jost
 * *** empty log message ***
 *
 * Revision 1.1  2009/07/01 07:02:20  jost
 * Initial
 *
 **********************************************************************/

package de.jost_net.JVerein.Test;

import java.io.IOException;
import java.net.MalformedURLException;

import junit.framework.TestCase;

import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

import com.lowagie.text.DocumentException;

import de.jost_net.JVerein.Einstellungen;
import de.willuhn.datasource.rmi.DBService;
import de.willuhn.jameica.messaging.MessageCollector;
import de.willuhn.jameica.messaging.StatusBarMessage;
import de.willuhn.jameica.system.Application;

/**
 */
public abstract class AbstractTest extends TestCase
{
  protected UseCaseDescription usecaseDescription;

  protected static SWTBot bot;

  private static int screenshotcounter = 0;

  private static MessageCollector collector;

  public AbstractTest(String name) throws DocumentException,
      MalformedURLException, IOException
  {
    super(name);
    // Collector erzeugen
    if (collector == null)
    {
      collector = new MessageCollector();
      // Festlegen, welche Art von Nachricht wir mitschneiden wollen
      collector.collect(StatusBarMessage.class);
      // Registrieren.
      Application.getMessagingFactory().registerMessageConsumer(collector);
    }
    if (bot == null)
    {
      bot = new SWTBot();
    }

  }

  /*
   * @see TestCase#setUp()
   */
  protected void setUp() throws Exception
  {
    super.setUp();
  }

  protected UseCaseDescription getUseCaseDescription()
  {
    return usecaseDescription;
  }

  public DBService getDBService() throws Exception
  {
    return (DBService) Einstellungen.getDBService();
  }

  protected void navigate(String... menuitems)
  {
    SWTBotTree mntree = bot.tree(0);
    SWTBotTreeItem mnt = mntree.getTreeItem("Jameica");
    for (String menuitem : menuitems)
    {
      mnt = mnt.getNode(menuitem);
    }
    mnt.click();
  }

  protected void writeDocument() throws DocumentException,
      MalformedURLException, IOException
  {
    screenshotcounter++;
    if (screenshotcounter > 1)
    {
      TestPlugin.doc.newPage();
    }
    try
    {
      Thread.sleep(30);
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
    String imagepath = JameicaStarter.WORK_DIR + "/" + screenshotcounter
        + ".png";
    new SWTBot().captureScreenshot(imagepath);
    usecaseDescription.write(TestPlugin.doc, imagepath);
  }

  protected StatusBarMessage getMessage()
  {
    return (StatusBarMessage) collector.pop();
  }

}
