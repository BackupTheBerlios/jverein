/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/search/MitgliedSearchProvider.java,v $
 * $Revision: 1.8 $
 * $Date: 2011/10/01 21:49:54 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: MitgliedSearchProvider.java,v $
 * Revision 1.8  2011/10/01 21:49:54  jost
 * Log-Einträge entfernt. Zeigt Eclipse-History-View viel besser an. Macht den Quelltext schlanker.
 *
 **********************************************************************/
package de.jost_net.JVerein.search;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.MitgliedDetailAction;
import de.jost_net.JVerein.rmi.Mitglied;
import de.jost_net.JVerein.util.JVDateFormatTTMMJJJJ;
import de.willuhn.datasource.rmi.DBIterator;
import de.willuhn.jameica.search.Result;
import de.willuhn.jameica.search.SearchProvider;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

/**
 * Implementierung eines Search-Provider fuer die Suche nach Mitgliedern.
 */
public class MitgliedSearchProvider implements SearchProvider
{

  /**
   * @see de.willuhn.jameica.search.SearchProvider#getName()
   */
  public String getName()
  {
    return JVereinPlugin.getI18n().tr("Mitglieder");
  }

  @SuppressWarnings("unchecked")
  public List search(String search) throws RemoteException
  {
    if (search == null || search.length() == 0)
      return null;

    String text = "%" + search.toLowerCase() + "%";
    DBIterator list = Einstellungen.getDBService().createList(Mitglied.class);
    list.addFilter("LOWER(name) LIKE ? OR " + "LOWER(vorname) LIKE ? OR "
        + "ort LIKE ? OR " + "blz LIKE ? OR " + "konto LIKE ?", text, text,
        text, text, text);

    ArrayList results = new ArrayList();
    while (list.hasNext())
    {
      results.add(new MyResult((Mitglied) list.next()));
    }
    return results;
  }

  /**
   * Hilfsklasse fuer die formatierte Anzeige der Ergebnisse.
   */
  private static class MyResult implements Result
  {

    private static final long serialVersionUID = -1084818772620611937L;

    private Mitglied m = null;

    private MyResult(Mitglied m)
    {
      this.m = m;
    }

    public void execute() throws ApplicationException
    {
      new MitgliedDetailAction().handleAction(this.m);
    }

    public String getName()
    {
      try
      {
        return m.getNameVorname()
            + ", "
            + m.getAnschrift()
            + (m.getGeburtsdatum() != null ? ", "
                + new JVDateFormatTTMMJJJJ().format(m.getGeburtsdatum()) : "")
            + (m.getKonto() != null ? ", "
                + JVereinPlugin.getI18n().tr("Konto") + ": " + m.getKonto()
                + ", " + JVereinPlugin.getI18n().tr("BLZ") + ": " + m.getBlz()
                : "");
      }
      catch (RemoteException re)
      {
        Logger.error("unable to determin result name", re);
        return null;
      }
    }

  }

}
