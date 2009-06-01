<? include ("frame.inc"); ?>
    <h1>Dokumentation: Auswertung: Mitglieder</h1>
    <p>Der Mitgliederbestand kann nach den unten aufgef�hrten Merkmalen selektiert werden.</p>
    <p>Die Ausgabe erfolgt entweder im PDF oder im CSV-Format. Nach der Erzeugung der Datei wird ein 
    	entsprechendes Anzeigeprogramm aufgerufen.</p>
    <p>Die Sortierung erfolgt nach</p>
	<ul>
		<li> Name, Vorname</li>
		<li> Eintrittsdatum</li>
		<li> Geburtsdatum</li>
		<li> Geburtsmonat und -jahr (Geburtstagliste). Hinweis: Runde Geburtstage k�nnen in der 
			<a href="dokumentationauswertungjubilaeen.php">Jubil�umsliste</a> ausgegeben werden.</li>
	</ul>
	<p>Ab Version 1.1: Sofern <a href="administration_felddefinitionen.php">Zusatzfelder definiert</a> 
	    wurden, werden die in die CSV-Datei exportiert.</p>
    <img src='images/Auswertung.jpg' class='screenshot'>
   
    <!-- 
    $Log: dokumentationauswertungmitglieder.php,v $
    Revision 1.1  2009/05/08 14:46:22  jost
    shtml - php

    Revision 1.1  2009/05/03 15:33:30  jost
    Neue Homepage

    -->
        
<? include ("footer.inc"); ?>
