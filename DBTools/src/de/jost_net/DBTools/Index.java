/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/DBTools/src/de/jost_net/DBTools/Index.java,v $
 * $Revision: 1.1 $
 * $Date: 2007/07/08 09:37:38 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: Index.java,v $
 * Revision 1.1  2007/07/08 09:37:38  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.DBTools;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;

import de.jost_net.DBTools.PDFUtil;

/**
 * Repräsentiert einen Index
 * 
 */
public class Index
{

  private String name;

  private Vector<String> columnnames;

  private String sort;

  public Index(ResultSet rs) throws SQLException
  {
    this.name = rs.getString("INDEX_NAME");
    columnnames = new Vector<String>();
    columnnames.addElement(rs.getString("COLUMN_NAME"));
    this.sort = rs.getString("ASC_OR_DESC");
  }

  public void addColumn(ResultSet rs) throws SQLException
  {
    columnnames.addElement(rs.getString("COLUMN_NAME"));
  }

  public PdfPTable generatePDFHeader(Document doc) throws DocumentException
  {
    doc.add(new Paragraph("Indices"));
    float[] widths = { 90, 90, 30 };
    PdfPTable pdft = new PdfPTable(widths);
    pdft.setSpacingBefore(15);
    pdft.setWidthPercentage(100);

    pdft.addCell(PDFUtil.getCell("Name", Color.LIGHT_GRAY, Element.ALIGN_LEFT));
    pdft.addCell(PDFUtil.getCell("Spalte(n)", Color.LIGHT_GRAY,
        Element.ALIGN_LEFT));
    pdft.addCell(PDFUtil.getCell("Sortierung", Color.LIGHT_GRAY,
        Element.ALIGN_RIGHT));
    pdft.setHeaderRows(1);
    return pdft;
  }

  public void generatePDF(PdfPTable tab, int row)
  {
    row++;
    tab.addCell(PDFUtil.getCell(this.name, PDFUtil.getColor(row),
        Element.ALIGN_LEFT));
    String spalten = "";
    for (int i = 0; i < columnnames.size(); i++)
    {
      spalten += columnnames.elementAt(i) + "\n";
    }
    spalten = spalten.substring(0, spalten.length() - 1);
    tab.addCell(PDFUtil.getCell(spalten, PDFUtil.getColor(row),
        Element.ALIGN_LEFT));
    tab.addCell(PDFUtil.getCell(this.sort, PDFUtil.getColor(row),
        Element.ALIGN_CENTER));
  }

  @Override
  public String toString()
  {
    String ret = "  [index] name=" + name + ", sort=" + sort + ", columns=(";
    for (int i = 0; i < columnnames.size(); i++)
    {
      ret += columnnames.elementAt(i) + ",";
    }
    ret += ")\n";
    return ret;
  }

}
