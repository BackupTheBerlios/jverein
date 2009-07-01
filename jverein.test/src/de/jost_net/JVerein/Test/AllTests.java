/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein.test/src/de/jost_net/JVerein/Test/Attic/AllTests.java,v $
 * $Revision: 1.1 $
 * $Date: 2009/07/01 07:02:20 $
 * $Author: jost $
 *
 * Copyright (c) by willuhn.webdesign and  (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: AllTests.java,v $
 * Revision 1.1  2009/07/01 07:02:20  jost
 * Initial
 *
 **********************************************************************/

package de.jost_net.JVerein.Test;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

import junit.framework.Test;
import junit.framework.TestSuite;
import de.willuhn.io.FileUtil;
import de.willuhn.jameica.system.Application;
import de.willuhn.jameica.system.Config;
import de.willuhn.jameica.system.StartupParams;
import de.willuhn.logging.Logger;

/**
 */
public class AllTests extends TestSuite
{

  public final static String WORK_DIR = "/tmp/jverein.junit/work";

  /**
   * Main-Method.
   * 
   * @param args
   *          werden ignoriert
   * @throws Exception
   */
  public static void main(String[] args) throws Exception
  {
    suite();
  }

  /**
   * @return
   * @throws Exception
   */
  public static Test suite() throws Exception
  {
    Logger.info("Starte Test-Suite");
    TestSuite suite = new TestSuite("Test for de.jost_net.JVerein.Test");

    // Altes Test-Verzeichnis loeschen, falls es existiert
    File dir = new File(WORK_DIR);
    if (dir.exists() && dir.isDirectory())
    {
      Logger.info("Loesche altes Test-Verzeichnis " + dir);
      FileUtil.deleteRecursive(dir);
    }

    // Config erstellen, die auf die noetigen Plugins verweist
    OutputStream os = null;
    try
    {
      File configDir = new File(WORK_DIR, "cfg");
      configDir.mkdirs();
      Properties props = new Properties();
      props.put("jameica.plugin.dir.0", "../hibiscus");
      props.put("jameica.plugin.dir.1", "../jverein");
      props.put("jameica.plugin.dir.2", "../jverein.test");
      os = new BufferedOutputStream(new FileOutputStream(new File(configDir,
          Config.class.getName() + ".properties")));
      props.store(os, "");
    }
    finally
    {
      if (os != null)
      {
        os.close();
      }
    }

    // Anwendung starten
    Logger.info("Starte Jameica");

    String[] args = new String[] { "-f", WORK_DIR, // Arbeitsverzeichnis
        "-p", "test", // Master-Passwort
        "-d" // Server-Mode - ohne GUI
    };

    StartupParams params = new StartupParams(args);
    Application.newInstance(params);

    // Wir koennen die Tests nicht direkt hier ausfuehren, weil sie
    // als Plugin im Context von Jameica laufen muessen. Daher ist
    // auch die Klasse AllTests selbst ein Plugin. Die eigentlichen
    // Tests laufen dann im init().
    return suite;
  }
}
