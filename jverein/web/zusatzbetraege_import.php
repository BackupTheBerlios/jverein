<? include ("frame.inc"); ?>
    <h1>Zusatzbetr�ge Import</h1>
    <h2>Default-Format</h2>
	<p>Zusatzbetr�ge k�nnen auch importiert werden. Der Import kann �ber eine CSV-Datei (Default-Format), 
	die nachfolgend beschrieben wird, erfolgen.
	Der Dateiname muss eine Endung haben. Z. B. .csv oder .txt. Es kann jede beliebige Endung verwendet werden. 
	Die Datenfelder werden durch Semikolon getrennt. Das Encoding muss ISO-8859-1 sein.</p>
	<p>Zur Zuordnung des Zusazbetrages zum Mitglied muss entweder die Mitglieds_Nr oder Nachname und Vorname angegeben
	werden. Die Angabe von Nachname und Vorname setzt voraus, dass es keine Doubletten bei den Namen gibt.</p>
	<table border="1">
	<tr>
		<td>Mitglieds_Nr</td>
		<td>Mitgliedsnummer. Muss angegeben sein, wenn Nachname und Vorname nicht angegeben wurden.</td>
	</tr>
	<tr>
		<td>Nachname</td>
		<td>Nachname. Muss in Kombination mit dem Vornamen abgegeben werden, wenn die Mitglieds_Nr nicht angegeben wurde.</td>
	</tr>
	<tr>
		<td>Vorname</td>
		<td>Vorname. Muss in Kombination mit dem Nachnamen abgegeben werden, wenn die Mitglieds_Nr nicht angegeben wurde.</td>
	</tr>
	<tr>
		<td>Betrag</td>
		<td>Betrag. Anstatt eines Komma ist ein Punkt anzugeben.</td>
	</tr>
  <tr>
    <td>Buchungstext</td>
    <td>Buchungstext. Max 27 Stellen. Darf kein Semikolon enthalten.</td>
  </tr>
  <tr>
    <td>Buchungstext2</td>
    <td>2. Buchungstext. Max 27 Stellen. Darf kein Semikolon enthalten. Optional</td>
  </tr>
	<tr>
		<td>F�lligkeit</td>
		<td>Datum der F�lligkeit des Betrages. Format TT.MM.JJJJ</td>
	</tr>
	<tr>
		<td>Intervall</td>
		<td>Intervall der Zahlung. 0=keine Wiederholung, 1 = monatlich, 2 = zweimonatlich, 3 = viertelj�hrlich, 6 = halbj�hrlich, 12 = j�hrlich</td>
	</tr>
	</table>
	<p>Die Mitgliedsnummer kann in der Treffermenge der Mitgliedersuche angezeigt werden. Daf�r muss unter 
	Administration>Einstellungen>Tabellen ein H�kchen vor ID gesetzt werden.</p>
	<p>Jede Datei enth�lt eine Kopfzeile und pro Zusatzbuchung eine Zeile.</p>
	
	<h2>Spezialformate</h2>
	<p>In JVerein wird momentan ein Plugin-Mechanismus f�r Im- und Exportformate entwickelt. Damit wird es m�glich sein, 
	spezielle Formate eingelesen werden. M�glich sind spezielle CSV-Formate, Datenbanken, XML ..... Ich Bedarfsfalle bitte
	ich um eine Mail.</p> 
	<!-- 
    $Log: zusatzbetraege_import.php,v $
    Revision 1.3  2011/07/17 17:06:47  jost
    *** empty log message ***

    Revision 1.2  2009-11-05 20:08:47  jost
    *** empty log message ***

    Revision 1.1  2009/10/17 19:57:40  jost
    *** empty log message ***

    -->
	
<? include ("footer.inc"); ?>

