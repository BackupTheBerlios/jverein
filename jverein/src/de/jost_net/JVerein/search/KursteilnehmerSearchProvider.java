/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/search/KursteilnehmerSearchProvider.java,v $
 * $Revision: 1.6 $
 * $Date: 2011/08/05 14:53:11 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: KursteilnehmerSearchProvider.java,v $
 * Revision 1.6  2011/08/05 14:53:11  jost
 * Warnings entfernt.
 *
 * Revision 1.5  2011-02-12 09:42:02  jost
 * Statische Codeanalyse mit Findbugs
 *
 * Revision 1.4  2010-10-15 09:58:30  jost
 * Code aufger�umt
 *
 * Revision 1.3  2009-06-11 21:04:24  jost
 * Vorbereitung I18N
 *
 * Revision 1.2  2008/10/01 14:17:57  jost
 * Warnungen entfernt
 *
 * Revision 1.1  2008/09/04 18:57:37  jost
 * SearchProvider für die neue Jameica-Suchmaschine
 *
 **********************************************************************/
package de.jost_net.JVerein.search;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.KursteilnehmerDetailAction;
import de.jost_net.JVerein.rmi.Kursteilnehmer;
import de.willuhn.datasource.rmi.DBIterator;
import de.willuhn.jameica.search.Result;
import de.willuhn.jameica.search.SearchProvider;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

/**
 * Implementierung eines Search-Provider fuer die Suche nach Kursteilnehmern
 */
public class KursteilnehmerSearchProvider implements SearchProvider
{

  public String getName()
  {
    return JVereinPlugin.getI18n().tr("Kursteilnehmer");
  }

  public List<MyResult> search(String search) throws RemoteException
  {
    if (search == null || search.length() == 0)
    {
      return null;
    }

    String text = "%" + search.toLowerCase() + "%";
    DBIterator list = Einstellungen.getDBService().createList(
        Kursteilnehmer.class);
    list.addFilter("LOWER(name) LIKE ? OR " + "LOWER(vzweck1) LIKE ? OR "
        + "vzweck2 LIKE ? OR " + "blz LIKE ? OR " + "konto LIKE ?", text, text,
        text, text, text);

    ArrayList<MyResult> results = new ArrayList<MyResult>();
    while (list.hasNext())
    {
      results.add(new MyResult((Kursteilnehmer) list.next()));
    }
    return results;
  }

  /**
   * Hilfsklasse fuer die formatierte Anzeige der Ergebnisse.
   */
  private static class MyResult implements Result
  {

    private static final long serialVersionUID = -1685817053590491168L;

    private Kursteilnehmer k = null;

    private MyResult(Kursteilnehmer k)
    {
      this.k = k;
    }

    public void execute() throws ApplicationException
    {
      new KursteilnehmerDetailAction().handleAction(this.k);
    }

    public String getName()
    {
      try
      {
        return k.getName() + ", " + k.getVZweck1() + ", " + k.getVZweck2()
            + ", " + JVereinPlugin.getI18n().tr("Konto") + ": " + k.getKonto()
            + ", " + JVereinPlugin.getI18n().tr("BLZ") + ": " + k.getBlz();
      }
      catch (RemoteException re)
      {
        Logger.error("unable to determin result name", re);
        return null;
      }
    }

  }

}
