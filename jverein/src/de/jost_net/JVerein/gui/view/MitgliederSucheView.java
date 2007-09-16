/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/MitgliederSucheView.java,v $
 * $Revision: 1.9 $
 * $Date: 2007/09/16 17:52:37 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: MitgliederSucheView.java,v $
 * Revision 1.9  2007/09/16 17:52:37  jost
 * Selektion nach Mitgliedsstatus
 *
 * Revision 1.8  2007/08/23 19:26:09  jost
 * Bugfix
 *
 * Revision 1.7  2007/08/23 18:45:25  jost
 * Standard-Tab für die Mitglieder-Suche
 * und Bug #011764
 *
 * Revision 1.6  2007/07/20 20:15:52  jost
 * Bessere Fehlermeldung
 *
 * Revision 1.5  2007/04/03 16:03:24  jost
 * Meldung, wenn keine Beitragsgruppe erfa�t ist.
 *
 * Revision 1.4  2007/03/25 17:02:43  jost
 * 1. Zeitoptimierung bei der Suche
 * 2. Tab mit allen Mitgliedern
 *
 * Revision 1.3  2007/02/23 20:27:42  jost
 * Mail- und Webadresse im Header korrigiert.
 *
 * Revision 1.2  2006/10/21 09:19:30  jost
 * Korrekter Ablauf bei leerer Datenbank
 *
 * Revision 1.1  2006/09/20 15:39:10  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TabFolder;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.gui.action.BackAction;
import de.jost_net.JVerein.gui.action.MitgliedDetailAction;
import de.jost_net.JVerein.gui.control.MitgliedControl;
import de.willuhn.datasource.rmi.DBService;
import de.willuhn.datasource.rmi.ResultSetExtractor;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.input.Input;
import de.willuhn.jameica.gui.input.LabelInput;
import de.willuhn.jameica.gui.parts.TablePart;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.gui.util.Color;
import de.willuhn.jameica.gui.util.LabelGroup;
import de.willuhn.jameica.gui.util.TabGroup;
import de.willuhn.jameica.system.Settings;
import de.willuhn.util.ApplicationException;

public class MitgliederSucheView extends AbstractView
{
  private TabFolder folder = null;

  private TablePart[] p;

  private TabGroup[] tab;

  private Settings settings;

  private final String[] b = { "A", "�", "B", "C", "D", "E", "F", "G", "H",
      "I", "J", "K", "L", "M", "N", "O", "�", "P", "Q", "R", "S", "T", "U",
      "�", "V", "W", "X", "Y", "Z", "*" };

  public void bind() throws Exception
  {
    GUI.getView().setTitle("Suche Mitglied");

    final MitgliedControl control = new MitgliedControl(this);

    String sql = "select count(*) from beitragsgruppe";
    DBService service = Einstellungen.getDBService();
    ResultSetExtractor rs = new ResultSetExtractor()
    {
      public Object extract(ResultSet rs) throws RemoteException, SQLException
      {
        rs.next();
        return new Long(rs.getLong(1));
      }
    };
    Long anzahlbeitragsgruppe = (Long) service
        .execute(sql, new Object[] {}, rs);
    if (anzahlbeitragsgruppe.longValue() == 0)
    {
      new LabelInput("Noch keine Beitragsgruppe erfa�t. Bitte unter "
          + "Plugins|JVerein|Beitragsgruppe erfassen.").paint(getParent());
    }

    rs = new ResultSetExtractor()
    {
      public Object extract(ResultSet rs) throws RemoteException, SQLException
      {
        rs.next();
        return new Long(rs.getLong(1));
      }
    };

    LabelGroup group = new LabelGroup(getParent(), "Auswahl");
    Input mitglstat = control.getMitgliedStatus();
    mitglstat.addListener(new Listener()
    {
      public void handleEvent(Event event)
      {
        if (event.type != SWT.Selection)
        {
          return;
        }
        int si = folder.getSelectionIndex();
        TabRefresh(control, si);
      }
    });
    group.addLabelPair("Mitgliedschaft", mitglstat);

    settings = new Settings(this.getClass());
    settings.setStoreWhenRead(true);
  
    Long anzahl = (Long) service.execute(sql, new Object[] {}, rs);
    if (anzahl.longValue() > 0)
    {
      folder = new TabFolder(getParent(), SWT.NONE);
      folder.setLayoutData(new GridData(GridData.FILL_BOTH));
      folder.setBackground(Color.BACKGROUND.getSWTColor());

      tab = new TabGroup[b.length];
      p = new TablePart[b.length];

      for (int i = 0; i < b.length; i++)
      {
        tab[i] = new TabGroup(folder, b[i]);
      }
      int si = 0;
      if (Einstellungen.getMitgliederStandardTab().equals("*"))
      {
        si = b.length - 1;
      }
      for (int i = 0; i < b.length; i++)
      {
        if (b[i].equals(settings.getString("lasttab", "A")))
        {
          si = i;
        }
      }
      p[si] = control.getMitgliedTable(b[si]);
      p[si].paint(tab[si].getComposite());
      folder.setSelection(si);
      folder.addSelectionListener(new SelectionListener()
      {
        public void widgetDefaultSelected(SelectionEvent e)
        {
          //
        }

        public void widgetSelected(SelectionEvent e)
        {
          int si = folder.getSelectionIndex();
          settings.setAttribute("lasttab", b[si]);
          TabRefresh(control, si);
        }
      });
    }
    ButtonArea buttons = new ButtonArea(this.getParent(), 2);
    if (anzahlbeitragsgruppe > 0)
    {
      buttons.addButton("Neu", new MitgliedDetailAction());
    }
    buttons.addButton("<< Zur�ck", new BackAction());
  }

  public void unbind() throws ApplicationException
  {
  }

  private void TabRefresh(MitgliedControl control, int index)
  {
    try
    {
      if (p[index] != null)
      {
        Control[] c = tab[index].getComposite().getChildren();
        c[0].dispose();
      }
      p[index] = control.getMitgliedTable(b[index]);
      p[index].paint(tab[index].getComposite());
      folder.getParent().layout(true, true);
    }
    catch (RemoteException e1)
    {
      e1.printStackTrace();
    }
  }
}
