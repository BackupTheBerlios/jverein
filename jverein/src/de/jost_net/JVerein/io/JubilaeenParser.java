/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/io/JubilaeenParser.java,v $
 * $Revision: 1.3 $
 * $Date: 2010/10/15 09:58:29 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: JubilaeenParser.java,v $
 * Revision 1.3  2010/10/15 09:58:29  jost
 * Code aufger�umt
 *
 * Revision 1.2  2010-01-01 20:28:24  jost
 * Typo
 *
 * Revision 1.1  2007/12/22 08:27:02  jost
 * Neu: Jubiläenliste
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
        throw new RuntimeException("Ung�ltiger Eintrag: " + element);
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
