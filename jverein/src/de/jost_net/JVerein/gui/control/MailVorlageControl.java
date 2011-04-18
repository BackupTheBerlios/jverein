/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/control/MailVorlageControl.java,v $
 * $Revision: 1.2 $
 * $Date: 2011/04/18 08:24:56 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: MailVorlageControl.java,v $
 * Revision 1.2  2011/04/18 08:24:56  jost
 * Verl�ngerung der Texte auf 10.000 Zeichen
 *
 * Revision 1.1  2010-02-01 20:57:58  jost
 * Neu: Einfache Mailfunktion
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.control;

import java.rmi.RemoteException;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.gui.action.MailVorlageDetailAction;
import de.jost_net.JVerein.gui.menu.MailVorlageMenu;
import de.jost_net.JVerein.rmi.MailVorlage;
import de.willuhn.datasource.rmi.DBIterator;
import de.willuhn.datasource.rmi.DBService;
import de.willuhn.jameica.gui.AbstractControl;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.input.TextAreaInput;
import de.willuhn.jameica.gui.input.TextInput;
import de.willuhn.jameica.gui.parts.TablePart;
import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;

public class MailVorlageControl extends AbstractControl
{
  private TablePart mailvorlageList;

  private TextInput betreff;

  private TextAreaInput txt;

  private MailVorlage mailvorlage;

  public MailVorlageControl(AbstractView view)
  {
    super(view);
  }

  private MailVorlage getMailVorlage()
  {
    if (mailvorlage != null)
    {
      return mailvorlage;
    }
    mailvorlage = (MailVorlage) getCurrentObject();
    return mailvorlage;
  }

  public TextInput getBetreff(boolean withFocus) throws RemoteException
  {
    if (betreff != null)
    {
      return betreff;
    }
    betreff = new TextInput(getMailVorlage().getBetreff(), 100);
    betreff.setName("Betreff");
    betreff.setMandatory(true);
    if (withFocus)
    {
      betreff.focus();
    }
    return betreff;
  }

  public TextAreaInput getTxt() throws RemoteException
  {
    if (txt != null)
    {
      return txt;
    }
    txt = new TextAreaInput(getMailVorlage().getTxt(), 10000);
    txt.setName("Text");
    txt.setMandatory(true);
    return txt;
  }

  public void handleStore()
  {
    try
    {
      MailVorlage mv = getMailVorlage();
      mv.setBetreff((String) getBetreff(false).getValue());
      mv.setTxt((String) getTxt().getValue());
      mv.store();
      GUI.getStatusBar().setSuccessText("MailVorlage gespeichert");
    }
    catch (ApplicationException e)
    {
      GUI.getStatusBar().setErrorText(e.getMessage());
    }
    catch (RemoteException e)
    {
      String fehler = "Fehler bei speichern der MailVorlage: "
          + e.getLocalizedMessage();
      Logger.error(fehler, e);
      GUI.getStatusBar().setErrorText(fehler);
    }
  }

  public TablePart getMailVorlageTable() throws RemoteException
  {
    if (mailvorlageList != null)
    {
      return mailvorlageList;
    }
    DBService service = Einstellungen.getDBService();
    DBIterator fdef = service.createList(MailVorlage.class);
    mailvorlageList = new TablePart(fdef, new MailVorlageDetailAction());
    mailvorlageList.addColumn("Betreff", "betreff");
    mailvorlageList.setContextMenu(new MailVorlageMenu());
    return mailvorlageList;
  }
}
