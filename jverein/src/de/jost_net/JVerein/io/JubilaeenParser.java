/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/io/JubilaeenParser.java,v $
 * $Revision: 1.4 $
 * $Date: 2011/10/01 21:47:49 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/
package de.jost_net.JVerein.io;

import java.util.StringTokenizer;
import java.util.Vector;

public class JubilaeenParser
{

  private Vector<Integer> elemente;

  private int ei = 0;

  public JubilaeenParser(String jubilaeen) throws RuntimeException
  {
    // Zerlegen in die einzelnen Elemente
    StringTokenizer stt = new StringTokenizer(jubilaeen, ",");
    elemente = new Vector<Integer>();
    while (stt.hasMoreElements())
    {
      String element = stt.nextToken();
      try
      {
        Integer jubilaeum = Integer.parseInt(element);
        elemente.add(jubilaeum);
      }
      catch (NumberFormatException e)
      {
        throw new RuntimeException("Ungültiger Eintrag: " + element);
      }
    }
  }

  public boolean hasNext()
  {
    if (ei < elemente.size())
    {
      return true;
    }
    else
    {
      return false;
    }
  }

  public int getNext()
  {
    Integer i = elemente.elementAt(ei);
    ei++;
    return i.intValue();
  }
}
