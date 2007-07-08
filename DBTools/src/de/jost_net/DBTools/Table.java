/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/DBTools/src/de/jost_net/DBTools/Table.java,v $
 * $Revision: 1.1 $
 * $Date: 2007/07/08 09:37:38 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: Table.java,v $
 * Revision 1.1  2007/07/08 09:37:38  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.DBTools;

import java.awt.Color;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfDestination;
import com.lowagie.text.pdf.PdfOutline;
import com.lowagie.text.pdf.PdfPTable;

import de.jost_net.DBTools.PDFUtil;

/**
 * Repräsentiert eine Tabelle
 */
public class Table
{

  private String name;

  private String type;

  private String remarks;

  private Vector<Column> columns;

  private Vector<Index> indices;

  private Vector<ForeignKey> impKeys;

  private Vector<ForeignKey> expKeys;

  public Table(ResultSet rs) throws SQLException
  {
    this.name = rs.getString("TABLE_NAME");
    this.type = rs.getString("TABLE_TYPE");
    this.remarks = rs.getString("REMARKS");
    columns = new Vector<Column>();
    indices = new Vector<Index>();
    impKeys = new Vector<ForeignKey>();
    expKeys = new Vector<ForeignKey>();
  }

  public void addColumns(DatabaseMetaData meta) throws SQLException
  {
    ResultSet rs = meta.getColumns(null, null, name, "%");
    while (rs.next())
    {
      columns.addElement(new Column(rs));
    }
  }

  public void addIndices(DatabaseMetaData meta) throws SQLException
  {
    ResultSet rs = meta.getIndexInfo(null, null, name, false, false);
    Index ind = null;
    while (rs.next())
    {
      if (rs.getShort("ORDINAL_POSITION") == 1)
      {
        ind = new Index(rs);
        indices.addElement(ind);
      }
      else
      {
        ind.addColumn(rs);
      }
    }
  }

  public void addImportedKeys(DatabaseMetaData meta) throws SQLException
  {
    ResultSet rs = meta.getImportedKeys(null, null, name);
    while (rs.next())
    {
      impKeys.addElement(new ForeignKey("i", rs));
    }
  }

  public void addExportedKeys(DatabaseMetaData meta) throws SQLException
  {
    ResultSet rs = meta.getExportedKeys(null, null, name);
    while (rs.next())
    {
      expKeys.addElement(new ForeignKey("e", rs));
    }
  }

  public String getName()
  {
    return name;
  }

  public void compare(Table table2)
  {
    compareColumns(table2);
  }

  private void compareColumns(Table table2)
  {
    for (Column col1 : columns)
    {
      Column col2 = table2.getColumn(col1.getName());
      if (col2 == null)
      {
        System.out.println("Spalte " + col1.getName() + " in Tabelle "
            + getName() + " fehlt");
      }
      else
      {
        col1.compare(getName(), col2);
      }
    }
    ArrayList<String> columnnames = table2.getColumnNames();
    for (String cn : columnnames)
    {
      if (this.getColumn(cn) == null)
      {
        System.out.println("Spalte " + cn + " in Tabelle " + getName()
            + " in DB2 zuviel vorhanden");
      }
    }
  }

  public Column getColumn(String name)
  {
    for (Column col : columns)
    {
      if (col.getName().equals(name))
      {
        return col;
      }
    }
    return null;
  }

  public ArrayList<String> getColumnNames()
  {
    ArrayList<String> retval = new ArrayList<String>();
    for (Column col : columns)
    {
      retval.add(col.getName());
    }
    return retval;
  }

  public void generatePDF(Document doc, PdfContentByte cb)
      throws DocumentException
  {
    Paragraph par = new Paragraph(name, new Font(Font.HELVETICA, 16, Font.BOLD));
    PdfDestination dest = new PdfDestination(PdfDestination.FITB);
    new PdfOutline(cb.getRootOutline(), dest, name);
    doc.add(par);
    float[] widths = { 40, 180 };
    PdfPTable pdft = new PdfPTable(widths);
    pdft.setWidthPercentage(100);
    pdft.setSpacingBefore(20);
    pdft.addCell(PDFUtil.getCell("Typ", Color.LIGHT_GRAY, Element.ALIGN_LEFT));
    pdft.addCell(PDFUtil.getCell(this.type, Color.LIGHT_GRAY,
        Element.ALIGN_LEFT));
    pdft.addCell(PDFUtil.getCell("Bemerkung", Color.LIGHT_GRAY,
        Element.ALIGN_LEFT));
    pdft.addCell(PDFUtil.getCell(this.remarks, Color.LIGHT_GRAY,
        Element.ALIGN_LEFT));
    doc.add(pdft);
    doc.add(new Paragraph(" "));

    // Columns
    PdfPTable tab = columns.elementAt(0).generatePDFHeader(doc);
    int row = 0;
    for (int i = 0; i < columns.size(); i++)
    {
      columns.elementAt(i).generatePDF(tab, row);
    }
    doc.add(tab);
    doc.add(new Paragraph(" "));

    // Indices
    if (indices.size() > 0)
    {
      tab = indices.elementAt(0).generatePDFHeader(doc);
      row = 0;
      for (int i = 0; i < indices.size(); i++)
      {
        indices.elementAt(i).generatePDF(tab, row);
      }
      doc.add(tab);
      doc.add(new Paragraph(" "));
    }

    // imported Keys
    if (impKeys.size() > 0)
    {
      tab = impKeys.elementAt(0).generatePDFHeader(doc);
      row = 0;
      for (int i = 0; i < impKeys.size(); i++)
      {
        impKeys.elementAt(i).generatePDF(tab, row);
      }
      doc.add(tab);
      doc.add(new Paragraph(" "));
    }

    // exported Keys
    if (expKeys.size() > 0)
    {
      tab = expKeys.elementAt(0).generatePDFHeader(doc);
      row = 0;
      for (int i = 0; i < expKeys.size(); i++)
      {
        expKeys.elementAt(i).generatePDF(tab, row);
      }
      doc.add(tab);
      doc.add(new Paragraph(" "));
    }
  }

  @Override
  public String toString()
  {
    String ret = "  [Table] Name=" + this.name + ", type=" + type
        + ", remarks=" + remarks + "\n";
    for (int i = 0; i < columns.size(); i++)
    {
      ret += columns.elementAt(i).toString();
    }
    for (int i = 0; i < indices.size(); i++)
    {
      ret += indices.elementAt(i).toString();
    }
    for (int i = 0; i < impKeys.size(); i++)
    {
      ret += impKeys.elementAt(i).toString();
    }
    for (int i = 0; i < expKeys.size(); i++)
    {
      ret += expKeys.elementAt(i).toString();
    }
    return ret;
  }

}
