/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/DBTools/src/de/jost_net/DBTools/PDFUtil.java,v $
 * $Revision: 1.1 $
 * $Date: 2007/07/08 09:37:38 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: PDFUtil.java,v $
 * Revision 1.1  2007/07/08 09:37:38  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.DBTools;

import java.awt.Color;

import com.lowagie.text.Chunk;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;

/**
 * Hilfsmethoden für die PDF-Generierung
 */
public class PDFUtil
{

  public static PdfPCell getCell(String text, Color farbe, int ausrichtung)
  {
    PdfPCell c = new PdfPCell(new Phrase(new Chunk(text, new Font(
        Font.HELVETICA, 8))));
    c.setBackgroundColor(farbe);
    c.setHorizontalAlignment(ausrichtung);
    c.setBorderColor(Color.WHITE);
    return c;
  }

  public static Color getColor(int row)
  {
    Color co = null;
    if (row % 2 == 0)
    {
      co = new Color(240, 240, 240);
    }
    else
    {
      co = new Color(220, 220, 220);
    }
    return co;
  }

}
