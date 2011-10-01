/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/input/KontoInput.java,v $
 * $Revision: 1.2 $
 * $Date: 2011/10/01 21:44:04 $
 * $Author: jost $
 * $Locker:  $
 * $State: Exp $
 *
 * Copyright (c) by willuhn software & services
 * All rights reserved
 **********************************************************************/

package de.jost_net.JVerein.gui.input;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.kapott.hbci.manager.HBCIUtils;

import de.willuhn.datasource.GenericIterator;
import de.willuhn.datasource.pseudo.PseudoIterator;
import de.willuhn.datasource.rmi.DBIterator;
import de.willuhn.jameica.gui.input.SelectInput;
import de.willuhn.jameica.hbci.HBCI;
import de.willuhn.jameica.hbci.Settings;
import de.willuhn.jameica.hbci.rmi.Konto;
import de.willuhn.jameica.system.Application;
import de.willuhn.logging.Logger;
import de.willuhn.util.I18N;

/**
 * Autosuggest-Feld zur Eingabe/Auswahl eines Kontos.
 */
public class KontoInput extends SelectInput
{
  private final static I18N i18n = Application.getPluginLoader()
      .getPlugin(HBCI.class).getResources().getI18N();

  /**
   * ct.
   * 
   * @param konto
   *          ausgewaehltes Konto.
   * @param filter
   *          optionaler Konto-Filter.
   * @throws RemoteException
   */
  public KontoInput(Konto konto) throws RemoteException
  {
    super(init(), konto);
    setName(i18n.tr("Konto"));
    setPleaseChoose(i18n.tr("Bitte w�hlen..."));
  }

  /**
   * Initialisiert die Liste der Konten.
   * 
   * @param filter
   *          Konto-Filter.
   * @return Liste der Konten.
   * @throws RemoteException
   */
  private static GenericIterator init() throws RemoteException
  {
    DBIterator it = Settings.getDBService().createList(Konto.class);
    it.setOrder("ORDER BY blz, kontonummer");
    List<Konto> l = new ArrayList<Konto>();
    // Konto keinHibiscus = (Konto) Settings.getDBService().createObject(
    // Konto.class, null);
    // keinHibiscus.setBezeichnung("kein Hibiscuskonto");
    // l.add(keinHibiscus);
    while (it.hasNext())
    {
      l.add((Konto) it.next());
    }
    return PseudoIterator.fromArray(l.toArray(new Konto[l.size()]));
  }

  /**
   * @see de.willuhn.jameica.gui.input.SelectInput#format(java.lang.Object)
   */
  protected String format(Object bean)
  {
    if (bean == null)
      return null;

    if (!(bean instanceof Konto))
      return bean.toString();

    try
    {
      Konto k = (Konto) bean;
      boolean disabled = (k.getFlags() & Konto.FLAG_DISABLED) == Konto.FLAG_DISABLED;
      StringBuffer sb = new StringBuffer();
      if (disabled)
        sb.append("[");

      sb.append(i18n.tr("Kto. {0}", k.getKontonummer()));

      String blz = k.getBLZ();
      sb.append(" [");
      String bankName = HBCIUtils.getNameForBLZ(blz);
      if (bankName != null && bankName.length() > 0)
      {
        sb.append(bankName);
      }
      else
      {
        sb.append("BLZ ");
        sb.append(blz);
      }
      sb.append("] ");
      sb.append(k.getName());

      String bez = k.getBezeichnung();
      if (bez != null && bez.length() > 0)
      {
        sb.append(" - ");
        sb.append(bez);
      }
      if (disabled)
        sb.append("]");
      return sb.toString();
    }
    catch (RemoteException re)
    {
      Logger.error("unable to format address", re);
      return null;
    }
  }

}
