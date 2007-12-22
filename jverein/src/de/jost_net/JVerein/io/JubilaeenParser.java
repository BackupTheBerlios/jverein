/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/io/JubilaeenParser.java,v $
 * $Revision: 1.1 $
 * $Date: 2007/12/22 08:27:02 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: JubilaeenParser.java,v $
 * Revision 1.1  2007/12/22 08:27:02  jost
 * Neu: JubilÃ¤enliste
 *
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
        throw new RuntimeException("Ungültiger Eintag: " + element);
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
    Integer i = (Integer) elemente.elementAt(ei);
    ei++;
    return i.intValue();
  }
}
