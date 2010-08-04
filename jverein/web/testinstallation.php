<? include ("frame.inc"); ?>
    <h1>Installation von Testversionen</h1>
    <h2>Wichtig!</h2>
    <p>Bei der Arbeit mit Testversionen kann immer etwas unvorhersehbares passieren. Daher ist
       es wichtig, eine Datensicherung anzufertigen. Der Programm- und der Datenordner 
       (<a href="faq.php#programmordner">siehe auch FAQ</a>) werden auf einen USB-Stick oder ein
       anderes externes Medium kopiert.
    </p>
    <h2>Programminstallation</h2>
    <p>Die Installation von Entwicklerversionen ist <a href='installation.php#entwicklerversion'>hier</a>
    beschrieben. Wichtig ist die Installation in ein "frisches" Verzeichnis. Z. B. jvereintest.</p>
    <h2>Programmstart</h2>
    <p>Der Programmstart kann je nach Vorliebe entweder �ber ein einzurichtendes 
    <a href="http://de.wikipedia.org/wiki/Icon_%28Computer%29">Icon</a> oder �ber die
    <a href="http://de.wikipedia.org/wiki/Kommandozeile">Kommandozeile</a> erfolgen. Gestartet wird
    das Skript f�r das entsprechende Betriebssystem aus dem neu eingerichteten Programmordner. Zus�tzlich
    wird der Schalter -f &gt;datenordner&lt; angegeben.</p>
    <p>
    Beispiel f�r Linux:
    </p>
    <code>/home/heiner/jvereintest/jameica.sh -f /home/heiner/jverein.test</code>
    <p>
    Wichtig! Der Datenordner darf nicht innerhalb des Programmordners liegen. Wird der Schalter -f vergessen,
    wird das <a href="faq.php#programmordner">Standardordner</a> �berschrieben.</p>
    </p>
    <!-- 
    $Log: testinstallation.php,v $
    Revision 1.1  2010/08/04 11:36:59  jost
    Neu

    -->
       
<? include ("footer.inc"); ?>

