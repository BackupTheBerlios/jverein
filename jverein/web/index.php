<? include ("frame.inc"); ?>
	   
	<div style="float:left; width: 600px;border:0px">
	<p><a href="forum">Verbesserungsvorschl�ge und Fehlermeldungen bitte ins Forum posten</a>
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
	<p>Die Vereinsbuchf�hrung (Einnamen/Ausgaben) kann �ber eine integrierte einfache 
	   Buchf�hrung erledigt werden.</p>
	<p>Die Mitgliederdaten k�nnen nach vielen Kriterien <a href="dokumentationauswertungmitglieder.php">
	   ausgewertet</a> werden. Die Ausgabe erfolgt im PDF-Format oder im CSV-Format. Die Erstellung 
	   einer Liste mit <a href="dokumentationauswertungjubilaeen.php">Mitgliedschafts- oder Altersjubli�en</a> ist m�glich.</p>
	<p>In Version 1.3 ist der Versand von Mails an Mitglieder realisiert worden. Der Versand ist an einzelne Mitglieder, 
	   Gruppen von Mitgliedern oder alle Mitglieder m�glich. F�r regelm��ig zu versendende Mails k�nnen Vorlagen erstellt 
	   werden. Durch Variable ist die Personalisierung der Mails m�glich.</p>
	<p>Die Erstellung von Rechnungen und Spendenbescheinigungen m�glich.</p>
	<p>JVerein ist mit vielen Screenshots ausgiebig <a href='dokumentation.php'>dokumentiert</a>.</p>
	<p>JVerein steht unter GPL. In diesem Rahmen kann JVerein genutzt werden.</p>
	<p>Zus�tzlich gibt es eine Bitte des Autors: Wenn JVerein "produktiv" genutzt wird, bitte ich um einen 
	   Eintrag im <a href="http://www.jverein.de/forum/viewforum.php?f=3">Forum unter Vorstellung der Verein, 
	   die JVerein nutzen</a>. Diese Daten dienen der "Erfolgskontrolle".</p>
	</div>   
	<div style="float:left; width:200px;  left: 850px; ">    
	   	<h1>News</h1>
    	<ul>
    		<li>09.04.2010: <a href='version1.3.0.php'>Version 1.3.0 freigegeben</a>.</li> 
    		<li>28.03.2010: Neuer  <a href='download.php'>Downloadbereich</a></li> 
    	</ul>
	</div>
	   
	<!-- 
    $Log: index.php,v $
    Revision 1.30  2010/04/09 13:09:58  jost
    *** empty log message ***

    Revision 1.29  2010/04/09 08:56:56  jost
    Version 1.3.0

    Revision 1.28  2010/04/08 17:57:56  jost
    *** empty log message ***

    Revision 1.27  2010/03/28 18:37:06  jost
    *** empty log message ***

    Revision 1.26  2010/03/27 20:18:47  jost
    *** empty log message ***

    Revision 1.25  2010/03/16 19:22:14  jost
    Hinweis auf das Forum

    Revision 1.24  2010/03/05 21:56:02  jost
    Hinweis auf Mails

    Revision 1.23  2010/03/01 17:14:02  jost
    *** empty log message ***

    Revision 1.22  2010/02/15 20:07:25  jost
    *** empty log message ***

    Revision 1.21  2010/02/01 21:05:50  jost
    *** empty log message ***

    Revision 1.20  2010/01/10 21:00:12  jost
    *** empty log message ***

    Revision 1.19  2010/01/01 22:40:33  jost
    *** empty log message ***

    Revision 1.18  2010/01/01 21:40:37  jost
    *** empty log message ***

    Revision 1.17  2010/01/01 20:13:21  jost
    *** empty log message ***

    Revision 1.16  2009/12/22 18:51:56  jost
    *** empty log message ***

    Revision 1.15  2009/12/02 22:32:06  jost
    Neues Dokument: Zugriff mit OpenOffice-Base auf die H2-Datenbank

    Revision 1.14  2009/12/01 20:20:45  jost
    *** empty log message ***

    Revision 1.13  2009/10/31 14:23:14  jost
    *** empty log message ***

    Revision 1.12  2009/10/17 19:57:40  jost
    *** empty log message ***

    Revision 1.11  2009/09/27 21:31:48  jost
    *** empty log message ***

    Revision 1.10  2009/08/30 06:30:45  jost
    *** empty log message ***

    Revision 1.9  2009/08/24 20:08:17  jost
    *** empty log message ***

    Revision 1.8  2009/08/24 17:19:33  jost
    *** empty log message ***

    Revision 1.7  2009/08/23 18:59:21  jost
    Version 1.2

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
  

