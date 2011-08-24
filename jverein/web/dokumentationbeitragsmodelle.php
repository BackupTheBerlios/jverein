<? include ("frame.inc"); ?>
    <h1>Dokumentation: Beitragsmodelle</h1>
	<h2>Allgemein</h2>
	<p>Zur Beitragsberechnung sind eine oder mehrere <a href="administration_beitragsgruppen.php">
		Beitragsgruppen</a> einzurichten. Z. B. Jugendliche, Erwachsene, Beitragsfreie. Jeder dieser 
		Beitragsgruppen wird ein Text und eine Beitragsh�he zugeordnet. Dabei kann es sich um Monats-, 
		Vierteljahres-, Halbjahres- oder Jahresbetr�ge handeln. Siehe unten.</p>
	<h2>Familientarif</h2>
	<p>Alle Mitglieder, f�r die der Familientarif gilt sind einzeln einzugeben. Zur Abbildung eines 
		Familientarifes sind 2 <a href="administration_beitragsgruppen.php">Beitragsgruppen</a> 
		einzurichten. Einmal eine Gruppe f�r den Zahler und eine Gruppe f�r die beitragsfreien 
		Familienmitglieder. Als Zahler ist immer eines der famlienangeh�rigen Mitglieder auszuw�hlen. 
		Es wird nicht ein evtl. zahlendes Elternteil, dass nicht Mitglied ist eingetragen. Die 
		Famlienmitglieder sind miteinander verkn�pft. Tritt das zahlende Mitglied aus, erscheint ein 
		entsprechender Hinweis. Dann muss entweder ein bislang beitragsfreies Mitglied zum Zahler 
		erkl�rt werden oder der Familienverband wird aufgel�st und es werden Einzelbeitr�ge erhoben.</p>
	<h2>Zahlungsrhythmen</h2>
	<p>JVerein unterst�tzt verschiedene Betragsmodelle:</p>
	<ul>
		<li>Feste Zahlungstermine. Alle Mitglieder zahlen zu festen Terminen ihren Beitrag. Dieser 
			Termin kann j�hrlich, halbj�hrlich, viertelj�hrlich oder monatlich sein. Zu diesem Termin 
			wird f�r jedes Mitglied der Beitrag gem�� <a href="administration_beitragsgruppen.php">
			Beitragsgruppe</a> eingezogen.</li>
		<li>Unterschiedliche Zahlungstermine. Die Mitglieder zahlen einen Monatsbeitrag gem�� 
			<a href="administration_beitragsgruppen.php">Beitragsgruppe</a>. Dieser Beitrag wird 
			entweder j�hrlich (12 Monatsbeitr�ge), halbj�hrlich (6 Monatsbeitr�ge), viertelj�hrlich 
			(3 Monatsbeitr�ge) oder monatlich erhoben. Bei der Abrechnung muss dann ausgew�hlt werden,
			welche Beitr�ge abgerechnet werden sollen.</li>
	</ul>
	
    <!-- 
    $Log: dokumentationbeitragsmodelle.php,v $
    Revision 1.2  2011/08/24 16:49:31  jost
    *** empty log message ***

    Revision 1.1  2009-05-08 14:46:22  jost
    shtml - php

    Revision 1.1  2009/05/03 15:33:30  jost
    Neue Homepage

    -->

<? include ("footer.inc"); ?>

