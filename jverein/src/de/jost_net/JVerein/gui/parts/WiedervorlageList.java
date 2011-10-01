/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/parts/WiedervorlageList.java,v $
 * $Revision: 1.6 $
 * $Date: 2011/10/01 21:44:54 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/
package de.jost_net.JVerein.gui.parts;

import java.rmi.RemoteException;

import org.eclipse.swt.widgets.Composite;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.WiedervorlageAction;
import de.jost_net.JVerein.gui.menu.WiedervorlageMenu;
import de.jost_net.JVerein.rmi.Mitglied;
import de.jost_net.JVerein.rmi.Wiedervorlage;
import de.jost_net.JVerein.util.JVDateFormatTTMMJJJJ;
import de.willuhn.datasource.rmi.DBIterator;
import de.willuhn.datasource.rmi.DBService;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.Part;
import de.willuhn.jameica.gui.formatter.DateFormatter;
import de.willuhn.jameica.gui.formatter.Formatter;
import de.willuhn.jameica.gui.parts.TablePart;

public class WiedervorlageList extends TablePart implements Part
{
  private TablePart wiedervorlageList;

  public WiedervorlageList(Action action)
  {
    super(action);
  }

  public Part getWiedervorlageList() throws RemoteException
  {
    DBService service = Einstellungen.getDBService();
    DBIterator wiedervorlagen = service.createList(Wiedervorlage.class);
    wiedervorlagen.setOrder("ORDER BY datum DESC");

    if (wiedervorlageList == null)
    {
      wiedervorlageList = new TablePart(wiedervorlagen,
          new WiedervorlageAction(null));
      wiedervorlageList.addColumn(JVereinPlugin.getI18n().tr("Name"),
          "mitglied", new Formatter()
          {
            public String format(Object o)
            {
              Mitglied m = (Mitglied) o;
              if (m == null)
                return null;
              String name = null;
              try
              {
                name = m.getNameVorname();
              }
              catch (RemoteException e)
              {
                e.printStackTrace();
              }
              return name;
            }
          });
      wiedervorlageList.addColumn(JVereinPlugin.getI18n().tr("Datum"), "datum",
          new DateFormatter(new JVDateFormatTTMMJJJJ()));
      wiedervorlageList.addColumn(JVereinPlugin.getI18n().tr("Vermerk"),
          "vermerk");
      wiedervorlageList.addColumn(JVereinPlugin.getI18n().tr("Erledigung"),
          "erledigung", new DateFormatter(new JVDateFormatTTMMJJJJ()));
      wiedervorlageList
          .setContextMenu(new WiedervorlageMenu(wiedervorlageList));
      wiedervorlageList.setRememberColWidths(true);
      wiedervorlageList.setRememberOrder(true);
      wiedervorlageList.setSummary(true);
    }
    else
    {
      wiedervorlageList.removeAll();
      while (wiedervorlagen.hasNext())
      {
        wiedervorlageList.addItem(wiedervorlagen.next());
      }
    }
    return wiedervorlageList;
  }

  @Override
  public synchronized void paint(Composite parent) throws RemoteException
  {
    super.paint(parent);
  }

}
