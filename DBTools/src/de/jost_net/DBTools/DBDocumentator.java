/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/DBTools/src/de/jost_net/DBTools/DBDocumentator.java,v $
 * $Revision: 1.1 $
 * $Date: 2007/07/08 09:37:38 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: DBDocumentator.java,v $
 * Revision 1.1  2007/07/08 09:37:38  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.DBTools;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;

/**
 * Dokumentiert die Struktur einer Datenbank in einem PDF-Dokument.
 */
public class DBDocumentator
{

  private Connection conn;

  public PdfContentByte cb;

  public DBDocumentator(String driver, String jdbcurl, String user,
      String pass, String filename) throws Exception
  {
    // PDF-Dokument öffnen
    Document document = new Document(PageSize.A4, 70, 50, 50, 50);
    PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(
        filename));
    // step 3: we open the document
    document.open();
    cb = writer.getDirectContent();
    writer.setViewerPreferences(PdfWriter.PageModeUseOutlines);

    // Datenbankverbindung aufbauen
    Class.forName(driver).newInstance();
    conn = DriverManager.getConnection(jdbcurl, user, pass);
    DatabaseMetaData meta = conn.getMetaData();
    System.out.println(meta.getDatabaseProductName() + " "
        + meta.getDatabaseProductVersion());
    Database db = new Database(meta);
    db.generatePDF(document, cb);
    // System.out.println(db.toString());
    document.close();
    System.out.println("Fertig");
  }

  public static void main(String[] args)
  {
    if (args.length != 5)
    {
      System.out.println("Falsche Anzahl an Parametern");
      usage();
      System.exit(1);
    }
    try
    {
      new DBDocumentator(args[0], args[1], args[2], args[3], args[4]);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  private static void usage()
  {
    System.out
        .println("usage: java de.jost_net.DBDoc.DBDoc driver jdbcurl user password outputfile");
  }

}
