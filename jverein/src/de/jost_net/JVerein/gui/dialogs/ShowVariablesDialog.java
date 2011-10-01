/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/dialogs/ShowVariablesDialog.java,v $
 * $Revision: 1.3 $
 * $Date: 2011/10/01 21:43:36 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/
package de.jost_net.JVerein.gui.dialogs;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.swt.widgets.Composite;

import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.menu.ShowVariablesMenu;
import de.willuhn.datasource.GenericObject;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.dialogs.AbstractDialog;
import de.willuhn.jameica.gui.parts.TablePart;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.logging.Logger;

/**
 * Dialog zur Zuordnung einer Buchungsart.
 */
public class ShowVariablesDialog extends AbstractDialog
{
  private Map<String, Object> vars;

  /**
   * @param position
   */
  public ShowVariablesDialog(Map<String, Object> vars)
  {
    super(AbstractDialog.POSITION_CENTER);
    setTitle(JVereinPlugin.getI18n().tr("Liste der Variablen"));
    setSize(400, 400);
    this.vars = vars;
    try
    {
      this.open();
    }
    catch (Exception e)
    {
      Logger.error("Fehler", e);
    }
  }

  @Override
  protected void paint(Composite parent) throws Exception
  {

    List<GenericObject> list = new ArrayList<GenericObject>();

    for (Entry<String, Object> entry : vars.entrySet())
    {
      list.add(new Var(entry));
    }
    TablePart tab = new TablePart(list, null);
    tab.addColumn("Name", "name");
    tab.addColumn("Wert", "wert");
    tab.setRememberOrder(true);
    tab.setContextMenu(new ShowVariablesMenu(parent));
    tab.paint(parent);
    ButtonArea buttons = new ButtonArea(parent, 2);
    buttons.addButton(JVereinPlugin.getI18n().tr("OK"), new Action()
    {

      public void handleAction(Object context)
      {
        close();
      }
    });

  }

  /**
   * @see de.willuhn.jameica.gui.dialogs.AbstractDialog#getData()
   */
  @Override
  public Object getData() throws Exception
  {
    return null;
  }

  public class Var implements GenericObject
  {
    private String name;

    private Object wert;

    public Var(Entry<String, Object> entry)
    {
      this.name = entry.getKey();
      this.wert = entry.getValue();
    }

    public String[] getAttributeNames() throws RemoteException
    {
      return new String[] { "name", "wert" };
    }

    public String getID() throws RemoteException
    {
      return "name";
    }

    public boolean equals(GenericObject arg0) throws RemoteException
    {
      return false;
    }

    public Object getAttribute(String arg0) throws RemoteException
    {
      if (arg0.equals("name"))
      {
        return name;
      }
      else if (arg0.equals("wert"))
      {
        return wert;
      }
      return null;
    }

    public String getPrimaryAttribute() throws RemoteException
    {
      return "name";
    }

  }
}
