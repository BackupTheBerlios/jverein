/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/DBTools/src/de/jost_net/DBTools/DBComparator.java,v $
 * $Revision: 1.1 $
 * $Date: 2007/07/08 09:37:38 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: DBComparator.java,v $
 * Revision 1.1  2007/07/08 09:37:38  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.DBTools;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;

/**
 * Vergleicht die Strukturen von 2 Datenbanken.<br>
 * ACHTUNG! Zur Zeit werden Indices und Foreign Keys noch nicht abgeglichen!
 */
public class DBComparator
{

  private Connection conn1;

  private Connection conn2;

  public DBComparator(String driver1, String jdbcurl1, String user1,
      String pass1, String driver2, String jdbcurl2, String user2, String pass2)
      throws Exception
  {
    // Datenbankverbindung 1 aufbauen
    Class.forName(driver1).newInstance();
    conn1 = DriverManager.getConnection(jdbcurl1, user1, pass1);
    DatabaseMetaData meta1 = conn1.getMetaData();
    System.out.println(meta1.getDatabaseProductName() + " "
        + meta1.getDatabaseProductVersion());
    Database db1 = new Database(meta1);

    // Datenbankverbindung 2 aufbauen
    Class.forName(driver2).newInstance();
    conn2 = DriverManager.getConnection(jdbcurl2, user2, pass2);
    DatabaseMetaData meta2 = conn2.getMetaData();
    System.out.println(meta2.getDatabaseProductName() + " "
        + meta2.getDatabaseProductVersion());
    Database db2 = new Database(meta2);

    db1.compare(db2);
    System.out.println("Fertig");
  }

  public static void main(String[] args)
  {
    if (args.length != 8)
    {
      System.out.println("Falsche Anzahl an Parametern");
      usage();
      System.exit(1);
    }
    try
    {
      new DBComparator(args[0], args[1], args[2], args[3], args[4], args[5],
          args[6], args[7]);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  private static void usage()
  {
    System.out
        .println("usage: java de.jost_net.DBDoc.DBComparator driver1 jdbcurl1 user1 password1 driver2 jdbcurl2 user2 password2 ");
  }

}
