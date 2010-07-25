/**********************************************************************
 * $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/src/de/jost_net/JVerein/Messaging/MitgliedskontoMessage.java,v $
 * $Revision: 1.1 $
 * $Date: 2010/07/25 18:45:23 $
 * $Author: jost $
 *
 * Copyright (c) by Heiner Jostkleigrewe
 * All rights reserved
 * heiner@jverein.de
 * www.jverein.de
 * $Log: MitgliedskontoMessage.java,v $
 * Revision 1.1  2010/07/25 18:45:23  jost
 * Neu: Mitgliedskonto
 *
 **********************************************************************/

package de.jost_net.JVerein.Messaging;

import de.willuhn.datasource.GenericObject;
import de.willuhn.jameica.hbci.messaging.ObjectMessage;

/**
 * Wird versendet, wenn der Mitgliedskonto-Baum sich geaendert hat.
 */
public class MitgliedskontoMessage extends ObjectMessage
{
  public MitgliedskontoMessage(GenericObject object)
  {
    super(object);
  }
}