<? include ("frame.inc"); ?>
	   
	<div style="float:left; width: 600px;border:0px">
    <h1>Home</h1>
    <p>
 <!--      <img src="images/JVerein_Box.jpg" alt="JVerein-Software-Box" >  -->
       <i>JVerein</i> ist eine Open-Source-Vereinsverwaltung mit einer Anbindung an die ebenfalls unter 
       Open-Source-Lizenz stehende Homebankingsoftware 
       <a href="http://www.willuhn.de/products/hibiscus/">Hibiscus</a>.
     </p>
     <p>Die Implementierung erfolgt mit Java. Der Ablauf auf vielen Plattformen ist damit gew�hrleistet. 
        Als GUI-Framework kommt <a href="http://www.willuhn.de/products/jameica/">Jameica</a>  zum Einsatz.
     </p>
     <p>F�r jedes Mitglied k�nnen folgende Daten gespeichert werden:</p>
     <ul>
        <li>Namen</li>
        <li>Adresse</li>
        <li>Zahlungsweg</li>
        <li>Bankverbindung</li>
        <li>Zahlungrhytmus</li>
        <li>Kommunikationsdaten (Tel./eMail)</li>
        <li>Daten zur Mitgliedschaft (Eintritt, Beitragsgruppe, Austritt)</li>
        <li>Eigenschaften des Mitglieds</li>
        <li>Wiedervorlagetermine</li>
        <li>Vermerke</li>
        <li>Zusatzzahlungen (einmalig und wiederkehrend)</li>
        <li>Individuelle Zusatzfelder (ab Version 1.1)</li>
        <li>Lehrg�nge (ab Version 1.2)</li>
     </ul>
     <p>Ab Version 1.2 k�nnen auch juristische Personen (Firmen, Organisationen, Beh�rden) als Mitglieder
     	gespeichert werden.</p>
	<p>F�r den Beitragseinzug k�nnen eine oder mehrere Beitragsgruppen gebildet werden. Die Abbildung eines 
	   Familientarifes mit der Bildung von Familienverb�nden ist m�glich. Es wird entweder ein fester 
	   Beitrag (pro Beitragsgruppe) in einem Intervall (z. B. Jahresbeitrag oder Monatsbeitrag) erhoben oder 
	   ein Monatsbeitrag der monatlich, viertelj�hrlich, halbj�hrlich oder j�hrlich eingezogen wird. 
	</p>
	<p>Der Beitragseinzug erfolgt in der Regel �ber das Abbuchungsverfahren. Die Abbuchungen werden im 
	   DTAUS-Format mit Hilfe meiner Bibliothek <a href="http://obantoo.berlios.de">OBanToo</a> ausgegeben 
	   und k�nnen in <a href="http://www.willuhn.de/products/hibiscus/">Hibiscus</a> importiert werden 
	   oder direkt an <a href="http://www.willuhn.de/products/hibiscus/">Hibiscus</a> als Einzel- oder 
	   Sammellastschrift �bergeben werden. Alle Abrechnungsdaten werden festgehalten. Daraus k�nnen 
	   anschlie�end Rechnungen erstellt werden.</p>
	<p>F�r Mitglieder, die nicht am Abbuchungsverfahren teilnehmen werden die Zahlungsdaten zur manuellen 
	   �berwachung des Zahlungseinganges festgehalten.</p>
	<p>Zus�tzlich k�nnen f�r jedes Mitglied zu beliebigen Zeitpunkten Zusatzabbuchungen eingegeben werden 
	   (Strafgelder, Eigenanteile ...), die auch wiederkehrend sein k�nnen.</p>
	<p>Die Vereinsbuchf�hrung (Einnamen/Ausgaben) kann ab Version 1.1 �ber eine integrierte einfache 
	   Buchf�hrung erledigt werden.</p>
	<p>Die Mitgliederdaten k�nnen nach vielen Kriterien <a href="dokumentationauswertungmitglieder.php">
	   ausgewertet</a> werden. Die Ausgabe erfolgt im PDF-Format oder im CSV-Format. Die Erstellung 
	   einer Liste mit <a href="dokumentationauswertungjubilaeen.php">Mitgliedschafts- oder Altersjubli�en</a> ist m�glich.</p>
	<p>Ab Version 1.1 ist die Erstellung von Rechnungen und Spendenbescheinigungen m�glich.</p>
	<p>JVerein ist mit vielen Screenshots ausgiebig <a href='dokumentation.php'>dokumentiert</a>.</p>
	<p>JVerein steht unter GPL. In diesem Rahmen kann JVerein genutzt werden.</p>
	<p>Zus�tzlich gibt es eine Bitte des Autors: Wenn JVerein "produktiv" genutzt wird, bitte ich um eine 
	   kurze Nachricht an <a href="mailto:heiner@jverein.de">heiner@jverein.de</a> mit dem Namen des Vereins 
	   und der Anzahl der Mitglieder. Diese Daten dienen der "Erfolgskontrolle".</p>
	   
	</div>   
	<div style="float:left; width:200px;  left: 850px; ">    
	   	<h1>News</h1>
    	<ul>
        	<li>09.08.2009: Ver�ffentlichung des <a href="http://prdownload.berlios.de/jverein/jverein.1.2.0-devel-190.zip">Release-Kandidaten 1 der Version 1.2.0</a>. Siehe <a href="changelog.php">Changelog</a></li>
       	 	<li>16.07.2009: Ver�ffentlichung der <a href="http://prdownload.berlios.de/jverein/jverein.1.2.0-devel-183.zip">Entwickler-Version 1.2.0 Rev. 183</a>. Siehe <a href="changelog.php">Changelog</a></li>
    		<li>14.03.2009: Ver�ffentlichung der <a href="http://prdownload.berlios.de/jverein/jverein.1.2.0-devel-173.zip">Entwickler-Version 1.2.0 Rev. 173</a>. Siehe <a href="changelog.php">Changelog</a></li> 
    	</ul>
	</div>
	   
	<!-- 
    $Log: index.php,v $
    Revision 1.6  2009/08/20 20:15:32  jost
    *** empty log message ***

    Revision 1.5  2009/08/17 18:52:58  jost
    neues Layout

    Revision 1.4  2009/08/09 15:36:17  jost
    *** empty log message ***

    Revision 1.3  2009/08/09 15:34:41  jost
    *** empty log message ***

    Revision 1.2  2009/07/17 11:42:16  jost
    *** empty log message ***

    Revision 1.1  2009/05/08 14:46:22  jost
    shtml - php

    Revision 1.22  2009/05/03 15:33:30  jost
    Neue Homepage

    -->
	   
 <? include ("footer.inc"); ?>
  

