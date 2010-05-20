<? include ("frame.inc"); ?>
    <h1>Jameica + Plugins "portable"</h1>
	<p>Autor: Marco H�gel, Links aktualisiert von Guido (mfc)</p>
	<p></p>
	<p>Wie allgemein bekannt ist setzt das GUI-Framework Jameica sowie dadurch die verschiedenen 
		Plugins wie Hibiscus (Onlinebanking), JVerein (Vereinsverwaltung), Syntax (Finanzbuchhaltung) 
		oder Jollina (Lohnbuchhaltung) zwingend ein installiertes Java Runtime Environment (JRE) ab 
		Version 1.4 (JVerein ab 1.5) auf dem jeweils genutzten PC voraus. Jameica und die Plugins 
		selbst ben�tigen ja keine Installation, sondern m�ssen nur (richtig) entpackt werden.</p>
	<p>Zu Hause hat man zumeist eine JRE installiert. Wie ist es aber, wenn man z. B. in einem 
		Internetcafe o.�. sitzt und seine Onlinebanking-Gesch�fte nicht per Browser abwickeln m�chte? 
		Hier kann man nicht zwingend von einer installierten JRE ausgehen, somit kann man evtl. 
		Jameica inkl. Plugins nicht nutzen.</p>
	<p>Dieser Frage bin ich nachgegangen und habe durch Recherchen im Internet bzw. durch die 
		gegebenen M�glichkeiten von Jameica auch eine L�sung f�r Windows (ab 2000) gefunden, die ich 
		hiermit n�her vorstellen m�chte.</p>
	<h2>Was wird ben�tigt?</h2>
	<ul>
		<li> Jameica (<a href="http://www.willuhn.de/">http://www.willuhn.de</a>)</li>
		<li> mindestens 1 Plugin (s.o.)</li>
		<li> Java Portablizer (<a href="http://portableapps.com/apps/utilities/java_portable">
			http://portableapps.com/apps/utilities/java_portable</a>)</li>
		<li> USB-Stick oder anderer Wechseldatentr�ger mit mindestens 128MB freiem Speicherplatz* 
			(je nach Anzahl der installierten Plugins entsprechend mehr!)</li>
		<li> Zur Einrichtung: PC mit installierter JRE ab Version 1.4 (bei JVerein ab 1.5)</li>
	</ul>
	<p>*Der Speicherplatz wird haupts�chlich f�r die JRE (ca. 80MB) ben�tigt!</p>
	<h2>Vorgehensweise:</h2>
	<h3>1. �berpr�fung Java-Version auf dem PC</h3>
	<p>Auf dem PC, auf welchem die portable Version erstellt werden soll, muss zun�chst �berpr�ft werden, 
		ob und in welcher Version die JRE installiert ist.</p>
	<p>Hierzu �ffnen wir eine Eingabeaufforderung (Start -&gt; Programme -&gt; Zubeh�r -&gt; Eingabeaufforderung) 
		und geben den Befehl "java -version" ein:</p>
	<img src='images/Jameica_portable-img1.png' class='screenshot'>
	<p>Wir erhalten die aktuelle Version der JRE angezeigt, hier 1.6.0_02.</p>
	<p>Hinweis:</p>
	<p>Sollte auf dem PC noch kein JRE installiert sein, so muss dies zun�chst nachgeholt werden.</p>
	<p>Download JRE:<a href="https://cds.sun.com/is-bin/INTERSHOP.enfinity/WFS/CDS-CDS_Developer-Site/en_US/-/USD/ViewProductDetail-Start?ProductRef=jre-6u20-oth-JPR@CDS-CDS_Developer">
		https://cds.sun.com/is-bin/INTERSHOP.enfinity/WFS/CDS-CDS_Developer-Site/en_US/-/USD/ViewProductDetail-Start?ProductRef=jre-6u20-oth-JPR@CDS-CDS_Developer</a>
		(gefunden �ber die Seite: http://java.sun.com/javase/downloads/index.jsp  dort JRE unter dem linken Icon JAVA anklicken)
		</p>
	<p>Nach der Installation k�nnen Sie gegebenenfalls nochmals die Java-Version �berpr�fen, sollte 
		aber nicht mehr notwendig sein!</p>
	<h3>2. Entpacken von Jameica + Plugin(s) auf einen Wechseldatentr�ger</h3>
	<p>Falls noch nicht geschehen, laden Sie sich bitte das Paket Jameica f�r Windows auf Ihren PC, 
		ebenfalls das/die Plugins, die Sie auf dem Wechseldatentr�ger nutzen wollen.</p>
	<p>Downloadlinks:</p>
	<p><a href="http://www.willuhn.de/">http://www.willuhn.de</a></p>
	<p><a href="http://www.jverein.de/">http://www.jverein.de</a></p>
	<p><a href="http://www.jollina.de/">http://www.jollina.de</a></p>
	<p>Entpacken Sie nun zun�chst das Paket jameica-win32.zip auf Ihren Wechseldatentr�ger. Sie k�nnen 
		das Paket entweder direkt ins Root, also z.B. X:\ entpacken, oder aber in ein Unterverzeichnis 
		des Wechseldatentr�gers, z.B. X:/Portablesoftware.</p>
	<p>Anschlie�end entpacken Sie das/die Plugin(s) in das Pluginsverzeichnis von Jameica. In 
		vorgenannten Beispielen:</p>
	<p>X:\jameica\plugins</p>
	<p>bzw.</p>
	<p>X:\Portablesoftware\jameica\plugins</p>
	<h3>3. Java vom lokalen PC auf den Wechseldatentr�ger "kopieren"</h3>
	<p>Laden Sie sich, so noch nicht geschehen, den Java Portablizer von 
		<a href="http://portableapps.com/apps/utilities/java_portable">
		http://portableapps.com/apps/utilities/java_portable</a>
		herunter.</p>
	<p>Starten Sie den Java Portablizer.</p>
	<img src='images/Jameica_portable-img2.png' class='screenshot'>
	<p>Dieses Fenster einfach mit "Next" best�tigen.</p>
	<img src='images/Jameica_portable-img3.png' class='screenshot'>
	<p>Geben Sie als Destination Folder den Installations-Pfad von Jameica auf dem Wechseldatentr�ger 
		an und am besten als Unterverzeichnis zus�tzlich "Java". Klicken Sie anschlie�end auf "Install".</p>
	<p>Die notwendigen Java-Dateien werden nun ebenfalls auf den Stick kopiert.</p>
	<h3>4. jameica.bat an Java anpassen</h3>
	<p>Wechseln Sie in das Verzeichnis von Jameica auf dem Wechseldatentr�ger:</p>
	<img src='images/Jameica_portable-img4.png' class='screenshot'>
	<p>Mit der rechten Maustaste klicken Sie auf die Datei "jameica.bat" und w�hlen den Punkt 
		Bearbeiten.</p>
	<img src='images/Jameica_portable-img5.png' class='screenshot'>
	<p>Hier m�ssen Sie nun der Datei "javaw.exe" den richtigen Pfad mitgeben, damit Jameica auf das auf 
		dem Wechseldatentr�ger befindliche Java zugreift. In unserem Beispiel haben wir als Pfad "Java" 
		unterhalb des Installationspfades von Jameica gew�hlt. Die Datei "javaw.exe" befindet sich 
		hierunter nochmals im Verzeichnis "/bin".</p>
	<p>Statt "start javaw.exe ..." m�ssen Sie also "start Java/bin/javaw.exe ..." eingeben. Sollten Sie 
		einen anderen Installationspfad f�r die JRE gew�hlt haben, so m�ssen Sie den Pfad nat�rlich 
		entsprechend anpassen.</p>
	<p>�ber Datei -&gt; Speichern k�nnen Sie die angepasste "jameica.bat" speichern.</p>
	<h3>5. Startdatei f�r Daten auf dem USB-Stick erstellen</h3>
	<p>Da standardm��ig die Daten von Jameica bzw. deren Plugins nach</p>
	<p>&quot;C:\Dokumente und Einstellungen\&lt;benutzername&gt;\.jameica&quot;</p>
	<p>gespeichert werden, m�ssen wir noch eine Startdatei erstellen, damit die Daten auf dem 
		Wechseldatentr�ger gespeichert werden.</p>
	<p>Beachte:</p>
	<p>Das Datenverzeichnis muss au�erhalb des Programmverzeichnisses von Jameica liegen!!!</p>
	<p>Wechseln Sie zun�chst in das Programmverzeichnis von Jameica auf dem Wechseldatentr�ger:</p>
	<img src='images/Jameica_portable-img6.png' class='screenshot'>
	<p>Per rechter Maustaste erstellen Sie �ber "Neu -&gt; Textdokument" ein neues Dokument und geben diesem 
		z. B. den Namen "jameica-portable.bat".</p>
	<img src='images/Jameica_portable-img7.png' class='screenshot'>
	<p>Diese Meldung mit "Ja" best�tigen.</p>
	<img src='images/Jameica_portable-img8.png' class='screenshot'>
	<p>Mit der rechten Maustaste klicken Sie auf die eben erstellte Datei und w�hlen den Punkt 
		"Bearbeiten", es �ffnet sich ein leeres Editorfenster, in welches wir z. B. folgendes eingeben:</p>
	<img src='images/Jameica_portable-img9.png' class='screenshot'>
	<p>Beachte: Sollten Sie einen Pfad mit Unterordner(n) erstellen wollen z.B. "..\daten\jameica", 
		dann m�ssen die �bergeordneten Ordner manuell angelegt werden. Im Beispiel muss also der 
		Ordner ..\daten bereits angelegt sein!</p>
	<p>Sollte der von Ihnen gew�hlte Pfad ein Leerzeichen enthalten, so setzen Sie den Pfad bitte in 
		Anf�hrungszeichen, also so:</p>
	<img src='images/Jameica_portable-img10.png' class='screenshot'>
	<p>Anschlie�end speichern wir diese Datei �ber "Datei -&gt; Speichern" wieder ab.</p>
	<p><b>Fertig ist unsere portable Version von Jameica und dessen/deren Plugin(s)!!!</b></p>
	<p>Die portable Version von Jameica starten Sie bitte �ber die Datei "jameica-portable.bat". 
		Sollten Sie der Datei einen anderen Namen verpasst haben, dann entsprechend.</p>
	<p><center>Viel Spa�!!!</center></p>
	<p><b>Weitere Hinweise:</b></p>
	<ul>
		<li> Es w�re m�glich, dass man die JRE f�r den Betrieb von Jameica und dessen Plugins noch 
			um diverse Dateien bereinigen kann, um den Umfang der Installation zu verringern. Leider 
			fehlen mir dazu die Kenntnisse!</li>
		<li> Wer bereits ein Java auf seinem Wechseldatentr�ger "installiert" hat kann auch dieses 
			nutzen, muss aber entsprechend die Pfade in der Datei "jameica.bat" anpassen.</li>
		<li> F�r die weitere Einrichtung/Nutzung von Jameica bzw. der Plugins beachten Sie bitte 
			die Dokumentationen zum jeweiligen Produkt.</li>
	</ul>
	<p><b>Zusatzhinweis f�r Nutzer von komfortablen "Portable Apps Launcher"</b></p>
	<p>Es gibt diverse Tools mit denen man sich ein komfortables Startmen� f�r portable Programme 
		auf einem Wechseldatentr�ger erstellen kann.</p>
	<p>Sollte man ein solches im Einsatz haben, so sollte nat�rlich der Installationspfad entsprechend 
		deren Vorgaben gew�hlt werden.</p>
	<p>Durch den Einsatz eines solchen Programmes kann u. a. Punkt 5. (erstellen einer zus�tzlichen 
		Startdatei) entfallen, so man der Startdatei Parameter mitgeben kann.</p>
	<p>Hier am Beispiel von ASuite (<a href="http://www.salvadorsoftware.com/software/asuite">
		http://www.salvadorsoftware.com/software/asuite</a>) erl�utert.</p>
	<img src='images/Jameica_portable-img11.png' class='screenshot'>
	<p>In den Eigenschaften des Programmes:</p>
	<img src='images/Jameica_portable-img12.png' class='screenshot'>
	
	<!-- 
    $Log: jameica_portable.php,v $
    Revision 1.3  2010/05/20 18:37:23  jost
    *** empty log message ***

    Revision 1.2  2009/06/20 18:38:05  jost
    Umlaute korrekt darstellen.

    Revision 1.1  2009/05/08 14:46:22  jost
    shtml - php

    Revision 1.2  2009/05/03 15:45:50  jost
    Encoding korrigiert.

    Revision 1.1  2009/05/03 15:33:30  jost
    Neue Homepage

    -->
	
<? include ("footer.inc"); ?>

