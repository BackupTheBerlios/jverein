<? include ("frame.inc"); ?>
    <h1>Changelog</h1>
    <ul>
      <li><a name="v210">Version 2.1.0 noch nicht freigegeben</a></li>
        <ul>
          <li>Bugfix bei der Mitgliedersuche, sofern mit Zusatzfeldern experimentiert wurde.</li>
          <li>Basisdaten des Mitglieds k�nnen mit einem Rechtsklick kopiert werden. Siehe auch <a href='http://www.jverein.de/forum/viewtopic.php?t=542&p=2418'>Forum</a></li>
          <li>Mitglieder�bersicht: Korrekte Sortierung nach ID. Siehe auch <a href="http://www.jverein.de/forum/viewtopic.php?f=5&t=582&p=2436">Forum</a></li>
          <li>Import benutzerfreundlicher. Patch von Julian.</li>
          <li>Beim Import von Zusatzbetr�gen sind jetzt auch Anf�hrungszeichen als Feldbegrenzer erlaubt.</li>
          <li>Sofortige Aktualisierung der Anzeige nach Ist-L�schung im Mitgliedskonto. Patch von Julian.</li>
          <li>Neu: Spenden f�r JVerein.</li>
          <li>Neu: �bersicht Familienbeitr�ge. Doppelklick �ffnet das Mitglied. Rechtsklick bietet die M�glichkeit, das Mitglied aus dem Famlilienverband zu entfernen.</li>
          <hr>
          <li>Folgende �nderungen wurden nach Rev. 309 vorgenommen:</li>
          <li>Import: Spalte individuellerbeitrag ist keine Pflichtspalte</li>
          <li>Mitglieder-Statistik: Anpassung Ein- und Austritte an das Gesch�ftsjahr.</li>
          <li>Gesch�ftsjahr: Bugfix bei der Berechnung des Endes des Gesch�ftsjahres.</li>
          <li>Zusatzbetr�ge: Bugfix Default-Wert f�r das F�lligkeitsdatum</li>
          <li>##Familienmitglied k�nnen jetzt per Rechtsklick entfernt werden.</li>
          <li>Bei der L�schung von Eigenschaften wird jetzt auch die Zuordnung von Mitgliedern zur Eigenschaft gel�scht.</li>
          <li>Vermeidung NPE bei der Aufbereitung der Variablen f�r die Mahnung.</li>
          <li>Abrechnung: Vermeidung NPE bei fehlendem Stichtagsdatum.</li>
          <li>Mahnung: Korrekte Verarbeitung der Mitgliedskonto*-Variablen.</li>
          <hr>
          <li>Folgende �nderungen wurden nach Rev. 312 vorgenommen:</li>
          <li>Sterbedatum ist jetzt optional. Unter Einstellungen|Ansicht|Sterbedatum kann die Einstellung vorgenommen werden.</li>
          <li>Neu: Ausgabe Kontoauszug Mitgliedskonto.</li>
          <li>Mitglied: Anzeige der Mitgliedsnummer.</li>
          <li>Bugfix bei der Tabellenh�he der Arbeitsdienst�berpr�fung.</li>
          <li>Bugfix "�bernahme"-Button f�r Soll+Ist im Mitgliedskontoauswahldialog. <a href='http://www.jverein.de/forum/viewtopic.php?f=5&t=680'>Patch von Danzelot.</a></li>
          <li>Bugfix Filter �berpr�fung Arbeitseins�tze. Siehe <a href='http://www.jverein.de/forum/viewtopic.php?f=5&t=678'>Forum</a></li>
          <li>Neue Filteroption "alle" bei der �berpr�fung Arbeitseins�tze. Siehe <a href='http://www.jverein.de/forum/viewtopic.php?f=5&t=678'>Forum</a></li>
          <li>Mitgliedskontoauswahl: Bugfix OperationCanceledException und Widged is disposed. Siehe <a href='http://www.jverein.de/forum/viewtopic.php?f=5&t=679'>Forum</a>
          <li>Abrechnung/Beitragsmodell: Vereinfachung der Parameter, j�hrlich, halbj�hrlich, viertelj�hrlich und monatlich zu 'alle' bzw. 'gleicher Termin f�r alle'</a>
          <hr>
          <li>Folgende �nderungen wurden nach Rev. 315 vorgenommen:</li>
          <li>Bei der Speicherung der Einstellungen wird auf notwendige Plugins gepr�ft, wenn Dokumentenspeicherung aktiviert wurde.</li>
          <li>Bugfix Sortierung Buchungsartenliste. Siehe <a href='http://www.jverein.de/forum/viewtopic.php?t=693&p=2709#p2709'>Forum</a></li>
        </ul>
      </li>
      <li><a name="v201">Version 2.0.1 vom 29.6.2011</a></li>
        <ul>
          <li>Bugfix Autovervollst�ndigung Name beim Mitglied.</li>
          <li>Bugfix Erstinstallation mit H2-Datenbank</li>
          <li>Bugfix Summierung bei der kompakten Abbuchung.</li>
          <li>Personalbogen: Sortierung der Zusatzbetr�ge nach F�lligkeitsdatum.</li>
        </ul>
      </li>
    	<li><a name="v200">Version 2.0.0 vom 23.6.2011</a></li>
    		<ul>
    			<li>Termin�bersicht Geburtstage und Wiedervorlagen.</li>
					<li>Die Boxen "aktuelle Geburtstage" und "Wiedervorlagen" f�r die Startseite entfernt. Ersatz ist die neue Termin�bersicht.</li>
					<li>Neue Box "Termine" f�r die Startseite erstellt</li>
    			<li>Arbeitseins�tze</li>
					<li>Speicherung zus�tzlicher Adressen (u. a. Spender/innen) erm�glicht.</li>
    			<li>Speicherung von Dokumenten zu Buchungen und Mitgliedern. Siehe <a href='http://developer.berlios.de/bugs/?func=detailbug&bug_id=17106&group_id=7335'>#17106</a></li>
    			<li>Eigenschaftengruppen k�nnen so markiert werden, dass maximal eine Eigenschaft ausgew�hlt werden kann.  <a href='https://developer.berlios.de/bugs/?func=detailbug&bug_id=17741&group_id=7335'>#17741</a> </li>
    			<li>Erzeugung Sollbuchung bei Zuordnung des Mitgliedskontos.</li>
    			<li>Stammdaten des Vereins in die Einstellungen verschoben.</li>
          <li>Neu: Individueller Beitrag pro Mitglied. �berschreibt Betrag in der Beitragsgruppe.</li>
					<li>Beitragsgruppen k�nnen jetzt Buchungsarten zugewiesen werden. Diese werden bei der Verwendung des Mitgliedskontos bei der Abrechung (z. Z. nur neue Version) in die Ists�tze der Abbuchungsbetr�ge geschrieben.</li>
					<li>Freie Formulare eingef�hrt. Unter Administration|Formulare k�nnen die Formulare eingerichtet werden. Mit einem rechten Mausklick auf ein Mitglied werden im Kontextmen� auch die freien Formulare angezeigt. Ein Klick darauf erzeugt das Formular</li>
					<li>Buchungen k�nnen jetzt auch dem "Mitgliedskonto" eines Spenders zugewiesen werden.</li>
					<li>Zus�tzliche Datenfelder in Mails und Formatierung von Datums- und Betragsfeldern <a href='https://developer.berlios.de/bugs/?func=detailbug&bug_id=17648&group_id=7335'>#17648</a></li>
          <li>CSV-Export (Mitgliederauswertung) total �berarbeitet. Neue Spaltennamen. Eigenschaften werden mit ausgegeben. Zur Aktivierung des alten Exports in der Datei .jameica/cfg/de.jost_net.JVerein.gui.control.MitgliedControl.properties eine Zeile mit "auswertung.csv.kompatibilitaet=true" einf�gen</li>
					<li>Neu: PDF-Liste der Buchungsarten</li>
    			<li>Buchungsarten kann jetzt auch die Eigenschaft "Spende" gegeben werden.</li>
					<li>Unterst�tzung f�r das neue jameica.ical-Plugin. Installation siehe <a href='http://jameica.berlios.de/doku.php?id=support:install:jameica.ical'>Jameica-Wiki</a> </li>
          <li>Vereinheitlichung im Umgang mit den Variablen bei Formularen. Neue Variablennamen. Teilweise wurden die Werte konvertiert, teilweise muss von Hand nachgebessert werden. An den meisten Stellen stehen jetzt s�mtliche Daten des Mitglieds, Eigenschaften und Zusatzfelder zur Verf�gung.</li>
    		  <li>Neu: Funktion zur gleichzeitigen Zuordnung einer Eigenschaft an viele Mitglieder</li>
					<li>Neu: Spendenbescheinigung aus dem Mitgliedskonto (Automatische Spendenbescheinigung)</li>
					<li>Neu: Standardformular f�r Spendenbescheinigungen</li>
					<li>Neu: Spendenbescheinigung f�r Sachspenden</li>
					<li>Neu: Neues Abrechnungsmodul. Damit ist es m�glich, mit dem Parameter "kompakte Abbuchung" mehrere Abbuchungen von einem Konto zu einer Abbuchung zusammenfassen. Die interne Struktur des Moduls musste generell �berarbeitet werden. Daher ausgiebig testen. Das neue Modul steht zun�chst �ber den Parameter "Test neue Abbuchung" zur Verf�gung.</li>
					<li>Das Datum der letzten �nderung wird bei Mitgliedern und Adressen gespeichert. Die Anzeige erfolgt in der Treffer�bersicht. Dazu muss unter Administration|Einstellungen|Tabellen das entsprechende H�kchen gesetzt werden.</li>
    		  <li>Beim Versand von Mails k�nnen die Zusatzfelder mit $zusatzfeld.ZUSATZFELDNAME ausgegeben werden.</li>
    			<li>Manuelle Zahlungseing�nge und Rechnungen (bis 1.3) entfernt.</li>
    			<li>Bugfix beim Import von Zusatzfeldern. Bislang wurde nur der Datentyp "Zeichenfolge" korrekt importiert.</li>
    			<li>Bugfix beim Import. Sortierung der Daten ist nicht mehr erforderlich.</li>
    			<li>Mitgliedsstatus in die Mitgliedsauswertung aufgenommen.</li>
    			<li>Zusatzbetr�ge: Bugfix bei Buchungen ohne Intervall wird jetzt das F�lligkeitsdatum korrekt behandelt.</li>
    			<li>Personalbogen: Bugfix Eigenschaften.</li>
    			<li>Abbuchung: Verwendungszweck 2 ist jetzt auch 27 Zeichen (vorher 25 Zeichen)  lang</li>
    			<li>Mitgliedskonto: Bugfix Summen</li>
    			<li>Bugfix bei der Erfassung von Buchungen (Vorgabe Konto). <a href='https://developer.berlios.de/bugs/?func=detailbug&bug_id=17827&group_id=7335'>Bug #17827</a></li>
    			<li>Tastatursteuerung wegen Problemen mit Jameica/Hibiscus wieder entfernt.</li>
					<li>Bugfix in der Altersstatistik. �ber 100-j�hrige wurden in der Summe nicht ber�cksichtigt.Siehe <a href='http://www.jverein.de/forum/viewtopic.php?f=5&t=339'>Forum</a>.</li>
					<li>In den Einstellungen im Tab Anzeige kann jetzt die Verz�gerungszeit f�r die Suchfelder Name, Vorname und Strasse der Mitglieder und Adressen eingestellt werden.</li>
    			<li>Mailversand: Unterschiedliche Mitglieder k�nnen mit gleicher Adresse angeschrieben werden.</li>
					<li>Bugfix: �nderungen an den Einstellungen werden sofort wirksam.</li>
					<li>Bugfix: Bei den Spendenbescheinigungen wird der Betrag wie in der Vorschau positioniert.</li>
					<li>Warnung bei der Neuaufnahme von Arbeitseins�tzen, Lehrg�ngen, Wiedervorlagen und Zusatzbetr�gen, wenn das neue Mitglied noch nicht gespeichert ist.</li>
					<li>Colins Patch zur MySQL-Performancesteigerung.</li>
					<li>Bugfix bei der Umschl�sselung eines Mitgliedes von einer Beitragsart mit der Art Familie/Angeh�riger in eine andere Beitragsart.</li>
					<li>Bugfix Diagnose-Backup</li>
					<li>Liste Zusatzbetr�ge: Sortierung nach Namen aufgenommen. <a href='https://developer.berlios.de/bugs/?func=detailbug&bug_id=17793&group_id=7335'>#17793</a></li>
    			<li>Lehrgangsart in die �bersicht aufgenommen.</li>
    			<li>Vermeidung NPE bei der Erfassung von Lehrg�ngen.</li>
					<li>Zusatzbetr�ge: In den Verwendungszweck k�nnen nur noch die f�r DTAUS zul�ssigen Zeichen eingegeben werden.</li>
    		  <li>Keine Abrechnung von Zusatzbetr�gen bei ausgetretenen Mitgliedern</li>
    		  <li>Korrekte Sortierung von Ganzzahl-Zusatzfeldern in der Mitglieder�bersicht.</li>
    		  <li>Neu: Filter f�r die Lehrgangs�bersicht.</li>
    		  <li>Bugfix Abrechnung DTAUS mit ���� gr��er 27 Stellen.</li>
    		  <li>Bugfix Zusatzbetr�ge n�chste F�lligkeit.</li>
    		  <li>Bugfix Sollbuchung bearbeiten. Irrt�mlich wurde generell der Default-Zahlungsweg vorgegeben.</li>
    		  <li>Selektion der Mitglieder (Dialog und Auswertung) nach Zusatzfeldern.</li>
					<li>Die Texte von Mails und Mailvorlagen k�nnen jetzt 10.000 Zeichen lang sein.</li>
					<li>Arbeitseins�tze werden auf dem Personalbogen ausgegeben.</li>
          <li>Bugfix: Sortierung der Buchungen bleibt nach Bearbeitung der Buchungsart erhalten.</li>
          <li>Alternative Sortierung des Buchungsjournals nach Buchungsnummer</li>
          <li>Bei der Eingabe einer (Ist-)Buchung (Barzahlung) kann zun�chst das Mitgliedskonto ausgew�hlt werden. Name und Vorname des Mitgliedes, Betrag und Verwendungszweck der Sollbuchung sowie das aktuelle Tagesdatum werden in die Buchung eingetragen.</li>
          <li>Neu: Istbuchung kann vom Mitgliedskonto gel�st werden.</li>
          <li>�nderung der Zuordnung des Hibiscus-Kontos zum JVerein-Konto erm�glicht.</li>
          <li>Mitgliederliste: Bugfix bei der Sortierung nach Namen und Vornamen </li>
          <li>Korrektes Encoding beim Mailversand unter Ubuntu</li>
          <li>Buchungstext2 f�r Zusatzbetr�ge.</li>
          <li>Jahressaldo: Bugfix abweichendes Gesch�ftsjahr.</li>
          <li>Neu: Bei der Erstellung von Rechnungen k�nnen Abbucher ausgeschlossen werden.</li>
          <li>Bei der Erfassung der Mails kann mit einem Rechtsklick auf eine Mail-Adresse ein Dialog mit einer Liste aller Variablen angezeigt werden.</li>
          <li>Zus�tzliche Variablen: mitglied_anrede_foermlich und mitglied_anrede_du</li>
    		  <li>Abrechnung: Bugfix Textschl�ssel bei Hibiscusbuchungen</li>
          <li>Korrekte Darstellung von Buchungen in der globalen Suche.</li>
          <li>Buchung zum Mitgliedskonto zuordnen: Spezialsuche bei Namen mit Namensvors�tzen (von, di, de ...)</li>
          <li>In der Datenbank den Datentyp f�r booleans von char(5) auf boolean (H2) bzw. tinyint (MySQL) umgestellt.</li>
          <li>Foreign Key Arbeitseinsatz->Mitglied aufgenommen.</li>
          <li>Bei Zusatzbetr�gen und Wiedervorlagen Button zum Mitglied eingebaut.<a href='http://www.jverein.de/forum/viewtopic.php?t=583&p=2246#p2246*'>Siehe hier</a></li>
    		</ul>
	   	<li><a name="v140">Version 1.4.0 vom 10.11.2010</a></li>
    		<ul>
    			<li>Box der aktuellen Geburtstage: Durch Doppelklick kann das Mitglied direkt bearbeitet werden. 
    				Das Kontextmen� steht ebenfalls zur Verf�gung.Zus�tzlich Telefonnummern und 
    				Email-Adresse aufgenommen.</li>
    			<li>Scrollbar f. Buchung aufgenommen.</li>
    			<li>Gr��e der Kontoauswahlbox der Buchf�hrung ver�ndert.</li>
    			<li>Bugfix beim Import des Zahlungsrhytmusses</li>
    			<li>Erste Version des Mitgliedskontos</li>
    			<li>Rechnungen: F�r ein Mitglied werden ggfls. mehrere Positionen auf eine Rechnung
    			    zusammengefasst. Achtung! Die Formularfelder "Zahlungsgrund1" und "Zahlungsgrund2"
    			    sind durch das Formularfeld "Zahlungsgrund" zu ersetzen.
    			<li>Im Formularfeldeditor kann jetzt auch direkt das Formular angezeigt werden.</li>
 				<li>Featurerequest <a href='http://developer.berlios.de/bugs/?func=detailbug&bug_id=17284&group_id=7335'>#17284</a>
 					Unter Einstellungen&gt;Rechnungen k�nnen die Texte eingegeben werden. Beim Text f�r die Abbuchung
 					k�nnen die Variablen ${Konto} und ${BLZ} in den Text eingemischt werden.</li>
 				<li>Bugfix: Abgeschlossene Buchungen k�nnen nicht mehr gel�scht werden.</li>
 				<li>Optimierung der Bedienung per Tastatur. Siehe auch <a href='https://developer.berlios.de/bugs/?func=detailbug&bug_id=17221&group_id=7335'#17221> 
 				    und <a href='http://www.jverein.de/forum/viewtopic.php?f=1&t=143'>Tastaturbedienung</a></li>
 				<li>Foto des Mitglieds kann gespeichert werden.</li>
 				<li>Ausdruck von Personalb�gen mit allen Daten eines Mitgliedes.</li>
 				<li>Buchungen werden bei der Sortierung nach ID jetzt numerisch sortiert. Siehe auch <a href='http://www.jverein.de/forum/viewtopic.php?f=5&t=182&p=731#p731'>Forum</a>
    			<li>Eigenschaftengruppen k�nnen jetzt das Merkmal 'Pflicht' haben. Dann muss mindestens eine Eigenschaft ausgew�hlt werden. Siehe auch <a href='https://developer.berlios.de/bugs/?func=detailbug&bug_id=17217&group_id=7335'>#17217</a> und <a href='http://www.jverein.de/forum/viewtopic.php?f=1&t=142'>Forum</a></li>
    			<li>Mitgliederliste im PDF-Format: Ausgabe der Eigenschaften.</li>
                <li>Buchf�hrung: �berall, wo ein Bankkonto auszuw�hlen ist, wird das zuletzt genutzte vorgegeben.</li>   	
    			<li>Bugfixes bei der Erstellung des Diagnose-Backups.</li>
    			<li>Maximale L�nge des Textes f�r Mails und Mailvorlagen auf 10.000 Zeichen verl�ngert.</li>
    			<li>Bei der Erstellung eines Jahresabschlusses werden jetzt die Anfangsbest�nde des Folgejahres eingetragen. Bei der L�schung von Jahresabschl�ssen werden die Anfangsbest�nde des Folgejahres gel�scht. Siehe auch <a href='https://developer.berlios.de/bugs/?func=detailbug&bug_id=16845&group_id=7335'><#16845></a></li>
    		    <li>L�nge der Kontobezeichnung auf 255 Zeichen verl�ngert.</li>
    			<li>Hibiscus-Import der Konten checkt jetzt auf doppelte Konten.</li>
    			<li>Ausgabe der Zusatzbuchungen im PDF-Format. <a href='https://developer.berlios.de/bugs/?func=detailbug&bug_id=17513&group_id=7335'>#17513</a>
    			<li>Datenimport: Klare Fehlermeldung bei korrupter Import-Datei im Bereich der Eigenschaften. Patch von Umbilo.</li>
    			<li>Datenimport: Eigenschaftengruppen k�nnen jetzt auch importiert werden.</li>
    			<li>Buchungen k�nnen die Kontoauszugsinformationen (Auszug, Blatt) en bloc zugewiesen werden.</li>
    			<li>Vermeidung NullPointerException bei der Suche nach Kursteilnehmern.</li>
    			<li>Intern: Hilfe in die Views verlagert</li>
    			<li>Import Hibiscus-Konten: Check auf doppelte Konten</li>
    			<li>Bei der Neuaufnahme einer manuellen Buchung wird sofort nach der Speicherung die ID angezeigt.</li>
    			<li>Korrekter Hinweis auf fehlende Stammdaten bei der Abrechnung.</li>
    			<li>Buchung: Dropdown-Liste Buchungsart alphabetisch sortiert</li>
    			<li>Import: Vermerk1 und Vermerk2 k�nnen jetzt importiert werden.</li>
    			<li>Mitglieder im Ausland: Unter Einstellungen kann jetzt ein entsprechendes H�kchen gesetzt werden. Anschlie�end kann der Staat beim Mitglied erfasst werden.</li>
				<li>Sterbetag aufgenommen.</li>
				<li>Bugfix bei leerer/neuer Datenbank.</li>
				<li>Zusatzbetr�ge-Import: Im Fehlerfall wird der Name des Mitglieds mit ausgegeben.</li>
    		</ul>

	   	<li>Version 1.3.2 vom 18.05.2010</li>
    		<ul>
    			<li>Bugfix bei der Behandlung ausgetretener Mitglieder</li>
    		</ul>
	   	<li>Version 1.3.1 vom 16.05.2010</li>
    		<ul>
    			<li>Zus�tzliche Pr�fung der Bankverbindung bei der Abrechnung</li>
    			<li>Fehlendes Foreign-Key-Constraint Eigenschaften/Mitglied eingef�gt.</li>
				<li>Bugfix Eigenschaften</li>
				<li>Aktuelle Geburtstage: Korrekte Behandlung ausgetretener Mitglieder</li>
				<li>Buchungsklassen-Saldo: �berschrift korrigiert.</li>
				<li>Programminterner einheitlicher Umgang mit ausgetretenen Mitgliedern</li>
				<li>Bugfix Einrichtung MySQL-Datenbank</li>
    		</ul>

	   	<li>Version 1.3.0 vom 09.04.2010</li>
    		<ul>
    		    <li>Abrechnung: Mitgliedsnummer in den Verwendungszweck aufgenommen.</li>
    		    <li>Bugfix beim Import des Eintrittsdatums.</li>
    			<li>Adressbuchexport ins Mail-Men� verschoben.</li>
    		    <li>Mailfunktion um Dateianh�nge erweitert.</li>
    			<li>IBAN direkt bei der Eingabe von Kontonummer und BLZ berechnen</li>
    		    <li>Liste der Zusatzbetr�ge: true/false wird als X bzw. _ dargestellt</li>
    		    <li>Mitgliedsdaten: Der zuletzt ausgew�hlte Tab wird bei Bet�tigung von "zur�ck" aktiviert.</li>
    		    <li>Erste Version eines einfachen Programms zum Versand von Mails.</li>
    		    <li>Standardwerte f�r Zahlungsweg und Zahlungsrhytmus bei der Neueingabe von Mitgliedern k�nnen unter Administration|Einstellungen|Beitr�ge eingestellt werden. <a href='https://developer.berlios.de/bugs/?func=detailbug&bug_id=16605&group_id=7335'>#16605</a></li>
    			<li>Spendenbescheinigung: Existierende Spendenbescheinigung kann als Vorlage f�r eine neue Spendenbescheinigung verwendet werden.</li>
    			<li>Neu: Konkrete Fehlermeldung, wenn bei der Erstellung der Altersjubil�umsliste der Eintrag in den Stammdaten fehlt.</li>
    			<li>Zusatzfeldern k�nnen jetzt Datentypen zugeordnet werden</li>
    			<li>Zusatzfelder: Bei der L�schung einer Felddefinition wird nach R�ckfrage auch der Inhalt der Datenfelder beim Mitglied gel�scht.</li>
    			<li>Buchf�hrung: Mehrere Buchungen k�nnen gleichzeitig gel�scht werden.</li>
    			<li>Buchf�hrung: Mehrfache Buchungs�bernahme verhindert.</li>
    			<li>Bugfix beim Austritt aller Mitglieder einer Familie (Familienbeitrag)</li>
    			<li>Bugfix fehlerhafte Kontonummer bei der Eingabe und bei der Abrechnung</li>
    			<li>Bei Manuellen Zahlungseing�ngen gibt es jetzt Mehrfachselektion.<a href='http://developer.berlios.de/bugs/?func=detailbug&bug_id=16431&group_id=7335'>#16431</a></li>
    			<li>Neu: Eigenschaften k�nnen jetzt in Eigenschaftengruppen zusammengefasst werden.</li>
    			<li>Eigenschaften k�nnen importiert werden. <a href='https://developer.berlios.de/bugs/?func=detailbug&group_id=7335&bug_id=16162'>#16162</a></li>
    			<li>DB-Update restore: Meldung bei nicht leerer Datenbank</li>
    			<li>Mitglied: Anzeige IBAN</li>
    			<li>Neu: Import von Zusatzbetr�gen. Sowohl Default-Format als auch individuelle Formate.<a href='https://developer.berlios.de/bugs/?func=detailbug&group_id=7335&bug_id=16336'>#16336</a></li>
    			<li>Die Art der Buchungsart (Einnahme, Ausgabe, Umbuchung) wurde bei der Bearbeitung generell auf Einnahme gesetzt.</li>
    			<li>Lauff�higkeit mit den aktuellen Nightly-Builds von Jameica 1.9 und Hibiscus 1.11 hergestellt.</li>
    			<li>Vermeidung NullPointerException beim Jahressaldo.</li>
    			<li>Neu: Buchungsklassen zur Zusammenfassung von Buchungsarten. siehe auch <a href='buchfuehrungzusammenhaenge.php'>Zusammenh�nge der Buchf�hrung</a>.</li>
    			<li>Neu: Buchungsjournal, Feature Request <a href='https://developer.berlios.de/bugs/?func=detailbug&bug_id=16103&group_id=7335'>#16103</a></li>
    			<li>Auswertung Mitglieder: Mitglieder mit Austrittsdatum in der Zukunft werden mit ausgewertet. Bug <a href='https://developer.berlios.de/bugs/?func=detailbug&bug_id=16223&group_id=7335'>#16223</a></li>
    			<li>Bugfix beim Import des Feldes Zahlungsrhytmus.</li>
    		</ul>
    	<li>Version 1.2 vom 23.8.2009</li>
    		<ul>
    			<li>Bugfix beim Import von Mitgliedern mit dem Zahlungsweg "Barzahlung".</li>
    			<li>Bugfix bei den Abrechungen. �berlange Namen.</li>
    			<li>Bugfix bei den Abrechungen. Spezialfall mehrere F�lligkeiten im Jahr und gleichzeitig beitragsfreie Beitragsgruppe.</li>
    		    <li>Neue Box "aktuelle Geburtstage". Wird unter dem Men�punkt Jameica mit dem Button "Startseite anpassen" aktiviert.</li>
    		    <li>Bugfix bei der Erstellung von Rechnungen</li>
    		    <li>Mitglied: Zahlungsdaten in eignen Tab verschoben. Platzoptimierung</li>
    		    <li>Bessere Fehlermeldung bei der L�schung von Beitragsgruppen, denen noch Mitglieder zugeordnet sind. Bug <a href='https://developer.berlios.de/bugs/?func=detailbug&bug_id=16223&group_id=7335'>#16223</a></li>
    		    <li>Bei der Auswahl des Geschlechts werden jetzt die Langtexte m�nnlich und weiblich angezeigt.</li>
    		    <li>Bugfix FirstStart: Jetzt wird auch die Existenz von Beitragsgruppen abgefragt</li>
    			<li>Die Men�punkte von Plugins>JVerein ins Hauptmen� kopiert</li>
    			<li>Mitglied: Name, Vorname und Stra�e als Suchfelder (Auto-Vervollst�ndigung)</li>
    			<li>Bugfix: L�schen von Mitgliedern mit Zusatzfeldern.</li>
    			<li>Neu: Juristische Personen (Firmen, Organisationen, Beh�rden) k�nnen als Mitglied gespeichert werden.</li>
    			<li>Neu: <a href="lehrgaenge.php">Lehrg�nge</a> </li>
    			<li>Zus�tzliche Felder zur Rechnungserstellung. 
    				Siehe #<a href="https://developer.berlios.de/bugs/?func=detailbug&amp;bug_id=15062&amp;group_id=7335">15062</a></li>
    			<li>Bugfix "Fehler nach L�schung einer Beitragsgruppe". Bug #<a href="https://developer.berlios.de/bugs/?func=detailbug&amp;bug_id=15301&amp;group_id=7335">15301</a></li>
    			<li>Mitgliederstatistik: Bugfix Altersgruppen und Ber�cksichtigung des Eintrittsdatums.</li>
    			<li>Konfiguration: EMail-Adresse kann als Spalte ausgew�hlt werden.</li>
    			<li>Abrechnung: Formularwerte speichern und wiederherstellen.</li>
    			<li>Anrede in die Spaltenauswahl aufgenommen.</li>
    			<li>Bugfix Zusatzbetr�ge</li>
    			<li>Silbentrennung in Reports aufgenommen</li>
    			<li>Icons auf Buttons</li>
    			<li>Spendenbescheinigung um 'Ersatz f�r Aufwendungen' erweitert</li>
    			<li>Mitglieder ohne Eintrittsdatum werden jetzt ber�cksichtigt. 
    			    Bug #<a href="https://developer.berlios.de/bugs/?func=detailbug&amp;bug_id=15132&amp;group_id=7335">15132</a></li>
    		</ul>
    	</li>
    	<li>Version 1.1.1 vom 19.1.2009
    		<ul>
    			<li>07.01.2009
    				<ul>
    					<li>Unter MySQL kam es zu einem Fehler beim L�schen von Zusatzfeldern. 
    						Bug #<a href="https://developer.berlios.de/bugs/?func=detailbug&amp;bug_id=15024&amp;group_id=7335">15024</a></li>
    				</ul>
    			</li>
    			<li>04.01.2009
					<ul>
						<li>Mehreren Buchungen kann gleichzeitig eine Buchungsart zugeordnet werden.</li>
					</ul>
				</li>
				<li>03.01.2009
					<ul>
						<li>Abrechnung erfolgt irrt�mlicherweise auch f�r ausgetretene Mitglieder (da hatte 
							ich einen Bug entfernt und gleichzeitig einen neuen eingebaut ;-) 
							Bug #<a href="http://developer.berlios.de/bugs/?func=detailbug&amp;bug_id=14996&amp;group_id=7335">14996</a>
						</li>
					</ul>
				</li>
				<li>02.01.2009
					<ul>
						<li>Rechnungen auch f�r Zusatzbetr�ge</li>
					</ul>
				</li>
				<li>01.01.2009
					<ul>
						<li>Beim L�schen von Rechnungen wurde eine NullPointerException geworfen. 
							Bug #<a href="https://developer.berlios.de/bugs/?func=detailbug&amp;bug_id=14989&amp;group_id=7335">14989</a></li>
					</ul>
				</li>
				<li>30.12.2008
					<ul>
						<li>Icons auf 16x16 Pixel skaliert. 
							Bug #<a href="https://developer.berlios.de/bugs/?func=detailbug&amp;bug_id=14979&amp;group_id=7335">14979</a></li>
						<li>Fehlende Icons in Kontextmen�s erg�nzt. 
							Bug #<a href="https://developer.berlios.de/bugs/?func=detailbug&amp;bug_id=14980&amp;group_id=7335">14980</a></li>
						<li>Summenzeilen in der Buchungsliste korrekt ausgeben. 
							Bug #<a href="https://developer.berlios.de/bugs/?func=detailbug&amp;bug_id=14978&amp;group_id=7335">14978</a></li>
						<li>Anpassung an neue Jameica-Versionierung.</li>
					</ul>
				</li>
			</ul>
		</li>
		<li>Version 1.1.0.1 vom 29.12.2008
			<ul>
				<li>Korrekte Verarbeitung wenn Eintritts- und/oder Geburtsdatum des Mitglieds fehlen. 
					Bug #<a href="http://developer.berlios.de/bugs/?func=detailbug&amp;bug_id=14975&amp;group_id=7335">14975</a></li>
				<li>Bugfix Foreign Key unter MySQL</li>
				<li>Bugfix Formularanzeige</li>
				<li>Bugfix Tabelle buchungsart f�r MySQL</li>
			</ul>
		</li>
		<li>Version 1.1 vom 28.12.2008
	<ul>
	<li>27.12.2008
		<ul>
			<li>Bugfix Booleans aus MySQL-DB lesen</li>
			<li>About-Dialog gibt jetzt auch Build-Datum und -Nummer aus.</li>
			<li>JVerein pr�ft, ob die passende Jameica-Version installiert wurde.</li>
		</ul>
	</li>
	<li>24.12.2008
		<ul>
			<li>Bei Altersjubil�en wird jetzt das Geburtsdatum anstatt des Eintrittsdatums ausgegeben.</li>
		</ul>
	</li>
	<li>23.12.2008
		<ul>
			<li>Vermeidung von NullpointerExceptions nach Import von leeren Kommunikationsdaten</li>
		</ul>
	</li>
	<li>22.12.2008
		<ul>
			<li>Beta3 herausgegeben.</li>
			<li>Zusatzabbuchungen-&gt;Zusatzbetrag</li>
			<li>Icons ins Men� und in die Kontextmen�s aufgenommen.</li>
			<li>Telefonnummern auf 20 Stellen verl�ngert.</li>
			<li>Bugfix MySQL-Support. Siehe auch ge�nderte 
				<a href="dokumentationmysql.php">Installationsbeschreibung</a></li>
		</ul>
	</li>
	<li>19.12.2008
		<ul>
			<li>Bugfix F�lligkeitsberechnung Zusatzabbuchungen</li>
		</ul>
	</li>
	<li>18.12.2008
		<ul>
			<li>Bugfix Import: Adressierungszus�tze</li>
			<li>Bugfix Dropdown-Box Zahlungsweg</li>
			<li>Abrechnung: Mitglieder mit einem Eintrittsdatum in der Zukunft bleiben unber�cksichtigt.</li>
			<li>Abrechnung: Beim Beitragsmodell 'monatlich mit monatl., viertel-, halb- oder j�hrlicher 
				Zahlungsweise' wird jetzt auch 'eingetretene Mitglieder' angeboten.</li>
		</ul>
	</li>
	<li>14.12.2008
		<ul>
			<li>Bug im Zusammenhang mit Drop-Down-Boxen gefixed.</li>
			<li>Beta2 herausgegeben.</li>
		</ul>
	</li>
	<li>11.12.2008
		<ul>
			<li>Beta-Phase er�ffnet. Zwischen den Feiertagen wird, sofern keine schwerwiegenden Fehler 
				auftreten, die Version 1.1 freigegeben.</li>
			<li>Redaktionelle �nderung an der Buchungsliste.</li>
		</ul>
	</li>
	<li>04.12.2008
		<ul>
			<li>Handy in den CSV-Export aufgenommen.</li>
		</ul>
	</li>
	<li>03.12.2008
		<ul>
			<li>Buchungen der Buchf�hrung um Auszugs- und Blattnummer erweitert.</li>
		</ul>
	</li>
	<li>30.11.2008
		<ul>
			<li>Spalten der Mitglieder�bersicht sind jetzt konfigurierbar. Die Konfiguration f�r alle 
				�bersichten ist vorgesehen und wird sp�ter realisiert.</li>
			<li>Bugfix: F�r die Auswertung Zusatzabrechnungen erfolgt jetzt auch der Aufruf des 
				Anzeigeprogrammes.</li>
		</ul>
	</li>
	<li>16.11.2008
		<ul>
			<li>Verschiebung der Speicherung der Programmeinstellungen von einer Property-Datei in 
				die Datenbank zur Vermeidung von Problemen in Multi-User-Umgebungen.</li>
		</ul>
	</li>
	<li>13.11.2008
		<ul>
			<li>Mitglied: Adressierungszusatz aufgenommen (Mitgliedsdaten, PDF-Liste, CSV-Export, Import)</li>
		</ul>
	</li>
	<li>11.11.2008
		<ul>
			<li>Einstellungen: Neu gruppiert und mit Scrollbars versehen.</li>
			<li>Mitglieder: Suche nach Geschlecht im Dialog und in den Auswertungen implementiert. 
				Such-Masken 2spaltig formatiert.</li>
		</ul>
	</li>
	<li>30.09.2008
		<ul>
			<li>Kursteilnehmer k�nnen nach Namen oder Eingabedatum gefiltert werden.</li>
			<li>Abrechnungsinformationen k�nnen nach Datum oder Verwendungszweck gefiltert werden.</li>
		</ul>
	</li>
	<li>29.09.2008
		<ul>
			<li>Unter Plugins|JVerein|Erweitert kann jetzt ein Backup im XML-Format erzeugt und anschlie�end 
				in eine leere Datenbank importiert werden. Ben�tigt Jameica-Nightly-Build &gt;= 30.9.2008</li>
		</ul>
	</li>
	<li>28.09.2008
		<ul>
			<li>Bug #<a href="https://developer.berlios.de/bugs/?func=detailbug&amp;bug_id=14496&amp;group_id=7335">14496</a> gefixed.</li>
		</ul>
	</li>
	<li>21.09.2008
		<ul>
			<li>Beim Start wird gepr�ft, ob die korrekten Versionen von Jameica und Hibiscus installiert sind.</li>
			<li>Bei den <a href="dokumentationauswertungjubilaeen.php">Jubil�en</a> k�nnen jetzt Altersjubilare 
				ausgewertet werden. Unter <a href="administration_stammdaten.php"> Stammdaten</a> 
				werden die Geburtstage konfiguriert.</li>
		</ul>
	</li>
	<li>16.09.2008
		<ul>
			<li>Neu: <a href="rechnungen.php">Rechnung</a></li>
			<li>Abbuchung hei�t jetzt Abrechnung.</li>
		</ul>
	</li>
	<li>04.09.2008
		<ul>
			<li>SearchProvider f�r die neue Jameica-Suchmaschine (Mitglieder und Kursteilnehmer)</li>
		</ul>
	</li>
	<li>19.07.2008
		<ul>
			<li>Neu: <a href="administration_formulare.php">Formulare</a></li>
			<li>Neu: <a href="spendenbescheinigung.php">Spendenbescheinigung</a></li>
		</ul>
	</li>
	<li>12.07.2008
		<ul>
			<li>Bugfix beim CSV-Export mit leeren Zusatzfeldern</li>
		</ul>
	</li>
	<li>10.07.2008
		<ul>
			<li>PDF-Export der Buchungen jetzt entweder als Einzelbuchungen oder als Summen</li>
			<li>Optimierung der internen Reporter-Klasse</li>
			<li>Mitgliederliste auf den Reporter umgestellt. Kommunikationsdaten aufgenommen.</li>
			<li>Bugfix im Script zur Erzeugung der Datenbank</li>
		</ul>
	</li>
	<li>09.07.2008
		<ul>
			<li>Abbuchung: Fehlermeldungen von <a href="http://obantoo.berlios.de/">OBanToo</a> werden 
				an die Oberfl�che gebracht</li>
		</ul>
	</li>
	<li>09.07.2008
		<ul>
			<li>Freigabe der Buchf�hrung zum allgemeinen Test</li>
		</ul>
	</li>
	<li>29.06.2008
		<ul>
			<li>Alpha-Version der neuen Buchf�hrung</li>
			<li>Neues Datenfeld zur Speicherung der Handy-Nummer des Mitglieds. Import angepasst.</li>
		</ul>
	</li>
	<li>21.06.2008
		<ul>
			<li>Bugfix: "von-Datum" bei der Abbuchung wird jetzt korrekt dargestellt</li>
		</ul>
	</li>
	<li>07.05.2008
		<ul>
			<li>Beim wiederholten Import werden jetzt alle notwendigen Tabellen gel�scht</li>
		</ul>
	</li>
	<li>05.05.2008
		<ul>
			<li>Bug #<a href="https://developer.berlios.de/bugs/?func=detailbug&amp;bug_id=13726&amp;group_id=7335">13726</a> gefixed</li>
			<li>Bugfix NPE bei Zusatzfeldern</li>
		</ul>
	</li>
	<li>10.04.2008
		<ul>
			<li>Speicherung von Zusatzfeldern bei den Mitgliedern 
				(<a href="administration_felddefinitionen.php">Definition</a>, 
				<a href="mitglied.php#Zusatzfelder">Mitglied</a>, 
				<a href="administration_import.php">Import</a>, 
				 <a href="dokumentationauswertungmitglieder.php">Export</a>).</li>
		</ul>
	</li>
	<li>04.04.2008
		<ul>
			<li>L�nge des Feldes Titel auf 20 verl�ngert, L�nge des Feldes PLZ auf 10 verl�ngert.</li>
			<li>Bugfix: Anzeige der Hilfe unter Windows.</li>
		</ul>
	</li>
	<li>03.04.2008: Scrollbars in der Mitglieder-Detail-Ansicht von Olaf �bernommen. Neues Nightly von 
		Jameica erforderlich.</li>
</ul>
</li>
<li>Version 1.0 vom 25.03.2008
	<ul>
		<li>Import des Zahlungsrhytmusses implementiert.</li>
		<li>Neu: In die Jameica-Startseite kann eine �bersicht �ber die Wiedervorlagen eingeblendet werden.</li>
		<li>Die Statistik bezieht sich jetzt auf einen Stichtag. Mitglieder, die nach dem Stichtag 
			ausgetreten sind, werden noch als Mitglieder gewertet.</li>
		<li>In der Statistik wird jetzt die Anzahl der Anmeldungen und Abmeldungen zwischen dem 1.1. und 
			dem eingegebenen Stichtag ausgegeben.</li>
		<li>Bei der Abbuchung kann jetzt die DTAUS-Datei ins PDF-Format konvertiert werden.</li>
		<li>PDF-Dokumentation durch Link zu diesem Wiki ersetzt.</li>
		<li>Neu: Jubil�umsliste</li>
		<li>Direkte �bernahme der Lastschriften als Einzel- oder Sammellastschriften in 
			<a href="http://www.willuhn.de/products/hibiscus/">Hibiscus</a>.</li>
		<li>Bei den <a href="administration_einstellungen.php">Einstellungen</a> k�nnen jetzt 
			Dateinamenmuster vorgegeben werden. Dieses Muster wird bei der Erzeugung von Dateinamen 
			verwendet.</li>
		<li>Erweiterung um Hilfefunktion.</li>
		<li>Bei Neuinstallationen wird jetzt standardm��ig die H2-Datenbank installiert.</li>
		<li>Bug #<a href="https://developer.berlios.de/bugs/?func=detailbug&amp;bug_id=12860&amp;group_id=7335">12860</a>
			gefixed </li>
		<li>Zu jedem Mitglied k�nnen Eigenschaften gespeichert werden. Diese Eigenschaften k�nnen im Dialog 
			und bei der Auswertung in die Selektion einbezogen werden. 
			Siehe <a href="https://developer.berlios.de/bugs/?func=detailbug&amp;bug_id=12862&amp;group_id=7335">#12862</a></li>
		<li>Erweiterung der Filterkriterien bei der Mitgliedersuche.</li>
		<li>h2.jar entfernt. Wird durch Jameica mitgeliefert.</li>
		<li><a href="dokumentationmysql.php">MySQL-Support</a> von Michael Trapp �bernommen.</li>
		<li>Bei der Abbuchung wird jetzt ein Stichtag abgefragt. Dieser Stichtag wird zur Pr�fung herangezogen, 
			ob ein ausgetretenes Mitglied noch zahlen muss. 
			Siehe <a href="https://developer.berlios.de/bugs/?func=detailbug&amp;bug_id=12861&amp;group_id=7335">#12861</a></li>
		<li>Bei der Abbuchung k�nnen jetzt die Jahres-, Halbjahres- und Vierteljahresabbuchungen auch separat 
			ausgef�hrt werden. Siehe <a href="https://developer.berlios.de/bugs/?func=detailbug&amp;bug_id=12861&amp;group_id=7335">#12861</a> 
			und <a href="https://developer.berlios.de/bugs/?func=detailbug&amp;bug_id=13021&amp;group_id=7335">#13021</a>.</li>
		<li>Bugfix bei der Ber�cksichtigung des Austrittsdatums bei der Mitgliederauswertung.</li>
		<li>Bugfix bei der Abbuchung: Zusatzabbuchung und Kursteilnehmer nur abbuchen, wenn entsprechendes 
			H�kchen gesetzt ist.</li>
		<li>MySQL-Treiber entfernt. Wird jetzt von Jameica mitgeliefert.</li>
		<li>CSV-Export erweitert um die Bezeichnung der Beitragsgruppe.</li>
		<li>Bugfix: Bei der Mitgliedersuche wurden auch Eintritts- und Austrittsdaten der Auswertung 
			ber�cksichtigt.</li>
		<li>Workaround f. Bug in Jameica</li>
	</ul>
</li>
<li>Version 0.9.4 vom 16.12.2007
	<ul>
		<li>Bugfix Update-Mimik/Migration. Update und Migration '�berholten sich'.</li>
	</ul>
</li>
<li>Version 0.9.3 vom 03.12.2007
	<ul>
		<li>Deployment-Bug: Es wurde nicht alles komplett ausgeliefert.</li>
	</ul>
</li>
<li>Version 0.9.2 vom 02.12.2007
	<ul>
		<li>Mitgliedersuche: Einschr�nkung auf angemeldete oder abgemeldete Mitgliedschaft</li>
		<li>Umstellung auf die H2-Datenbank</li>
		<li>Bugfix bei Zusatzabbuchungen ohne Interval</li>
		<li>Dokumentation: SPG-Export-Hardcopy auf Forderung von SPG durch textuelle Beschreibung ersetzt.</li>
		<li>Wegfall der Einstellung "Standardtab bei Mitgliedersuche". Es wird immer der zuletzt ausgew�hlte 
			Tab angezeigt.</li>
		<li>Neu: Geburtstagsliste bei der Auswertung der Mitglieder</li>
		<li>Intern: Neue Mimik f�r das Update der Datenbankstruktur</li>
		<li>Neues Beitragsmodell</li>
		<li>Bugfix wiederkehrende Zusatzabbuchungen</li>
	</ul>
</li>
<li>Version 0.9.1 vom 04.09.2007
	<ul>
		<li>Beschr�nkung der L�nge des Vereinsnamens auf 27 Stellen wegen Problemen mit der Abbuchung</li>
		<li>Bugfix bei der Abbuchung, wenn es keine beitragsfreie Beitragsgruppe gibt</li>
		<li>Neuer Konfigurationsbildschirm im Plugins-Men�: Es kann bestimmt werden, ob das Geburtsdatum 
			und das Eintrittsdatum ein Pflichtfeld ist und ob die Reiter Kommunikationsdaten, 
			Zusatzabbuchungen, Vermerke und Wiedervorlage sowie die Kursteilnehmer angezeigt werden. 
			Bug #11762</li>
		<li>Einstellungsdialog erweitert um den Standard-Tab f�r die Mitglieder-Suche</li>
		<li>Bug <a href="https://developer.berlios.de/bugs/?func=detailbug&amp;bug_id=11763&amp;group_id=7335">#11763</a> gefixed</li>
		<li>Bug <a href="https://developer.berlios.de/bugs/?func=detailbug&amp;bug_id=11764&amp;group_id=7335">#11764</a> gefixed</li>
		<li>Bug <a href="https://developer.berlios.de/bugs/?func=detailbug&amp;bug_id=11819&amp;group_id=7335">#11819</a> gefixed</li>
		<li>Korrekte Anzeige der Pflichtfelder beim Mitglied. Achtung! Wer Wert auf dieses Feature legt, 
			muss ein Jameica- Nighly-Build &gt;= 30.8.2007 einsetzen. Ansonsten werden BLZ und Kontonummer 
			nicht als Pflichtfeld angezeigt.</li>
		<li>Mitglieder k�nnen jetzt �ber das Context-Men� bearbeitet und gel�scht werden</li>
		<li>Beitragsgruppen k�nnen jetzt �ber das Context-Men� mit einem rechten Mausklick oder einen 
			Button gel�scht werden.</li>
	</ul>
</li>
<li>Version 0.9 vom 20.07.2007
	<ul>
		<li>�berpr�fung der Datenbank-Struktur beim Startup.</li>
		<li>Wiederkehrende Zusatzabbuchungen</li>
		<li>Bei der Neuerfassung von Mitgliedern wird nach der Eingabe der PLZ der Ort automatisch ermittelt, 
			sofern er bereits bei mindestens einem Mitglied gespeichert ist</li>
		<li>Bugfix in der SQL-DDL-Update-Routine</li>
		<li>Vermeidung ClassNotFoundException</li>
		<li>Bugfix bei der Abbuchung mit mehr als einer beitragsfreien Beitragsgruppe</li>
		<li>Neu: Wiedervorlagetermin bei jedem Mitglied und �bersicht �ber alle Termine</li>
		<li>Neu: Auswertung Kursteilnehmer</li>
		<li>Bugfix: Stammdatenverwaltung</li>
		<li>Neu: Handbuch im PDF-Format</li>
		<li>Neu: Handbuch kann �ber Plugins|JVerein|Handbuch aufgerufen werden</li>
	</ul>
</li>
<li>Version 0.7 vom 27.03.2007
	<ul>
		<li>Zeit-Optimierung bei der Anzeige der Mitglieder-Suche</li>
		<li>Neu: Bei der Suche der Mitglieder gibt es jetzt einen Tab mit allen Mitgliedern</li>
		<li>Kursteilnehmer: Abbuchungsdatum kann zur�ckgesetzt werden</li>
		<li>Beitragsgruppe: Beitragsart kann jetzt festgelegt werden</li>
		<li>Bugfix Import. Jetzt k�nnen, wie dokumentiert, beliebige Dateinamen verwenden werden</li>
		<li>Erweiterung Import um Zahlungsweg</li>
		<li>Zus�tzliche Plausibilit�tspr�fung der Import-Datei</li>
		<li>Herstellung von Familienverb�nden f�r Familientarife</li>
		<li>Zus�tzliche Plausi bei der Abbuchung</li>
	</ul>
</li>
<li>Version 0.6 vom 18.03.2007
	<ul>
		<li>Neu: Zahlungsweg beim Mitglied</li>
		<li>Neu: Tabelle zur �berwachung der manuellen Zahlungseing�nge</li>
		<li>Bugfix: Bei der Abbuchung von Kursteilnehmern wird das Abbuchungsdatum jetzt korrekt gesetzt</li>
		<li>Anpassung an Jameica/Hibiscus: Kennzeichnung der Pflichtfelder</li>
	</ul>
</li>
<li>Version 0.5 vom 10.03.2007
	<ul>
		<li>Neu: Kursteilnehmer</li>
		<li>Bugfix Mitgliederstatistik. Nur aktive Mitglieder werden ber�cksichtigt.</li>
		<li>Randeinstellungen der Mitgliederliste ge�ndert.</li>
		<li>Konfiguration aus dem Extras-Men� zur Jameica-Konformit�t in das Plugins-Men� am oberen 
			Rand verschoben</li>
		<li>Buchf�hrung entfernt. Mit dem aktuellen Nightly-Build von Jameica und Hibiscus kann diese 
			Aufgabe viel besser erledigt werden</li>
		<li>Zus�tzliche Vermerkfelder bei den Mitgliedern</li>
	</ul>
</li>
<li>Version 0.4a vom 27.01.2007
	<ul>
		<li>Installationsproblem beseitigt.</li>
	</ul>
</li>
</ul>
<? include ("footer.inc"); ?>

