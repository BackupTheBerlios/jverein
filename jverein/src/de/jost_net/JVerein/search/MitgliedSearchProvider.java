/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/search/MitgliedSearchProvider.java,v $
 * $Revision: 1.6 $
 * $Date: 2011/02/12 09:42:02 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: MitgliedSearchProvider.java,v $
 * Revision 1.6  2011/02/12 09:42:02  jost
 * Statische Codeanalyse mit Findbugs
 *
 * Revision 1.5  2010-10-15 09:58:30  jost
 * Code aufger�umt
 *
 * Revision 1.4  2009-06-11 21:04:24  jost
 * Vorbereitung I18N
 *
 * Revision 1.3  2008/11/29 13:14:38  jost
 * Refactoring: Warnungen beseitigt.
 *
 * Revision 1.2  2008/10/01 14:18:07  jost
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
        + "ort LIKE ? OR " + "blz LIKE ? OR " + "konto LIKE ?", new String[] {
        text, text, text, text, text });

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
