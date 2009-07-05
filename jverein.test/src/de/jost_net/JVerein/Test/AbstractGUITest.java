/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein.test/src/de/jost_net/JVerein/Test/Attic/AbstractGUITest.java,v $
 * $Revision: 1.1 $
 * $Date: 2009/07/05 13:40:25 $
 * $Author: jost $
 *
 * Copyright (c) by willuhn.webdesign and  (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: AbstractGUITest.java,v $
 * Revision 1.1  2009/07/05 13:40:25  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.JVerein.Test;

import java.io.IOException;
import java.net.MalformedURLException;

import org.eclipse.swtbot.swt.finder.SWTBot;

import com.lowagie.text.DocumentException;

public abstract class AbstractGUITest extends AbstractTest
{
  private static int screenshotcounter = 0;

  public AbstractGUITest(String name) throws DocumentException,
      MalformedURLException, IOException
  {
    super(name);
  }

  @Override
  protected void writeDocument() throws DocumentException,
      MalformedURLException, IOException
  {
    screenshotcounter++;
    String imagepath = JameicaStarter.WORK_DIR + "/" + screenshotcounter
        + ".png";
    new SWTBot().captureScreenshot(imagepath);
    usecaseDescription.write(TestPlugin.doc, imagepath);
  }
}
