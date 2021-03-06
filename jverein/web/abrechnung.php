<? include ("frame.inc"); ?>
    <h1>Abrechung</h1>
	<p>Die Abrechnung wird mit dem untenstehenden Bildschirm initiiert. Es k�nnen</p>
	<ul>
		<li>Mitgliedsbeitr�ge je nach <a href="dokumentationbeitragsmodelle.php">Beitragsmodell</a></li>
		<li>Beitr�ge f�r im laufenden Jahr eingetretene Mitglieder</li>
		<li>Zusatzbetr�ge</li>
		<li>Kursgeb�hren</li>
	</ul>
	<p>verarbeitet werden.</p>
	<p>Sofern als Modus <b>nicht</b> 'keine Beitragsabbuchung' ausgew�hlt wurde, werden f�r alle 
		Mitglieder, die nicht ausgetreten sind oder deren Austrittsdatum nach dem Stichtag liegt, 
		die Beitr�ge gem�� eingetragener Beitragsgruppe und Zahlungsrhytmus eingezogen.</p>
	<p>F�r Mitglieder, die im Laufe des Jahres eingetreten sind, k�nnen ebenfalls die Beitr�ge 
		eingezogen werden. Dazu wird das Eingabedatum eingetragen, ab dem die Beitr�ge f�r 
		nachtr�glich eingetretene Mitglieder abgebucht werden sollen.</p>
	<p>Mit der Option Zusatzbetr�ge werden auch die zus�tzlichen Betr�ge abgebucht.</p>
	<p>F�r die Abbuchung werden die Daten werden in eine DTAUS-Datei geschrieben. Diese Datei kann entweder direkt auf 
		Diskette ausgegeben und bei der Bank eingereicht oder zum Beispiel in Hibiscus importiert 
		werden. Buchungen k�nnen als Einzel- oder Sammellastschriften direkt in Hibiscus verbucht 
		werden. Die Kontonummer in den <a href="administration_stammdaten.php">Stammdaten</a> 
		wird mit den Kontonummern in Hibiscus abgeglichen. Gibt es eine �bereinstimmende Bankverbindung, 
		wird diese verwendet. Ansonsten erscheint der Hibiscus-Konto-Auswahldialog. </p>
	<p>F�r Mitglieder ohne den Zahlungsweg 'Abbuchung' werden die Daten f�r die �berwachung des Zahlungseinganges
		in die Tabelle 'manuelle Zahlungseing�nge' geschrieben.</p>
	<p>Optional kann die ausgegebene DTAUS-Datei in ein PDF-Dokument zum Ausdruck ausgegeben werden.</p>
    <p>Teilnehmer von Kursen k�nnen abgerechnet werden. Kursteilnehmer sind Personen, die
		nicht Mitglied des Vereins sind. Sofern Mitglieder an Kursen teilnehmen, die zus�tzlich
		abgerechnet werden, bieten sich die Zusatzabbuchungen an.</p>
    <img src='images/Abbuchung095.jpg' class='screenshot'>
        
    <!-- 
    $Log: abrechnung.php,v $
    Revision 1.3  2010/11/07 07:12:15  jost
    Versionshinweise entfernt.

    Revision 1.2  2009/11/05 20:07:02  jost
    Hinweis auf 'manuellen Zahlungseingang' aufgenommen.

    Revision 1.1  2009/05/08 14:46:22  jost
    shtml - php

    Revision 1.3  2009/05/03 15:31:53  jost
    Neue Homepage

    Revision 1.2  2009/05/03 09:29:14  jost
    Neue Homepage

    -->
<? include ("footer.inc"); ?>

