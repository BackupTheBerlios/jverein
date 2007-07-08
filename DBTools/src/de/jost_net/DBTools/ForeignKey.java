/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/DBTools/src/de/jost_net/DBTools/ForeignKey.java,v $
 * $Revision: 1.1 $
 * $Date: 2007/07/08 09:37:38 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: ForeignKey.java,v $
 * Revision 1.1  2007/07/08 09:37:38  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.DBTools;

import java.awt.Color;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;

import de.jost_net.DBTools.PDFUtil;

/**
 * Repräsentiert einen Foreign Key. Wird sowohl für exportierte als auch für
 * importierte Keys eingesetzt.
 */
public class ForeignKey
{

  /**
   * Imported (i) or Exported (e)
   */
  private String keytyp;

  private String name;

  private String pktablename;

  private String pkcolumnname;

  private String fktablename;

  private String fkcolumnname;

  private int updaterule;

  private int deleterule;

  public ForeignKey(String typ, ResultSet rs) throws SQLException
  {
    this.name = rs.getString("FK_NAME");
    this.pktablename = rs.getString("PKTABLE_NAME");
    this.pkcolumnname = rs.getString("PKCOLUMN_NAME");
    this.fktablename = rs.getString("FKTABLE_NAME");
    this.fkcolumnname = rs.getString("FKCOLUMN_NAME");
    this.deleterule = rs.getInt("DELETE_RULE");
    this.updaterule = rs.getInt("UPDATE_RULE");

    this.keytyp = typ;
  }

  public PdfPTable generatePDFHeader(Document doc) throws DocumentException
  {
    String text = "Foreign Keys";
    if (keytyp.equals("e"))
    {
      text = "Exportierte " + text;
    }
    doc.add(new Paragraph(text));
    float[] widths = { 60, 40, 60, 40, 40, 40 };
    PdfPTable pdft = new PdfPTable(widths);
    pdft.setWidthPercentage(100);
    pdft.setSpacingBefore(15);

    pdft.addCell(PDFUtil
        .getCell("Name", Color.LIGHT_GRAY, Element.ALIGN_CENTER));
    pdft.addCell(PDFUtil.getCell("Spalte", Color.LIGHT_GRAY,
        Element.ALIGN_CENTER));
    pdft.addCell(PDFUtil.getCell("Tabelle", Color.LIGHT_GRAY,
        Element.ALIGN_CENTER));
    pdft.addCell(PDFUtil.getCell("Spalte", Color.LIGHT_GRAY,
        Element.ALIGN_CENTER));
    pdft.addCell(PDFUtil.getCell("Update", Color.LIGHT_GRAY,
        Element.ALIGN_CENTER));
    pdft.addCell(PDFUtil.getCell("Delete", Color.LIGHT_GRAY,
        Element.ALIGN_CENTER));
    pdft.setHeaderRows(1);
    return pdft;
  }

  public void generatePDF(PdfPTable tab, int row)
  {
    row++;
    tab.addCell(PDFUtil.getCell(this.name, PDFUtil.getColor(row),
        Element.ALIGN_LEFT));
    if (this.keytyp.equals("e"))
    {
      tab.addCell(PDFUtil.getCell(this.pkcolumnname, PDFUtil.getColor(row),
          Element.ALIGN_LEFT));
      tab.addCell(PDFUtil.getCell(this.fktablename, PDFUtil.getColor(row),
          Element.ALIGN_LEFT));
      tab.addCell(PDFUtil.getCell(this.fkcolumnname, PDFUtil.getColor(row),
          Element.ALIGN_LEFT));
    }
    else
    {
      tab.addCell(PDFUtil.getCell(this.pkcolumnname, PDFUtil.getColor(row),
          Element.ALIGN_LEFT));
      tab.addCell(PDFUtil.getCell(this.pktablename, PDFUtil.getColor(row),
          Element.ALIGN_LEFT));
      tab.addCell(PDFUtil.getCell(this.pkcolumnname, PDFUtil.getColor(row),
          Element.ALIGN_LEFT));
    }
    tab.addCell(PDFUtil.getCell(getRule(this.updaterule),
        PDFUtil.getColor(row), Element.ALIGN_LEFT));
    tab.addCell(PDFUtil.getCell(getRule(this.deleterule),
        PDFUtil.getColor(row), Element.ALIGN_LEFT));
  }

  @Override
  public String toString()
  {
    String ret = "  [Foreign Key(" + keytyp + ") name=" + name
        + ", pktable_name=" + pktablename + ", pkcolumn_name=" + pkcolumnname
        + ", fktable_name=" + fktablename + ", fkcolumn_name=" + fkcolumnname
        + ", update_rule=" + getRule(updaterule) + ", delete_rule="
        + getRule(deleterule);
    return ret;
  }

  private String getRule(int rule)
  {
    switch (rule)
    {
      case DatabaseMetaData.importedKeyCascade:
        return "cascade";
      case DatabaseMetaData.importedKeyRestrict:
        return "restrict";
      case DatabaseMetaData.importedKeyNoAction:
        return "no action";
      case DatabaseMetaData.importedKeySetNull:
        return "set null";
      case DatabaseMetaData.importedKeySetDefault:
        return "set default";
    }
    return "";
  }

}
