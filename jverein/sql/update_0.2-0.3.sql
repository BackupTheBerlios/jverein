------------------------------------------------------------------------
-- $Source: /home/xubuntu/berlios_backup/github/tmp-cvs/jverein/Repository/jverein/sql/Attic/update_0.2-0.3.sql,v $
-- $Revision: 1.1 $
-- $Date: 2006/12/04 21:11:44 $
-- $Author: jost $
--
-- Copyright (c) by Heiner Jostkleigrewe
-- All rights reserved
--
------------------------------------------------------------------------
--
--
ALTER CREATE TABLE stammdaten
(
  id			INTEGER		default UNIQUEKEY('stammdaten'),
  name			VARCHAR(30) NOT NULL,
  blz			VARCHAR(8)  NOT NULL,
  konto         VARCHAR(10) NOT NULL,
  altersgruppen VARCHAR(50),
  UNIQUE        (id),
  PRIMARY KEY   (id)
);

------------------------------------------------------------------------
-- $Log: update_0.2-0.3.sql,v $
-- Revision 1.1  2006/12/04 21:11:44  jost
-- *** empty log message ***
--
-- Revision 1.1  2006/10/29 07:51:28  jost
-- Neu: Mitgliederstatistik
--
------------------------------------------------------------------------