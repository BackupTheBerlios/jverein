/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/io/Reporter.java,v $
 * $Revision: 1.20 $
 * $Date: 2011/10/01 21:47:50 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/

package de.jost_net.JVerein.io;

import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.HyphenationAuto;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.util.JVDateFormatTTMMJJJJ;
import de.willuhn.jameica.plugin.AbstractPlugin;
import de.willuhn.jameica.system.Application;
import de.willuhn.util.ProgressMonitor;

/**
 * Kapselt den Export von Daten im PDF-Format.
 */
public class Reporter
{

  // private I18N i18n = null;

  private ArrayList<PdfPCell> headers;

  private ArrayList<Integer> widths;

  private OutputStream out;

  private Document rpt;

  private PdfPTable table;

  private int maxRecords;

  private int currRecord = 0;

  private ProgressMonitor monitor;

  private HyphenationAuto hyph;

  public Reporter(OutputStream out, ProgressMonitor monitor, String title,
      String subtitle, int maxRecords) throws DocumentException
  {
    this(out, monitor, title, subtitle, maxRecords, 80, 30, 20, 20);
  }

  public Reporter(OutputStream out, float linkerRand, float rechterRand,
      float obererRand, float untererRand) throws DocumentException
  {
    this.out = out;
    rpt = new Document();
    rpt.setMargins(linkerRand, rechterRand, obererRand, untererRand);
    hyph = new HyphenationAuto("de", "DE", 2, 2);
    PdfWriter.getInstance(rpt, out);
    AbstractPlugin plugin = Application.getPluginLoader().getPlugin(
        JVereinPlugin.class);
    rpt.addAuthor(plugin.getManifest().getName() + " - Version "
        + plugin.getManifest().getVersion());
    rpt.open();
    headers = new ArrayList<PdfPCell>();
    widths = new ArrayList<Integer>();
  }

  public Reporter(OutputStream out, ProgressMonitor monitor, String title,
      String subtitle, int maxRecords, float linkerRand, float rechterRand,
      float obererRand, float untererRand) throws DocumentException
  {
    // this.i18n = Application.getPluginLoader().getPlugin(JVereinPlugin.class)
    // .getResources().getI18N();
    this.out = out;
    this.monitor = monitor;
    rpt = new Document();
    hyph = new HyphenationAuto("de", "DE", 2, 2);
    PdfWriter.getInstance(rpt, out);
    rpt.setMargins(linkerRand, rechterRand, obererRand, untererRand);
    if (this.monitor != null)
    {
      this.monitor.setStatusText("Erzeuge Liste");
      this.monitor.addPercentComplete(1);
    }
    AbstractPlugin plugin = Application.getPluginLoader().getPlugin(
        JVereinPlugin.class);
    rpt.addAuthor(plugin.getManifest().getName() + " - Version "
        + plugin.getManifest().getVersion());
    rpt.addTitle(subtitle);

    Chunk fuss = new Chunk(title + " | " + subtitle + " | erstellt am "
        + new JVDateFormatTTMMJJJJ().format(new Date()) + "     Seite: ",
        FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD));
    HeaderFooter hf = new HeaderFooter(new Phrase(fuss), true);
    hf.setAlignment(Element.ALIGN_CENTER);
    rpt.setFooter(hf);

    rpt.open();

    if (title.length() > 0)
    {
      Paragraph pTitle = new Paragraph(title, FontFactory.getFont(
          FontFactory.HELVETICA_BOLD, 13));
      pTitle.setAlignment(Element.ALIGN_CENTER);
      rpt.add(pTitle);

      Paragraph psubTitle = new Paragraph(subtitle, FontFactory.getFont(
          FontFactory.HELVETICA_BOLD, 10));
      psubTitle.setAlignment(Element.ALIGN_CENTER);
      rpt.add(psubTitle);
    }
    headers = new ArrayList<PdfPCell>();
    widths = new ArrayList<Integer>();

    monitor.setPercentComplete(0);
    this.maxRecords = maxRecords;
  }

  /**
   * Fuegt einen neuen Absatz hinzu.
   * 
   * @param p
   * @throws DocumentException
   */
  public void add(Paragraph p) throws DocumentException
  {
    rpt.add(p);
  }

  public void add(String text, int size) throws DocumentException
  {
    Paragraph p = new Paragraph(text, FontFactory.getFont(
        FontFactory.HELVETICA_BOLD, size));
    p.setAlignment(Element.ALIGN_LEFT);
    rpt.add(p);

  }

  /**
   * Fuegt der Tabelle einen neuen Spaltenkopf hinzu.
   * 
   * @param text
   * @param align
   * @param width
   * @param color
   */
  public void addHeaderColumn(String text, int align, int width, Color color)
  {
    headers.add(getDetailCell(text, align, color));
    widths.add(Integer.valueOf(width));
  }

  /**
   * Fuegt eine neue Zelle zur Tabelle hinzu.
   * 
   * @param cell
   */
  public void addColumn(PdfPCell cell)
  {
    table.addCell(cell);
  }

  public void addColumn(byte[] image, int width, int height,
      int horizontalalignment) throws BadElementException,
      MalformedURLException, IOException
  {
    Image i = Image.getInstance(image);
    float w = i.width() / width;
    float h = i.height() / height;
    if (w > h)
    {
      h = i.height() / w;
      w = width;
    }
    else
    {
      w = i.width() / h;
      h = height;
    }
    i.scaleToFit(w, h);
    PdfPCell cell = new PdfPCell(i, false);
    cell.setPadding(3);
    cell.setHorizontalAlignment(horizontalalignment);
    table.addCell(cell);
  }

  /**
   * Fuegt eine neue Zelle zur Tabelle hinzu.
   */
  public void addColumn(String text, int align, Color backgroundcolor)
  {
    addColumn(getDetailCell(text, align, backgroundcolor));
  }

  /**
   * Fuegt eine neue Zelle mit einem boolean-Value zur Tabelle hinzu
   */
  public void addColumn(boolean value)
  {
    addColumn(value ? "X" : "", Element.ALIGN_CENTER, Color.WHITE);
  }

  /**
   * Fuegt eine neue Zelle zur Tabelle hinzu.
   */
  public void addColumn(String text, int align)
  {
    addColumn(getDetailCell(text, align, Color.WHITE));
  }

  public void addColumn(String text, int align, int colspan)
  {
    addColumn(getDetailCell(text, align, Color.WHITE, colspan));
  }

  public void addColumn(String text, int align, Color backgroundcolor,
      int colspan)
  {
    addColumn(getDetailCell(text, align, backgroundcolor, colspan));
  }

  /**
   * Fuegt eine neue Zelle zur Tabelle hinzu.
   */
  public void addColumn(Double value)
  {
    if (value != null)
    {
      addColumn(getDetailCell(value.doubleValue()));
    }
    else
    {
      addColumn(getDetailCell("", Element.ALIGN_LEFT));
    }
  }

  /**
   * Fuegt eine neue Zelle zur Tabelle hinzu.
   */
  public void addColumn(double value)
  {
    addColumn(getDetailCell(value));
  }

  /**
   * Fuegt eine neue Zelle zur Tabelle hinzu.
   */
  public void addColumn(Date value, int align)
  {
    if (value != null)
    {
      addColumn(getDetailCell(value, align));
    }
    else
    {
      addColumn("", Element.ALIGN_LEFT);
    }
  }

  /**
   * Rueckt den Monitor weiter.
   */
  public void setNextRecord()
  {
    currRecord++;
    if (monitor != null)
      monitor.setPercentComplete(currRecord / maxRecords * 100);
  }

  /**
   * Erzeugt den Tabellen-Header mit 100 % Breite.
   * 
   * @throws DocumentException
   */
  public void createHeader() throws DocumentException
  {
    createHeader(100f, Element.ALIGN_LEFT);
  }

  /**
   * Erzeugt den Tabellen-Header.
   * 
   * @param tabellenbreiteinprozent
   *          Breite der Tabelle in Prozent
   * @param alignment
   *          Horizontale Ausrichtung der Tabelle (siehe com.lowagie.Element.)
   * @throws DocumentException
   */
  public void createHeader(float tabellenbreiteinprozent, int alignment)
      throws DocumentException
  {
    table = new PdfPTable(headers.size());
    table.setWidthPercentage(tabellenbreiteinprozent);
    table.setHorizontalAlignment(alignment);
    float[] w = new float[headers.size()];
    for (int i = 0; i < headers.size(); i++)
    {
      Integer breite = widths.get(i);
      w[i] = breite.intValue();
    }
    table.setWidths(w);
    table.setSpacingBefore(10);
    table.setSpacingAfter(0);
    for (int i = 0; i < headers.size(); i++)
    {
      PdfPCell cell = headers.get(i);
      table.addCell(cell);
    }
    table.setHeaderRows(1);
  }

  public void newPage()
  {
    rpt.newPage();
  }

  public void closeTable() throws DocumentException
  {
    if (table == null)
    {
      return;
    }
    rpt.add(table);
    table = null;
    headers = new ArrayList<PdfPCell>();
    widths = new ArrayList<Integer>();
  }

  /**
   * Schliesst den Report.
   * 
   * @throws IOException
   * @throws DocumentException
   */
  public void close() throws IOException, DocumentException
  {
    try
    {
      if (monitor != null)
      {
        monitor.setPercentComplete(100);
        monitor.setStatusText("PDF-Export beendet");
      }
      if (table != null)
      {
        rpt.add(table);
      }
      rpt.close();
    }
    finally
    {
      // Es muss sichergestellt sein, dass der OutputStream
      // immer geschlossen wird
      out.close();
    }
  }

  /**
   * Erzeugt eine Zelle der Tabelle.
   * 
   * @param text
   *          der anzuzeigende Text.
   * @param align
   *          die Ausrichtung.
   * @param backgroundcolor
   *          die Hintergundfarbe.
   * @return die erzeugte Zelle.
   */
  private PdfPCell getDetailCell(String text, int align, Color backgroundcolor)
  {
    PdfPCell cell = new PdfPCell(new Phrase(new Chunk(notNull(text),
        FontFactory.getFont(FontFactory.HELVETICA, 8)).setHyphenation(hyph)));
    cell.setHorizontalAlignment(align);
    cell.setBackgroundColor(backgroundcolor);
    return cell;
  }

  private PdfPCell getDetailCell(String text, int align, Color backgroundcolor,
      int colspan)
  {
    PdfPCell cell = new PdfPCell(new Phrase(new Chunk(notNull(text),
        FontFactory.getFont(FontFactory.HELVETICA, 8)).setHyphenation(hyph)));
    cell.setHorizontalAlignment(align);
    cell.setBackgroundColor(backgroundcolor);
    cell.setColspan(colspan);
    return cell;

  }

  /**
   * Erzeugt eine Zelle der Tabelle.
   * 
   * @param text
   *          der anzuzeigende Text.
   * @param align
   *          die Ausrichtung.
   * @return die erzeugte Zelle.
   */
  private PdfPCell getDetailCell(String text, int align)
  {
    return getDetailCell(text, align, Color.WHITE);
  }

  /**
   * Erzeugt eine Zelle fuer die uebergebene Zahl.
   * 
   * @param value
   *          die Zahl.
   * @return die erzeugte Zelle.
   */
  private PdfPCell getDetailCell(double value)
  {
    Font f = null;
    if (value >= 0)
    {
      f = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL,
          Color.BLACK);
    }
    else
    {
      f = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL, Color.RED);
    }
    PdfPCell cell = new PdfPCell(new Phrase(
        Einstellungen.DECIMALFORMAT.format(value), f));
    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
    return cell;
  }

  /**
   * Erzeugt eine Zelle fuer das uebergebene Datum.
   * 
   * @param value
   *          das Datum.
   * @return die erzeugte Zelle.
   */
  private PdfPCell getDetailCell(Date value, int align)
  {
    if (value.equals(Einstellungen.NODATE))
    {
      return getDetailCell("", Element.ALIGN_LEFT);
    }
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    return getDetailCell(sdf.format(value), align);
  }

  /**
   * Gibt einen Leerstring aus, falls der Text null ist.
   * 
   * @param text
   *          der Text.
   * @return der Text oder Leerstring - niemals null.
   */
  public String notNull(String text)
  {
    return text == null ? "" : text;
  }
}
