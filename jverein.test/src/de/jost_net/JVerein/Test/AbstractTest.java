/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein.test/src/de/jost_net/JVerein/Test/AbstractTest.java,v $
 * $Revision: 1.2 $
 * $Date: 2009/07/05 13:40:25 $
 * $Author: jost $
 *
 * Copyright (c) by willuhn.webdesign and  (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: AbstractTest.java,v $
 * Revision 1.2  2009/07/05 13:40:25  jost
 * *** empty log message ***
 *
 * Revision 1.1  2009/07/01 07:02:20  jost
 * Initial
 *
 **********************************************************************/

package de.jost_net.JVerein.Test;

import java.io.IOException;
import java.net.MalformedURLException;

import junit.framework.TestCase;

import com.lowagie.text.DocumentException;

/**
 */
public abstract class AbstractTest extends TestCase
{
  protected UseCaseDescription usecaseDescription;

  public AbstractTest(String name) throws DocumentException,
      MalformedURLException, IOException
  {
    super(name);
    usecaseDescription = new UseCaseDescription();
  }

  /*
   * @see TestCase#setUp()
   */
  protected void setUp() throws Exception
  {
    super.setUp();
  }

  protected UseCaseDescription getUseCaseDescription()
  {
    return usecaseDescription;
  }

  protected void writeDocument() throws DocumentException,
      MalformedURLException, IOException
  {
    usecaseDescription.write(TestPlugin.doc);
  }

}
