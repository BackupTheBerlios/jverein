/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/io/IORegistry.java,v $
 * $Revision: 1.4 $
 * $Date: 2010/10/15 09:58:29 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: IORegistry.java,v $
 * Revision 1.4  2010/10/15 09:58:29  jost
 * Code aufger�umt
 *
 * Revision 1.3  2010-02-01 21:01:30  jost
 * Vermeidung Warnings.
 *
 * Revision 1.2  2009/12/17 19:25:44  jost
 * *** empty log message ***
 *
 * Revision 1.1  2009/10/20 18:00:48  jost
 * Neu: Import von Zusatzbetr�gen
 *
 **********************************************************************/
package de.jost_net.JVerein.io;

import java.util.ArrayList;

import de.willuhn.jameica.system.Application;
import de.willuhn.logging.Logger;
import de.willuhn.util.ClassFinder;

/**
 * Ueber diese Klasse koennen alle verfuegbaren Export-und Import Formate
 * abgerufen werden.
 */
public class IORegistry
{

  // Liste der Zusatzbetr�ge-Import-Filter
  private static ArrayList<?> zusatzbetraege = null;

  static
  {
    Logger.info("Suche Import-Filter");
    zusatzbetraege = load(IZusatzbetraegeImport.class);
  }

  /**
   * Sucht im Classpath nach allen Filtern.
   * 
   * @param type
   *        zu ladender Typ.
   * @return Liste der gefundenen Importer/Exporter.
   */
  private static synchronized ArrayList<?> load(Class<?> type)
  {
    ArrayList<IO> l = new ArrayList<IO>();
    try
    {
      ClassFinder finder = Application.getClassLoader().getClassFinder();
      Class<?>[] list = finder.findImplementors(type);
      if (list == null || list.length == 0)
      {
        throw new ClassNotFoundException();
      }
      // Initialisieren
      for (int i = 0; i < list.length; ++i)
      {
        try
        {
          Logger.info("trying to load " + list[i].getName());
          IO io = (IO) list[i].newInstance();
          Logger.info("loaded: " + io.getName());
          l.add(io);
        }
        catch (Exception e)
        {
          Logger.error("error while loading import/export filter "
              + list[i].getName(), e);
        }
      }

    }
    catch (ClassNotFoundException e)
    {
      Logger.warn("no filters found for type: " + type.getName());
    }
    return l;
  }

  /**
   * Liefert eine Liste aller verfuegbaren Import-Formate f�r Zusatzbetr�ge
   */
  public static Importer[] getImporter(Class<?> type)
      throws ClassNotFoundException
  {
    if (type.getName().equals(IZusatzbetraegeImport.class.getName()))
    {
      return zusatzbetraege.toArray(new IZusatzbetraegeImport[zusatzbetraege.size()]);
    }
    throw new ClassNotFoundException("Klasse " + type.getCanonicalName()
        + " nicht gefunden.");
  }
}