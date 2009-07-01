/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein.test/src/de/jost_net/JVerein/Test/AbstractTest.java,v $
 * $Revision: 1.1 $
 * $Date: 2009/07/01 07:02:20 $
 * $Author: jost $
 *
 * Copyright (c) by willuhn.webdesign and  (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: AbstractTest.java,v $
 * Revision 1.1  2009/07/01 07:02:20  jost
 * Initial
 *
 **********************************************************************/

package de.jost_net.JVerein.Test;

import junit.framework.TestCase;
import de.jost_net.JVerein.Einstellungen;
import de.willuhn.datasource.rmi.DBService;

/**
 */
public class AbstractTest extends TestCase
{

  /**
   * Constructor for KontoImplTest.
   * 
   * @param arg0
   */
  public AbstractTest(String arg0)
  {
    super(arg0);
  }

  /*
   * @see TestCase#setUp()
   */
  protected void setUp() throws Exception
  {
    super.setUp();
  }

  public DBService getDBService() throws Exception
  {
    return (DBService) Einstellungen.getDBService();
  }

}
