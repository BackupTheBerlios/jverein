/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/Messaging/FamilienbeitragMessage.java,v $
 * $Revision: 1.1 $
 * $Date: 2011/08/01 18:28:11 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: FamilienbeitragMessage.java,v $
 * Revision 1.1  2011/08/01 18:28:11  jost
 * Neu: Familienmitglied aus Familienverband entfernen.
 *
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