/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein.test/src/de/jost_net/JVerein/Test/TestPlugin.java,v $
 * $Revision: 1.1 $
 * $Date: 2009/07/01 07:02:20 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: TestPlugin.java,v $
 * Revision 1.1  2009/07/01 07:02:20  jost
 * Initial
 *
 **********************************************************************/
package de.jost_net.JVerein.Test;

import junit.textui.TestRunner;
import de.jost_net.JVerein.Test.DB.StammdatenTest;
import de.willuhn.jameica.plugin.AbstractPlugin;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

/**
 * Launcher fuer die zu testenden Plugins.
 */
public class TestPlugin extends AbstractPlugin
{
  /**
   * @see de.willuhn.jameica.plugin.AbstractPlugin#init()
   */
  public void init() throws ApplicationException
  {
    TestRunner.run(StammdatenTest.class);

    // Wir muessen den Shutdown-Test in einem Extra
    // Thread machen, da der PluginLoader aus der Init-Methode
    // unseres Plugins zurueckgekehrt sein muss.
    Thread t = new Thread()
    {
      public void run()
      {
        // Wir warten erstmal noch 5 Sekunden
        Logger.info("Warte 5 Sekunden bis Shutdown");
        try
        {
          sleep(5000l);
        }
        catch (InterruptedException e)
        {
          throw new RuntimeException(e);
        }
        TestRunner.run(ShutdownTest.class);
      }
    };
    t.start();
  }
}
