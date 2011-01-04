<? include ("frame.inc"); ?>
    <h1>FAQ</h1>
    <h2>Inhaltsverzeichnis</h2>
    <a href='#neuerlaptop'>Wie kann ich JVerein auf einen neuen Rechner bringen?</a><br>
    <a href='#programmordner'>Wo liegt der JVerein-Programmordner?</a><br>
    <a href='#datenordner'>Wo liegt der JVerein-Datenordner standardm��ig?</a><br>
    <a href='#datenordnerverschieben'>Wie kann ich den Datenordner an einen Nichtstandardplatz legen?</a><br>
    <a href='#internet'>Kann JVerein �ber ein Netzwerk betrieben werden?</a><br>
    <br>
    <a name="neuerlaptop"></a>
    <p>Q: Ich habe mir einen neuen Laptop zugelegt. Nun m�chte ich von meinem alten Laptop alles auf den Neuen �bertragen.
          Welche Dateien bzw. Ordner muss ich mitnehmen? Muss ich das Prog. neu installieren?<br>
       A: Es muss der Programm- und der Datenordner auf den neuen Rechner �bertragen werden.</p>
    <a name="programmordner"></a>
    <p>Q: Wo finde ich den Programmordner?<br>
       A: Es gibt keinen fest definierten Platz f�r den Programmordner. Sofern die Jameica-Suite
          installiert wurde, ist der Ordner unter c:\Programme\Jameica zu finden. Unter Linux 
          wird oft unter /opt/jameica oder ~/jameica installiert.</p>
    <a name="datenordner"></a>
    <p>Q: Wo finde ich den Datenordner standardm��ig?<br>
       A:
          <table>
          <tr>
          <th>Betriebssystem</th><th>Verzeichnis</th>
          </tr>
          <tr>
          <td>Linux</td><td>/home/&lt;username&gt;/.jameica</td>
          </tr>
          <tr>
          <td>Windows 2000/XP</td><td>C:\Dokumente und Einstellungen\&lt;username&gt;\.jameica</td>
          </tr>
          <tr>
          <td>Windows Vista</td><td>C:\Users\&lt;username&gt;\.jameica oder C:\Benutzer\&lt;username&gt;\.jameica</td>
          </tr>
          <tr>
          <td>MacOS</td><td>/Users/&lt;username&gt;/.jameica oder /Users/&lt;username&gt;/Library/jameica (falls Jameica 1.7 die erste benutzte Version war, dann letzteres, sonst das erst-genannte)</td>
          </tr>
          </table>    
          </p>
    <a name="datenordner"></a>
    <p>Q: Wie kann ich den Datenordner an einen Nichtstandardplatz legen?<br>
       A:
          <p>
          Beim Aufruf von Jameica wird der Schalter <code>-f pfad</code> angegeben.</p>
          <p>Beispiel: jameica.bat -f c:/meinejameicadaten</p>
          <p>siehe auch <a href='http://jameica.berlios.de/doku.php?id=support:install#optionale_startparameter'>Jameica-Doku</a>.
    <a name='internet'></a>
          <p>Unter Windows kann mit einem rechten Mausklick auf das Jameica-Icon &gt; Eigenschaften folgendes Bild ge�ffnet
             werden:</p>
          <img src='../images/jameicasuiteeigenschaften.png' class='screenshot'>
          <p>Im Feld Ziel wird der Schalter -f VERZEICHNIS wie angegeben ver�ndert.</p>
    <p>Q: Kann JVerein �bers Netzwerk betrieben werden?<br>
       A: JVerein kann seine Daten in einer MySQL-Datenbank speichern. Siehe auch 
          <a href='http://www.jverein.de/dokumentationmysql.php'>MySQL-Support</a>.
          Beim Betrieb �ber das Internet ist darauf zu achten, dass die Daten 
          verschl�sselt �bertragen werden. Weiterhin wird darauf hingewiesen, dass Jameica,
          Hibiscus und JVerein keine Benutzerverwaltung haben. Jeder, der Zugriff auf das
          Verfahren hat, kann alles machen. Beim Umgang mit Geld sicher eine nicht
          befriedigende Angelegenheit. Es ist auch nicht nachvollziehbar, wer welche
          �nderung vorgenommen hat. Mir stellt sich allerdings immer wieder die Frage nach
          dem "Warum?". Meines Erachtens k�nnen die Daten durch eine Person gepflegt werden.
          Ich bin selber Kassenwart eines Vereins mit ca. 400 Mitgliedern und wei� somit 
          wovon ich rede. Den �brigen Vorstandsmitgliedern k�nnen PDF-Dokumente oder CSV-Dateien 
          zur Serienbriefgenerierung zur Verf�gung
          gestellt werden. Darin kann auch ohne Probleme gesucht werden.</p> 
    <!-- 
    $Log: faq.php,v $
    Revision 1.8  2011/01/04 21:15:58  jost
    *** empty log message ***

    Revision 1.7  2010-10-04 14:32:55  jost
    *** empty log message ***

    Revision 1.6  2010-10-01 09:06:16  jost
    *** empty log message ***

    Revision 1.5  2010-10-01 08:37:54  jost
    Datenverzeichnis verschieben.

    Revision 1.4  2010-08-08 14:46:12  jost
    Neuer Beitrag und Inhaltsverzeichnis

    Revision 1.3  2010-08-05 07:52:52  jost
    neuer Anker

    Revision 1.2  2010-08-04 11:36:38  jost
    redakt

    Revision 1.1  2010-08-04 10:41:39  jost
    neu: FAQ

    -->
       
<? include ("footer.inc"); ?>

