/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/io/JahressaldoPDF.java,v $
 * $Revision: 1.4 $
 * $Date: 2010/10/15 09:58:28 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: JahressaldoPDF.java,v $
 * Revision 1.4  2010/10/15 09:58:28  jost
 * Code aufger�umt
 *
 * Revision 1.3  2009-03-01 18:00:52  jost
 * Zus�tzliche Zeile "�berschuss/Verlust"
 *
 * Revision 1.2  2008/07/10 07:59:21  jost
 * Optimierung der internen Reporter-Klasse
 *
 * Revision 1.1  2008/05/25 19:37:08  jost
 * Neu: Jahressaldo
 *
 **********************************************************************/
package de.jost_net.JVerein.io;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;

import de.jost_net.JVerein.util.Geschaeftsjahr;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.internal.action.Program;
import de.willuhn.jameica.messaging.StatusBarMessage;
import de.willuhn.jameica.system.Application;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;
import de.willuhn.util.ProgressMonitor;

public class JahressaldoPDF
{

  public JahressaldoPDF(ArrayList<SaldoZeile> zeile, final File file,
      ProgressMonitor monitor, Geschaeftsjahr gj) throws ApplicationException
  {
    try
    {
      FileOutputStream fos = new FileOutputStream(file);
      String subtitle = gj.toString();
      Reporter reporter = new Reporter(fos, monitor, "Jahressaldo", subtitle,
          zeile.size());

      reporter.addHeaderColumn("Konto-\nnummer", Element.ALIGN_CENTER, 50,
          Color.LIGHT_GRAY);
      reporter.addHeaderColumn("Kontobezeichnung", Element.ALIGN_CENTER, 90,
          Color.LIGHT_GRAY);
      reporter.addHeaderColumn("Anfangs-\nbestand", Element.ALIGN_CENTER, 45,
          Color.LIGHT_GRAY);
      reporter.addHeaderColumn("Einnahmen", Element.ALIGN_CENTER, 45,
          Color.LIGHT_GRAY);
      reporter.addHeaderColumn("Ausgaben", Element.ALIGN_CENTER, 45,
          Color.LIGHT_GRAY);
      reporter.addHeaderColumn("Um-\nbuchungen", Element.ALIGN_CENTER, 45,
          Color.LIGHT_GRAY);
      reporter.addHeaderColumn("Endbestand", Element.ALIGN_CENTER, 45,
          Color.LIGHT_GRAY);
      reporter.addHeaderColumn("Bemerkung", Element.ALIGN_CENTER, 100,
          Color.LIGHT_GRAY);
      reporter.createHeader();

      for (SaldoZeile sz : zeile)
      {
        reporter.addColumn((String) sz.getAttribute("kontonummer"),
            Element.ALIGN_LEFT);
        reporter.addColumn((String) sz.getAttribute("kontobezeichnung"),
            Element.ALIGN_LEFT);
        reporter.addColumn((Double) sz.getAttribute("anfangsbestand"));
        reporter.addColumn((Double) sz.getAttribute("einnahmen"));
        reporter.addColumn((Double) sz.getAttribute("ausgaben"));
        reporter.addColumn((Double) sz.getAttribute("umbuchungen"));
        reporter.addColumn((Double) sz.getAttribute("endbestand"));
        reporter.addColumn((String) sz.getAttribute("bemerkung"),
            Element.ALIGN_LEFT);
      }
      reporter.closeTable();
      monitor.setStatusText("Auswertung fertig.");

      reporter.close();
      fos.close();
      GUI.getDisplay().asyncExec(new Runnable()
      {

        public void run()
        {
          try
          {
            new Program().handleAction(file);
          }
          catch (ApplicationException ae)
          {
            Application.getMessagingFactory().sendMessage(
                new StatusBarMessage(ae.getLocalizedMessage(),
                    StatusBarMessage.TYPE_ERROR));
          }
        }
      });
    }
    catch (DocumentException e)
    {
      e.printStackTrace();
      Logger.error("error while creating report", e);
      throw new ApplicationException("Fehler beim Erzeugen des Reports", e);
    }
    catch (FileNotFoundException e)
    {
      Logger.error("error while creating report", e);
      throw new ApplicationException("Fehler beim Erzeugen des Reports", e);
    }
    catch (IOException e)
    {
      Logger.error("error while creating report", e);
      throw new ApplicationException("Fehler beim Erzeugen des Reports", e);
    }

  }

}
