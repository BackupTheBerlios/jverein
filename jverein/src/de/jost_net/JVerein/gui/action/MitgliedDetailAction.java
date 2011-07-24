/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/MitgliedDetailAction.java,v $
 * $Revision: 1.8 $
 * $Date: 2011/07/24 18:02:34 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: MitgliedDetailAction.java,v $
 * Revision 1.8  2011/07/24 18:02:34  jost
 * Neu: Auflistung Familienbeitr�ge
 *
 * Revision 1.7  2011-01-27 22:16:56  jost
 * Neu: Speicherung von weiteren Adressen in der Mitgliedertabelle
 *
 * Revision 1.6  2010-11-22 20:57:53  jost
 * Vorbereitungs Arbeitseinsatz�berpr�fung.
 *
 * Revision 1.5  2010-07-25 18:28:31  jost
 * Neu: Mitgliedskonto
 *
 * Revision 1.4  2009/06/11 21:02:05  jost
 * Vorbereitung I18N
 *
 * Revision 1.3  2009/04/25 05:27:30  jost
 * Neu: Juristische Personen  k�nnen als Mitglied gespeichert werden.
 *
 * Revision 1.2  2007/02/23 20:26:00  jost
 * Mail- und Webadresse im Header korrigiert.
 *
 * Revision 1.1  2006/09/20 15:38:12  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.control.FamilienbeitragNode;
import de.jost_net.JVerein.gui.dialogs.PersonenartDialog;
import de.jost_net.JVerein.gui.view.MitgliedDetailView;
import de.jost_net.JVerein.io.ArbeitseinsatzZeile;
import de.jost_net.JVerein.rmi.Mitglied;
import de.jost_net.JVerein.rmi.Mitgliedskonto;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.util.ApplicationException;

public class MitgliedDetailAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    Mitglied m = null;
    try
    {
      if (context != null && context instanceof FamilienbeitragNode)
      {
        FamilienbeitragNode fbn = (FamilienbeitragNode) context;
        m = (Mitglied) fbn.getMitglied();
      }
      else if (context != null && context instanceof ArbeitseinsatzZeile)
      {
        ArbeitseinsatzZeile aez = (ArbeitseinsatzZeile) context;
        m = (Mitglied) aez.getAttribute("mitglied");
      }
      else if (context != null && (context instanceof Mitglied))
      {
        m = (Mitglied) context;
      }
      else if (context != null && (context instanceof Mitgliedskonto))
      {
        Mitgliedskonto mk = (Mitgliedskonto) context;
        m = mk.getMitglied();
      }
      else
      {
        m = (Mitglied) Einstellungen.getDBService().createObject(
            Mitglied.class, null);
        if (Einstellungen.getEinstellung().getJuristischePersonen())
        {
          PersonenartDialog pad = new PersonenartDialog(
              PersonenartDialog.POSITION_CENTER);
          String pa = (String) pad.open();
          m.setPersonenart(pa);
        }
        else
        {
          m.setPersonenart("n");
        }
      }
    }
    catch (Exception e)
    {
      throw new ApplicationException(JVereinPlugin.getI18n().tr(
          "Fehler bei der Erzeugung eines neuen Mitgliedes"), e);
    }
    GUI.startView(new MitgliedDetailView(), m);
  }
}
