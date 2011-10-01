/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/search/BuchungSearchProvider.java,v $
 * $Revision: 1.6 $
 * $Date: 2011/10/01 21:49:54 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/
package de.jost_net.JVerein.search;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.BuchungAction;
import de.jost_net.JVerein.rmi.Buchung;
import de.willuhn.datasource.rmi.DBIterator;
import de.willuhn.jameica.search.Result;
import de.willuhn.jameica.search.SearchProvider;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

/**
 * Implementierung eines Search-Provider fuer die Suche nach Buchungen
 */
public class BuchungSearchProvider implements SearchProvider
{

  public String getName()
  {
    return JVereinPlugin.getI18n().tr("Buchung");
  }

  public List<MyResult> search(String search) throws RemoteException
  {
    if (search == null || search.length() == 0)
    {
      return null;
    }

    String text = "%" + search.toLowerCase() + "%";
    DBIterator list = Einstellungen.getDBService().createList(Buchung.class);
    list.addFilter(
        "LOWER(name) LIKE ? OR betrag like ? OR "
            + "LOWER(zweck) LIKE ? OR LOWER(zweck2) LIKE ? OR LOWER(kommentar) LIKE ?",
        text, text, text, text, text);

    ArrayList<MyResult> results = new ArrayList<MyResult>();
    while (list.hasNext())
    {
      results.add(new MyResult((Buchung) list.next()));
    }
    return results;
  }

  /**
   * Hilfsklasse fuer die formatierte Anzeige der Ergebnisse.
   */
  private static class MyResult implements Result
  {

    private static final long serialVersionUID = -1685817053590491168L;

    private Buchung b = null;

    private MyResult(Buchung b)
    {
      this.b = b;
    }

    public void execute() throws ApplicationException
    {
      new BuchungAction().handleAction(this.b);
    }

    public String getName()
    {
      try
      {
        return b.getName() + ", " + b.getZweck() + ", " + b.getZweck2() + ", "
            + b.getKommentar() + ", " + JVereinPlugin.getI18n().tr("Konto")
            + ": " + b.getKonto().getNummer();
      }
      catch (RemoteException re)
      {
        Logger.error("unable to determin result name", re);
        return null;
      }
    }

  }

}
