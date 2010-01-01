<? include ("frame.inc"); ?>
    <h1>Administration: Felddefinitionen</h1>
    <p>
    <img src='images/Felddefinitionen.jpg' class='screenshot'>
	<p>Der Benutzer kann zus�tzliche Datenfelder definieren. Durch einen Klick auf neu �ffnet sich folgendes 
	Fenster:
    <img src='images/Felddefinition.jpg' class='screenshot'>
    <p>Der Names des Feldes kann auch den Zeichen a-z und 0-9 und _ (Unterstrich) bestehen. Er darf keine 
    Leerzeichen enthalten und sich nicht mit existierenden Feldnamen �berschneiden. Als Label kann ein 
    beliebiger Begriff verwendet werden, der bei der Eingabe der Daten den Feld vorangestellt wird. 
    </p>
    <p>Ab Version 1.3 kann aus den Datentypen
    <ul>
    	<li>Zeichenkette (bis zu 1.000 Zeichen lang)</li>
    	<li>Datum (Format TT.MM.JJJJ)</li>
    	<li>Ganzzahl</li>
    	<li>Ja/Nein-Wert</li>
    	<li>W�hrung</li>
    </ul>
    ausgew�hlt werden.</p>
    <p>Feldnamen und Label k�nnen jederzeit ge�ndert werden. Daten gehen hierdurch nicht verloren. Bis zur 
    Version 1.2 (einschl.) kann ein Feld kann nur gel�scht werden, wenn bei keinem Mitglied Daten in diesem 
    Feld gespeichert sind. Ab Version 1.3 werden die Daten nach R�ckfrage gel�scht.</li>
	<p>Bei der �nderung des Datentypen ist zu beachten, dass eine Konvertierung m�glich sein muss. Beispielsweise
	kann ein Zusatzfeld vom Typ Zeichenfolge nur dann in den Typ Datum umgewandelt werden, wenn ausschlie�lich Daten
	in der Form TT.MM.JJJJ gespeichert sind. Alle Datentypen k�nnen in Zeichenfolge umgewandelt werden.</li>     

    <!-- 
    $Log: administration_felddefinitionen.php,v $
    Revision 1.2  2010/01/01 20:13:21  jost
    *** empty log message ***

    Revision 1.1  2009/05/08 14:46:22  jost
    shtml - php

    Revision 1.2  2009/05/03 15:33:30  jost
    Neue Homepage

    -->


<? include ("footer.inc"); ?>

