/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein.test/src/de/jost_net/JVerein/Test/TestDocument.java,v $
 * $Revision: 1.3 $
 * $Date: 2009/07/19 20:47:48 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: TestDocument.java,v $
 * Revision 1.3  2009/07/19 20:47:48  jost
 * *** empty log message ***
 *
 * Revision 1.2  2009/07/06 07:21:02  jost
 * *** empty log message ***
 *
 * Revision 1.1  2009/07/05 13:40:25  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.JVerein.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfWriter;

import de.jost_net.JVerein.Einstellungen;
import de.willuhn.logging.Logger;

public class TestDocument
{
  private Document doc = null;

  public TestDocument(String path)
  {
    doc = new Document();
    try
    {
      PdfWriter.getInstance(doc, new FileOutputStream(path));
      doc.open();
      Font font1 = FontFactory.getFont(FontFactory.HELVETICA, 20, Font.BOLD);
      Font font2 = FontFactory.getFont(FontFactory.HELVETICA, 15, Font.BOLD);
      Paragraph p1 = new Paragraph("\n\n\n\n\n\n\n\n\n\nJVerein-Testprotokoll",
          font1);
      p1.setAlignment(Element.ALIGN_CENTER);
      doc.add(p1);
      String datum = Einstellungen.TIMESTAMPFORMAT.format(new Date());
      Paragraph p2 = new Paragraph("vom " + datum, font2);
      p2.setAlignment(Element.ALIGN_CENTER);
      doc.add(p2);
      HeaderFooter hf = new HeaderFooter(new Phrase(
          "JVerein-Test                                    " + datum
              + "                        Seite: "), true);
      doc.setFooter(hf);
      doc.newPage();
    }
    catch (FileNotFoundException e)
    {
      Logger.error("File not found", e);
    }
    catch (DocumentException e)
    {
      Logger.error("Fehler im Dokument: ", e);
    }
  }

  public void add(Element e) throws DocumentException
  {
    doc.add(e);
  }

  public void newPage() throws DocumentException
  {
    doc.newPage();
  }

  public void close()
  {
    doc.close();
  }

}
