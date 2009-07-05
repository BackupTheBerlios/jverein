/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein.test/src/de/jost_net/JVerein/Test/TestPlugin.java,v $
 * $Revision: 1.2 $
 * $Date: 2009/07/05 13:40:25 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: TestPlugin.java,v $
 * Revision 1.2  2009/07/05 13:40:25  jost
 * *** empty log message ***
 *
 * Revision 1.1  2009/07/01 07:02:20  jost
 * Initial
 *
 **********************************************************************/
package de.jost_net.JVerein.Test;

import junit.textui.TestRunner;
import de.jost_net.JVerein.Test.GUI.StammdatenGUITest;
import de.willuhn.jameica.plugin.AbstractPlugin;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

/**
 * Launcher fuer die zu testenden Plugins.
 */
public class TestPlugin extends AbstractPlugin
{
  public static TestDocument doc;

  /**
   * @see de.willuhn.jameica.plugin.AbstractPlugin#init()
   */
  public void init() throws ApplicationException
  {
    System.out.println("-->Start des Test-Plugins");
    new Thread(new Runnable()
    {
      public void run()
      {
        try
        {
          doc = new TestDocument(JameicaStarter.WORK_DIR + "/testdoc.pdf");
          Thread.sleep(2000);
          // TestRunner.run(StammdatenTest.class);
          TestRunner.run(StammdatenGUITest.class);
          Logger.info("Warte 2 Sekunden bis Shutdown");
          Thread.sleep(2000);
          TestRunner.run(ShutdownTest.class);
          doc.close();
        }
        catch (Throwable e)
        {
          e.printStackTrace();
        }
      }
    }).start();
  }
}
