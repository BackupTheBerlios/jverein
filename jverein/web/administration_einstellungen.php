<? include ("frame.inc"); ?>
    <h1>Administration: Einstellungen</h1>
    <p>Durch die Einstellungen kann das Verhalten von JVerein beeinflu�t werden.</p>
    <h3>Ansicht</h3>
    <img src='images/Einstellungenansicht.png' class='screenshot'>
    <p class='hervorgehoben'>Nach �nderungen der mit Stern gekennzeichneten Werte ist ein Neustart 
    von Jameica erforderlich.</p>
	<p>Folgende Einstellungen k�nnen vorgenommen werden:</p>
	<ul>
	<li>Geburtsdatum und Eintrittsdatum Pflichtfeld</li>
	<li>Tab Kommunikationsdaten beim Mitglied anzeigen</li>
	<li>Tab Zusatzabbuchungen beim Mitglied anzeigen. Impliziert, dass die �bersicht der 
	Zusatzabbuchungen (nicht) angezeigt wird und die Option bei der Abbuchung (in)aktiv ist.</li>
	<li>Tab Vermerke beim Mitglied anzeigen.</li>
	<li>Tab Wiedervorlage beim Mitglied anzeigen. Impliziert, dass die �bersicht der Wiedervorlagen 
	(nicht) angezeigt wird.</li>
	<li>Kursteilnehmer ein-/ausblenden. Auswirkung auf die Abbuchung.</li>
	<li>Juristische Personen erlaubt. Die Eingabe von Firmen, Organisationen und Beh�rden als Mitglieder
		wird erlaubt. Anstatt Name und Vorname werden Name-Zeile1 und Name-Zeile2 erfasst. Geburtsdatum
		und Geschlecht werden nicht erfasst.</li>
	<li>Externe Mitgliedsnummern verwenden. Vereine, die auf Bundes- oder Landesebene organisiert 
	sind und eine durchg�ngige Mitgliedsnummer verwalten m�chten, k�nnen in JVerein eine externe 
	Mitgliedsnummer speichern.</li>
	</ul>
	
	<h2>Beitragsmodell</h2>
    <img src='images/Einstellungenbeitraege.jpg' class='screenshot'>
	<p>Beitragsmodell, siehe auch Beitragsmodelle</</p>
	<h2>Dateinamenmuster</h2>
	<img src='images/Einstellungendateinamen.jpg' class='screenshot'>
	<p>Bei der Ausgabe von Dateien (Abbuchung, Auswertungen...) werden die Dateinamen nach dem 
	vorgegebenen Muster aufgebaut. Es k�nnen zus�tzliche, vom Betriebssystem unterst�tzte Zeichen, 
	in das Muster aufgenommen werden. Bleibt das Muster leer, wird kein Vorschlag f�r den Dateinnamen 
	angezeigt. </p>
	<p>Folgende Variable stehen zur Verf�gung:</p>
	<ul>
	<li>a$ : Aufgabe (z. B. auswertung, abbuchung)</li>
	<li>d$ : Aktuelles Datum</li>
	<li>s$ : Sortierung. Wird nur bei Auswertungen gef�llt. Ansonsten leer.</li>
	<li>z$ : Aktuelle Zeit</li>
	</ul>
	
	<h2>Buchf�hrung</h2>
	<img src='images/Einstellungenbuchfuehrung.jpg' class='screenshot'>
	<p>Beginn des Gesch�ftsjahres in der Form TT.MM. (Ab Version 1.1)</p> 

	<h2>Rechnungen</h2>
	<img src='images/Einstellungenrechnungen.jpg' class='screenshot'>
	<p>Festlegung, f�r welche Zahlungswege Rechnungsinformationen gespeichert werden sollen. (Ab Version 1.1)</p>
	
	
	<h2><a name='tabellen'>Tabellen</a></h2>
	<p>Festlegung der Spalten, die in Tabellen angezeigt werden sollen. 
	<img src='images/Einstellungentabellen.jpg' class='screenshot'>
	
	<h2>Updates</h2>
	<p>Festlegung zur Pr�fung auf Updates. Das Interval kann entweder manuell, t�glich oder monatlich sein.
	Sofern Diagnoseunterlagen mitgesendet werden d�rfen, wird der Name des Vereins �bermittelt. Dies dient
	der 'Erfolgskontrolle' f�r den Programmautor. Weitere Daten werden nicht �bermittelt.</p> 
	<img src='images/Einstellungenupdates.jpg' class='screenshot'>
	
	<!-- 
    $Log: administration_einstellungen.php,v $
    Revision 1.4  2009/11/17 21:05:16  jost
    *** empty log message ***

    Revision 1.3  2009/09/13 19:20:57  jost
    *** empty log message ***

    Revision 1.2  2009/09/01 19:53:56  jost
    Einstellungen Tabellen korrekt beschrieben

    Revision 1.1  2009/05/08 14:46:22  jost
    shtml - php

    Revision 1.3  2009/05/05 17:28:50  jost
    Neu: Juristische Personen als Mitglieder

    Revision 1.2  2009/05/03 15:33:30  jost
    Neue Homepage

    -->
	
	
 <? include ("footer.inc"); ?>

