/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/util/Checker.java,v $
 * $Revision: 1.1 $
 * $Date: 2009/03/26 21:05:56 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: Checker.java,v $
 * Revision 1.1  2009/03/26 21:05:56  jost
 * Email-Adress-Checker
 *
 **********************************************************************/
package de.jost_net.JVerein.util;

import java.util.StringTokenizer;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class Checker
{
  public static boolean isValidEmailAddress(String emailAddress)
  {
    // a null string is invalid
    if (emailAddress == null)
      return false;

    // a string without a "@" is an invalid email address
    if (emailAddress.indexOf("@") < 0)
      return false;

    // a string without a "." is an invalid email address
    if (emailAddress.indexOf(".") < 0)
      return false;

    if (lastEmailFieldTwoCharsOrMore(emailAddress) == false)
      return false;

    try
    {
      new InternetAddress(emailAddress);
      return true;
    }
    catch (AddressException ae)
    {
      // log exception
      return false;
    }
  }

  /**
   * Returns true if the last email field (i.e., the country code, or something
   * like .com, .biz, .cc, etc.) is two chars or more in length, which it really
   * must be to be legal.
   */
  private static boolean lastEmailFieldTwoCharsOrMore(String emailAddress)
  {
    if (emailAddress == null)
      return false;
    StringTokenizer st = new StringTokenizer(emailAddress, ".");
    String lastToken = null;
    while (st.hasMoreTokens())
    {
      lastToken = st.nextToken();
    }

    if (lastToken.length() >= 2)
    {
      return true;
    }
    else
    {
      return false;
    }
  }
}