<? include ("frame.inc"); ?>
    <h1>Mitgliedskonto</h1>
	<p>ab Version 1.4 verf�gbar</p>
	
	<h2>Aktivierung</h2>
	<p>Zun�chst ist das Mitgliedskonto zu aktivieren. Das geschieht unter Administration&gt;Einstellungen&gt;Anzeige
    <img src='images/MitgliedskontoEinstellungen.png' class='screenshot'>
    <p>Anschlie�end ist JVerein neu zu starten.</p> 
    
    <h2>Abrechnung</h2>
    <p>Die Abrechnung schreibt Informationen zu Mitgliedsbeitr�gen und Zusatzabrechnungen in die Tabelle
    Mitgliedskonto. Zus�tzlich werden f�r den Zahlungsweg Abbuchung Informationen in die Buchungstabelle
    der Buchf�hrung geschrieben.</p>
    <p>Fehlerhafte oder Test-Abrechnungen k�nnen r�ckg�ngig gemacht werden. In dem Abrechnungsformular 
    kann durch klick auf R�ckg�ngig</p>
    <img src='images/MitgliedskontoRueckgaengig.png' class='screenshot'>
    <p>eine �bersicht der Abrechnungsl�ufe ge�ffnet werden:</p>
    <img src='images/MitgliedskontoAbrechnungslaeufe.png' class='screenshot'>
    <p>Durch einen Rechtsklick auf einen Abrechnungslauf �ffnet sich ein 
    Kontextmen� mit der Option l�schen:</p>
    <img src='images/MitgliedskontoAbrechnungslaeufeLoeschen.png' class='screenshot'>
    <p>Nach einer Best�tigung</p>
    <img src='images/MitgliedskontoAbrechnungslaufLoeschenBestaetigen.png' class='screenshot'>
  	<p>werden die verkn�pften Datens�tze aus der Mitgliedskontotabelle und der Buchungstabelle gel�scht.</p>  
    
    <h2><a name="uebersicht"></a>Mitgliedskonten�bersicht</h2>
    <p>Es gibt eine zentrale �bersicht �ber alle Buchungen der Mitgliedskonten-Tabelle. Die Buchungen
    k�nnen �ber einen Zeitraum oder �ber einen Namen, bzw. Namensfragment gefiltert werden. Zus�tzlich
    kann angegeben werden, ob nur Mitgliedskonten mit Differenzen zwischen Soll und Ist (Offene Posten
    oder �berzahlungen) angezeigt werden.</p>
    <img src='images/MitgliedskontenUebersicht.png' class='screenshot'>
    <p>Durch einen Doppelklick auf die Buchung erscheint das Mitglied.</p>
    <img src='images/MitgliedskontoMitglied.png' class='screenshot'>
    
    <h2>Mitgliedskonto beim Mitglied</h2>
    <img src='images/MitgliedskontoMitglied.png' class='screenshot'>
    <p>In der Baumansicht werden die Summen pro Mitglied, die einzelnen Mitgliedskonten-Sollbuchungen (Soll und
    zugeordnetes Ist, Rechnersymbol), sowie die einzelnen zugeordneten Istbuchungen (Geldscheine-Symbol) angezeigt.</p>
    <p>Mit einem rechten Mausklick auf das Mitglied �ffnet sich ein Kontextmen�. Damit k�nnen neue
    Sollbuchungen aufgenommen werden.</p>
    <img src='images/MitgliedskontoNeu.png' class='screenshot'>
    <p>Mit einem rechten Mausklick auf eine Mitgliedskonto-Soll-Buchung �ffnet sich ein Kontextmen�. Damit
    kann die Sollbuchung bearbeitet, oder, sofern keine Istbuchung zugeordnet ist, auch gel�scht werden.</p>
    
    <h2><a name="auswahl"></a>Buchungen dem Mitgliedskonto zuordnen</h2>
    <p>Unter Buchf�hrung&gt;Buchungen ist eine Buchung auszuw�hlen und doppelt anzuklicken:</p>
    <img src='images/MitgliedskontoBuchungen.png' class='screenshot'>
    <p>Durch einen Klick auf ... neben Mitgliedskonto erscheint folgender Dialog:</p>
    <img src='images/MitgliedskontoAuswahl.png' class='screenshot'>
    <p>Der Name aus der Buchung wird in das Namensfeld �bernommen. Der Inhalt wird in W�rter zerlegt und
    in den Spalten Name, Vorname und Verwendungszweck 1 gesucht. Standardm��ig ist offene Posten angehakt.
    Dadurch werden nur Mitgliedskonto-Soll-Buchungen angezeigt, die noch nicht ausgeglichen sind.</p>
    
    
    
    
    
    <h2>TODO</h2>
    <ul>
    	<li>Informationen �ber Zusatzabrechnungen schreiben</li>
    	<li>Gegenbuchung in der Buchf�hrung erzeugen</li>
    	<li>Rechnungen auf neue Mimik umstellen</li>
    	<li>Manuellen Zahlungseingang ausmustern</li>
    </ul>   
    <!-- 
    $Log: mitgliedskonto.php,v $
    Revision 1.1  2010/07/25 18:49:30  jost
    Neu: Mitgliedskonto

    -->
    
<? include ("footer.inc"); ?>

