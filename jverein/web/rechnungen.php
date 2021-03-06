<? include ("frame.inc"); ?>
    <h1>Rechnung</h1>
    <p>
    <p>Diese Funktion wird ab Version 1.4 durch die Rechnungen des Mitgliedskontos ersetzt. Ab Version 1.5 steht sie nicht
       mehr zur Verf�gung.</p>
   
   	<p>Vorbereitend ist sind ein oder mehrere <a href="administration_formulare.php">Formulare</a> einzurichten.</p>
   	<p>Als Formularfelder stehen zur Verf�gung:</p>
   	<ul>
   		<li> Empf�nger</li>
   		<li> Zahlungsgrund 1</li>
   		<li> Zahlungsgrund 2</li>
   		<li> Betrag</li>
   		<li> Zahlungsweg</li>
   		<li> Tagesdatum</li>
   	</ul>
   	<p>Sofern die Abrechnungsdaten von einem Mitglied stammen, stehen auch die Daten der Mitgliedertabelle 
   		zur Verf�gung</p>
   	<p>Durch die <a href="abrechnung.php">Abrechnung</a> werden die Informationen in einer separaten Tabelle 
   		festgehalten. Daraus k�nnen die Rechnungen erstellt werden.</p>
   	<p>�bersicht �ber die Rechnungen:</p>
    <img src='images/Rechnungen.jpg' class='screenshot'>
   	<p>Mit einem Doppelklick auf eine Rechnungszeile �ffnet sich das folgende Fenster mit den 
   		Rechnungsinformationen. Diese k�nnen hier auch bearbeitet werden.</p>
    <img src='images/Rechnung.jpg' class='screenshot'>
	<p>In der �bersicht der Rechnungen k�nnen eine oder mehrere Rechnungen markiert werden. Durch einen 
		Rechtsklick �ffnet sich ein Kontextmen�. Damit k�nnen entweder die Rechnungen ausgestellt werden 
		oder gel�scht werden.</p>
	<p>Beispiel f�r eine Rechnung</p>
	<img src='images/Rechnungausgefuellt.jpg' class='screenshot'>
    
    <!-- 
    $Log: rechnungen.php,v $
    Revision 1.2  2010/11/07 07:16:05  jost
    Hinweis auf Wegfall ab Version 1.5

    Revision 1.1  2009/05/08 14:46:22  jost
    shtml - php

    Revision 1.2  2009/05/03 15:33:30  jost
    Neue Homepage

    -->

 <? include ("footer.inc"); ?>

