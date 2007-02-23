/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/gui/view/AbbuchungView.java,v $
 * $Revision: 1.3 $
 * $Date: 2007/02/23 20:27:28 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: AbbuchungView.java,v $
 * Revision 1.3  2007/02/23 20:27:28  jost
 * Mail- und Webadresse im Header korrigiert.
 *
 * Revision 1.2  2006/10/21 09:18:54  jost
 * Zus�tzliche Plausi.
 *
 * Revision 1.1  2006/09/20 15:39:10  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.JVerein.gui.view;

import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.jost_net.JVerein.Einstellungen;
import de.jost_net.JVerein.gui.action.BackAction;
import de.jost_net.JVerein.gui.control.AbbuchungControl;
import de.willuhn.datasource.rmi.DBService;
import de.willuhn.datasource.rmi.ResultSetExtractor;
import de.willuhn.jameica.gui.AbstractView;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.util.ButtonArea;
import de.willuhn.jameica.gui.util.LabelGroup;
import de.willuhn.util.ApplicationException;

public class AbbuchungView extends AbstractView
{
  public void bind() throws Exception
  {
    String sql = "select count(*) from stammdaten";
    DBService service = Einstellungen.getDBService();
    ResultSetExtractor rs = new ResultSetExtractor()
    {
      public Object extract(ResultSet rs) throws RemoteException, SQLException
      {
        rs.next();
        return new Long(rs.getLong(1));
      }
    };
    Long anzahl = (Long) service.execute(sql, new Object[] {}, rs);
    if (anzahl.longValue() != 1)
    {
      throw new ApplicationException("Stammdaten fehlen. Bitte erfassen.");
    }

    GUI.getView().setTitle("Abbuchung");

    final AbbuchungControl control = new AbbuchungControl(this);

    LabelGroup group = new LabelGroup(getParent(), "Parameter");
    group.addLabelPair("Jahresabbuchung", control.getJahresabbuchung());
    group.addLabelPair("Von Eingabedatum", control.getVondatum());
    group.addLabelPair("Zahlungsgrund", control.getZahlungsgrund());
    group.addLabelPair("Zusatzabbuchung", control.getZusatzabbuchung());

    ButtonArea buttons = new ButtonArea(this.getParent(), 2);
    buttons.addButton(control.getStartButton());
    buttons.addButton("<< Zur�ck", new BackAction());

  }

  public void unbind() throws ApplicationException
  {
  }
}
