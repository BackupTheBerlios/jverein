/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/control/EigenschaftGruppeControl.java,v $
 * $Revision: 1.3 $
 * $Date: 2010/09/09 18:49:34 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: EigenschaftGruppeControl.java,v $
 * Revision 1.3  2010/09/09 18:49:34  jost
 * Eigenschaftengruppen k�nnen jetzt auch das Merkmal "Pflicht" haben. Dann mu� mindestens eine Eigenschaft ausgew�hlt werden.
 *
 * Revision 1.2  2009/11/23 20:39:44  jost
 * Bugfix L�sch-Button
 *
 * Revision 1.1  2009/11/17 20:56:33  jost
 * Neu: Eigenschaft und EigenschaftGruppe
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.control;

import java.rmi.RemoteException;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.gui.action.EigenschaftGruppeDetailAction;
import de.jost_net.JVerein.gui.formatter.JaNeinFormatter;
import de.jost_net.JVerein.gui.menu.EigenschaftGruppeMenu;
import de.jost_net.JVerein.rmi.EigenschaftGruppe;
import de.willuhn.datasource.rmi.DBIterator;
import de.willuhn.datasource.rmi.DBService;
import de.willuhn.jameica.gui.AbstractControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.Part;
import de.willuhn.jameica.gui.input.CheckboxInput;
import de.willuhn.jameica.gui.input.Input;
import de.willuhn.jameica.gui.input.TextInput;
import de.willuhn.jameica.gui.parts.TablePart;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

public class EigenschaftGruppeControl extends AbstractControl
{
  private de.willuhn.jameica.system.Settings settings;

  private TablePart eigenschaftgruppeList;

  private Input bezeichnung;

  private CheckboxInput pflicht;

  private EigenschaftGruppe eigenschaftgruppe;

  public EigenschaftGruppeControl(AbstractView view)
  {
    super(view);
    settings = new de.willuhn.jameica.system.Settings(this.getClass());
    settings.setStoreWhenRead(true);
  }

  private EigenschaftGruppe getEigenschaftGruppe()
  {
    if (eigenschaftgruppe != null)
    {
      return eigenschaftgruppe;
    }
    eigenschaftgruppe = (EigenschaftGruppe) getCurrentObject();
    return eigenschaftgruppe;
  }

  public Input getBezeichnung() throws RemoteException
  {
    if (bezeichnung != null)
    {
      return bezeichnung;
    }
    bezeichnung = new TextInput(getEigenschaftGruppe().getBezeichnung(), 30);
    return bezeichnung;
  }

  public CheckboxInput getPflicht() throws RemoteException
  {
    if (pflicht != null)
    {
      return pflicht;
    }
    pflicht = new CheckboxInput(getEigenschaftGruppe().getPflicht());
    return pflicht;
  }

  /**
   * This method stores the project using the current values.
   */
  public void handleStore()
  {
    try
    {
      EigenschaftGruppe eg = getEigenschaftGruppe();
      eg.setBezeichnung((String) getBezeichnung().getValue());
      eg.setPflicht((Boolean) getPflicht().getValue());
      try
      {
        eg.store();
        GUI.getStatusBar().setSuccessText("Eigenschaften Gruppe gespeichert");
      }
      catch (ApplicationException e)
      {
        GUI.getStatusBar().setErrorText(e.getMessage());
      }
    }
    catch (RemoteException e)
    {
      String fehler = "Fehler bei speichern der Eigenschaft Gruppe";
      Logger.error(fehler, e);
      GUI.getStatusBar().setErrorText(fehler);
    }
  }

  public Part getEigenschaftGruppeList() throws RemoteException
  {
    if (eigenschaftgruppeList != null)
    {
      return eigenschaftgruppeList;
    }

    DBService service = Einstellungen.getDBService();
    DBIterator eigenschaftgruppe = service.createList(EigenschaftGruppe.class);
    eigenschaftgruppe.setOrder("ORDER BY bezeichnung");

    eigenschaftgruppeList = new TablePart(eigenschaftgruppe,
        new EigenschaftGruppeDetailAction(false));
    eigenschaftgruppeList.addColumn("Bezeichnung", "bezeichnung");
    eigenschaftgruppeList
        .addColumn("Pflicht", "pflicht", new JaNeinFormatter());
    eigenschaftgruppeList.setContextMenu(new EigenschaftGruppeMenu());
    eigenschaftgruppeList.setRememberColWidths(true);
    eigenschaftgruppeList.setRememberOrder(true);
    eigenschaftgruppeList.setSummary(true);
    return eigenschaftgruppeList;
  }
}
