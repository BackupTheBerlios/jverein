/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/DBTools/src/de/jost_net/DBTools/Column.java,v $
 * $Revision: 1.1 $
 * $Date: 2007/07/08 09:37:38 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: Column.java,v $
 * Revision 1.1  2007/07/08 09:37:38  jost
 * *** empty log message ***
 *
 **********************************************************************/

package de.jost_net.DBTools;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;

import de.jost_net.DBTools.PDFUtil;

/**
 * Repräsentiert eine Spalte in einer Tabelle
 */
public class Column
{

  private String name;

  private String type;

  private int size;

  private String decimaldigits;

  private String nullable;

  private String remarks;

  public Column(ResultSet rs) throws SQLException
  {
    this.name = rs.getString("COLUMN_NAME");
    this.type = rs.getString("TYPE_NAME");
    this.size = rs.getInt("COLUMN_SIZE");
    this.decimaldigits = rs.getString("DECIMAL_DIGITS");
    this.nullable = rs.getString("IS_NULLABLE");
    this.remarks = rs.getString("REMARKS");
  }

  public String getName()
  {
    return name;
  }

  public String getDatatype()
  {
    return type;
  }

  public int getSize()
  {
    return size;
  }

  public String getNullable()
  {
    return nullable;
  }

  public void compare(String tablename, Column col)
  {
    if (!getDatatype().equals(col.getDatatype()))
    {
      System.out.println("Spalte " + col.getName() + " in Tabelle " + tablename
          + ": Datentyp differiert (" + getDatatype() + "/" + col.getDatatype()
          + ")");
    }
    if (getSize() != col.getSize())
    {
      System.out.println("Spalte " + getName() + " in Tabelle " + tablename
          + ": Länge differiert (" + getSize() + "/" + col.getSize() + ")");
    }
    if (!getNullable().equals(col.getNullable()))
    {
      System.out.println("Spalte " + getName() + " in Tabelle " + getName()
          + ": Null-Werte differieren (" + getNullable() + "/"
          + col.getNullable() + ")");
    }
  }

  public PdfPTable generatePDFHeader(Document doc) throws DocumentException
  {
    doc.add(new Paragraph("Spalten"));
    float[] widths = { 90, 40, 40, 30, 30, 60 };
    PdfPTable pdft = new PdfPTable(widths);
    pdft.setWidthPercentage(100);
    pdft.setSpacingBefore(15);

    pdft.addCell(PDFUtil.getCell("Name", Color.LIGHT_GRAY, Element.ALIGN_LEFT));
    pdft.addCell(PDFUtil.getCell("Typ", Color.LIGHT_GRAY, Element.ALIGN_LEFT));
    pdft.addCell(PDFUtil
        .getCell("Größe", Color.LIGHT_GRAY, Element.ALIGN_RIGHT));
    pdft.addCell(PDFUtil.getCell("Dezimal-Stellen", Color.LIGHT_GRAY,
        Element.ALIGN_CENTER));
    pdft.addCell(PDFUtil
        .getCell("Null", Color.LIGHT_GRAY, Element.ALIGN_CENTER));
    pdft.addCell(PDFUtil.getCell("Bemerkung", Color.LIGHT_GRAY,
        Element.ALIGN_CENTER));
    pdft.setHeaderRows(1);
    return pdft;
  }

  public void generatePDF(PdfPTable tab, int row)
  {
    row++;
    tab.addCell(PDFUtil.getCell(this.name, PDFUtil.getColor(row),
        Element.ALIGN_LEFT));
    tab.addCell(PDFUtil.getCell(this.type, PDFUtil.getColor(row),
        Element.ALIGN_LEFT));
    tab.addCell(PDFUtil.getCell(this.size + "", PDFUtil.getColor(row),
        Element.ALIGN_RIGHT));
    tab.addCell(PDFUtil.getCell(this.decimaldigits, PDFUtil.getColor(row),
        Element.ALIGN_CENTER));
    tab.addCell(PDFUtil.getCell(this.nullable, PDFUtil.getColor(row),
        Element.ALIGN_CENTER));
    tab.addCell(PDFUtil.getCell(this.remarks, PDFUtil.getColor(row),
        Element.ALIGN_LEFT));
  }

  @Override
  public String toString()
  {
    String ret = "    [Column] name=" + name + ", type=" + type + ", size="
        + size + ",decimals=" + decimaldigits + ", nullable=" + nullable + "\n";
    return ret;
  }
}
