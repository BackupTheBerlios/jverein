/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/DBTools/src/de/jost_net/DBTools/Database.java,v $
 * $Revision: 1.1 $
 * $Date: 2007/07/08 09:37:38 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: Database.java,v $
 * Revision 1.1  2007/07/08 09:37:38  jost
 * *** empty log message ***
 *
 **********************************************************************/

package de.jost_net.DBTools;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;

import de.jost_net.DBTools.Table;

/**
 * Repräsentiert eine Datenbank
 */
public class Database
{

  private Vector<Table> tables;

  public Database(DatabaseMetaData meta) throws SQLException
  {
    tables = new Vector<Table>();
    String[] tableType = { "TABLE" };
    ResultSet rs = meta.getTables(null, null, "%", tableType);
    while (rs.next())
    {
      tables.addElement(new Table(rs));
    }
    rs.close();
    for (int i = 0; i < tables.size(); i++)
    {
      tables.elementAt(i).addColumns(meta);
      tables.elementAt(i).addIndices(meta);
      tables.elementAt(i).addImportedKeys(meta);
      tables.elementAt(i).addExportedKeys(meta);
    }
  }

  public void compare(Database db2)
  {
    for (Table table : tables)
    {
      Table table2 = db2.getTable(table.getName());
      if (table2 == null)
      {
        System.out.println("Tabelle " + table.getName() + " fehlt in DB2");
      }
      table.compare(table2);
    }
    ArrayList<String> tablenames = db2.getTableNames();
    for (String tn : tablenames)
    {
      if (this.getTable(tn) == null)
      {
        System.out.println("Tabelle " + tn + " in DB2 zuviel vorhanden");
      }
    }
  }

  public Table getTable(String name)
  {
    for (Table table : tables)
    {
      if (table.getName().equals(name))
      {
        return table;
      }
    }
    return null;
  }

  public ArrayList<String> getTableNames()
  {
    ArrayList<String> retval = new ArrayList<String>();
    for (Table table : tables)
    {
      retval.add(table.getName());
    }
    return retval;
  }

  public void generatePDF(Document doc, PdfContentByte cb)
      throws DocumentException
  {
    for (int i = 0; i < tables.size(); i++)
    {
      tables.elementAt(i).generatePDF(doc, cb);
      doc.newPage();
    }

  }

  @Override
  public String toString()
  {
    String ret = "[Database]\n";
    for (int i = 0; i < tables.size(); i++)
    {
      ret += tables.elementAt(i).toString();
    }
    return ret;
  }
}
