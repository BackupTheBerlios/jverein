/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein.test/src/de/jost_net/JVerein/Test/UseCaseDescription.java,v $
 * $Revision: 1.2 $
 * $Date: 2009/07/19 20:47:48 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: UseCaseDescription.java,v $
 * Revision 1.2  2009/07/19 20:47:48  jost
 * *** empty log message ***
 *
 * Revision 1.1  2009/07/05 13:40:25  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.JVerein.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

public class UseCaseDescription
{
  private String name;

  private String description;

  private String precondition;

  private ArrayList<String> execution;

  private String expectedresult;

  public UseCaseDescription(String name, String description)
  {
    execution = new ArrayList<String>();
    this.name = name;
    this.description = description;
  }

  public void setPreCondition(String precondition)
  {
    this.precondition = precondition;
  }

  public void addExecution(String execution)
  {
    this.execution.add(execution);
  }

  public void setExpectedResult(String expectedresult)
  {
    this.expectedresult = expectedresult;
  }

  public void write(TestDocument doc) throws MalformedURLException,
      DocumentException, IOException
  {
    write(doc, null);
  }

  public void write(TestDocument doc, String imagepath)
      throws DocumentException, MalformedURLException, IOException
  {
    PdfPTable tab = new PdfPTable(new float[] { 5, 19 });
    tab.setWidthPercentage(100);
    tab.addCell("Name");
    tab.addCell(name);
    tab.addCell("Beschreibung");
    tab.addCell(description);
    tab.addCell("Voraussetzung(en)");
    tab.addCell(precondition);
    tab.addCell("Ausführung");
    String s = "";
    for (String exe : execution)
    {
      s += "- " + exe + "\n";
    }
    tab.addCell(s);
    tab.addCell("Erwartetes Ergebnis");
    tab.addCell(expectedresult);
    if (imagepath != null)
    {
      PdfPCell cell1 = new PdfPCell(new Phrase("Hardcopy"));
      cell1.setColspan(2);
      tab.addCell(cell1);
      Image img = Image.getInstance(imagepath);
      img.scalePercent(40.5f);
      PdfPCell cell2 = new PdfPCell(img);
      cell2.setColspan(2);
      tab.addCell(cell2);
    }
    doc.add(tab);
  }
}
