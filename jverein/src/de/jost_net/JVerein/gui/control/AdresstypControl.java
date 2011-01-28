/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/control/AdresstypControl.java,v $
 * $Revision: 1.2 $
 * $Date: 2011/01/28 17:03:49 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: AdresstypControl.java,v $
 * Revision 1.2  2011/01/28 17:03:49  jost
 * private->public
 * Action f�r Tabelle festgelegt.
 *
 * Revision 1.1  2011-01-27 22:18:06  jost
 * Neu: Speicherung von weiteren Adressen in der Mitgliedertabelle
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.control;

import java.rmi.RemoteException;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.gui.action.AdresstypAction;
import de.jost_net.JVerein.rmi.Adresstyp;
import de.willuhn.datasource.rmi.DBIterator;
import de.willuhn.datasource.rmi.DBService;
import de.willuhn.jameica.gui.AbstractControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.Part;
import de.willuhn.jameica.gui.input.Input;
import de.willuhn.jameica.gui.input.TextInput;
import de.willuhn.jameica.gui.parts.TablePart;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

public class AdresstypControl extends AbstractControl
{
  private de.willuhn.jameica.system.Settings settings;

  private TablePart adresstypList;

  private Input bezeichnung;

  private Adresstyp adresstyp;

  public AdresstypControl(AbstractView view)
  {
    super(view);
    settings = new de.willuhn.jameica.system.Settings(this.getClass());
    settings.setStoreWhenRead(true);
  }

  public Adresstyp getAdresstyp()
  {
    if (adresstyp != null)
    {
      return adresstyp;
    }
    adresstyp = (Adresstyp) getCurrentObject();
    return adresstyp;
  }

  public Input getBezeichnung() throws RemoteException
  {
    if (bezeichnung != null)
    {
      return bezeichnung;
    }
    bezeichnung = new TextInput(getAdresstyp().getBezeichnung(), 30);
    return bezeichnung;
  }

  /**
   * This method stores the project using the current values.
   */
  public void handleStore()
  {
    try
    {
      Adresstyp at = getAdresstyp();
      at.setBezeichnung((String) getBezeichnung().getValue());
      try
      {
        at.store();
        GUI.getStatusBar().setSuccessText("Adresstyp gespeichert");
      }
      catch (ApplicationException e)
      {
        GUI.getStatusBar().setErrorText(e.getMessage());
      }
    }
    catch (RemoteException e)
    {
      String fehler = "Fehler bei speichern des Adresstypen";
      Logger.error(fehler, e);
      GUI.getStatusBar().setErrorText(fehler);
    }
  }

  public Part getAdresstypList() throws RemoteException
  {
    DBService service = Einstellungen.getDBService();
    DBIterator adresstypen = service.createList(Adresstyp.class);
    adresstypen.setOrder("ORDER BY bezeichnung");

    adresstypList = new TablePart(adresstypen, new AdresstypAction());
    adresstypList.addColumn("Bezeichnung", "bezeichnung");
    // buchungsartList.setContextMenu(new BuchungsartMenu());
    adresstypList.setRememberColWidths(true);
    adresstypList.setRememberOrder(true);
    adresstypList.setSummary(true);
    return adresstypList;
  }
}
