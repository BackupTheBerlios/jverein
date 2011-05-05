/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/menu/MitgliedskontoMenu.java,v $
 * $Revision: 1.5 $
 * $Date: 2011/05/05 19:53:28 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: MitgliedskontoMenu.java,v $
 * Revision 1.5  2011/05/05 19:53:28  jost
 * Neu: Istbuchungen k�nnen vom Mitgliedskonto gel�st werden.
 *
 * Revision 1.4  2011-02-12 09:34:29  jost
 * Statische Codeanalyse mit Findbugs
 *
 * Revision 1.3  2010-10-15 09:58:29  jost
 * Code aufger�umt
 *
 * Revision 1.2  2010-08-04 10:40:52  jost
 * Javadoc
 *
 * Revision 1.1  2010-07-25 18:36:03  jost
 * Neu: Mitgliedskonto
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.menu;

import java.rmi.RemoteException;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.JVereinPlugin;
import de.jost_net.JVerein.gui.action.MitgliedskontoDetailAction;
import de.jost_net.JVerein.gui.action.MitgliedskontoDetailSollLoeschenAction;
import de.jost_net.JVerein.gui.action.MitgliedskontoDetailSollNeuAction;
import de.jost_net.JVerein.gui.action.MitgliedskontoIstLoesenAction;
import de.jost_net.JVerein.gui.control.MitgliedskontoNode;
import de.jost_net.JVerein.rmi.Buchung;
import de.willuhn.datasource.rmi.DBIterator;
import de.willuhn.jameica.gui.Action;
import de.willuhn.jameica.gui.parts.CheckedContextMenuItem;
import de.willuhn.jameica.gui.parts.ContextMenu;
import de.willuhn.jameica.gui.parts.ContextMenuItem;
import de.willuhn.logging.Logger;
import de.willuhn.util.I18N;

/**
 * Kontext-Menu, welches an MitgliedskontenListen gehangen werden kann.
 */
public class MitgliedskontoMenu extends ContextMenu
{

  private final static I18N i18n = JVereinPlugin.getI18n();

  /**
   * Erzeugt ein Kontext-Menu fuer eine Liste von Mitgliedskonten.
   */
  public MitgliedskontoMenu()
  {
    addItem(new MitgliedItem(i18n.tr("neue Sollbuchung"),
        new MitgliedskontoDetailSollNeuAction(), "accessories-calculator.png"));
    addItem(ContextMenuItem.SEPARATOR);
    addItem(new SollItem(i18n.tr("Sollbuchung bearbeiten"),
        new MitgliedskontoDetailAction(), "accessories-calculator.png"));
    addItem(new SollOhneIstItem(i18n.tr("Sollbuchung l�schen"),
        new MitgliedskontoDetailSollLoeschenAction(),
        "accessories-calculator.png"));
    addItem(new SollMitIstItem(i18n.tr("Istbuchung vom Mitgliedskonto l�sen"),
        new MitgliedskontoIstLoesenAction(), "accessories-calculator.png"));
  }

  private static class MitgliedItem extends CheckedContextMenuItem
  {

    /**
     * @param text
     * @param action
     * @param optionale
     *          Angabe eines Icons.
     */
    private MitgliedItem(String text, Action action, String icon)
    {
      super(text, action, icon);
    }

    @Override
    public boolean isEnabledFor(Object o)
    {
      if (o instanceof MitgliedskontoNode)
      {
        MitgliedskontoNode mkn = (MitgliedskontoNode) o;
        if (mkn.getType() == MitgliedskontoNode.MITGLIED)
        {
          return true;
        }
        else
        {
          return false;
        }
      }
      return super.isEnabledFor(o);
    }
  }

  private static class SollItem extends CheckedContextMenuItem
  {

    /**
     * @param text
     * @param action
     * @param optionale
     *          Angabe eines Icons.
     */
    private SollItem(String text, Action action, String icon)
    {
      super(text, action, icon);
    }

    @Override
    public boolean isEnabledFor(Object o)
    {
      if (o instanceof MitgliedskontoNode)
      {
        MitgliedskontoNode mkn = (MitgliedskontoNode) o;
        if (mkn.getType() == MitgliedskontoNode.SOLL)
        {
          return true;
        }
        else
        {
          return false;
        }
      }
      return super.isEnabledFor(o);
    }
  }

  private static class SollOhneIstItem extends CheckedContextMenuItem
  {

    /**
     * @param text
     * @param action
     * @param optionale
     *          Angabe eines Icons.
     */
    private SollOhneIstItem(String text, Action action, String icon)
    {
      super(text, action, icon);
    }

    @Override
    public boolean isEnabledFor(Object o)
    {
      if (o instanceof MitgliedskontoNode)
      {
        MitgliedskontoNode mkn = (MitgliedskontoNode) o;
        if (mkn.getType() == MitgliedskontoNode.SOLL)
        {
          DBIterator it;
          try
          {
            it = Einstellungen.getDBService().createList(Buchung.class);
            it.addFilter("mitgliedskonto = ?", new Object[] { mkn.getID() });
            if (it.size() == 0)
            {
              return true;
            }
          }
          catch (RemoteException e)
          {
            Logger.error("Fehler", e);
          }
          return false;
        }
        else
        {
          return false;
        }
      }
      return super.isEnabledFor(o);
    }
  }

  private static class SollMitIstItem extends CheckedContextMenuItem
  {

    /**
     * @param text
     * @param action
     * @param optionale
     *          Angabe eines Icons.
     */
    private SollMitIstItem(String text, Action action, String icon)
    {
      super(text, action, icon);
    }

    @Override
    public boolean isEnabledFor(Object o)
    {
      if (o instanceof MitgliedskontoNode)
      {
        MitgliedskontoNode mkn = (MitgliedskontoNode) o;
        if (mkn.getType() == MitgliedskontoNode.IST)
        {
          return true;
        }
        else
        {
          return false;
        }
      }
      return super.isEnabledFor(o);
    }
  }

}
