/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/Messaging/FamilienbeitragMessage.java,v $
 * $Revision: 1.2 $
 * $Date: 2011/10/01 21:48:47 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 **********************************************************************/

package de.jost_net.JVerein.Messaging;

import de.willuhn.datasource.GenericObject;
import de.willuhn.jameica.hbci.messaging.ObjectMessage;

/**
 * Wird versendet, wenn der FamilienbeitragsTree geaendert hat.
 */
public class FamilienbeitragMessage extends ObjectMessage
{
  public FamilienbeitragMessage(GenericObject object)
  {
    super(object);
  }
}