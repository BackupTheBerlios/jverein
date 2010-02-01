/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/MitgliedMailSendenAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2010/02/01 20:57:35 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: MitgliedMailSendenAction.java,v $
 * Revision 1.1  2010/02/01 20:57:35  jost
 * Neu: Einfache Mailfunktion
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.action;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.TreeSet;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.control.MailVorlageControl;
import de.jost_net.JVerein.gui.dialogs.MailVorlagenAuswahlDialog;
import de.jost_net.JVerein.gui.view.MailDetailView;
import de.jost_net.JVerein.rmi.Mail;
import de.jost_net.JVerein.rmi.MailEmpfaenger;
import de.jost_net.JVerein.rmi.MailVorlage;
import de.jost_net.JVerein.rmi.Mitglied;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.dialogs.YesNoDialog;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

public class MitgliedMailSendenAction implements Action
{
  public void handleAction(Object context) throws ApplicationException
  {
    try
    {
      if (context != null
          && (context instanceof Mitglied || context instanceof Mitglied[]))
      {
        ArrayList<Mitglied> mitgl = new ArrayList<Mitglied>();
        TreeSet<MailEmpfaenger> empf = new TreeSet<MailEmpfaenger>();
        if (context instanceof Mitglied)
        {
          mitgl.add((Mitglied) context);
        }
        else if (context instanceof Mitglied[])
        {
          for (Mitglied mitglied : (Mitglied[]) context)
          {
            mitgl.add(mitglied);
          }
        }
        String mitgliederohnemail = "";
        for (Mitglied mitglied : mitgl)
        {
          MailEmpfaenger me = (MailEmpfaenger) Einstellungen.getDBService()
              .createObject(MailEmpfaenger.class, null);
          if (mitglied.getEmail() == null || mitglied.getEmail().length() == 0)
          {
            if (mitgliederohnemail.length() > 0)
            {
              mitgliederohnemail += ", ";
            }
            mitgliederohnemail += mitglied.getNameVorname();
          }
          else
          {
            me.setMitglied(mitglied);
            empf.add(me);
          }
        }
        if (mitgliederohnemail.length() > 0)
        {
          YesNoDialog d = new YesNoDialog(YesNoDialog.POSITION_CENTER);
          d.setTitle(JVereinPlugin.getI18n().tr("Mail senden"));
          d.setText(JVereinPlugin.getI18n().tr(
              "Folgende Mitglieder haben keine Mail-Adresse:"
                  + mitgliederohnemail + "\nWeiter?"));
          try
          {
            Boolean choice = (Boolean) d.open();
            if (!choice.booleanValue())
            {
              return;
            }
          }
          catch (Exception e)
          {
            Logger.error(JVereinPlugin.getI18n().tr(
                "Fehler bei der Auswahl der Mail-Empf�nger"), e);
            return;
          }

        }
        MailVorlagenAuswahlDialog mvad = new MailVorlagenAuswahlDialog(
            new MailVorlageControl(null),
            MailVorlagenAuswahlDialog.POSITION_CENTER);
        Mail mail = (Mail) Einstellungen.getDBService().createObject(
            Mail.class, null);
        try
        {
          MailVorlage mv = (MailVorlage) mvad.open();
          if (mv != null)
          {
            mail.setBetreff(mv.getBetreff());
            mail.setTxt(mv.getTxt());
          }
        }
        catch (Exception e)
        {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        mail.setEmpfaenger(empf);
        GUI.startView(MailDetailView.class.getName(), mail);
      }
      else
      {
        throw new ApplicationException(JVereinPlugin.getI18n().tr(
            "Keinen Empf�nger ausgew�hlt"));
      }
    }
    catch (RemoteException e)
    {
      throw new ApplicationException("Fehler aufgetreten: "
          + e.getLocalizedMessage());
    }
  }
}
