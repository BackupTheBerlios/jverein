/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein.test/src/de/jost_net/JVerein/Test/AbstractDBTest.java,v $
 * $Revision: 1.1 $
 * $Date: 2009/07/05 13:40:25 $
 * $Author: jost $
 *
 * Copyright (c) by willuhn.webdesign and  (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: AbstractDBTest.java,v $
 * Revision 1.1  2009/07/05 13:40:25  jost
 * *** empty log message ***
 *
 **********************************************************************/
package de.jost_net.JVerein.Test;

import java.io.IOException;
import java.net.MalformedURLException;

import com.lowagie.text.DocumentException;

import de.jost_net.JVerein.Einstellungen;
import de.willuhn.datasource.rmi.DBService;

public abstract class AbstractDBTest extends AbstractTest
{
  public AbstractDBTest(String name) throws DocumentException,
      MalformedURLException, IOException
  {
    super(name);
  }

  public DBService getDBService() throws Exception
  {
    return (DBService) Einstellungen.getDBService();
  }

}
