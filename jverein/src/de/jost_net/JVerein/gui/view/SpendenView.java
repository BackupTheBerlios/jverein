/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/SpendenView.java,v $
 * $Revision: 1.2 $
 * $Date: 2011/10/01 21:46:34 $
 * $Author: jost $
 * Kopiert aus Hibiscus DonateView
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/

package de.jost_net.JVerein.gui.view;

import java.util.Calendar;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import de.jost_net.JVerein.JVereinPlugin;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.parts.ButtonArea;
import de.willuhn.jameica.gui.util.Container;
import de.willuhn.jameica.gui.util.SWTUtil;
import de.willuhn.jameica.gui.util.SimpleContainer;
import de.willuhn.jameica.hbci.Settings;
import de.willuhn.jameica.hbci.rmi.Dauerauftrag;
import de.willuhn.jameica.hbci.rmi.Turnus;
import de.willuhn.jameica.hbci.rmi.Ueberweisung;
import de.willuhn.jameica.messaging.StatusBarMessage;
import de.willuhn.jameica.system.Application;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;
import de.willuhn.util.I18N;

/**
 * View fuer den Spenden-Aufruf.
 */
public class SpendenView extends AbstractView
{
  private final static I18N i18n = JVereinPlugin.getI18n();

  /**
   * @see de.willuhn.jameica.gui.AbstractView#bind()
   */
  public void bind() throws Exception
  {
    GUI.getView().setTitle(i18n.tr("Spenden f�r JVerein"));

    {
      Composite comp = new Composite(this.getParent(), SWT.NONE);
      comp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
      comp.setLayout(SWTUtil.createGrid(2, false));

      Container container = new SimpleContainer(comp);
      container.addHeadline(i18n.tr("Warum eigentlich?") + "  ");
      container
          .addText(
              i18n.tr("JVerein wird in meiner Freizeit entwickelt. Urspr�nglich habe ich es entwickelt, da mir bei der "
                  + "�bernahme meiner T�tigkeit als Kassierer eines Vereins keine geeignete Software zur Verf�gung stand, die unter "
                  + "LINUX ablauff�hig ist. Sp�ter habe ich mich entschlossen JVerein als OpenSource zur Verf�gung zu stellen. "
                  + "Inzwischen nutzen ca. 500-600 Vereine (gesch�tzt aufgrund der Downloadzahlen und der Zuschriften) JVerein. "
                  + "Es sind jede Menge Funktionalit�ten zus�tzlich entwickelt worden. Benutzer sind der Meinung, dass JVerein "
                  + "den Vergleich mit professionellen L�sungen f�r viel Geld nicht scheuen muss. "
                  + "\n\nZur Deckung der Kosten f�r Web-Server und Hardware, bitte ich um einen kleinen Beitrag. Der kann entweder "
                  + "einmalig oder laufend geleistet werden. Nicht vergessen: Auftrag in Hibiscus abschicken."
                  + "\n\nVielen Dank!\n\nHeiner Jostkleigrewe"), true);

      Canvas c = SWTUtil.getCanvas(comp, SWTUtil.getImage("JVerein.png"),
          SWT.TOP | SWT.LEFT);
      ((GridData) c.getLayoutData()).minimumWidth = 340;
    }

    {
      final char[] kto = new char[] { '5', '6', '9', '1', '5', '3', '0', '0',
          '0' };
      final char[] blz = new char[] { '2', '0', '0', '4', '1', '1', '1', '1' };
      final String name = "Heiner Jostkleigrewe";

      ButtonArea buttons = new ButtonArea();
      buttons.addButton(i18n.tr("Dauerauftrag erstellen"), new Action()
      {
        public void handleAction(Object context) throws ApplicationException
        {
          try
          {
            Dauerauftrag d = (Dauerauftrag) Settings.getDBService()
                .createObject(Dauerauftrag.class, null);
            d.setGegenkontoBLZ(new String(blz));
            d.setGegenkontoNummer(new String(kto));
            d.setGegenkontoName(name);
            d.setZweck("Beitrag Weitereintwicklung");
            d.setZweck2("JVerein");
            // Wir lassen 7 Tage Vorlauf
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, 7);
            d.setErsteZahlung(cal.getTime());
            Turnus turnus = (Turnus) Settings.getDBService().createObject(
                Turnus.class, null);
            turnus.setIntervall(1);
            turnus.setTag(cal.get(Calendar.DAY_OF_MONTH));
            turnus.setZeiteinheit(Turnus.ZEITEINHEIT_MONATLICH);
            d.setTurnus(turnus);
            new de.willuhn.jameica.hbci.gui.action.DauerauftragNew()
                .handleAction(d);
          }
          catch (Exception e)
          {
            Logger.error("unable to create dauerauftrag", e);
            Application.getMessagingFactory().sendMessage(
                new StatusBarMessage(i18n.tr(
                    "Fehler beim Anlegen des Dauerauftrages: {0}",
                    e.getMessage()), StatusBarMessage.TYPE_ERROR));
          }
        }
      }, null, false, "emblem-special.png");
      buttons.addButton(i18n.tr("...oder Einzelspende"), new Action()
      {
        public void handleAction(Object context) throws ApplicationException
        {
          try
          {
            Ueberweisung u = (Ueberweisung) Settings.getDBService()
                .createObject(Ueberweisung.class, null);
            u.setGegenkontoBLZ(new String(blz));
            u.setGegenkontoNummer(new String(kto));
            u.setGegenkontoName(name);
            u.setZweck("Beitrag Weitereintwicklung");
            u.setZweck2("JVerein");
            new de.willuhn.jameica.hbci.gui.action.UeberweisungNew()
                .handleAction(u);
          }
          catch (Exception e)
          {
            Logger.error("unable to create ueberweisung", e);
            Application.getMessagingFactory().sendMessage(
                new StatusBarMessage(
                    i18n.tr("Fehler beim Anlegen der �berweisung: {0}",
                        e.getMessage()), StatusBarMessage.TYPE_ERROR));
          }
        }
      }, null, false, "stock_next.png");
      buttons.paint(getParent());
    }

  }

}
