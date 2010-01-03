<? include ("frame.inc"); ?>
    <h1>Administration: Formulare</h1>
    <p>
    <p>In JVerein werden u. a. f�r Spendenbescheinigungen Formulare hinterlegt. Bei den Formularen 
    handelt es sich um PDF-Dokumente. Diese PDF-Dokumente k�nnen mit einem beliebigen Programm 
    (z. B. OOWriter oder in Verbindung mit einem PDF-Drucker wie FreePDF) erstellt werden. In 
    JVerein k�nnen zu jedem Formular Formularfelder definiert werden. Diese Formularfelder werden 
    auf dem PDF-Dokument an feste Stellen positioniert. JVerein schreibt dann an dieser Position 
    mit dem f�r das jeweilige Formularfeld definierten Zeichensatz und in der gew�nschten 
    Zeichengr��e Inhalte aus der Datenbank oder die auf der jeweiligen Maske eingetragenen Werte.</p>
    
    <p>Liste der bereits definierten Formulare:</p>
    <img src='images/Formulare.jpg' class='screenshot'>
    <p>Durch einen Klick auf "neu" bzw. mit einem Doppelklick auf ein bestehendes Formular �ffnet sich
    das Formular-Bearbeitungsfenster - im zweiten Fall bereits gef�llt mit den vorhandenen Werten 
    (siehe unten). Mit einem Rechtsklick auf ein bestehendes Formular �ffnet sich ein Kontextmen�. 
    Dort kann man ausw�hlen: Der Dialog zur Definition von neuen bzw. zur Bearbeitung von bestehenden 
    Formularfeldern f�r das jeweilige Formular kann ge�ffnet werden, das Formular selbst kann mit den 
    Formularfeldern angezeigt werden, wobei Beispieldaten eingef�gt werden, oder das Formular kann 
    gel�scht werden.</p>
    <img src='images/Formular.jpg' class='screenshot'>

	<p>Jedem Formular wird ein eindeutiger Name gegeben. Ein Formulartyp ist auszuw�hlen. Bei der 
	Neuaufnahme oder beim Update eines Formulares ist eine PDF-Datei auszuw�hlen. Die PDF-Vorlage 
	wird in der Datenbank gespeichert, d. h. wenn die Vorlage auf der Festplatte ge�ndert wird, 
	hat das erst mal keine Auswirkungen auf JVerein bis es erneut importiert wird.</p>
	<p>Liste der Formularfelder:</p>
    <img src='images/Formularfelder.jpg' class='screenshot'>
    <p>Folgende Formularfelder stehen f�r Spendenbescheinigungen zur Verf�gung:
    <table>
    	<tr>
    		<td>Tagesdatum</td><td>Enth�lt das aktuelle Datum im Format TT.MM.JJJJ</td>
    	</tr>
    	<tr>
    		<td>Empf�nger</td><td>Empf�nger der Spendenbescheinigung. Der jeweilige Dialog zur 
    								Ausstellung einer Spendenbescheinigung bzw. einer Rechnung 
    								enth�lt mehrere Zeilen f�r die Adresse. In manchen Dialogen 
    								werden diese Zeilen bereits aus den Bestandsdaten automatisch 
    								gef�llt. Sie werden an der definierten Position untereinander 
    								ausgegeben.</td>
    	</tr>
    	<tr>
    		<td>Bescheinigungsdatum<td>Datum der Bescheinigung aus der Datenbank. Formatiert TT.MM.JJJJ</td>
    	</tr>
    	<tr>
    		<td>Betrag</td><td>H�he der Spende</td>
    	</tr>
    	<tr>
    		<td>Betrag in Worten</td><td>Der Betrag ausgeschrieben in Worten.</td>
    	</tr>
    	<tr>
    		<td>Spendedatum</td><td>Datum der Spende aus der Datenbank. Formatiert TT.MM.JJJJ</td>
    	</tr>
    	<tr>
    		<td>ErsatzAufwendungen</td><td>Aus der Datenbank</td>
    	</tr>
    </table>
    </p>
    <p>Folgende Formularfelder stehen f�r Rechnungen zur Verf�gung:
    <table>
    	<tr>
    		<td>Tagesdatum</td><td>Enth�lt das aktuelle Datum im Format TT.MM.JJJJ</td>
    	</tr>
    	<tr>
    		<td>Empf�nger</td><td>Empf�nger der Rechnung. Formatiert f�r ein Adressfeld.</td>
    	</tr>
    	<tr>
    		<td>Zahlungsgrund1</td><td>&nbsp;</td>
    	</tr>
    	<tr>
    		<td>Zahlungsgrund2</td><td>&nbsp;</td>
    	</tr>
    	<tr>
    		<td>Buchungsdatum</td><td>&nbsp;</td>
    	</tr>
    	<tr>
    		<td>Betrag</td><td>&nbsp;</td>
    	</tr>
    	<tr>
    		<td>sowie alle Felder des Mitgliedsdatensatzes</td><td>&nbsp;</td>
    	</tr>
    </table>
    </p>
    <p>Im Men� "Formularfelder" �ffnet sich durch einen Klick auf "neu" bzw. mit einem Doppelklick 
    auf ein bestehendes Formularfeld der Dialog zur Bearbeitung des Formularfelds - im zweiten Fall 
    bereits gef�llt mit den vorhandenen Werten (siehe unten). Mit einem Rechtsklick auf ein 
    bestehendes Formularfeld �ffnet sich ein Kontextmen�, �ber das das Formularfeld gel�scht werden 
    kann.</p>
    
    <p>Die Definition eines Formularfeldes besteht aus dem Inhalt, der �ber ein vordefiniertes Feld 
    belegt wird (Empf�nger, Betrag etc.), der Startposition auf der Seite (gemessen von links und 
    unten in Millimetern, eine Positionierung auf hundertstel Millimeter ist m�glich), der 
    Schriftart/Font (ausw�hlbar aus einer Liste) und der Schriftgr��e (Font-H�he).</p>

	<p>Wenn die Formularfelder definiert wurden, kann die Position in Verbindung mit dem aktuellen 
	Formular �berpr�ft und angepasst werden. Gehe hierzu in das Men� "Formulare", markiere das 
	aktuell bearbeitete Formular und gehe �ber das Kontext-Men� (rechter Mausklick) auf "anzeigen". 
	Pr�fe das Aussehen des generierten Formulars anhand der Testdaten und korrigiere gegebenenfalls 
	noch einmal die Positionen der Formularfelder und die Schriftgr��en bis das Gesamtbild passt.</p>
	<img src='images/Formularfeld.jpg' class='screenshot'>
	
    <p>Beispiel f�r ein Formular (ohne Daten)</p>
    <img src='images/Formularroh.jpg' class='screenshot'>
    <p>Beispiel f�r ein ausgef�lltes Formular</p>
    <img src='images/Formularausgefuellt.jpg' class='screenshot'>
    
    <!-- 
    $Log: administration_formulare.php,v $
    Revision 1.2  2010/01/03 08:59:09  jost
    *** empty log message ***

    Revision 1.1  2009/05/08 14:46:22  jost
    shtml - php

    Revision 1.2  2009/05/03 15:33:30  jost
    Neue Homepage

    -->
    
<? include ("footer.inc"); ?>

