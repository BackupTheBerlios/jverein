<? include ("frame.inc"); ?>
    <h1>Zusatzbeitr�ge</h1>
    <p>F�r jedes Mitglied k�nnen Zusatzbetr�ge hinterlegt werden. Diese Betr�ge werden zus�tzlich 
    	zum Mitgliedsbeitrag eingezogen. Die Abrechnungen k�nnen einmalig (z. B. Eigenanteil f�r die 
    	Teilnahme an einer Veranstaltung) oder wiederkehrend (z. B. Instrumentenversicherung)
    	angelegt werden. Als Wiederholungs-Intervall stehen</p>
	<ul>
		<li>monatlich</li>
		<li>zweimonatlich</li>
		<li>viertelj�hrlich</li>
		<li>halbj�hrlich</li>
		<li>j�hrlich</li>
	</ul>
	<p>zur Auswahl.</p>
	<p>Die Zusatzbetr�ge k�nnen sowohl bei jedem Mitglied auf dem entsprechenden Reiter angesehen 
	werden, als auch in einer �bersicht f�r alle Mitglieder. Dabei kann die Anzeige eingeschr�nkt 
	werden auf</p>
	<ul>
		<li>alle Mitglieder</li>
		<li>aktive</li>
		<li>noch nicht ausgef�hrte Betr�ge</li>
		<li>nach den verschieden Ausf�hrungsterminen.</li>
	</ul>
	<p>Durch einen Doppelklick auf einen Zusatzbetrag wird der Bildschirm f�r die Bearbeitung ge�ffnet. 
		Mit einem Rechtsklick �ffnet sich ein Men�. Damit kann</p>
	<ul>
		<li>der n�chste F�lligkeitstermin gesetzt werden</li>
		<li>der vorherige F�lligkeitstermin gesetzt werden</li>
		<li>das Ausf�hrungsdatum gel�scht werden</li>
		<li>der Zusatzbetrag werden</li>
	</ul>
    <img src='images/Zusatzabbuchungenuebersicht.jpg' class='screenshot'>
  	<img src='images/Mitgliedzusatzabbuchungneu.jpg' class='screenshot'>
	
	<p>Bei der Abrechnung finden folgende Pr�fungen statt:</p>
	<ul>
		<li> liegt das F�lligkeitsdatum auf dem Endedatum oder danach. Keine Berechnung.</li>
		<li> liegt das F�lligkeitsdatum auf dem Tagesdatum oder davor. Berechnung. Auf das 
			F�lligkeitsdatum wird das Intervall addiert.</li>
	</ul>
	<p>Beipiel 1:</p>
	<p>F�lligkeitsdatum: 1.11.2008 Endedatum: 31.10.2008  - keine Berechnung</p>
	<p>Beispiel 2:</p>
	<p>F�lligkeitsdatum: 1.11.2008 Endedatum: 31.12.2100 Intervall: Monatlich  Tagesdatum: 
		1.11.2008 - Berechnung. Anschlie�end wird das F�lligkeitsdatum auf den 1.12.2008 gesetzt. 
		Am oder nach dem 1.12.2008 findet die n�chste Berechnung statt. Danach wird das 
		F�lligkeitsdatum auf den 1.1.2009 gesetzt.....</p>

    <!-- 
    $Log: zusatzbetraege.php,v $
    Revision 1.1  2009/05/08 14:46:22  jost
    shtml - php

    Revision 1.2  2009/05/03 15:33:30  jost
    Neue Homepage

    -->
        
<? include ("footer.inc"); ?>

