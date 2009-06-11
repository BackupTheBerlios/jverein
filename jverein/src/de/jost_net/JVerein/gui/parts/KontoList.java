/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/parts/KontoList.java,v $
 * $Revision: 1.2 $
 * $Date: 2009/06/11 21:03:24 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * basiert auf KontoList aus Hibiscus
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: KontoList.java,v $
 * Revision 1.2  2009/06/11 21:03:24  jost
 * Vorbereitung I18N
 *
 * Revision 1.1  2008/05/22 06:51:59  jost
 * Buchführung
 *
 **********************************************************************/

package de.jost_net.JVerein.gui.parts;

import java.rmi.RemoteException;

import org.eclipse.swt.widgets.Composite;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.rmi.Konto;
import de.willuhn.datasource.GenericIterator;
import de.willuhn.datasource.rmi.DBIterator;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.Part;
import de.willuhn.jameica.gui.parts.TablePart;

/**
 * Implementierung einer fix und fertig vorkonfigurierten Liste aller Konten.
 */
public class KontoList extends TablePart implements Part
{
  public KontoList(Action action) throws RemoteException
  {
    this(init(), action);
  }

  public KontoList(GenericIterator konten, Action action)
  {
    super(konten, action);

    addColumn(JVereinPlugin.getI18n().tr("Kontonummer"), "nummer");
    addColumn(JVereinPlugin.getI18n().tr("Bezeichnung"), "bezeichnung");
    setRememberOrder(true);
    setRememberColWidths(true);

  }

  /**
   * @see de.willuhn.jameica.gui.Part#paint(org.eclipse.swt.widgets.Composite)
   */
  public synchronized void paint(Composite parent) throws RemoteException
  {
    super.paint(parent);
  }

  /**
   * Initialisiert die Konten-Liste.
   * 
   * @return Liste der Konten.
   * @throws RemoteException
   */
  private static DBIterator init() throws RemoteException
  {
    DBIterator i = Einstellungen.getDBService().createList(Konto.class);
    i.setOrder("ORDER BY nummer, bezeichnung");
    return i;
  }
}
