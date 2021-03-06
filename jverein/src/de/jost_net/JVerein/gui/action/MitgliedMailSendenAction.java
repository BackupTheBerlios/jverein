/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/action/MitgliedMailSendenAction.java,v $
 * $Revision: 1.3 $
 * $Date: 2011/10/01 21:39:59 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
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
        StringBuilder mitgliederohnemail = new StringBuilder();
        for (Mitglied mitglied : mitgl)
        {
          MailEmpfaenger me = (MailEmpfaenger) Einstellungen.getDBService()
              .createObject(MailEmpfaenger.class, null);
          if (mitglied.getEmail() == null || mitglied.getEmail().length() == 0)
          {
            if (mitgliederohnemail.length() > 0)
            {
              mitgliederohnemail.append(", ");
            }
            mitgliederohnemail.append(mitglied.getNameVorname());
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
                  + mitgliederohnemail.toString() + "\nWeiter?"));
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
            Logger.error(
                JVereinPlugin.getI18n().tr(
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
